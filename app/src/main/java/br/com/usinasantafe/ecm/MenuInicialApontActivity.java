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

import br.com.usinasantafe.ecm.bo.ConexaoWeb;
import br.com.usinasantafe.ecm.bo.ManipDadosReceb;
import br.com.usinasantafe.ecm.to.tb.variaveis.BoletimTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.CompVCanaBkpTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.CompVCanaTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.ConfigTO;

public class MenuInicialApontActivity extends ActivityGeneric {

    private ProgressDialog progressBar;
    private ListView lista;
    private ConfigTO configTO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_inicial_apont);

        configTO = new ConfigTO();
        List configList = configTO.all();
        configTO = (ConfigTO) configList.get(0);
        configList.clear();

        listarMenu();

        Button buttonRetMenuInicialApont = (Button) findViewById(R.id.buttonRetMenuInicialApont);

        buttonRetMenuInicialApont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent(MenuInicialApontActivity.this, MenuMotoMecActivity.class);
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

                    CompVCanaTO compVCanaTO = new CompVCanaTO();
                    List compVCanaList = compVCanaTO.get("status", 1L);

                    if (compVCanaList.size() > 0) {

                        compVCanaTO = (CompVCanaTO) compVCanaList.get(0);
                        compVCanaList.clear();

                        if ((!compVCanaTO.getDataSaidaUsina().equals("")) && (!compVCanaTO.getDataChegCampo().equals(""))) {

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
                                    Intent it = new Intent(MenuInicialApontActivity.this, MenuMotoMecActivity.class);
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
                                Intent it = new Intent(MenuInicialApontActivity.this, MenuMotoMecActivity.class);
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
//                                Intent it = new Intent(MenuInicialApontActivity.this, MenuMotoMecActivity.class);
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

                    CompVCanaBkpTO compVCanaBkpTO = new CompVCanaBkpTO();
                    int qtdeCompVCanaTO = compVCanaBkpTO.count();

                    if (qtdeCompVCanaTO > 0) {
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

                    BoletimTO boletimTO = new BoletimTO();
                    int qtdeBoletim = boletimTO.count();

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
