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
import br.com.usinasantafe.ecm.to.tb.variaveis.CompVCanaTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.ConfigTO;

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

                if(!editTextPadrao.getText().toString().equals("")){

                    ConfigTO configTO = new ConfigTO();
                    List listaConfig = configTO.all();
                    configTO = (ConfigTO) listaConfig.get(0);

                    CaminhaoTO caminhaoTO = new CaminhaoTO();
                    List caminhaoList = caminhaoTO.get("idCaminhao", configTO.getIdCamConfig());
                    caminhaoTO = (CaminhaoTO) caminhaoList.get(0);
                    caminhaoList.clear();

                    CarretaTO carretaTO = new CarretaTO();
                    List carretaList = carretaTO.get("codCarreta", Long.parseLong(editTextPadrao.getText().toString()));

                    if(carretaList.size() > 0){

                        carretaTO = (CarretaTO) carretaList.get(0);
                        carretaList.clear();

                        CompVCanaTO compVCanaTO = new CompVCanaTO();
                        List compVCanaList = compVCanaTO.get("status", 1L);
                        compVCanaTO = (CompVCanaTO) compVCanaList.get(0);

                        int proxTela = 1; //1 - Tela OS; 2 - NUMERAÇÃO NÃO CORRESPONDE DA CARRETA; 3 - ESSA CARRETA JÁ FOI INSERIDA

                        if(caminhaoTO.getTipoCaminhao() == 1){
                            if(carretaTO.getTipoCarreta() == 4){
                                if(ecmContext.getNumCarreta() == 1){
                                    compVCanaTO.setCarr1(carretaTO.getCodCarreta());
                                    compVCanaTO.update();
                                }
                                else if(ecmContext.getNumCarreta() == 2){
                                    if(compVCanaTO.getCarr1() != carretaTO.getCodCarreta()){
                                        compVCanaTO.setCarr2(carretaTO.getCodCarreta());
                                        compVCanaTO.update();
                                    }
                                    else{
                                        proxTela = 2;
                                    }
                                }
                                else if(ecmContext.getNumCarreta() == 3){
                                    if((compVCanaTO.getCarr1() != carretaTO.getCodCarreta())
                                            && (compVCanaTO.getCarr2() != carretaTO.getCodCarreta())){
                                        compVCanaTO.setCarr3(carretaTO.getCodCarreta());
                                        compVCanaTO.update();
                                    }
                                    else{
                                        proxTela = 2;
                                    }
                                }
                                else if(ecmContext.getNumCarreta() == 4){
                                    if((compVCanaTO.getCarr1() != carretaTO.getCodCarreta())
                                            && (compVCanaTO.getCarr2() != carretaTO.getCodCarreta())
                                            && (compVCanaTO.getCarr3() != carretaTO.getCodCarreta())){
                                        compVCanaTO.setCarr4(carretaTO.getCodCarreta());
                                        compVCanaTO.update();
                                    }
                                    else{
                                        proxTela = 2;
                                    }
                                }
                            }
                            else if(carretaTO.getTipoCarreta() == 8){
                                proxTela = 3;
                            }
                        }
                        else if(caminhaoTO.getTipoCaminhao() == 6){
                            if(carretaTO.getTipoCarreta() == 4){
                                if(ecmContext.getNumCarreta() == 1){
                                    proxTela = 3;
                                }
                                else if(ecmContext.getNumCarreta() == 2){
                                    compVCanaTO.setCarr2(carretaTO.getCodCarreta());
                                    compVCanaTO.update();
                                }
                                else if(ecmContext.getNumCarreta() == 3){
                                    if(compVCanaTO.getCarr2() != carretaTO.getCodCarreta()){
                                        compVCanaTO.setCarr3(carretaTO.getCodCarreta());
                                        compVCanaTO.update();
                                    }
                                    else{
                                        proxTela = 2;
                                    }
                                }
                                else if(ecmContext.getNumCarreta() == 4){
                                    if((compVCanaTO.getCarr2() != carretaTO.getCodCarreta())
                                        && (compVCanaTO.getCarr3() != carretaTO.getCodCarreta())){
                                        compVCanaTO.setCarr3(carretaTO.getCodCarreta());
                                        compVCanaTO.update();
                                    }
                                    else{
                                        proxTela = 2;
                                    }
                                }
                            }
                            else if(carretaTO.getTipoCarreta() == 8){
                                if(ecmContext.getNumCarreta() == 1){
                                    compVCanaTO.setCarr1(carretaTO.getCodCarreta());
                                    compVCanaTO.update();
                                }
                                else if(ecmContext.getNumCarreta() > 1){
                                    proxTela = 3;
                                }
                            }
                        }

                        compVCanaList.clear();

                        AlertDialog.Builder alerta = new AlertDialog.Builder(CarretaActivity.this);

                        switch(proxTela){
                            case 1:
                                Intent it = new Intent(CarretaActivity.this, OSActivity.class);
                                startActivity(it);
                                finish();
                                break;
                            case 2:
                                alerta.setTitle("ATENÇÃO");
                                alerta.setMessage("ESSA CARRETA JÁ FOI INSERIDA. VERIFIQUE NOVAMENTE A NUMERAÇÃO DA CARRETA.");
                                alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        editTextPadrao.setText("");
                                    }
                                });
                                alerta.show();
                                break;
                            case 3:
                                alerta.setTitle("ATENÇÃO");
                                alerta.setMessage("A NUMERAÇÃO DIGITADA NÃO CORRESPONDE DA CARRETA " + ecmContext.getNumCarreta() +". VERIFIQUE SE VOCÊ NÃO ESTA INVERTENDO AS CARRETAS.");
                                alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        editTextPadrao.setText("");
                                    }
                                });
                                alerta.show();
                                break;
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
