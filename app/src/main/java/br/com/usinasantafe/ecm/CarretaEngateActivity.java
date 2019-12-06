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
import br.com.usinasantafe.ecm.model.bean.variaveis.CarretaEngDesengBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.ConfigBean;

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


                if(!editTextPadrao.getText().toString().equals("")){

                    Long carreta = Long.parseLong(editTextPadrao.getText().toString());

                    ConfigBean configBean = new ConfigBean();
                    List listaConfig = configBean.all();
                    configBean = (ConfigBean) listaConfig.get(0);

                    CaminhaoBean caminhaoBeanBD = new CaminhaoBean();
                    List listaCam = caminhaoBeanBD.get("idCaminhao", configBean.getIdEquipConfig());
                    caminhaoBeanBD = (CaminhaoBean) listaCam.get(0);//

                    CarretaBean carretaBeanBD = new CarretaBean();
                    List listaCarreta = carretaBeanBD.get("codCarreta", carreta);

                    CarretaEngDesengBean carretaEngDesengBean = new CarretaEngDesengBean();
                    List listaCarrEngDeseng = carretaEngDesengBean.all();

                    if(listaCarreta.size() > 0){

                        carretaBeanBD = (CarretaBean) listaCarreta.get(0);//

                        if(caminhaoBeanBD.getTipoCaminhao() == 1){
                            if(carretaBeanBD.getTipoCarreta() == 4){
                                if(listaCarrEngDeseng.size() == 0){
                                    carretaEngDesengBean.setNumCarreta(carreta);
                                    carretaEngDesengBean.setPosCarreta((long) 1);
                                    carretaEngDesengBean.insert();
                                    Intent it = new Intent(CarretaEngateActivity.this, MsgCarretaEngateActivity.class);
                                    startActivity(it);
                                    finish();
                                }
                                else if(listaCarrEngDeseng.size() == 1){
                                    CarretaEngDesengBean carretaEngDesengBeanPesq = new CarretaEngDesengBean();
                                    List carEngDesengTOPesq = carretaEngDesengBeanPesq.get("posCarreta", (long) 1);
                                    carretaEngDesengBeanPesq =  (CarretaEngDesengBean) carEngDesengTOPesq.get(0);
                                    if(carretaEngDesengBeanPesq.getNumCarreta() != carreta){
                                        carretaEngDesengBean.setNumCarreta(carreta);
                                        carretaEngDesengBean.setPosCarreta((long) 2);
                                        carretaEngDesengBean.insert();
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

                                    CarretaEngDesengBean carretaEngDesengBeanPesq = new CarretaEngDesengBean();
                                    List carEngDesengTOPesq = carretaEngDesengBeanPesq.get("numCarreta", carreta);

                                    if(carEngDesengTOPesq.size() == 0){
                                        carretaEngDesengBean.setNumCarreta(carreta);
                                        carretaEngDesengBean.setPosCarreta((long) 3);
                                        carretaEngDesengBean.insert();
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
                                else if(listaCarrEngDeseng.size() == 3){

                                    CarretaEngDesengBean carretaEngDesengBeanPesq = new CarretaEngDesengBean();
                                    List carEngDesengTOPesq = carretaEngDesengBeanPesq.get("numCarreta", carreta);

                                    if(carEngDesengTOPesq.size() == 0){
                                        carretaEngDesengBean.setNumCarreta(carreta);
                                        carretaEngDesengBean.setPosCarreta((long) 4);
                                        carretaEngDesengBean.insert();
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
                            else if(carretaBeanBD.getTipoCarreta() == 8){
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
                        else if(caminhaoBeanBD.getTipoCaminhao() == 6){
                            if(carretaBeanBD.getTipoCarreta() == 4){
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
                                    carretaEngDesengBean.setNumCarreta(carreta);
                                    carretaEngDesengBean.setPosCarreta((long) 2);
                                    carretaEngDesengBean.insert();
                                    Intent it = new Intent(CarretaEngateActivity.this, MsgCarretaEngateActivity.class);
                                    startActivity(it);
                                    finish();
                                }
                                else if(listaCarrEngDeseng.size() == 2){

                                    CarretaEngDesengBean carretaEngDesengBeanPesq = new CarretaEngDesengBean();
                                    List carEngDesengTOPesq = carretaEngDesengBeanPesq.get("numCarreta", carreta);

                                    if(carEngDesengTOPesq.size() == 0){
                                        carretaEngDesengBean.setNumCarreta(carreta);
                                        carretaEngDesengBean.setPosCarreta((long) 3);
                                        carretaEngDesengBean.insert();
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
                                else if(listaCarrEngDeseng.size() == 3){

                                    CarretaEngDesengBean carretaEngDesengBeanPesq = new CarretaEngDesengBean();
                                    List carEngDesengTOPesq = carretaEngDesengBeanPesq.get("numCarreta", carreta);

                                    if(carEngDesengTOPesq.size() == 0){
                                        carretaEngDesengBean.setNumCarreta(carreta);
                                        carretaEngDesengBean.setPosCarreta((long) 4);
                                        carretaEngDesengBean.insert();
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
                            else if(carretaBeanBD.getTipoCarreta() == 8){
                                if(listaCarrEngDeseng.size() == 0){
                                    carretaEngDesengBean.setNumCarreta(carreta);
                                    carretaEngDesengBean.setPosCarreta((long) 1);
                                    carretaEngDesengBean.insert();
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
                if(editTextPadrao.getText().toString().length() > 0){
                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
                }
            }
        });


    }

    public void onBackPressed()  {
        Intent it = new Intent(CarretaEngateActivity.this, MsgCarretaEngateActivity.class);
        startActivity(it);
        finish();
    }

}
