package br.com.usinasantafe.ecm.control;

import br.com.usinasantafe.ecm.model.bean.estaticas.ColabBean;
import br.com.usinasantafe.ecm.model.bean.estaticas.EquipBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.ConfigBean;
import br.com.usinasantafe.ecm.model.dao.CaminhaoDAO;
import br.com.usinasantafe.ecm.model.dao.ConfigDAO;

public class ConfigCTR {

    public ConfigCTR() {
    }

    ///////////////////////////////////////// CONFIG //////////////////////////////////////////////

    public boolean hasElements(){
        ConfigDAO configDAO = new ConfigDAO();
        return configDAO.hasElements();
    }

    public ConfigBean getConfig(){
        ConfigDAO configDAO = new ConfigDAO();
        return configDAO.getConfig();
    }

    public void insConfig(ConfigBean configBean){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.insConfig(configBean);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////// GET CONFIG, EQUIP E COLAB ////////////////////////////////////

    public boolean getConfigSenha(String senha){
        ConfigDAO configDAO = new ConfigDAO();
        return configDAO.getConfigSenha(senha);
    }

    public Long getCodEquipConfig(){
        ConfigDAO configDAO = new ConfigDAO();
        return configDAO.getCodEquipConfig();
    }

    public EquipBean getCaminhao(Long nroEquip){
        CaminhaoDAO caminhaoDAO = new CaminhaoDAO();
        return caminhaoDAO.getCaminhao(nroEquip);
    }

    public EquipBean getEquip(){
        ConfigDAO configDAO = new ConfigDAO();
        return configDAO.getEquip(getConfig().getIdEquipConfig());
    }

    public Long getVerInforConfig(){
        ConfigDAO configDAO = new ConfigDAO();
        return configDAO.getVerInforConfig();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////////// SET CAMPOS ////////////////////////////////////////////

    public void setIdTurnoConfig(Long nroTurnoConfig){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setIdTurnoConfig(nroTurnoConfig);
    }

    public void setUltTurnoCLConfig(){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setUltTurnoCLConfig(configDAO.getIdTurnoConfig());
    }

    public void setDifDthrConfig(Long status){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setDifDthrConfig(status);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////////// VER CAMPOS ////////////////////////////////////////////

    public boolean verCaminhao(Long codEquip){
        CaminhaoDAO caminhaoDAO = new CaminhaoDAO();
        return caminhaoDAO.verCaminhao(codEquip);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////



}
