package br.com.usinasantafe.ecm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.ecm.pst.EspecificaPesquisa;
import br.com.usinasantafe.ecm.to.tb.estaticas.LiberacaoTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.InfBoletimTO;

public class MsgLiberacaoActivity extends ActivityGeneric {

    private ECMContext ecmContext;
    private LiberacaoTO liberacaoTOBD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg_liberacao);

        ecmContext = (ECMContext) getApplication();
        Button buttonOkMsgLiberacao = (Button) findViewById(R.id.buttonOkMsgLiberacao);
        Button buttonCancMsgLiberacao = (Button) findViewById(R.id.buttonCancMsgLiberacao);
        TextView textViewMsgLiberacao = (TextView) findViewById(R.id.textViewMsgLiberacao);

        LiberacaoTO liberacaoTOBDPesq = new LiberacaoTO();

        ArrayList arrayList = new ArrayList();

        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("codigoLiberacao");
        pesquisa.setValor(ecmContext.getLiberacaoOS());

        arrayList.add(pesquisa);

        EspecificaPesquisa pesquisa2 = new EspecificaPesquisa();
        pesquisa2.setCampo("nroOSLiberacao");
        pesquisa2.setValor(ecmContext.getNroOS());

        arrayList.add(pesquisa2);

        List lista = liberacaoTOBDPesq.get(arrayList);
        liberacaoTOBD = (LiberacaoTO) lista.get(0);

        textViewMsgLiberacao.setText(liberacaoTOBD.getCodFazendaLiberacao() + " - " + liberacaoTOBD.getNomeFazendaLiberacao());

        buttonOkMsgLiberacao.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Long tipoLiberacao = liberacaoTOBD.getTipoLiberacao();
                Long codLiberacao = liberacaoTOBD.getCodigoLiberacao();

                InfBoletimTO infBoletimTO = new InfBoletimTO();
                List infBoletimTOList = infBoletimTO.all();
                infBoletimTO = (InfBoletimTO) infBoletimTOList.get(0);

                switch(ecmContext.getNumCarreta()){
                    case 0:
                        infBoletimTO.setLibCam(codLiberacao);
                        ecmContext.setNumCarreta(1);
                        break;
                    case 1:
                        infBoletimTO.setLibCarr1(codLiberacao);
                        ecmContext.setNumCarreta(2);
                        break;
                    case 2:
                        infBoletimTO.setLibCarr2(codLiberacao);
                        ecmContext.setNumCarreta(3);
                        break;
                    case 3:
                        infBoletimTO.setLibCarr3(codLiberacao);
                        ecmContext.setNumCarreta(4);
                        break;
                }

                if((tipoLiberacao == 3) || (tipoLiberacao == 4)){

                    switch(ecmContext.getNumCarreta() - 1){

                        case 0:
                            infBoletimTO.setMaqCam((long) 0);
                            infBoletimTO.setOpCam((long) 0);
                            break;
                        case 1:
                            infBoletimTO.setMaqCarr1((long) 0);
                            infBoletimTO.setOpCarr1((long) 0);
                            break;
                        case 2:
                            infBoletimTO.setMaqCarr2((long) 0);
                            infBoletimTO.setOpCarr2((long) 0);
                            break;
                        case 3:
                            infBoletimTO.setMaqCarr3((long) 0);
                            infBoletimTO.setOpCarr3((long) 0);
                            break;

                    }

                    infBoletimTO.update();
                    Intent it = new Intent(MsgLiberacaoActivity.this, MsgNumCarretaActivity.class);
                    startActivity(it);
                    finish();
                }
                else if((tipoLiberacao == 1) || (tipoLiberacao == 2)){
                    Intent it = new Intent(MsgLiberacaoActivity.this, MaquinaActivity.class);
                    startActivity(it);
                    finish();
                }

            }
        });

        buttonCancMsgLiberacao.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent it = new Intent(MsgLiberacaoActivity.this, LiberacaoActivity.class);
                startActivity(it);
                finish();
            }
        });

    }

    public void onBackPressed()  {
    }

}
