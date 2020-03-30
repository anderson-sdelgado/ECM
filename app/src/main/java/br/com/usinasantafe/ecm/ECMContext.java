package br.com.usinasantafe.ecm;

import br.com.usinasantafe.ecm.control.CECCTR;
import br.com.usinasantafe.ecm.control.CheckListCTR;
import br.com.usinasantafe.ecm.control.ConfigCTR;
import br.com.usinasantafe.ecm.control.MotoMecCTR;
import br.com.usinasantafe.ecm.control.PneuCTR;

import android.app.Application;

public class ECMContext extends Application {

	private CECCTR CECCTR;
	private ConfigCTR configCTR;
	private MotoMecCTR motoMecCTR;
	private CheckListCTR checkListCTR;
	private PneuCTR pneuCTR;

	private int verPosTela;
	// 1 - Inicio do Aplicativo;
	// 2 - Saida da Usina;
	// 3 - Desengate no Menu Moto Mec;
	// 4 - Engate no Menu Moto Mec;
	// 5 - Certificado;
	// 6 - Desengate no Parada;
	// 7 - Engate no Parada;
	// 8 - Finalizar Boletim Moto Mec;

	private int posMenu;
	private int contDataHora;

	private Long cargoMotomec;
	private Long lugarMotivoParada;
	private String textoHorimetro;

	private boolean verTimer;

	public static String versaoAplic = "3.00";
	private String verAtualCL;

	private int posCheckList;

	private int dia;
	private int mes;
	private int ano;
	private int hora;
	private int minuto;

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

    public CECCTR getCECCTR() {
        if (CECCTR == null)
			CECCTR = new CECCTR();
        return CECCTR;
    }

    public CheckListCTR getCheckListCTR() {
        if (checkListCTR == null)
			checkListCTR = new CheckListCTR();
        return checkListCTR;
    }

	public PneuCTR getPneuCTR(){
		if (pneuCTR == null)
			pneuCTR = new PneuCTR();
		return pneuCTR;
	}

	public int getPosMenu() {
		return posMenu;
	}

	public void setPosMenu(int posMenu) {
		this.posMenu = posMenu;
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

	public int getPosCheckList() {
		return posCheckList;
	}

	public void setPosCheckList(int posCheckList) {
		this.posCheckList = posCheckList;
	}

	public int getVerPosTela() {
		return verPosTela;
	}

	public void setVerPosTela(int verPosTela) {
		this.verPosTela = verPosTela;
	}

	public int getContDataHora() {
		return contDataHora;
	}

	public void setContDataHora(int contDataHora) {
		this.contDataHora = contDataHora;
	}

	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public int getHora() {
		return hora;
	}

	public void setHora(int hora) {
		this.hora = hora;
	}

	public int getMinuto() {
		return minuto;
	}

	public void setMinuto(int minuto) {
		this.minuto = minuto;
	}

	public String getTextoHorimetro() {
		return textoHorimetro;
	}

	public void setTextoHorimetro(String textoHorimetro) {
		this.textoHorimetro = textoHorimetro;
	}
}
