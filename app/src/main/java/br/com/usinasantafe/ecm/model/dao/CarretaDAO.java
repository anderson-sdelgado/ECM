package br.com.usinasantafe.ecm.model.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.ecm.model.bean.estaticas.EquipBean;
import br.com.usinasantafe.ecm.model.bean.pst.PesqBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.CarretaUtilBean;

public class CarretaDAO {

    public CarretaDAO() {
    }

    public boolean verQtdeCarreta(Long tipo){
        CarretaUtilBean carretaUtilBean = new CarretaUtilBean();
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqTipoCarreta(tipo));
        List carretaList = carretaUtilBean.get(pesqArrayList);
        boolean ver = carretaList.size() > 0;
        carretaList.clear();
        return ver;
    }

    public boolean verCarretaAlocada(Long idCarreta, Long tipo){
        CarretaUtilBean carretaUtilBean = new CarretaUtilBean();
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqTipoCarreta(tipo));
        pesqArrayList.add(getPesqIdEquip(idCarreta));
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

    public Long posCarreta(Long tipo){
        CarretaUtilBean carretaUtilBean = new CarretaUtilBean();
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqTipoCarreta(tipo));
        List carretaList = carretaUtilBean.get(pesqArrayList);
        Long pos = Long.valueOf(carretaList.size());
        pesqArrayList.clear();
        carretaList.clear();
        return pos;
    }

    public String textoCarreta(){
        CarretaUtilBean carretaUtilBean = new CarretaUtilBean();
        List carretaList = carretaUtilBean.getAndOrderBy("tipoCarreta", 2L, "idCarretaUtil", true);
        String textoCarreta = "";
        if (carretaList.size() == 0) {
            textoCarreta = "CARRETA(S): ";
        } else if (carretaList.size() == 1) {
            carretaUtilBean = (CarretaUtilBean) carretaList.get(0);
            textoCarreta = "CARRETA(S): " + carretaUtilBean.getIdEquip();
        } else if (carretaList.size() == 2) {
            textoCarreta = "CARRETA(S): ";
            carretaUtilBean = (CarretaUtilBean) carretaList.get(0);
            textoCarreta = textoCarreta + carretaUtilBean.getIdEquip();
            carretaUtilBean = (CarretaUtilBean) carretaList.get(1);
            textoCarreta = textoCarreta + " - " + carretaUtilBean.getIdEquip();
        } else if (carretaList.size() == 3) {
            textoCarreta = "CARRETA(S): ";
            carretaUtilBean = (CarretaUtilBean) carretaList.get(0);
            textoCarreta = textoCarreta + carretaUtilBean.getIdEquip();
            carretaUtilBean = (CarretaUtilBean) carretaList.get(1);
            textoCarreta = textoCarreta + " - " + carretaUtilBean.getIdEquip();
            carretaUtilBean = (CarretaUtilBean) carretaList.get(2);
            textoCarreta = textoCarreta + " - " + carretaUtilBean.getIdEquip();
        } else {
            textoCarreta = "CARRETA(S): ";
            carretaUtilBean = (CarretaUtilBean) carretaList.get(0);
            textoCarreta = textoCarreta + carretaUtilBean.getIdEquip();
            carretaUtilBean = (CarretaUtilBean) carretaList.get(1);
            textoCarreta = textoCarreta + " - " + carretaUtilBean.getIdEquip();
            carretaUtilBean = (CarretaUtilBean) carretaList.get(2);
            textoCarreta = textoCarreta + " - " + carretaUtilBean.getIdEquip();
            carretaUtilBean = (CarretaUtilBean) carretaList.get(3);
            textoCarreta = textoCarreta + " - " + carretaUtilBean.getIdEquip();
        }
        carretaList.clear();
        return textoCarreta;
    }

    private PesqBean getPesqTipoCarreta(Long tipo){
        PesqBean pesqBean = new PesqBean();
        pesqBean.setCampo("tipoCarreta");
        pesqBean.setValor(tipo);
        pesqBean.setTipo(1);
        return pesqBean;
    }

    private PesqBean getPesqIdEquip(Long idEquip){
        PesqBean pesqBean = new PesqBean();
        pesqBean.setCampo("idEquip");
        pesqBean.setValor(idEquip);
        pesqBean.setTipo(1);
        return pesqBean;
    }

    private PesqBean getPesqNroEquip(Long nroEquip){
        PesqBean pesqBean = new PesqBean();
        pesqBean.setCampo("nroEquip");
        pesqBean.setValor(nroEquip);
        pesqBean.setTipo(1);
        return pesqBean;
    }

}
