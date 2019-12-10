package br.com.usinasantafe.ecm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import br.com.usinasantafe.ecm.model.bean.estaticas.RAtivOSBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.CertifCanaBean;

public class MsgAtivOSActivity extends ActivityGeneric {

    private RAtivOSBean rAtivOSBean;
    private String descAtividade;
    private ECMContext ecmContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg_atividade_os);

        ecmContext = (ECMContext) getApplication();

        Button buttonOkMsgAtivOS = (Button) findViewById(R.id.buttonOkMsgAtivOS);
        Button buttonCancMsgAtivOS = (Button) findViewById(R.id.buttonCancMsgAtivOS);

        TextView textViewNomeAtividade = (TextView) findViewById(R.id.textViewNomeAtividade);

        rAtivOSBean = ecmContext.getCertifCanaCTR().getAtivOS();

//        codAtivOS = atividadeOsTO.getIdRAtivOS();
//        ecmContext.setNroOS(atividadeOsTO.getNroOS());

        textViewNomeAtividade.setText(rAtivOSBean.getCodFazenda() + " - " + rAtivOSBean.getDescrFazenda());

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
