package br.com.usinasantafe.ecm.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.usinasantafe.ecm.ECMContext;
import br.com.usinasantafe.ecm.R;
import br.com.usinasantafe.ecm.util.ConexaoWeb;

public class MsgNumCarretaActivity extends ActivityGeneric {

    private ECMContext ecmContext;
    private int numCarreta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg_num_carreta);

        ecmContext = (ECMContext) getApplication();

        TextView textViewMsgNumCarreta = (TextView) findViewById(R.id.textViewMsgNumCarreta);

        numCarreta = ecmContext.getMotoMecCTR().qtdeCarreta() + 1;
        if (ecmContext.getVerPosTela() == 5){
            textViewMsgNumCarreta.setText("DESEJA INSERIR A CARRETA " + numCarreta +"?");
        }
        else{
            textViewMsgNumCarreta.setText("DESEJA ENGATAR A CARRETA " + numCarreta + "?");
        }

        Button buttonOkMsgNumCarreta = (Button) findViewById(R.id.buttonOkMsgNumCarreta);
        Button buttonCancMsgNumCarreta = (Button) findViewById(R.id.buttonCancMsgNumCarreta);

        buttonOkMsgNumCarreta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (numCarreta < 4) {
                    Intent it = new Intent(MsgNumCarretaActivity.this, CarretaActivity.class);
                    startActivity(it);
                    finish();
                } else {

                    AlertDialog.Builder alerta = new AlertDialog.Builder(MsgNumCarretaActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("PROIBIDO A INSERÇÃO DE MAIS DE 3 CARRETAS.");

                    alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });

                    alerta.show();

                }
            }
        });

        buttonCancMsgNumCarreta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ecmContext.getVerPosTela() == 4){
                    if(numCarreta < 1){
                        Long statusCon;
                        ConexaoWeb conexaoWeb = new ConexaoWeb();
                        if (conexaoWeb.verificaConexao(MsgNumCarretaActivity.this)) {
                            statusCon = 1L;
                        }
                        else{
                            statusCon = 0L;
                        }
                        ecmContext.getMotoMecCTR().insApontMM(getLongitude(), getLatitude(), statusCon);
                    }
                    Intent it = new Intent(MsgNumCarretaActivity.this, MenuMotoMecActivity.class);
                    startActivity(it);
                    finish();
                }
                else if (ecmContext.getVerPosTela() == 7){
                    Intent it = new Intent(MsgNumCarretaActivity.this, ListaParadaActivity.class);
                    startActivity(it);
                    finish();
                }
                else{
                    Intent it = new Intent(MsgNumCarretaActivity.this, ListaFinalizaApontActivity.class);
                    startActivity(it);
                    finish();
                }
            }
        });

    }

    public void onBackPressed()  {
    }

}
