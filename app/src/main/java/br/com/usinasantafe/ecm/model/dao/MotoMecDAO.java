package br.com.usinasantafe.ecm.model.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.ecm.model.bean.estaticas.MotoMecBean;
import br.com.usinasantafe.ecm.model.pst.EspecificaPesquisa;
import br.com.usinasantafe.ecm.model.bean.variaveis.ApontMotoMecBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.ConfigBean;
import br.com.usinasantafe.ecm.util.Tempo;

public class MotoMecDAO {

    public MotoMecDAO() {
    }

    public List getMotoMecList(){

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqBeanAplic());
        pesqArrayList.add(getPesqBeanMotoMec());

        MotoMecBean motoMecBean = new MotoMecBean();
        return motoMecBean.getAndOrderBy(pesqArrayList, "posicaoMotoMec", true);

    }

    public Long getOpCorSaidaUsina(){

        MotoMecBean motoMecBean = new MotoMecBean();
        ArrayList pesqArrayList = new ArrayList();

        pesqArrayList.add(getPesqBeanAplic());
        pesqArrayList.add(getPesqBeanMotoMec());

        EspecificaPesquisa especificaPesquisa3 = new EspecificaPesquisa();
        especificaPesquisa3.setCampo("codFuncaoOperMotoMec");
        especificaPesquisa3.setValor(2L);
        especificaPesquisa3.setTipo(1);
        pesqArrayList.add(especificaPesquisa3);

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

        EspecificaPesquisa especificaPesquisa3 = new EspecificaPesquisa();
        especificaPesquisa3.setCampo("codFuncaoOperMotoMec");
        especificaPesquisa3.setValor(11L);
        especificaPesquisa3.setTipo(1);
        pesqArrayList.add(especificaPesquisa3);

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

        EspecificaPesquisa especificaPesquisa3 = new EspecificaPesquisa();
        especificaPesquisa3.setCampo("codFuncaoOperMotoMec");
        especificaPesquisa3.setValor(12L);
        especificaPesquisa3.setTipo(1);
        pesqArrayList.add(especificaPesquisa3);

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

        EspecificaPesquisa especificaPesquisa3 = new EspecificaPesquisa();
        especificaPesquisa3.setCampo("codFuncaoOperMotoMec");
        especificaPesquisa3.setValor(18L);
        especificaPesquisa3.setTipo(1);
        pesqArrayList.add(especificaPesquisa3);

        List motoMecList = motoMecBean.get(pesqArrayList);
        motoMecBean = (MotoMecBean) motoMecList.get(0);
        motoMecList.clear();

        return motoMecBean.getCodOperMotoMec();

    }

    public Long getSaidaCampo(){

        MotoMecBean motoMecBean = new MotoMecBean();
        ArrayList pesqArrayList = new ArrayList();

        pesqArrayList.add(getPesqBeanAplic());
        pesqArrayList.add(getPesqBeanInvisivel());

        EspecificaPesquisa especificaPesquisa3 = new EspecificaPesquisa();
        especificaPesquisa3.setCampo("codFuncaoOperMotoMec");
        especificaPesquisa3.setValor(13L);
        especificaPesquisa3.setTipo(1);
        pesqArrayList.add(especificaPesquisa3);

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

        EspecificaPesquisa especificaPesquisa3 = new EspecificaPesquisa();
        especificaPesquisa3.setCampo("codFuncaoOperMotoMec");
        especificaPesquisa3.setValor(14L);
        especificaPesquisa3.setTipo(1);
        pesqArrayList.add(especificaPesquisa3);

        List motoMecList = motoMecBean.get(pesqArrayList);
        motoMecBean = (MotoMecBean) motoMecList.get(0);
        motoMecList.clear();

        return motoMecBean.getCodOperMotoMec();

    }



    public List getParadaList(){

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqBeanAplic());
        pesqArrayList.add(getPesqBeanParada());
        MotoMecBean motoMecBean = new MotoMecBean();
        return motoMecBean.getAndOrderBy(pesqArrayList, "posicaoMotoMec", true);

    }

    public void salvaSaidaCampo(Long ativOS, ConfigBean configBean) {

        ApontMotoMecBean apontMotoMecBean = new ApontMotoMecBean();
        apontMotoMecBean.setOpCor(getSaidaCampo());
        apontMotoMecBean.setCodEquip(configBean.getCodEquipConfig());
        apontMotoMecBean.setMatricColab(configBean.getMatricColabConfig());
        apontMotoMecBean.setDthr(Tempo.getInstance().dataComHora());
        apontMotoMecBean.setCaux(ativOS);
        apontMotoMecBean.insert();

//        envioApontMotoMec();

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

    private EspecificaPesquisa getPesqBeanAplic(){
        EspecificaPesquisa especificaPesquisa = new EspecificaPesquisa();
        especificaPesquisa.setCampo("aplicOperMotoMec");
        especificaPesquisa.setValor(1L);
        especificaPesquisa.setTipo(1);
        return especificaPesquisa;
    }

    private EspecificaPesquisa getPesqBeanMotoMec(){
        EspecificaPesquisa especificaPesquisa = new EspecificaPesquisa();
        especificaPesquisa.setCampo("tipoOperMotoMec");
        especificaPesquisa.setValor(1L);
        especificaPesquisa.setTipo(1);
        return especificaPesquisa;
    }

    private EspecificaPesquisa getPesqBeanParada(){
        EspecificaPesquisa especificaPesquisa = new EspecificaPesquisa();
        especificaPesquisa.setCampo("tipoOperMotoMec");
        especificaPesquisa.setValor(2L);
        especificaPesquisa.setTipo(1);
        return especificaPesquisa;
    }

    private EspecificaPesquisa getPesqBeanInvisivel(){
        EspecificaPesquisa especificaPesquisa = new EspecificaPesquisa();
        especificaPesquisa.setCampo("tipoOperMotoMec");
        especificaPesquisa.setValor(0L);
        especificaPesquisa.setTipo(1);
        return especificaPesquisa;
    }

}
