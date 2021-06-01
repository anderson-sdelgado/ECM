package br.com.usinasantafe.ecm.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.usinasantafe.ecm.ECMContext;
import br.com.usinasantafe.ecm.R;
import br.com.usinasantafe.ecm.model.bean.estaticas.FuncBean;

public class VerMotoristaActivity extends ActivityGeneric {

    private ECMContext ecmContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_motorista);

        ecmContext = (ECMContext) getApplication();

        Button buttonManterMotorista = (Button) findViewById(R.id.buttonManterMotorista);
        Button buttonAlterarCabecalho = (Button) findViewById(R.id.buttonAlterarCabecalho);

        TextView textViewCodMotorista = (TextView) findViewById(R.id.textViewCodMotorista);
        TextView textViewNomeMotorista = (TextView) findViewById(R.id.textViewNomeMotorista);

        textViewCodMotorista.setText(String.valueOf(ecmContext.getMotoMecCTR().getMatricNomeFunc().getMatricFunc()));
        textViewNomeMotorista.setText(ecmContext.getMotoMecCTR().getMatricNomeFunc().getNomeFunc());

        buttonManterMotorista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alerta = new AlertDialog.Builder(VerMotoristaActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("A VIAGEM FOI FINALIZADA E SERÁ ENVIADA AUTOMATICAMENTE. FAVOR ENTREGAR O CELULAR PARA O MOTORISTA.");
                alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        ecmContext.getCECCTR().fechaPreCEC();
                        Intent it = new Intent(VerMotoristaActivity.this, MenuMotoMecActivity.class);
                        startActivity(it);
                        finish();

                    }
                });
                alerta.show();

            }
        });

        buttonAlterarCabecalho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alerta = new AlertDialog.Builder(VerMotoristaActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("DESEJA REALMENTE TROCA O MOTORISTA? ISSO ENCERRA-LA O BOLETIM.");

                alerta.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ecmContext.setVerPosTela(9);
                        Intent it = new Intent(VerMotoristaActivity.this, HodometroActivity.class);
                        startActivity(it);
                        finish();
                    }
                });

                alerta.setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                alerta.show();

            }
        });

    }

    public void onBackPressed()  {
    }

}
