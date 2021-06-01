package br.com.usinasantafe.ecm.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.ecm.ECMContext;
import br.com.usinasantafe.ecm.R;
import br.com.usinasantafe.ecm.model.bean.estaticas.MotoMecBean;
import br.com.usinasantafe.ecm.util.ConexaoWeb;
import br.com.usinasantafe.ecm.util.Tempo;

public class ListaParadaActivity extends ActivityGeneric {

    private ListView paradaListView;
    private ECMContext ecmContext;
    private List<MotoMecBean> paradaList;
    private int posicao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_parada);

        ecmContext = (ECMContext) getApplication();

        Button buttonRetMenuParada = (Button) findViewById(R.id.buttonRetMenuParada);

        ArrayList<String> itens = new ArrayList<String>();
        paradaList = ecmContext.getMotoMecCTR().paradaList();
        for(MotoMecBean motoMecBean : paradaList){
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
                MotoMecBean motoMecBean = paradaList.get(posicao);
                ecmContext.getMotoMecCTR().setMotoMecBean(motoMecBean);

                if (ecmContext.getConfigCTR().getConfig().getDtUltApontConfig().equals(Tempo.getInstance().dataComHora())) {
                    Toast.makeText(ListaParadaActivity.this, "POR FAVOR! ESPERE 1 MINUTO PARA REALIZAR UM NOVO APONTAMENTO.",
                            Toast.LENGTH_LONG).show();
                }
                else {

                    if (motoMecBean.getCodFuncaoOperMotoMec() == 1) {

                        AlertDialog.Builder alerta = new AlertDialog.Builder(ListaParadaActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("FOI DADO ENTRADA NA ATIVIDADE: " + motoMecBean.getDescrOperMotoMec());

                        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Long statusCon;
                                ConexaoWeb conexaoWeb = new ConexaoWeb();
                                if (conexaoWeb.verificaConexao(ListaParadaActivity.this)) {
                                    statusCon = 1L;
                                } else {
                                    statusCon = 0L;
                                }
                                ecmContext.getMotoMecCTR().insApontMM(getLongitude(), getLatitude(), statusCon);
                                paradaListView.setSelection(posicao + 1);
                            }
                        });

                        alerta.show();

                    } else if (motoMecBean.getCodFuncaoOperMotoMec() == 11) { //DESENGATE

                        if (ecmContext.getMotoMecCTR().hasElemCarreta()) {

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

                    } else if (motoMecBean.getCodFuncaoOperMotoMec() == 12) { //ENGATE

                        if (!ecmContext.getMotoMecCTR().hasElemCarreta()) {

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

            }

        });

        buttonRetMenuParada.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (ecmContext.getConfigCTR().getConfig().getDtUltApontConfig().equals(Tempo.getInstance().dataComHora())) {
                    Toast.makeText(ListaParadaActivity.this, "POR FAVOR! ESPERE 1 MINUTO PARA REALIZAR UM NOVO APONTAMENTO.",
                            Toast.LENGTH_LONG).show();
                }
                else {
                    Long statusCon;
                    ConexaoWeb conexaoWeb = new ConexaoWeb();
                    if (conexaoWeb.verificaConexao(ListaParadaActivity.this)) {
                        statusCon = 1L;
                    } else {
                        statusCon = 0L;
                    }

                    ecmContext.getMotoMecCTR().setAtivApont(ecmContext.getConfigCTR().getConfig().getAtivConfig());
                    ecmContext.getMotoMecCTR().insApontMM(getLongitude(), getLatitude(), statusCon);
//                    ecmContext.getMotoMecCTR().insVoltaTrab(getLongitude(), getLatitude(), statusCon);

                    Intent it = new Intent(ListaParadaActivity.this, MenuMotoMecActivity.class);
                    startActivity(it);
                    finish();
                }
            }

        });


    }

    public void onBackPressed()  {
    }

}
