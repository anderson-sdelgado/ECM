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

import br.com.usinasantafe.ecm.model.bean.estaticas.AtividadeBean;
import br.com.usinasantafe.ecm.util.ConexaoWeb;

public class ListaAtividadeActivity extends ActivityGeneric {

    private ListView ativListView;
    private ECMContext ecmContext;
    private ProgressDialog progressBar;
    private ArrayList ativArrayList;
    private Long nroOS = 0L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_atividade);

        ecmContext = (ECMContext) getApplication();

        Button buttonAtualAtividade = (Button) findViewById(R.id.buttonAtualAtividade);
        Button buttonRetAtividade = (Button) findViewById(R.id.buttonRetAtividade);
        TextView textViewTituloAtividade = (TextView) findViewById(R.id.textViewTituloAtividade);

        nroOS =  ecmContext.getConfigCTR().getConfig().getOsConfig();

        buttonAtualAtividade.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                ConexaoWeb conexaoWeb = new ConexaoWeb();

                if (conexaoWeb.verificaConexao(ListaAtividadeActivity.this)) {

                    progressBar = new ProgressDialog(v.getContext());
                    progressBar.setCancelable(true);
                    progressBar.setMessage("Atualizando Atividades...");
                    progressBar.show();

                    ecmContext.getMotoMecCTR().verAtiv( String.valueOf(nroOS), ListaAtividadeActivity.this, ListaAtividadeActivity.class, progressBar);

                } else {

                    AlertDialog.Builder alerta = new AlertDialog.Builder(ListaAtividadeActivity.this);
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

        buttonRetAtividade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(ListaAtividadeActivity.this, OSActivity.class);
                startActivity(it);
            }
        });

        if(ecmContext.getVerPosTela() == 1){
            textViewTituloAtividade.setText("ATIVIDADE PRINCIPAL");
        } else {
            textViewTituloAtividade.setText("ATIVIDADE");
        }

        ativArrayList = ecmContext.getMotoMecCTR().getAtivArrayList(nroOS);

        ArrayList<String> itens = new ArrayList<String>();
        for (int i = 0; i < ativArrayList.size(); i++) {
            AtividadeBean atividadeBean = (AtividadeBean) ativArrayList.get(i);
            itens.add(atividadeBean.getCodAtiv() + " - " + atividadeBean.getDescrAtiv());
        }

        AdapterList adapterList = new AdapterList(this, itens);
        ativListView = (ListView) findViewById(R.id.listAtividade);
        ativListView.setAdapter(adapterList);

        ativListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {
                // TODO Auto-generated method stub

                AtividadeBean atividadeBean = (AtividadeBean) ativArrayList.get(position);
                ativArrayList.clear();

                ecmContext.getConfigCTR().setAtivConfig(atividadeBean.getIdAtiv());

                if(ecmContext.getVerPosTela() == 2){

                    Long statusCon;
                    ConexaoWeb conexaoWeb = new ConexaoWeb();
                    if (conexaoWeb.verificaConexao(ListaAtividadeActivity.this)) {
                        statusCon = 1L;
                    }
                    else{
                        statusCon = 0L;
                    }

                    ecmContext.getMotoMecCTR().setAtivApont(atividadeBean.getIdAtiv());
                    ecmContext.getMotoMecCTR().insApontMM(getLongitude(), getLatitude(), statusCon);
                    ecmContext.setPosMenu(8);

                    Intent it = new Intent(ListaAtividadeActivity.this, MenuMotoMecActivity.class);
                    startActivity(it);
                    finish();

                }
                else{

                    ecmContext.getMotoMecCTR().setOSBol(ecmContext.getConfigCTR().getConfig().getOsConfig());
                    ecmContext.getMotoMecCTR().setAtivBol(atividadeBean.getIdAtiv());

                    Intent it = new Intent(ListaAtividadeActivity.this, HodometroActivity.class);
                    startActivity(it);
                    finish();

                }

            }

        });

    }

    public void onBackPressed() {
    }

}
