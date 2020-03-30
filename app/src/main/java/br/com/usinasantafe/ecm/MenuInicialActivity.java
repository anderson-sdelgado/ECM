package br.com.usinasantafe.ecm;

import android.Manifest;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.usinasantafe.ecm.control.CheckListCTR;
import br.com.usinasantafe.ecm.model.bean.estaticas.ColabBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.CECBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.CabecCLBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.PreCECBean;
import br.com.usinasantafe.ecm.util.ConexaoWeb;
import br.com.usinasantafe.ecm.util.EnvioDadosServ;
import br.com.usinasantafe.ecm.util.VerifDadosServ;
import br.com.usinasantafe.ecm.model.bean.variaveis.RespItemCLBean;

public class MenuInicialActivity extends ActivityGeneric {

    private ListView menuInicialListView;
    private ECMContext ecmContext;
    private ProgressDialog progressBar;

    private TextView textViewProcesso;
    private Handler customHandler = new Handler();
    private boolean verTela;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_inicial);

        verif();

        ecmContext = (ECMContext) getApplication();

        if(!checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)){
            String[] PERMISSIONS = {android.Manifest.permission.WRITE_EXTERNAL_STORAGE};
            ActivityCompat.requestPermissions((Activity) this, PERMISSIONS, 112);
        }

        progressBar = new ProgressDialog(this);
        CheckListCTR checkListCTR = new CheckListCTR();

        if(ecmContext.getMotoMecCTR().verBolAberto()){
            if(checkListCTR.verCabecAberto()){
                startTimer("N_NAC");
                checkListCTR.clearRespCabecAberto();
                ecmContext.setPosCheckList(1);
                Intent it = new Intent(MenuInicialActivity.this, ItemCheckListActivity.class);
                startActivity(it);
                finish();
            }
            else{
                if(ecmContext.getPneuCTR().verCalibAberto()){
                    startTimer("N_NAC");
                    Intent it = new Intent(MenuInicialActivity.this, ListaPosPneuActivity.class);
                    startActivity(it);
                    finish();
                }
                else {
                    verTela = true;
                    atualizarAplic();
                }
            }
        }
        else{
            verTela = false;
            atualizarAplic();
        }


        listarMenuInicial();

    }

    public void listarMenuInicial() {

        ArrayList<String> itens = new ArrayList<String>();

        itens.add("APONTAMENTO");
        itens.add("CONFIGURAÇÃO");
        itens.add("SAIR");

        AdapterList adapterList = new AdapterList(this, itens);
        menuInicialListView = (ListView) findViewById(R.id.listaMenuInicial);
        menuInicialListView.setAdapter(adapterList);

        menuInicialListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                TextView textView = v.findViewById(R.id.textViewItemList);
                String text = textView.getText().toString();

                if (text.equals("APONTAMENTO")) {

                    ColabBean colabBean = new ColabBean();
                    if(colabBean.hasElements() && ecmContext.getConfigCTR().hasElements()) {
                        ecmContext.setVerPosTela(1);
                        customHandler.removeCallbacks(updateTimerThread);
                        Intent it = new Intent(MenuInicialActivity.this, MotoristaActivity.class);
                        startActivity(it);
                        finish();
                    }

                } else if (text.equals("CONFIGURAÇÃO")) {

                    Intent it = new Intent(MenuInicialActivity.this, SenhaActivity.class);
                    startActivity(it);
                    finish();

                } else if (text.equals("SAIR")) {

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

    private Runnable updateTimerThread = new Runnable() {

        public void run() {
            if (ecmContext.getConfigCTR().hasElements()) {
                if (EnvioDadosServ.getInstance().getStatusEnvio() == 1) {
                    textViewProcesso.setTextColor(Color.YELLOW);
                    textViewProcesso.setText("Enviando Dados...");
                } else if (EnvioDadosServ.getInstance().getStatusEnvio() == 2) {
                    textViewProcesso.setTextColor(Color.RED);
                    textViewProcesso.setText("Existem Dados para serem Enviados");
                } else if (EnvioDadosServ.getInstance().getStatusEnvio() == 3) {
                    textViewProcesso.setTextColor(Color.GREEN);
                    textViewProcesso.setText("Todos os Dados já foram Enviados");
                }
            } else {
                textViewProcesso.setTextColor(Color.RED);
                textViewProcesso.setText("Aparelho sem Equipamento");
            }
            customHandler.postDelayed(this, 10000);
        }
    };

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

        if(verTela){
            Intent it = new Intent(MenuInicialActivity.this, MenuMotoMecActivity.class);
            startActivity(it);
            finish();
        }

    }

    public boolean checkPermission(String permission){
        int check = ContextCompat.checkSelfPermission(this, permission);
        return (check == PackageManager.PERMISSION_GRANTED);
    }

    public void atualizarAplic(){
        ConexaoWeb conexaoWeb = new ConexaoWeb();
        if (conexaoWeb.verificaConexao(this)) {
            if (ecmContext.getConfigCTR().hasElements()) {
                progressBar.setCancelable(true);
                progressBar.setMessage("Buscando Atualização...");
                progressBar.show();
                VerifDadosServ.getInstance().verAtualAplic(ecmContext.versaoAplic, this, progressBar);
            }
        } else {
            startTimer("N_NAC");
        }
    }

    public void verif(){

        CECBean CECBean = new CECBean();
        List boletimList = CECBean.all();

        Log.i("PMM", "BoletimBkpBean");

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
