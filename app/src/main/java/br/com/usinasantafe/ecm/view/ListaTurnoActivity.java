package br.com.usinasantafe.ecm.view;

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

import br.com.usinasantafe.ecm.ECMContext;
import br.com.usinasantafe.ecm.R;
import br.com.usinasantafe.ecm.model.bean.estaticas.TurnoBean;
import br.com.usinasantafe.ecm.util.ConexaoWeb;
import br.com.usinasantafe.ecm.util.Tempo;

public class ListaTurnoActivity extends ActivityGeneric {

    private ListView turnoListView;
    private ECMContext ecmContext;
    private List turnoList;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_turno);

        ecmContext = (ECMContext) getApplication();
        Button buttonRetListaTurno = (Button) findViewById(R.id.buttonRetListaTurno);
        Button buttonAtualTurno = (Button) findViewById(R.id.buttonAtualTurno);

        buttonAtualTurno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alerta = new AlertDialog.Builder(  ListaTurnoActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("DESEJA REALMENTE ATUALIZAR BASE DE DADOS?");
                alerta.setNegativeButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        ConexaoWeb conexaoWeb = new ConexaoWeb();

                        if (conexaoWeb.verificaConexao(ListaTurnoActivity.this)) {

                            progressBar = new ProgressDialog(ListaTurnoActivity.this);
                            progressBar.setCancelable(true);
                            progressBar.setMessage("ATUALIZANDO ...");
                            progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                            progressBar.setProgress(0);
                            progressBar.setMax(100);
                            progressBar.show();

                            ecmContext.getMotoMecCTR().atualDadosTurno(ListaTurnoActivity.this, ListaTurnoActivity.class, progressBar);

                        } else {

                            AlertDialog.Builder alerta = new AlertDialog.Builder( ListaTurnoActivity.this);
                            alerta.setTitle("ATENÇÃO");
                            alerta.setMessage("FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.");
                            alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });

                            alerta.show();

                        }


                    }
                });

                alerta.setPositiveButton("NÃO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                alerta.show();

            }

        });

        TurnoBean turnoBean = new TurnoBean();
        turnoList = turnoBean.get("codTurno", ecmContext.getConfigCTR().getEquip().getCodTurno());

        ArrayList<String> itens = new ArrayList<String>();

        for(int i = 0; i < turnoList.size(); i++){
            turnoBean = (TurnoBean) turnoList.get(i);
            itens.add(turnoBean.getDescTurno());
        }

        AdapterList adapterList = new AdapterList(this, itens);
        turnoListView = (ListView) findViewById(R.id.turnoListView);
        turnoListView.setAdapter(adapterList);

        turnoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                TurnoBean turnoBean = (TurnoBean) turnoList.get(position);
                turnoList.clear();

                ecmContext.getMotoMecCTR().setTurnoBol(turnoBean.getIdTurno());

                if(Tempo.getInstance().verDthrServ(ecmContext.getConfigCTR().getConfig().getDtServConfig())){
                    ecmContext.getConfigCTR().setDifDthrConfig(0L);
                    Intent it = new Intent(ListaTurnoActivity.this, OSActivity.class);
                    startActivity(it);
                    finish();
                }
                else{
                    if(ecmContext.getConfigCTR().getConfig().getDifDthrConfig() == 0){
                        ecmContext.setContDataHora(1);
                        Intent it = new Intent(ListaTurnoActivity.this, MsgDataHoraActivity.class);
                        startActivity(it);
                        finish();
                    }
                    else{
                        Intent it = new Intent(ListaTurnoActivity.this, OSActivity.class);
                        startActivity(it);
                        finish();
                    }
                }

            }

        });

        buttonRetListaTurno.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent it = new Intent(ListaTurnoActivity.this, CaminhaoActivity.class);
                startActivity(it);
                finish();
            }

        });

    }

    public void onBackPressed() {
    }

}
