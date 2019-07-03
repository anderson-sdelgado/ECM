package br.com.usinasantafe.ecm;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

import br.com.usinasantafe.ecm.to.tb.estaticas.MotoristaTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.CabecalhoTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.ConfigTO;

public class MotoristaActivity extends ActivityGeneric {

    private ECMContext ecmContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motorista);

        ecmContext = (ECMContext) getApplication();

        Button buttonOkMotorista = (Button) findViewById(R.id.buttonOkPadrao);
        Button buttonCancMotorista = (Button) findViewById(R.id.buttonCancPadrao);

        buttonOkMotorista.setOnClickListener(new View.OnClickListener() {
            @SuppressWarnings("rawtypes")
            @Override
            public void onClick(View v) {

                if (!editTextPadrao.getText().toString().equals("")) {

                    MotoristaTO motoristaTO = new MotoristaTO();
                    List motoristaList = motoristaTO.get("codMotorista", Long.parseLong(editTextPadrao.getText().toString()));

                    if (motoristaList.size() > 0) {

                        ConfigTO configTO = new ConfigTO();
                        List configList = configTO.all();
                        configTO = (ConfigTO) configList.get(0);
                        configList.clear();

                        configTO.setCrachaMotoConfig(Long.parseLong(editTextPadrao.getText().toString()));
                        configTO.update();

                        Intent it = new Intent(MotoristaActivity.this, CaminhaoTurnoActivity.class);
                        startActivity(it);
                        finish();

                    }
                    else{

                        AlertDialog.Builder alerta = new AlertDialog.Builder(MotoristaActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("MATRICULA INCORRETA! POR FAVOR, DIGITE NOVAMENTE SEU CRACHÁ.");

                        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                        alerta.show();

                    }


                }
                else{

                    AlertDialog.Builder alerta = new AlertDialog.Builder(MotoristaActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("POR FAVOR, DIGITE O SEU CRACHÁ.");

                    alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    alerta.show();

                }

            }

        });

        buttonCancMotorista.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (editTextPadrao.getText().toString().length() > 0) {
                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
                } else {
                    Intent it = new Intent(MotoristaActivity.this, PrincipalActivity.class);
                    startActivity(it);
                    finish();
                }
            }
        });

    }

    public void onBackPressed()  {
    }

}
