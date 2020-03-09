package br.com.usinasantafe.ecm.control;

import java.util.List;

import br.com.usinasantafe.ecm.model.bean.estaticas.EquipBean;
import br.com.usinasantafe.ecm.model.bean.estaticas.RAtivOSBean;
import br.com.usinasantafe.ecm.model.bean.estaticas.RLibOSBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.CarretaUtilBean;
import br.com.usinasantafe.ecm.model.dao.CarretaDAO;
import br.com.usinasantafe.ecm.model.dao.CertifCanaDAO;
import br.com.usinasantafe.ecm.model.dao.RAtivOSDAO;
import br.com.usinasantafe.ecm.model.dao.RLibOSDAO;

public class CertifCanaCTR {

    public CertifCanaCTR() {
    }

    //////////////////////////////CABECALHO ABERTO//////////////////////////////////////////

    public void salvarCertifAberto(){
        CertifCanaDAO certifCanaDAO = new CertifCanaDAO();
        certifCanaDAO.salvarCertifAberto();
    }

    public void delCertifAberto(){
        CertifCanaDAO certifCanaDAO = new CertifCanaDAO();
        delCarretaCertif(certifCanaDAO.getCertifAberto().getIdCertifCana());
        certifCanaDAO.delCertifAberto();
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

    public boolean verCertifAberto(){
        CertifCanaDAO certifCanaDAO = new CertifCanaDAO();
        return certifCanaDAO.verCertifAberto();
    }

    public boolean verDataCertif(){
        CertifCanaDAO certifCanaDAO = new CertifCanaDAO();
        return certifCanaDAO.verDataCertif();
    }

    public boolean verAtivOS(Long idRAtivOS){
        RAtivOSDAO rAtivOSDAO = new RAtivOSDAO();
        return rAtivOSDAO.verAtivOS(idRAtivOS);
    }

    public boolean verNroOS(Long nroOS){
        CertifCanaDAO certifCanaDAO = new CertifCanaDAO();
        RAtivOSDAO rAtivOSDAO = new RAtivOSDAO();
        return rAtivOSDAO.verNroOS(certifCanaDAO.getCertifAberto().getAtivOS(), nroOS);
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
        CertifCanaDAO certifCanaDAO = new CertifCanaDAO();
        RLibOSDAO rLibOSDAO = new RLibOSDAO();
        return  rLibOSDAO.verLibOS(codLib, certifCanaDAO.getCertifAberto().getNroOS());
    }

    ////////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////SET DADOS////////////////////////////////

    public void setDataSaidaCampo(){
        CertifCanaDAO certifCanaDAO = new CertifCanaDAO();
        certifCanaDAO.setDataSaidaCampo();
        MotoMecCTR motoMecCTR = new MotoMecCTR();
        motoMecCTR.salvaSaidaCampoMM();
    }

    public void setDataChegCampo(){
        CertifCanaDAO certifCanaDAO = new CertifCanaDAO();
        certifCanaDAO.setDataChegCampo();
    }

    public void setAtivOS(Long ativOS){
        CertifCanaDAO certifCanaDAO = new CertifCanaDAO();
        certifCanaDAO.setAtivOS(ativOS);
    }

    public void setLibCam(Long libCam){
        CertifCanaDAO certifCanaDAO = new CertifCanaDAO();
        certifCanaDAO.setLibCam(libCam);
    }

    public void setNroOS(Long nroOS){
        CertifCanaDAO certifCanaDAO = new CertifCanaDAO();
        certifCanaDAO.setNroOS(nroOS);
    }

    public void insCarreta(Long nroCarreta, Long tipo){
        CarretaDAO carretaDAO = new CarretaDAO();
        CertifCanaDAO certifCanaDAO = new CertifCanaDAO();
        Long posCarreta = carretaDAO.posCarreta(tipo) + 1;
        Long idCertif = 0L;
        if(tipo == 1){
            idCertif = certifCanaDAO.getCertifAberto().getIdCertifCana();
        }
        carretaDAO.insCarreta(idCertif, posCarreta, nroCarreta, tipo);
    }

    public void setLibCarreta(Long nroLib){
        CarretaDAO carretaDAO = new CarretaDAO();
        CertifCanaDAO certifCanaDAO = new CertifCanaDAO();
        Long posCarreta = carretaDAO.posCarreta(1L);
        Long idCertif = certifCanaDAO.getCertifAberto().getIdCertifCana();
        CarretaUtilBean carretaUtilBean = carretaDAO.getCarreta(idCertif, posCarreta);
        carretaDAO.setLibCarreta(carretaUtilBean, nroLib);
    }

    /////////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////GET DADOS/////////////////////////////////

    public String getDataChegCampo(){
        CertifCanaDAO certifCanaDAO = new CertifCanaDAO();
        return certifCanaDAO.getDataChegCampo();
    }

    public Long getPosCarreta(Long tipo){
        CarretaDAO carretaDAO = new CarretaDAO();
        return carretaDAO.posCarreta(tipo);
    }

    public RAtivOSBean getAtivOS(){
        RAtivOSDAO rAtivOSDAO = new RAtivOSDAO();
        CertifCanaDAO certifCanaDAO = new CertifCanaDAO();
        RAtivOSBean rAtivOSBean = rAtivOSDAO.getAtivOS(certifCanaDAO.getCertifAberto().getAtivOS());
        return rAtivOSBean;
    }

    public RLibOSBean getRLibOSBean(){
        RLibOSDAO rLibOSDAO = new RLibOSDAO();
        CertifCanaDAO certifCanaDAO = new CertifCanaDAO();
        Long libCam = 0L;
        if(!verQtdeCarreta(1L)){
            libCam = certifCanaDAO.getCertifAberto().getLibCam();
        }
        RLibOSBean rLibOSBean = rLibOSDAO.getRLibOSBean(libCam, certifCanaDAO.getCertifAberto().getNroOS());
        return rLibOSBean;
    }

    /////////////////////////////////////////////////////////////////////////////

}
