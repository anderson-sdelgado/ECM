package br.com.usinasantafe.ecm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

import br.com.usinasantafe.ecm.model.bean.variaveis.CertifCanaBean;
import br.com.usinasantafe.ecm.util.ManipDadosEnvio;
import br.com.usinasantafe.ecm.util.Tempo;

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

                CertifCanaBean certifCanaBean = new CertifCanaBean();
                List compVCanaList = certifCanaBean.get("status", 1L);
                certifCanaBean = (CertifCanaBean) compVCanaList.get(0);
                compVCanaList.clear();

                certifCanaBean.setDataSaidaCampo(Tempo.getInstance().dataComHora());
                certifCanaBean.update();

                ecmContext.getApontMotoMecBean().setOpCor((long) 437);
                ManipDadosEnvio.getInstance().salvaMotoMec(ecmContext.getApontMotoMecBean());

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
