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

import br.com.usinasantafe.ecm.model.bean.variaveis.CertifCanaBkpBean;
import br.com.usinasantafe.ecm.util.ConexaoWeb;
import br.com.usinasantafe.ecm.util.AtualDadosServ;
import br.com.usinasantafe.ecm.model.bean.variaveis.BoletimBean;

public class MenuCertifActivity extends ActivityGeneric {

    private ProgressDialog progressBar;
    private ListView menuCertifListView;
    private ECMContext ecmContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_certif);

        ecmContext = (ECMContext) getApplication();

        listarMenu();

        Button buttonRetMenuInicialApont = (Button) findViewById(R.id.buttonRetMenuInicialApont);

        buttonRetMenuInicialApont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent(MenuCertifActivity.this, MenuMotoMecActivity.class);
                startActivity(it);
                finish();

            }
        });


    }

    public void listarMenu() {

        ArrayList<String> itens = new ArrayList<String>();

        itens.add("APONTAMENTO");
        itens.add("ATUALIZAR");
        itens.add("LOG VIAGEM");
        itens.add("LOG BOLETIM");

        AdapterList adapterList = new AdapterList(this, itens);
        menuCertifListView = (ListView) findViewById(R.id.listViewMenuInicialApont);
        menuCertifListView.setAdapter(adapterList);

        menuCertifListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @SuppressWarnings("rawtypes")
            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                TextView textView = v.findViewById(R.id.textViewItemList);
                String text = textView.getText().toString();

                if (text.equals("APONTAMENTO")) {

                    if (ecmContext.getCECCTR().verPreCECAberto()) {

                        if (ecmContext.getCECCTR().verDataCertif()) {

                            Intent it = new Intent(MenuCertifActivity.this, AtivOSActivity.class);
                            startActivity(it);
                            finish();

                        } else {

                            AlertDialog.Builder alerta = new AlertDialog.Builder(MenuCertifActivity.this);
                            alerta.setTitle("ATENÇÃO");
                            alerta.setMessage("É NECESSÁRIO A INSERÇÃO DO HORARIO DE SAÍDA DA USINA E/OU DE CHEGADA AO CAMPO.");
                            alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent it = new Intent(MenuCertifActivity.this, MenuMotoMecActivity.class);
                                    startActivity(it);
                                    finish();
                                }
                            });

                            alerta.show();

                        }
                    }
                    else {

                        AlertDialog.Builder alerta = new AlertDialog.Builder(MenuCertifActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("É NECESSÁRIO A INSERÇÃO DO HORARIO DE SAÍDA DA USINA E/OU DE CHEGADA AO CAMPO.");
                        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent it = new Intent(MenuCertifActivity.this, MenuMotoMecActivity.class);
                                startActivity(it);
                                finish();
                            }
                        });

                        alerta.show();


                    }

                } else if (text.equals("ATUALIZAR")) {

                    ConexaoWeb conexaoWeb = new ConexaoWeb();

                    if (conexaoWeb.verificaConexao(MenuCertifActivity.this)) {

                        progressBar = new ProgressDialog(v.getContext());
                        progressBar.setCancelable(true);
                        progressBar.setMessage("ATUALIZANDO ...");
                        progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                        progressBar.setProgress(0);
                        progressBar.setMax(100);
                        progressBar.show();

                        ecmContext.getConfigCTR().atualTodasTabelas(MenuCertifActivity.this, progressBar);

                    } else {
                        AlertDialog.Builder alerta = new AlertDialog.Builder(MenuCertifActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.");
                        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                        alerta.show();
                    }

                } else if (text.equals("LOG VIAGEM")) {

                    CertifCanaBkpBean certifCanaBkpBean = new CertifCanaBkpBean();
                    int qtdeCompVCanaBean = certifCanaBkpBean.count();

                    if (qtdeCompVCanaBean > 0) {
                        Intent it = new Intent(MenuCertifActivity.this, CertificadoBKPActivity.class);
                        startActivity(it);
                        finish();
                    } else {
                        AlertDialog.Builder alerta = new AlertDialog.Builder(MenuCertifActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("NÃO TEM NENHUMA VIAGEM SALVA NA BASE DE DADOS.");
                        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        alerta.show();
                    }

                } else if (text.equals("LOG BOLETIM")) {

                    BoletimBean boletimBean = new BoletimBean();
                    int qtdeBoletim = boletimBean.count();

                    if (qtdeBoletim > 0) {
                        Intent it = new Intent(MenuCertifActivity.this, BoletimBKPActivity.class);
                        startActivity(it);
                        finish();
                    } else {
                        AlertDialog.Builder alerta = new AlertDialog.Builder(MenuCertifActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("NÃO TEM NENHUM BOLETIM SALVO NA BASE DE DADOS.");
                        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
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
