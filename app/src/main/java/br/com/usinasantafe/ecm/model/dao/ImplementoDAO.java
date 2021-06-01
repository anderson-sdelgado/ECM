package br.com.usinasantafe.ecm.model.dao;

import java.util.List;

import br.com.usinasantafe.ecm.model.bean.variaveis.ApontImpleMMBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.ApontMMBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.CarretaBean;

public class ImplementoDAO {

    public ImplementoDAO() {
    }

    public void insImplemento(ApontMMBean apontMMBean){

        CarretaBean carretaBean = new CarretaBean();
        List<CarretaBean> carretaList = carretaBean.orderBy("posCarreta", true);

        for (CarretaBean carretaBeanBD : carretaList) {
            ApontImpleMMBean apontImpleMMBean = new ApontImpleMMBean();
            apontImpleMMBean.setIdApontMM(apontMMBean.getIdApontMM());
            apontImpleMMBean.setCodEquipImpleMM(carretaBeanBD.getNroEquip());
            apontImpleMMBean.setPosImpleMM(carretaBeanBD.getPosCarreta());
            apontImpleMMBean.setDthrImpleMM(apontMMBean.getDthrApontMM());
            apontImpleMMBean.insert();
        }
    }

}
