package br.com.usinasantafe.ecm.control;

import android.app.ProgressDialog;
import android.content.Context;

import java.util.ArrayList;

import br.com.usinasantafe.ecm.model.bean.variaveis.ApontMMBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.BoletimMMBean;
import br.com.usinasantafe.ecm.model.dao.ApontMMDAO;
import br.com.usinasantafe.ecm.model.dao.BoletimMMDAO;
import br.com.usinasantafe.ecm.util.AtualDadosServ;

public class MotoMecCTR {

    private BoletimMMBean boletimMMBean;

    public MotoMecCTR() {
        if (boletimMMBean == null)
            boletimMMBean = new BoletimMMBean();
    }

    public boolean verBolAberto(){
        BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
        return boletimMMDAO.verBolAberto();
    }

    public void atualApont(){
        ApontMMDAO apontMMDAO = new ApontMMDAO();
        ApontMMBean apontMMBean = apontMMDAO.getApontMMAberto();
        apontMMDAO.updApont(apontMMBean);
    }

    public BoletimMMBean getBolAberto(){
        BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
        return boletimMMDAO.getBolAberto();
    }

    //////////////////////////// SETAR CAMPOS ///////////////////////////////////////////////

    public void setFuncBol(Long matric){
        boletimMMBean.setMatricFuncBolMM(matric);
    }

    public void setEquipBol(){
        ConfigCTR configCTR = new ConfigCTR();
        boletimMMBean.setIdEquipBolMM(configCTR.getEquip().getIdEquip());
    }

    public void setTurnoBol(Long idTurno){
        boletimMMBean.setIdTurnoBolMM(idTurno);
    }

    public void setOSBol(Long os){
            boletimMMBean.setOsBolMM(os);
    }

//    public void setAtivBol(Long ativ){
//        ConfigCTR configCTR = new ConfigCTR();
//        if(configCTR.getEquip().getTipo() == 1) {
//            boletimMMBean.setAtivPrincBolMM(ativ);
//            boletimMMBean.setStatusConBolMM(configCTR.getConfig().getStatusConConfig());
//        }
//        else{
//            boletimFertBean.setAtivPrincBolFert(ativ);
//            boletimFertBean.setStatusConBolFert(configCTR.getConfig().getStatusConConfig());
//        }
//    }
//
//
//    public void setHodometroInicialBol(Double horimetroNum, Double longitude, Double latitude){
//        ConfigCTR configCTR = new ConfigCTR();
//        if(configCTR.getEquip().getTipo() == 1) {
//            boletimMMBean.setHodometroInicialBolMM(horimetroNum);
//            boletimMMBean.setHodometroFinalBolMM(0D);
//            boletimMMBean.setIdExtBolMM(0L);
//            boletimMMBean.setLongitudeBolMM(longitude);
//            boletimMMBean.setLatitudeBolMM(latitude);
//        }
//        else{
//            boletimFertBean.setHodometroInicialBolFert(horimetroNum);
//            boletimFertBean.setHodometroFinalBolFert(0D);
//            boletimFertBean.setIdExtBolFert(0L);
//            boletimFertBean.setLongitudeBolFert(longitude);
//            boletimFertBean.setLongitudeBolFert(latitude);
//        }
//    }
//
//    public void setHodometroFinalBol(Double horimetroNum){
//        ConfigCTR configCTR = new ConfigCTR();
//        if(configCTR.getEquip().getTipo() == 1) {
//            boletimMMBean.setHodometroFinalBolMM(horimetroNum);
//        }
//        else{
//            boletimFertBean.setHodometroFinalBolFert(horimetroNum);
//        }
//    }
//
//    public void setIdEquipBombaBol(Long idEquip){
//        boletimFertBean.setIdEquipBombaBolFert(idEquip);
//    }

    /////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////// GET DE CAMPOS ///////////////////////////////////////////

//    public Long getAtiv(){
//        ConfigCTR configCTR = new ConfigCTR();
//        if(configCTR.getEquip().getTipo() == 1) {
//            return boletimMMBean.getAtivPrincBolMM();
//        }
//        else{
//            return boletimFertBean.getAtivPrincBolFert();
//        }
//    }
//
//    public Long getTurno(){
//        ConfigCTR configCTR = new ConfigCTR();
//        if(configCTR.getEquip().getTipo() == 1) {
//            return boletimMMBean.getIdTurnoBolMM();
//        }
//        else{
//            return boletimFertBean.getIdTurnoBolFert();
//        }
//    }
//
//    public Long getFunc(){
//        ConfigCTR configCTR = new ConfigCTR();
//        if(configCTR.getEquip().getTipo() == 1) {
//            return boletimMMBean.getMatricFuncBolMM();
//        }
//        else{
//            return boletimFertBean.getMatricFuncBolFert();
//        }
//    }
//
//    public Long getIdExtBol(){
//        ConfigCTR configCTR = new ConfigCTR();
//        if(configCTR.getEquip().getTipo() == 1) {
//            return boletimMMBean.getIdExtBolMM();
//        }
//        else{
//            return boletimFertBean.getIdExtBolFert();
//        }
//    }
//
//    public Long getStatusConBol(){
//        ConfigCTR configCTR = new ConfigCTR();
//        if(configCTR.getEquip().getTipo() == 1) {
//            return boletimMMBean.getStatusConBolMM();
//        }
//        else{
//            return boletimFertBean.getStatusConBolFert();
//        }
//    }
//
//    public Long getOS() {
//        ConfigCTR configCTR = new ConfigCTR();
//        if(configCTR.getEquip().getTipo() == 1) {
//            return boletimMMBean.getOsBolMM();
//        }
//        else{
//            return boletimFertBean.getOsBolFert();
//        }
//    }
//
//    public Long getIdBol(){
//        ConfigCTR configCTR = new ConfigCTR();
//        if(configCTR.getEquip().getTipo() == 1) {
//            BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
//            return boletimMMDAO.getIdBolAberto();
//        }
//        else{
//            BoletimFertDAO boletimFertDAO = new BoletimFertDAO();
//            return boletimFertDAO.getIdBolAberto();
//        }
//    }
//
//    public Double getLongitude(){
//        ConfigCTR configCTR = new ConfigCTR();
//        if(configCTR.getEquip().getTipo() == 1) {
//            return boletimMMBean.getLongitudeBolMM();
//        }
//        else{
//            return boletimFertBean.getLongitudeBolFert();
//        }
//    }
//
//    public Double getLatitude(){
//        ConfigCTR configCTR = new ConfigCTR();
//        if(configCTR.getEquip().getTipo() == 1) {
//            return boletimMMBean.getLatitudeBolMM();
//        }
//        else{
//            return boletimFertBean.getLatitudeBolFert();
//        }
//    }

    /////////////////////////////////////////////////////////////////////////////////////

    //////////////////////// MANIPULAR DADOS MOTOMEC BOLETIM //////////////////////////////////////

    ///////////// SALVAR DADOS ///////////////

    public void salvarBolAberto(){
        BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
        boletimMMDAO.salvarBolAberto(boletimMMBean);
    }

    public void salvarBolFechado(){
        BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
        boletimMMDAO.salvarBolFechado(boletimMMBean);
    }

    ////////// VERIFICAÇÃO PRA ENVIO ///////////////

    public boolean verEnvioBolAberto(){
        BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
        return boletimMMDAO.bolAbertoSemEnvioList().size() > 0;
    }

    public boolean verEnvioBolFech() {
        BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
        return boletimMMDAO.bolFechadoList().size() > 0;
    }

    ////////// DADOS PRA ENVIO ///////////////

    public String dadosEnvioBolAberto(){
        BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
        return boletimMMDAO.dadosEnvioBolAberto(getBolAberto());
    }

    public String dadosEnvioBolFechado(){
        BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
        return boletimMMDAO.dadosEnvioBolFechado();
    }

    ////////// MANIPULAÇÃO RETORNO DE ENVIO ///////////////

    public void updBolAberto(String retorno){
        BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
        boletimMMDAO.updateBolAberto(retorno);
    }

    public void delBolFechado(String retorno) {
        BoletimMMDAO boletimMMDAO = new BoletimMMDAO();
        boletimMMDAO.deleteBolFechado(retorno);
    }

    //////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////// MANIPULAR APONT DADOS MOTOMEC ////////////////////////////////////

    ////////// VERIFICAÇÃO PRA ENVIO ///////////////

    public Boolean verEnvioDadosApont(){
        Boolean retorno = false;
        ApontMMDAO apontMMDAO = new ApontMMDAO();
        return (apontMMDAO.getListApontEnvio().size() > 0);
    }

    ////////// DADOS PRA ENVIO ///////////////

    public String dadosEnvioApontBolMM(Long idBol){
        ApontMMDAO apontMMDAO = new ApontMMDAO();
        return apontMMDAO.dadosEnvioApontMM(apontMMDAO.getListApontEnvio(idBol));
    }

    public String dadosEnvioApontMM(){
        ApontMMDAO apontMMDAO = new ApontMMDAO();
        return apontMMDAO.dadosEnvioApontMM(apontMMDAO.getListApontEnvio());
    }

    ////////// MANIPULAÇÃO RETORNO DE ENVIO ///////////////

    public void updateApontMM(String retorno) {
        ApontMMDAO apontMMDAO = new ApontMMDAO();
        apontMMDAO.updateApont(retorno);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////// ATUALIZAÇÃO DE DADOS POR CLASSE /////////////////////////////////////

    public void atualDadosMotorista(Context telaAtual, Class telaProx, ProgressDialog progressDialog){
        ArrayList operadorArrayList = new ArrayList();
        operadorArrayList.add("MotoristaBean");
        AtualDadosServ.getInstance().atualGenericoBD(telaAtual, telaProx, progressDialog, operadorArrayList);
    }

    public void atualDadosTurno(Context telaAtual, Class telaProx, ProgressDialog progressDialog) {
        ArrayList turnoArrayList = new ArrayList();
        turnoArrayList.add("TurnoBean");
        AtualDadosServ.getInstance().atualGenericoBD(telaAtual, telaProx, progressDialog, turnoArrayList);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////

//    public List getMotoMecList() {
//        MotoMecDAO motoMecDAO = new MotoMecDAO();
//        return motoMecDAO.getMotoMecList();
//    }
//
//    public List getParadaList() {
//        MotoMecDAO motoMecDAO = new MotoMecDAO();
//        return motoMecDAO.getParadaList();
//    }
//
//    public void salvaMotoMec(Long opCorMotoMec){
//        MotoMecDAO motoMecDAO = new MotoMecDAO();
//        CertifCanaDAO certifCanaDAO = new CertifCanaDAO();
//        Long ativOS = 0L;
//        if(certifCanaDAO.verCertifAberto()){
//            ativOS = certifCanaDAO.getCertifAberto().getAtivOS();
//        }
//        ConfigDAO configDAO = new ConfigDAO();
//        motoMecDAO.salvaMotoMec(opCorMotoMec, ativOS, configDAO.getConfig());
//    }
//
//    public void salvaSaidaCampoMM(){
//        MotoMecDAO motoMecDAO = new MotoMecDAO();
//        CertifCanaDAO certifCanaDAO = new CertifCanaDAO();
//        Long ativOS = 0L;
//        if(certifCanaDAO.verCertifAberto()){
//            ativOS = certifCanaDAO.getCertifAberto().getAtivOS();
//        }
//        ConfigDAO configDAO = new ConfigDAO();
//        motoMecDAO.salvaSaidaCampo(ativOS, configDAO.getConfig());
//    }
//
//    public Long getOpCorSaidaUsina(){
//        MotoMecDAO motoMecDAO = new MotoMecDAO();
//        return motoMecDAO.getOpCorSaidaUsina();
//    }
//
//    public Long getDesengateCarreta(){
//        MotoMecDAO motoMecDAO = new MotoMecDAO();
//        return motoMecDAO.getDesengateCarreta();
//    }
//
//    public Long getEngateCarreta(){
//        MotoMecDAO motoMecDAO = new MotoMecDAO();
//        return motoMecDAO.getEngateCarreta();
//    }
//
//    public Long getCheckList(){
//        MotoMecDAO motoMecDAO = new MotoMecDAO();
//        return motoMecDAO.getCheckList();
//    }
//
//    public Long getVoltaTrabalho(){
//        MotoMecDAO motoMecDAO = new MotoMecDAO();
//        return motoMecDAO.getVoltaTrabalho();
//    }
//
//    public String textoCarreta(){
//        CarretaDAO carretaDAO = new CarretaDAO();
//        return carretaDAO.textoCarreta();
//    }

}
