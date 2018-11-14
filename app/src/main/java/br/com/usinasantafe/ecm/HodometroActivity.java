package br.com.usinasantafe.ecm;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

import br.com.usinasantafe.ecm.bo.ManipDadosEnvio;
import br.com.usinasantafe.ecm.to.tb.variaveis.HodometroTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.InfBoletimTO;

public class HodometroActivity extends ActivityGeneric {

    private ECMContext ecmContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hodometro);

        ecmContext = (ECMContext) getApplication();
        Button buttonOkCarreta = (Button) findViewById(R.id.buttonOkPadrao);
        Button buttonCancCarreta = (Button) findViewById(R.id.buttonCancPadrao);

        buttonOkCarreta.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (!editTextPadrao.getText().toString().equals("")) {
                    HodometroTO hodometroTO = new HodometroTO();
                    hodometroTO.setHodometro(Long.parseLong(editTextPadrao.getText().toString()));
                    hodometroTO.deleteAll();
                    hodometroTO.insert();

                    Intent it = new Intent(HodometroActivity.this, MenuMotoMecActivity.class);
                    startActivity(it);
                    finish();

                    ManipDadosEnvio.getInstance().salvaMotoMec(ecmContext.getApontMotoMecTO());

                } else {
                    AlertDialog.Builder alerta = new AlertDialog.Builder(HodometroActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("POR FAVOR. DIGITE O VALOR DO HODOMETRO.");
                    alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    alerta.show();
                }
            }
        });

        buttonCancCarreta.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (editTextPadrao.getText().toString().length() > 0) {
                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
                } else {
                    Intent it = new Intent(HodometroActivity.this, MenuMotoMecActivity.class);
                    startActivity(it);
                    finish();
                }
            }
        });


    }

    public void onBackPressed()  {
    }

}
