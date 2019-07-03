package br.com.usinasantafe.ecm;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

import br.com.usinasantafe.ecm.to.tb.estaticas.MotoristaTO;

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


                if(!editTextPadrao.getText().toString().equals("")){

                    MotoristaTO motoristaTO = new MotoristaTO();

                    if(motoristaTO.exists("codMotorista", Long.parseLong(editTextPadrao.getText().toString()))){

                        CabecalhoTO cabecalhoTO = new CabecalhoTO();
                        List infBoletimTOList = cabecalhoTO.all();
                        cabecalhoTO = (CabecalhoTO) infBoletimTOList.get(0);

                        switch(ecmContext.getNumCarreta()){
                            case 0:
                                cabecalhoTO.setOpCam(motoristaTO.getCodMotorista());
                                break;
                            case 1:
                                cabecalhoTO.setOpCarr1(motoristaTO.getCodMotorista());
                                break;
                            case 2:
                                cabecalhoTO.setOpCarr2(motoristaTO.getCodMotorista());
                                break;
                            case 3:
                                cabecalhoTO.setOpCarr3(motoristaTO.getCodMotorista());
                                break;
                        }

                        cabecalhoTO.update();

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
