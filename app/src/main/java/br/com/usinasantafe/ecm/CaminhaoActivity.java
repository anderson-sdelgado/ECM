package br.com.usinasantafe.ecm;

//import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

        import br.com.usinasantafe.ecm.model.bean.variaveis.ConfigBean;

public class CaminhaoActivity extends ActivityGeneric {

    private ECMContext ecmContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caminhao);

        TextView textViewCodCaminhaoTurno = (TextView) findViewById(R.id.textViewCodCaminhaoTurno);
        Button buttonOkCaminhao = (Button) findViewById(R.id.buttonOkCaminhao);
        Button buttonCancCaminhao = (Button) findViewById(R.id.buttonCancCaminhao);

        ecmContext = (ECMContext) getApplication();

        textViewCodCaminhaoTurno.setText(String.valueOf(ecmContext.getConfigCTR().getCodEquipConfig()));

        buttonOkCaminhao.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(ecmContext.getVerPosTela() == 5){


                    if(ecmContext.getConfigCTR().getEquip().getClasseEquip() == 1){

                        Intent it = new Intent(CaminhaoActivity.this, OSActivity.class);
                        startActivity(it);
                        finish();

                    }
                    else if(ecmContext.getConfigCTR().getEquip().getClasseEquip() == 6){

                        ecmContext.getCertifCanaCTR().setLibCam(0L);

                        Intent it = new Intent(CaminhaoActivity.this, MsgNumCarretaActivity.class);
                        startActivity(it);
                        finish();
                    }

                }
                else{
                    Intent it = new Intent(CaminhaoActivity.this, ListaTurnoActivity.class);
                    startActivity(it);
                    finish();
                }




            }

        });

        buttonCancCaminhao.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (ecmContext.getVerPosTela() == 1) {
                    Intent it = new Intent(CaminhaoActivity.this, MotoristaActivity.class);
                    startActivity(it);
                    finish();
                }

//                Intent it = new Intent(CaminhaoActivity.this, CertificadoActivity.class);
//                startActivity(it);
//                finish();

            }

        });


    }

    public void onBackPressed()  {
    }

}
