package br.com.usinasantafe.ecm.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import br.com.usinasantafe.ecm.ECMContext;
import br.com.usinasantafe.ecm.R;
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

                        if (ecmContext.getConfigCTR().verOS(nroOS)){

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
        Intent it = new Intent(OSActivity.this, ListaTurnoActivity.class);
        startActivity(it);
        finish();
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
