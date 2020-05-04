package br.com.usinasantafe.ecm.util;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.util.Log;

import br.com.usinasantafe.ecm.control.CECCTR;
import br.com.usinasantafe.ecm.control.CheckListCTR;
import br.com.usinasantafe.ecm.control.ConfigCTR;
import br.com.usinasantafe.ecm.control.MotoMecCTR;
import br.com.usinasantafe.ecm.util.connHttp.PostCadGenerico;
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

    ////////////////////////////////// ENVIAR DADOS //////////////////////////////////////////////

    public void enviarChecklist() {

        CheckListCTR checkListCTR = new CheckListCTR();
        String dados = checkListCTR.dadosEnvio();

        UrlsConexaoHttp urlsConexaoHttp = new UrlsConexaoHttp();

        String[] url = {urlsConexaoHttp.getsInsertChecklist()};
        Map<String, Object> parametrosPost = new HashMap<String, Object>();
        parametrosPost.put("dado", dados);

        Log.i("ECM", "DADOS CHECKLIST = " + dados);

        PostCadGenerico conHttpPostGenerico = new PostCadGenerico();
        conHttpPostGenerico.setParametrosPost(parametrosPost);
        conHttpPostGenerico.execute(url);

    }

    public void envioPreCEC() {

        CECCTR cecCTR = new CECCTR();
        String dados = cecCTR.dadosEnvioPreCEC();

        String[] url = {urlsConexaoHttp.getsInsertPreCEC()};
        Map<String, Object> parametrosPost = new HashMap<String, Object>();
        parametrosPost.put("dado", dados);

        Log.i("ECM", "DADOS VIAGEM = " + dados);

        PostCadGenerico.getInstance().setParametrosPost(parametrosPost);

        PostCadGenerico postCadGenerico = new PostCadGenerico();
        postCadGenerico.setParametrosPost(parametrosPost);
        postCadGenerico.execute(url);

    }

    public void enviarBolFechadosMM() {

        MotoMecCTR motoMecCTR = new MotoMecCTR();
        String dados = motoMecCTR.dadosEnvioBolFechadoMM();

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
        String dados = motoMecCTR.dadosEnvioBolAbertoMM();

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

    ///////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////////RECEBIMENTO DE DADOS//////////////////////////////////////

    public void recDados(String result){
        if(result.trim().startsWith("GRAVOU-CHECKLIST")){
            CheckListCTR checkListCTR = new CheckListCTR();
            checkListCTR.delChecklist();
        } else if (result.trim().startsWith("BOLABERTOMM")) {
            MotoMecCTR motoMecCTR = new MotoMecCTR();
            motoMecCTR.updBolAbertoMM(result);
        } else if (result.trim().startsWith("BOLFECHADOMM")) {
            MotoMecCTR motoMecCTR = new MotoMecCTR();
            motoMecCTR.delBolFechadoMM(result);
        } else if (result.trim().startsWith("APONTMM")) {
            MotoMecCTR motoMecCTR = new MotoMecCTR();
            motoMecCTR.updateApontMM(result);
        } else if(result.trim().startsWith("PRECEC")){
            CECCTR cecCTR = new CECCTR();
            cecCTR.atualPreCEC(result);
        }
        else{
            Tempo.getInstance().setEnvioDado(true);
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////////VERIFICAÇÃO DE DADOS//////////////////////////////////////

    public boolean verifCheckList() {
        CheckListCTR checkListCTR = new CheckListCTR();
        return checkListCTR.verEnvioDados();
    }

    public boolean verifPreCEC() {
        CECCTR cecCTR = new CECCTR();
        return cecCTR.verPreCECFechado();
    }

    public Boolean verifBolFechadoMM() {
        MotoMecCTR motoMecCTR = new MotoMecCTR();
        return motoMecCTR.verEnvioBolFechMM();
    }

    public Boolean verifBolAbertoSemEnvioMM() {
        MotoMecCTR motoMecCTR = new MotoMecCTR();
        return motoMecCTR.verEnvioBolAbertoMM();
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

    ///////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////////MECANISMO DE ENVIO///////////////////////////////////////

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
            } else {
                if (verifPreCEC()) {
                    envioPreCEC();
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
                && (!verifPreCEC())
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

    ///////////////////////////////////////////////////////////////////////////////////////////////

    public void setEnviando(boolean enviando) {
        this.enviando = enviando;
    }

    public void setStatusEnvio(int statusEnvio) {
        this.statusEnvio = statusEnvio;
    }

}
