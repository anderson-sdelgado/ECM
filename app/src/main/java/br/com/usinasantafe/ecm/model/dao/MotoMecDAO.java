package br.com.usinasantafe.ecm.model.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.ecm.model.bean.estaticas.MotoMecBean;
import br.com.usinasantafe.ecm.model.bean.pst.PesqBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.ApontMotoMecBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.ConfigBean;
import br.com.usinasantafe.ecm.util.Tempo;

public class MotoMecDAO {

    public MotoMecDAO() {
    }

    public Long getOpCorSaidaUsina(){

        MotoMecBean motoMecBean = new MotoMecBean();
        ArrayList pesqArrayList = new ArrayList();

        pesqArrayList.add(getPesqBeanAplic());
        pesqArrayList.add(getPesqBeanMotoMec());

        PesqBean pesqBean3 = new PesqBean();
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

        pesqArrayList.add(getPesqBeanAplic());
        pesqArrayList.add(getPesqBeanMotoMec());

        PesqBean pesqBean3 = new PesqBean();
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

        pesqArrayList.add(getPesqBeanAplic());
        pesqArrayList.add(getPesqBeanMotoMec());

        PesqBean pesqBean3 = new PesqBean();
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

        pesqArrayList.add(getPesqBeanAplic());
        pesqArrayList.add(getPesqBeanParada());

        PesqBean pesqBean3 = new PesqBean();
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

        pesqArrayList.add(getPesqBeanAplic());
        pesqArrayList.add(getPesqBeanInvisivel());

        PesqBean pesqBean3 = new PesqBean();
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
        pesqArrayList.add(getPesqBeanAplic());
        pesqArrayList.add(getPesqBeanMotoMec());

        MotoMecBean motoMecBean = new MotoMecBean();
        return motoMecBean.getAndOrderBy(pesqArrayList, "posicaoMotoMec", true);

    }

    public List getParadaList(){

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqBeanAplic());
        pesqArrayList.add(getPesqBeanParada());
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

    private PesqBean getPesqBeanAplic(){
        PesqBean pesqBean = new PesqBean();
        pesqBean.setCampo("aplicOperMotoMec");
        pesqBean.setValor(1L);
        pesqBean.setTipo(1);
        return pesqBean;
    }

    private PesqBean getPesqBeanMotoMec(){
        PesqBean pesqBean = new PesqBean();
        pesqBean.setCampo("tipoOperMotoMec");
        pesqBean.setValor(1L);
        pesqBean.setTipo(1);
        return pesqBean;
    }

    private PesqBean getPesqBeanParada(){
        PesqBean pesqBean = new PesqBean();
        pesqBean.setCampo("tipoOperMotoMec");
        pesqBean.setValor(2L);
        pesqBean.setTipo(1);
        return pesqBean;
    }

    private PesqBean getPesqBeanInvisivel(){
        PesqBean pesqBean = new PesqBean();
        pesqBean.setCampo("tipoOperMotoMec");
        pesqBean.setValor(0L);
        pesqBean.setTipo(1);
        return pesqBean;
    }

}
