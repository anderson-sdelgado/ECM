package br.com.usinasantafe.ecm.model.dao;

import android.app.ProgressDialog;
import android.content.Context;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.ecm.control.ConfigCTR;
import br.com.usinasantafe.ecm.model.bean.estaticas.OSBean;
import br.com.usinasantafe.ecm.model.pst.EspecificaPesquisa;
import br.com.usinasantafe.ecm.util.VerifDadosServ;

public class OSDAO {

    public OSDAO() {
    }

    public OSBean getOS(Long nroOS){
        List<OSBean> osList = osList(nroOS);
        OSBean osBean = osList.get(0);
        osList.clear();
        return osBean;
    }

    public OSBean getOSAtiv(Long idAtivOS, Long nroOS){
        List<OSBean> ativOSList = ativOSList(idAtivOS, nroOS);
        OSBean osBean = ativOSList.get(0);
        ativOSList.clear();
        return osBean;
    }

    public OSBean getOSLib(Long idLibOS, Long nroOS){
        List<OSBean> libOSList = libOSList(idLibOS, nroOS);
        OSBean osBean = libOSList.get(0);
        libOSList.clear();
        return osBean;
    }

    public boolean verAtivOS(Long idAtivOS, Long nroOS){
        List<OSBean> ativOSList = ativOSList(idAtivOS, nroOS);
        boolean retorno = ativOSList.size() > 0;
        ativOSList.clear();
        return retorno;
    }

    public boolean verLibOS(Long idLibOS, Long nroOS){
        List<OSBean> libOSList = libOSList(idLibOS, nroOS);
        boolean retorno = libOSList.size() > 0;
        libOSList.clear();
        return retorno;
    }

    private List<OSBean> osList(Long nroOS){
        OSBean rAtivOSBean = new OSBean();
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqNroOS(nroOS));
        return rAtivOSBean.get(pesqArrayList);
    }

    private List<OSBean> ativOSList(Long idAtivOS, Long nroOS){
        OSBean rAtivOSBean = new OSBean();
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqAtiv(idAtivOS));
        pesqArrayList.add(getPesqNroOS(nroOS));
        return rAtivOSBean.get(pesqArrayList);
    }

    private List<OSBean> libOSList(Long idLibOS, Long nroOS){
        OSBean rAtivOSBean = new OSBean();
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqLib(idLibOS));
        pesqArrayList.add(getPesqNroOS(nroOS));
        return rAtivOSBean.get(pesqArrayList);
    }

    private EspecificaPesquisa getPesqAtiv(Long idAtivOS){
        EspecificaPesquisa especificaPesquisa = new EspecificaPesquisa();
        especificaPesquisa.setCampo("idAtivOS");
        especificaPesquisa.setValor(idAtivOS);
        especificaPesquisa.setTipo(1);
        return especificaPesquisa;
    }

    private EspecificaPesquisa getPesqLib(Long idLibOS){
        EspecificaPesquisa especificaPesquisa = new EspecificaPesquisa();
        especificaPesquisa.setCampo("idLibOS");
        especificaPesquisa.setValor(idLibOS);
        especificaPesquisa.setTipo(1);
        return especificaPesquisa;
    }

    private EspecificaPesquisa getPesqNroOS(Long nroOS){
        EspecificaPesquisa especificaPesquisa = new EspecificaPesquisa();
        especificaPesquisa.setCampo("nroOS");
        especificaPesquisa.setValor(nroOS);
        especificaPesquisa.setTipo(1);
        return especificaPesquisa;
    }

    public void verOS(String dado, Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        VerifDadosServ.getInstance().setVerTerm(false);
        VerifDadosServ.getInstance().verDados(dado, "OS", telaAtual, telaProx, progressDialog);
    }

    public void recDadosOS(String result){

        try {

            ConfigCTR configCTR = new ConfigCTR();

            if (!result.contains("exceeded")) {

                JSONObject jObj = new JSONObject(result.trim());
                JSONArray jsonArray = jObj.getJSONArray("dados");

                if (jsonArray.length() > 0) {

                    OSBean osTO = new OSBean();

                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject objeto = jsonArray.getJSONObject(i);
                        Gson gson = new Gson();
                        osTO = gson.fromJson(objeto.toString(), OSBean.class);
                        osTO.insert();

                    }

                    configCTR.setStatusConConfig(1L);
                    VerifDadosServ.getInstance().pulaTelaComTerm();

                } else {

                    configCTR.setStatusConConfig(0L);
                    VerifDadosServ.getInstance().msgComTerm("OS INEXISTENTE NA BASE DE DADOS! FAVOR VERIFICA A NUMERAÇÃO.");

                }

            } else {

                configCTR.setStatusConConfig(0L);
                VerifDadosServ.getInstance().msgComTerm("EXCEDEU TEMPO LIMITE DE PESQUISA! POR FAVOR, PROCURE UM PONTO MELHOR DE CONEXÃO DOS DADOS.");

            }

        } catch (Exception e) {
            VerifDadosServ.getInstance().msgSemTerm("FALHA DE PESQUISA DE OS! POR FAVOR, TENTAR NOVAMENTE COM UM SINAL MELHOR.");
        }

    }

    public boolean verOS(Long nroOS){
        OSBean osTO = new OSBean();
        List osList = osTO.get("nroOS", nroOS);
        boolean ret = osList.size() > 0;
        osList.clear();
        return ret;
    }

}
