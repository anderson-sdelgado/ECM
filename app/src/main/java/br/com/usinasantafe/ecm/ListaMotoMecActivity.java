package br.com.usinasantafe.ecm;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.ecm.model.bean.variaveis.CertifCanaBkpBean;
import br.com.usinasantafe.ecm.util.Tempo;
import br.com.usinasantafe.ecm.model.bean.estaticas.MotoMecBean;
import br.com.usinasantafe.ecm.model.bean.estaticas.ColabBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.CarretaEngDesengBean;

public class ListaMotoMecActivity extends ActivityGeneric {

    private ListView motoMecListView;
    private ECMContext ecmContext;
    private TextView textViewMotorista;
    private TextView textViewCarreta;
    private TextView textViewUltimaViagem;
    private ProgressDialog progressBar;
    private List motoMecList;
    private MotoMecBean motoMecBean;
    private int posicao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_moto_mec);

        ecmContext = (ECMContext) getApplication();

        Button buttonRetMotoMec = (Button) findViewById(R.id.buttonRetMotoMec);
        Button buttonParadaMotoMec = (Button) findViewById(R.id.buttonParadaMotoMec);
        textViewMotorista = (TextView) findViewById(R.id.textViewMotorista);
        textViewCarreta = (TextView) findViewById(R.id.textViewCarreta);
        textViewUltimaViagem = (TextView) findViewById(R.id.textViewUltimaViagem);

        listarMenu();

        buttonRetMotoMec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(ListaMotoMecActivity.this, MenuInicialActivity.class);
                startActivity(it);
                finish();
            }
        });

        buttonParadaMotoMec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                    ecmContext.setCargoMotomec(motoMecBean.getCargoMotoMec());
//                    ecmContext.setLugarMotivoParada(1L);

                Intent it = new Intent(ListaMotoMecActivity.this, ListaParadaActivity.class);
                startActivity(it);
                finish();
            }
        });

    }

    public void listarMenu() {

        ColabBean colabBean = ecmContext.getConfigCTR().getColab();
        textViewMotorista.setText(colabBean.getMatricColab() + " - " + colabBean.getNomeColab());

        CarretaEngDesengBean carretaEngDesengBean = new CarretaEngDesengBean();
        List listCarreta = carretaEngDesengBean.all();

        String carreta = "";
        if (listCarreta.size() == 0) {
            textViewCarreta.setText("CARRETA(S): ");
        } else if (listCarreta.size() == 1) {
            carretaEngDesengBean = (CarretaEngDesengBean) listCarreta.get(0);
            textViewCarreta.setText("CARRETA(S): " + carretaEngDesengBean.getNumCarreta());
        } else if (listCarreta.size() == 2) {
            carreta = "CARRETA(S): ";
            carretaEngDesengBean = (CarretaEngDesengBean) listCarreta.get(0);
            carreta = carreta + carretaEngDesengBean.getNumCarreta();
            carretaEngDesengBean = (CarretaEngDesengBean) listCarreta.get(1);
            carreta = carreta + " - " + carretaEngDesengBean.getNumCarreta();
            textViewCarreta.setText(carreta);
        } else if (listCarreta.size() == 3) {
            carreta = "CARRETA(S): ";
            carretaEngDesengBean = (CarretaEngDesengBean) listCarreta.get(0);
            carreta = carreta + carretaEngDesengBean.getNumCarreta();
            carretaEngDesengBean = (CarretaEngDesengBean) listCarreta.get(1);
            carreta = carreta + " - " + carretaEngDesengBean.getNumCarreta();
            carretaEngDesengBean = (CarretaEngDesengBean) listCarreta.get(2);
            carreta = carreta + " - " + carretaEngDesengBean.getNumCarreta();
            textViewCarreta.setText(carreta);
        } else {
            carreta = "CARRETA(S): ";
            carretaEngDesengBean = (CarretaEngDesengBean) listCarreta.get(0);
            carreta = carreta + carretaEngDesengBean.getNumCarreta();
            carretaEngDesengBean = (CarretaEngDesengBean) listCarreta.get(1);
            carreta = carreta + " - " + carretaEngDesengBean.getNumCarreta();
            carretaEngDesengBean = (CarretaEngDesengBean) listCarreta.get(2);
            carreta = carreta + " - " + carretaEngDesengBean.getNumCarreta();
            carretaEngDesengBean = (CarretaEngDesengBean) listCarreta.get(3);
            carreta = carreta + " - " + carretaEngDesengBean.getNumCarreta();
            textViewCarreta.setText(carreta);
        }


        CertifCanaBkpBean certifCanaBkpBean = new CertifCanaBkpBean();
        int qtdeCompVCanaBean = certifCanaBkpBean.count();

        if (qtdeCompVCanaBean == 0) {
            textViewUltimaViagem.setText("NÃO POSSUE CARREGAMENTOS");
        } else {
            List lista = certifCanaBkpBean.all();
            certifCanaBkpBean = (CertifCanaBkpBean) lista.get(qtdeCompVCanaBean - 1);
            textViewUltimaViagem.setText("ULT. VIAGEM: " + Tempo.getInstance().dataHoraCTZ(certifCanaBkpBean.getDataSaidaCampo()));
        }

        ArrayList<String> motoMecArrayList = new ArrayList<String>();
        motoMecList = ecmContext.getMotoMecCTR().getMotoMecList();
        for (int i = 0; i < motoMecList.size(); i++) {
            MotoMecBean motoMecBean = (MotoMecBean) motoMecList.get(i);
            motoMecArrayList.add(motoMecBean.getDescrOperMotoMec());
        }

        AdapterList adapterList = new AdapterList(this, motoMecArrayList);
        motoMecListView = (ListView) findViewById(R.id.motoMecListView);
        motoMecListView.setAdapter(adapterList);

        motoMecListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                posicao = position;
                motoMecBean = (MotoMecBean) motoMecList.get(position);

                if (motoMecBean.getCodFuncaoOperMotoMec() == 1) {  // ATIVIDADES NORMAIS

                    AlertDialog.Builder alerta = new AlertDialog.Builder(ListaMotoMecActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("FOI DADO ENTRADA NA ATIVIDADE: " + motoMecBean.getDescrOperMotoMec());

                    alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ecmContext.getMotoMecCTR().salvaMotoMec(motoMecBean.getCodOperMotoMec());
                            motoMecListView.setSelection(posicao + 1);
                        }
                    });

                    alerta.show();

                } else if (motoMecBean.getCodFuncaoOperMotoMec() == 4) { // CERTIFICADO

                    ecmContext.setVerPosTela(5);
                    Intent it = new Intent(ListaMotoMecActivity.this, MenuInicialApontActivity.class);
                    startActivity(it);
                    finish();

                } else if (motoMecBean.getCodFuncaoOperMotoMec() == 8) { // TROCAR MOTORISTA

                    ecmContext.setVerPosTela(2);
                    Intent it = new Intent(ListaMotoMecActivity.this, MotoristaActivity.class);
                    startActivity(it);
                    finish();

                } else if (motoMecBean.getCodFuncaoOperMotoMec() == 2) { // SAIDA DA USINA

                    if (ecmContext.getCertifCanaCTR().verCertifAberto()) {

                        String mensagem = "O HORÁRIO DE SAÍDA DA USINA JÁ FOI INSERIDO ANTERIORMENTE. " +
                                "POR FAVOR TERMINE DE FAZER O APONTAMENTO OU REENVIE OS APONTAMENTOS JÁ PRONTOS.";

                        AlertDialog.Builder alerta = new AlertDialog.Builder(ListaMotoMecActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage(mensagem);

                        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                motoMecListView.setSelection(posicao + 1);
                            }
                        });
                        alerta.show();

                    } else {

                        Intent it = new Intent(ListaMotoMecActivity.this, FrenteActivity.class);
                        startActivity(it);
                        finish();

                    }


                } else if (motoMecBean.getCodFuncaoOperMotoMec() == 3) { // CHEGADA CAMPO

                    String mensagem = "";


                    if (ecmContext.getCertifCanaCTR().verCertifAberto()) {
                        mensagem = "É NECESSÁRIO A INSERÇÃO DO HORÁRIO DE SAÍDA DA USINA.";
                    } else {
                        if (ecmContext.getCertifCanaCTR().getDataChegCampo().equals("")) {
                            mensagem = "FOI DADO ENTRADA NA ATIVIDADE: " + motoMecBean.getDescrOperMotoMec();
                        } else {
                            mensagem = "O HORÁRIO DE CHEGADA AO CAMPO JÁ FOI INSERIDO ANTERIORMENTE. " +
                                    "POR FAVOR TERMINEI DE FAZER O APONTAMENTO OU REENVIE OS APONTAMENTOS JÁ PRONTOS.";
                        }
                    }

                    AlertDialog.Builder alerta = new AlertDialog.Builder(ListaMotoMecActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage(mensagem);

                    alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (ecmContext.getCertifCanaCTR().getDataChegCampo().equals("")) {
                                ecmContext.getCertifCanaCTR().setDataChegCampo();
                                ecmContext.getMotoMecCTR().salvaMotoMec(motoMecBean.getCodOperMotoMec());
                            }
                            motoMecListView.setSelection(posicao + 1);
                        }
                    });

                    alerta.show();

                } else if (motoMecBean.getCodFuncaoOperMotoMec() == 10) { // PESAGEM

                    ecmContext.getMotoMecCTR().salvaMotoMec(motoMecBean.getCodOperMotoMec());

                    progressBar = new ProgressDialog(v.getContext());
                    progressBar.setCancelable(true);
                    progressBar.setMessage("Buscando o boletim...");
                    progressBar.show();

//                    if (!ecmContext.getCertifCanaCTR().verCertifAberto()) {
//                        ManipDadosVerif.getInstance().verDados(ecmContext.getApontMotoMecBean().getCodEquip().toString(), "BoletimBean",
//                                ListaMotoMecActivity.this, BoletimActivity.class, progressBar);
//                    } else {
//                        ManipDadosVerif.getInstance().verDados(ecmContext.getApontMotoMecBean().getCodEquip().toString(), "BoletimTOViagem",
//                                ListaMotoMecActivity.this, BoletimActivity.class, progressBar);
//                    }

                } else if (motoMecBean.getCodFuncaoOperMotoMec() == 11) { // DESENGATE

                    CarretaEngDesengBean carretaEngDesengBean = new CarretaEngDesengBean();

                    if (carretaEngDesengBean.hasElements()) {

                        AlertDialog.Builder alerta = new AlertDialog.Builder(ListaMotoMecActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("DESEJA REALMENTE DESENGATAR AS CARRETAS?");

                        alerta.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ecmContext.setVerPosTela(3);
                                Intent it = new Intent(ListaMotoMecActivity.this, DesengateCarretaActivity.class);
                                startActivity(it);
                                finish();
                            }
                        });

                        alerta.setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                        alerta.show();

                    }

                } else if (motoMecBean.getCodFuncaoOperMotoMec() == 12) { // ENGATE

                    CarretaEngDesengBean carretaEngDesengBean = new CarretaEngDesengBean();

                    if (!carretaEngDesengBean.hasElements()) {

                        AlertDialog.Builder alerta = new AlertDialog.Builder(ListaMotoMecActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("DESEJA REALMENTE ENGATAR AS CARRETAS?");

                        alerta.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ecmContext.setVerPosTela(4);
                                Intent it = new Intent(ListaMotoMecActivity.this, MsgNumCarretaActivity.class);
                                startActivity(it);
                                finish();
                            }
                        });

                        alerta.setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                        alerta.show();

                    }

                } else if (motoMecBean.getCodFuncaoOperMotoMec() == 13) { // HODOMETRO

                    ecmContext.getMotoMecCTR().salvaMotoMec(motoMecBean.getCodOperMotoMec());
                    Intent it = new Intent(ListaMotoMecActivity.this, HodometroActivity.class);
                    startActivity(it);
                    finish();
                }

            }

        });

    }

//    public ListView getMotoMecListView() {
//        return this.motoMecListView;
//    }

    public void onBackPressed() {
    }

}
