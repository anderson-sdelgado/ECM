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
import br.com.usinasantafe.ecm.to.tb.variaveis.CompVCanaTO;

public class MsgLiberacaoActivity extends ActivityGeneric {

    private ECMContext ecmContext;
    private LiberacaoTO liberacaoTO;

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

        EspecificaPesquisa pesquisa1 = new EspecificaPesquisa();
        pesquisa1.setCampo("codigoLiberacao");
        pesquisa1.setValor(ecmContext.getLiberacaoOS());

        arrayList.add(pesquisa1);

        EspecificaPesquisa pesquisa2 = new EspecificaPesquisa();
        pesquisa2.setCampo("nroOSLiberacao");
        pesquisa2.setValor(ecmContext.getNroOS());

        arrayList.add(pesquisa2);

        List lista = liberacaoTOBDPesq.get(arrayList);
        liberacaoTO = (LiberacaoTO) lista.get(0);

        textViewMsgLiberacao.setText(liberacaoTO.getCodFazendaLiberacao() + " - " + liberacaoTO.getNomeFazendaLiberacao());

        buttonOkMsgLiberacao.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Long codLib = liberacaoTO.getCodigoLiberacao();

                CompVCanaTO compVCanaTO = new CompVCanaTO();
                List compVCanaList = compVCanaTO.get("status", 1L);
                compVCanaTO = (CompVCanaTO) compVCanaList.get(0);
                compVCanaList.clear();

                switch(ecmContext.getNumCarreta()){
                    case 0:
                        compVCanaTO.setLibCam(codLib);
                        ecmContext.setNumCarreta(1);
                        break;
                    case 1:
                        compVCanaTO.setLibCarr1(codLib);
                        ecmContext.setNumCarreta(2);
                        break;
                    case 2:
                        compVCanaTO.setLibCarr2(codLib);
                        ecmContext.setNumCarreta(3);
                        break;
                    case 3:
                        compVCanaTO.setLibCarr3(codLib);
                        ecmContext.setNumCarreta(4);
                        break;
                    case 4:
                        compVCanaTO.setLibCarr4(codLib);
                        ecmContext.setNumCarreta(5);
                        break;
                }

                compVCanaTO.update();


                Intent it = new Intent(MsgLiberacaoActivity.this, MsgNumCarretaActivity.class);
                startActivity(it);
                finish();


            }
        });

        buttonCancMsgLiberacao.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent it = new Intent(MsgLiberacaoActivity.this, LiberacaoActivity.class);
                startActivity(it);
                finish();
            }
        });

    }

    public void onBackPressed()  {
    }

}
