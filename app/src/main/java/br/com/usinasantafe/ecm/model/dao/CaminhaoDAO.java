package br.com.usinasantafe.ecm.model.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.ecm.model.bean.estaticas.EquipBean;
import br.com.usinasantafe.ecm.model.bean.pst.PesqBean;

public class CaminhaoDAO {

    public CaminhaoDAO() {
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

        PesqBean pesqBean = new PesqBean();
        pesqBean.setCampo("codEquip");
        pesqBean.setValor(codEquip);
        pesqBean.setTipo(1);
        pesqArrayList.add(pesqBean);

        PesqBean pesqBean2 = new PesqBean();
        pesqBean2.setCampo("tipoEquip");
        pesqBean2.setValor(1L);
        pesqBean2.setTipo(1);
        pesqArrayList.add(pesqBean2);

        return equipBean.get(pesqArrayList);

    }

    public EquipBean getCaminhao(Long codEquip){
        List equipList = caminhaoList(codEquip);
        EquipBean equipBean = (EquipBean) equipList.get(0);
        equipList.clear();
        return equipBean;
    }

}
