package br.com.usinasantafe.ecm;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.ecm.model.bean.estaticas.FuncBean;
import br.com.usinasantafe.ecm.model.bean.estaticas.MotoMecBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.CarretaBean;
import br.com.usinasantafe.ecm.util.ConexaoWeb;

public class MenuMotoMecActivity extends ActivityGeneric {

    private ListView motoMecListView;
    private ECMContext ecmContext;
    private TextView textViewMotorista;
    private TextView textViewCarreta;
    private TextView textViewUltimaViagem;
    private ProgressDialog progressBar;
    private List motoMecList;
    private int posicao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_moto_mec);

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
                AlertDialog.Builder alerta = new AlertDialog.Builder(MenuMotoMecActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("DESEJA REALMENTE ENCERRA O BOLETIM?");

                alerta.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ecmContext.setVerPosTela(8);
                        Intent it = new Intent(MenuMotoMecActivity.this, HodometroActivity.class);
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
        });

        buttonParadaMotoMec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MenuMotoMecActivity.this, ListaParadaActivity.class);
                startActivity(it);
                finish();
            }
        });

    }

    public void listarMenu() {

        FuncBean funcBean = ecmContext.getMotoMecCTR().getMatricNomeFunc();
        textViewMotorista.setText(funcBean.getMatricFunc() + " - " + funcBean.getNomeFunc());
        textViewCarreta.setText(ecmContext.getMotoMecCTR().getDescrCarreta());
        textViewUltimaViagem.setText(ecmContext.getMotoMecCTR().getDataSaidaUlt());

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
                MotoMecBean motoMecBean = (MotoMecBean) motoMecList.get(position);
                ecmContext.getMotoMecCTR().setMotoMecBean(motoMecBean);

                Log.i("ECM", "CodFuncaoOperMotoMec = " + motoMecBean.getCodFuncaoOperMotoMec());

                if (motoMecBean.getCodFuncaoOperMotoMec() == 1) {  // ATIVIDADES NORMAIS

                    AlertDialog.Builder alerta = new AlertDialog.Builder(MenuMotoMecActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("FOI DADO ENTRADA NA ATIVIDADE: " + motoMecBean.getDescrOperMotoMec());

                    alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            Long statusCon;
                            ConexaoWeb conexaoWeb = new ConexaoWeb();
                            if (conexaoWeb.verificaConexao(MenuMotoMecActivity.this)) {
                                statusCon = 1L;
                            }
                            else{
                                statusCon = 0L;
                            }

                            ecmContext.getMotoMecCTR().insApontMM(getLongitude(), getLatitude(), statusCon);
                            motoMecListView.setSelection(posicao + 1);

                        }
                    });

                    alerta.show();

                } else if (motoMecBean.getCodFuncaoOperMotoMec() == 4) { // CERTIFICADO

                    ecmContext.setVerPosTela(5);
                    Intent it = new Intent(MenuMotoMecActivity.this, MenuCertifActivity.class);
                    startActivity(it);
                    finish();

                } else if (motoMecBean.getCodFuncaoOperMotoMec() == 2) { // SAIDA DA USINA

                    if (ecmContext.getCECCTR().verPreCECAberto()) {

                        String mensagem = "O HORÁRIO DE SAÍDA DA USINA JÁ FOI INSERIDO ANTERIORMENTE. " +
                                "POR FAVOR TERMINE DE FAZER O APONTAMENTO OU REENVIE OS APONTAMENTOS JÁ PRONTOS.";

                        AlertDialog.Builder alerta = new AlertDialog.Builder(MenuMotoMecActivity.this);
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

                        ecmContext.setVerPosTela(2);
                        Intent it = new Intent(MenuMotoMecActivity.this, FrenteActivity.class);
                        startActivity(it);
                        finish();

                    }

                } else if (motoMecBean.getCodFuncaoOperMotoMec() == 3) { // CHEGADA CAMPO

                    String mensagem = "";

                    if (!ecmContext.getCECCTR().verPreCECAberto()) {
                        mensagem = "É NECESSÁRIO A INSERÇÃO DO HORÁRIO DE SAÍDA DA USINA.";
                    } else {
                        if (ecmContext.getCECCTR().getDataChegCampo().equals("")) {
                            mensagem = "FOI DADO ENTRADA NA ATIVIDADE: " + motoMecBean.getDescrOperMotoMec();
                        } else {
                            mensagem = "O HORÁRIO DE CHEGADA AO CAMPO JÁ FOI INSERIDO ANTERIORMENTE. " +
                                    "POR FAVOR TERMINEI DE FAZER O APONTAMENTO OU REENVIE OS APONTAMENTOS JÁ PRONTOS.";
                        }
                    }

                    AlertDialog.Builder alerta = new AlertDialog.Builder(MenuMotoMecActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage(mensagem);

                    alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            if (ecmContext.getCECCTR().verPreCECAberto()) {
                                if (ecmContext.getCECCTR().getDataChegCampo().equals("")) {
                                    ecmContext.getCECCTR().setDataChegCampo();

                                    Long statusCon;
                                    ConexaoWeb conexaoWeb = new ConexaoWeb();
                                    if (conexaoWeb.verificaConexao(MenuMotoMecActivity.this)) {
                                        statusCon = 1L;
                                    } else {
                                        statusCon = 0L;
                                    }
                                    ecmContext.getMotoMecCTR().insApontMM(getLongitude(), getLatitude(), statusCon);
                                }
                                motoMecListView.setSelection(posicao + 1);
                            }
                        }
                    });

                    alerta.show();

                } else if (motoMecBean.getCodFuncaoOperMotoMec() == 6) { // PESAGEM

                    Long statusCon;
                    ConexaoWeb conexaoWeb = new ConexaoWeb();
                    if (conexaoWeb.verificaConexao(MenuMotoMecActivity.this)) {
                        statusCon = 1L;
                    }
                    else{
                        statusCon = 0L;
                    }
                    ecmContext.getMotoMecCTR().insApontMM(getLongitude(), getLatitude(), statusCon);

                    progressBar = new ProgressDialog(v.getContext());
                    progressBar.setCancelable(true);
                    progressBar.setMessage("BUSCANDO BOLETIM...");
                    progressBar.show();

                    ecmContext.getCECCTR().delPreCECAberto();
                    ecmContext.getCECCTR().verCECServ(MenuMotoMecActivity.this, CECActivity.class, progressBar);

                } else if ((motoMecBean.getCodFuncaoOperMotoMec() == 8)
                        || (motoMecBean.getCodFuncaoOperMotoMec() == 19)){ // DESENGATE

                    CarretaBean carretaBean = new CarretaBean();

                    if (carretaBean.hasElements()) {

                        AlertDialog.Builder alerta = new AlertDialog.Builder(MenuMotoMecActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("DESEJA REALMENTE DESENGATAR AS CARRETAS?");

                        alerta.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ecmContext.setVerPosTela(3);
                                Intent it = new Intent(MenuMotoMecActivity.this, DesengCarretaActivity.class);
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

                } else if ((motoMecBean.getCodFuncaoOperMotoMec() == 9)
                        || (motoMecBean.getCodFuncaoOperMotoMec() == 20)) { // ENGATE

                    CarretaBean carretaBean = new CarretaBean();

                    if (!carretaBean.hasElements()) {

                        AlertDialog.Builder alerta = new AlertDialog.Builder(MenuMotoMecActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("DESEJA REALMENTE ENGATAR AS CARRETAS?");

                        alerta.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ecmContext.setVerPosTela(4);
                                Intent it = new Intent(MenuMotoMecActivity.this, MsgNumCarretaActivity.class);
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

                }

            }

        });

    }

    public void onBackPressed() {
    }

}
