package br.com.usinasantafe.ecm.bo;

import java.util.ArrayList;
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
import br.com.usinasantafe.ecm.to.tb.variaveis.AtividadeOsTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.CabecCheckListTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.CarretaEngDesengTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.CompVCanaBkpTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.CompVCanaTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.CompVVinhacaTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.ConfiguracaoTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.InfBoletimTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.RespItemCheckListTO;

public class ManipDadosEnvio {

    private static ManipDadosEnvio instance = null;
    private UrlsConexaoHttp urlsConexaoHttp;
    private List listDatasFrenteTO;
    private boolean envio;

    public ManipDadosEnvio() {
        // TODO Auto-generated constructor stub
        urlsConexaoHttp = new UrlsConexaoHttp();
    }

    public static ManipDadosEnvio getInstance() {
        if (instance == null) {
            instance = new ManipDadosEnvio();
        }
        return instance;
    }

    public void salvaViagemCana() {

        InfBoletimTO infBoletimTO = new InfBoletimTO();
        List infBoletimTOList = infBoletimTO.all();
        infBoletimTO = (InfBoletimTO) infBoletimTOList.get(0);
        CompVCanaTO compVCanaTO = new CompVCanaTO();

        int qtde = compVCanaTO.count();
        qtde = qtde + 1;
        compVCanaTO.setId((long) qtde);

        compVCanaTO.setCam(infBoletimTO.getCam());
        compVCanaTO.setLibCam(infBoletimTO.getLibCam());
        compVCanaTO.setMaqCam(infBoletimTO.getMaqCam());
        compVCanaTO.setOpCam(infBoletimTO.getOpCam());
        compVCanaTO.setMoto(infBoletimTO.getCodigoMoto());
        compVCanaTO.setCarr1(infBoletimTO.getCarr1());
        compVCanaTO.setLibCarr1(infBoletimTO.getLibCarr1());
        compVCanaTO.setMaqCarr1(infBoletimTO.getMaqCarr1());
        compVCanaTO.setOpCarr1(infBoletimTO.getOpCarr1());
        compVCanaTO.setCarr2(infBoletimTO.getCarr2());
        compVCanaTO.setLibCarr2(infBoletimTO.getLibCarr2());
        compVCanaTO.setMaqCarr2(infBoletimTO.getMaqCarr2());
        compVCanaTO.setOpCarr2(infBoletimTO.getOpCarr2());
        compVCanaTO.setCarr3(infBoletimTO.getCarr3());
        compVCanaTO.setLibCarr3(infBoletimTO.getLibCarr3());
        compVCanaTO.setMaqCarr3(infBoletimTO.getMaqCarr3());
        compVCanaTO.setOpCarr3(infBoletimTO.getOpCarr3());
        compVCanaTO.setDataChegCampo(infBoletimTO.getDataChegCampo());
        compVCanaTO.setDataSaidaCampo(infBoletimTO.getDataSaidaCampo());
        compVCanaTO.setDataSaidaUsina(infBoletimTO.getDataSaidaUsina());
        compVCanaTO.setNoteiro(infBoletimTO.getNoteiro());
        compVCanaTO.setTurno(infBoletimTO.getTurno());

        compVCanaTO.insert();

        infBoletimTO.setDataChegCampo("");
        infBoletimTO.setDataSaidaCampo("");
        infBoletimTO.setDataSaidaUsina("");
        infBoletimTO.update();

        List viagemCana = verifDadosViagemCana();
        envioViagemCana(viagemCana);

        CompVCanaBkpTO compVCanaBkpTO = new CompVCanaBkpTO();
        compVCanaBkpTO.setMoto(compVCanaTO.getMoto());
        compVCanaBkpTO.setCarr1(compVCanaTO.getCarr1());
        compVCanaBkpTO.setCarr2(compVCanaTO.getCarr2());
        compVCanaBkpTO.setCarr3(compVCanaTO.getCarr3());
        compVCanaBkpTO.setDataSaidaCampo(compVCanaTO.getDataSaidaCampo());
        compVCanaBkpTO.setNoteiro(compVCanaTO.getNoteiro());

        List listaCompVCanaBkp = compVCanaBkpTO.all();
        int qtdeVCana = listaCompVCanaBkp.size();
        if (qtdeVCana == 10) {
            CompVCanaBkpTO compVCanaBkpTODel = (CompVCanaBkpTO) listaCompVCanaBkp.get(0);
            compVCanaBkpTODel.delete();
        }

        compVCanaBkpTO.insert();

    }

    public void salvaViagemVinhaca(CompVVinhacaTO compVVinhacaTO) {

        int qtde = compVVinhacaTO.count();
        qtde = qtde + 1;

        compVVinhacaTO.setId((long) qtde);
        compVVinhacaTO.insert();

        List viagemVinhaca = verifDadosViagemVinhaca();
        envioViagemVinhaca(viagemVinhaca);

    }

    public void salvaMotoMec(ApontMotoMecTO apontMotoMecTO) {

        InfBoletimTO infBoletimTO = new InfBoletimTO();
        List lTurno = infBoletimTO.all();
        infBoletimTO = (InfBoletimTO) lTurno.get(0);

        AtividadeOsTO atividadeOsTO = new AtividadeOsTO();
        List listaAtiv = atividadeOsTO.all();
        atividadeOsTO = (AtividadeOsTO) listaAtiv.get(0);

        ConfiguracaoTO configuracaoTO = new ConfiguracaoTO();
        List listaConfig = configuracaoTO.all();
        configuracaoTO = (ConfiguracaoTO) listaConfig.get(0);

        int qtde = apontMotoMecTO.count();
        qtde = qtde + 1;

        apontMotoMecTO.setId((long) qtde);
        apontMotoMecTO.setVeic(configuracaoTO.getCodCamConfig());
        apontMotoMecTO.setMotorista(infBoletimTO.getCodigoMoto());

        apontMotoMecTO.setDihi(Tempo.getInstance().datahora());
        apontMotoMecTO.setCaux(atividadeOsTO.getAtivOS());

        apontMotoMecTO.setEstado(atividadeOsTO.getEstado());
        apontMotoMecTO.setFrente(1L);

        CarretaEngDesengTO carretaEngDesengTO = new CarretaEngDesengTO();
        List listaCarretaEngDeseng = carretaEngDesengTO.all();
        qtde = listaCarretaEngDeseng.size();

        if (qtde == 0) {
            apontMotoMecTO.setCar1((long) 0);
            apontMotoMecTO.setCar2((long) 0);
            apontMotoMecTO.setCar3((long) 0);
        } else if (qtde == 1) {
            carretaEngDesengTO = (CarretaEngDesengTO) listaCarretaEngDeseng.get(0);
            apontMotoMecTO.setCar1(carretaEngDesengTO.getNumCarreta());
            apontMotoMecTO.setCar2((long) 0);
            apontMotoMecTO.setCar3((long) 0);
        } else if (qtde == 2) {
            carretaEngDesengTO = (CarretaEngDesengTO) listaCarretaEngDeseng.get(0);
            apontMotoMecTO.setCar1(carretaEngDesengTO.getNumCarreta());
            carretaEngDesengTO = (CarretaEngDesengTO) listaCarretaEngDeseng.get(1);
            apontMotoMecTO.setCar2(carretaEngDesengTO.getNumCarreta());
            apontMotoMecTO.setCar3((long) 0);
        } else if (qtde == 3) {
            carretaEngDesengTO = (CarretaEngDesengTO) listaCarretaEngDeseng.get(0);
            apontMotoMecTO.setCar1(carretaEngDesengTO.getNumCarreta());
            carretaEngDesengTO = (CarretaEngDesengTO) listaCarretaEngDeseng.get(1);
            apontMotoMecTO.setCar2(carretaEngDesengTO.getNumCarreta());
            carretaEngDesengTO = (CarretaEngDesengTO) listaCarretaEngDeseng.get(2);
            apontMotoMecTO.setCar3(carretaEngDesengTO.getNumCarreta());
        }

        apontMotoMecTO.insert();

        List motoMec = verifDadosMotoMec();
        envioMotoMec(motoMec);

    }

    public void salvaCheckList(ArrayList listItem) {

        InfBoletimTO infBoletimTO = new InfBoletimTO();
        List lTurno = infBoletimTO.all();
        infBoletimTO = (InfBoletimTO) lTurno.get(0);

        ConfiguracaoTO configTO = new ConfiguracaoTO();
        List listaConfig = configTO.all();
        configTO = (ConfiguracaoTO) listaConfig.get(0);

        CabecCheckListTO cabecCheckListTO = new CabecCheckListTO();
        int qtdeCab = cabecCheckListTO.count();
        qtdeCab = qtdeCab + 1;

        cabecCheckListTO.setIdCabecCheckList((long) qtdeCab);
        cabecCheckListTO.setDtCabecCheckList(Tempo.getInstance().datahora());
        cabecCheckListTO.setEquipCabecCheckList(configTO.getCodCamConfig());
        cabecCheckListTO.setFuncCabecCheckList(infBoletimTO.getCodigoMoto());
        cabecCheckListTO.setTurnoCabecCheckList(infBoletimTO.getTurno());
        cabecCheckListTO.insert();

        configTO.setDtUltimoCheckListConfig(Tempo.getInstance().dataSHora());
        configTO.update();

        RespItemCheckListTO resp = new RespItemCheckListTO();
        int qtdeItem = resp.count();

        for (int i = 0; i < listItem.size(); i++) {

            resp = (RespItemCheckListTO) listItem.get(i);

            qtdeItem = qtdeItem + 1;

            resp.setIdCabecItemCheckList((long) qtdeCab);
            resp.setIdItemCheckList((long) qtdeItem);
            resp.insert();

        }

        enviarChecklist();

    }

    public void delApontMotoMec() {
        ApontMotoMecTO apontMotoMecTODel = new ApontMotoMecTO();
        apontMotoMecTODel.deleteAll();
    }


    public void delApontCana() {
        CompVCanaTO compVCanaTODel = new CompVCanaTO();
        compVCanaTODel.deleteAll();
    }

    public void delChecklist() {
        CabecCheckListTO cabecCheckListTO = new CabecCheckListTO();
        cabecCheckListTO.deleteAll();
        RespItemCheckListTO respItemCheckListTO = new RespItemCheckListTO();
        respItemCheckListTO.deleteAll();
    }

    public void delApontVinhaca() {
        CompVVinhacaTO compVVinhacaTO = new CompVVinhacaTO();
        compVVinhacaTO.deleteAll();
    }

    public List verifDadosViagemCana() {
        CompVCanaTO compVCanaTO = new CompVCanaTO();
        return compVCanaTO.all();
    }

    public void envioViagemCana(List viagemCana) {

        JsonArray jsonArray = new JsonArray();
        CompVCanaTO compVCanaTO = new CompVCanaTO();

        for (int i = 0; i < viagemCana.size(); i++) {

            compVCanaTO = (CompVCanaTO) viagemCana.get(i);
            Gson gson = new Gson();
            jsonArray.add(gson.toJsonTree(compVCanaTO, compVCanaTO.getClass()));

        }

        JsonObject json = new JsonObject();
        json.add("dados", jsonArray);

        String[] url = {urlsConexaoHttp.getsApontVCana()};
        Map<String, Object> parametrosPost = new HashMap<String, Object>();
        parametrosPost.put("dado", json.toString());

        Log.i("ECM", "LISTA = " + json.toString());

        ConHttpPostCadGenerico.getInstance().setParametrosPost(parametrosPost);

        ConHttpPostCadGenerico conHttpPostCadGenerico = new ConHttpPostCadGenerico();
        conHttpPostCadGenerico.setParametrosPost(parametrosPost);
        conHttpPostCadGenerico.execute(url);

    }

    public List verifDadosViagemVinhaca() {
        CompVVinhacaTO compVVinhacaTORec = new CompVVinhacaTO();
        return compVVinhacaTORec.all();
    }

    public void envioViagemVinhaca(List viagemVinhaca) {

        JsonArray jsonArray = new JsonArray();

        for (int i = 0; i < viagemVinhaca.size(); i++) {

            CompVVinhacaTO compVVinhacaTON = (CompVVinhacaTO) viagemVinhaca.get(i);
            Gson gson = new Gson();
            jsonArray.add(gson.toJsonTree(compVVinhacaTON, compVVinhacaTON.getClass()));

        }

        JsonObject json = new JsonObject();
        json.add("dados", jsonArray);

        String[] url = {urlsConexaoHttp.getsApontVVinhaca()};
        Map<String, Object> parametrosPost = new HashMap<String, Object>();
        parametrosPost.put("dado", json.toString());

        ConHttpPostCadGenerico conHttpPostCadGenerico = new ConHttpPostCadGenerico();
        conHttpPostCadGenerico.setParametrosPost(parametrosPost);
        conHttpPostCadGenerico.execute(url);

    }

    public List verifDadosMotoMec() {

        ApontMotoMecTO apontMotoMecTORec = new ApontMotoMecTO();
        return apontMotoMecTORec.all();

    }

    public void envioMotoMec(List listaDados) {

        JsonArray jsonArray = new JsonArray();

        for (int i = 0; i < listaDados.size(); i++) {
            ApontMotoMecTO apontMotoMecTON = (ApontMotoMecTO) listaDados.get(i);

            if(apontMotoMecTON.getOpcor() == null){
                apontMotoMecTON.setOpcor(437L);
            }

            Gson gson = new Gson();
            jsonArray.add(gson.toJsonTree(apontMotoMecTON, apontMotoMecTON.getClass()));
        }

        JsonObject json = new JsonObject();
        json.add("dados", jsonArray);

        Log.i("ECM", "LISTA = " + json.toString());

        String[] url = {urlsConexaoHttp.getsInsertMotoMec()};
        Map<String, Object> parametrosPost = new HashMap<String, Object>();
        parametrosPost.put("dado", json.toString());

        ConHttpPostCadGenerico conHttpPostCadGenerico = new ConHttpPostCadGenerico();
        conHttpPostCadGenerico.setParametrosPost(parametrosPost);
        conHttpPostCadGenerico.execute(url);

    }

    public void enviarChecklist() {

        CabecCheckListTO cabecCheckListTO = new CabecCheckListTO();
        List listCabec = cabecCheckListTO.all();

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

        ConHttpPostCadGenerico conHttpPostGenerico = new ConHttpPostCadGenerico();
        conHttpPostGenerico.setParametrosPost(parametrosPost);
        conHttpPostGenerico.execute(url);

    }

    public void envioDados(Context context) {

        ConexaoWeb conexaoWeb = new ConexaoWeb();
        if (conexaoWeb.verificaConexao(context)) {
            delChecklist();
            delApontVinhaca();
            List viagemCana = verifDadosViagemCana();
            if (viagemCana.size() > 0) {
                envioViagemCana(viagemCana);
            } else {
                List motoMec = verifDadosMotoMec();
                if (motoMec.size() > 0) {
                    envioMotoMec(motoMec);
                }
            }
        }

    }


    public boolean verifDadosEnvio() {

        if ((verifDadosViagemCana().size() == 0) && (verifDadosViagemVinhaca().size() == 0)
                && (verifDadosMotoMec().size() == 0) && (verifDadosChecklist().size() == 0)) {
            return false;
        } else {
            return true;
        }
    }

    public List verifDadosChecklist() {
        CabecCheckListTO cabecCheckListTO = new CabecCheckListTO();
        return cabecCheckListTO.all();
    }


}
