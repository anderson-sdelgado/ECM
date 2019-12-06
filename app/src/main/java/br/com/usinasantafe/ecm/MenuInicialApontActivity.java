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

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.ecm.model.bean.variaveis.CertifCanaBkpBean;
import br.com.usinasantafe.ecm.util.ConexaoWeb;
import br.com.usinasantafe.ecm.util.ManipDadosReceb;
import br.com.usinasantafe.ecm.model.bean.variaveis.BoletimBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.CertifCanaBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.ConfigBean;

public class MenuInicialApontActivity extends ActivityGeneric {

    private ProgressDialog progressBar;
    private ListView lista;
    private ConfigBean configBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_inicial_apont);

        configBean = new ConfigBean();
        List configList = configBean.all();
        configBean = (ConfigBean) configList.get(0);
        configList.clear();

        listarMenu();

        Button buttonRetMenuInicialApont = (Button) findViewById(R.id.buttonRetMenuInicialApont);

        buttonRetMenuInicialApont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent(MenuInicialApontActivity.this, ListaMotoMecActivity.class);
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
        lista = (ListView) findViewById(R.id.listViewMenuInicialApont);
        lista.setAdapter(adapterList);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @SuppressWarnings("rawtypes")
            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                if (position == 0) {

                    CertifCanaBean certifCanaBean = new CertifCanaBean();
                    List compVCanaList = certifCanaBean.get("status", 1L);

                    if (compVCanaList.size() > 0) {

                        certifCanaBean = (CertifCanaBean) compVCanaList.get(0);
                        compVCanaList.clear();

                        if ((!certifCanaBean.getDataSaidaUsina().equals("")) && (!certifCanaBean.getDataChegCampo().equals(""))) {

                            Intent it = new Intent(MenuInicialApontActivity.this, AtividadeOSActivity.class);
                            startActivity(it);
                            finish();

                        } else {

                            AlertDialog.Builder alerta = new AlertDialog.Builder(MenuInicialApontActivity.this);
                            alerta.setTitle("ATENÇÃO");
                            alerta.setMessage("É NECESSÁRIO A INSERÇÃO DO HORARIO DE SAÍDA DA USINA E/OU DE CHEGADA AO CAMPO.");
                            alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent it = new Intent(MenuInicialApontActivity.this, ListaMotoMecActivity.class);
                                    startActivity(it);
                                    finish();
                                }
                            });

                            alerta.show();

                        }
                    }
                    else {

                        AlertDialog.Builder alerta = new AlertDialog.Builder(MenuInicialApontActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("É NECESSÁRIO A INSERÇÃO DO HORARIO DE SAÍDA DA USINA E/OU DE CHEGADA AO CAMPO.");
                        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent it = new Intent(MenuInicialApontActivity.this, ListaMotoMecActivity.class);
                                startActivity(it);
                                finish();
                            }
                        });

                        alerta.show();

//                        AlertDialog.Builder alerta = new AlertDialog.Builder(MenuInicialApontActivity.this);
//                        alerta.setTitle("ATENÇÃO");
//                        alerta.setMessage("POR FAVOR TERMINEI DE FAZER O APONTAMENTO OU REENVIE OS APONTAMENTOS JÁ PRONTOS.");
//
//                        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                Intent it = new Intent(MenuInicialApontActivity.this, ListaMotoMecActivity.class);
//                                startActivity(it);
//                                finish();
//                            }
//                        });
//                        alerta.show();

                    }

                } else if (position == 1) {

                    ConexaoWeb conexaoWeb = new ConexaoWeb();

                    if (conexaoWeb.verificaConexao(MenuInicialApontActivity.this)) {

                        progressBar = new ProgressDialog(v.getContext());
                        progressBar.setCancelable(true);
                        progressBar.setMessage("ATUALIZANDO ...");
                        progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                        progressBar.setProgress(0);
                        progressBar.setMax(100);
                        progressBar.show();
                        ManipDadosReceb.getInstance().atualizarBD(progressBar);
                        ManipDadosReceb.getInstance().setContext(MenuInicialApontActivity.this);

                    } else {
                        AlertDialog.Builder alerta = new AlertDialog.Builder(MenuInicialApontActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.");
                        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                        alerta.show();
                    }

                } else if (position == 2) {

                    CertifCanaBkpBean certifCanaBkpBean = new CertifCanaBkpBean();
                    int qtdeCompVCanaBean = certifCanaBkpBean.count();

                    if (qtdeCompVCanaBean > 0) {
                        Intent it = new Intent(MenuInicialApontActivity.this, BackupViagemCanaActivity.class);
                        startActivity(it);
                        finish();
                    } else {
                        AlertDialog.Builder alerta = new AlertDialog.Builder(MenuInicialApontActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("NÃO TEM NENHUMA VIAGEM SALVA NA BASE DE DADOS.");
                        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        alerta.show();
                    }

                } else if (position == 3) {

                    BoletimBean boletimBean = new BoletimBean();
                    int qtdeBoletim = boletimBean.count();

                    if (qtdeBoletim > 0) {
                        Intent it = new Intent(MenuInicialApontActivity.this, BackupBoletimActivity.class);
                        startActivity(it);
                        finish();
                    } else {
                        AlertDialog.Builder alerta = new AlertDialog.Builder(MenuInicialApontActivity.this);
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
