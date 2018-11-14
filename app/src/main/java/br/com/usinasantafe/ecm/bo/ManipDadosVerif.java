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
import br.com.usinasantafe.ecm.to.tb.variaveis.AtualizaTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.BoletimTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.CompVCanaTO;

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

    public void manipularDadosHttp(String result, String tipo) {

        if (!result.equals("")) {
            if (tipo.equals("BoletimTO")){
                retornoBoletim(result);
            } else {
                retornoVerifNormal(result, tipo);
            }

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

    public void retornoVerifNormal(String result, String tipo) {

        try {

            JSONObject jObj = new JSONObject(result);
            JSONArray jsonArray = jObj.getJSONArray("dados");
            Class classe = Class.forName(manipLocalClasse(tipo));

            if (jsonArray.length() > 0) {

                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject objeto = jsonArray.getJSONObject(i);
                    Gson gson = new Gson();
                    genericRecordable = new GenericRecordable();
                    genericRecordable.insert(gson.fromJson(objeto.toString(), classe), classe);

                }

                Intent it = new Intent(telaAtual, telaProx);
                telaAtual.startActivity(it);

            } else {

                AlertDialog.Builder alerta = new AlertDialog.Builder(telaAtual);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage(variavel + " INEXISTENTE NA BASE DE DADOS.");

                alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                    }
                });
                alerta.show();

            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            Log.i("ERRO", "Erro Manip = " + e);
        }

    }

    public void retornoBoletim(String result) {

        try {

            JSONObject jObj = new JSONObject(result);
            JSONArray jsonArray = jObj.getJSONArray("dados");

            JSONObject objeto = jsonArray.getJSONObject(0);
            Gson gson = new Gson();
            BoletimTO boletimTO = gson.fromJson(objeto.toString(), BoletimTO.class);
            boletimTO.insert();

            this.progressDialog.dismiss();
            Intent it = new Intent(telaAtual, telaProx);
            telaAtual.startActivity(it);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            Log.i("ERRO", "Erro Manip2 = " + e);
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

}
