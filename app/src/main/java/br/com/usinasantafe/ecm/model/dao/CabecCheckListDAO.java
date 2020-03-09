package br.com.usinasantafe.ecm.model.dao;

import java.util.List;

import br.com.usinasantafe.ecm.model.bean.estaticas.ItemCLBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.CabecCLBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.ConfigBean;
import br.com.usinasantafe.ecm.util.Tempo;

public class CabecCheckListDAO {

    public CabecCheckListDAO() {
    }

    public boolean verCabecAberto(){
        CabecCLBean cabecCLBean = new CabecCLBean();
        List cabecList = cabecCLBean.get("statusCabecCheckList", 1L);
        Boolean ret = (cabecList.size() > 0);
        cabecList.clear();
        return ret;
    }

    public Long getIdCabecAberto(){
        CabecCLBean cabecCLBean = getCabecAberto();
        return cabecCLBean.getIdCabCL();
    }


    public CabecCLBean getCabecAberto(){
        CabecCLBean cabecCLBean = new CabecCLBean();
        List cabecList = cabecCLBean.get("statusCabecCheckList", 1L);
        cabecCLBean = (CabecCLBean) cabecList.get(0);
        cabecList.clear();
        return cabecCLBean;
    }

    public void insCabec(Long idCheckListEquip, ConfigBean configBean){

        ItemCLBean itemCheckListBean = new ItemCLBean();
        List itemCheckList = itemCheckListBean.get("idCheckList", idCheckListEquip);
        Long qtde = (long) itemCheckList.size();
        itemCheckList.clear();

        CabecCLBean cabecCLBean = new CabecCLBean();
        cabecCLBean.setDtCabCL(Tempo.getInstance().dataComHora());
        cabecCLBean.setEquipCabCL(configBean.getCodEquipConfig());
        cabecCLBean.setFuncCabCL(configBean.getMatricColabConfig());
        cabecCLBean.setTurnoCabCL(configBean.getIdTurnoConfig());
        cabecCLBean.setStatusCabCL(1L);
        cabecCLBean.insert();

    }

    public void fechaCabec(){
        CabecCLBean cabecCLBean = getCabecAberto();
        cabecCLBean.setStatusCabCL(2L);
        cabecCLBean.update();
    }

}
