package br.com.usinasantafe.ecm.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.com.usinasantafe.ecm.ECMContext;
import br.com.usinasantafe.ecm.R;
import br.com.usinasantafe.ecm.util.ConexaoWeb;

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

                Long statusCon;
                ConexaoWeb conexaoWeb = new ConexaoWeb();
                if (conexaoWeb.verificaConexao(MsgSaidaCampoActivity.this)) {
                    statusCon = 1L;
                }
                else{
                    statusCon = 0L;
                }
                ecmContext.getCECCTR().setDataSaidaCampo();

                ecmContext.getMotoMecCTR().setAtivApont(ecmContext.getConfigCTR().getConfig().getAtivConfig());
                ecmContext.getMotoMecCTR().insApontMM(getLongitude(), getLatitude(), statusCon);
//                ecmContext.getMotoMecCTR().insSaídaCampo(getLongitude(), getLatitude(), statusCon);

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
