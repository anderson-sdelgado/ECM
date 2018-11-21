package br.com.usinasantafe.ecm.bo;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.usinasantafe.ecm.PrincipalActivity;
import br.com.usinasantafe.ecm.conWEB.ConHttpPostCadGenerico;
import br.com.usinasantafe.ecm.conWEB.ConHttpPostVerGenerico;
import br.com.usinasantafe.ecm.conWEB.UrlsConexaoHttp;
import br.com.usinasantafe.ecm.pst.GenericRecordable;
import br.com.usinasantafe.ecm.to.tb.estaticas.CaminhaoTO;
import br.com.usinasantafe.ecm.to.tb.estaticas.ItemCheckListTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.AtualizaTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.BoletimTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.CabecCheckListTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.CompVCanaTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.ConfiguracaoTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.InfBoletimTO;

/**
 * Created by anderson on 16/11/2015.
 */
public class ManipDadosVerif {

    private static ManipDadosVerif instance = null;
    private GenericRecordable genericRecordable;
    private UrlsConexaoHttp urlsConexaoHttp;
    private Context telaAtual;
    private Class telaProx;
    private String variavel;
    private int qtde;
    private ProgressDialog progressDialog;
    private String dado;
    private String tipo;
    private AtualizaTO atualizaTO;
    private PrincipalActivity principalActivity;
    private ConHttpPostVerGenerico conHttpPostVerGenerico;

    public ManipDadosVerif() {
        //genericRecordable = new GenericRecordable();
    }

    public static ManipDadosVerif getInstance() {
        if (instance == null)
            instance = new ManipDadosVerif();
        return instance;
    }

    public void manipularDadosHttp(String result) {

        if (!result.equals("")) {
            retornoVerifNormal(result);
        }

    }

    public String manipLocalClasse(String classe) {
        if (classe.contains("TO")) {
            classe = urlsConexaoHttp.localPSTEstatica + classe;
        }
        return classe;
    }

    public void verAtualizacao(AtualizaTO atualizaTO, PrincipalActivity principalActivity, ProgressDialog progressDialog) {

        urlsConexaoHttp = new UrlsConexaoHttp();
        this.atualizaTO = atualizaTO;
        this.progressDialog = progressDialog;
        this.tipo = "Atualiza";
        this.principalActivity = principalActivity;

        envioAtualizacao();

    }

    public void verDados(String dado, String tipo, Context telaAtual, Class telaProx, ProgressDialog progressDialog) {

        urlsConexaoHttp = new UrlsConexaoHttp();
        this.telaAtual = telaAtual;
        this.telaProx = telaProx;
        this.progressDialog = progressDialog;
        this.dado = dado;
        this.tipo = tipo;

        envioDados();

    }


    public void verDados(String dado, String tipo, Context telaAtual, Class telaProx, String variavel) {

        urlsConexaoHttp = new UrlsConexaoHttp();
        this.telaAtual = telaAtual;
        this.telaProx = telaProx;
        this.variavel = variavel;
        this.dado = dado;
        this.tipo = tipo;

        envioDados();

    }

    public void envioDados() {

        Map<String, Object> parametrosPost = new HashMap<String, Object>();
        String[] url = {urlsConexaoHttp.urlVerifica(tipo)};

        Log.i("ECM", "URL = " + urlsConexaoHttp.urlVerifica(tipo));

        if(this.tipo.equals("BoletimTOViagem")) {

            JsonArray jsonArray = new JsonArray();
            CompVCanaTO compVCanaTO = new CompVCanaTO();

            List viagemCana = compVCanaTO.all();

            for (int i = 0; i < viagemCana.size(); i++) {

                compVCanaTO = (CompVCanaTO) viagemCana.get(i);
                Gson gson = new Gson();
                jsonArray.add(gson.toJsonTree(compVCanaTO, compVCanaTO.getClass()));

            }

            JsonObject json = new JsonObject();
            json.add("dados", jsonArray);

            Log.i("ECM", "LISTA = " + json.toString());

            parametrosPost.put("dado", json.toString());
            compVCanaTO.deleteAll();

        }
        else{
            parametrosPost.put("dado", String.valueOf(dado));
        }


        conHttpPostVerGenerico = new ConHttpPostVerGenerico();
        conHttpPostVerGenerico.setParametrosPost(parametrosPost);
        if(this.tipo.equals("BoletimTOViagem")) {
            this.tipo = "BoletimTO";
        }
        conHttpPostVerGenerico.setTipo(this.tipo);
        Log.i("ECM", "TIPO = " + tipo);

        conHttpPostVerGenerico.execute(url);

    }

    public void retornoVerifNormal(String result) {

        try {

            if(this.tipo.equals("BoletimTO")) {

                JSONObject jObj = new JSONObject(result);
                JSONArray jsonArray = jObj.getJSONArray("dados");

                JSONObject objeto = jsonArray.getJSONObject(0);
                Gson gson = new Gson();
                BoletimTO boletimTO = gson.fromJson(objeto.toString(), BoletimTO.class);
                boletimTO.insert();

                this.progressDialog.dismiss();
                Intent it = new Intent(telaAtual, telaProx);
                telaAtual.startActivity(it);

            }
            else if(this.tipo.equals("Atualiza")) {

                String verAtualizacao = result.trim();

                if(verAtualizacao.equals("S")){
                    AtualizarAplicativo atualizarAplicativo = new AtualizarAplicativo();
                    atualizarAplicativo.setContext(this.principalActivity);
                    atualizarAplicativo.execute();
                }
                else{
                    this.principalActivity.startTimer(verAtualizacao);
                }

            }else if(this.tipo.equals("CheckList")) {

                if (!result.contains("exceeded")) {

                    JSONObject jObj = new JSONObject(result);
                    JSONArray jsonArray = jObj.getJSONArray("dados");
                    Class classe = Class.forName(urlsConexaoHttp.localPSTEstatica + "ItemCheckListTO");

                    if (jsonArray.length() > 0) {

                        genericRecordable = new GenericRecordable();
                        genericRecordable.deleteAll(classe);

                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject objeto = jsonArray.getJSONObject(i);
                            Gson gson = new Gson();
                            genericRecordable.insert(gson.fromJson(objeto.toString(), classe), classe);

                        }

                    }

                    cabecCheckList(Tempo.getInstance().datahora());
                    this.progressDialog.dismiss();
                    Intent it = new Intent(telaAtual, telaProx);
                    telaAtual.startActivity(it);

                }
                else{

                    cabecCheckList("0");
                    this.progressDialog.dismiss();
                    Intent it = new Intent(telaAtual, telaProx);
                    telaAtual.startActivity(it);

                }


            }

        } catch (Exception e) {
            Log.i("ERRO", "Erro Manip = " + e);
        }

    }


    public void envioAtualizacao(){

        JsonArray jsonArray = new JsonArray();

        Gson gson = new Gson();
        jsonArray.add(gson.toJsonTree(atualizaTO, atualizaTO.getClass()));

        JsonObject json = new JsonObject();
        json.add("dados", jsonArray);

        Log.i("PMM", "LISTA = " + json.toString());

        String[] url = {urlsConexaoHttp.urlVerifica(tipo)};
        Map<String, Object> parametrosPost = new HashMap<String, Object>();
        parametrosPost.put("dado", json.toString());

        conHttpPostVerGenerico = new ConHttpPostVerGenerico();
        conHttpPostVerGenerico.setParametrosPost(parametrosPost);
        conHttpPostVerGenerico.execute(url);

    }

    public void cabecCheckList(String data){

        InfBoletimTO infBoletimTO = new InfBoletimTO();
        List lTurno = infBoletimTO.all();
        infBoletimTO = (InfBoletimTO) lTurno.get(0);

        ConfiguracaoTO configTO = new ConfiguracaoTO();
        List listConfigTO = configTO.all();
        configTO = (ConfiguracaoTO) listConfigTO.get(0);

        CaminhaoTO caminhaoTO = new CaminhaoTO();
        List caminhaoList = caminhaoTO.get("idCaminhao", configTO.getIdCamConfig());
        caminhaoTO = (CaminhaoTO) caminhaoList.get(0);
        caminhaoList.clear();

        ItemCheckListTO itemCheckListTO = new ItemCheckListTO();
        List itemCheckList =  itemCheckListTO.get("idChecklist", caminhaoTO.getIdChecklist());
        Long qtde = (long) itemCheckList.size();
        itemCheckList.clear();

        CabecCheckListTO cabecCheckListTO = new CabecCheckListTO();
        cabecCheckListTO.setDtCabecCheckList(Tempo.getInstance().datahora());
        cabecCheckListTO.setEquipCabecCheckList(configTO.getCodCamConfig());
        cabecCheckListTO.setFuncCabecCheckList(infBoletimTO.getCodigoMoto());
        cabecCheckListTO.setTurnoCabecCheckList(infBoletimTO.getTurno());
        cabecCheckListTO.setQtdeItemCabecCheckList(qtde);
        cabecCheckListTO.setStatusCabecCheckList(1L);
        cabecCheckListTO.setDtAtualCheckList("0");
        cabecCheckListTO.insert();

    }

}
