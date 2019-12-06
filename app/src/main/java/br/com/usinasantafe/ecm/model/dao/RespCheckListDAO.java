package br.com.usinasantafe.ecm.model.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.ecm.control.ConfigCTR;
import br.com.usinasantafe.ecm.model.bean.estaticas.EquipBean;
import br.com.usinasantafe.ecm.model.bean.estaticas.ItemCLBean;
import br.com.usinasantafe.ecm.model.bean.pst.PesquisaBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.RespCheckListBean;

public class RespCheckListDAO {

    public RespCheckListDAO() {
    }

    public ItemCLBean getItemCheckList(int pos, EquipBean equipBean){

        ItemCLBean itemCheckListBean = new ItemCLBean();
        List itemCheckList = itemCheckListBean.get("idCheckList", equipBean.getIdCheckListEquip());
        itemCheckListBean = (ItemCLBean) itemCheckList.get(pos - 1);
        itemCheckList.clear();

        return itemCheckListBean;

    }

    public void salvarRespCheckList(Long idCabCL, RespCheckListBean respCheckListBean){

        ArrayList pesqArrayList = new ArrayList();
        PesquisaBean pesquisa1 = new PesquisaBean();
        pesquisa1.setCampo("idCabItCL");
        pesquisa1.setValor(idCabCL);
        pesquisa1.setTipo(1);
        pesqArrayList.add(pesquisa1);

        PesquisaBean pesquisa2 = new PesquisaBean();
        pesquisa2.setCampo("idItBDItCL");
        pesquisa2.setValor(respCheckListBean.getIdItItemCheckList());
        pesquisa2.setTipo(1);
        pesqArrayList.add(pesquisa2);

        List respList = respCheckListBean.get(pesqArrayList);
        if(respList.size() > 0) {
            Long opcao = respCheckListBean.getOpcaoItemCheckList();
            respCheckListBean = (RespCheckListBean) respList.get(0);
            respCheckListBean.setOpcaoItemCheckList(opcao);
            respCheckListBean.update();
        }
        else{
            respCheckListBean.setIdCabecItemCheckList(idCabCL);
            respCheckListBean.insert();
        }
        respList.clear();
    }

}
