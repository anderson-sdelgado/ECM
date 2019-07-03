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

import br.com.usinasantafe.ecm.bo.ManipDadosEnvio;
import br.com.usinasantafe.ecm.bo.ManipDadosVerif;
import br.com.usinasantafe.ecm.bo.Tempo;
import br.com.usinasantafe.ecm.pst.EspecificaPesquisa;
import br.com.usinasantafe.ecm.to.tb.estaticas.MotoMecTO;
import br.com.usinasantafe.ecm.to.tb.estaticas.MotoristaTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.CarretaEngDesengTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.CompVCanaBkpTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.CompVCanaTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.ConfigTO;

public class MenuMotoMecActivity extends ActivityGeneric {

    private ListView menuMotoMecListView;
    private ECMContext ecmContext;
    private TextView textViewMotorista;
    private TextView textViewCarreta;
    private TextView textViewUltimaViagem;
    private ProgressDialog progressBar;
    private List menuMotoMecList;
    private ConfigTO configTO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_moto_mec);

        ecmContext = (ECMContext) getApplication();

        Button buttonRetMotoMec = (Button) findViewById(R.id.buttonRetMotoMec);
        textViewMotorista = (TextView) findViewById(R.id.textViewMotorista);
        textViewCarreta = (TextView) findViewById(R.id.textViewCarreta);
        textViewUltimaViagem = (TextView) findViewById(R.id.textViewUltimaViagem);

        configTO = new ConfigTO();
        List configList = configTO.all();
        configTO = (ConfigTO) configList.get(0);
        configList.clear();

//        CabecalhoTO cabecalhoTO = new CabecalhoTO();
//        List lTurno = cabecalhoTO.all();
//        cabecalhoTO = (CabecalhoTO) lTurno.get(0);
//
//        MotoMecTO motoMecBDpesq = new MotoMecTO();
//        motoMecBDpesq.setCargoMotoMec(cabecalhoTO.getTipoAtiv());
//        motoMecBDpesq.setTipoMotoMec((long) 1);
//
//        listarMenu(motoMecBDpesq);

        listarMenu();

        buttonRetMotoMec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent(MenuMotoMecActivity.this, PrincipalActivity.class);
                startActivity(it);
                finish();

            }
        });

    }

    public void listarMenu() {

        ArrayList listaPesq = new ArrayList();
        String carreta = "";

        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("cargoMotoMec");
        pesquisa.setValor(1L);
        listaPesq.add(pesquisa);

        EspecificaPesquisa pesquisa2 = new EspecificaPesquisa();
        pesquisa2.setCampo("tipoMotoMec");
        pesquisa2.setValor(1L);
        listaPesq.add(pesquisa2);

        MotoMecTO motoMecTO = new MotoMecTO();
        menuMotoMecList = motoMecTO.getAndOrderBy(listaPesq, "posicaoMotoMec", true);

        ArrayList<String> itens = new ArrayList<String>();

//        CabecalhoTO cabecalhoTO = new CabecalhoTO();
//        List lTurno = cabecalhoTO.all();
//        cabecalhoTO = (CabecalhoTO) lTurno.get(0);


        MotoristaTO motoristaTO = new MotoristaTO();
        List motoristaList = motoristaTO.get("codMotorista", configTO.getCrachaMotoConfig());
        motoristaTO = (MotoristaTO) motoristaList.get(0);
        motoristaList.clear();

        textViewMotorista.setText(configTO.getCrachaMotoConfig() + " - " + motoristaTO.getNomeMotorista());

        CarretaEngDesengTO carretaEngDesengTO = new CarretaEngDesengTO();
        List listCarreta = carretaEngDesengTO.all();

        if (listCarreta.size() == 0) {
            textViewCarreta.setText("CARRETA(S): ");
        } else if (listCarreta.size() == 1) {
            carretaEngDesengTO = (CarretaEngDesengTO) listCarreta.get(0);
            textViewCarreta.setText("CARRETA(S): " + carretaEngDesengTO.getNumCarreta());
        } else if (listCarreta.size() == 2) {
            carreta = "CARRETA(S): ";
            carretaEngDesengTO = (CarretaEngDesengTO) listCarreta.get(0);
            carreta = carreta + carretaEngDesengTO.getNumCarreta();
            carretaEngDesengTO = (CarretaEngDesengTO) listCarreta.get(1);
            carreta = carreta + " - " + carretaEngDesengTO.getNumCarreta();
            textViewCarreta.setText(carreta);
        } else if (listCarreta.size() == 3) {
            carreta = "CARRETA(S): ";
            carretaEngDesengTO = (CarretaEngDesengTO) listCarreta.get(0);
            carreta = carreta + carretaEngDesengTO.getNumCarreta();
            carretaEngDesengTO = (CarretaEngDesengTO) listCarreta.get(1);
            carreta = carreta + " - " + carretaEngDesengTO.getNumCarreta();
            carretaEngDesengTO = (CarretaEngDesengTO) listCarreta.get(2);
            carreta = carreta + " - " + carretaEngDesengTO.getNumCarreta();
            textViewCarreta.setText(carreta);
        } else {
            carreta = "CARRETA(S): ";
            carretaEngDesengTO = (CarretaEngDesengTO) listCarreta.get(0);
            carreta = carreta + carretaEngDesengTO.getNumCarreta();
            carretaEngDesengTO = (CarretaEngDesengTO) listCarreta.get(1);
            carreta = carreta + " - " + carretaEngDesengTO.getNumCarreta();
            carretaEngDesengTO = (CarretaEngDesengTO) listCarreta.get(2);
            carreta = carreta + " - " + carretaEngDesengTO.getNumCarreta();
            carretaEngDesengTO = (CarretaEngDesengTO) listCarreta.get(3);
            carreta = carreta + " - " + carretaEngDesengTO.getNumCarreta();
            textViewCarreta.setText(carreta);
        }


        CompVCanaBkpTO compVCanaBkpTO = new CompVCanaBkpTO();
        int qtdeCompVCanaTO = compVCanaBkpTO.count();

        if (qtdeCompVCanaTO == 0) {
            textViewUltimaViagem.setText("NÃO POSSUE CARREGAMENTOS");
        } else {
            List lista = compVCanaBkpTO.all();
            compVCanaBkpTO = (CompVCanaBkpTO) lista.get(qtdeCompVCanaTO - 1);
            textViewUltimaViagem.setText("ULT. VIAGEM: " + Tempo.getInstance().dataHoraCTZ(compVCanaBkpTO.getDataSaidaCampo()));
        }


        for (int i = 0; i < menuMotoMecList.size(); i++) {
            motoMecTO = (MotoMecTO) menuMotoMecList.get(i);
            itens.add(motoMecTO.getNomeMotoMec());
        }

        AdapterList adapterList = new AdapterList(this, itens);
        menuMotoMecListView = (ListView) findViewById(R.id.listViewMotoMec);
        menuMotoMecListView.setAdapter(adapterList);

        menuMotoMecListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                final int posicao = position;

                MotoMecTO motoMecBD = (MotoMecTO) menuMotoMecList.get(position);

                if (motoMecBD.getFuncaoMotoMec() == 1) {  // ATIVIDADES NORMAIS

                    AlertDialog.Builder alerta = new AlertDialog.Builder(MenuMotoMecActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("FOI DADO ENTRADA NA ATIVIDADE: " + motoMecBD.getNomeMotoMec());

                    ecmContext.getApontMotoMecTO().setOpcor(motoMecBD.getOpcorMotoMec());
                    ManipDadosEnvio.getInstance().salvaMotoMec(ecmContext.getApontMotoMecTO());

                    alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            menuMotoMecListView.setSelection(posicao + 1);
                        }
                    });

                    alerta.show();

                } else if (motoMecBD.getFuncaoMotoMec() == 5) {  // MOTIVO DE PARADA

                    ecmContext.setCargoMotomec(motoMecBD.getCargoMotoMec());
                    ecmContext.setLugarMotivoParada(1L);

                    Intent it = new Intent(MenuMotoMecActivity.this, MotivoParadaActivity.class);
                    startActivity(it);
                    finish();

                } else if (motoMecBD.getFuncaoMotoMec() == 4) { // CERTIFICADO


                    Intent it = new Intent(MenuMotoMecActivity.this, MenuInicialApontActivity.class);
                    startActivity(it);
                    finish();

                } else if (motoMecBD.getFuncaoMotoMec() == 8) { // TROCAR MOTORISTA

                    ecmContext.setTelaAltMoto(3);
                    Intent it = new Intent(MenuMotoMecActivity.this, MotoristaActivity.class);
                    startActivity(it);
                    finish();

                } else if (motoMecBD.getFuncaoMotoMec() == 2) { // SAIDA DA USINA

                    CompVCanaTO compVCanaTO = new CompVCanaTO();
                    List compVCanaList = compVCanaTO.get("status", 1L);

                    if (compVCanaList.size() > 0) {

                        compVCanaList.clear();

                        ecmContext.getApontMotoMecTO().setOpcor(motoMecBD.getOpcorMotoMec());

                        Intent it = new Intent(MenuMotoMecActivity.this, FrenteActivity.class);
                        startActivity(it);
                        finish();

                    } else {

                        String mensagem = "O HORÁRIO DE SAÍDA DA USINA JÁ FOI INSERIDO ANTERIORMENTE. " +
                                "POR FAVOR TERMINE DE FAZER O APONTAMENTO OU REENVIE OS APONTAMENTOS JÁ PRONTOS.";

                        AlertDialog.Builder alerta = new AlertDialog.Builder(MenuMotoMecActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage(mensagem);

                        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                menuMotoMecListView.setSelection(posicao + 1);
                            }
                        });
                        alerta.show();

                    }


                } else if (motoMecBD.getFuncaoMotoMec() == 3) { // CHEGADA CAMPO

                    String mensagem = "";

                    CompVCanaTO compVCanaTO = new CompVCanaTO();
                    List compVCanaList = compVCanaTO.get("status", 1L);

                    String dataSaidaUsina = "";
                    String dataChegCampo = "";
                    if (compVCanaList.size() > 0) {

                    }

                    if (compVCanaList.size() == 0) {

                        mensagem = "É NECESSÁRIO A INSERÇÃO DO HORÁRIO DE SAÍDA DA USINA.";

                    } else {

                        compVCanaTO = (CompVCanaTO) compVCanaList.get(0);
                        dataChegCampo = compVCanaTO.getDataChegCampo();

                        if (dataChegCampo.equals("")) {

                            compVCanaTO.setDataChegCampo(Tempo.getInstance().datahora());
                            compVCanaTO.update();

                            mensagem = "FOI DADO ENTRADA NA ATIVIDADE: " + motoMecBD.getNomeMotoMec();

                            ecmContext.getApontMotoMecTO().setOpcor(motoMecBD.getOpcorMotoMec());

                            ManipDadosEnvio.getInstance().salvaMotoMec(ecmContext.getApontMotoMecTO());

                        } else {
                            mensagem = "O HORÁRIO DE CHEGADA AO CAMPO JÁ FOI INSERIDO ANTERIORMENTE. " +
                                    "POR FAVOR TERMINEI DE FAZER O APONTAMENTO OU REENVIE OS APONTAMENTOS JÁ PRONTOS.";
                        }

                        compVCanaList.clear();

                    }

                    AlertDialog.Builder alerta = new AlertDialog.Builder(MenuMotoMecActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage(mensagem);

                    alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            menuMotoMecListView.setSelection(posicao + 1);
                        }
                    });

                    alerta.show();

                } else if (motoMecBD.getFuncaoMotoMec() == 10) { // PESAGEM

                    ecmContext.getApontMotoMecTO().setOpcor(motoMecBD.getOpcorMotoMec());
                    ManipDadosEnvio.getInstance().salvaMotoMec(ecmContext.getApontMotoMecTO());

                    progressBar = new ProgressDialog(v.getContext());
                    progressBar.setCancelable(true);
                    progressBar.setMessage("Buscando o boletim...");
                    progressBar.show();

                    CompVCanaTO compVCanaTO = new CompVCanaTO();

                    if (!compVCanaTO.hasElements()) {

                        ManipDadosVerif.getInstance().verDados(ecmContext.getApontMotoMecTO().getVeic().toString(), "BoletimTO",
                                MenuMotoMecActivity.this, BoletimActivity.class, progressBar);

                    } else {

                        ManipDadosVerif.getInstance().verDados(ecmContext.getApontMotoMecTO().getVeic().toString(), "BoletimTOViagem",
                                MenuMotoMecActivity.this, BoletimActivity.class, progressBar);

                    }

                } else if (motoMecBD.getFuncaoMotoMec() == 11) { // DESENGATE

                    CarretaEngDesengTO carretaEngDesengTO = new CarretaEngDesengTO();
                    List listaCarreta = carretaEngDesengTO.all();

                    if (listaCarreta.size() > 0) {

                        AlertDialog.Builder alerta = new AlertDialog.Builder(MenuMotoMecActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("DESEJA REALMENTE DESENGATAR AS CARRETAS?");

                        alerta.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Intent it = new Intent(MenuMotoMecActivity.this, DesengateCarretaActivity.class);
                                MotoMecTO motoMec = (MotoMecTO) menuMotoMecList.get(posicao);
                                ecmContext.getApontMotoMecTO().setOpcor(motoMec.getOpcorMotoMec());
                                if (motoMec.getCodigoMotoMec() == 27) {
                                    ecmContext.getApontMotoMecTO().setTipoEngDeseng((long) 1);
                                } else if (motoMec.getCodigoMotoMec() == 37) {
                                    ecmContext.getApontMotoMecTO().setTipoEngDeseng((long) 3);
                                }
                                startActivity(it);

                            }
                        });

                        alerta.setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                        alerta.show();

                    }

                } else if (motoMecBD.getFuncaoMotoMec() == 12) { // ENGATE

                    CarretaEngDesengTO carretaEngDesengTO = new CarretaEngDesengTO();
                    List listaCarreta = carretaEngDesengTO.all();

                    if (listaCarreta.size() == 0) {

                        AlertDialog.Builder alerta = new AlertDialog.Builder(MenuMotoMecActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("DESEJA REALMENTE ENGATAR AS CARRETAS?");

                        alerta.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                MotoMecTO motoMec = (MotoMecTO) menuMotoMecList.get(posicao);
                                ecmContext.getApontMotoMecTO().setOpcor(motoMec.getOpcorMotoMec());
                                if (motoMec.getCodigoMotoMec() == 26) {
                                    ecmContext.getApontMotoMecTO().setTipoEngDeseng((long) 2);
                                } else if (motoMec.getCodigoMotoMec() == 36) {
                                    ecmContext.getApontMotoMecTO().setTipoEngDeseng((long) 4);
                                }

                                Intent it = new Intent(MenuMotoMecActivity.this, MsgCarretaEngateActivity.class);
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

                } else if (motoMecBD.getFuncaoMotoMec() == 13) { // HODOMETRO

                    ecmContext.getApontMotoMecTO().setOpcor(motoMecBD.getOpcorMotoMec());
                    ManipDadosEnvio.getInstance().salvaMotoMec(ecmContext.getApontMotoMecTO());
                    Intent it = new Intent(MenuMotoMecActivity.this, HodometroActivity.class);
                    startActivity(it);
                    finish();
                }

            }

        });

    }

    public ListView getMenuMotoMecListView() {
        return this.menuMotoMecListView;
    }

    public void onBackPressed() {
    }

}
