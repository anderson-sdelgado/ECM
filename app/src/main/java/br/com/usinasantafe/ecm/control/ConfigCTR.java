package br.com.usinasantafe.ecm.control;

import br.com.usinasantafe.ecm.model.bean.estaticas.ColabBean;
import br.com.usinasantafe.ecm.model.bean.estaticas.EquipBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.ConfigBean;
import br.com.usinasantafe.ecm.model.dao.CaminhaoDAO;
import br.com.usinasantafe.ecm.model.dao.ColabDAO;
import br.com.usinasantafe.ecm.model.dao.ConfigDAO;

public class ConfigCTR {

    public ConfigCTR() {
    }

    ///////////////////////////////////////// CONFIG //////////////////////////////////////////////

    public boolean hasElemConfig(){
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

    public ColabBean getColab(){
        ColabDAO colabDAO = new ColabDAO();
        return colabDAO.getColab(getConfig().getMatricColabConfig());
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////////// SET CAMPOS ////////////////////////////////////////////

    public void setMatricColabConfig(Long matricColab){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setMatricColabConfig(matricColab);
    }

    public void setIdTurnoConfig(Long nroTurnoConfig){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setIdTurnoConfig(nroTurnoConfig);
    }

    public void setUltTurnoCLConfig(){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setUltTurnoCLConfig(configDAO.getIdTurnoConfig());
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////////// VER CAMPOS ////////////////////////////////////////////

    public boolean verCaminhao(Long codEquip){
        CaminhaoDAO caminhaoDAO = new CaminhaoDAO();
        return caminhaoDAO.verCaminhao(codEquip);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

}
