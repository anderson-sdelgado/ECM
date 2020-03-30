package br.com.usinasantafe.ecm.model.dao;

import java.util.List;

import br.com.usinasantafe.ecm.model.bean.estaticas.EquipBean;
import br.com.usinasantafe.ecm.model.bean.estaticas.TurnoBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.BoletimMMBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.PreCECBean;
import br.com.usinasantafe.ecm.util.Tempo;

public class PreCECDAO {

    public PreCECDAO() {
    }

    ///////////////////////////////////CERTIFICADO ABERTO//////////////////////////////////////////

    public void abrirPreCEC(){
        PreCECBean preCECBean = new PreCECBean();
        preCECBean.setDataSaidaUsina(Tempo.getInstance().dataComHora().getDataHora());
        preCECBean.setDataChegCampo("");
        preCECBean.setDataSaidaCampo("");
        preCECBean.setAtivOS(0L);
        preCECBean.setCam(0L);
        preCECBean.setLibCam(0L);
        preCECBean.setCarr1(0L);
        preCECBean.setLibCarr1(0L);
        preCECBean.setCarr2(0L);
        preCECBean.setLibCarr2(0L);
        preCECBean.setCarr3(0L);
        preCECBean.setLibCarr3(0L);
        preCECBean.setCarr4(0L);
        preCECBean.setLibCarr4(0L);
        preCECBean.setStatus(1L);
        preCECBean.insert();
    }

    public void clearPreCECAberto(){
        PreCECBean preCECBean = getPreCECAberto();
        preCECBean.setAtivOS(0L);
        preCECBean.setCam(0L);
        preCECBean.setLibCam(0L);
        preCECBean.setCarr1(0L);
        preCECBean.setLibCarr1(0L);
        preCECBean.setCarr2(0L);
        preCECBean.setLibCarr2(0L);
        preCECBean.setCarr3(0L);
        preCECBean.setLibCarr3(0L);
        preCECBean.setCarr4(0L);
        preCECBean.setLibCarr4(0L);
        preCECBean.update();
    }

    public void fechaPreCEC(BoletimMMBean boletimMMBean){
        PreCECBean preCECBean = getPreCECAberto();
        preCECBean.setMoto(boletimMMBean.getMatricFuncBolMM());
        TurnoBean turnoBean = new TurnoBean();
        List turnoList = turnoBean.get("idTurno", boletimMMBean.getIdTurnoBolMM());
        turnoBean = (TurnoBean) turnoList.get(0);
        turnoList.clear();
        preCECBean.setTurno(turnoBean.getCodTurno());
        EquipBean equipBean = new EquipBean();
        List equipList = equipBean.get("idEquip", boletimMMBean.getIdEquipBolMM());
        equipBean = (EquipBean) equipList.get(0);
        equipList.clear();
        preCECBean.setCam(equipBean.getNroEquip());
        preCECBean.update();
    }

    /////////////////////////////VERIFICAR DADOS////////////////////////////////

    public boolean verPreCECListFechado(){
        List certifFechadoList = getPreCECListFechado();
        boolean retorno = certifFechadoList.size() > 0;
        certifFechadoList.clear();
        return retorno;
    }

    public boolean verPreCECAberto(){
        List certifAbertoList = preCECAbertoList();
        boolean retorno = certifAbertoList.size() > 0;
        certifAbertoList.clear();
        return retorno;
    }

    public boolean verDataCertif(){
        List certifCanaList = preCECAbertoList();
        PreCECBean preCECBean = (PreCECBean) certifCanaList.get(0);
        certifCanaList.clear();
        return ((!preCECBean.getDataSaidaUsina().equals("")) && (!preCECBean.getDataChegCampo().equals("")));
    }

//    public boolean verCarretaPreCEC(Long nroCarreta){
//        PreCECBean preCECBean = getPreCECAberto();
//        boolean ver = true;
//        if(preCECBean.getCarr1() == nroCarreta){
//            ver = false;
//        } else if(preCECBean.getCarr2() == nroCarreta){
//            ver = false;
//        } else if(preCECBean.getCarr3() == nroCarreta){
//            ver = false;
//        } else if(preCECBean.getCarr4() == nroCarreta){
//            ver = false;
//        }
//        return ver;
//    }

    ////////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////SET DADOS////////////////////////////////

    public void setDataChegCampo(){
        PreCECBean preCECBean = getPreCECAberto();
        preCECBean.setDataChegCampo(Tempo.getInstance().dataComHora().getDataHora());
        preCECBean.update();
    }

    public void setDataSaidaCampo(){
        PreCECBean preCECBean = getPreCECAberto();
        preCECBean.setDataSaidaCampo(Tempo.getInstance().dataComHora().getDataHora());
        preCECBean.update();
    }

    public void setAtivOS(Long ativOS){
        PreCECBean preCECBean = getPreCECAberto();
        preCECBean.setAtivOS(ativOS);
        preCECBean.update();
    }

    public void setLib(Long lib, int qtde){
        PreCECBean preCECBean = getPreCECAberto();
        if(qtde == 0){
            preCECBean.setLibCam(lib);
        } else if(qtde == 1){
            preCECBean.setLibCarr1(lib);
        } else if(qtde == 2){
            preCECBean.setLibCarr2(lib);
        } else if(qtde == 3){
            preCECBean.setLibCarr3(lib);
        } else if(qtde == 4){
            preCECBean.setLibCarr4(lib);
        }
        preCECBean.update();
    }

    public void setCarr(Long carr, int pos){
        PreCECBean preCECBean = getPreCECAberto();
        if(pos == 1){
            preCECBean.setCarr1(carr);
        } else if(pos == 2){
            preCECBean.setCarr2(carr);
        } else if(pos == 3){
            preCECBean.setCarr3(carr);
        } else if(pos == 4){
            preCECBean.setCarr4(carr);
        }
        preCECBean.update();
    }

    /////////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////GET DADOS/////////////////////////////////

    public Long getLib(int qtde){
        PreCECBean preCECBean = getPreCECAberto();
        Long lib = 0L;
        if(qtde == 0){
            lib = preCECBean.getLibCam();
        } else if(qtde == 1){
            lib = preCECBean.getLibCarr1();
        } else if(qtde == 2){
            lib = preCECBean.getLibCarr2();
        } else if(qtde == 3){
            lib = preCECBean.getLibCarr3();
        } else if(qtde == 4){
            lib = preCECBean.getLibCarr4();
        }
        return lib;
    }

    public PreCECBean getPreCECAberto(){
        List preCECList = preCECAbertoList();
        PreCECBean preCECBean = (PreCECBean) preCECList.get(0);
        preCECList.clear();
        return preCECBean;
    }

    public String getDataChegCampo(){
        PreCECBean preCECBean = getPreCECAberto();
        return preCECBean.getDataChegCampo();
    }

    public String getDataSaidaUlt(){
        PreCECBean preCECBean = new PreCECBean();
        int qtdePreCEC = preCECBean.count();
        String retorno;
        if (qtdePreCEC == 0) {
            retorno = "NÃO POSSUE CARREGAMENTOS";
        } else {
            List preCECList = preCECBean.all();
            preCECBean = (PreCECBean) preCECList.get(qtdePreCEC - 1);
            retorno = "ULT. VIAGEM: " + Tempo.getInstance().dataHoraCTZ(preCECBean.getDataSaidaCampo());
        }
        return retorno;
    }

    /////////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////LIST DE PRECEC/////////////////////////////////////////

    private List preCECAbertoList(){
        PreCECBean preCECBean = new PreCECBean();
        List preCECList = preCECBean.get("status", 1L);
        return preCECList;
    }

    public List getPreCECListFechado(){
        PreCECBean preCECBean = new PreCECBean();
        List preCECList = preCECBean.get("status", 2L);
        return preCECList;
    }

    public List getPreCECListEnviado(){
        PreCECBean preCECBean = new PreCECBean();
        List preCECList = preCECBean.get("status", 3L);
        return preCECList;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

}
