package br.com.usinasantafe.ecm.model.dao;

import java.util.List;

import br.com.usinasantafe.ecm.model.bean.estaticas.FuncBean;
import br.com.usinasantafe.ecm.model.bean.estaticas.PneuBean;

public class PneuDAO {

    public PneuDAO() {
    }

    public boolean verPneu(String codPneu){
        List<FuncBean> pneuList = pneuList(codPneu);
        boolean ret = pneuList.size() > 0;
        pneuList.clear();
        return ret;
    }

    private List<FuncBean> pneuList(String codPneu){
        PneuBean pneuBean = new PneuBean();
        return pneuBean.get("codPneu", codPneu);
    }

}
