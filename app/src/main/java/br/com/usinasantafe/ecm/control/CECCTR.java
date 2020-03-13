package br.com.usinasantafe.ecm.control;

import java.util.List;

import br.com.usinasantafe.ecm.model.bean.estaticas.EquipBean;
import br.com.usinasantafe.ecm.model.bean.estaticas.RAtivOSBean;
import br.com.usinasantafe.ecm.model.bean.estaticas.RLibOSBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.CarretaUtilBean;
import br.com.usinasantafe.ecm.model.dao.CarretaDAO;
import br.com.usinasantafe.ecm.model.dao.PreCECDAO;
import br.com.usinasantafe.ecm.model.dao.RAtivOSDAO;
import br.com.usinasantafe.ecm.model.dao.RLibOSDAO;

public class CECCTR {

    public CECCTR() {
    }

    //////////////////////////////CABECALHO ABERTO//////////////////////////////////////////

    public void salvarCertifAberto(){
        PreCECDAO preCECDAO = new PreCECDAO();
        preCECDAO.salvarCertifAberto();
    }

    public void delCertifAberto(){
        PreCECDAO preCECDAO = new PreCECDAO();
        delCarretaCertif(preCECDAO.getCertifAberto().getIdCertifCana());
        preCECDAO.delCertifAberto();
    }

    ////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////CARRETA/////////////////////////////////////

    public void delCarreta(Long tipo){
        CarretaDAO carretaDAO = new CarretaDAO();
        carretaDAO.delCarreta(tipo);
    }

    private void delCarretaCertif(Long idCertif){
        CarretaDAO carretaDAO = new CarretaDAO();
        carretaDAO.delCarretaCertif(idCertif);
    }

    public List carretaList(Long tipo){
        CarretaDAO carretaDAO = new CarretaDAO();
        return carretaDAO.carretaList(tipo);
    }

    ////////////////////////////////////////////////////////////////////////////

    /////////////////////////////VERIFICAR DADOS////////////////////////////////

    public boolean verPreCECAberto(){
        PreCECDAO preCECDAO = new PreCECDAO();
        return preCECDAO.verCertifAberto();
    }

    public boolean verDataCertif(){
        PreCECDAO preCECDAO = new PreCECDAO();
        return preCECDAO.verDataCertif();
    }

    public boolean verAtivOS(Long idRAtivOS){
        RAtivOSDAO rAtivOSDAO = new RAtivOSDAO();
        return rAtivOSDAO.verAtivOS(idRAtivOS);
    }

    public boolean verNroOS(Long nroOS){
        PreCECDAO preCECDAO = new PreCECDAO();
        RAtivOSDAO rAtivOSDAO = new RAtivOSDAO();
        return rAtivOSDAO.verNroOS(preCECDAO.getCertifAberto().getAtivOS(), nroOS);
    }

    public int verCarreta(Long nroCarreta, Long tipo){
        int retorno; //1 - CARRETA CORRETA; 2 - NÃO EXISTE NA BASE DE DADOS; 3 - CARRETA REPETIDA; 4 - CARRETA INVERTIDA;
        CarretaDAO carretaDAO = new CarretaDAO();
        if(carretaDAO.verCarretaBD(nroCarreta)){
            EquipBean carretaBean = carretaDAO.getCarretaBD(nroCarreta);
            if(!carretaDAO.verCarreta(carretaBean.getIdEquip(), tipo) ){
                Long posCarreta = carretaDAO.posCarreta(tipo) + 1;
                ConfigCTR configCTR = new ConfigCTR();
                EquipBean caminhaoBean = configCTR.getEquip();
                if(caminhaoBean.getClasseEquip() == 1){ //CAMINHÃO CANAVIEIRO
                    if(carretaBean.getClasseEquip() != 21){//REBOQUE
                        retorno = 1;
                    }
                    else{
                        retorno = 4;
                    }
                } else { //CAVALO CANAVIEIRO
                    if(carretaBean.getClasseEquip() == 21){  //SEMI REBOQUE
                        if(posCarreta == 1){
                            retorno = 1;
                        }
                        else{
                            retorno = 4;
                        }
                    } else { //REBOQUE
                        if(posCarreta > 1){
                            retorno = 1;
                        }
                        else{
                            retorno = 4;
                        }
                    }
                }
            }
            else{
                retorno = 3;
            }
        }
        else{
            retorno = 2;
        }
        return retorno;
    }

    public boolean verQtdeCarreta(Long tipo){
        CarretaDAO carretaDAO = new CarretaDAO();
        return carretaDAO.verQtdeCarreta(tipo);
    }

    public boolean verLibOS(Long codLib){
        PreCECDAO preCECDAO = new PreCECDAO();
        RLibOSDAO rLibOSDAO = new RLibOSDAO();
        return  rLibOSDAO.verLibOS(codLib, preCECDAO.getCertifAberto().getNroOS());
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
        MotoMecCTR motoMecCTR = new MotoMecCTR();
        motoMecCTR.salvaSaidaCampoMM();
    }

    public void setAtivOS(Long ativOS){
        PreCECDAO preCECDAO = new PreCECDAO();
        preCECDAO.setAtivOS(ativOS);
    }

    public void setLibCam(Long libCam){
        PreCECDAO preCECDAO = new PreCECDAO();
        preCECDAO.setLibCam(libCam);
    }

    public void setNroOS(Long nroOS){
        PreCECDAO preCECDAO = new PreCECDAO();
        preCECDAO.setNroOS(nroOS);
    }

    public void insCarreta(Long nroCarreta, Long tipo){
        CarretaDAO carretaDAO = new CarretaDAO();
        PreCECDAO preCECDAO = new PreCECDAO();
        Long posCarreta = carretaDAO.posCarreta(tipo) + 1;
        Long idCertif = 0L;
        if(tipo == 1){
            idCertif = preCECDAO.getCertifAberto().getIdCertifCana();
        }
        carretaDAO.insCarreta(idCertif, posCarreta, nroCarreta, tipo);
    }

    public void setLibCarreta(Long nroLib){
        CarretaDAO carretaDAO = new CarretaDAO();
        PreCECDAO preCECDAO = new PreCECDAO();
        Long posCarreta = carretaDAO.posCarreta(1L);
        Long idCertif = preCECDAO.getCertifAberto().getIdCertifCana();
        CarretaUtilBean carretaUtilBean = carretaDAO.getCarreta(idCertif, posCarreta);
        carretaDAO.setLibCarreta(carretaUtilBean, nroLib);
    }

    /////////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////GET DADOS/////////////////////////////////

    public String getDataChegCampo(){
        PreCECDAO preCECDAO = new PreCECDAO();
        return preCECDAO.getDataChegCampo();
    }

    public Long getPosCarreta(Long tipo){
        CarretaDAO carretaDAO = new CarretaDAO();
        return carretaDAO.posCarreta(tipo);
    }

    public RAtivOSBean getAtivOS(){
        RAtivOSDAO rAtivOSDAO = new RAtivOSDAO();
        PreCECDAO preCECDAO = new PreCECDAO();
        RAtivOSBean rAtivOSBean = rAtivOSDAO.getAtivOS(preCECDAO.getCertifAberto().getAtivOS());
        return rAtivOSBean;
    }

    public RLibOSBean getRLibOSBean(){
        RLibOSDAO rLibOSDAO = new RLibOSDAO();
        PreCECDAO preCECDAO = new PreCECDAO();
        Long libCam = 0L;
        if(!verQtdeCarreta(1L)){
            libCam = preCECDAO.getCertifAberto().getLibCam();
        }
        RLibOSBean rLibOSBean = rLibOSDAO.getRLibOSBean(libCam, preCECDAO.getCertifAberto().getNroOS());
        return rLibOSBean;
    }

    /////////////////////////////////////////////////////////////////////////////

}
