package br.com.usinasantafe.ecm.model.dao;

import android.app.ProgressDialog;
import android.content.Context;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import br.com.usinasantafe.ecm.model.bean.variaveis.CECBean;
import br.com.usinasantafe.ecm.util.VerifDadosServ;

public class CECDAO {

    public CECDAO() {
    }

    public boolean verCEC(){
        List cecList = getCECList();
        boolean retorno = cecList.size() > 0;
        cecList.clear();
        return retorno;
    }

    public List getCECList(){
        CECBean cecBean = new CECBean();
        List equipList = cecBean.get("status", 2L);
        return equipList;
    }

    public void verCEC(String dado, Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        VerifDadosServ.getInstance().setVerTerm(false);
        VerifDadosServ.getInstance().verDados(dado, "CEC", telaAtual, telaProx, progressDialog);
    }

    public void recDadosCEC(String result){

        try {

            if (!result.contains("exceeded")) {

                JSONObject jObj = new JSONObject(result.trim());
                JSONArray jsonArray = jObj.getJSONArray("cec");

                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject objeto = jsonArray.getJSONObject(i);
                    Gson gson = new Gson();
                    CECBean cecBean = gson.fromJson(objeto.toString(), CECBean.class);
                    cecBean.insert();

                }

                VerifDadosServ.getInstance().pulaTelaComTerm();

            } else {
                VerifDadosServ.getInstance().envioDados();
            }

        } catch (Exception e) {
            VerifDadosServ.getInstance().envioDados();
        }

    }

}
