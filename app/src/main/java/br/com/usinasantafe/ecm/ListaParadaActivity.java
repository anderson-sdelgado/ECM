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

import br.com.usinasantafe.ecm.model.bean.estaticas.MotoMecBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.CarretaUtilBean;

public class ListaParadaActivity extends ActivityGeneric {

    private ListView paradaListView;
    private ECMContext ecmContext;
    private List paradaList;
    private MotoMecBean motoMecBean;
    private int posicao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_parada);

        ecmContext = (ECMContext) getApplication();

        Button buttonRetMenuParada = (Button) findViewById(R.id.buttonRetMenuParada);

        paradaList = ecmContext.getMotoMecCTR().getParadaList();

        ArrayList<String> itens = new ArrayList<String>();

        for(int i = 0; i < paradaList.size(); i++){
            motoMecBean = (MotoMecBean) paradaList.get(i);
            itens.add(motoMecBean.getDescrOperMotoMec());
        }

        AdapterList adapterList = new AdapterList(this, itens);
        paradaListView = (ListView) findViewById(R.id.listViewMotParada);
        paradaListView.setAdapter(adapterList);

        paradaListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                posicao = position;

                motoMecBean = (MotoMecBean) paradaList.get(posicao);

                if(motoMecBean.getCodFuncaoOperMotoMec() == 1){

                    AlertDialog.Builder alerta = new AlertDialog.Builder(ListaParadaActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("FOI DADO ENTRADA NA ATIVIDADE: " + motoMecBean.getDescrOperMotoMec());

                    alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ecmContext.getMotoMecCTR().salvaMotoMec(motoMecBean.getCodOperMotoMec());
                            paradaListView.setSelection(posicao + 1);
                        }
                    });

                    alerta.show();

                }
                else if(motoMecBean.getCodFuncaoOperMotoMec() == 11) { //DESENGATE

                    CarretaUtilBean carretaUtilBean = new CarretaUtilBean();

                    if (carretaUtilBean.hasElements()) {

                        AlertDialog.Builder alerta = new AlertDialog.Builder(ListaParadaActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("DESEJA REALMENTE DESENGATAR AS CARRETAS?");

                        alerta.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                ecmContext.setVerPosTela(6);
                                Intent it = new Intent(ListaParadaActivity.this, DesengCarretaActivity.class);
                                MotoMecBean motoMec = (MotoMecBean) paradaList.get(posicao);
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
                else if(motoMecBean.getCodFuncaoOperMotoMec() == 12){ //ENGATE

                    CarretaUtilBean carretaUtilBean = new CarretaUtilBean();

                    if(!carretaUtilBean.hasElements()){

                        AlertDialog.Builder alerta = new AlertDialog.Builder(ListaParadaActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("DESEJA REALMENTE ENGATAR AS CARRETAS?");

                        alerta.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ecmContext.setVerPosTela(7);
                                Intent it = new Intent(ListaParadaActivity.this, MsgNumCarretaActivity.class);
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

        buttonRetMenuParada.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ecmContext.getMotoMecCTR().salvaMotoMec(ecmContext.getMotoMecCTR().getVoltaTrabalho());
                Intent it = new Intent(ListaParadaActivity.this, MenuMotoMecActivity.class);
                startActivity(it);
                finish();
            }

        });


    }

    public void onBackPressed()  {
    }

}
