package br.com.usinasantafe.ecm.model.dao;

import java.util.List;

import br.com.usinasantafe.ecm.model.bean.variaveis.ConfigBean;

public class ConfigDAO {

    public ConfigDAO() {
    }

    public boolean hasElements(){
        ConfigBean configBean = new ConfigBean();
        return configBean.hasElements();
    }

    public ConfigBean getConfig(){
        ConfigBean configBean = new ConfigBean();
        List configList = configBean.all();
        configBean = (ConfigBean) configList.get(0);
        configList.clear();
        return configBean;
    }

    public void insConfig(ConfigBean configBean){
        configBean.setIdTurnoConfig(0L);
        configBean.setUltTurnoCLConfig(0L);
        configBean.setMatricColabConfig(0L);
        configBean.deleteAll();
        configBean.insert();
    }

    public void setMatricColabConfig(Long matricColab){
        ConfigBean configBean = getConfig();
        configBean.setMatricColabConfig(matricColab);
        configBean.update();
    }

    public void setIdTurnoConfig(Long idTurnoConfig){
        ConfigBean configBean = getConfig();
        configBean.setIdTurnoConfig(idTurnoConfig);
        configBean.update();
    }

    public void setUltTurnoCLConfig(Long ultTurnoCLConfig){
        ConfigBean configBean = getConfig();
        configBean.setUltTurnoCLConfig(ultTurnoCLConfig);
        configBean.update();
    }

    public Long getCodEquipConfig(){
        return getConfig().getCodEquipConfig();
    }

    public boolean getConfigSenha(String senha){
        ConfigBean configBean = new ConfigBean();
        List configList = configBean.get("senhaConfig", senha);
        boolean ret = configList.size() > 0;
        configList.clear();
        return ret;
    }

    public Long getIdTurnoConfig(){
        return getConfig().getIdTurnoConfig();
    }

}
