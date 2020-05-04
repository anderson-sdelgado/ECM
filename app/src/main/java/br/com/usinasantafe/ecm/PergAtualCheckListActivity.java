package br.com.usinasantafe.ecm;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.com.usinasantafe.ecm.util.ConexaoWeb;
import br.com.usinasantafe.ecm.util.VerifDadosServ;

public class PergAtualCheckListActivity extends AppCompatActivity {

    private ProgressDialog progressBar;
    private ECMContext ecmContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perg_atual_check_list);

        ecmContext = (ECMContext) getApplication();

        Button buttonSimAtualCL = (Button) findViewById(R.id.buttonSimAtualCL);
        Button buttonNaoAtualCL = (Button) findViewById(R.id.buttonNaoAtualCL);

        buttonNaoAtualCL.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent it = new Intent(  PergAtualCheckListActivity.this, ItemCheckListActivity.class);
                startActivity(it);
                finish();

            }
        });

        buttonSimAtualCL.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                ConexaoWeb conexaoWeb = new ConexaoWeb();

                if (conexaoWeb.verificaConexao(PergAtualCheckListActivity.this)) {

                    progressBar = new ProgressDialog(PergAtualCheckListActivity.this);
                    progressBar.setCancelable(true);
                    progressBar.setMessage("Atualizando CheckList...");
                    progressBar.show();

                    VerifDadosServ.getInstance().verDados("", "CheckList"
                            , PergAtualCheckListActivity.this, ItemCheckListActivity.class, progressBar);

                } else {

                    AlertDialog.Builder alerta = new AlertDialog.Builder( PergAtualCheckListActivity.this);
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

    }
}
