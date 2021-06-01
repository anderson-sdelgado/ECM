package br.com.usinasantafe.ecm.model.dao;

import java.util.List;

import br.com.usinasantafe.ecm.model.bean.estaticas.FuncBean;

public class FuncDAO {

    public FuncDAO() {
    }

    public boolean hasElements(){
        FuncBean funcBean = new FuncBean();
        return funcBean.hasElements();
    }

    public boolean verFunc(Long matricFunc){
        List<FuncBean> funcList = funcList(matricFunc);
        boolean ret = funcList.size() > 0;
        funcList.clear();
        return ret;
    }

    public FuncBean getFunc(Long matricColab){
        List<FuncBean> funcList = funcList(matricColab);
        FuncBean funcBean = (FuncBean) funcList.get(0);
        funcList.clear();
        return funcBean;
    }

    private List<FuncBean> funcList(Long matricFunc){
        FuncBean funcBean = new FuncBean();
        return funcBean.get("matricFunc", matricFunc);
    }

}
