package br.com.usinasantafe.ecm.model.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.ecm.model.bean.estaticas.EquipBean;
import br.com.usinasantafe.ecm.model.pst.EspecificaPesquisa;
import br.com.usinasantafe.ecm.model.bean.variaveis.CarretaUtilBean;

public class CarretaDAO {

    public CarretaDAO() {
    }

    public void insCarreta(Long idCertif, Long posicao, Long idEquip, Long tipo){
        CarretaUtilBean carretaUtilBean = new CarretaUtilBean();
        carretaUtilBean.setIdCertif(idCertif);
        carretaUtilBean.setPosCarreta(posicao);
        carretaUtilBean.setNroEquip(idEquip);
        carretaUtilBean.setTipoCarreta(tipo);
        carretaUtilBean.setLibCarreta(0L);
        carretaUtilBean.insert();
    }

    public void delCarreta(Long tipo){
        List carretaList = carretaList(tipo);
        for (int i = 0; i < carretaList.size(); i++) {
            CarretaUtilBean carretaUtilBean = (CarretaUtilBean) carretaList.get(i);
            carretaUtilBean.delete();
        }
        carretaList.clear();
    }

    public void delCarretaCertif(Long idCertif){
        List carretaList = carretaCertifList(idCertif);
        for (int i = 0; i < carretaList.size(); i++) {
            CarretaUtilBean carretaUtilBean = (CarretaUtilBean) carretaList.get(i);
            carretaUtilBean.delete();
        }
        carretaList.clear();
    }

    public void setLibCarreta(CarretaUtilBean carretaUtilBean, Long nroLib){
        carretaUtilBean.setLibCarreta(nroLib);
        carretaUtilBean.insert();
    }

    public boolean verQtdeCarreta(Long tipo){
        List carretaList = carretaList(tipo);
        boolean ver = carretaList.size() > 0;
        carretaList.clear();
        return ver;
    }

    public boolean verCarreta(Long nroCarreta, Long tipo){
        CarretaUtilBean carretaUtilBean = new CarretaUtilBean();
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqTipo(tipo));
        pesqArrayList.add(getPesqNroEquip(nroCarreta));
        List carretaList = carretaUtilBean.get(pesqArrayList);
        boolean ver = carretaList.size() > 0;
        carretaList.clear();
        return ver;
    }

    public boolean verCarretaBD(Long nroCarreta){
        List carretaList = carretaBDList(nroCarreta);
        boolean ver = carretaList.size() > 0;
        carretaList.clear();
        return ver;
    }

    public EquipBean getCarretaBD(Long nroCarreta){
        List carretaList = carretaBDList(nroCarreta);
        EquipBean equipBean = (EquipBean) carretaList.get(0);
        carretaList.clear();
        return equipBean;
    }

    private List carretaBDList(Long nroCarreta){
        CarretaUtilBean carretaUtilBean = new CarretaUtilBean();
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqNroEquip(nroCarreta));
        return carretaUtilBean.get(pesqArrayList);
    }

    public CarretaUtilBean getCarreta(Long idCertif, Long posicao){
        List carretaList = carretaCertifList(idCertif, posicao);
        CarretaUtilBean carretaUtilBean = (CarretaUtilBean) carretaList.get(0);
        carretaList.clear();
        return carretaUtilBean;
    }

    private List carretaCertifList(Long idCertif){
        CarretaUtilBean carretaUtilBean = new CarretaUtilBean();
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqCertif(idCertif));
        return carretaUtilBean.get(pesqArrayList);
    }

    private List carretaCertifList(Long idCertif, Long posicao){
        CarretaUtilBean carretaUtilBean = new CarretaUtilBean();
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqCertif(idCertif));
        pesqArrayList.add(getPesqPosicao(posicao));
        return carretaUtilBean.get(pesqArrayList);
    }

    public Long posCarreta(Long tipo){
        List carretaList = carretaList(tipo);
        Long pos = Long.valueOf(carretaList.size());
        carretaList.clear();
        return pos;
    }

    public List carretaList(Long tipo){
        CarretaUtilBean carretaUtilBean = new CarretaUtilBean();
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqTipo(tipo));
        return carretaUtilBean.get(pesqArrayList);
    }

    public String getDescrCarreta(){
        CarretaUtilBean carretaUtilBean = new CarretaUtilBean();
        List carretaList = carretaUtilBean.getAndOrderBy("tipoCarreta", 2L, "idCarretaUtil", true);
        String textoCarreta = "";
        if (carretaList.size() == 0) {
            textoCarreta = "CARRETA(S): ";
        } else if (carretaList.size() == 1) {
            carretaUtilBean = (CarretaUtilBean) carretaList.get(0);
            textoCarreta = "CARRETA(S): " + carretaUtilBean.getNroEquip();
        } else if (carretaList.size() == 2) {
            textoCarreta = "CARRETA(S): ";
            carretaUtilBean = (CarretaUtilBean) carretaList.get(0);
            textoCarreta = textoCarreta + carretaUtilBean.getNroEquip();
            carretaUtilBean = (CarretaUtilBean) carretaList.get(1);
            textoCarreta = textoCarreta + " - " + carretaUtilBean.getNroEquip();
        } else if (carretaList.size() == 3) {
            textoCarreta = "CARRETA(S): ";
            carretaUtilBean = (CarretaUtilBean) carretaList.get(0);
            textoCarreta = textoCarreta + carretaUtilBean.getNroEquip();
            carretaUtilBean = (CarretaUtilBean) carretaList.get(1);
            textoCarreta = textoCarreta + " - " + carretaUtilBean.getNroEquip();
            carretaUtilBean = (CarretaUtilBean) carretaList.get(2);
            textoCarreta = textoCarreta + " - " + carretaUtilBean.getNroEquip();
        } else {
            textoCarreta = "CARRETA(S): ";
            carretaUtilBean = (CarretaUtilBean) carretaList.get(0);
            textoCarreta = textoCarreta + carretaUtilBean.getNroEquip();
            carretaUtilBean = (CarretaUtilBean) carretaList.get(1);
            textoCarreta = textoCarreta + " - " + carretaUtilBean.getNroEquip();
            carretaUtilBean = (CarretaUtilBean) carretaList.get(2);
            textoCarreta = textoCarreta + " - " + carretaUtilBean.getNroEquip();
            carretaUtilBean = (CarretaUtilBean) carretaList.get(3);
            textoCarreta = textoCarreta + " - " + carretaUtilBean.getNroEquip();
        }
        carretaList.clear();
        return textoCarreta;
    }

    private EspecificaPesquisa getPesqCertif(Long idCertif){
        EspecificaPesquisa especificaPesquisa = new EspecificaPesquisa();
        especificaPesquisa.setCampo("idCertif");
        especificaPesquisa.setValor(idCertif);
        especificaPesquisa.setTipo(1);
        return especificaPesquisa;
    }

    private EspecificaPesquisa getPesqTipo(Long tipo){
        EspecificaPesquisa especificaPesquisa = new EspecificaPesquisa();
        especificaPesquisa.setCampo("tipoCarreta");
        especificaPesquisa.setValor(tipo);
        especificaPesquisa.setTipo(1);
        return especificaPesquisa;
    }

    private EspecificaPesquisa getPesqNroEquip(Long nroEquip){
        EspecificaPesquisa especificaPesquisa = new EspecificaPesquisa();
        especificaPesquisa.setCampo("nroEquip");
        especificaPesquisa.setValor(nroEquip);
        especificaPesquisa.setTipo(1);
        return especificaPesquisa;
    }

    private EspecificaPesquisa getPesqPosicao(Long posicao){
        EspecificaPesquisa especificaPesquisa = new EspecificaPesquisa();
        especificaPesquisa.setCampo("posCarreta");
        especificaPesquisa.setValor(posicao);
        especificaPesquisa.setTipo(1);
        return especificaPesquisa;
    }

}
