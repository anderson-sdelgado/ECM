package br.com.usinasantafe.ecm.model.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.ecm.model.bean.estaticas.RAtivOSBean;
import br.com.usinasantafe.ecm.model.bean.pst.PesqBean;

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

    private PesqBean getPesqRAtivOS(Long idRAtivOS){
        PesqBean pesqBean = new PesqBean();
        pesqBean.setCampo("idRAtivOS");
        pesqBean.setValor(idRAtivOS);
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
