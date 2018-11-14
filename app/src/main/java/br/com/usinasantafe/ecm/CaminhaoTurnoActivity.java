package br.com.usinasantafe.ecm;

import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import br.com.usinasantafe.ecm.to.tb.variaveis.ConfiguracaoTO;

public class CaminhaoTurnoActivity extends ActivityGeneric {

    private ECMContext ecmContext;
    private TextView textViewCodCaminhao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caminhao_turno);

        ecmContext = (ECMContext) getApplication();

        textViewCodCaminhao = (TextView) findViewById(R.id.textViewCodCaminhaoTurno);
        Button buttonOkCaminhao = (Button) findViewById(R.id.buttonOkCaminhaoTurno);
        Button buttonCancCaminhao = (Button) findViewById(R.id.buttonCancCaminhaoTurno);

        ConfiguracaoTO configTO = new ConfiguracaoTO();
        List listConfigTO = configTO.all();

        configTO = (ConfiguracaoTO) listConfigTO.get(0);
        textViewCodCaminhao.setText(String.valueOf(configTO.getCodCamConfig()));

        buttonOkCaminhao.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                if (ecmContext.getAltMotoL() == 1) {//Menu Inicial
                    Intent it = new Intent(CaminhaoTurnoActivity.this, ListaTurnoActivity.class);
                    startActivity(it);
                    finish();
                } else if (ecmContext.getAltMotoL() == 2) {//Ver Motorista Final
                    Intent it = new Intent(CaminhaoTurnoActivity.this, VerMotoristaActivity.class);
                    startActivity(it);
                    finish();
                } else if (ecmContext.getAltMotoL() == 3) {//Menu Moto Mec
                    ecmContext.setPosMenu(1);
                    Intent it = new Intent(CaminhaoTurnoActivity.this, MenuMotoMecActivity.class);
                    startActivity(it);
                    finish();
                }

            }
        });

        buttonCancCaminhao.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent it = new Intent(CaminhaoTurnoActivity.this, MotoristaActivity.class);
                startActivity(it);
                finish();
            }

        });


    }

    public void onBackPressed()  {
    }

}
