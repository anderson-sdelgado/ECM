package br.com.usinasantafe.ecm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.ecm.model.bean.pst.PesquisaBean;
import br.com.usinasantafe.ecm.model.bean.estaticas.LiberacaoBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.CertifCanaBean;

public class MsgLiberacaoActivity extends ActivityGeneric {

    private ECMContext ecmContext;
    private LiberacaoBean liberacaoBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg_liberacao);

        ecmContext = (ECMContext) getApplication();
        Button buttonOkMsgLiberacao = (Button) findViewById(R.id.buttonOkMsgLiberacao);
        Button buttonCancMsgLiberacao = (Button) findViewById(R.id.buttonCancMsgLiberacao);
        TextView textViewMsgLiberacao = (TextView) findViewById(R.id.textViewMsgLiberacao);

        LiberacaoBean liberacaoBeanBDPesq = new LiberacaoBean();

        ArrayList arrayList = new ArrayList();

        PesquisaBean pesquisa1 = new PesquisaBean();
        pesquisa1.setCampo("codigoLiberacao");
        pesquisa1.setValor(ecmContext.getLiberacaoOS());

        arrayList.add(pesquisa1);

        PesquisaBean pesquisa2 = new PesquisaBean();
        pesquisa2.setCampo("nroOSLiberacao");
        pesquisa2.setValor(ecmContext.getNroOS());

        arrayList.add(pesquisa2);

        List lista = liberacaoBeanBDPesq.get(arrayList);
        liberacaoBean = (LiberacaoBean) lista.get(0);

        textViewMsgLiberacao.setText(liberacaoBean.getCodFazendaLiberacao() + " - " + liberacaoBean.getNomeFazendaLiberacao());

        buttonOkMsgLiberacao.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Long codLib = liberacaoBean.getCodigoLiberacao();

                CertifCanaBean certifCanaBean = new CertifCanaBean();
                List compVCanaList = certifCanaBean.get("status", 1L);
                certifCanaBean = (CertifCanaBean) compVCanaList.get(0);
                compVCanaList.clear();

                switch(ecmContext.getNumCarreta()){
                    case 0:
                        certifCanaBean.setLibCam(codLib);
                        ecmContext.setNumCarreta(1);
                        break;
                    case 1:
                        certifCanaBean.setLibCarr1(codLib);
                        ecmContext.setNumCarreta(2);
                        break;
                    case 2:
                        certifCanaBean.setLibCarr2(codLib);
                        ecmContext.setNumCarreta(3);
                        break;
                    case 3:
                        certifCanaBean.setLibCarr3(codLib);
                        ecmContext.setNumCarreta(4);
                        break;
                    case 4:
                        certifCanaBean.setLibCarr4(codLib);
                        ecmContext.setNumCarreta(5);
                        break;
                }

                certifCanaBean.update();


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
