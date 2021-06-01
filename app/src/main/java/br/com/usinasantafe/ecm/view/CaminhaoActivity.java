package br.com.usinasantafe.ecm.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.usinasantafe.ecm.ECMContext;
import br.com.usinasantafe.ecm.R;

public class CaminhaoActivity extends ActivityGeneric {

    private ECMContext ecmContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caminhao);

        TextView textViewCodEquip = (TextView) findViewById(R.id.textViewCodEquip);
        TextView textViewDescEquip = (TextView) findViewById(R.id.textViewDescEquip);
        Button buttonOkCaminhao = (Button) findViewById(R.id.buttonOkCaminhao);
        Button buttonCancCaminhao = (Button) findViewById(R.id.buttonCancCaminhao);

        ecmContext = (ECMContext) getApplication();

        textViewCodEquip.setText(String.valueOf(ecmContext.getConfigCTR().getEquip().getNroEquip()));
        textViewDescEquip.setText(String.valueOf(ecmContext.getConfigCTR().getEquip().getDescrClasseEquip()));

        buttonOkCaminhao.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(ecmContext.getVerPosTela() == 5){

                    ecmContext.getMotoMecCTR().delCarreta();

                    if(ecmContext.getConfigCTR().getEquip().getCodClasseEquip() == 1L){
                        ecmContext.getCECCTR().setLib(ecmContext.getCECCTR().getOSTipoAtiv().getIdLibOS());
                    }

                    Intent it = new Intent(CaminhaoActivity.this, MsgNumCarretaActivity.class);
                    startActivity(it);
                    finish();

                }
                else{

                    ecmContext.getMotoMecCTR().setEquipBol();

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
                    Intent it = new Intent(CaminhaoActivity.this, FuncionarioActivity.class);
                    startActivity(it);
                    finish();
                }
                else if (ecmContext.getVerPosTela() == 5) {
                    Intent it = new Intent(CaminhaoActivity.this, MenuCertifActivity.class);
                    startActivity(it);
                    finish();
                }

            }

        });

    }

    public void onBackPressed()  {
    }

}
