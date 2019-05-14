package br.com.usinasantafe.ecm;

import android.Manifest;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.usinasantafe.ecm.bo.ConexaoWeb;
import br.com.usinasantafe.ecm.bo.ManipDadosVerif;
import br.com.usinasantafe.ecm.to.tb.variaveis.AtividadeOsTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.AtualizaTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.CompVCanaTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.ConfiguracaoTO;

public class PrincipalActivity extends ActivityGeneric {

    private ListView lista;
    private ECMContext ecmContext;
    private ConfiguracaoTO configTO;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

//        verif();

        ecmContext = (ECMContext) getApplication();

        if(!checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)){
            String[] PERMISSIONS = {android.Manifest.permission.WRITE_EXTERNAL_STORAGE};
            ActivityCompat.requestPermissions((Activity) this, PERMISSIONS, 112);
        }

        ConexaoWeb conexaoWeb = new ConexaoWeb();
        configTO = new ConfiguracaoTO();
        List configList = configTO.all();

        progressBar = new ProgressDialog(this);

        if(conexaoWeb.verificaConexao(this))
        {

            configTO = new ConfiguracaoTO();
            configList = configTO.all();

            if(configList.size() > 0){

                progressBar.setCancelable(true);
                progressBar.setMessage("Buscando Atualização...");
                progressBar.show();

                configTO = (ConfiguracaoTO) configList.get(0);
                AtualizaTO atualizaTO = new AtualizaTO();
                atualizaTO.setIdEquipAtualizacao(configTO.getCodCamConfig());
                atualizaTO.setVersaoAtual(ecmContext.versaoAplic);
                ManipDadosVerif.getInstance().verAtualizacao(atualizaTO, this, progressBar);
            }

        }
        else{
            startTimer("N_NAC");
        }

        configList.clear();

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

                }
            }

        });

    }

    public void onBackPressed()  {
    }

    public void startTimer(String verAtualizacao) {

        Log.i("PMM", "VERATUAL = " + verAtualizacao);
        ecmContext.setVerAtualCL(verAtualizacao);
        boolean alarmeAtivo = (PendingIntent.getBroadcast(this, 0, new Intent("ALARME_DISPARADO"), PendingIntent.FLAG_NO_CREATE) == null);

        if(progressBar.isShowing()){
            progressBar.dismiss();
        }

        if(alarmeAtivo){

            Log.i("PMM", "NOVO TIMER");

            Intent intent = new Intent("EXECUTAR_ALARME");
            PendingIntent p = PendingIntent.getBroadcast(this, 0, intent, 0);

            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(System.currentTimeMillis());
            c.add(Calendar.SECOND, 1);

            AlarmManager alarme = (AlarmManager) getSystemService(ALARM_SERVICE);
            alarme.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), 60000, p);

        }
        else{
            Log.i("PMM", "TIMER já ativo");
        }
    }

    public boolean checkPermission(String permission){
        int check = ContextCompat.checkSelfPermission(this, permission);
        return (check == PackageManager.PERMISSION_GRANTED);
    }

    public void verif(){

        CompVCanaTO compVCanaTO = new CompVCanaTO();
        List compVCanaList = compVCanaTO.all();

        Log.i("PMM", " ENTROU ");

        for (int i = 0; i < compVCanaList.size(); i++) {

            compVCanaTO = (CompVCanaTO) compVCanaList.get(i);
            Log.i("PMM", " VIAGEM ");
            Log.i("PMM", " idCompVCana " + compVCanaTO.getIdCompVCana());
            Log.i("PMM", " cam " + compVCanaTO.getCam());
            Log.i("PMM", " libCam " + compVCanaTO.getLibCam());
            Log.i("PMM", " maqCam " + compVCanaTO.getMaqCam());
            Log.i("PMM", " opCam " + compVCanaTO.getOpCam());
            Log.i("PMM", " moto " + compVCanaTO.getMoto());
            Log.i("PMM", " carr1 " + compVCanaTO.getCarr1());
            Log.i("PMM", " libCarr1 " + compVCanaTO.getLibCarr1());
            Log.i("PMM", " maqCarr1 " + compVCanaTO.getMaqCarr1());
            Log.i("PMM", " opCarr1 " + compVCanaTO.getOpCarr1());
            Log.i("PMM", " carr2 " + compVCanaTO.getCarr2());
            Log.i("PMM", " libCarr2 " + compVCanaTO.getLibCarr1());
            Log.i("PMM", " maqCarr2 " + compVCanaTO.getMaqCarr2());
            Log.i("PMM", " opCarr2 " + compVCanaTO.getOpCarr2());
            Log.i("PMM", " carr3 " + compVCanaTO.getCarr3());
            Log.i("PMM", " libCarr3 " + compVCanaTO.getLibCarr3());
            Log.i("PMM", " maqCarr3 " + compVCanaTO.getMaqCarr3());
            Log.i("PMM", " opCarr3 " + compVCanaTO.getOpCarr3());
            Log.i("PMM", " carr4 " + compVCanaTO.getIdCompVCana());
            Log.i("PMM", " libCarr4 " + compVCanaTO.getIdCompVCana());
            Log.i("PMM", " maqCarr4 " + compVCanaTO.getIdCompVCana());
            Log.i("PMM", " opCarr4 " + compVCanaTO.getIdCompVCana());
            Log.i("PMM", " dataChegCampo " + compVCanaTO.getDataChegCampo());
            Log.i("PMM", " dataSaidaCampo " + compVCanaTO.getDataSaidaCampo());
            Log.i("PMM", " dataSaidaUsina " + compVCanaTO.getDataSaidaUsina());
            Log.i("PMM", " noteiro " + compVCanaTO.getNoteiro());
            Log.i("PMM", " turno " + compVCanaTO.getTurno());


        }

    }

}
