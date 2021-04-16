package br.com.usinasantafe.ecm.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.usinasantafe.ecm.ECMContext;
import br.com.usinasantafe.ecm.R;
import br.com.usinasantafe.ecm.model.bean.estaticas.OSBean;


public class MsgAtivOSActivity extends ActivityGeneric {

    private ECMContext ecmContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg_atividade_os);

        ecmContext = (ECMContext) getApplication();

        Button buttonOkMsgAtivOS = (Button) findViewById(R.id.buttonOkMsgAtivOS);
        Button buttonCancMsgAtivOS = (Button) findViewById(R.id.buttonCancMsgAtivOS);

        TextView textViewNomeAtividade = (TextView) findViewById(R.id.textViewNomeAtividade);

        OSBean osBean = ecmContext.getCECCTR().getOSAtiv();
        textViewNomeAtividade.setText(osBean.getIdProprAgr() + " - " + osBean.getDescrProprAgr());

        buttonOkMsgAtivOS.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent it = new Intent(MsgAtivOSActivity.this, CaminhaoActivity.class);
                startActivity(it);
                finish();

            }

        });

        buttonCancMsgAtivOS.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent it = new Intent(MsgAtivOSActivity.this, AtivOSActivity.class);
                startActivity(it);
                finish();
            }

        });

    }

    public void onBackPressed()  {
    }

}
