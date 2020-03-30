package br.com.usinasantafe.ecm.control;

import java.util.List;

import br.com.usinasantafe.ecm.model.bean.estaticas.EquipBean;
import br.com.usinasantafe.ecm.model.bean.estaticas.EquipSegBean;
import br.com.usinasantafe.ecm.model.bean.estaticas.OSBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.PreCECBean;
import br.com.usinasantafe.ecm.model.dao.BoletimMMDAO;
import br.com.usinasantafe.ecm.model.dao.CECDAO;
import br.com.usinasantafe.ecm.model.dao.CarretaDAO;
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

    /////////////////////////////VERIFICAR DADOS////////////////////////////////

    public boolean verPreCECAberto(){
        PreCECDAO preCECDAO = new PreCECDAO();
        return preCECDAO.verPreCECAberto();
    }

    public boolean verPreCECFechado(){
        PreCECDAO preCECDAO = new PreCECDAO();
        return preCECDAO.verPreCECAberto();
    }

    public boolean verDataCertif(){
        PreCECDAO preCECDAO = new PreCECDAO();
        return preCECDAO.verDataCertif();
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

//    public int verCarrPreCEC(Long nroCarreta){
//        int retorno; //1 - CARRETA CORRETA; 2 - NÃO EXISTE NA BASE DE DADOS; 3 - CARRETA REPETIDA; 4 - CARRETA INVERTIDA;
//        CarretaDAO carretaDAO = new CarretaDAO();
//        PreCECDAO preCECDAO = new PreCECDAO();
//        if(carretaDAO.verCarretaBD(nroCarreta)){
//            if(preCECDAO.verCarretaPreCEC(nroCarreta)){
//                ConfigCTR configCTR = new ConfigCTR();
//                EquipBean equipBean = configCTR.getEquip();
//                EquipSegBean carreta = carretaDAO.getCarretaBD(nroCarreta);
//                if(equipBean.getCodClasseEquip() == 1){ //CAMINHÃO CANAVIEIRO
//                    if(carreta.getCodClasseEquip() != 21){//REBOQUE
//                        retorno = 1;
//                    }
//                    else{
//                        retorno = 4;
//                    }
//                } else { //CAVALO CANAVIEIRO
//                    if(carreta.getCodClasseEquip() == 21){  //SEMI REBOQUE
//                        if(getPos() == 1){
//                            retorno = 1;
//                        }
//                        else{
//                            retorno = 4;
//                        }
//                    } else { //REBOQUE
//                        if(getPos() > 1){
//                            retorno = 1;
//                        }
//                        else{
//                            retorno = 4;
//                        }
//                    }
//                }
//            }
//            else{
//                retorno = 3;
//            }
//        }
//        else{
//            retorno = 2;
//        }
//        return retorno;
//    }

    public boolean verCEC(){
        CECDAO cecDAO = new CECDAO();
        return cecDAO.verCEC();
    }

    public void recCEC(){
        if(verPreCECFechado()){

        }
        else{

        }
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

    /////////////////////////////////////////////////////////////////////////////

}
