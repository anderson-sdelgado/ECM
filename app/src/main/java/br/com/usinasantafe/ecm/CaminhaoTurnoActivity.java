package br.com.usinasantafe.ecm;

import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import br.com.usinasantafe.ecm.to.tb.variaveis.ConfigTO;

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

        ConfigTO configTO = new ConfigTO();
        List listConfigTO = configTO.all();
        configTO = (ConfigTO) listConfigTO.get(0);
        textViewCodCaminhao.setText(String.valueOf(configTO.getCodCamConfig()));

        buttonOkCaminhao.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (ecmContext.getTelaAltMoto() == 1) {
                    Intent it = new Intent(CaminhaoTurnoActivity.this, ListaTurnoActivity.class);
                    startActivity(it);
                    finish();
                } else if (ecmContext.getTelaAltMoto() == 2) {
                    Intent it = new Intent(CaminhaoTurnoActivity.this, VerMotoristaActivity.class);
                    startActivity(it);
                    finish();
                } else if (ecmContext.getTelaAltMoto() == 3) {
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

                Intent it = new Intent(CaminhaoTurnoActivity.this, MotoristaActivity.class);
                startActivity(it);
                finish();
            }

        });


    }

    public void onBackPressed()  {
    }

}
