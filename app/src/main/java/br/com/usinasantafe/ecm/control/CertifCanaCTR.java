package br.com.usinasantafe.ecm.control;

import java.util.List;

import br.com.usinasantafe.ecm.model.bean.variaveis.CertifCanaBean;
import br.com.usinasantafe.ecm.model.dao.CertifCanaDAO;

public class CertifCanaCTR {

    public CertifCanaCTR() {
    }

    public boolean verCertifAberto(){
        CertifCanaDAO certifCanaDAO = new CertifCanaDAO();
        return certifCanaDAO.verCertifAberto();
    }

    public void salvarCertifAberto(){
        CertifCanaDAO certifCanaDAO = new CertifCanaDAO();
        certifCanaDAO.salvarCertifAberto();
    }

    public String getDataChegCampo(){
        CertifCanaDAO certifCanaDAO = new CertifCanaDAO();
        return certifCanaDAO.getDataChegCampo();
    }

    public void setDataChegCampo(){
        CertifCanaDAO certifCanaDAO = new CertifCanaDAO();
        certifCanaDAO.setDataChegCampo();
    }

}
