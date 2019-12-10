package br.com.usinasantafe.ecm.model.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.ecm.model.bean.estaticas.RLibOSBean;
import br.com.usinasantafe.ecm.model.bean.pst.PesqBean;

public class RLibOSDAO {

    public RLibOSDAO() {
    }

    public boolean verLibOS(Long codLib, Long nroOS){
        List rAtivOSList = libOSList(codLib, nroOS);
        boolean retorno = rAtivOSList.size() > 0;
        rAtivOSList.clear();
        return retorno;
    }

    public RLibOSBean getRLibOSBean(Long codLib, Long nroOS){
        List rAtivOSList = libOSList(codLib, nroOS);
        RLibOSBean rLibOSBean = (RLibOSBean) rAtivOSList.get(0);
        rAtivOSList.clear();
        return rLibOSBean;
    }

    private List libOSList(Long codLib, Long nroOS){
        RLibOSBean rLibOSBean = new RLibOSBean();
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqBeanLib(codLib));
        pesqArrayList.add(getPesqNroOS(nroOS));
        return rLibOSBean.get(pesqArrayList);
    }

    private PesqBean getPesqBeanLib(Long codLib){
        PesqBean pesqBean = new PesqBean();
        pesqBean.setCampo("codLib");
        pesqBean.setValor(codLib);
        pesqBean.setTipo(1);
        return pesqBean;
    }

    private PesqBean getPesqNroOS(Long nroOS){
        PesqBean pesqBean = new PesqBean();
        pesqBean.setCampo("nroOS");
        pesqBean.setValor(nroOS);
        pesqBean.setTipo(1);
        return pesqBean;
    }

}
