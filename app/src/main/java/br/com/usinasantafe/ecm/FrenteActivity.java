package br.com.usinasantafe.ecm;

//import android.support.v7.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

import br.com.usinasantafe.ecm.bo.ManipDadosEnvio;
import br.com.usinasantafe.ecm.bo.Tempo;
import br.com.usinasantafe.ecm.to.tb.variaveis.AtividadeOsTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.InfBoletimTO;

public class FrenteActivity extends ActivityGeneric {

    private ECMContext ecmContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frente);

        ecmContext = (ECMContext) getApplication();

        Button buttonOkFrente = (Button) findViewById(R.id.buttonOkPadrao);
        Button buttonCancFrente = (Button) findViewById(R.id.buttonCancPadrao);

        buttonOkFrente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!editTextPadrao.getText().toString().equals("")) {

                    int frente = Integer.parseInt(editTextPadrao.getText().toString());

                    if (frente < 50) {

                        InfBoletimTO infBoletimTO = new InfBoletimTO();
                        List infBoletimTOList = infBoletimTO.all();
                        infBoletimTO = (InfBoletimTO) infBoletimTOList.get(0);

                        infBoletimTO.setFrente(Long.parseLong(editTextPadrao.getText().toString()));
                        infBoletimTO.setDataSaidaUsina(Tempo.getInstance().data());
                        infBoletimTO.update();

                        AtividadeOsTO atividadeOs = new AtividadeOsTO();
                        atividadeOs.deleteAll();
                        atividadeOs.setAtivOS((long) 0);
                        atividadeOs.setEstado((long) 0);
                        atividadeOs.insert();

                        ecmContext.setPosMenu(8);
                        ManipDadosEnvio.getInstance().salvaMotoMec(ecmContext.getApontMotoMecTO());

                        Intent it = new Intent(FrenteActivity.this, MenuMotoMecActivity.class);
                        startActivity(it);
                        finish();


                    }
                    else{

                        AlertDialog.Builder alerta = new AlertDialog.Builder(FrenteActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("FRENTE INCORRETA! POR FAVOR, VERIFIQUE A NUMERAÇÃO DA FRENTE E DIGITE NOVAMENTE.");

                        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // TODO Auto-generated method stub
                            }
                        });

                        alerta.show();

                    }

                }

            }
        });

        buttonCancFrente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextPadrao.getText().toString().length() > 0) {
                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
                } else {
                    Intent it = new Intent(FrenteActivity.this, MenuMotoMecActivity.class);
                    startActivity(it);
                    finish();
                }
            }
        });


    }

    public void onBackPressed()  {
    }

}
