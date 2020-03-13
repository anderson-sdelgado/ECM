package br.com.usinasantafe.ecm.model.dao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.ecm.control.MotoMecCTR;
import br.com.usinasantafe.ecm.model.bean.variaveis.ApontMMBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.CabecPneuBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.ItemPneuBean;
import br.com.usinasantafe.ecm.model.pst.EspecificaPesquisa;
import br.com.usinasantafe.ecm.util.Tempo;

public class ApontMMDAO {

    public ApontMMDAO() {
    }

    public Long getIdApontAberto() {
        ApontMMBean apontMMBean = getApontMMAberto();
        return apontMMBean.getIdApontMM();
    }

    public ApontMMBean getApontMMAberto(){
        ApontMMBean apontMMBean = new ApontMMBean();
        List apontaMMList = apontMMBean.get("statusApontMM", 0L);
        apontMMBean = (ApontMMBean) apontaMMList.get(0);
        apontaMMList.clear();
        return apontMMBean;
    }

    public void updApont(ApontMMBean apontMMBean){

        apontMMBean.setStatusApontMM(1L);
        apontMMBean.update();

    }

    public List getListApontEnvio(Long idBolMM){

        ApontMMBean apontMMBean = new ApontMMBean();

        ArrayList pesqArrayList = new ArrayList();
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("statusApontMM");
        pesquisa.setValor(1L);
        pesquisa.setTipo(1);
        pesqArrayList.add(pesquisa);

        EspecificaPesquisa pesquisa2 = new EspecificaPesquisa();
        pesquisa2.setCampo("idBolApontMM");
        pesquisa2.setValor(idBolMM);
        pesquisa2.setTipo(1);
        pesqArrayList.add(pesquisa2);

        return apontMMBean.get(pesqArrayList);

    }

    public List getListApontEnvio() {
        ApontMMBean apontMMBean = new ApontMMBean();
        return apontMMBean.get("statusApontMM", 1L);
    }

    public String dadosEnvioApontMM(List apontaList){

        JsonArray jsonArrayAponta = new JsonArray();
        JsonArray jsonArrayCabecPneu = new JsonArray();
        JsonArray jsonArrayItemPneu = new JsonArray();

        for (int i = 0; i < apontaList.size(); i++) {

            ApontMMBean apontMMBean = (ApontMMBean) apontaList.get(i);
            Gson gson = new Gson();
            jsonArrayAponta.add(gson.toJsonTree(apontMMBean, apontMMBean.getClass()));

            CabecPneuBean cabecPneuBean = new CabecPneuBean();
            List cabecPneuList = cabecPneuBean.get("idApontCabecPneu", apontMMBean.getIdApontMM());

            for (int l = 0; l < cabecPneuList.size(); l++) {

                cabecPneuBean = (CabecPneuBean) cabecPneuList.get(l);
                Gson gsonCabecPneu = new Gson();
                jsonArrayCabecPneu.add(gsonCabecPneu.toJsonTree(cabecPneuBean, cabecPneuBean.getClass()));

                ItemPneuBean itemPneuBean = new ItemPneuBean();
                List itemPneuList = itemPneuBean.get("idCabecItemPneu", cabecPneuBean.getIdCabecPneu());

                for (int m = 0; m < itemPneuList.size(); m++) {
                    itemPneuBean = (ItemPneuBean) itemPneuList.get(m);
                    Gson gsonItemPneu = new Gson();
                    jsonArrayItemPneu.add(gsonItemPneu.toJsonTree(itemPneuBean, itemPneuBean.getClass()));
                }

                itemPneuList.clear();

            }

            cabecPneuList.clear();

        }

        apontaList.clear();


        JsonObject jsonAponta = new JsonObject();
        jsonAponta.add("aponta", jsonArrayAponta);

        JsonObject jsonCabecPneu = new JsonObject();
        jsonCabecPneu.add("cabecpneu", jsonArrayCabecPneu);

        JsonObject jsonItemPneu = new JsonObject();
        jsonItemPneu.add("itempneu", jsonArrayItemPneu);

        return jsonAponta.toString() + "|" + jsonCabecPneu.toString() + "#" + jsonItemPneu.toString();

    }

    public void updateApont(String retorno) {

        try{

            int pos1 = retorno.indexOf("_") + 1;

            String objPrinc = retorno.substring(pos1);

            JSONObject jObjApontMM = new JSONObject(objPrinc);
            JSONArray jsonArrayApontMM = jObjApontMM.getJSONArray("apont");

            if (jsonArrayApontMM.length() > 0) {

                ArrayList<Long> rList = new ArrayList<Long>();
                ApontMMBean apontMMBean = new ApontMMBean();

                for (int i = 0; i < jsonArrayApontMM.length(); i++) {

                    JSONObject objApont = jsonArrayApontMM.getJSONObject(i);
                    Gson gsonApont = new Gson();
                    apontMMBean = gsonApont.fromJson(objApont.toString(), ApontMMBean.class);

                    rList.add(apontMMBean.getIdApontMM());

                }

                List apontMMList = apontMMBean.in("idApontMM", rList);

                for (int i = 0; i < apontMMList.size(); i++) {

                    apontMMBean = (ApontMMBean) apontMMList.get(i);
                    apontMMBean.setStatusApontMM(2L);
                    apontMMBean.update();

                }

                CabecPneuBean cabecPneuBean = new CabecPneuBean();
                List cabecPneuList = cabecPneuBean.in("idApontCabecPneu", rList);

                for (int l = 0; l < cabecPneuList.size(); l++) {

                    cabecPneuBean = (CabecPneuBean) cabecPneuList.get(l);

                    ItemPneuBean itemPneuBean = new ItemPneuBean();
                    List itemPneuList = itemPneuBean.get("idCabecItemPneu", cabecPneuBean.getIdCabecPneu());

                    for (int m = 0; m < itemPneuList.size(); m++) {
                        itemPneuBean = (ItemPneuBean) itemPneuList.get(m);
                        itemPneuBean.delete();
                    }

                    cabecPneuBean.delete();
                }

                rList.clear();

            }

        }
        catch(Exception e){
            Tempo.getInstance().setEnvioDado(true);
        }

    }

    public List getListAllApont(Long idBolMM){
        ApontMMBean apontMMBean = new ApontMMBean();
        return apontMMBean.getAndOrderBy("idBolApontMM", idBolMM, "idApontMM", true);
    }


    public ApontMMBean createApont(MotoMecCTR motoMecCTR){
        ApontMMBean apontMMBean = new ApontMMBean();
        List apontList = getListAllApont(motoMecCTR.getIdBol());
        if (apontList.size() == 0) {
            apontMMBean.setIdBolApontMM(motoMecCTR.getIdBol());
            apontMMBean.setIdExtBolApontMM(motoMecCTR.getIdExtBol());
            apontMMBean.setOsApontMM(motoMecCTR.getOS());
            apontMMBean.setAtivApontMM(motoMecCTR.getAtiv());
            apontMMBean.setParadaApontMM(0L);
            apontMMBean.setStatusConApontMM(motoMecCTR.getStatusConBol());
            apontMMBean.setStatusApontMM(1L);
            apontMMBean.setLongitudeApontMM(motoMecCTR.getLongitude());
            apontMMBean.setLatitudeApontMM(motoMecCTR.getLatitude());
        } else {
            ApontMMBean ultApontTO = (ApontMMBean) apontList.get(apontList.size() - 1);
            apontMMBean = ultApontTO;
            apontMMBean.setStatusApontMM(1L);
        }
        apontMMBean.setTransbApontMM(0L);
        apontList.clear();
        return apontMMBean;
    }

    public ApontMMBean createApontAtividade(MotoMecCTR motoMecCTR){
        ApontMMBean apontMMBean = new ApontMMBean();
        List apontList = getListAllApont(motoMecCTR.getIdBol());
        if (apontList.size() == 0) {
            apontMMBean.setIdBolApontMM(motoMecCTR.getIdBol());
            apontMMBean.setIdExtBolApontMM(motoMecCTR.getIdExtBol());
            apontMMBean.setOsApontMM(motoMecCTR.getOS());
        } else {
            ApontMMBean ultApontBean = (ApontMMBean) apontList.get(apontList.size() - 1);
            apontMMBean = ultApontBean;
            apontMMBean.setStatusApontMM(1L);
        }
        apontMMBean.setTransbApontMM(0L);
        apontList.clear();
        return apontMMBean;
    }

    public ApontMMBean createApontParada(MotoMecCTR boletimCTR){
        ApontMMBean apontMMBean = new ApontMMBean();
        List apontList = getListAllApont(boletimCTR.getIdBol());
        if (apontList.size() == 0) {
            apontMMBean.setIdBolApontMM(boletimCTR.getIdBol());
            apontMMBean.setIdExtBolApontMM(boletimCTR.getIdExtBol());
            apontMMBean.setOsApontMM(boletimCTR.getOS());
            apontMMBean.setAtivApontMM(boletimCTR.getAtiv());
            apontMMBean.setParadaApontMM(0L);
            apontMMBean.setStatusConApontMM(boletimCTR.getStatusConBol());
            apontMMBean.setStatusApontMM(1L);
            apontMMBean.setLongitudeApontMM(boletimCTR.getLongitude());
            apontMMBean.setLatitudeApontMM(boletimCTR.getLatitude());
        } else {
            ApontMMBean ultApontTO = (ApontMMBean) apontList.get(apontList.size() - 1);
            apontMMBean = ultApontTO;
            apontMMBean.setStatusApontMM(1L);
        }
        apontMMBean.setTransbApontMM(0L);
        apontList.clear();
        return apontMMBean;
    }

    public void salvarApont(ApontMMBean apontMMBean){
        apontMMBean.insert();
    }

}
