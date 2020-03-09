package br.com.usinasantafe.ecm.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import android.content.Context;
import android.util.Log;

import br.com.usinasantafe.ecm.control.ConfigCTR;
import br.com.usinasantafe.ecm.control.MotoMecCTR;
import br.com.usinasantafe.ecm.model.bean.variaveis.CabecCLBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.CertifCanaBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.CertifCanaBkpBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.RespItemCLBean;
import br.com.usinasantafe.ecm.util.connHttp.PostCadGenerico;
import br.com.usinasantafe.ecm.model.bean.variaveis.ApontMotoMecBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.ConfigBean;
import br.com.usinasantafe.ecm.util.connHttp.UrlsConexaoHttp;

public class EnvioDadosServ {

    private static EnvioDadosServ instance = null;
    private UrlsConexaoHttp urlsConexaoHttp;
    private int statusEnvio; //1 - Enviando; 2 - Existe Dados para Enviar; 3 - Todos os Dados Enviados
    private boolean enviando = false;

    public EnvioDadosServ() {
        urlsConexaoHttp = new UrlsConexaoHttp();
    }

    public static EnvioDadosServ getInstance() {
        if (instance == null) {
            instance = new EnvioDadosServ();
        }
        return instance;
    }

    //////////////////////// SALVAR DADOS ////////////////////////////////////////////

    public void salvaCheckList() {

        CabecCLBean cabecCLBean = new CabecCLBean();
        List cabecCheckListLista = cabecCLBean.get("statusCabecCheckList", 1L);
        cabecCLBean = (CabecCLBean) cabecCheckListLista.get(0);
        cabecCheckListLista.clear();

        cabecCLBean.setStatusCabCL(2L);
        cabecCLBean.update();

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

        CabecCLBean cabecCLBean = new CabecCLBean();
        List listCabec = boletinsCheckList();

        JsonArray jsonArrayCabec = new JsonArray();
        JsonArray jsonArrayItem = new JsonArray();

        for (int i = 0; i < listCabec.size(); i++) {

            cabecCLBean = (CabecCLBean) listCabec.get(i);
            Gson gsonCabec = new Gson();
            jsonArrayCabec.add(gsonCabec.toJsonTree(cabecCLBean, cabecCLBean.getClass()));

            RespItemCLBean respItemCLBean = new RespItemCLBean();
            List listaItem = respItemCLBean.get("idCabecItemCheckList", cabecCLBean.getIdCabCL());

            for (int j = 0; j < listaItem.size(); j++) {

                respItemCLBean = (RespItemCLBean) listaItem.get(j);
                Gson gsonItem = new Gson();
                jsonArrayItem.add(gsonItem.toJsonTree(respItemCLBean, respItemCLBean.getClass()));

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

        PostCadGenerico conHttpPostGenerico = new PostCadGenerico();
        conHttpPostGenerico.setParametrosPost(parametrosPost);
        conHttpPostGenerico.execute(url);

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

        PostCadGenerico.getInstance().setParametrosPost(parametrosPost);

        PostCadGenerico postCadGenerico = new PostCadGenerico();
        postCadGenerico.setParametrosPost(parametrosPost);
        postCadGenerico.execute(url);

    }

    public void enviarBolFechadosMM() {

        MotoMecCTR motoMecCTR = new MotoMecCTR();
        String dados = motoMecCTR.dadosEnvioBolFechado();

        Log.i("PMM", "FECHADO = " + dados);

        UrlsConexaoHttp urlsConexaoHttp = new UrlsConexaoHttp();

        String[] url = {urlsConexaoHttp.getsInsertBolFechadoMM()};
        Map<String, Object> parametrosPost = new HashMap<String, Object>();
        parametrosPost.put("dado", dados);

        PostCadGenerico conHttpPostGenerico = new PostCadGenerico();
        conHttpPostGenerico.setParametrosPost(parametrosPost);
        conHttpPostGenerico.execute(url);

    }

    public void enviarBolAbertosMM() {

        MotoMecCTR motoMecCTR = new MotoMecCTR();
        String dados = motoMecCTR.dadosEnvioBolAberto();

        Log.i("PMM", "ABERTO = " + dados);

        UrlsConexaoHttp urlsConexaoHttp = new UrlsConexaoHttp();

        String[] url = {urlsConexaoHttp.getsInsertBolAbertoMM()};
        Map<String, Object> parametrosPost = new HashMap<String, Object>();
        parametrosPost.put("dado", dados);

        PostCadGenerico conHttpPostGenerico = new PostCadGenerico();
        conHttpPostGenerico.setParametrosPost(parametrosPost);
        conHttpPostGenerico.execute(url);

    }

    public void envioApontMM() {

        MotoMecCTR motoMecCTR = new MotoMecCTR();
        String dados = motoMecCTR.dadosEnvioApontMM();

        Log.i("PMM", "APONTAMENTO = " + dados);

        String[] url = {urlsConexaoHttp.getsInsertApontaMM()};
        Map<String, Object> parametrosPost = new HashMap<String, Object>();
        parametrosPost.put("dado", dados);

        PostCadGenerico postCadGenerico = new PostCadGenerico();
        postCadGenerico.setParametrosPost(parametrosPost);
        postCadGenerico.execute(url);

    }

    /////////////////////////////// DELETAR DADOS ///////////////////////////////////////////////

    public void delChecklist() {
        CabecCLBean cabecCLBean = new CabecCLBean();
        cabecCLBean.deleteAll();
        RespItemCLBean respItemCLBean = new RespItemCLBean();
        respItemCLBean.deleteAll();
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
        CabecCLBean cabecCLBean = new CabecCLBean();
        return cabecCLBean.get("statusCabecCheckList", 2L);
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

    public Boolean verifBolFechadoMM() {
        MotoMecCTR motoMecCTR = new MotoMecCTR();
        return motoMecCTR.verEnvioBolFech();
    }

    public Boolean verifBolAbertoSemEnvioMM() {
        MotoMecCTR motoMecCTR = new MotoMecCTR();
        return motoMecCTR.verEnvioBolAberto();
    }

    public Boolean verifApontMM() {
        MotoMecCTR motoMecCTR = new MotoMecCTR();
        return motoMecCTR.verEnvioDadosApont();
    }

    public Boolean verifInfor() {
        boolean ret = false;
        ConfigCTR configCTR = new ConfigCTR();
        if(configCTR.hasElements()){
            if(configCTR.getVerInforConfig() == 1){
                ret = true;
            }
        }
        return ret;
    }

    /////////////////////////MECANISMO DE ENVIO//////////////////////////////////

    public void envioDados(Context context) {
        enviando = true;
        ConexaoWeb conexaoWeb = new ConexaoWeb();
        if (conexaoWeb.verificaConexao(context)) {
            envioDadosPrinc();
        }
        else{
            enviando = false;
        }

    }

    public void envioDadosPrinc() {

        if(verifInfor()){
            VerifDadosServ.getInstance().verDadosInfor();
        }
        else {
            if (verifCheckList()) {
                enviarChecklist();
            }
            else {
                if (verifViagemCana()) {
                    envioViagemCana();
                } else {
                    if (verifBolFechadoMM()) {
                        enviarBolFechadosMM();
                    } else {
                        if (verifBolAbertoSemEnvioMM()) {
                            enviarBolAbertosMM();
                        } else {
                            if (verifApontMM()) {
                                envioApontMM();
                            }
                        }
                    }
                }
            }
        }
    }

    public boolean verifDadosEnvio() {
        if ((!verifInfor())
                && (!verifCheckList())
                && (!verifViagemCana())
                && (!verifBolFechadoMM())
                && (!verifBolAbertoSemEnvioMM())
                && (!verifApontMM())){
            enviando = false;
            return false;
        } else {
            return true;
        }
    }

    public int getStatusEnvio() {
        if (enviando) {
            statusEnvio = 1;
        } else {
            if (!verifDadosEnvio()) {
                statusEnvio = 3;
            } else {
                statusEnvio = 2;
            }
        }
        return statusEnvio;
    }

    public void setEnviando(boolean enviando) {
        this.enviando = enviando;
    }

    public void setStatusEnvio(int statusEnvio) {
        this.statusEnvio = statusEnvio;
    }

}
