package br.com.usinasantafe.ecm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import br.com.usinasantafe.ecm.to.tb.estaticas.AtividadeOSTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.AtividadeOsTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.InfBoletimTO;

public class MsgAtividadeOSActivity extends ActivityGeneric {

    private Long codigoativos;
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
        codigoativos = atividadeOsTO.getCodigoAtivOS();
        ecmContext.setNroOS(atividadeOsTO.getNroOSAtivOS());

        textViewNomeAtividade.setText(descAtividade);

        buttonOkMsgAtivOS.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                AtividadeOsTO atividadeOs = new AtividadeOsTO();
                atividadeOs.deleteAll();
                atividadeOs.setAtivOS(codigoativos);
                atividadeOs.estado = 1L;
                atividadeOs.insert();

                InfBoletimTO infBoletimTO = new InfBoletimTO();
                List lTurno = infBoletimTO.all();
                infBoletimTO = (InfBoletimTO) lTurno.get(0);

                if (infBoletimTO.getTipoAtiv() == 1) {
                    Intent it = new Intent(MsgAtividadeOSActivity.this, CertificadoActivity.class);
                    startActivity(it);
                    finish();
                } else if (infBoletimTO.getTipoAtiv() == 2) {
                    Intent it = new Intent(MsgAtividadeOSActivity.this, VerMotoristaActivity.class);
                    startActivity(it);
                    finish();
                }
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
