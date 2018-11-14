package br.com.usinasantafe.ecm;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

import br.com.usinasantafe.ecm.to.tb.estaticas.NoteiroTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.InfBoletimTO;

public class NoteiroActivity extends ActivityGeneric {

    private ECMContext ecmContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noteiro);

        ecmContext = (ECMContext) getApplication();
        Button buttonOkNoteiro = (Button) findViewById(R.id.buttonOkPadrao);
        Button buttonCancNoteiro = (Button) findViewById(R.id.buttonCancPadrao);

        buttonOkNoteiro.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                if(!editTextPadrao.getText().toString().equals("")){

                    InfBoletimTO infBoletimTO = new InfBoletimTO();
                    List lTurno = infBoletimTO.all();
                    infBoletimTO = (InfBoletimTO) lTurno.get(0);

                    NoteiroTO noteiroTOBD = new NoteiroTO();

                    if(infBoletimTO.getTipoAtiv() == 1){

                        if(noteiroTOBD.exists("codNoteiro", Long.parseLong(editTextPadrao.getText().toString()))){

//                            ecmContext.getCompVCanaTO().setNoteiro(Long.parseLong(editTextPadrao.getText().toString()));
                            Intent it = new Intent(NoteiroActivity.this, AtividadeOSActivity.class);
                            startActivity(it);
                            finish();

                        }
                        else{

                            AlertDialog.Builder alerta = new AlertDialog.Builder(NoteiroActivity.this);
                            alerta.setTitle("ATENÇÃO");
                            alerta.setMessage("MATRICULA DO NOTEIRO INEXISTENTE NA BASE DE DADOS.");

                            alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    editTextPadrao.setText("");
                                }
                            });

                            alerta.show();

                        }

                    }
                    else if(infBoletimTO.getTipoAtiv() == 2){

                        if(noteiroTOBD.exists("codNoteiro", Long.parseLong(editTextPadrao.getText().toString()))){

                            ecmContext.getCompVVinhacaTO().setApont(Long.parseLong(editTextPadrao.getText().toString()));
                            Intent it = new Intent(NoteiroActivity.this, ListaLocalActivity.class);
                            startActivity(it);
                            finish();

                        }
                        else{

                            AlertDialog.Builder alerta = new AlertDialog.Builder(NoteiroActivity.this);
                            alerta.setTitle("ATENÇÃO");
                            alerta.setMessage("MATRICULA DO NOTEIRO INEXISTENTE NA BASE DE DADOS.");

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

        });

        buttonCancNoteiro.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(editTextPadrao.getText().toString().length() > 0){
                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
                }
                else{
                    Intent it = new Intent(NoteiroActivity.this, MenuInicialApontActivity.class);
                    startActivity(it);
                    finish();
                }
            }

        });

    }

    public void onBackPressed()  {
    }

}
