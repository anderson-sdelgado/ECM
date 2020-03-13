package br.com.usinasantafe.ecm;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import java.util.List;

import br.com.usinasantafe.ecm.model.bean.estaticas.OSBean;
import br.com.usinasantafe.ecm.util.ConexaoWeb;
import br.com.usinasantafe.ecm.util.VerifDadosServ;


public class OSActivity extends ActivityGeneric {

    private ECMContext ecmContext;
    private ProgressDialog progressBar;
    private Handler customHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_os);

        ecmContext = (ECMContext) getApplication();

        Button buttonOkOS = (Button) findViewById(R.id.buttonOkPadrao);
        Button buttonCancOS = (Button) findViewById(R.id.buttonCancPadrao);

        buttonOkOS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!editTextPadrao.getText().toString().equals("")){

                    try{

                        Long nroOS = Long.parseLong(editTextPadrao.getText().toString());

                        ecmContext.getConfigCTR().setOsConfig(nroOS);

                        ConexaoWeb conexaoWeb = new ConexaoWeb();
                        OSBean osTO = new OSBean();

                        if (osTO.hasElements()) {

                            List osList = osTO.get("nroOS", nroOS);

                            if (osList.size() > 0) {

                                if (conexaoWeb.verificaConexao(OSActivity.this)) {
                                    ecmContext.getConfigCTR().setStatusConConfig(1L);
                                }
                                else{
                                    ecmContext.getConfigCTR().setStatusConConfig(0L);
                                }

                                VerifDadosServ.getInstance().setVerTerm(true);

                                Intent it = new Intent(OSActivity.this, ListaAtividadeActivity.class);
                                startActivity(it);
                                finish();

                            } else {

                                if (conexaoWeb.verificaConexao(OSActivity.this)) {

                                    progressBar = new ProgressDialog(v.getContext());
                                    progressBar.setCancelable(true);
                                    progressBar.setMessage("PESQUISANDO OS...");
                                    progressBar.show();

                                    customHandler.postDelayed(updateTimerThread, 10000);

                                    ecmContext.getMotoMecCTR().verOS(editTextPadrao.getText().toString()
                                            , OSActivity.this, ListaAtividadeActivity.class, progressBar);


                                } else {

                                    ecmContext.getConfigCTR().setStatusConConfig(0L);

                                    Intent it = new Intent(OSActivity.this, ListaAtividadeActivity.class);
                                    startActivity(it);
                                    finish();

                                }

                            }

                        } else {

                            if (conexaoWeb.verificaConexao(OSActivity.this)) {

                                progressBar = new ProgressDialog(v.getContext());
                                progressBar.setCancelable(true);
                                progressBar.setMessage("PESQUISANDO OS...");
                                progressBar.show();

                                customHandler.postDelayed(updateTimerThread, 10000);

                                ecmContext.getMotoMecCTR().verOS(editTextPadrao.getText().toString()
                                        , OSActivity.this, ListaAtividadeActivity.class, progressBar);

                            } else {

                                ecmContext.getConfigCTR().setStatusConConfig(0L);

                                Intent it = new Intent(OSActivity.this, ListaAtividadeActivity.class);
                                startActivity(it);
                                finish();

                            }

                        }

                    }
                    catch (NumberFormatException e){

                        AlertDialog.Builder alerta = new AlertDialog.Builder( OSActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("VALOR DE OS INCORRETO! FAVOR VERIFICAR.");
                        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                        alerta.show();

                    }



//                    if(ecmContext.getCECCTR().verNroOS(Long.parseLong(editTextPadrao.getText().toString()))){
//
//                        ecmContext.getCECCTR().setNroOS(Long.parseLong(editTextPadrao.getText().toString()));
//                        Intent it = new Intent(OSActivity.this, LibOSActivity.class);
//                        startActivity(it);
//                        finish();
//
//                    }
//                    else{
//
//                        AlertDialog.Builder alerta = new AlertDialog.Builder(OSActivity.this);
//                        alerta.setTitle("ATENÇÃO");
//                        alerta.setMessage("O.S. NÃO CORRESPONDENTE A ATIVIDADE ANTERIORMENTE DIGITADA. POR FAVOR, DIGITE A O.S. CORRESPONDE A MESMA.");
//
//                        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//
//                                editTextPadrao.setText("");
//                            }
//                        });
//                        alerta.show();
//
//                    }

                }
            }
        });

        buttonCancOS.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(editTextPadrao.getText().toString().length() > 0){
                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
                }
            }
        });

    }

    public void onBackPressed()  {
        if(!ecmContext.getCECCTR().verQtdeCarreta(1L)){
            Intent it = new Intent(OSActivity.this, CaminhaoActivity.class);
            startActivity(it);
            finish();
        }
        else{
            Intent it = new Intent(OSActivity.this, CarretaActivity.class);
            startActivity(it);
            finish();
        }
    }

    private Runnable updateTimerThread = new Runnable() {

        public void run() {

            if(!VerifDadosServ.getInstance().isVerTerm()) {

                VerifDadosServ.getInstance().cancelVer();

                if (progressBar.isShowing()) {
                    progressBar.dismiss();
                }

                ecmContext.getConfigCTR().setStatusConConfig(0L);
                Intent it = new Intent(OSActivity.this, ListaAtividadeActivity.class);
                startActivity(it);
                finish();

            }

        }
    };

}
