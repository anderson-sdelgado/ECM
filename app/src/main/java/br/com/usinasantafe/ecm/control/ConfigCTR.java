package br.com.usinasantafe.ecm.control;

import android.app.ProgressDialog;
import android.content.Context;

import br.com.usinasantafe.ecm.model.bean.estaticas.ColabBean;
import br.com.usinasantafe.ecm.model.bean.estaticas.EquipBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.ConfigBean;
import br.com.usinasantafe.ecm.model.dao.CaminhaoDAO;
import br.com.usinasantafe.ecm.model.dao.ConfigDAO;
import br.com.usinasantafe.ecm.util.AtualDadosServ;

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

    public void setStatusConConfig(Long status){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setStatusConConfig(status);
    }

    public void setHorimetroConfig(Double horimetro){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setHorimetroConfig(horimetro);
    }

    public void setDtUltApontConfig(String data){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setDtUltApontConfig(data);
    }

    public void setOsConfig(Long nroOS){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setOsConfig(nroOS);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////////// VER CAMPOS ////////////////////////////////////////////

    public boolean verCaminhao(Long codEquip){
        CaminhaoDAO caminhaoDAO = new CaminhaoDAO();
        return caminhaoDAO.verCaminhao(codEquip);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    ///////////////////////////// ATUALIZAÇÃO DE DADOS ////////////////////////////////////////////

    public void atualTodasTabelas(Context tela, ProgressDialog progressDialog){
        AtualDadosServ.getInstance().atualTodasTabBD(tela, progressDialog);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

}
