package br.com.usinasantafe.ecm.model.bean;

public class AtualAplicBean {

    private Long idEquipAtualizacao;
    private Long idCheckList;
    private String versaoAtual;
    private String versaoNova;
    private Long flagAtualApp;
    private Long flagAtualCheckList;
    private Long flagLogEnvio;
    private Long flagLogErro;
    private String dthr;

    public AtualAplicBean() {
    }

    public Long getIdEquipAtualizacao() {
        return idEquipAtualizacao;
    }

    public void setIdEquipAtualizacao(Long idEquipAtualizacao) {
        this.idEquipAtualizacao = idEquipAtualizacao;
    }

    public Long getIdCheckList() {
        return idCheckList;
    }

    public void setIdCheckList(Long idCheckList) {
        this.idCheckList = idCheckList;
    }

    public String getVersaoAtual() {
        return versaoAtual;
    }

    public void setVersaoAtual(String versaoAtual) {
        this.versaoAtual = versaoAtual;
    }

    public String getVersaoNova() {
        return versaoNova;
    }

    public void setVersaoNova(String versaoNova) {
        this.versaoNova = versaoNova;
    }

    public Long getFlagAtualApp() {
        return flagAtualApp;
    }

    public void setFlagAtualApp(Long flagAtualApp) {
        this.flagAtualApp = flagAtualApp;
    }

    public Long getFlagAtualCheckList() {
        return flagAtualCheckList;
    }

    public void setFlagAtualCheckList(Long flagAtualCheckList) {
        this.flagAtualCheckList = flagAtualCheckList;
    }

    public Long getFlagLogEnvio() {
        return flagLogEnvio;
    }

    public void setFlagLogEnvio(Long flagLogEnvio) {
        this.flagLogEnvio = flagLogEnvio;
    }

    public Long getFlagLogErro() {
        return flagLogErro;
    }

    public void setFlagLogErro(Long flagLogErro) {
        this.flagLogErro = flagLogErro;
    }

    public String getDthr() {
        return dthr;
    }

    public void setDthr(String dthr) {
        this.dthr = dthr;
    }
}
