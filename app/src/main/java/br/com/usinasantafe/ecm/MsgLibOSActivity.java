package br.com.usinasantafe.ecm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.usinasantafe.ecm.model.bean.estaticas.RLibOSBean;

public class MsgLibOSActivity extends ActivityGeneric {

    private ECMContext ecmContext;
    private RLibOSBean RLibOSBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg_liberacao);

        ecmContext = (ECMContext) getApplication();
        Button buttonOkMsgLiberacao = (Button) findViewById(R.id.buttonOkMsgLiberacao);
        Button buttonCancMsgLiberacao = (Button) findViewById(R.id.buttonCancMsgLiberacao);
        TextView textViewMsgLiberacao = (TextView) findViewById(R.id.textViewMsgLiberacao);

        RLibOSBean = ecmContext.getCECCTR().getRLibOSBean();

        textViewMsgLiberacao.setText(RLibOSBean.getCodFazenda() + " - " + RLibOSBean.getDescrFazenda());

        buttonOkMsgLiberacao.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent it = new Intent(MsgLibOSActivity.this, MsgNumCarretaActivity.class);
                startActivity(it);
                finish();

            }
        });

        buttonCancMsgLiberacao.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent it = new Intent(MsgLibOSActivity.this, LibOSActivity.class);
                startActivity(it);
                finish();
            }
        });

    }

    public void onBackPressed()  {
    }

}
