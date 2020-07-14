package br.com.usinasantafe.ecm.control;

import android.app.ProgressDialog;
import android.content.Context;

import java.util.List;

import br.com.usinasantafe.ecm.model.bean.estaticas.OSBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.CECBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.PreCECBean;
import br.com.usinasantafe.ecm.model.dao.BoletimMMDAO;
import br.com.usinasantafe.ecm.model.dao.CECDAO;
import br.com.usinasantafe.ecm.model.dao.CarretaDAO;
import br.com.usinasantafe.ecm.model.dao.EquipDAO;
import br.com.usinasantafe.ecm.model.dao.OSDAO;
import br.com.usinasantafe.ecm.model.dao.PreCECDAO;

public class CECCTR {

    public CECCTR() {
    }

    ///////////////////////////////////// CABECALHO //////////////////////////////////////////////

    public void salvarPrecCECAberto(){
        PreCECDAO preCECDAO = new PreCECDAO();
        preCECDAO.abrirPreCEC();
    }

    public void clearPreCECAberto(){
        PreCECDAO preCECDAO = new PreCECDAO();
        preCECDAO.clearPreCECAberto();
    }

    public void fechaPreCEC(){
        BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
        PreCECDAO preCECDAO = new PreCECDAO();
        preCECDAO.fechaPreCEC(boletimMMDAO.getBolMMAberto());
    }

    public String dadosEnvioPreCEC(){
        PreCECDAO preCECDAO = new PreCECDAO();
        return preCECDAO.dadosEnvioPreCEC();
    }

    public void atualPreCEC(String result){
        PreCECDAO preCECDAO = new PreCECDAO();
        preCECDAO.updatePreCEC(result);
    }

    public void recDados(String result){
        int pos1 = result.indexOf("_") + 1;
        String precec = result.substring(0, (pos1 - 1));
        String cec = result.substring(pos1);
        PreCECDAO preCECDAO = new PreCECDAO();
        preCECDAO.atualPreCEC(precec);
        CECDAO cecDAO = new CECDAO();
        cecDAO.recDadosCEC(cec);
    }

    public void delPreCECAberto(){
        PreCECDAO preCECDAO = new PreCECDAO();
        preCECDAO.delPreCECAberto();
    }

    public void delCEC(){
        CECDAO cecDAO = new CECDAO();
        cecDAO.delCEC();
    }

    /////////////////////////////VERIFICAR DADOS////////////////////////////////

    public boolean verPreCECAberto(){
        PreCECDAO preCECDAO = new PreCECDAO();
        return preCECDAO.verPreCECAberto();
    }

    public boolean verPreCECFechado(){
        PreCECDAO preCECDAO = new PreCECDAO();
        return preCECDAO.verPreCECFechado();
    }

    public boolean verDataPreCEC(){
        PreCECDAO preCECDAO = new PreCECDAO();
        return preCECDAO.verDataPreCEC();
    }

    public boolean verAtivOS(Long idAtivOS){
        OSDAO osDAO = new OSDAO();
        ConfigCTR configCTR = new ConfigCTR();
        return osDAO.verAtivOS(idAtivOS, configCTR.getConfig().getOsConfig());
    }

    public boolean verLibOS(Long idLibOS){
        OSDAO osDAO = new OSDAO();
        ConfigCTR configCTR = new ConfigCTR();
        return osDAO.verLibOS(idLibOS, configCTR.getConfig().getOsConfig());
    }

    public boolean verCEC(){
        CECDAO cecDAO = new CECDAO();
        return cecDAO.verCEC();
    }

    public void verCECServ(Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        CECDAO cecDAO = new CECDAO();
        EquipDAO equipDAO = new EquipDAO();
        PreCECDAO preCECDAO = new PreCECDAO();
        String dados = equipDAO.dadosEnvioEquip() + "_" + preCECDAO.dadosEnvioPreCEC();
        cecDAO.verCEC(dados, telaAtual, telaProx, progressDialog);
    }

    ////////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////SET DADOS////////////////////////////////

    public void setDataChegCampo(){
        PreCECDAO preCECDAO = new PreCECDAO();
        preCECDAO.setDataChegCampo();
    }

    public void setDataSaidaCampo(){
        PreCECDAO preCECDAO = new PreCECDAO();
        preCECDAO.setDataSaidaCampo();
    }

    public void setAtivOS(Long ativOS){
        PreCECDAO preCECDAO = new PreCECDAO();
        preCECDAO.setAtivOS(ativOS);
    }

    public void setLib(Long libCam){
        PreCECDAO preCECDAO = new PreCECDAO();
        CarretaDAO carretaDAO = new CarretaDAO();
        preCECDAO.setLib(libCam, carretaDAO.getQtdeCarreta());
    }

    public void setCarr(Long carr){
        PreCECDAO preCECDAO = new PreCECDAO();
        CarretaDAO carretaDAO = new CarretaDAO();
        preCECDAO.setCarr(carr, carretaDAO.getQtdeCarreta());
    }

    /////////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////GET DADOS/////////////////////////////////

    public String getDataChegCampo(){
        PreCECDAO preCECDAO = new PreCECDAO();
        return preCECDAO.getDataChegCampo();
    }

    public OSBean getOSAtiv(){
        OSDAO osDAO = new OSDAO();
        ConfigCTR configCTR = new ConfigCTR();
        return osDAO.getOSAtiv(getPreCECAberto().getAtivOS(), configCTR.getConfig().getOsConfig());
    }

    public OSBean getOSLib(){
        OSDAO osDAO = new OSDAO();
        ConfigCTR configCTR = new ConfigCTR();
        return osDAO.getOSLib(getLib(), configCTR.getConfig().getOsConfig());
    }

    public List getPreCECFechadoList(){
        PreCECDAO preCECDAO = new PreCECDAO();
        return preCECDAO.getPreCECListFechado();
    }

    public Long getLib(){
        PreCECDAO preCECDAO = new PreCECDAO();
        CarretaDAO carretaDAO = new CarretaDAO();
        return preCECDAO.getLib(carretaDAO.getQtdeCarreta());
    }

    public List getPreCECListEnviado(){
        PreCECDAO preCECDAO = new PreCECDAO();
        return preCECDAO.getPreCECListEnviado();
    }

    public PreCECBean getPreCECAberto(){
        PreCECDAO preCECDAO = new PreCECDAO();
        return preCECDAO.getPreCECAberto();
    }

    public CECBean getCEC(){
        CECDAO cecDAO = new CECDAO();
        return cecDAO.getCEC();
    }

    /////////////////////////////////////////////////////////////////////////////

}
