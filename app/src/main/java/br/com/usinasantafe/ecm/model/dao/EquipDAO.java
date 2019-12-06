package br.com.usinasantafe.ecm.model.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.ecm.model.bean.estaticas.EquipBean;
import br.com.usinasantafe.ecm.model.bean.pst.PesquisaBean;

public class EquipDAO {

    public EquipDAO() {
    }

    public boolean verCaminhao(Long codEquip){
        List equipList = caminhaoList(codEquip);
        boolean retorno = equipList.size() > 0;
        equipList.clear();
        return retorno;
    }

    public List caminhaoList(Long codEquip){

        EquipBean equipBean = new EquipBean();
        ArrayList pesqArrayList = new ArrayList();

        PesquisaBean pesquisaBean = new PesquisaBean();
        pesquisaBean.setCampo("codEquip");
        pesquisaBean.setValor(codEquip);
        pesquisaBean.setTipo(1);
        pesqArrayList.add(pesquisaBean);

        PesquisaBean pesquisaBean2 = new PesquisaBean();
        pesquisaBean2.setCampo("tipoEquip");
        pesquisaBean2.setValor(1L);
        pesquisaBean2.setTipo(1);
        pesqArrayList.add(pesquisaBean2);

        return equipBean.get(pesqArrayList);

    }

    public EquipBean getCaminhao(Long codEquip){
        List equipList = caminhaoList(codEquip);
        EquipBean equipBean = (EquipBean) equipList.get(0);
        equipList.clear();
        return equipBean;
    }

    public EquipBean getEquip(Long idEquip){
        EquipBean equipBean = new EquipBean();
        List equipList = equipBean.get("idEquip", idEquip);
        equipBean = (EquipBean) equipList.get(0);
        equipList.clear();
        return equipBean;
    }

}
