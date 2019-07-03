package br.com.usinasantafe.ecm;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import br.com.usinasantafe.ecm.bo.ManipDadosEnvio;
import br.com.usinasantafe.ecm.to.tb.estaticas.MotoristaTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.ConfigTO;

public class VerMotoristaActivity extends ActivityGeneric {

    private ListView lista;
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

        ConfigTO configTO = new ConfigTO();
        List configList = configTO.all();
        configTO = (ConfigTO) configList.get(0);
        configList.clear();

        MotoristaTO motoristaTO = new MotoristaTO();
        List motoristaList = motoristaTO.get("codMotorista", configTO.getCrachaMotoConfig());
        motoristaTO = (MotoristaTO) motoristaList.get(0);
        motoristaList.clear();

        textViewCodMotorista.setText(String.valueOf(motoristaTO.getCodMotorista()));
        textViewNomeMotorista.setText(motoristaTO.getNomeMotorista());

        buttonManterMotorista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alerta = new AlertDialog.Builder(VerMotoristaActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("A VIAGEM FOI FINALIZADA E SERÁ ENVIADA AUTOMATICAMENTE. FAVOR ENTREGAR O CELULAR PARA O MOTORISTA.");
                alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        ManipDadosEnvio.getInstance().salvaViagemCana();

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

                ecmContext.setTelaAltMoto(2);
                Intent it = new Intent(VerMotoristaActivity.this, MotoristaActivity.class);
                startActivity(it);
                finish();

            }
        });

    }

    public void onBackPressed()  {
    }

}
