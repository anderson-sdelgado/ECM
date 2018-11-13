package br.com.usinasantafe.ecm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.j256.ormlite.android.apptools.OpenHelperManager;

import br.com.usinasantafe.ecm.bo.ManipDadosEnvio;
import br.com.usinasantafe.ecm.bo.Tempo;
import br.com.usinasantafe.ecm.pst.DatabaseHelper;

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

		if(Tempo.getInstance().getDatahora() != null) {
			Tempo.getInstance().getDatahora().setTime(Tempo.getInstance().getDatahora().getTime() + 60000L);
		}

		Log.i("ECM", "DATA HORA = " + Tempo.getInstance().data());
		if(ManipDadosEnvio.getInstance().verifDadosEnvio()){
			Log.i("ECM", "ENVIANDO");
			ManipDadosEnvio.getInstance().envioDados(context);
		}

	}
}