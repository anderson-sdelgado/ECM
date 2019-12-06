package br.com.usinasantafe.ecm;

import br.com.usinasantafe.ecm.control.CertifCanaCTR;
import br.com.usinasantafe.ecm.control.CheckListCTR;
import br.com.usinasantafe.ecm.control.ConfigCTR;
import br.com.usinasantafe.ecm.control.MotoMecCTR;

import android.app.Application;

public class ECMContext extends Application {

	private CertifCanaCTR certifCanaCTR;
	private ConfigCTR configCTR;
	private MotoMecCTR motoMecCTR;
	private CheckListCTR checkListCTR;

	private int verPosTela;
	// 1 - Inicio do Aplicativo;
	// 2 - Troca Motorista no Menu Moto Mec;
	// 3 - Desengate no Menu Moto Mec;
	// 4 - Engate no Menu Moto Mec;
	// 5 - Certificado;
	// 6 - Desengate no Parada;
	// 7 - Engate no Parada;

	private int numCarreta;
	//	private int telaAltMoto; //1 - Menu Inicial, 2 - Ver Motorista Final, 3 - Menu Moto Mec
	private int posMenu;

	private Long codigoAtivOS;
	private Long nroOS;
	private Long liberacaoOS;

	private Long cargoMotomec;
	private Long lugarMotivoParada;

	private boolean verTimer;

	public static String versaoAplic = "2.02";
	private String verAtualCL;

	private int posChecklist;

	@Override
	public void onCreate() {
		super.onCreate();
	}

	public ConfigCTR getConfigCTR(){
		if (configCTR == null)
			configCTR = new ConfigCTR();
		return configCTR;
	}

	public MotoMecCTR getMotoMecCTR(){
		if (motoMecCTR == null)
			motoMecCTR = new MotoMecCTR();
		return motoMecCTR;
	}

    public CertifCanaCTR getCertifCanaCTR() {
        if (certifCanaCTR == null)
			certifCanaCTR = new CertifCanaCTR();
        return certifCanaCTR;
    }

    public CheckListCTR getCheckListCTR() {
        if (checkListCTR == null)
			checkListCTR = new CheckListCTR();
        return checkListCTR;
    }
    
	public int getNumCarreta() {
		return numCarreta;
	}

	public void setNumCarreta(int numCarreta) {
		this.numCarreta = numCarreta;
	}

	public int getPosMenu() {
		return posMenu;
	}

	public void setPosMenu(int posMenu) {
		this.posMenu = posMenu;
	}

	public Long getCodigoAtivOS() {
		return codigoAtivOS;
	}

	public void setCodigoAtivOS(Long codigoAtivOS) {
		this.codigoAtivOS = codigoAtivOS;
	}

	public Long getNroOS() {
		return nroOS;
	}

	public void setNroOS(Long nroOS) {
		this.nroOS = nroOS;
	}

	public Long getLiberacaoOS() {
		return liberacaoOS;
	}

	public void setLiberacaoOS(Long liberacaoOS) {
		this.liberacaoOS = liberacaoOS;
	}

	public Long getCargoMotomec() {
		return cargoMotomec;
	}

	public void setCargoMotomec(Long cargoMotomec) {
		this.cargoMotomec = cargoMotomec;
	}

	public Long getLugarMotivoParada() {
		return lugarMotivoParada;
	}

	public void setLugarMotivoParada(Long lugarMotivoParada) {
		this.lugarMotivoParada = lugarMotivoParada;
	}

	public boolean isVerTimer() {
		return verTimer;
	}

	public void setVerTimer(boolean verTimer) {
		this.verTimer = verTimer;
	}

	public String getVerAtualCL() {
		return verAtualCL;
	}

	public void setVerAtualCL(String verAtualCL) {
		this.verAtualCL = verAtualCL;
	}

	public int getPosChecklist() {
		return posChecklist;
	}

	public void setPosChecklist(int posChecklist) {
		this.posChecklist = posChecklist;
	}

	public int getVerPosTela() {
		return verPosTela;
	}

	public void setVerPosTela(int verPosTela) {
		this.verPosTela = verPosTela;
	}
}
