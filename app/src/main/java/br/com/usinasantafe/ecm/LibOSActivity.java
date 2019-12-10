package br.com.usinasantafe.ecm;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class LibOSActivity extends ActivityGeneric {

    private ECMContext ecmContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liberacao);

        ecmContext = (ECMContext) getApplication();

        Button buttonOkLiberacao = (Button) findViewById(R.id.buttonOkPadrao);
        Button buttonCancLiberacao = (Button) findViewById(R.id.buttonCancPadrao);

        buttonOkLiberacao.setOnClickListener(new View.OnClickListener() {

            @SuppressWarnings({ "rawtypes", "unchecked" })
            @Override
            public void onClick(View v) {

                if(!editTextPadrao.getText().toString().equals("")){

                    if(ecmContext.getCertifCanaCTR().verLibOS(Long.parseLong(editTextPadrao.getText().toString()))){

                        if(!ecmContext.getCertifCanaCTR().verQtdeCarreta(1L)){
                            ecmContext.getCertifCanaCTR().setLibCam(Long.parseLong(editTextPadrao.getText().toString()));
                        }
                        else{

                        }
                        Intent it = new Intent(LibOSActivity.this, MsgLibOSActivity.class);
                        startActivity(it);
                        finish();

                    }
                    else{

                        AlertDialog.Builder alerta = new AlertDialog.Builder(LibOSActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("LIBERAÇÃO INEXISTENTE NA BASE DE DADOS OU LIBERAÇÃO NÃO CORRESPONDE A O.S. DIGITADA! POR FAVOR, VERIFIQUE A NUMERAÇÃO DIGITADA.");
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

        buttonCancLiberacao.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(editTextPadrao.getText().toString().length() > 0){
                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
                }
            }
        });


    }

    public void onBackPressed()  {
        Intent it = new Intent(LibOSActivity.this, OSActivity.class);
        startActivity(it);
        finish();
    }

}
