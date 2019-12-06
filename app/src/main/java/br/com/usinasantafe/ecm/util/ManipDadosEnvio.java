package br.com.usinasantafe.ecm.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import android.content.Context;
import android.util.Log;

import br.com.usinasantafe.ecm.model.bean.variaveis.CertifCanaBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.CertifCanaBkpBean;
import br.com.usinasantafe.ecm.util.connection.ConHttpPostCadGenerico;
import br.com.usinasantafe.ecm.model.bean.variaveis.ApontMotoMecBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.CabecCheckListBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.ConfigBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.RespCheckListBean;

public class ManipDadosEnvio {

    private static ManipDadosEnvio instance = null;
    private UrlsConexaoHttp urlsConexaoHttp;

    public ManipDadosEnvio() {
        urlsConexaoHttp = new UrlsConexaoHttp();
    }

    public static ManipDadosEnvio getInstance() {
        if (instance == null) {
            instance = new ManipDadosEnvio();
        }
        return instance;
    }

    //////////////////////// SALVAR DADOS ////////////////////////////////////////////

    public void salvaCheckList() {

        CabecCheckListBean cabecCheckListBean = new CabecCheckListBean();
        List cabecCheckListLista = cabecCheckListBean.get("statusCabecCheckList", 1L);
        cabecCheckListBean = (CabecCheckListBean) cabecCheckListLista.get(0);
        cabecCheckListLista.clear();

        cabecCheckListBean.setStatusCabecCheckList(2L);
        cabecCheckListBean.update();

        enviarChecklist();

    }



    public void salvaViagemCana() {

        ConfigBean configBean = new ConfigBean();
        List configList = configBean.all();
        configBean = (ConfigBean) configList.get(0);
        configList.clear();

        CertifCanaBean certifCanaBean = new CertifCanaBean();
        List compVCanaList = certifCanaBean.get("status", 1L);
        certifCanaBean = (CertifCanaBean) compVCanaList.get(0);

        certifCanaBean.setCam(configBean.getCodEquipConfig());
        certifCanaBean.setMoto(configBean.getMatricColabConfig());
        certifCanaBean.setTurno(configBean.getIdTurnoConfig());
        certifCanaBean.setStatus(2L);
        certifCanaBean.update();

        envioViagemCana();

        CertifCanaBkpBean certifCanaBkpBean = new CertifCanaBkpBean();
        certifCanaBkpBean.setMoto(certifCanaBean.getMoto());
        certifCanaBkpBean.setCarr1(certifCanaBean.getCarr1());
        certifCanaBkpBean.setCarr2(certifCanaBean.getCarr2());
        certifCanaBkpBean.setCarr3(certifCanaBean.getCarr3());
        certifCanaBkpBean.setDataSaidaCampo(certifCanaBean.getDataSaidaCampo());
        certifCanaBkpBean.setNoteiro(certifCanaBean.getMoto());

        List listaCompVCanaBkp = certifCanaBkpBean.all();
        int qtdeVCana = listaCompVCanaBkp.size();
        if (qtdeVCana == 10) {
            CertifCanaBkpBean certifCanaBkpBeanDel = (CertifCanaBkpBean) listaCompVCanaBkp.get(0);
            certifCanaBkpBeanDel.delete();
        }

        certifCanaBkpBean.insert();

    }

    //////////////////////// ENVIAR DADOS ////////////////////////////////////////////

    public void enviarChecklist() {

        CabecCheckListBean cabecCheckListBean = new CabecCheckListBean();
        List listCabec = boletinsCheckList();

        JsonArray jsonArrayCabec = new JsonArray();
        JsonArray jsonArrayItem = new JsonArray();

        for (int i = 0; i < listCabec.size(); i++) {

            cabecCheckListBean = (CabecCheckListBean) listCabec.get(i);
            Gson gsonCabec = new Gson();
            jsonArrayCabec.add(gsonCabec.toJsonTree(cabecCheckListBean, cabecCheckListBean.getClass()));

            RespCheckListBean respCheckListBean = new RespCheckListBean();
            List listaItem = respCheckListBean.get("idCabecItemCheckList", cabecCheckListBean.getIdCabecCheckList());

            for (int j = 0; j < listaItem.size(); j++) {

                respCheckListBean = (RespCheckListBean) listaItem.get(j);
                Gson gsonItem = new Gson();
                jsonArrayItem.add(gsonItem.toJsonTree(respCheckListBean, respCheckListBean.getClass()));

            }

        }

        JsonObject jsonCabec = new JsonObject();
        jsonCabec.add("cabecalho", jsonArrayCabec);

        JsonObject jsonItem = new JsonObject();
        jsonItem.add("item", jsonArrayItem);

        String dados = jsonCabec.toString() + "_" + jsonItem.toString();

        UrlsConexaoHttp urlsConexaoHttp = new UrlsConexaoHttp();

        String[] url = {urlsConexaoHttp.getsApontChecklist()};
        Map<String, Object> parametrosPost = new HashMap<String, Object>();
        parametrosPost.put("dado", dados);


        Log.i("ECM", "DADOS VIAGEM = " + dados);

        ConHttpPostCadGenerico conHttpPostGenerico = new ConHttpPostCadGenerico();
        conHttpPostGenerico.setParametrosPost(parametrosPost);
        conHttpPostGenerico.execute(url);

    }

    public void envioApontMotoMec() {

        ApontMotoMecBean apontMotoMecBean = new ApontMotoMecBean();
        List apontMotoMecList = apontamentosMotoMec();

        JsonArray jsonArray = new JsonArray();

        for (int i = 0; i < apontMotoMecList.size(); i++) {

            apontMotoMecBean = (ApontMotoMecBean) apontMotoMecList.get(i);

            if(apontMotoMecBean.getOpCor() == null){
                apontMotoMecBean.setOpCor(437L);
            }

            Gson gson = new Gson();
            jsonArray.add(gson.toJsonTree(apontMotoMecBean, apontMotoMecBean.getClass()));
        }

        JsonObject json = new JsonObject();
        json.add("dados", jsonArray);

        Log.i("ECM", "LISTA = " + json.toString());

        String[] url = {urlsConexaoHttp.getsInsertMotoMec()};
        Map<String, Object> parametrosPost = new HashMap<String, Object>();
        parametrosPost.put("dado", json.toString());

        Log.i("ECM", "DADOS MOTOMEC = " + json.toString());

        ConHttpPostCadGenerico conHttpPostCadGenerico = new ConHttpPostCadGenerico();
        conHttpPostCadGenerico.setParametrosPost(parametrosPost);
        conHttpPostCadGenerico.execute(url);

    }

    public void envioViagemCana() {

        CertifCanaBean certifCanaBean = new CertifCanaBean();
        List viagemCanaList = viagensCana();

        JsonArray jsonArray = new JsonArray();

        for (int i = 0; i < viagemCanaList.size(); i++) {

            certifCanaBean = (CertifCanaBean) viagemCanaList.get(i);
            Gson gson = new Gson();
            jsonArray.add(gson.toJsonTree(certifCanaBean, certifCanaBean.getClass()));

        }

        JsonObject json = new JsonObject();
        json.add("dados", jsonArray);

        String[] url = {urlsConexaoHttp.getsApontVCana()};
        Map<String, Object> parametrosPost = new HashMap<String, Object>();
        parametrosPost.put("dado", json.toString());

        Log.i("ECM", "DADOS VIAGEM = " + json.toString());

        ConHttpPostCadGenerico.getInstance().setParametrosPost(parametrosPost);

        ConHttpPostCadGenerico conHttpPostCadGenerico = new ConHttpPostCadGenerico();
        conHttpPostCadGenerico.setParametrosPost(parametrosPost);
        conHttpPostCadGenerico.execute(url);

    }

    /////////////////////////////// DELETAR DADOS ///////////////////////////////////////////////

    public void delChecklist() {
        CabecCheckListBean cabecCheckListBean = new CabecCheckListBean();
        cabecCheckListBean.deleteAll();
        RespCheckListBean respCheckListBean = new RespCheckListBean();
        respCheckListBean.deleteAll();
    }

    public void delApontMotoMec() {
        ApontMotoMecBean apontMotoMecBean = new ApontMotoMecBean();
        apontMotoMecBean.deleteAll();
    }

    public void delViagemCana() {
        CertifCanaBean certifCanaBean = new CertifCanaBean();
        certifCanaBean.deleteAll();
    }

    //////////////////////////TRAZER DADOS////////////////////////////

    public List boletinsCheckList(){
        CabecCheckListBean cabecCheckListBean = new CabecCheckListBean();
        return cabecCheckListBean.get("statusCabecCheckList", 2L);
    }

    public List apontamentosMotoMec() {
        ApontMotoMecBean apontMotoMecBeanRec = new ApontMotoMecBean();
        return apontMotoMecBeanRec.all();

    }

    public List viagensCana() {
        CertifCanaBean certifCanaBean = new CertifCanaBean();
        return certifCanaBean.get("status", 2L);
    }

    //////////////////////VERIFICAÇÃO DE DADOS///////////////////////////

    public boolean verifCheckList() {
        return boletinsCheckList().size() > 0;
    }

    public boolean verifViagemCana() {
        return viagensCana().size() > 0;
    }

    public boolean verifApontMotoMec() {
        return apontamentosMotoMec().size() > 0;
    }

    /////////////////////////MECANISMO DE ENVIO//////////////////////////////////

    public void envioDados(Context context) {

        ConexaoWeb conexaoWeb = new ConexaoWeb();
        if (conexaoWeb.verificaConexao(context)) {
            if (verifCheckList()) {
                enviarChecklist();
            } else {
                if (verifViagemCana()) {
                    envioViagemCana();
                } else {
                    if (verifApontMotoMec()) {
                        envioApontMotoMec();
                    }
                }
            }
        }

    }

    public boolean verifDadosEnvio() {
        if ((!verifViagemCana())
                && (!verifApontMotoMec())
                && (!verifCheckList())) {
            return false;
        } else {
            return true;
        }
    }

}
