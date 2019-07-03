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
import br.com.usinasantafe.ecm.to.tb.variaveis.ApontMotoMecTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.AtualizaTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.BoletimBkpTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.BoletimTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.CabecCheckListTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.CompVCanaBkpTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.CompVCanaTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.ConfigTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.RespItemCheckListTO;

public class PrincipalActivity extends ActivityGeneric {

    private ListView lista;
    private ECMContext ecmContext;
    private ConfigTO configTO;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        verif();

        ecmContext = (ECMContext) getApplication();

        if(!checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)){
            String[] PERMISSIONS = {android.Manifest.permission.WRITE_EXTERNAL_STORAGE};
            ActivityCompat.requestPermissions((Activity) this, PERMISSIONS, 112);
        }

        ConexaoWeb conexaoWeb = new ConexaoWeb();
        configTO = new ConfigTO();
        List configList = configTO.all();

        progressBar = new ProgressDialog(this);

        if(conexaoWeb.verificaConexao(this))
        {

            configTO = new ConfigTO();
            configList = configTO.all();

            if(configList.size() > 0){

                progressBar.setCancelable(true);
                progressBar.setMessage("Buscando Atualização...");
                progressBar.show();

                configTO = (ConfigTO) configList.get(0);
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

        CabecCheckListTO cabecCheckListTO = new CabecCheckListTO();
        List cabecList = cabecCheckListTO.get("statusCabecCheckList", 1L);

        if (cabecList.size() > 0) {

            RespItemCheckListTO respItemCheckListTO = new RespItemCheckListTO();

            if (respItemCheckListTO.hasElements()) {
                cabecCheckListTO = (CabecCheckListTO) cabecList.get(0);
                List respList = respItemCheckListTO.get("idCabecItemCheckList", cabecCheckListTO.getIdCabecCheckList());
                for (int i = 0; i < respList.size(); i++) {
                    respItemCheckListTO = (RespItemCheckListTO) respList.get(i);
                    respItemCheckListTO.delete();
                }
            }

            if (progressBar.isShowing()) {
                progressBar.dismiss();
            }

            ecmContext.setPosChecklist(1L);
            Intent it = new Intent(PrincipalActivity.this, ItemCheckListActivity.class);
            startActivity(it);
            finish();

        }

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

                    ConfigTO configTO = new ConfigTO();
                    if(configTO.hasElements()) {
                        ecmContext.setTelaAltMoto(1);
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

        ApontMotoMecTO apontMotoMecTO = new ApontMotoMecTO();
        List apontMotoMecList = apontMotoMecTO.all();

        Log.i("PMM", "AKI");

        Log.i("PMM", "ApontMotoMec");

        for (int i = 0; i < apontMotoMecList.size(); i++) {

            apontMotoMecTO = (ApontMotoMecTO) apontMotoMecList.get(i);
            Log.i("PMM", "idApontMM = " + apontMotoMecTO.getIdApontMM());
            Log.i("PMM", "veic = " + apontMotoMecTO.getVeic());
            Log.i("PMM", "motorista = " + apontMotoMecTO.getMotorista());
            Log.i("PMM", "opcor = " + apontMotoMecTO.getOpcor());
            Log.i("PMM", "dihi = " + apontMotoMecTO.getDihi());
            Log.i("PMM", "caux = " + apontMotoMecTO.getCaux());
            Log.i("PMM", "tipoEngDeseng = " + apontMotoMecTO.getTipoEngDeseng());

        }

        BoletimBkpTO boletimBkpTO = new BoletimBkpTO();
        List boletimBkpList = boletimBkpTO.all();

        Log.i("PMM", "BoletimBkpTO");

        for (int i = 0; i < boletimBkpList.size(); i++) {

            boletimBkpTO = (BoletimBkpTO) boletimBkpList.get(i);
            Log.i("PMM", "idBoleto = " + boletimBkpTO.getIdBoleto());
            Log.i("PMM", "caminhaoBoleto = " + boletimBkpTO.getCaminhaoBoleto());
            Log.i("PMM", "possuiSorteioBoleto = " + boletimBkpTO.getPossuiSorteioBoleto());
            Log.i("PMM", "cecPaiBoleto = " + boletimBkpTO.getCecPaiBoleto());
            Log.i("PMM", "cdFrenteBoleto = " + boletimBkpTO.getCdFrenteBoleto());
            Log.i("PMM", "dthrEntradaBoleto = " + boletimBkpTO.getDthrEntradaBoleto());
            Log.i("PMM", "cecSorteado1Boleto = " + boletimBkpTO.getCecSorteado1Boleto());
            Log.i("PMM", "unidadeSorteada1Boleto = " + boletimBkpTO.getUnidadeSorteada1Boleto());
            Log.i("PMM", "cecSorteado2Boleto = " + boletimBkpTO.getCecSorteado2Boleto());
            Log.i("PMM", "unidadeSorteada2Boleto = " + boletimBkpTO.getUnidadeSorteada2Boleto());
            Log.i("PMM", "cecSorteado3Boleto = " + boletimBkpTO.getCecSorteado3Boleto());
            Log.i("PMM", "unidadeSorteada3Boleto = " + boletimBkpTO.getUnidadeSorteada3Boleto());
            Log.i("PMM", "pesoLiquidoBoleto = " + boletimBkpTO.getPesoLiquidoBoleto());

        }

        BoletimTO boletimTO = new BoletimTO();
        List boletimList = boletimTO.all();

        Log.i("PMM", "BoletimBkpTO");

        for (int i = 0; i < boletimList.size(); i++) {

            boletimTO = (BoletimTO) boletimList.get(i);
            Log.i("PMM", "idBoleto = " + boletimTO.getIdBoletim());
            Log.i("PMM", "caminhaoBoleto = " + boletimTO.getCaminhaoBoleto());
            Log.i("PMM", "possuiSorteioBoleto = " + boletimTO.getPossuiSorteioBoleto());
            Log.i("PMM", "cecPaiBoleto = " + boletimTO.getCecPaiBoleto());
            Log.i("PMM", "cdFrenteBoleto = " + boletimTO.getCdFrenteBoleto());
            Log.i("PMM", "dthrEntradaBoleto = " + boletimTO.getDthrEntradaBoleto());
            Log.i("PMM", "cecSorteado1Boleto = " + boletimTO.getCecSorteado1Boleto());
            Log.i("PMM", "unidadeSorteada1Boleto = " + boletimTO.getUnidadeSorteada1Boleto());
            Log.i("PMM", "cecSorteado2Boleto = " + boletimTO.getCecSorteado2Boleto());
            Log.i("PMM", "unidadeSorteada2Boleto = " + boletimTO.getUnidadeSorteada2Boleto());
            Log.i("PMM", "cecSorteado3Boleto = " + boletimTO.getCecSorteado3Boleto());
            Log.i("PMM", "unidadeSorteada3Boleto = " + boletimTO.getUnidadeSorteada3Boleto());
            Log.i("PMM", "pesoLiquidoBoleto = " + boletimTO.getPesoLiquidoBoleto());

        }

        CompVCanaTO compVCanaTO = new CompVCanaTO();
        List compVCanaList = compVCanaTO.all();

        Log.i("PMM", "CompVCanaTO");

        for (int i = 0; i < compVCanaList.size(); i++) {

            compVCanaTO = (CompVCanaTO) compVCanaList.get(i);
            Log.i("PMM", "idCompVCana = " + compVCanaTO.getIdCompVCana());
            Log.i("PMM", "cam = " + compVCanaTO.getCam());
            Log.i("PMM", "libCam = " + compVCanaTO.getLibCam());
            Log.i("PMM", "moto = " + compVCanaTO.getMoto());
            Log.i("PMM", "carr1 = " + compVCanaTO.getCarr1());
            Log.i("PMM", "libCarr1 = " + compVCanaTO.getLibCarr1());
            Log.i("PMM", "carr2 = " + compVCanaTO.getCarr2());
            Log.i("PMM", "libCarr2 = " + compVCanaTO.getLibCarr2());
            Log.i("PMM", "carr3 = " + compVCanaTO.getCarr3());
            Log.i("PMM", "libCarr3 = " + compVCanaTO.getLibCarr3());
            Log.i("PMM", "carr4 = " + compVCanaTO.getCarr4());
            Log.i("PMM", "libCarr4 = " + compVCanaTO.getLibCarr4());
            Log.i("PMM", "dataChegCampo = " + compVCanaTO.getDataChegCampo());
            Log.i("PMM", "dataSaidaCampo = " + compVCanaTO.getDataSaidaCampo());
            Log.i("PMM", "dataSaidaUsina = " + compVCanaTO.getDataSaidaUsina());
            Log.i("PMM", "turno = " + compVCanaTO.getTurno());

        }


        CompVCanaBkpTO compVCanaBkpTO = new CompVCanaBkpTO();
        List compVCanaBkpList = compVCanaBkpTO.all();

        Log.i("PMM", "CompVCanaBkpTO");

        for (int i = 0; i < compVCanaBkpList.size(); i++) {

            compVCanaBkpTO = (CompVCanaBkpTO) compVCanaBkpList.get(i);
            Log.i("PMM", "idVCanaBkp = " + compVCanaBkpTO.getIdVCanaBkp());
            Log.i("PMM", "moto = " + compVCanaBkpTO.getMoto());
            Log.i("PMM", "carr1 = " + compVCanaBkpTO.getCarr1());
            Log.i("PMM", "carr2 = " + compVCanaBkpTO.getCarr2());
            Log.i("PMM", "carr3 = " + compVCanaBkpTO.getCarr3());
            Log.i("PMM", "dataSaidaCampo = " + compVCanaBkpTO.getDataSaidaCampo());
            Log.i("PMM", "noteiro = " + compVCanaBkpTO.getNoteiro());

        }

        CabecCheckListTO cabecCheckListTO = new CabecCheckListTO();
        List cabecList = cabecCheckListTO.all();

        Log.i("PMM", "CabecCheckList");

        for (int j = 0; j < cabecList.size(); j++) {

            cabecCheckListTO = (CabecCheckListTO) cabecList.get(j);
            Log.i("PMM", "IdCabecCheck = " + cabecCheckListTO.getIdCabecCheckList());
            Log.i("PMM", "EquipCabecCheckList = " + cabecCheckListTO.getEquipCabecCheckList());
            Log.i("PMM", "DtCabecCheckList = " + cabecCheckListTO.getDtCabecCheckList());
            Log.i("PMM", "FuncCabecCheckList = " + cabecCheckListTO.getFuncCabecCheckList());
            Log.i("PMM", "TurnoCabecCheckList = " + cabecCheckListTO.getTurnoCabecCheckList());
            Log.i("PMM", "StatusCabecCheckList = " + cabecCheckListTO.getStatusCabecCheckList());
            Log.i("PMM", "QtdeItemCabecCheckList = " + cabecCheckListTO.getQtdeItemCabecCheckList());
            Log.i("PMM", "DtCabecCheckList = " + cabecCheckListTO.getDtCabecCheckList());

        }

        RespItemCheckListTO respItemCheckListTO = new RespItemCheckListTO();
        List respItemList = respItemCheckListTO.all();

        Log.i("PMM", "RespItemCheckList");

        for (int j = 0; j < respItemList.size(); j++) {

            respItemCheckListTO = (RespItemCheckListTO) respItemList.get(j);
            Log.i("PMM", "IdItemCheckList = " + respItemCheckListTO.getIdItemCheckList());
            Log.i("PMM", "IdItItemCheckList = " + respItemCheckListTO.getIdItItemCheckList());
            Log.i("PMM", "IdCabecItemCheckList = " + respItemCheckListTO.getIdCabecItemCheckList());
            Log.i("PMM", "OpcaoItemCheckList = " + respItemCheckListTO.getOpcaoItemCheckList());

        }

        Log.i("PMM", "versão = " + ECMContext.versaoAplic);

    }

}
