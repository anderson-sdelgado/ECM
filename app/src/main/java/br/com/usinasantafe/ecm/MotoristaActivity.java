package br.com.usinasantafe.ecm;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

import br.com.usinasantafe.ecm.to.tb.estaticas.MotoristaTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.InfBoletimTO;

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
                // TODO Auto-generated method stub

                if (!editTextPadrao.getText().toString().equals("")) {

                    MotoristaTO motoristaBDPesq = new MotoristaTO();
                    List listaMotorista = motoristaBDPesq.get("codMotorista", Long.parseLong(editTextPadrao.getText().toString()));

                    if (listaMotorista.size() > 0) {

                        MotoristaTO motoristaBD = (MotoristaTO) listaMotorista.get(0);

                        InfBoletimTO infBoletimTO = new InfBoletimTO();
                        List lInfBol = infBoletimTO.all();
                        infBoletimTO = (InfBoletimTO) lInfBol.get(0);
                        infBoletimTO.setCodigoMoto(motoristaBD.getCodMotorista());
                        infBoletimTO.setNomeMoto(motoristaBD.getNomeMotorista());
                        infBoletimTO.setTipoAtiv(1L);
                        infBoletimTO.update();

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
                // TODO Auto-generated method stub
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
