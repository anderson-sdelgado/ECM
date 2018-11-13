package br.com.usinasantafe.ecm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.usinasantafe.ecm.bo.ManipDadosEnvio;
import br.com.usinasantafe.ecm.to.tb.variaveis.CarretaEngDesengTO;

public class MsgCarretaEngateActivity extends ActivityGeneric {

    private ECMContext ecmContext;
    private TextView textViewMsgCarretaEng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg_carreta_engate);

        textViewMsgCarretaEng = (TextView) findViewById(R.id.textViewMsgCarretaEng);

        ecmContext = (ECMContext) getApplication();

        Button buttonSimCarretaEng = (Button) findViewById(R.id.buttonSimCarretaEng);
        Button buttonNaoCarretaEng = (Button) findViewById(R.id.buttonNaoCarretaEng);

        CarretaEngDesengTO carretaEngDesengTO = new CarretaEngDesengTO();
        int qtdeCarreta = carretaEngDesengTO.all().size();
        qtdeCarreta = qtdeCarreta + 1;

        textViewMsgCarretaEng.setText("DESEJA ENGATAR A CARRETA " + qtdeCarreta + "?");

        buttonSimCarretaEng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MsgCarretaEngateActivity.this, CarretaEngateActivity.class);
                startActivity(it);
                finish();
            }
        });

        buttonNaoCarretaEng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ecmContext.getApontMotoMecTO().getTipoEngDeseng() < 5) {
                    Intent it = new Intent(MsgCarretaEngateActivity.this, MenuMotoMecActivity.class);
                    startActivity(it);
                    finish();
                } else {
                    Intent it = new Intent(MsgCarretaEngateActivity.this, MotivoParadaActivity.class);
                    startActivity(it);
                    finish();
                }
                ManipDadosEnvio.getInstance().salvaMotoMec(ecmContext.getApontMotoMecTO());
            }
        });

    }

    public void onBackPressed()  {
    }

}
