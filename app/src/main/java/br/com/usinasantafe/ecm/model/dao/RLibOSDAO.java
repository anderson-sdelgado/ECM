package br.com.usinasantafe.ecm.model.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.ecm.model.bean.estaticas.RLibOSBean;
import br.com.usinasantafe.ecm.model.pst.EspecificaPesquisa;

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

    private EspecificaPesquisa getPesqBeanLib(Long codLib){
        EspecificaPesquisa especificaPesquisa = new EspecificaPesquisa();
        especificaPesquisa.setCampo("codLib");
        especificaPesquisa.setValor(codLib);
        especificaPesquisa.setTipo(1);
        return especificaPesquisa;
    }

    private EspecificaPesquisa getPesqNroOS(Long nroOS){
        EspecificaPesquisa especificaPesquisa = new EspecificaPesquisa();
        especificaPesquisa.setCampo("nroOS");
        especificaPesquisa.setValor(nroOS);
        especificaPesquisa.setTipo(1);
        return especificaPesquisa;
    }

}
