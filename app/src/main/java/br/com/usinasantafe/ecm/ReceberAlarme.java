package br.com.usinasantafe.ecm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.List;

import br.com.usinasantafe.ecm.model.bean.variaveis.ApontImpleMMBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.ApontMMBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.BoletimMMBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.CECBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.CabecCLBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.PreCECBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.RespItemCLBean;
import br.com.usinasantafe.ecm.util.EnvioDadosServ;
import br.com.usinasantafe.ecm.util.Tempo;
import br.com.usinasantafe.ecm.model.pst.DatabaseHelper;

/**
 * BroadcastReceiver para receber o alarme depois do tempo especificado
 * 
 * @author ricardo
 * 
 */
public class ReceberAlarme extends BroadcastReceiver {

	private DatabaseHelper databaseHelper = null;

	@Override
	public void onReceive(Context context, Intent intent) {

		if(DatabaseHelper.getInstance() == null){
			new DatabaseHelper(context);
		}

		Log.i("ECM", "DATA HORA = " + Tempo.getInstance().dataComHora().getDataHora());
		verif();
		if(EnvioDadosServ.getInstance().verifDadosEnvio()){
			Log.i("ECM", "ENVIANDO");
			EnvioDadosServ.getInstance().envioDados(context);
		}

	}

	public void verif(){

		BoletimMMBean boletimMMBean = new BoletimMMBean();
		List boletimMMList = boletimMMBean.all();

		Log.i("PMM", "AKI");

		for (int i = 0; i < boletimMMList.size(); i++) {

			boletimMMBean = (BoletimMMBean) boletimMMList.get(i);
			Log.i("PMM", "BOLETIM MM");
			Log.i("PMM", "idBoletim = " + boletimMMBean.getIdBolMM());
			Log.i("PMM", "idExtBoletim = " + boletimMMBean.getIdExtBolMM());
			Log.i("PMM", "codMotoBoletim = " + boletimMMBean.getMatricFuncBolMM());
			Log.i("PMM", "codEquipBoletim = " + boletimMMBean.getIdEquipBolMM());
			Log.i("PMM", "codTurnoBoletim = " + boletimMMBean.getIdTurnoBolMM());
			Log.i("PMM", "hodometroInicialBoletim = " + boletimMMBean.getHodometroInicialBolMM());
			Log.i("PMM", "hodometroFinalBoletim = " + boletimMMBean.getHodometroFinalBolMM());
			Log.i("PMM", "osBoletim = " + boletimMMBean.getOsBolMM());
			Log.i("PMM", "ativPrincBoletim = " + boletimMMBean.getAtivPrincBolMM());
			Log.i("PMM", "dthrInicioBoletim = " + boletimMMBean.getDthrInicialBolMM());
			Log.i("PMM", "dthrFimBoletim = " + boletimMMBean.getDthrFinalBolMM());
			Log.i("PMM", "statusBoletim = " + boletimMMBean.getStatusBolMM());
			Log.i("PMM", "qtdeApontBolMM = " + boletimMMBean.getQtdeApontBolMM());

		}

		ApontMMBean apontMMBean = new ApontMMBean();
		List apontaMMList = apontMMBean.all();

		for (int i = 0; i < apontaMMList.size(); i++) {

			apontMMBean = (ApontMMBean) apontaMMList.get(i);
			Log.i("PMM", "APONTAMENTO MM");
			Log.i("PMM", "idAponta = " + apontMMBean.getIdApontMM());
			Log.i("PMM", "idBolAponta = " + apontMMBean.getIdBolApontMM());
			Log.i("PMM", "idExtBolAponta = " + apontMMBean.getIdExtBolApontMM());
			Log.i("PMM", "osAponta = " + apontMMBean.getOsApontMM());
			Log.i("PMM", "atividadeAponta = " + apontMMBean.getAtivApontMM());
			Log.i("PMM", "paradaAponta = " + apontMMBean.getParadaApontMM());
			Log.i("PMM", "transbordoAponta = " + apontMMBean.getTransbApontMM());
			Log.i("PMM", "dthrAponta = " + apontMMBean.getDthrApontMM());

		}

		ApontImpleMMBean apontImpleMMBean = new ApontImpleMMBean();
		List apontImpleList = apontImpleMMBean.all();

		for (int l = 0; l < apontImpleList.size(); l++) {
			apontImpleMMBean = (ApontImpleMMBean) apontImpleList.get(l);
			Log.i("PMM", "IMPLEMENTO");
			Log.i("PMM", "idApontImplemento = " + apontImpleMMBean.getIdApontImpleMM());
			Log.i("PMM", "idApontMM = " + apontImpleMMBean.getIdApontMM());
			Log.i("PMM", "posImplemento = " + apontImpleMMBean.getPosImpleMM());
			Log.i("PMM", "codEquipImplemento = " + apontImpleMMBean.getCodEquipImpleMM());
		}

		CECBean CECBean = new CECBean();
		List boletimList = CECBean.all();

		Log.i("PMM", "CECBean");

		for (int i = 0; i < boletimList.size(); i++) {

			CECBean = (CECBean) boletimList.get(i);
			Log.i("PMM", "idBoleto = " + CECBean.getIdCEC());
			Log.i("PMM", "caminhaoBoleto = " + CECBean.getCaminhaoCEC());
			Log.i("PMM", "possuiSorteioBoleto = " + CECBean.getPossuiSorteioCEC());
			Log.i("PMM", "cecPaiBoleto = " + CECBean.getCecPaiCEC());
			Log.i("PMM", "cdFrenteBoleto = " + CECBean.getCodFrenteCEC());
			Log.i("PMM", "dthrEntradaBoleto = " + CECBean.getDthrEntradaCEC());
			Log.i("PMM", "cecSorteado1Boleto = " + CECBean.getCecSorteado1CEC());
			Log.i("PMM", "unidadeSorteada1Boleto = " + CECBean.getUnidadeSorteada1CEC());
			Log.i("PMM", "cecSorteado2Boleto = " + CECBean.getCecSorteado2CEC());
			Log.i("PMM", "unidadeSorteada2Boleto = " + CECBean.getUnidadeSorteada2CEC());
			Log.i("PMM", "cecSorteado3Boleto = " + CECBean.getCecSorteado3CEC());
			Log.i("PMM", "unidadeSorteada3Boleto = " + CECBean.getUnidadeSorteada3CEC());
			Log.i("PMM", "pesoLiquidoBoleto = " + CECBean.getPesoLiquidoCEC());

		}

		PreCECBean preCECBean = new PreCECBean();
		List compVCanaList = preCECBean.all();

		Log.i("PMM", "PreCECBean");

		for (int i = 0; i < compVCanaList.size(); i++) {

			preCECBean = (PreCECBean) compVCanaList.get(i);
			Log.i("PMM", "idCompVCana = " + preCECBean.getIdPreCEC());
			Log.i("PMM", "cam = " + preCECBean.getCam());
			Log.i("PMM", "libCam = " + preCECBean.getLibCam());
			Log.i("PMM", "moto = " + preCECBean.getMoto());
			Log.i("PMM", "carr1 = " + preCECBean.getCarr1());
			Log.i("PMM", "libCarr1 = " + preCECBean.getLibCarr1());
			Log.i("PMM", "carr2 = " + preCECBean.getCarr2());
			Log.i("PMM", "libCarr2 = " + preCECBean.getLibCarr2());
			Log.i("PMM", "carr3 = " + preCECBean.getCarr3());
			Log.i("PMM", "libCarr3 = " + preCECBean.getLibCarr3());
			Log.i("PMM", "carr4 = " + preCECBean.getCarr4());
			Log.i("PMM", "libCarr4 = " + preCECBean.getLibCarr4());
			Log.i("PMM", "dataChegCampo = " + preCECBean.getDataChegCampo());
			Log.i("PMM", "dataSaidaCampo = " + preCECBean.getDataSaidaCampo());
			Log.i("PMM", "dataSaidaUsina = " + preCECBean.getDataSaidaUsina());
			Log.i("PMM", "turno = " + preCECBean.getTurno());

		}

		CabecCLBean cabecCLBean = new CabecCLBean();
		List cabecList = cabecCLBean.all();

		Log.i("PMM", "CabecCheckList");

		for (int j = 0; j < cabecList.size(); j++) {

			cabecCLBean = (CabecCLBean) cabecList.get(j);
			Log.i("PMM", "IdCabecCheck = " + cabecCLBean.getIdCabCL());
			Log.i("PMM", "EquipCabecCheckList = " + cabecCLBean.getEquipCabCL());
			Log.i("PMM", "DtCabecCheckList = " + cabecCLBean.getDtCabCL());
			Log.i("PMM", "FuncCabecCheckList = " + cabecCLBean.getFuncCabCL());
			Log.i("PMM", "TurnoCabecCheckList = " + cabecCLBean.getTurnoCabCL());
			Log.i("PMM", "StatusCabecCheckList = " + cabecCLBean.getStatusCabCL());
			Log.i("PMM", "DtCabecCheckList = " + cabecCLBean.getDtCabCL());

		}

		RespItemCLBean respItemCLBean = new RespItemCLBean();
		List respItemList = respItemCLBean.all();

		Log.i("PMM", "RespItemCheckList");

		for (int j = 0; j < respItemList.size(); j++) {

			respItemCLBean = (RespItemCLBean) respItemList.get(j);
			Log.i("PMM", "IdItemCheckList = " + respItemCLBean.getIdItCL());
			Log.i("PMM", "IdItItemCheckList = " + respItemCLBean.getIdItBDItCL());
			Log.i("PMM", "IdCabecItemCheckList = " + respItemCLBean.getIdCabItCL());
			Log.i("PMM", "OpcaoItemCheckList = " + respItemCLBean.getOpItCL());

		}

		Log.i("PMM", "versão = " + ECMContext.versaoAplic);

	}

}