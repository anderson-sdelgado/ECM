package br.com.usinasantafe.ecm.model.dao;

import java.util.List;

import br.com.usinasantafe.ecm.model.bean.variaveis.PreCECBean;
import br.com.usinasantafe.ecm.util.Tempo;

public class PreCECDAO {

    public PreCECDAO() {
    }

    ///////////////////////////////////CERTIFICADO ABERTO//////////////////////////////////////////

    public void salvarCertifAberto(){
        PreCECBean preCECBean = new PreCECBean();
        preCECBean.setDataSaidaUsina(Tempo.getInstance().dataComHora().getDataHora());
        preCECBean.setDataChegCampo("");
        preCECBean.setDataSaidaCampo("");
        preCECBean.setAtivOS(0L);
        preCECBean.setCam(0L);
        preCECBean.setLibCam(0L);
        preCECBean.setCarr1(0L);
        preCECBean.setLibCarr1(0L);
        preCECBean.setCarr2(0L);
        preCECBean.setLibCarr2(0L);
        preCECBean.setCarr3(0L);
        preCECBean.setLibCarr3(0L);
        preCECBean.setCarr4(0L);
        preCECBean.setLibCarr4(0L);
        preCECBean.setStatus(1L);
        preCECBean.insert();
    }

    private List certifAbertoList(){
        PreCECBean preCECBean = new PreCECBean();
        List certifCanaList = preCECBean.get("status", 1L);
        return certifCanaList;
    }

    public void delCertifAberto(){
        List certifCanaList = certifAbertoList();
        PreCECBean preCECBean = (PreCECBean)  certifCanaList.get(0);
        preCECBean.delete();
    }

    /////////////////////////////VERIFICAR DADOS////////////////////////////////

    public boolean verCertifAberto(){
        List certifAbertoList = certifAbertoList();
        boolean retorno = certifAbertoList.size() > 0;
        certifAbertoList.clear();
        return retorno;
    }

    public boolean verDataCertif(){
        List certifCanaList = certifAbertoList();
        PreCECBean preCECBean = (PreCECBean) certifCanaList.get(0);
        certifCanaList.clear();
        return ((!preCECBean.getDataSaidaUsina().equals("")) && (!preCECBean.getDataChegCampo().equals("")));
    }

    ////////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////SET DADOS////////////////////////////////

    public void setDataChegCampo(){
        PreCECBean preCECBean = getCertifAberto();
        preCECBean.setDataChegCampo(Tempo.getInstance().dataComHora().getDataHora());
        preCECBean.update();
    }

    public void setDataSaidaCampo(){
        PreCECBean preCECBean = getCertifAberto();
        preCECBean.setDataSaidaCampo(Tempo.getInstance().dataComHora());
        preCECBean.update();
    }

    public void setAtivOS(Long ativOS){
        PreCECBean preCECBean = getCertifAberto();
        preCECBean.setAtivOS(ativOS);
        preCECBean.update();
    }

    public void setLibCam(Long libCam){
        PreCECBean preCECBean = getCertifAberto();
        preCECBean.setLibCam(libCam);
        preCECBean.update();
    }

    public void setNroOS(Long nroOS){
        PreCECBean preCECBean = getCertifAberto();
        preCECBean.setNroOS(nroOS);
        preCECBean.update();
    }

    /////////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////GET DADOS/////////////////////////////////

    public PreCECBean getCertifAberto(){
        List certifCanaList = certifAbertoList();
        PreCECBean preCECBean = (PreCECBean) certifCanaList.get(0);
        certifCanaList.clear();
        return preCECBean;
    }

    public String getDataChegCampo(){
        PreCECBean preCECBean = getCertifAberto();
        return preCECBean.getDataChegCampo();
    }

    public String getDataSaidaUlt(){
        PreCECBean preCECBean = new PreCECBean();
        int qtdePreCEC = preCECBean.count();

        String retorno;
        if (qtdePreCEC == 0) {
            retorno = "NÃO POSSUE CARREGAMENTOS";
        } else {
            List preCECList = preCECBean.all();
            preCECBean = (PreCECBean) preCECList.get(qtdePreCEC - 1);
            retorno = "ULT. VIAGEM: " + Tempo.getInstance().dataHoraCTZ(preCECBean.getDataSaidaCampo());
        }

        return retorno;

    }

    /////////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////CERTIFICADO FECHADO/////////////////////////////////////////

    public boolean verCertifFechado(){
        List certifFechadoList = certifFechadoList();
        boolean retorno = certifFechadoList.size() > 0;
        certifFechadoList.clear();
        return retorno;
    }

    public List certifFechadoList(){
        PreCECBean preCECBean = new PreCECBean();
        List equipList = preCECBean.get("status", 2L);
        return equipList;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

}
