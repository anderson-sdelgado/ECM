package br.com.usinasantafe.ecm.model.dao;

import java.util.List;

import br.com.usinasantafe.ecm.model.bean.estaticas.ColabBean;
import br.com.usinasantafe.ecm.model.bean.estaticas.EquipBean;

public class ColabDAO {

    public ColabDAO() {
    }

    public ColabBean getColab(Long matricColab){
        ColabBean colabBean = new ColabBean();
        List colabList = colabBean.get("matricColab", matricColab);
        colabBean = (ColabBean) colabList.get(0);
        colabList.clear();
        return colabBean;
    }

}
