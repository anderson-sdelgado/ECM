package br.com.usinasantafe.ecm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import br.com.usinasantafe.ecm.to.tb.estaticas.AtividadeOSTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.CompVCanaTO;

public class MsgAtividadeOSActivity extends ActivityGeneric {

    private Long codAtivOS;
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

        AtividadeOSTO atividadeOsTOBD = new AtividadeOSTO();
        List lista = atividadeOsTOBD.get("codigoAtivOS", ecmContext.getCodigoAtivOS());
        AtividadeOSTO atividadeOsTO = (AtividadeOSTO) lista.get(0);

        descAtividade = atividadeOsTO.getCodFazendaAtivOS() + " - " + atividadeOsTO.getNomeFazendaAtivOS();
        codAtivOS = atividadeOsTO.getCodigoAtivOS();
        ecmContext.setNroOS(atividadeOsTO.getNroOSAtivOS());

        textViewNomeAtividade.setText(descAtividade);

        buttonOkMsgAtivOS.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                CompVCanaTO compVCanaTO = new CompVCanaTO();
                List compVCanaList = compVCanaTO.get("status", 1L);
                compVCanaTO = (CompVCanaTO) compVCanaList.get(0);
                compVCanaTO.setAtivOS(codAtivOS);
                compVCanaTO.update();

                Intent it = new Intent(MsgAtividadeOSActivity.this, CertificadoActivity.class);
                startActivity(it);
                finish();

            }

        });

        buttonCancMsgAtivOS.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent it = new Intent(MsgAtividadeOSActivity.this, AtividadeOSActivity.class);
                startActivity(it);
                finish();
            }

        });

    }

    public void onBackPressed()  {
    }

}
