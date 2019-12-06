package br.com.usinasantafe.ecm;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

import br.com.usinasantafe.ecm.model.bean.estaticas.CaminhaoBean;
import br.com.usinasantafe.ecm.model.bean.estaticas.CarretaBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.CertifCanaBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.ConfigBean;

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

                    if (ecmContext.getVerPosTela() == 4){



                    }

                }


//                if(!editTextPadrao.getText().toString().equals("")){
//
//                    ConfigBean configBean = new ConfigBean();
//                    List listaConfig = configBean.all();
//                    configBean = (ConfigBean) listaConfig.get(0);
//
//                    CaminhaoBean caminhaoBean = new CaminhaoBean();
//                    List caminhaoList = caminhaoBean.get("idCaminhao", configBean.getIdEquipConfig());
//                    caminhaoBean = (CaminhaoBean) caminhaoList.get(0);
//                    caminhaoList.clear();
//
//                    CarretaBean carretaBean = new CarretaBean();
//                    List carretaList = carretaBean.get("codCarreta", Long.parseLong(editTextPadrao.getText().toString()));
//
//                    if(carretaList.size() > 0){
//
//                        carretaBean = (CarretaBean) carretaList.get(0);
//                        carretaList.clear();
//
//                        CertifCanaBean certifCanaBean = new CertifCanaBean();
//                        List compVCanaList = certifCanaBean.get("status", 1L);
//                        certifCanaBean = (CertifCanaBean) compVCanaList.get(0);
//
//                        int proxTela = 1; //1 - Tela OS; 2 - NUMERAÇÃO NÃO CORRESPONDE DA CARRETA; 3 - ESSA CARRETA JÁ FOI INSERIDA
//
//                        if(caminhaoBean.getTipoCaminhao() == 1){
//                            if(carretaBean.getTipoCarreta() == 4){
//                                if(ecmContext.getNumCarreta() == 1){
//                                    certifCanaBean.setCarr1(carretaBean.getCodCarreta());
//                                    certifCanaBean.update();
//                                }
//                                else if(ecmContext.getNumCarreta() == 2){
//                                    if(certifCanaBean.getCarr1() != carretaBean.getCodCarreta()){
//                                        certifCanaBean.setCarr2(carretaBean.getCodCarreta());
//                                        certifCanaBean.update();
//                                    }
//                                    else{
//                                        proxTela = 2;
//                                    }
//                                }
//                                else if(ecmContext.getNumCarreta() == 3){
//                                    if((certifCanaBean.getCarr1() != carretaBean.getCodCarreta())
//                                            && (certifCanaBean.getCarr2() != carretaBean.getCodCarreta())){
//                                        certifCanaBean.setCarr3(carretaBean.getCodCarreta());
//                                        certifCanaBean.update();
//                                    }
//                                    else{
//                                        proxTela = 2;
//                                    }
//                                }
//                                else if(ecmContext.getNumCarreta() == 4){
//                                    if((certifCanaBean.getCarr1() != carretaBean.getCodCarreta())
//                                            && (certifCanaBean.getCarr2() != carretaBean.getCodCarreta())
//                                            && (certifCanaBean.getCarr3() != carretaBean.getCodCarreta())){
//                                        certifCanaBean.setCarr4(carretaBean.getCodCarreta());
//                                        certifCanaBean.update();
//                                    }
//                                    else{
//                                        proxTela = 2;
//                                    }
//                                }
//                            }
//                            else if(carretaBean.getTipoCarreta() == 8){
//                                proxTela = 3;
//                            }
//                        }
//                        else if(caminhaoBean.getTipoCaminhao() == 6){
//                            if(carretaBean.getTipoCarreta() == 4){
//                                if(ecmContext.getNumCarreta() == 1){
//                                    proxTela = 3;
//                                }
//                                else if(ecmContext.getNumCarreta() == 2){
//                                    certifCanaBean.setCarr2(carretaBean.getCodCarreta());
//                                    certifCanaBean.update();
//                                }
//                                else if(ecmContext.getNumCarreta() == 3){
//                                    if(certifCanaBean.getCarr2() != carretaBean.getCodCarreta()){
//                                        certifCanaBean.setCarr3(carretaBean.getCodCarreta());
//                                        certifCanaBean.update();
//                                    }
//                                    else{
//                                        proxTela = 2;
//                                    }
//                                }
//                                else if(ecmContext.getNumCarreta() == 4){
//                                    if((certifCanaBean.getCarr2() != carretaBean.getCodCarreta())
//                                        && (certifCanaBean.getCarr3() != carretaBean.getCodCarreta())){
//                                        certifCanaBean.setCarr3(carretaBean.getCodCarreta());
//                                        certifCanaBean.update();
//                                    }
//                                    else{
//                                        proxTela = 2;
//                                    }
//                                }
//                            }
//                            else if(carretaBean.getTipoCarreta() == 8){
//                                if(ecmContext.getNumCarreta() == 1){
//                                    certifCanaBean.setCarr1(carretaBean.getCodCarreta());
//                                    certifCanaBean.update();
//                                }
//                                else if(ecmContext.getNumCarreta() > 1){
//                                    proxTela = 3;
//                                }
//                            }
//                        }
//
//                        compVCanaList.clear();
//
//                        AlertDialog.Builder alerta = new AlertDialog.Builder(CarretaActivity.this);
//
//                        switch(proxTela){
//                            case 1:
//                                Intent it = new Intent(CarretaActivity.this, OSActivity.class);
//                                startActivity(it);
//                                finish();
//                                break;
//                            case 2:
//                                alerta.setTitle("ATENÇÃO");
//                                alerta.setMessage("ESSA CARRETA JÁ FOI INSERIDA. VERIFIQUE NOVAMENTE A NUMERAÇÃO DA CARRETA.");
//                                alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        editTextPadrao.setText("");
//                                    }
//                                });
//                                alerta.show();
//                                break;
//                            case 3:
//                                alerta.setTitle("ATENÇÃO");
//                                alerta.setMessage("A NUMERAÇÃO DIGITADA NÃO CORRESPONDE DA CARRETA " + ecmContext.getNumCarreta() +". VERIFIQUE SE VOCÊ NÃO ESTA INVERTENDO AS CARRETAS.");
//                                alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        editTextPadrao.setText("");
//                                    }
//                                });
//                                alerta.show();
//                                break;
//                        }
//
//                    }
//                    else{
//                        AlertDialog.Builder alerta = new AlertDialog.Builder(CarretaActivity.this);
//                        alerta.setTitle("ATENÇÃO");
//                        alerta.setMessage("CARRETA INEXISTENTE NA BASE DE DADOS.");
//                        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                editTextPadrao.setText("");
//                            }
//                        });
//                        alerta.show();
//                    }
//                }

            }
        });

        buttonCancCarreta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editTextPadrao.getText().toString().length() > 0){
                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
                }
            }
        });


    }

    public void onBackPressed()  {
        if (ecmContext.getVerPosTela() == 4){
            Intent it = new Intent(CarretaActivity.this, MsgNumCarretaActivity.class);
            startActivity(it);
            finish();
        }
//        Intent it = new Intent(CarretaActivity.this, MsgNumCarretaActivity.class);
//        startActivity(it);
//        finish();
    }

}
