package br.com.usinasantafe.ecm;

//import android.support.v7.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

import br.com.usinasantafe.ecm.to.tb.estaticas.CarregadeiraTO;

public class MaquinaActivity extends ActivityGeneric {

    private ECMContext ecmContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maquina);

        ecmContext = (ECMContext) getApplication();
        Button buttonOkMaquina = (Button) findViewById(R.id.buttonOkPadrao);
        Button buttonCancMaquina = (Button) findViewById(R.id.buttonCancPadrao);

        buttonOkMaquina.setOnClickListener(new View.OnClickListener() {

            @SuppressWarnings("rawtypes")
            @Override
            public void onClick(View v) {


                if(!editTextPadrao.getText().toString().equals("")){
                    CarregadeiraTO carregadeiraTOBD = new CarregadeiraTO();
                    List lista = carregadeiraTOBD.get("idCarregadeira", Long.parseLong(editTextPadrao.getText().toString()));

                    if(lista.size() > 0){

                        CabecalhoTO cabecalhoTO = new CabecalhoTO();
                        List infBoletimTOList = cabecalhoTO.all();
                        cabecalhoTO = (CabecalhoTO) infBoletimTOList.get(0);

                        switch(ecmContext.getNumCarreta()){
                            case 0:
                                cabecalhoTO.setMaqCam(carregadeiraTOBD.getIdCarregadeira());
                                break;
                            case 1:
                                cabecalhoTO.setMaqCarr1(carregadeiraTOBD.getIdCarregadeira());
                                break;
                            case 2:
                                cabecalhoTO.setMaqCarr2(carregadeiraTOBD.getIdCarregadeira());
                                break;
                            case 3:
                                cabecalhoTO.setMaqCarr3(carregadeiraTOBD.getIdCarregadeira());
                                break;
                        }

                        cabecalhoTO.update();
                        Intent it = new Intent(MaquinaActivity.this, OperadorActivity.class);
                        startActivity(it);
                        finish();

                    }
                    else{
                        AlertDialog.Builder alerta = new AlertDialog.Builder(MaquinaActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("CARREGADEIRA INEXISTENTE NA BASE DE DADOS.");

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

        buttonCancMaquina.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(editTextPadrao.getText().toString().length() > 0){
                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
                }
                else{
                    Intent it = new Intent(MaquinaActivity.this, MsgLiberacaoActivity.class);
                    startActivity(it);
                    finish();
                }
            }
        });


    }

    public void onBackPressed()  {
    }

}
