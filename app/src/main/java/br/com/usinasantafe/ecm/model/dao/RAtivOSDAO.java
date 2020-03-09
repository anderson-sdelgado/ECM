package br.com.usinasantafe.ecm.model.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.ecm.model.bean.estaticas.RAtivOSBean;
import br.com.usinasantafe.ecm.model.pst.EspecificaPesquisa;

public class RAtivOSDAO {

    public RAtivOSDAO() {
    }

    public RAtivOSBean getAtivOS(Long idRAtivOS){
        List rAtivOSList = ativOSList(idRAtivOS);
        RAtivOSBean rAtivOSBean = (RAtivOSBean) rAtivOSList.get(0);
        rAtivOSList.clear();
        return rAtivOSBean;
    }

    private List ativOSList(Long idRAtivOS){
        RAtivOSBean rAtivOSBean = new RAtivOSBean();
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqRAtivOS(idRAtivOS));
        return rAtivOSBean.get(pesqArrayList);
    }

    public boolean verAtivOS(Long idRAtivOS){
        List rAtivOSList = ativOSList(idRAtivOS);
        boolean retorno = rAtivOSList.size() > 0;
        rAtivOSList.clear();
        return retorno;
    }

    private List osList(Long idRAtivOS, Long nroOS){
        RAtivOSBean rAtivOSBean = new RAtivOSBean();
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqRAtivOS(idRAtivOS));
        pesqArrayList.add(getPesqNroOS(nroOS));
        return rAtivOSBean.get(pesqArrayList);
    }

    public boolean verNroOS(Long idRAtivOS, Long nroOS){
        List rAtivOSList = osList(idRAtivOS, nroOS);
        boolean retorno = rAtivOSList.size() > 0;
        rAtivOSList.clear();
        return retorno;
    }

    private EspecificaPesquisa getPesqRAtivOS(Long idRAtivOS){
        EspecificaPesquisa especificaPesquisa = new EspecificaPesquisa();
        especificaPesquisa.setCampo("idRAtivOS");
        especificaPesquisa.setValor(idRAtivOS);
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
