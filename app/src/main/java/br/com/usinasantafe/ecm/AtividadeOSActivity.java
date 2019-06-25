package br.com.usinasantafe.ecm;

//import android.support.v7.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

import br.com.usinasantafe.ecm.bo.ConexaoWeb;
import br.com.usinasantafe.ecm.bo.ManipDadosVerif;
import br.com.usinasantafe.ecm.to.tb.estaticas.AtividadeOSTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.InfBoletimTO;

public class AtividadeOSActivity extends ActivityGeneric {

    private ECMContext ecmContext;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atividade_os);

        context = this;
        ecmContext = (ECMContext) getApplication();

        Button buttonOkAtivOS = (Button) findViewById(R.id.buttonOkPadrao);
        Button buttonCancAtivOS = (Button) findViewById(R.id.buttonCancPadrao);

        buttonOkAtivOS.setOnClickListener(new View.OnClickListener() {

            @SuppressWarnings("rawtypes")
            @Override
            public void onClick(View v) {

                if (!editTextPadrao.getText().toString().equals("")) {

                    AtividadeOSTO atividadeOsTOBD = new AtividadeOSTO();

                    if (atividadeOsTOBD.exists("codigoAtivOS", Long.parseLong(editTextPadrao.getText().toString()))) {

                        ecmContext.setCodigoAtivOS(Long.parseLong(editTextPadrao.getText().toString()));
                        Intent it = new Intent(AtividadeOSActivity.this, MsgAtividadeOSActivity.class);
                        startActivity(it);
                        finish();

                    } else {

                        ConexaoWeb conexaoWeb = new ConexaoWeb();

                        ecmContext.setCodigoAtivOS(Long.parseLong(editTextPadrao.getText().toString()));

                        if (conexaoWeb.verificaConexao(AtividadeOSActivity.this)) {

                            ManipDadosVerif.getInstance().verDados(editTextPadrao.getText().toString(), "AtividadeOSTO",
                                    AtividadeOSActivity.this, MsgAtividadeOSActivity.class);

                        } else {
                            AlertDialog.Builder alerta = new AlertDialog.Builder(AtividadeOSActivity.this);
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

                    InfBoletimTO infBoletimTO = new InfBoletimTO();
                    List lTurno = infBoletimTO.all();
                    infBoletimTO = (InfBoletimTO) lTurno.get(0);

                    if (infBoletimTO.getTipoAtiv() == 1) {
                        Intent it = new Intent(AtividadeOSActivity.this, MenuInicialApontActivity.class);
                        startActivity(it);
                        finish();
                    } else if (infBoletimTO.getTipoAtiv() == 2) {
                        Intent it = new Intent(AtividadeOSActivity.this, OSActivity.class);
                        startActivity(it);
                        finish();
                    }

                }
            }
        });


    }

    public void onBackPressed()  {
    }

}
