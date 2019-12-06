package br.com.usinasantafe.ecm;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.com.usinasantafe.ecm.model.bean.estaticas.AtividadeOSBean;

public class OSActivity extends ActivityGeneric {

    private ECMContext ecmContext;

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

                    AtividadeOSBean atividadeOSBean = new AtividadeOSBean();

                    if(atividadeOSBean.exists("nroOSAtivOS", Long.parseLong(editTextPadrao.getText().toString()))){

                        if(ecmContext.getNroOS() == Long.parseLong(editTextPadrao.getText().toString())) {

                            Intent it = new Intent(OSActivity.this, LiberacaoActivity.class);
                            startActivity(it);
                            finish();

                        }
                        else{

                            AlertDialog.Builder alerta = new AlertDialog.Builder(OSActivity.this);
                            alerta.setTitle("ATENÇÃO");
                            alerta.setMessage("O.S. NÃO CORRESPONDENTE A ATIVIDADE ANTERIORMENTE DIGITADA. POR FAVOR, VERIFIQUE A O.S. DIGITE.");

                            alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    editTextPadrao.setText("");
                                }
                            });
                            alerta.show();

                        }

                    }
                    else{

                        AlertDialog.Builder alerta = new AlertDialog.Builder(OSActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("O.S. INEXISTENTE NA BASE DE DADOS.");

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

        buttonCancOS.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(editTextPadrao.getText().toString().length() > 0){
                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
                }
                else{

                    if(ecmContext.getNumCarreta() == 0){
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
            }
        });

    }

    public void onBackPressed()  {
    }

}
