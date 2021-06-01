package br.com.usinasantafe.ecm.model.dao;

import android.content.Context;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.usinasantafe.ecm.control.ConfigCTR;
import br.com.usinasantafe.ecm.model.bean.variaveis.InfColheitaBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.InfPlantioBean;
import br.com.usinasantafe.ecm.util.VerifDadosServ;

public class InformativoDAO {

    public InformativoDAO() {
    }

    public void verInformativo(String dado, Context telaAtual, Class telaProx){
        VerifDadosServ.getInstance().setVerTerm(false);
        VerifDadosServ.getInstance().verDados(dado, "Informativo", telaAtual, telaProx);
    }

    public void recInfor(String retorno){

        ConfigCTR configCTR = new ConfigCTR();

        try {

            if (!retorno.contains("exceeded")) {

                int pos1 = retorno.trim().indexOf("=") + 1;
                int pos2 = retorno.trim().indexOf("_") + 1;
                int tipo = Integer.valueOf(retorno.trim().substring(pos1, (pos2 - 1)));

                String dados = retorno.substring(pos2);

                if(tipo == 1){
                    recPlantio(dados);
                }
                else if(tipo == 3){
                    recColheita(dados);
                }
                else{
                    if(configCTR.getVerInforConfig() == 0L) {
                        configCTR.setVerInforConfig(3L);
                        VerifDadosServ.getInstance().pulaTelaComTermSemBarra();
                    }
                }

            } else {
                if(configCTR.getVerInforConfig() == 0L) {
                    configCTR.setVerInforConfig(1L);
                    VerifDadosServ.getInstance().pulaTelaComTermSemBarra();
                }
            }

        } catch (Exception e) {
            if(configCTR.getVerInforConfig() == 0L) {
                configCTR.setVerInforConfig(1L);
                VerifDadosServ.getInstance().pulaTelaComTermSemBarra();
            }
        }

    }

    private void recColheita(String result) {

        ConfigCTR configCTR = new ConfigCTR();

        try {

            if (!result.contains("exceeded")) {

                JSONObject jObj = new JSONObject(result);
                JSONArray jsonArray = jObj.getJSONArray("dados");

                if (jsonArray.length() > 0) {

                    InfColheitaBean infColheitaBean = new InfColheitaBean();
                    infColheitaBean.deleteAll();

                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject objeto = jsonArray.getJSONObject(i);
                        Gson gson = new Gson();
                        infColheitaBean = gson.fromJson(objeto.toString(), InfColheitaBean.class);
                        infColheitaBean.insert();

                    }

//                    if(configCTR.getVerInforConfig() == 0L){
//                        VerifDadosServ.getInstance().pulaTelaDadosInfor(DadosColheitaActivity.class);
//                    }
//                    else{
//                        configCTR.setVerInforConfig(2L);
//                    }

                } else {

                    if(configCTR.getVerInforConfig() == 0L) {
                        configCTR.setVerInforConfig(3L);
                        VerifDadosServ.getInstance().pulaTelaComTermSemBarra();
                    }
                }

            } else {
                if(configCTR.getVerInforConfig() == 0L) {
                    configCTR.setVerInforConfig(1L);
                    VerifDadosServ.getInstance().pulaTelaComTermSemBarra();
                }
            }

        } catch (Exception e) {
            if(configCTR.getVerInforConfig() == 0L) {
                configCTR.setVerInforConfig(1L);
                VerifDadosServ.getInstance().pulaTelaComTermSemBarra();
            }
        }

    }

    private void recPlantio(String result) {

        ConfigCTR configCTR = new ConfigCTR();

        try {

            if (!result.contains("exceeded")) {

                JSONObject jObj = new JSONObject(result);
                JSONArray jsonArray = jObj.getJSONArray("dados");

                if (jsonArray.length() > 0) {

                    InfPlantioBean infPlantioBean = new InfPlantioBean();
                    infPlantioBean.deleteAll();

                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject objeto = jsonArray.getJSONObject(i);
                        Gson gson = new Gson();
                        infPlantioBean = gson.fromJson(objeto.toString(), InfPlantioBean.class);
                        infPlantioBean.insert();

                    }

//                    if(configCTR.getVerInforConfig() == 0L){
//                        VerifDadosServ.getInstance().pulaTelaDadosInfor(DadosPlantioActivity.class);
//                    }
//                    else{
//                        configCTR.setVerInforConfig(2L);
//                    }

                } else {

                    if(configCTR.getVerInforConfig() == 0L) {
                        configCTR.setVerInforConfig(3L);
                        VerifDadosServ.getInstance().pulaTelaComTermSemBarra();
                    }
                }

            } else {
                if(configCTR.getVerInforConfig() == 0L) {
                    configCTR.setVerInforConfig(1L);
                    VerifDadosServ.getInstance().pulaTelaComTermSemBarra();
                }
            }

        } catch (Exception e) {
            if(configCTR.getVerInforConfig() == 0L) {
                configCTR.setVerInforConfig(1L);
                VerifDadosServ.getInstance().pulaTelaComTermSemBarra();
            }
        }

    }

}
