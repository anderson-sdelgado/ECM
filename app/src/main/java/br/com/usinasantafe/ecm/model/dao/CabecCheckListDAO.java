package br.com.usinasantafe.ecm.model.dao;

import java.util.List;

import br.com.usinasantafe.ecm.model.bean.estaticas.ItemCLBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.CabecCheckListBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.ConfigBean;
import br.com.usinasantafe.ecm.util.Tempo;

public class CabecCheckListDAO {

    public CabecCheckListDAO() {
    }

    public Long getIdCabecAberto(){
        CabecCheckListBean cabecCheckListBean = getCabecCheckListAberto();
        return cabecCheckListBean.getIdCabecCheckList();
    }

    public Long getQtdeItemCabecAberto(){
        CabecCheckListBean cabecCheckListBean = getCabecCheckListAberto();
        return cabecCheckListBean.getQtdeItemCabecCheckList();
    }

    private CabecCheckListBean getCabecCheckListAberto(){
        CabecCheckListBean cabecCheckListBean = new CabecCheckListBean();
        List cabecList = cabecCheckListBean.get("statusCabecCheckList", 1L);
        cabecCheckListBean = (CabecCheckListBean) cabecList.get(0);
        cabecList.clear();
        return  cabecCheckListBean;
    }

    public void insCabec(Long idCheckListEquip, ConfigBean configBean){

        ItemCLBean itemCheckListBean = new ItemCLBean();
        List itemCheckList = itemCheckListBean.get("idCheckList", idCheckListEquip);
        Long qtde = (long) itemCheckList.size();
        itemCheckList.clear();

        CabecCheckListBean cabecCheckListBean = new CabecCheckListBean();
        cabecCheckListBean.setDtCabecCheckList(Tempo.getInstance().dataComHora());
        cabecCheckListBean.setEquipCabecCheckList(configBean.getCodEquipConfig());
        cabecCheckListBean.setFuncCabecCheckList(configBean.getMatricColabConfig());
        cabecCheckListBean.setTurnoCabecCheckList(configBean.getIdTurnoConfig());
        cabecCheckListBean.setQtdeItemCabecCheckList(qtde);
        cabecCheckListBean.setStatusCabecCheckList(1L);
        cabecCheckListBean.insert();

    }

    public void fechaCabec(){
        CabecCheckListBean cabecCheckListBean = getCabecCheckListAberto();
        cabecCheckListBean.setStatusCabecCheckList(2L);
        cabecCheckListBean.update();
    }

}
