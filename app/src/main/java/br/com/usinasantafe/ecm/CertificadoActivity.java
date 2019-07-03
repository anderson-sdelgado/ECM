package br.com.usinasantafe.ecm;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import br.com.usinasantafe.ecm.bo.ConexaoWeb;
import br.com.usinasantafe.ecm.bo.ManipDadosReceb;

public class CertificadoActivity extends ActivityGeneric {

    private ProgressDialog progressBar;
    private ListView certListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certificado);

        Button buttonRetornarCertificado = (Button) findViewById(R.id.buttonRetornarCertificado);

        buttonRetornarCertificado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent( CertificadoActivity.this, MsgAtividadeOSActivity.class);
                startActivity(it);
            }
        });

        ArrayList<String> itens = new ArrayList<String>();
        itens.add("APONTAMENTO");
        itens.add("ATUALIZAR");

        AdapterList adapterList = new AdapterList(this, itens);
        certListView = (ListView) findViewById(R.id.listViewCertificado);
        certListView.setAdapter(adapterList);

        certListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                if (position == 0) {
                    Intent it = new Intent(CertificadoActivity.this, CaminhaoActivity.class);
                    startActivity(it);
                    finish();
                }
                else if (position == 1) {

                    ConexaoWeb conexaoWeb = new ConexaoWeb();

                    if(conexaoWeb.verificaConexao(CertificadoActivity.this)){
                        progressBar = new ProgressDialog(v.getContext());
                        progressBar.setCancelable(true);
                        progressBar.setMessage("ATUALIZANDO ...");
                        progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                        progressBar.setProgress(0);
                        progressBar.setMax(100);
                        progressBar.show();
                        ManipDadosReceb.getInstance().atualizarBD(progressBar);
                        ManipDadosReceb.getInstance().setContext(CertificadoActivity.this);
                    }
                    else{
                        AlertDialog.Builder alerta = new AlertDialog.Builder(CertificadoActivity.this);
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
            }


        });


    }

    public void onBackPressed()  {
    }

}
