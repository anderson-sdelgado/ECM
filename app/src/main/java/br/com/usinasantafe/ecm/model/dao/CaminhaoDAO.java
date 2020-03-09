package br.com.usinasantafe.ecm.model.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.ecm.model.bean.estaticas.EquipBean;
import br.com.usinasantafe.ecm.model.pst.EspecificaPesquisa;

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

        EspecificaPesquisa especificaPesquisa = new EspecificaPesquisa();
        especificaPesquisa.setCampo("codEquip");
        especificaPesquisa.setValor(codEquip);
        especificaPesquisa.setTipo(1);
        pesqArrayList.add(especificaPesquisa);

        EspecificaPesquisa especificaPesquisa2 = new EspecificaPesquisa();
        especificaPesquisa2.setCampo("tipoEquip");
        especificaPesquisa2.setValor(1L);
        especificaPesquisa2.setTipo(1);
        pesqArrayList.add(especificaPesquisa2);

        return equipBean.get(pesqArrayList);

    }

    public EquipBean getCaminhao(Long codEquip){
        List equipList = caminhaoList(codEquip);
        EquipBean equipBean = (EquipBean) equipList.get(0);
        equipList.clear();
        return equipBean;
    }

}
