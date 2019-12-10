package br.com.usinasantafe.ecm.control;

import java.util.List;

import br.com.usinasantafe.ecm.model.bean.estaticas.MotoMecBean;
import br.com.usinasantafe.ecm.model.dao.CarretaDAO;
import br.com.usinasantafe.ecm.model.dao.CertifCanaDAO;
import br.com.usinasantafe.ecm.model.dao.ConfigDAO;
import br.com.usinasantafe.ecm.model.dao.MotoMecDAO;

public class MotoMecCTR {

    public MotoMecCTR() {
    }

    public List getMotoMecList() {
        MotoMecDAO motoMecDAO = new MotoMecDAO();
        return motoMecDAO.getMotoMecList();
    }

    public List getParadaList() {
        MotoMecDAO motoMecDAO = new MotoMecDAO();
        return motoMecDAO.getParadaList();
    }

    public void salvaMotoMec(Long opCorMotoMec){
        MotoMecDAO motoMecDAO = new MotoMecDAO();
        CertifCanaDAO certifCanaDAO = new CertifCanaDAO();
        Long ativOS = 0L;
        if(certifCanaDAO.verCertifAberto()){
            ativOS = certifCanaDAO.getCertifAberto().getAtivOS();
        }
        ConfigDAO configDAO = new ConfigDAO();
        motoMecDAO.salvaMotoMec(opCorMotoMec, ativOS, configDAO.getConfig());
    }

    public Long getOpCorSaidaUsina(){
        MotoMecDAO motoMecDAO = new MotoMecDAO();
        return motoMecDAO.getOpCorSaidaUsina();
    }

    public Long getDesengateCarreta(){
        MotoMecDAO motoMecDAO = new MotoMecDAO();
        return motoMecDAO.getDesengateCarreta();
    }

    public Long getEngateCarreta(){
        MotoMecDAO motoMecDAO = new MotoMecDAO();
        return motoMecDAO.getEngateCarreta();
    }

    public Long getCheckList(){
        MotoMecDAO motoMecDAO = new MotoMecDAO();
        return motoMecDAO.getCheckList();
    }

    public Long getVoltaTrabalho(){
        MotoMecDAO motoMecDAO = new MotoMecDAO();
        return motoMecDAO.getVoltaTrabalho();
    }

    public String textoCarreta(){
        CarretaDAO carretaDAO = new CarretaDAO();
        return carretaDAO.textoCarreta();
    }

}
