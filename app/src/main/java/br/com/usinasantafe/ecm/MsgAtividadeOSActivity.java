package br.com.usinasantafe.ecm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import br.com.usinasantafe.ecm.model.bean.estaticas.AtividadeOSBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.CertifCanaBean;

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

        AtividadeOSBean atividadeOsTOBD = new AtividadeOSBean();
        List lista = atividadeOsTOBD.get("codigoAtivOS", ecmContext.getCodigoAtivOS());
        AtividadeOSBean atividadeOsTO = (AtividadeOSBean) lista.get(0);

        descAtividade = atividadeOsTO.getCodFazendaAtivOS() + " - " + atividadeOsTO.getNomeFazendaAtivOS();
        codAtivOS = atividadeOsTO.getCodigoAtivOS();
        ecmContext.setNroOS(atividadeOsTO.getNroOSAtivOS());

        textViewNomeAtividade.setText(descAtividade);

        buttonOkMsgAtivOS.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                CertifCanaBean certifCanaBean = new CertifCanaBean();
                List compVCanaList = certifCanaBean.get("status", 1L);
                certifCanaBean = (CertifCanaBean) compVCanaList.get(0);
                certifCanaBean.setAtivOS(codAtivOS);
                certifCanaBean.update();

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
