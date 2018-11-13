package br.com.usinasantafe.ecm;

import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import br.com.usinasantafe.ecm.to.tb.estaticas.CaminhaoTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.ConfiguracaoTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.InfBoletimTO;

public class CaminhaoActivity extends ActivityGeneric {

    private ECMContext ecmContext;
    private String caminhao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caminhao);

        ecmContext = (ECMContext) getApplication();
        TextView textViewCodCaminhaoTurno = (TextView) findViewById(R.id.textViewCodCaminhaoTurno);
        ConfiguracaoTO configTO = new ConfiguracaoTO();

        List lista = configTO.all();
        configTO = (ConfiguracaoTO) lista.get(0);//

        caminhao = String.valueOf(configTO.getCamConfig());
        textViewCodCaminhaoTurno.setText(caminhao);

        Button buttonOkCaminhao = (Button) findViewById(R.id.buttonOkCaminhao);
        Button buttonCancCaminhao = (Button) findViewById(R.id.buttonCancCaminhao);

        buttonOkCaminhao.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                CaminhaoTO caminhaoTOBD = new CaminhaoTO();

                List lista = caminhaoTOBD.get("idCaminhao", Long.parseLong(caminhao));
                caminhaoTOBD = (CaminhaoTO) lista.get(0);

                if(caminhaoTOBD.getTipoCaminhao() == 1){
                    ecmContext.setNumCarreta(0);
                    Intent it = new Intent(CaminhaoActivity.this, OSActivity.class);
                    startActivity(it);
                    finish();
                }
                else if(caminhaoTOBD.getTipoCaminhao() == 6){
                    ecmContext.setNumCarreta(1);
                    InfBoletimTO infBoletimTO = new InfBoletimTO();
                    List lTurno = infBoletimTO.all();
                    infBoletimTO = (InfBoletimTO) lTurno.get(0);
                    infBoletimTO.setLibCam(0L);
                    infBoletimTO.setMaqCam(0L);
                    infBoletimTO.setOpCam(0L);
                    infBoletimTO.update();
                    Intent it = new Intent(CaminhaoActivity.this, MsgNumCarretaActivity.class);
                    startActivity(it);
                    finish();
                }

            }

        });

        buttonCancCaminhao.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent it = new Intent(CaminhaoActivity.this, CertificadoActivity.class);
                startActivity(it);

            }

        });


    }

    public void onBackPressed()  {
    }

}
