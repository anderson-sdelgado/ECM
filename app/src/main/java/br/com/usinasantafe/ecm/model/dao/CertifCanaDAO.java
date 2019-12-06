package br.com.usinasantafe.ecm.model.dao;

import java.util.List;

import br.com.usinasantafe.ecm.model.bean.variaveis.CertifCanaBean;
import br.com.usinasantafe.ecm.util.Tempo;

public class CertifCanaDAO {

    public CertifCanaDAO() {
    }

    ///////////////////////////////////CERTIFICADO ABERTO//////////////////////////////////////////

    public boolean verCertifAberto(){
        List certifAbertoList = certifAbertoList();
        boolean retorno = certifAbertoList.size() > 0;
        certifAbertoList.clear();
        return retorno;
    }

    public List certifAbertoList(){
        CertifCanaBean certifCanaBean = new CertifCanaBean();
        List equipList = certifCanaBean.get("status", 1L);
        return equipList;
    }

    public CertifCanaBean getCertifAberto(){
        List certifCanaList = certifAbertoList();
        CertifCanaBean certifCanaBean = (CertifCanaBean) certifCanaList.get(0);
        certifCanaList.clear();
        return certifCanaBean;
    }

    public void salvarCertifAberto(){
        CertifCanaBean certifCanaBean = new CertifCanaBean();
        certifCanaBean.setDataSaidaUsina(Tempo.getInstance().dataComHora());
        certifCanaBean.setDataChegCampo("");
        certifCanaBean.setDataSaidaCampo("");
        certifCanaBean.setAtivOS(0L);
        certifCanaBean.setCam(0L);
        certifCanaBean.setLibCam(0L);
        certifCanaBean.setCarr1(0L);
        certifCanaBean.setLibCarr1(0L);
        certifCanaBean.setCarr2(0L);
        certifCanaBean.setLibCarr2(0L);
        certifCanaBean.setCarr3(0L);
        certifCanaBean.setLibCarr3(0L);
        certifCanaBean.setCarr4(0L);
        certifCanaBean.setLibCarr4(0L);
        certifCanaBean.setStatus(1L);
        certifCanaBean.insert();
    }

    public String getDataChegCampo(){
        CertifCanaBean certifCanaBean = getCertifAberto();
        return certifCanaBean.getDataChegCampo();
    }

    public void setDataChegCampo(){
        CertifCanaBean certifCanaBean = getCertifAberto();
        certifCanaBean.setDataChegCampo(Tempo.getInstance().dataComHora());
        certifCanaBean.update();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////CERTIFICADO ABERTO//////////////////////////////////////////

    public boolean verCertifFechado(){
        List certifFechadoList = certifFechadoList();
        boolean retorno = certifFechadoList.size() > 0;
        certifFechadoList.clear();
        return retorno;
    }

    public List certifFechadoList(){
        CertifCanaBean certifCanaBean = new CertifCanaBean();
        List equipList = certifCanaBean.get("status", 2L);
        return equipList;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

}
