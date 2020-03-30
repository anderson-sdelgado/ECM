package br.com.usinasantafe.ecm.model.dao;

import java.util.List;

import br.com.usinasantafe.ecm.model.bean.variaveis.CECBean;

public class CECDAO {

    public CECDAO() {
    }

    public boolean verCEC(){
        List cecList = getCECList();
        boolean retorno = cecList.size() > 0;
        cecList.clear();
        return retorno;
    }

    public List getCECList(){
        CECBean cecBean = new CECBean();
        List equipList = cecBean.get("status", 2L);
        return equipList;
    }

}
