package br.com.usinasantafe.ecm.control;

import android.app.ProgressDialog;
import android.content.Context;

import java.util.List;

import br.com.usinasantafe.ecm.model.bean.estaticas.EquipBean;
import br.com.usinasantafe.ecm.model.bean.estaticas.FuncBean;
import br.com.usinasantafe.ecm.model.bean.estaticas.OSBean;
import br.com.usinasantafe.ecm.model.bean.estaticas.TurnoBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.ConfigBean;
import br.com.usinasantafe.ecm.model.dao.ConfigDAO;
import br.com.usinasantafe.ecm.model.dao.EquipDAO;
import br.com.usinasantafe.ecm.model.dao.FuncDAO;
import br.com.usinasantafe.ecm.model.dao.OSDAO;
import br.com.usinasantafe.ecm.model.dao.TurnoDAO;
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

    public void salvarConfig(String senha){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.salvarConfig(senha);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////// GET CONFIG, EQUIP E COLAB ////////////////////////////////////

    public boolean getConfigSenha(String senha){
        ConfigDAO configDAO = new ConfigDAO();
        return configDAO.getConfigSenha(senha);
    }

    public EquipBean getEquip(){
        ConfigDAO configDAO = new ConfigDAO();
        return configDAO.getEquip(getConfig().getEquipConfig());
    }

    public Long getVerInforConfig(){
        ConfigDAO configDAO = new ConfigDAO();
        return configDAO.getVerInforConfig();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////////// SET CAMPOS ////////////////////////////////////////////

    public void setCheckListConfig(Long idTurno){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setCheckListConfig(idTurno);
    }

    public void setDifDthrConfig(Long difDthr){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setDifDthrConfig(difDthr);
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

    public void setAtivConfig(Long idAtiv){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setAtivConfig(idAtiv);
    }

    public void setEquipConfig(EquipBean equipBean){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setEquipConfig(equipBean);
    }

    public void setVerInforConfig(Long tipo){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setVerInforConfig(tipo);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////////// VER CAMPOS ////////////////////////////////////////////

    public void verEquipConfig(String dado, Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        EquipDAO equipDAO = new EquipDAO();
        equipDAO.verEquip(dado, telaAtual, telaProx, progressDialog);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    ///////////////////////////// ATUALIZAÇÃO DE DADOS ////////////////////////////////////////////

    public void atualTodasTabelas(Context tela, ProgressDialog progressDialog){
        AtualDadosServ.getInstance().atualTodasTabBD(tela, progressDialog);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    public boolean verOS(Long nroOS){
        OSDAO osDAO = new OSDAO();
        return osDAO.verOS(nroOS);
    }

    public OSBean getOS(Long nroOS){
        OSDAO osDAO = new OSDAO();
        return osDAO.getOS(nroOS);
    }

    ///////////////////////////// FUNCIONARIO ////////////////////////////////////////////

    public boolean verFunc(Long matricFunc){
        FuncDAO funcDAO = new FuncDAO();
        return funcDAO.verFunc(matricFunc);
    }

    public FuncBean getFunc(Long matricColab){
        FuncDAO funcDAO = new FuncDAO();
        return funcDAO.getFunc(matricColab);
    }

    public boolean hasElemFunc(){
        FuncDAO funcDAO = new FuncDAO();
        return funcDAO.hasElements();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    ///////////////////////////// FUNCIONARIO ////////////////////////////////////////////

    public List<TurnoBean> getTurnoList(Long codTurno){
        TurnoDAO turnoDAO = new TurnoDAO();
        return turnoDAO.getTurnoList(codTurno);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

}
