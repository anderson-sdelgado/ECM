package br.com.usinasantafe.ecm.model.dao;

import java.util.List;

import br.com.usinasantafe.ecm.model.bean.estaticas.EquipBean;
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
        configBean.deleteAll();
        configBean.insert();
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

    public void setStatusConConfig(Long status){
        ConfigBean configBean = getConfig();
        configBean.setStatusConConfig(status);
        configBean.update();
    }

    public void setHorimetroConfig(Double horimetro){
        ConfigBean configBean = getConfig();
        configBean.setHorimetroConfig(horimetro);
        configBean.setDtUltApontConfig("");
        configBean.update();
    }

    public void setDtUltApontConfig(String data){
        ConfigBean configBean = getConfig();
        configBean.setDtUltApontConfig(data);
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

    public EquipBean getEquip(Long idEquip){
        EquipBean equipBean = new EquipBean();
        List equipList = equipBean.get("idEquip", idEquip);
        equipBean = (EquipBean) equipList.get(0);
        equipList.clear();
        return equipBean;
    }

    public Long getVerInforConfig(){
        ConfigBean configBean = getConfig();
        return configBean.getVerInforConfig();
    }

    public void setDifDthrConfig(Long status){
        ConfigBean configBean = getConfig();
        configBean.setDifDthrConfig(status);
        configBean.update();
    }

    public void setOsConfig(Long nroOS){
        ConfigBean configBean = getConfig();
        configBean.setOsConfig(nroOS);
        configBean.update();
    }

    public Long getOsConfig(){
        return getConfig().getOsConfig();
    }

}
