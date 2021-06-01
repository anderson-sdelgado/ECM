package br.com.usinasantafe.ecm.model.dao;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import br.com.usinasantafe.ecm.model.bean.AtualAplicBean;
import br.com.usinasantafe.ecm.model.bean.estaticas.EquipBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.ConfigBean;
import br.com.usinasantafe.ecm.util.Tempo;

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

    public void salvarConfig(String senha){
        ConfigBean configBean = new ConfigBean();
        configBean.deleteAll();
        configBean.setUltTurnoCLConfig(0L);
        configBean.setDtUltCLConfig("");
        configBean.setDtUltApontConfig("");
        configBean.setDtServConfig("");
        configBean.setDifDthrConfig(0L);
        configBean.setVerInforConfig(0L);
        configBean.setFlagLogErro(0L);
        configBean.setFlagLogEnvio(0L);
        configBean.setSenhaConfig(senha);
        configBean.insert();
        configBean.commit();
    }

    public void setEquipConfig(EquipBean equipBean){
        ConfigBean configBean = getConfig();
        configBean.setEquipConfig(equipBean.getIdEquip());
        configBean.setHorimetroConfig(equipBean.getHorimetroEquip());
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


    public boolean getConfigSenha(String senha){
        ConfigBean configBean = new ConfigBean();
        List configList = configBean.get("senhaConfig", senha);
        boolean ret = configList.size() > 0;
        configList.clear();
        return ret;
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

    public void setDifDthrConfig(Long difDthr){
        ConfigBean configBean = getConfig();
        configBean.setDifDthrConfig(difDthr);
        configBean.update();
    }

    public void setOsConfig(Long nroOS){
        ConfigBean configBean = getConfig();
        configBean.setOsConfig(nroOS);
        configBean.update();
    }

    public void setAtivConfig(Long idAtiv){
        ConfigBean configBean = getConfig();
        configBean.setAtivConfig(idAtiv);
        configBean.update();
    }

    public Long getOsConfig(){
        return getConfig().getOsConfig();
    }

    public void setCheckListConfig(Long idTurno){
        ConfigBean configBean = getConfig();
        configBean.setUltTurnoCLConfig(idTurno);
        configBean.setDtUltCLConfig(Tempo.getInstance().dataSHora());
        configBean.update();
    }

    public void setVerInforConfig(Long tipo){
        ConfigBean configBean = getConfig();
        configBean.setVerInforConfig(tipo);
        configBean.update();
    }

    public AtualAplicBean recAtual(String result) {

        AtualAplicBean atualAplicBean = new AtualAplicBean();

        try {

            JSONObject jObj = new JSONObject(result);
            JSONArray jsonArray = jObj.getJSONArray("dados");

            if (jsonArray.length() > 0) {

                JSONObject objeto = jsonArray.getJSONObject(0);
                Gson gson = new Gson();
                atualAplicBean = gson.fromJson(objeto.toString(), AtualAplicBean.class);

                ConfigBean configBean = getConfig();
                configBean.setFlagLogEnvio(atualAplicBean.getFlagLogEnvio());
                configBean.setFlagLogErro(atualAplicBean.getFlagLogErro());
                configBean.setDtServConfig(atualAplicBean.getDthr());
                configBean.setAtualCheckList(atualAplicBean.getFlagAtualCheckList());
                configBean.update();

            }

        } catch (Exception e) {
        }

        return atualAplicBean;

    }

}
