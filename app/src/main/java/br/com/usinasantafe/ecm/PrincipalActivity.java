package br.com.usinasantafe.ecm;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.usinasantafe.ecm.bo.ManipDadosEnvio;
import br.com.usinasantafe.ecm.bo.ManipDadosReceb;
import br.com.usinasantafe.ecm.bo.Tempo;
import br.com.usinasantafe.ecm.to.tb.estaticas.MotoMecTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.ApontMotoMecTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.AtividadeOsTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.ConfiguracaoTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.InfBoletimTO;

public class PrincipalActivity extends ActivityGeneric {

    private ListView lista;
    private ECMContext ecmContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        ConfiguracaoTO configuracaoTO = new ConfiguracaoTO();
        configuracaoTO.hasElements();

        ecmContext = (ECMContext) getApplication();

        startTimer();

        listarMenuInicial();

    }

    public void listarMenuInicial() {

        ArrayList<String> itens = new ArrayList<String>();

        itens.add("APONTAMENTO");
        itens.add("CONFIGURAÇÃO");
        itens.add("SAIR");

        AdapterList adapterList = new AdapterList(this, itens);
        lista = (ListView) findViewById(R.id.listaMenuInicial);
        lista.setAdapter(adapterList);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {
                // TODO Auto-generated method stub
                if (position == 0) {

                    ConfiguracaoTO configuracaoTO = new ConfiguracaoTO();

                    if(configuracaoTO.hasElements()) {

                        AtividadeOsTO atividadeOs = new AtividadeOsTO();

                        if (atividadeOs.count() == 0) {
                            atividadeOs.setAtivOS((long) 0);
                            atividadeOs.setEstado((long) 0);
                            atividadeOs.insert();
                        }

                        ecmContext.setAltMotoL(1);
                        Intent it = new Intent(PrincipalActivity.this, MotoristaActivity.class);
                        startActivity(it);
                        finish();
                    }

                } else if (position == 1) {

                    Intent it = new Intent(PrincipalActivity.this, SenhaActivity.class);
                    startActivity(it);
                    finish();

                } else if (position == 2) {

                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_HOME);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);

                } else if (position == 3) {

//                    Intent it = new Intent(PrincipalActivity.this, ConexaoPushActivity.class);
//                    startActivity(it);

                    //MediaPlayer player = MediaPlayer.create(PrincipalActivity.this, R.raw.johnny);
                    //player.start();

					/*
                    Vibrator rr = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
			        long milliseconds = 100;
			        rr.vibrate(milliseconds);

			        try{
			        	Uri som = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
			        	Ringtone tone = RingtoneManager.getRingtone(PrincipalActivity.this, som);
			        	tone.play();
			        }
			        catch(Exception ex){

			        }
					*/

                }
            }

        });

    }

    public void onBackPressed()  {
    }

    public void startTimer() {

        boolean alarmeAtivo = (PendingIntent.getBroadcast(this, 0, new Intent("ALARME_DISPARADO"), PendingIntent.FLAG_NO_CREATE) == null);

        if(alarmeAtivo){

            Log.i("ECM", "NOVO ALARME");

            Intent intent = new Intent("EXECUTAR_ALARME");
            PendingIntent p = PendingIntent.getBroadcast(this, 0, intent, 0);

            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(System.currentTimeMillis());
            c.add(Calendar.SECOND, 1);

            AlarmManager alarme = (AlarmManager) getSystemService(ALARM_SERVICE);
            alarme.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), 60000, p);

        }
        else{
            Log.i("Script", "Alarme já ativo");
        }
    }


}
