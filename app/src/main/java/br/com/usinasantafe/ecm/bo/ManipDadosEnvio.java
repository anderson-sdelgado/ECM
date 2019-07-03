package br.com.usinasantafe.ecm.bo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import android.content.Context;
import android.util.Log;

import br.com.usinasantafe.ecm.conWEB.ConHttpPostCadGenerico;
import br.com.usinasantafe.ecm.conWEB.UrlsConexaoHttp;
import br.com.usinasantafe.ecm.to.tb.variaveis.ApontMotoMecTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.CabecCheckListTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.CompVCanaBkpTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.CompVCanaTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.ConfigTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.RespItemCheckListTO;

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

        CabecCheckListTO cabecCheckListTO = new CabecCheckListTO();
        List cabecCheckListLista = cabecCheckListTO.get("statusCabecCheckList", 1L);
        cabecCheckListTO = (CabecCheckListTO) cabecCheckListLista.get(0);
        cabecCheckListLista.clear();

        cabecCheckListTO.setStatusCabecCheckList(2L);
        cabecCheckListTO.update();

        enviarChecklist();

    }

    public void salvaMotoMec(ApontMotoMecTO apontMotoMecTO) {

        CompVCanaTO compVCanaTO = new CompVCanaTO();
        List compVCanaList = compVCanaTO.get("status", 1L);

        Long ativOS = 0L;
        if(compVCanaList.size() > 0){
            compVCanaTO = (CompVCanaTO) compVCanaList.get(0);
            ativOS = compVCanaTO.getAtivOS();
        }

        compVCanaList.clear();

        ConfigTO configTO = new ConfigTO();
        List listaConfig = configTO.all();
        configTO = (ConfigTO) listaConfig.get(0);

        apontMotoMecTO.setVeic(configTO.getCodCamConfig());
        apontMotoMecTO.setMotorista(configTO.getCrachaMotoConfig());
        apontMotoMecTO.setDihi(Tempo.getInstance().datahora());
        apontMotoMecTO.setCaux(ativOS);
        apontMotoMecTO.insert();

        envioApontMotoMec();

    }

    public void salvaViagemCana() {

        ConfigTO configTO = new ConfigTO();
        List configList = configTO.all();
        configTO = (ConfigTO) configList.get(0);
        configList.clear();

        CompVCanaTO compVCanaTO = new CompVCanaTO();
        List compVCanaList = compVCanaTO.get("status", 1L);
        compVCanaTO = (CompVCanaTO) compVCanaList.get(0);

        compVCanaTO.setCam(configTO.getCodCamConfig());
        compVCanaTO.setMoto(configTO.getCrachaMotoConfig());
        compVCanaTO.setTurno(configTO.getNroTurnoConfig());
        compVCanaTO.setStatus(2L);
        compVCanaTO.update();

        envioViagemCana();

        CompVCanaBkpTO compVCanaBkpTO = new CompVCanaBkpTO();
        compVCanaBkpTO.setMoto(compVCanaTO.getMoto());
        compVCanaBkpTO.setCarr1(compVCanaTO.getCarr1());
        compVCanaBkpTO.setCarr2(compVCanaTO.getCarr2());
        compVCanaBkpTO.setCarr3(compVCanaTO.getCarr3());
        compVCanaBkpTO.setDataSaidaCampo(compVCanaTO.getDataSaidaCampo());
        compVCanaBkpTO.setNoteiro(compVCanaTO.getMoto());

        List listaCompVCanaBkp = compVCanaBkpTO.all();
        int qtdeVCana = listaCompVCanaBkp.size();
        if (qtdeVCana == 10) {
            CompVCanaBkpTO compVCanaBkpTODel = (CompVCanaBkpTO) listaCompVCanaBkp.get(0);
            compVCanaBkpTODel.delete();
        }

        compVCanaBkpTO.insert();

    }

    //////////////////////// ENVIAR DADOS ////////////////////////////////////////////

    public void enviarChecklist() {

        CabecCheckListTO cabecCheckListTO = new CabecCheckListTO();
        List listCabec = boletinsCheckList();

        JsonArray jsonArrayCabec = new JsonArray();
        JsonArray jsonArrayItem = new JsonArray();

        for (int i = 0; i < listCabec.size(); i++) {

            cabecCheckListTO = (CabecCheckListTO) listCabec.get(i);
            Gson gsonCabec = new Gson();
            jsonArrayCabec.add(gsonCabec.toJsonTree(cabecCheckListTO, cabecCheckListTO.getClass()));

            RespItemCheckListTO respItemCheckListTO = new RespItemCheckListTO();
            List listaItem = respItemCheckListTO.get("idCabecItemCheckList", cabecCheckListTO.getIdCabecCheckList());

            for (int j = 0; j < listaItem.size(); j++) {

                respItemCheckListTO = (RespItemCheckListTO) listaItem.get(j);
                Gson gsonItem = new Gson();
                jsonArrayItem.add(gsonItem.toJsonTree(respItemCheckListTO, respItemCheckListTO.getClass()));

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

        ApontMotoMecTO apontMotoMecTO = new ApontMotoMecTO();
        List apontMotoMecList = apontamentosMotoMec();

        JsonArray jsonArray = new JsonArray();

        for (int i = 0; i < apontMotoMecList.size(); i++) {

            apontMotoMecTO = (ApontMotoMecTO) apontMotoMecList.get(i);

            if(apontMotoMecTO.getOpcor() == null){
                apontMotoMecTO.setOpcor(437L);
            }

            Gson gson = new Gson();
            jsonArray.add(gson.toJsonTree(apontMotoMecTO, apontMotoMecTO.getClass()));
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

        CompVCanaTO compVCanaTO = new CompVCanaTO();
        List viagemCanaList = viagensCana();

        JsonArray jsonArray = new JsonArray();

        for (int i = 0; i < viagemCanaList.size(); i++) {

            compVCanaTO = (CompVCanaTO) viagemCanaList.get(i);
            Gson gson = new Gson();
            jsonArray.add(gson.toJsonTree(compVCanaTO, compVCanaTO.getClass()));

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
        CabecCheckListTO cabecCheckListTO = new CabecCheckListTO();
        cabecCheckListTO.deleteAll();
        RespItemCheckListTO respItemCheckListTO = new RespItemCheckListTO();
        respItemCheckListTO.deleteAll();
    }

    public void delApontMotoMec() {
        ApontMotoMecTO apontMotoMecTO = new ApontMotoMecTO();
        apontMotoMecTO.deleteAll();
    }

    public void delViagemCana() {
        CompVCanaTO compVCanaTO = new CompVCanaTO();
        compVCanaTO.deleteAll();
    }

    //////////////////////////TRAZER DADOS////////////////////////////

    public List boletinsCheckList(){
        CabecCheckListTO cabecCheckListTO = new CabecCheckListTO();
        return cabecCheckListTO.get("statusCabecCheckList", 2L);
    }

    public List apontamentosMotoMec() {
        ApontMotoMecTO apontMotoMecTORec = new ApontMotoMecTO();
        return apontMotoMecTORec.all();

    }

    public List viagensCana() {
        CompVCanaTO compVCanaTO = new CompVCanaTO();
        return compVCanaTO.get("status", 2L);
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
