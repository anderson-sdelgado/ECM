package br.com.usinasantafe.ecm;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

import br.com.usinasantafe.ecm.bo.Tempo;
import br.com.usinasantafe.ecm.to.tb.estaticas.OperadorCarregTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.InfBoletimTO;

public class OperadorActivity extends ActivityGeneric {

    private ECMContext ecmContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operador);

        ecmContext = (ECMContext) getApplication();
        Button buttonOkOperador = (Button) findViewById(R.id.buttonOkPadrao);
        Button buttonCancOperador = (Button) findViewById(R.id.buttonCancPadrao);

        buttonOkOperador.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                if(!editTextPadrao.getText().toString().equals("")){

                    OperadorCarregTO operadorCarregTOBD = new OperadorCarregTO();

                    if(operadorCarregTOBD.exists("codOperadorCarreg", Long.parseLong(editTextPadrao.getText().toString()))){

                        InfBoletimTO infBoletimTO = new InfBoletimTO();
                        List infBoletimTOList = infBoletimTO.all();
                        infBoletimTO = (InfBoletimTO) infBoletimTOList.get(0);

                        switch(ecmContext.getNumCarreta()){
                            case 0:
                                infBoletimTO.setOpCam(operadorCarregTOBD.getCodOperadorCarreg());
                                break;
                            case 1:
                                infBoletimTO.setOpCarr1(operadorCarregTOBD.getCodOperadorCarreg());
                                break;
                            case 2:
                                infBoletimTO.setOpCarr2(operadorCarregTOBD.getCodOperadorCarreg());
                                break;
                            case 3:
                                infBoletimTO.setOpCarr3(operadorCarregTOBD.getCodOperadorCarreg());
                                break;
                        }

                        infBoletimTO.update();

                        Intent it = new Intent(OperadorActivity.this, MsgNumCarretaActivity.class);
                        startActivity(it);
                        finish();

                    }
                    else{

                        AlertDialog.Builder alerta = new AlertDialog.Builder(OperadorActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("OPERADOR CARREGADEIRA INEXISTENTE NA BASE DE DADOS.");

                        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // TODO Auto-generated method stub
                                editTextPadrao.setText("");
                            }
                        });
                        alerta.show();
                    }
                }

            }
        });

        buttonCancOperador.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(editTextPadrao.getText().toString().length() > 0){
                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
                }
                else{
                    Intent it = new Intent(OperadorActivity.this, MaquinaActivity.class);
                    startActivity(it);
                    finish();
                }
            }
        });


    }

    public void onBackPressed()  {
    }

}
