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

import br.com.usinasantafe.ecm.model.bean.variaveis.CertifCanaBkpBean;
import br.com.usinasantafe.ecm.util.ConexaoWeb;
import br.com.usinasantafe.ecm.util.ManipDadosVerif;
import br.com.usinasantafe.ecm.model.bean.variaveis.ApontMotoMecBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.AtualizaBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.BoletimBkpBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.BoletimBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.CabecCheckListBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.CertifCanaBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.RespCheckListBean;

public class MenuInicialActivity extends ActivityGeneric {

    private ListView menuInicialListView;
    private ECMContext ecmContext;
    private ProgressDialog progressBar;

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

        ConexaoWeb conexaoWeb = new ConexaoWeb();
        if(conexaoWeb.verificaConexao(this)) {

            if(ecmContext.getConfigCTR().hasElemConfig()){

                progressBar.setCancelable(true);
                progressBar.setMessage("Buscando Atualização...");
                progressBar.show();

                AtualizaBean atualizaBean = new AtualizaBean();
                atualizaBean.setIdEquipAtualizacao(ecmContext.getConfigCTR().getConfig().getCodEquipConfig());
                atualizaBean.setVersaoAtual(ecmContext.versaoAplic);
                ManipDadosVerif.getInstance().verAtualizacao(atualizaBean, this, progressBar);
            }

        }
        else{
            startTimer("N_NAC");
        }

        CabecCheckListBean cabecCheckListBean = new CabecCheckListBean();
        List cabecList = cabecCheckListBean.get("statusCabecCheckList", 1L);

        if (cabecList.size() > 0) {

            RespCheckListBean respCheckListBean = new RespCheckListBean();

            if (respCheckListBean.hasElements()) {
                cabecCheckListBean = (CabecCheckListBean) cabecList.get(0);
                List respList = respCheckListBean.get("idCabecItemCheckList", cabecCheckListBean.getIdCabecCheckList());
                for (int i = 0; i < respList.size(); i++) {
                    respCheckListBean = (RespCheckListBean) respList.get(i);
                    respCheckListBean.delete();
                }
            }

            if (progressBar.isShowing()) {
                progressBar.dismiss();
            }

            ecmContext.setPosChecklist(1);
            Intent it = new Intent(MenuInicialActivity.this, ItemCheckListActivity.class);
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
        menuInicialListView = (ListView) findViewById(R.id.listaMenuInicial);
        menuInicialListView.setAdapter(adapterList);

        menuInicialListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                if (position == 0) {

                    if(ecmContext.getConfigCTR().hasElemConfig()) {
                        ecmContext.setVerPosTela(1);
                        Intent it = new Intent(MenuInicialActivity.this, MotoristaActivity.class);
                        startActivity(it);
                        finish();
                    }

                } else if (position == 1) {

                    Intent it = new Intent(MenuInicialActivity.this, SenhaActivity.class);
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

        ApontMotoMecBean apontMotoMecBean = new ApontMotoMecBean();
        List apontMotoMecList = apontMotoMecBean.all();

        Log.i("PMM", "AKI");

        Log.i("PMM", "ApontMotoMec");

        for (int i = 0; i < apontMotoMecList.size(); i++) {

            apontMotoMecBean = (ApontMotoMecBean) apontMotoMecList.get(i);
            Log.i("PMM", "idApontMM = " + apontMotoMecBean.getIdApontMM());
            Log.i("PMM", "veic = " + apontMotoMecBean.getCodEquip());
            Log.i("PMM", "motorista = " + apontMotoMecBean.getMatricColab());
            Log.i("PMM", "opcor = " + apontMotoMecBean.getOpCor());
            Log.i("PMM", "dihi = " + apontMotoMecBean.getDthr());
            Log.i("PMM", "caux = " + apontMotoMecBean.getCaux());
            Log.i("PMM", "tipoEngDeseng = " + apontMotoMecBean.getTipoEngDeseng());

        }

        BoletimBkpBean boletimBkpBean = new BoletimBkpBean();
        List boletimBkpList = boletimBkpBean.all();

        Log.i("PMM", "BoletimBkpBean");

        for (int i = 0; i < boletimBkpList.size(); i++) {

            boletimBkpBean = (BoletimBkpBean) boletimBkpList.get(i);
            Log.i("PMM", "idBoleto = " + boletimBkpBean.getIdBoleto());
            Log.i("PMM", "caminhaoBoleto = " + boletimBkpBean.getCaminhaoBoleto());
            Log.i("PMM", "possuiSorteioBoleto = " + boletimBkpBean.getPossuiSorteioBoleto());
            Log.i("PMM", "cecPaiBoleto = " + boletimBkpBean.getCecPaiBoleto());
            Log.i("PMM", "cdFrenteBoleto = " + boletimBkpBean.getCdFrenteBoleto());
            Log.i("PMM", "dthrEntradaBoleto = " + boletimBkpBean.getDthrEntradaBoleto());
            Log.i("PMM", "cecSorteado1Boleto = " + boletimBkpBean.getCecSorteado1Boleto());
            Log.i("PMM", "unidadeSorteada1Boleto = " + boletimBkpBean.getUnidadeSorteada1Boleto());
            Log.i("PMM", "cecSorteado2Boleto = " + boletimBkpBean.getCecSorteado2Boleto());
            Log.i("PMM", "unidadeSorteada2Boleto = " + boletimBkpBean.getUnidadeSorteada2Boleto());
            Log.i("PMM", "cecSorteado3Boleto = " + boletimBkpBean.getCecSorteado3Boleto());
            Log.i("PMM", "unidadeSorteada3Boleto = " + boletimBkpBean.getUnidadeSorteada3Boleto());
            Log.i("PMM", "pesoLiquidoBoleto = " + boletimBkpBean.getPesoLiquidoBoleto());

        }

        BoletimBean boletimBean = new BoletimBean();
        List boletimList = boletimBean.all();

        Log.i("PMM", "BoletimBkpBean");

        for (int i = 0; i < boletimList.size(); i++) {

            boletimBean = (BoletimBean) boletimList.get(i);
            Log.i("PMM", "idBoleto = " + boletimBean.getIdBoletim());
            Log.i("PMM", "caminhaoBoleto = " + boletimBean.getCaminhaoBoleto());
            Log.i("PMM", "possuiSorteioBoleto = " + boletimBean.getPossuiSorteioBoleto());
            Log.i("PMM", "cecPaiBoleto = " + boletimBean.getCecPaiBoleto());
            Log.i("PMM", "cdFrenteBoleto = " + boletimBean.getCdFrenteBoleto());
            Log.i("PMM", "dthrEntradaBoleto = " + boletimBean.getDthrEntradaBoleto());
            Log.i("PMM", "cecSorteado1Boleto = " + boletimBean.getCecSorteado1Boleto());
            Log.i("PMM", "unidadeSorteada1Boleto = " + boletimBean.getUnidadeSorteada1Boleto());
            Log.i("PMM", "cecSorteado2Boleto = " + boletimBean.getCecSorteado2Boleto());
            Log.i("PMM", "unidadeSorteada2Boleto = " + boletimBean.getUnidadeSorteada2Boleto());
            Log.i("PMM", "cecSorteado3Boleto = " + boletimBean.getCecSorteado3Boleto());
            Log.i("PMM", "unidadeSorteada3Boleto = " + boletimBean.getUnidadeSorteada3Boleto());
            Log.i("PMM", "pesoLiquidoBoleto = " + boletimBean.getPesoLiquidoBoleto());

        }

        CertifCanaBean certifCanaBean = new CertifCanaBean();
        List compVCanaList = certifCanaBean.all();

        Log.i("PMM", "CertifCanaBean");

        for (int i = 0; i < compVCanaList.size(); i++) {

            certifCanaBean = (CertifCanaBean) compVCanaList.get(i);
            Log.i("PMM", "idCompVCana = " + certifCanaBean.getIdCertifCana());
            Log.i("PMM", "cam = " + certifCanaBean.getCam());
            Log.i("PMM", "libCam = " + certifCanaBean.getLibCam());
            Log.i("PMM", "moto = " + certifCanaBean.getMoto());
            Log.i("PMM", "carr1 = " + certifCanaBean.getCarr1());
            Log.i("PMM", "libCarr1 = " + certifCanaBean.getLibCarr1());
            Log.i("PMM", "carr2 = " + certifCanaBean.getCarr2());
            Log.i("PMM", "libCarr2 = " + certifCanaBean.getLibCarr2());
            Log.i("PMM", "carr3 = " + certifCanaBean.getCarr3());
            Log.i("PMM", "libCarr3 = " + certifCanaBean.getLibCarr3());
            Log.i("PMM", "carr4 = " + certifCanaBean.getCarr4());
            Log.i("PMM", "libCarr4 = " + certifCanaBean.getLibCarr4());
            Log.i("PMM", "dataChegCampo = " + certifCanaBean.getDataChegCampo());
            Log.i("PMM", "dataSaidaCampo = " + certifCanaBean.getDataSaidaCampo());
            Log.i("PMM", "dataSaidaUsina = " + certifCanaBean.getDataSaidaUsina());
            Log.i("PMM", "turno = " + certifCanaBean.getTurno());

        }


        CertifCanaBkpBean certifCanaBkpBean = new CertifCanaBkpBean();
        List compVCanaBkpList = certifCanaBkpBean.all();

        Log.i("PMM", "CertifCanaBkpBean");

        for (int i = 0; i < compVCanaBkpList.size(); i++) {

            certifCanaBkpBean = (CertifCanaBkpBean) compVCanaBkpList.get(i);
            Log.i("PMM", "idVCanaBkp = " + certifCanaBkpBean.getIdVCanaBkp());
            Log.i("PMM", "moto = " + certifCanaBkpBean.getMoto());
            Log.i("PMM", "carr1 = " + certifCanaBkpBean.getCarr1());
            Log.i("PMM", "carr2 = " + certifCanaBkpBean.getCarr2());
            Log.i("PMM", "carr3 = " + certifCanaBkpBean.getCarr3());
            Log.i("PMM", "dataSaidaCampo = " + certifCanaBkpBean.getDataSaidaCampo());
            Log.i("PMM", "noteiro = " + certifCanaBkpBean.getNoteiro());

        }

        CabecCheckListBean cabecCheckListBean = new CabecCheckListBean();
        List cabecList = cabecCheckListBean.all();

        Log.i("PMM", "CabecCheckList");

        for (int j = 0; j < cabecList.size(); j++) {

            cabecCheckListBean = (CabecCheckListBean) cabecList.get(j);
            Log.i("PMM", "IdCabecCheck = " + cabecCheckListBean.getIdCabecCheckList());
            Log.i("PMM", "EquipCabecCheckList = " + cabecCheckListBean.getEquipCabecCheckList());
            Log.i("PMM", "DtCabecCheckList = " + cabecCheckListBean.getDtCabecCheckList());
            Log.i("PMM", "FuncCabecCheckList = " + cabecCheckListBean.getFuncCabecCheckList());
            Log.i("PMM", "TurnoCabecCheckList = " + cabecCheckListBean.getTurnoCabecCheckList());
            Log.i("PMM", "StatusCabecCheckList = " + cabecCheckListBean.getStatusCabecCheckList());
            Log.i("PMM", "QtdeItemCabecCheckList = " + cabecCheckListBean.getQtdeItemCabecCheckList());
            Log.i("PMM", "DtCabecCheckList = " + cabecCheckListBean.getDtCabecCheckList());

        }

        RespCheckListBean respCheckListBean = new RespCheckListBean();
        List respItemList = respCheckListBean.all();

        Log.i("PMM", "RespItemCheckList");

        for (int j = 0; j < respItemList.size(); j++) {

            respCheckListBean = (RespCheckListBean) respItemList.get(j);
            Log.i("PMM", "IdItemCheckList = " + respCheckListBean.getIdItemCheckList());
            Log.i("PMM", "IdItItemCheckList = " + respCheckListBean.getIdItItemCheckList());
            Log.i("PMM", "IdCabecItemCheckList = " + respCheckListBean.getIdCabecItemCheckList());
            Log.i("PMM", "OpcaoItemCheckList = " + respCheckListBean.getOpcaoItemCheckList());

        }

        Log.i("PMM", "versão = " + ECMContext.versaoAplic);

    }

}
