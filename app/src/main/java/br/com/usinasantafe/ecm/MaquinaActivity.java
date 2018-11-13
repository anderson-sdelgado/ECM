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
import br.com.usinasantafe.ecm.to.tb.variaveis.InfBoletimTO;

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
                // TODO Auto-generated method stub

                if(!editTextPadrao.getText().toString().equals("")){
                    CarregadeiraTO carregadeiraTOBD = new CarregadeiraTO();
                    List lista = carregadeiraTOBD.get("idCarregadeira", Long.parseLong(editTextPadrao.getText().toString()));

                    if(lista.size() > 0){

                        InfBoletimTO infBoletimTO = new InfBoletimTO();
                        List infBoletimTOList = infBoletimTO.all();
                        infBoletimTO = (InfBoletimTO) infBoletimTOList.get(0);

                        switch(ecmContext.getNumCarreta()){
                            case 0:
                                infBoletimTO.setMaqCam(carregadeiraTOBD.getIdCarregadeira());
                                break;
                            case 1:
                                infBoletimTO.setMaqCarr1(carregadeiraTOBD.getIdCarregadeira());
                                break;
                            case 2:
                                infBoletimTO.setMaqCarr2(carregadeiraTOBD.getIdCarregadeira());
                                break;
                            case 3:
                                infBoletimTO.setMaqCarr3(carregadeiraTOBD.getIdCarregadeira());
                                break;
                        }

                        infBoletimTO.update();
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
                                // TODO Auto-generated method stub
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
                // TODO Auto-generated method stub
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
