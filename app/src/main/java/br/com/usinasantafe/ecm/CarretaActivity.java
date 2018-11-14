package br.com.usinasantafe.ecm;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

import br.com.usinasantafe.ecm.to.tb.estaticas.CaminhaoTO;
import br.com.usinasantafe.ecm.to.tb.estaticas.CarretaTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.ConfiguracaoTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.InfBoletimTO;

public class CarretaActivity extends ActivityGeneric {

    private ECMContext ecmContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carreta);

        ecmContext = (ECMContext) getApplication();
        Button buttonOkCarreta = (Button) findViewById(R.id.buttonOkPadrao);
        Button buttonCancCarreta = (Button) findViewById(R.id.buttonCancPadrao);

        buttonOkCarreta.setOnClickListener(new View.OnClickListener() {
            @SuppressWarnings("rawtypes")
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                if(!editTextPadrao.getText().toString().equals("")){

                    ConfiguracaoTO configTO = new ConfiguracaoTO();
                    List listaConfig = configTO.all();
                    configTO = (ConfiguracaoTO) listaConfig.get(0);

                    CaminhaoTO caminhaoTOBDPesq = new CaminhaoTO();
                    List listaCam = caminhaoTOBDPesq.get("idCaminhao", configTO.getCodCamConfig());
                    CaminhaoTO caminhaoTOBD = (CaminhaoTO) listaCam.get(0);//

                    CarretaTO carretaTOBD = new CarretaTO();
                    List listaCarreta = carretaTOBD.get("idCarreta", Long.parseLong(editTextPadrao.getText().toString()));

                    if(listaCarreta.size() > 0){

                        carretaTOBD = (CarretaTO) listaCarreta.get(0);//

                        if(caminhaoTOBD.getTipoCaminhao() == 1){
                            if(carretaTOBD.getTipoCarreta() == 4){
                                if(ecmContext.getNumCarreta() == 1){
                                    InfBoletimTO infBoletimTO = new InfBoletimTO();
                                    List lTurno = infBoletimTO.all();
                                    infBoletimTO = (InfBoletimTO) lTurno.get(0);
                                    infBoletimTO.setCarr1(carretaTOBD.getIdCarreta());
                                    infBoletimTO.update();
                                    Intent it = new Intent(CarretaActivity.this, OSActivity.class);
                                    startActivity(it);
                                    finish();
                                }
                                else if(ecmContext.getNumCarreta() == 2){
                                    InfBoletimTO infBoletimTO = new InfBoletimTO();
                                    List lTurno = infBoletimTO.all();
                                    infBoletimTO = (InfBoletimTO) lTurno.get(0);
                                    if(infBoletimTO.getCarr1() != carretaTOBD.getIdCarreta()){
                                        infBoletimTO.setCarr2(carretaTOBD.getIdCarreta());
                                        infBoletimTO.update();
                                        Intent it = new Intent(CarretaActivity.this, OSActivity.class);
                                        startActivity(it);
                                        finish();
                                    }
                                    else{
                                        AlertDialog.Builder alerta = new AlertDialog.Builder(CarretaActivity.this);
                                        alerta.setTitle("ATENÇÃO");
                                        alerta.setMessage("ESSA CARRETA JÁ FOI INSERIDA. VERIFIQUE NOVAMENTE A NUMERAÇÃO DA CARRETA.");
                                        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                editTextPadrao.setText("");
                                            }
                                        });
                                        alerta.show();
                                    }
                                }
                                else if(ecmContext.getNumCarreta() == 3){
                                    InfBoletimTO infBoletimTO = new InfBoletimTO();
                                    List lTurno = infBoletimTO.all();
                                    infBoletimTO = (InfBoletimTO) lTurno.get(0);
                                    if((infBoletimTO.getCarr1() != carretaTOBD.getIdCarreta())
                                            && (infBoletimTO.getCarr2() != carretaTOBD.getIdCarreta())){
                                        infBoletimTO.setCarr3(carretaTOBD.getIdCarreta());
                                        infBoletimTO.update();
                                        Intent it = new Intent(CarretaActivity.this, OSActivity.class);
                                        startActivity(it);
                                        finish();
                                    }
                                    else{
                                        AlertDialog.Builder alerta = new AlertDialog.Builder(CarretaActivity.this);
                                        alerta.setTitle("ATENÇÃO");
                                        alerta.setMessage("ESSA CARRETA JÁ FOI INSERIDA. VERIFIQUE NOVAMENTE A NUMERAÇÃO DA CARRETA.");
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
                            else if(carretaTOBD.getTipoCarreta() == 8){
                                AlertDialog.Builder alerta = new AlertDialog.Builder(CarretaActivity.this);
                                alerta.setTitle("ATENÇÃO");
                                alerta.setMessage("A NUMERAÇÃO DIGITADA NÃO CORRESPONDE DA CARRETA " + ecmContext.getNumCarreta() +". VERIFIQUE SE VOCÊ NÃO ESTA INVERTENDO AS CARRETAS.");
                                alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        editTextPadrao.setText("");
                                    }
                                });
                                alerta.show();
                            }

                        }
                        else if(caminhaoTOBD.getTipoCaminhao() == 6){
                            if(carretaTOBD.getTipoCarreta() == 4){
                                if(ecmContext.getNumCarreta() == 1){
                                    AlertDialog.Builder alerta = new AlertDialog.Builder(CarretaActivity.this);
                                    alerta.setTitle("ATENÇÃO");
                                    alerta.setMessage("A NUMERAÇÃO DIGITADA NÃO CORRESPONDE DA CARRETA " + ecmContext.getNumCarreta() +". VERIFIQUE SE VOCÊ NÃO ESTA INVERTENDO AS CARRETAS.");
                                    alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            editTextPadrao.setText("");
                                        }
                                    });
                                    alerta.show();
                                }
                                else if(ecmContext.getNumCarreta() == 2){
                                    InfBoletimTO infBoletimTO = new InfBoletimTO();
                                    List lTurno = infBoletimTO.all();
                                    infBoletimTO = (InfBoletimTO) lTurno.get(0);
                                    infBoletimTO.setCarr2(carretaTOBD.getIdCarreta());
                                    infBoletimTO.update();
                                    Intent it = new Intent(CarretaActivity.this, OSActivity.class);
                                    startActivity(it);
                                    finish();
                                }
                                else if(ecmContext.getNumCarreta() == 3){
                                    InfBoletimTO infBoletimTO = new InfBoletimTO();
                                    List lTurno = infBoletimTO.all();
                                    infBoletimTO = (InfBoletimTO) lTurno.get(0);
                                    if(infBoletimTO.getCarr2() != carretaTOBD.getIdCarreta()){
                                        infBoletimTO.setCarr3(carretaTOBD.getIdCarreta());
                                        infBoletimTO.update();
                                        Intent it = new Intent(CarretaActivity.this, OSActivity.class);
                                        startActivity(it);
                                        finish();
                                    }
                                    else{
                                        AlertDialog.Builder alerta = new AlertDialog.Builder(CarretaActivity.this);
                                        alerta.setTitle("ATENÇÃO");
                                        alerta.setMessage("ESSA CARRETA JÁ FOI INSERIDA. VERIFIQUE NOVAMENTE A NUMERAÇÃO DA CARRETA.");
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
                            else if(carretaTOBD.getTipoCarreta() == 8){
                                if(ecmContext.getNumCarreta() == 1){
                                    InfBoletimTO infBoletimTO = new InfBoletimTO();
                                    List lTurno = infBoletimTO.all();
                                    infBoletimTO = (InfBoletimTO) lTurno.get(0);
                                    infBoletimTO.setCarr1(carretaTOBD.getIdCarreta());
                                    infBoletimTO.update();
                                    Intent it = new Intent(CarretaActivity.this, OSActivity.class);
                                    startActivity(it);
                                    finish();
                                }
                                else if(ecmContext.getNumCarreta() == 2){
                                    AlertDialog.Builder alerta = new AlertDialog.Builder(CarretaActivity.this);
                                    alerta.setTitle("ATENÇÃO");
                                    alerta.setMessage("A NUMERAÇÃO DIGITADA NÃO CORRESPONDE DA CARRETA " + ecmContext.getNumCarreta() +". VERIFIQUE SE VOCÊ NÃO ESTA INVERTENDO AS CARRETAS.");
                                    alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            editTextPadrao.setText("");
                                        }
                                    });
                                    alerta.show();
                                }
                                else if(ecmContext.getNumCarreta() == 3){
                                    AlertDialog.Builder alerta = new AlertDialog.Builder(CarretaActivity.this);
                                    alerta.setTitle("ATENÇÃO");
                                    alerta.setMessage("A NUMERAÇÃO DIGITADA NÃO CORRESPONDE DA CARRETA " + ecmContext.getNumCarreta() +". VERIFIQUE SE VOCÊ NÃO ESTA INVERTENDO AS CARRETAS.");
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
                    }
                    else{
                        AlertDialog.Builder alerta = new AlertDialog.Builder(CarretaActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("CARRETA INEXISTENTE NA BASE DE DADOS.");
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

        buttonCancCarreta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(editTextPadrao.getText().toString().length() > 0){
                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
                }
                else{
                    Intent it = new Intent(CarretaActivity.this, MsgNumCarretaActivity.class);
                    startActivity(it);
                    finish();
                }
            }
        });


    }

    public void onBackPressed()  {
    }

}
