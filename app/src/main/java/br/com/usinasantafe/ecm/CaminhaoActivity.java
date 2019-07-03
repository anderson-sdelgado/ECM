package br.com.usinasantafe.ecm;

import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import br.com.usinasantafe.ecm.to.tb.estaticas.CaminhaoTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.CompVCanaTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.ConfigTO;

public class CaminhaoActivity extends ActivityGeneric {

    private ECMContext ecmContext;
    private ConfigTO configTO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caminhao);

        ecmContext = (ECMContext) getApplication();
        TextView textViewCodCaminhaoTurno = (TextView) findViewById(R.id.textViewCodCaminhaoTurno);

        configTO = new ConfigTO();
        List lista = configTO.all();
        configTO = (ConfigTO) lista.get(0);//

        textViewCodCaminhaoTurno.setText(String.valueOf(configTO.getCodCamConfig()));

        Button buttonOkCaminhao = (Button) findViewById(R.id.buttonOkCaminhao);
        Button buttonCancCaminhao = (Button) findViewById(R.id.buttonCancCaminhao);

        buttonOkCaminhao.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                CaminhaoTO caminhaoTO = new CaminhaoTO();
                List caminhaoList = caminhaoTO.get("idCaminhao", configTO.getIdCamConfig());
                caminhaoTO = (CaminhaoTO) caminhaoList.get(0);

                if(caminhaoTO.getTipoCaminhao() == 1){
                    ecmContext.setNumCarreta(0);
                    Intent it = new Intent(CaminhaoActivity.this, OSActivity.class);
                    startActivity(it);
                    finish();
                }
                else if(caminhaoTO.getTipoCaminhao() == 6){
                    ecmContext.setNumCarreta(1);

                    CompVCanaTO compVCanaTO = new CompVCanaTO();
                    List compVCanaList = compVCanaTO.get("status", 1L);
                    compVCanaTO = (CompVCanaTO) compVCanaList.get(0);
                    compVCanaTO.setLibCam(0L);
                    compVCanaTO.update();

                    Intent it = new Intent(CaminhaoActivity.this, MsgNumCarretaActivity.class);
                    startActivity(it);
                    finish();
                }

            }

        });

        buttonCancCaminhao.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent it = new Intent(CaminhaoActivity.this, CertificadoActivity.class);
                startActivity(it);

            }

        });


    }

    public void onBackPressed()  {
    }

}
