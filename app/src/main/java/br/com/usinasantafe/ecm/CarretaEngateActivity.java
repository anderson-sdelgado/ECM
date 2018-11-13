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
import br.com.usinasantafe.ecm.to.tb.variaveis.CarretaEngDesengTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.ConfiguracaoTO;

public class CarretaEngateActivity extends ActivityGeneric {

    private ECMContext ecmContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carreta_engate);

        ecmContext = (ECMContext) getApplication();
        Button buttonOkCarreta = (Button) findViewById(R.id.buttonOkPadrao);
        Button buttonCancCarreta = (Button) findViewById(R.id.buttonCancPadrao);

        buttonOkCarreta.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                if(!editTextPadrao.getText().toString().equals("")){

                    Long carreta = Long.parseLong(editTextPadrao.getText().toString());

                    ConfiguracaoTO configTO = new ConfiguracaoTO();
                    List listaConfig = configTO.all();
                    configTO = (ConfiguracaoTO) listaConfig.get(0);

                    CaminhaoTO caminhaoTOBDPesq = new CaminhaoTO();
                    List listaCam = caminhaoTOBDPesq.get("idCaminhao", configTO.getCamConfig());
                    CaminhaoTO caminhaoTOBD = (CaminhaoTO) listaCam.get(0);//

                    CarretaTO carretaTOBDPesq = new CarretaTO();
                    List listaCarreta = carretaTOBDPesq.get("idCarreta", carreta);

                    CarretaEngDesengTO carretaEngDesengTO = new CarretaEngDesengTO();
                    List listaCarrEngDeseng = carretaEngDesengTO.all();

                    if(listaCarreta.size() > 0){

                        CarretaTO carretaTOBD = (CarretaTO) listaCarreta.get(0);//

                        if(caminhaoTOBD.getTipoCaminhao() == 1){
                            if(carretaTOBD.getTipoCarreta() == 4){
                                if(listaCarrEngDeseng.size() == 0){
                                    carretaEngDesengTO.setNumCarreta(carreta);
                                    carretaEngDesengTO.setPosCarreta((long) 1);
                                    carretaEngDesengTO.insert();
                                    Intent it = new Intent(CarretaEngateActivity.this, MsgCarretaEngateActivity.class);
                                    startActivity(it);
                                    finish();
                                }
                                else if(listaCarrEngDeseng.size() == 1){
                                    CarretaEngDesengTO carretaEngDesengTOPesq = new CarretaEngDesengTO();
                                    List carEngDesengTOPesq = carretaEngDesengTOPesq.get("posCarreta", (long) 1);
                                    carretaEngDesengTOPesq =  (CarretaEngDesengTO) carEngDesengTOPesq.get(0);
                                    if(carretaEngDesengTOPesq.getNumCarreta() != carreta){
                                        carretaEngDesengTO.setNumCarreta(carreta);
                                        carretaEngDesengTO.setPosCarreta((long) 2);
                                        carretaEngDesengTO.insert();
                                        Intent it = new Intent(CarretaEngateActivity.this, MsgCarretaEngateActivity.class);
                                        startActivity(it);
                                        finish();
                                    }
                                    else{
                                        AlertDialog.Builder alerta = new AlertDialog.Builder(CarretaEngateActivity.this);
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
                                else if(listaCarrEngDeseng.size() == 2){

                                    CarretaEngDesengTO carretaEngDesengTOPesq = new CarretaEngDesengTO();
                                    List carEngDesengTOPesq = carretaEngDesengTOPesq.get("numCarreta", carreta);

                                    if(carEngDesengTOPesq.size() == 0){
//                                        ecmContext.getCompVCanaTO().setCarr3(carretaTOBDPesq.getIdCarreta());
                                        Intent it = new Intent(CarretaEngateActivity.this, MsgCarretaEngateActivity.class);
                                        startActivity(it);
                                        finish();
                                    }
                                    else{
                                        AlertDialog.Builder alerta = new AlertDialog.Builder(CarretaEngateActivity.this);
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
                                AlertDialog.Builder alerta = new AlertDialog.Builder(CarretaEngateActivity.this);
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
                                if(listaCarrEngDeseng.size() == 0){
                                    AlertDialog.Builder alerta = new AlertDialog.Builder(CarretaEngateActivity.this);
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
                                else if(listaCarrEngDeseng.size() == 1){
                                    carretaEngDesengTO.setNumCarreta(carreta);
                                    carretaEngDesengTO.setPosCarreta((long) 2);
                                    carretaEngDesengTO.insert();
                                    Intent it = new Intent(CarretaEngateActivity.this, MsgCarretaEngateActivity.class);
                                    startActivity(it);
                                    finish();
                                }
                                else if(listaCarrEngDeseng.size() == 2){

                                    CarretaEngDesengTO carretaEngDesengTOPesq = new CarretaEngDesengTO();
                                    List carEngDesengTOPesq = carretaEngDesengTOPesq.get("numCarreta", carreta);

                                    if(carEngDesengTOPesq.size() == 0){
                                        carretaEngDesengTO.setNumCarreta(carreta);
                                        carretaEngDesengTO.setPosCarreta((long) 3);
                                        carretaEngDesengTO.insert();
                                        Intent it = new Intent(CarretaEngateActivity.this, MsgCarretaEngateActivity.class);
                                        startActivity(it);
                                        finish();
                                    }
                                    else{
                                        AlertDialog.Builder alerta = new AlertDialog.Builder(CarretaEngateActivity.this);
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
                                if(listaCarrEngDeseng.size() == 0){
                                    carretaEngDesengTO.setNumCarreta(carreta);
                                    carretaEngDesengTO.setPosCarreta((long) 1);
                                    carretaEngDesengTO.insert();
                                    Intent it = new Intent(CarretaEngateActivity.this, MsgCarretaEngateActivity.class);
                                    startActivity(it);
                                    finish();
                                }
                                else if(listaCarrEngDeseng.size() > 0){
                                    AlertDialog.Builder alerta = new AlertDialog.Builder(CarretaEngateActivity.this);
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
                        AlertDialog.Builder alerta = new AlertDialog.Builder(CarretaEngateActivity.this);
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
                    Intent it = new Intent(CarretaEngateActivity.this, MsgCarretaEngateActivity.class);
                    startActivity(it);
                    finish();
                }

            }
        });


    }

    public void onBackPressed()  {
    }

}
