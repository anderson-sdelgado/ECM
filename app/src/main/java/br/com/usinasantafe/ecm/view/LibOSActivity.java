package br.com.usinasantafe.ecm.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.usinasantafe.ecm.ECMContext;
import br.com.usinasantafe.ecm.R;


public class LibOSActivity extends ActivityGeneric {

    private ECMContext ecmContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liberacao);

        ecmContext = (ECMContext) getApplication();

        Button buttonOkLiberacao = (Button) findViewById(R.id.buttonOkPadrao);
        Button buttonCancLiberacao = (Button) findViewById(R.id.buttonCancPadrao);
        EditText editText = (EditText) findViewById(R.id.editTextPadrao);

        editText.setText(String.valueOf(ecmContext.getConfigCTR().getOS(ecmContext.getConfigCTR().getConfig().getOsConfig()).getIdLibOS()));

        buttonOkLiberacao.setOnClickListener(new View.OnClickListener() {

            @SuppressWarnings({ "rawtypes", "unchecked" })
            @Override
            public void onClick(View v) {

                if(!editTextPadrao.getText().toString().equals("")){

                    if(ecmContext.getCECCTR().verLibOS(Long.parseLong(editTextPadrao.getText().toString()))){

                        ecmContext.getCECCTR().setLib(Long.parseLong(editTextPadrao.getText().toString()));

                        int numCarreta = ecmContext.getMotoMecCTR().qtdeCarreta() + 1;

                        if (numCarreta < 4) {
                            Intent it = new Intent(LibOSActivity.this, MsgNumCarretaActivity.class);
                            startActivity(it);
                            finish();
                        }
                        else{
                            Intent it = new Intent(LibOSActivity.this, ListaFinalizaApontActivity.class);
                            startActivity(it);
                            finish();
                        }

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
