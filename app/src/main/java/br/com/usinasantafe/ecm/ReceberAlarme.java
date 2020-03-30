package br.com.usinasantafe.ecm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

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

		Log.i("ECM", "DATA HORA = " + Tempo.getInstance().dataComHora());
		if(EnvioDadosServ.getInstance().verifDadosEnvio()){
			Log.i("ECM", "ENVIANDO");
			EnvioDadosServ.getInstance().envioDados(context);
		}

	}
}