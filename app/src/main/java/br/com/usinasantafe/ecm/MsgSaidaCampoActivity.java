package br.com.usinasantafe.ecm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MsgSaidaCampoActivity extends ActivityGeneric {

    private ECMContext ecmContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg_saida_campo);

        ecmContext = (ECMContext) getApplication();
        Button buttonSimSaidaCampo = (Button) findViewById(R.id.buttonSimSaidaCampo);
        Button buttonNaoSaidaCampo = (Button) findViewById(R.id.buttonNaoSaidaCampo);

        buttonSimSaidaCampo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ecmContext.getCertifCanaCTR().setDataSaidaCampo();

                Intent it = new Intent(MsgSaidaCampoActivity.this, VerMotoristaActivity.class);
                startActivity(it);
                finish();

            }
        });

        buttonNaoSaidaCampo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    public void onBackPressed()  {
    }

}
