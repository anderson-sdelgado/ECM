package br.com.usinasantafe.ecm.util;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
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
import br.com.usinasantafe.ecm.control.ConfigCTR;
import br.com.usinasantafe.ecm.control.MotoMecCTR;
import br.com.usinasantafe.ecm.model.bean.estaticas.EquipBean;
import br.com.usinasantafe.ecm.model.bean.estaticas.RAtivOSBean;
import br.com.usinasantafe.ecm.model.bean.estaticas.ROSAtivBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.CabecCLBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.PreCECBean;
import br.com.usinasantafe.ecm.util.connHttp.PostVerGenerico;
import br.com.usinasantafe.ecm.model.pst.GenericRecordable;
import br.com.usinasantafe.ecm.model.bean.estaticas.ItemCLBean;
import br.com.usinasantafe.ecm.model.bean.AtualAplicBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.BoletimBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.ConfigBean;
import br.com.usinasantafe.ecm.util.connHttp.UrlsConexaoHttp;

/**
 * Created by anderson on 16/11/2015.
 */
public class VerifDadosServ {

    private static VerifDadosServ instance = null;
    private GenericRecordable genericRecordable;
    private UrlsConexaoHttp urlsConexaoHttp;
    private Context telaAtual;
    private Class telaProx;
    private ProgressDialog progressDialog;
    private String dado;
    private String tipo;
    private AtualAplicBean atualAplicBean;
    private MenuInicialActivity menuInicialActivity;
    private PostVerGenerico postVerGenerico;
    private boolean verTerm;

    public VerifDadosServ() {
        //genericRecordable = new GenericRecordable();
    }

    public static VerifDadosServ getInstance() {
        if (instance == null)
            instance = new VerifDadosServ();
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

    public void verAtualAplic(String versaoAplic, MenuInicialActivity menuInicialActivity, ProgressDialog progressDialog) {

        AtualAplicBean atualAplicBean = new AtualAplicBean();
        ConfigCTR configCTR = new ConfigCTR();
        atualAplicBean.setIdEquipAtualizacao(configCTR.getConfig().getCodEquipConfig());
        atualAplicBean.setVersaoAtual(versaoAplic);

        urlsConexaoHttp = new UrlsConexaoHttp();
        this.progressDialog = progressDialog;
        this.tipo = "Atualiza";
        this.menuInicialActivity = menuInicialActivity;

        JsonArray jsonArray = new JsonArray();

        Gson gson = new Gson();
        jsonArray.add(gson.toJsonTree(atualAplicBean, atualAplicBean.getClass()));

        JsonObject json = new JsonObject();
        json.add("dados", jsonArray);

        Log.i("PMM", "LISTA = " + json.toString());

        String[] url = {urlsConexaoHttp.urlVerifica(tipo)};
        Map<String, Object> parametrosPost = new HashMap<String, Object>();
        parametrosPost.put("dado", json.toString());

        postVerGenerico = new PostVerGenerico();
        postVerGenerico.setParametrosPost(parametrosPost);
        postVerGenerico.execute(url);

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
            PreCECBean preCECBean = new PreCECBean();

            List viagemCana = preCECBean.all();

            for (int i = 0; i < viagemCana.size(); i++) {

                preCECBean = (PreCECBean) viagemCana.get(i);
                Gson gson = new Gson();
                jsonArray.add(gson.toJsonTree(preCECBean, preCECBean.getClass()));

            }

            JsonObject json = new JsonObject();
            json.add("dados", jsonArray);

            parametrosPost.put("dado", json.toString());
            Log.i("ECM", "DADOS = " + json.toString());
            preCECBean.deleteAll();

        }
        else{
            parametrosPost.put("dado", String.valueOf(dado));
            Log.i("ECM", "DADOS = " + String.valueOf(dado));
        }


        postVerGenerico = new PostVerGenerico();
        postVerGenerico.setParametrosPost(parametrosPost);
        if(this.tipo.equals("BoletimBeanViagem")) {
            this.tipo = "BoletimBean";
        }

        Log.i("ECM", "TIPO = " + tipo);

        postVerGenerico.setTipo(this.tipo);
        postVerGenerico.execute(url);

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

                ROSAtivBean RAtivOSBean = gson.fromJson(objeto.toString(), ROSAtivBean.class);
                RAtivOSBean.insert();

                this.progressDialog.dismiss();
                Intent it = new Intent(telaAtual, telaProx);
                telaAtual.startActivity(it);

            }

        } catch (Exception e) {
            Log.i("ERRO", "Erro Manip = " + e);
        }

    }

    public void cabecCheckList(String data){

        ConfigBean configBean = new ConfigBean();
        List listConfigBean = configBean.all();
        configBean = (ConfigBean) listConfigBean.get(0);

        EquipBean equipBean = new EquipBean();
        List caminhaoList = equipBean.get("idCaminhao", configBean.getIdEquipConfig());
        equipBean = (EquipBean) caminhaoList.get(0);
        caminhaoList.clear();

        ItemCLBean itemCLBean = new ItemCLBean();
        List itemCheckList =  itemCLBean.get("idChecklist", equipBean.getIdCheckListEquip());
        Long qtde = (long) itemCheckList.size();
        itemCheckList.clear();

        CabecCLBean cabecCLBean = new CabecCLBean();
        cabecCLBean.setDtCabCL(Tempo.getInstance().dataComHora());
        cabecCLBean.setEquipCabCL(configBean.getCodEquipConfig());
        cabecCLBean.setFuncCabCL(configBean.getMatricColabConfig());
        cabecCLBean.setTurnoCabCL(configBean.getIdTurnoConfig());
        cabecCLBean.setStatusCabCL(1L);
        cabecCLBean.insert();

    }

    public void verDadosInfor() {

        verTerm = true;
        urlsConexaoHttp = new UrlsConexaoHttp();
        this.tipo = "Informativo";
        ConfigCTR configCTR = new ConfigCTR();
        MotoMecCTR motoMecCTR = new MotoMecCTR();
        this.dado = String.valueOf(motoMecCTR.getBolAberto().getMatricFuncBolMM());
    }

    public void pulaTelaSemTerm(){
        this.progressDialog.dismiss();
        Intent it = new Intent(telaAtual, telaProx);
        telaAtual.startActivity(it);
    }

    public void msgSemTerm(String texto){
        this.progressDialog.dismiss();
        AlertDialog.Builder alerta = new AlertDialog.Builder(telaAtual);
        alerta.setTitle("ATENÇÃO");
        alerta.setMessage(texto);
        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
            }
        });
        alerta.show();
    }

    public void pulaTelaComTerm(){
        if(!verTerm){
            this.progressDialog.dismiss();
            this.verTerm = true;
            Intent it = new Intent(telaAtual, telaProx);
            telaAtual.startActivity(it);
        }
    }

    public void msgComTerm(String texto){
        if(!verTerm){
            this.progressDialog.dismiss();
            this.verTerm = true;
            AlertDialog.Builder alerta = new AlertDialog.Builder(telaAtual);
            alerta.setTitle("ATENÇÃO");
            alerta.setMessage(texto);
            alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // TODO Auto-generated method stub
                }
            });
            alerta.show();
        }
    }

    public void pulaTelaComTermSemBarra(){
        if(!verTerm){
            this.verTerm = true;
            Intent it = new Intent(telaAtual, telaProx);
            telaAtual.startActivity(it);
        }
    }

    public void cancelVer() {
        verTerm = true;
        if (postVerGenerico.getStatus() == AsyncTask.Status.RUNNING) {
            postVerGenerico.cancel(true);
        }
    }

    public boolean isVerTerm() {
        return verTerm;
    }

    public void setVerTerm(boolean verTerm) {
        this.verTerm = verTerm;
    }

}
