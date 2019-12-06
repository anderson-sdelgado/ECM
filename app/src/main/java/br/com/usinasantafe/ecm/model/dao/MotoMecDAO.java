package br.com.usinasantafe.ecm.model.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.ecm.model.bean.estaticas.MotoMecBean;
import br.com.usinasantafe.ecm.model.bean.pst.PesquisaBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.ApontMotoMecBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.ConfigBean;
import br.com.usinasantafe.ecm.util.Tempo;

public class MotoMecDAO {

    public MotoMecDAO() {
    }

    public Long getOpCorSaidaUsina(){

        MotoMecBean motoMecBean = new MotoMecBean();
        ArrayList pesqArrayList = new ArrayList();

        pesqArrayList.add(getPesquisaBeanAplic());
        pesqArrayList.add(getPesquisaBeanMotoMec());

        PesquisaBean pesqBean3 = new PesquisaBean();
        pesqBean3.setCampo("codFuncaoOperMotoMec");
        pesqBean3.setValor(2L);
        pesqBean3.setTipo(1);
        pesqArrayList.add(pesqBean3);

        List motoMecList = motoMecBean.get(pesqArrayList);
        motoMecBean = (MotoMecBean) motoMecList.get(0);
        motoMecList.clear();

        return motoMecBean.getCodOperMotoMec();

    }

    public Long getDesengateCarreta(){

        MotoMecBean motoMecBean = new MotoMecBean();
        ArrayList pesqArrayList = new ArrayList();

        pesqArrayList.add(getPesquisaBeanAplic());
        pesqArrayList.add(getPesquisaBeanMotoMec());

        PesquisaBean pesqBean3 = new PesquisaBean();
        pesqBean3.setCampo("codFuncaoOperMotoMec");
        pesqBean3.setValor(11L);
        pesqBean3.setTipo(1);
        pesqArrayList.add(pesqBean3);

        List motoMecList = motoMecBean.get(pesqArrayList);
        motoMecBean = (MotoMecBean) motoMecList.get(0);
        motoMecList.clear();

        return motoMecBean.getCodOperMotoMec();

    }

    public Long getEngateCarreta(){

        MotoMecBean motoMecBean = new MotoMecBean();
        ArrayList pesqArrayList = new ArrayList();

        pesqArrayList.add(getPesquisaBeanAplic());
        pesqArrayList.add(getPesquisaBeanMotoMec());

        PesquisaBean pesqBean3 = new PesquisaBean();
        pesqBean3.setCampo("codFuncaoOperMotoMec");
        pesqBean3.setValor(12L);
        pesqBean3.setTipo(1);
        pesqArrayList.add(pesqBean3);

        List motoMecList = motoMecBean.get(pesqArrayList);
        motoMecBean = (MotoMecBean) motoMecList.get(0);
        motoMecList.clear();

        return motoMecBean.getCodOperMotoMec();

    }

    public Long getCheckList(){

        MotoMecBean motoMecBean = new MotoMecBean();
        ArrayList pesqArrayList = new ArrayList();

        pesqArrayList.add(getPesquisaBeanAplic());
        pesqArrayList.add(getPesquisaBeanParada());

        PesquisaBean pesqBean3 = new PesquisaBean();
        pesqBean3.setCampo("codFuncaoOperMotoMec");
        pesqBean3.setValor(18L);
        pesqBean3.setTipo(1);
        pesqArrayList.add(pesqBean3);

        List motoMecList = motoMecBean.get(pesqArrayList);
        motoMecBean = (MotoMecBean) motoMecList.get(0);
        motoMecList.clear();

        return motoMecBean.getCodOperMotoMec();

    }

    public Long getVoltaTrabalho(){

        MotoMecBean motoMecBean = new MotoMecBean();
        ArrayList pesqArrayList = new ArrayList();

        pesqArrayList.add(getPesquisaBeanAplic());
        pesqArrayList.add(getPesquisaBeanInvisivel());

        PesquisaBean pesqBean3 = new PesquisaBean();
        pesqBean3.setCampo("codFuncaoOperMotoMec");
        pesqBean3.setValor(14L);
        pesqBean3.setTipo(1);
        pesqArrayList.add(pesqBean3);

        List motoMecList = motoMecBean.get(pesqArrayList);
        motoMecBean = (MotoMecBean) motoMecList.get(0);
        motoMecList.clear();

        return motoMecBean.getCodOperMotoMec();

    }

    public List getMotoMecList(){

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesquisaBeanAplic());
        pesqArrayList.add(getPesquisaBeanMotoMec());

        MotoMecBean motoMecBean = new MotoMecBean();
        return motoMecBean.getAndOrderBy(pesqArrayList, "posicaoMotoMec", true);

    }

    public List getParadaList(){

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesquisaBeanAplic());
        pesqArrayList.add(getPesquisaBeanParada());

        MotoMecBean motoMecBean = new MotoMecBean();
        return motoMecBean.getAndOrderBy(pesqArrayList, "posicaoMotoMec", true);

    }

    public void salvaMotoMec(Long opCorMotoMec, Long ativOS, ConfigBean configBean) {

        ApontMotoMecBean apontMotoMecBean = new ApontMotoMecBean();
        apontMotoMecBean.setOpCor(opCorMotoMec);
        apontMotoMecBean.setCodEquip(configBean.getCodEquipConfig());
        apontMotoMecBean.setMatricColab(configBean.getMatricColabConfig());
        apontMotoMecBean.setDthr(Tempo.getInstance().dataComHora());
        apontMotoMecBean.setCaux(ativOS);
        apontMotoMecBean.insert();

//        envioApontMotoMec();

    }

    private PesquisaBean getPesquisaBeanAplic(){
        PesquisaBean pesquisaBean = new PesquisaBean();
        pesquisaBean.setCampo("aplicOperMotoMec");
        pesquisaBean.setValor(1L);
        pesquisaBean.setTipo(1);
        return pesquisaBean;
    }

    private PesquisaBean getPesquisaBeanMotoMec(){
        PesquisaBean pesquisaBean = new PesquisaBean();
        pesquisaBean.setCampo("tipoOperMotoMec");
        pesquisaBean.setValor(1L);
        pesquisaBean.setTipo(1);
        return pesquisaBean;
    }

    private PesquisaBean getPesquisaBeanParada(){
        PesquisaBean pesquisaBean = new PesquisaBean();
        pesquisaBean.setCampo("tipoOperMotoMec");
        pesquisaBean.setValor(2L);
        pesquisaBean.setTipo(1);
        return pesquisaBean;
    }

    private PesquisaBean getPesquisaBeanInvisivel(){
        PesquisaBean pesquisaBean = new PesquisaBean();
        pesquisaBean.setCampo("tipoOperMotoMec");
        pesquisaBean.setValor(0L);
        pesquisaBean.setTipo(1);
        return pesquisaBean;
    }

}
