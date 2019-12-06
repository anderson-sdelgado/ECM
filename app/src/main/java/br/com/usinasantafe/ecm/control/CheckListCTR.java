package br.com.usinasantafe.ecm.control;

import java.util.List;

import br.com.usinasantafe.ecm.model.bean.estaticas.EquipBean;
import br.com.usinasantafe.ecm.model.bean.estaticas.ItemCLBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.ConfigBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.RespCheckListBean;
import br.com.usinasantafe.ecm.model.dao.CabecCheckListDAO;
import br.com.usinasantafe.ecm.model.dao.ConfigDAO;
import br.com.usinasantafe.ecm.model.dao.EquipDAO;
import br.com.usinasantafe.ecm.model.dao.RespCheckListDAO;

public class CheckListCTR {

    public CheckListCTR() {
    }

    public void insCabec(){
        EquipDAO equipDAO = new EquipDAO();
        ConfigDAO configDAO = new ConfigDAO();
        ConfigBean configBean = configDAO.getConfig();
        EquipBean equipBean = equipDAO.getEquip(configBean.getIdEquipConfig());
        CabecCheckListDAO cabecCheckListDAO = new CabecCheckListDAO();
        cabecCheckListDAO.insCabec(equipBean.getIdCheckListEquip(), configBean);
    }

    public ItemCLBean getItemCheckList(int pos){
        EquipDAO equipDAO = new EquipDAO();
        ConfigDAO configDAO = new ConfigDAO();
        ConfigBean configBean = configDAO.getConfig();
        EquipBean equipBean = equipDAO.getEquip(configBean.getIdEquipConfig());
        RespCheckListDAO respCheckListDAO = new RespCheckListDAO();
        return respCheckListDAO.getItemCheckList(pos, equipBean);
    }

    public void insResp(RespCheckListBean respCheckListBean){
        CabecCheckListDAO cabecCheckListDAO = new CabecCheckListDAO();
        RespCheckListDAO respCheckListDAO = new RespCheckListDAO();
        respCheckListDAO.salvarRespCheckList(cabecCheckListDAO.getIdCabecAberto(), respCheckListBean);
    }

    public Long getQtdeItemCabecAberto(){
        CabecCheckListDAO cabecCheckListDAO = new CabecCheckListDAO();
        return cabecCheckListDAO.getQtdeItemCabecAberto();
    }

    public void fechaCabec(){
        CabecCheckListDAO cabecCheckListDAO = new CabecCheckListDAO();
        cabecCheckListDAO.fechaCabec();
    }

}
