package br.com.usinasantafe.ecm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

import br.com.usinasantafe.ecm.bo.ManipDadosEnvio;
import br.com.usinasantafe.ecm.bo.Tempo;
import br.com.usinasantafe.ecm.to.tb.variaveis.InfBoletimTO;

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
                // TODO Auto-generated method stub

                InfBoletimTO infBoletimTO = new InfBoletimTO();
                List infBoletimTOList = infBoletimTO.all();
                infBoletimTO = (InfBoletimTO) infBoletimTOList.get(0);
                infBoletimTO.setDataSaidaCampo(Tempo.getInstance().datahora());
                infBoletimTO.update();

                ecmContext.getApontMotoMecTO().setOpcor((long) 437);
                ManipDadosEnvio.getInstance().salvaMotoMec(ecmContext.getApontMotoMecTO());
                Intent it = new Intent(MsgSaidaCampoActivity.this, VerMotoristaActivity.class);
                startActivity(it);
                finish();
            }
        });

        buttonNaoSaidaCampo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
            }
        });

    }

    public void onBackPressed()  {
    }

}
