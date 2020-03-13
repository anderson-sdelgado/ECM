package br.com.usinasantafe.ecm;

//import android.support.v7.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.com.usinasantafe.ecm.util.ConexaoWeb;
import br.com.usinasantafe.ecm.util.VerifDadosServ;

public class AtivOSActivity extends ActivityGeneric {

    private ECMContext ecmContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ativ_os);

        ecmContext = (ECMContext) getApplication();

        Button buttonOkAtivOS = (Button) findViewById(R.id.buttonOkPadrao);
        Button buttonCancAtivOS = (Button) findViewById(R.id.buttonCancPadrao);

        buttonOkAtivOS.setOnClickListener(new View.OnClickListener() {

            @SuppressWarnings("rawtypes")
            @Override
            public void onClick(View v) {

                if (!editTextPadrao.getText().toString().equals("")) {

                    if (ecmContext.getCECCTR().verAtivOS(Long.parseLong(editTextPadrao.getText().toString()))) {

                        ecmContext.getCECCTR().setAtivOS(Long.parseLong(editTextPadrao.getText().toString()));

                        Intent it = new Intent(AtivOSActivity.this, MsgAtivOSActivity.class);
                        startActivity(it);
                        finish();

                    } else {

                        ConexaoWeb conexaoWeb = new ConexaoWeb();
                        if (conexaoWeb.verificaConexao(AtivOSActivity.this)) {

                            VerifDadosServ.getInstance().verDados(editTextPadrao.getText().toString(), "RAtivOSBean",
                                    AtivOSActivity.this, MsgAtivOSActivity.class);

                        } else {
                            AlertDialog.Builder alerta = new AlertDialog.Builder(AtivOSActivity.this);
                            alerta.setTitle("ATENÇÃO");
                            alerta.setMessage("FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVER COM SINAL.");
                            alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
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

        buttonCancAtivOS.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (editTextPadrao.getText().toString().length() > 0) {
                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
                } else {
                    Intent it = new Intent(AtivOSActivity.this, MenuCertifActivity.class);
                    startActivity(it);
                    finish();
                }
            }
        });


    }

    public void onBackPressed()  {
    }

}
