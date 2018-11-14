package br.com.usinasantafe.ecm;

import br.com.usinasantafe.ecm.bo.ManipDadosEnvio;
import br.com.usinasantafe.ecm.bo.ManipDadosReceb;
import br.com.usinasantafe.ecm.bo.Tempo;
import br.com.usinasantafe.ecm.to.tb.variaveis.ApontMotoMecTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.CompVCanaTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.CompVVinhacaTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.InfBoletimTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.RespItemCheckListTO;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Intent;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;

public class ECMContext extends Application {

	private CompVCanaTO compVCanaTO;
	private CompVVinhacaTO compVVinhacaTO;
	private ApontMotoMecTO apontMotoMecTO;
	
	private int numCarreta;
	private int altMotoL;
	private int posMenu;

	private Long codigoAtivOS;
	private Long nroOS;
	private Long liberacaoOS;

	private boolean verTabelaConfig;
	private Long equipConfig;
	private String senhaConfig;

	private Long cargoMotomec;
	private Long lugarMotivoParada;

	private int posChecklist;
	private ArrayList<RespItemCheckListTO> listRespChecklist;

	private boolean verTimer;

	public static String versaoAplic = "1.0";
	private String verAtualCL;

	@Override
	public void onCreate() {

		super.onCreate();
//		count = 0L;
//		handler = new Handler();
//		handler.post(this);

	}

//	public CompVCanaTO getCompVCanaTO() {
//		if(compVCanaTO == null)
//			compVCanaTO = new CompVCanaTO();
//		return compVCanaTO;
//	}
	
    public CompVVinhacaTO getCompVVinhacaTO() {
        if (compVVinhacaTO == null)
        compVVinhacaTO = new CompVVinhacaTO();
        return compVVinhacaTO;
    }
	
//    public InfBoletimTO getInfBoletimTO() {
//        if (infBoletimTO == null)
//        infBoletimTO = new InfBoletimTO();
//        return infBoletimTO;
//    }
	
    public ApontMotoMecTO getApontMotoMecTO() {
        if (apontMotoMecTO == null)
        apontMotoMecTO = new ApontMotoMecTO();
        return apontMotoMecTO;
    }
    
	public int getNumCarreta() {
		return numCarreta;
	}

	public void setNumCarreta(int numCarreta) {
		this.numCarreta = numCarreta;
	}

	public int getAltMotoL() {
		return altMotoL;
	}

	public void setAltMotoL(int altMotoL) {
		this.altMotoL = altMotoL;
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

	public boolean isVerTabelaConfig() {
		return verTabelaConfig;
	}

	public void setVerTabelaConfig(boolean verTabelaConfig) {
		this.verTabelaConfig = verTabelaConfig;
	}

	public Long getEquipConfig() {
		return equipConfig;
	}

	public void setEquipConfig(Long equipConfig) {
		this.equipConfig = equipConfig;
	}

	public String getSenhaConfig() {
		return senhaConfig;
	}

	public void setSenhaConfig(String senhaConfig) {
		this.senhaConfig = senhaConfig;
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

	public ArrayList<RespItemCheckListTO> getListRespChecklist() {
		return listRespChecklist;
	}

	public void setListRespChecklist(ArrayList<RespItemCheckListTO> listRespChecklist) {
		this.listRespChecklist = listRespChecklist;
	}

	public int getPosChecklist() {
		return posChecklist;
	}

	public void setPosChecklist(int posChecklist) {
		this.posChecklist = posChecklist;
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
}
