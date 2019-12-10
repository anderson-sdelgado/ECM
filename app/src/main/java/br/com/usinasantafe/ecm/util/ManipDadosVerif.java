package br.com.usinasantafe.ecm.util;

import android.app.ProgressDialog;
import android.content.Context;
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

import br.com.usinasantafe.ecm.MenuInicialActivity;
import br.com.usinasantafe.ecm.model.bean.estaticas.RAtivOSBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.CertifCanaBean;
import br.com.usinasantafe.ecm.util.connection.ConHttpPostVerGenerico;
import br.com.usinasantafe.ecm.model.bean.pst.GenericRecordable;
import br.com.usinasantafe.ecm.model.bean.estaticas.CaminhaoBean;
import br.com.usinasantafe.ecm.model.bean.estaticas.ItemCLBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.AtualizaBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.BoletimBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.CabecCheckListBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.ConfigBean;

/**
 * Created by anderson on 16/11/2015.
 */
public class ManipDadosVerif {

    private static ManipDadosVerif instance = null;
    private GenericRecordable genericRecordable;
    private UrlsConexaoHttp urlsConexaoHttp;
    private Context telaAtual;
    private Class telaProx;
    private ProgressDialog progressDialog;
    private String dado;
    private String tipo;
    private AtualizaBean atualizaBean;
    private MenuInicialActivity menuInicialActivity;
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

    public void verAtualizacao(AtualizaBean atualizaBean, MenuInicialActivity menuInicialActivity, ProgressDialog progressDialog) {

        urlsConexaoHttp = new UrlsConexaoHttp();
        this.atualizaBean = atualizaBean;
        this.progressDialog = progressDialog;
        this.tipo = "Atualiza";
        this.menuInicialActivity = menuInicialActivity;

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


    public void verDados(String dado, String tipo, Context telaAtual, Class telaProx) {

        urlsConexaoHttp = new UrlsConexaoHttp();
        this.telaAtual = telaAtual;
        this.telaProx = telaProx;
        this.dado = dado;
        this.tipo = tipo;

        envioDados();

    }

    public void envioDados() {

        Map<String, Object> parametrosPost = new HashMap<String, Object>();
        String[] url = {urlsConexaoHttp.urlVerifica(tipo)};

        Log.i("ECM", "URL = " + urlsConexaoHttp.urlVerifica(tipo));

        if(this.tipo.equals("BoletimBeanViagem")) {

            JsonArray jsonArray = new JsonArray();
            CertifCanaBean certifCanaBean = new CertifCanaBean();

            List viagemCana = certifCanaBean.all();

            for (int i = 0; i < viagemCana.size(); i++) {

                certifCanaBean = (CertifCanaBean) viagemCana.get(i);
                Gson gson = new Gson();
                jsonArray.add(gson.toJsonTree(certifCanaBean, certifCanaBean.getClass()));

            }

            JsonObject json = new JsonObject();
            json.add("dados", jsonArray);

            parametrosPost.put("dado", json.toString());
            Log.i("ECM", "DADOS = " + json.toString());
            certifCanaBean.deleteAll();

        }
        else{
            parametrosPost.put("dado", String.valueOf(dado));
            Log.i("ECM", "DADOS = " + String.valueOf(dado));
        }


        conHttpPostVerGenerico = new ConHttpPostVerGenerico();
        conHttpPostVerGenerico.setParametrosPost(parametrosPost);
        if(this.tipo.equals("BoletimBeanViagem")) {
            this.tipo = "BoletimBean";
        }

        Log.i("ECM", "TIPO = " + tipo);

        conHttpPostVerGenerico.setTipo(this.tipo);
        conHttpPostVerGenerico.execute(url);

    }

    public void retornoVerifNormal(String result) {

        try {

            if(this.tipo.equals("BoletimBean")) {

                JSONObject jObj = new JSONObject(result);
                JSONArray jsonArray = jObj.getJSONArray("dados");

                JSONObject objeto = jsonArray.getJSONObject(0);
                Gson gson = new Gson();
                BoletimBean boletimBean = gson.fromJson(objeto.toString(), BoletimBean.class);
                boletimBean.insert();

                this.progressDialog.dismiss();
                Intent it = new Intent(telaAtual, telaProx);
                telaAtual.startActivity(it);

            }
            else if(this.tipo.equals("Atualiza")) {

                String verAtualizacao = result.trim();

                if(verAtualizacao.equals("S")){
                    AtualizarAplicativo atualizarAplicativo = new AtualizarAplicativo();
                    atualizarAplicativo.setContext(this.menuInicialActivity);
                    atualizarAplicativo.execute();
                }
                else{
                    this.menuInicialActivity.startTimer(verAtualizacao);
                }

            }
            else if(this.tipo.equals("CheckList")) {

                if (!result.contains("exceeded")) {

                    JSONObject jObj = new JSONObject(result);
                    JSONArray jsonArray = jObj.getJSONArray("dados");
                    Class classe = Class.forName(urlsConexaoHttp.localPSTEstatica + "ItemCLBean");

                    if (jsonArray.length() > 0) {

                        genericRecordable = new GenericRecordable();
                        genericRecordable.deleteAll(classe);

                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject objeto = jsonArray.getJSONObject(i);
                            Gson gson = new Gson();
                            genericRecordable.insert(gson.fromJson(objeto.toString(), classe), classe);

                        }

                    }

                    cabecCheckList(Tempo.getInstance().dataComHora());
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
            else if(this.tipo.equals("RAtivOSBean")) {

                JSONObject jObj = new JSONObject(result);
                JSONArray jsonArray = jObj.getJSONArray("dados");

                JSONObject objeto = jsonArray.getJSONObject(0);
                Gson gson = new Gson();
                RAtivOSBean RAtivOSBean = gson.fromJson(objeto.toString(), RAtivOSBean.class);
                RAtivOSBean.insert();

                this.progressDialog.dismiss();
                Intent it = new Intent(telaAtual, telaProx);
                telaAtual.startActivity(it);

            }

        } catch (Exception e) {
            Log.i("ERRO", "Erro Manip = " + e);
        }

    }


    public void envioAtualizacao(){

        JsonArray jsonArray = new JsonArray();

        Gson gson = new Gson();
        jsonArray.add(gson.toJsonTree(atualizaBean, atualizaBean.getClass()));

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

        ConfigBean configBean = new ConfigBean();
        List listConfigBean = configBean.all();
        configBean = (ConfigBean) listConfigBean.get(0);

        CaminhaoBean caminhaoBean = new CaminhaoBean();
        List caminhaoList = caminhaoBean.get("idCaminhao", configBean.getIdEquipConfig());
        caminhaoBean = (CaminhaoBean) caminhaoList.get(0);
        caminhaoList.clear();

        ItemCLBean itemCLBean = new ItemCLBean();
        List itemCheckList =  itemCLBean.get("idChecklist", caminhaoBean.getIdChecklist());
        Long qtde = (long) itemCheckList.size();
        itemCheckList.clear();

        CabecCheckListBean cabecCheckListBean = new CabecCheckListBean();
        cabecCheckListBean.setDtCabecCheckList(Tempo.getInstance().dataComHora());
        cabecCheckListBean.setEquipCabecCheckList(configBean.getCodEquipConfig());
        cabecCheckListBean.setFuncCabecCheckList(configBean.getMatricColabConfig());
        cabecCheckListBean.setTurnoCabecCheckList(configBean.getIdTurnoConfig());
        cabecCheckListBean.setQtdeItemCabecCheckList(qtde);
        cabecCheckListBean.setStatusCabecCheckList(1L);
        cabecCheckListBean.insert();

    }

}
