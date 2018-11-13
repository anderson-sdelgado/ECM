package br.com.usinasantafe.ecm;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.ecm.bo.ManipDadosEnvio;
import br.com.usinasantafe.ecm.pst.EspecificaPesquisa;
import br.com.usinasantafe.ecm.pst.MotoMecPST;
import br.com.usinasantafe.ecm.to.tb.estaticas.MotoMecTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.CarretaEngDesengTO;

public class MotivoParadaActivity extends ActivityGeneric {

    private ListView lista;
    private ECMContext ecmContext;
    private Long lugarMotPar;
    private List listaMM;
    private MotoMecTO motoMecBD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motivo_parada);

        ecmContext = (ECMContext) getApplication();

        lugarMotPar = ecmContext.getLugarMotivoParada();

        Button buttonRetMenuParada = (Button) findViewById(R.id.buttonRetMenuParada);

        motoMecBD = new MotoMecTO();
        ArrayList listaPesq = new ArrayList();

        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("cargoMotoMec");
        pesquisa.setValor(ecmContext.getCargoMotomec());
        listaPesq.add(pesquisa);

        EspecificaPesquisa pesquisa2 = new EspecificaPesquisa();
        pesquisa2.setCampo("tipoMotoMec");
        pesquisa2.setValor((long) 2);
        listaPesq.add(pesquisa2);

        MotoMecPST motoMecPST = new MotoMecPST();
        listaMM  = motoMecPST.get(listaPesq);

        ArrayList<String> itens = new ArrayList<String>();

        for(int i = 0; i < listaMM.size(); i++){
            motoMecBD = (MotoMecTO) listaMM.get(i);
            if(!motoMecBD.getNomeMotoMec().equals("VOLTAR AO TRABALHO")) {
                itens.add(motoMecBD.getNomeMotoMec());
            }
        }

        AdapterList adapterList = new AdapterList(this, itens);
        lista = (ListView) findViewById(R.id.listViewMotParada);
        lista.setAdapter(adapterList);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {
                // TODO Auto-generated method stub

                final int posicao = position;

                MotoMecTO motoMecBD = (MotoMecTO) listaMM.get(posicao);
                ecmContext.getApontMotoMecTO().setTipoFuncao(motoMecBD.getCargoMotoMec());

                if(motoMecBD.getFuncaoMotoMec() == 1){

                    AlertDialog.Builder alerta = new AlertDialog.Builder(MotivoParadaActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("FOI DADO ENTRADA NA ATIVIDADE: " + motoMecBD.getNomeMotoMec());

                    ecmContext.getApontMotoMecTO().setOpcor(motoMecBD.getOpcorMotoMec());
                    ManipDadosEnvio.getInstance().salvaMotoMec(ecmContext.getApontMotoMecTO());

                    alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            lista.setSelection(posicao + 1);
                        }
                    });

                    alerta.show();

                }
                else if (motoMecBD.getFuncaoMotoMec() == 9) //VOLTAR AO TRABALHO
                {

                    Intent it = new Intent(MotivoParadaActivity.this, MenuMotoMecActivity.class);
                    startActivity(it);
                    finish();

                    ecmContext.getApontMotoMecTO().setOpcor(motoMecBD.getOpcorMotoMec());
                    ManipDadosEnvio.getInstance().salvaMotoMec(ecmContext.getApontMotoMecTO());

                }
                else if(motoMecBD.getFuncaoMotoMec() == 11) //DESENGATE
                {

                    CarretaEngDesengTO carretaEngDesengTO = new CarretaEngDesengTO();
                    List listaCarreta = carretaEngDesengTO.all();

                    if(listaCarreta.size() > 0){

                        AlertDialog.Builder alerta = new AlertDialog.Builder(MotivoParadaActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("DESEJA REALMENTE DESENGATAR AS CARRETAS?");

                        alerta.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Intent it = new Intent(MotivoParadaActivity.this, DesengateCarretaActivity.class);
                                MotoMecTO motoMec = (MotoMecTO) listaMM.get(posicao);
                                ecmContext.getApontMotoMecTO().setOpcor(motoMec.getOpcorMotoMec());
                                ecmContext.getApontMotoMecTO().setTipoEngDeseng((long) 5);
                                startActivity(it);
                                finish();

                            }
                        });

                        alerta.setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // TODO Auto-generated method stub
                            }
                        });

                        alerta.show();

                    }

                }
                else if(motoMecBD.getFuncaoMotoMec() == 12) //ENGATE
                {

                    CarretaEngDesengTO carretaEngDesengTO = new CarretaEngDesengTO();
                    List listaCarreta = carretaEngDesengTO.all();

                    if(listaCarreta.size() > 0){

                        AlertDialog.Builder alerta = new AlertDialog.Builder(MotivoParadaActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("DESEJA REALMENTE DESENGATAR AS CARRETAS?");

                        alerta.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Intent it = new Intent(MotivoParadaActivity.this, DesengateCarretaActivity.class);
                                MotoMecTO motoMec = (MotoMecTO) listaMM.get(posicao);
                                ecmContext.getApontMotoMecTO().setOpcor(motoMec.getOpcorMotoMec());
                                ecmContext.getApontMotoMecTO().setTipoEngDeseng((long) 6);
                                startActivity(it);
                                finish();

                            }
                        });

                        alerta.setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // TODO Auto-generated method stub
                            }
                        });

                        alerta.show();

                    }

                }
                else{

                    AlertDialog.Builder alerta = new AlertDialog.Builder(MotivoParadaActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("FOI DADO ENTRADA NA ATIVIDADE: " + motoMecBD.getNomeMotoMec());

                    ecmContext.getApontMotoMecTO().setOpcor(motoMecBD.getOpcorMotoMec());
                    ManipDadosEnvio.getInstance().salvaMotoMec(ecmContext.getApontMotoMecTO());

                    alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            lista.setSelection(posicao + 1);
                        }
                    });

                    alerta.show();

                }

            }

        });

        buttonRetMenuParada.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                for(int i = 0; i < listaMM.size(); i++){
                    motoMecBD = (MotoMecTO) listaMM.get(i);
                    if(motoMecBD.getNomeMotoMec().equals("VOLTAR AO TRABALHO")) {
                        ecmContext.getApontMotoMecTO().setOpcor(motoMecBD.getOpcorMotoMec());
                    }
                }

                ManipDadosEnvio.getInstance().salvaMotoMec(ecmContext.getApontMotoMecTO());
                Intent it = new Intent(MotivoParadaActivity.this, MenuMotoMecActivity.class);
                startActivity(it);
                finish();

            }

        });


    }

    public void onBackPressed()  {
    }

}
