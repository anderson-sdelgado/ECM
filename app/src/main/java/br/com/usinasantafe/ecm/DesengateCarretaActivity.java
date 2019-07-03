package br.com.usinasantafe.ecm;

import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import br.com.usinasantafe.ecm.bo.ManipDadosEnvio;
import br.com.usinasantafe.ecm.to.tb.variaveis.CarretaEngDesengTO;

public class DesengateCarretaActivity extends ActivityGeneric {

    private ECMContext ecmContext;
    private TextView textViewMsgDesengCarreta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desengate_carreta);

        textViewMsgDesengCarreta = (TextView) findViewById(R.id.textViewMsgDesengCarreta);

        ecmContext = (ECMContext) getApplication();

        Button buttonSimDesengate = (Button) findViewById(R.id.buttonSimDesengate);
        Button buttonNaoDesengate = (Button) findViewById(R.id.buttonNaoDesengate);

        CarretaEngDesengTO carretaEngDesengTO = new CarretaEngDesengTO();
        String mensagem = "DESEJA DESENGATAR A(S) CARRETA(S):";
        List listCarreta = carretaEngDesengTO.all();

        for(int i = 0; i < listCarreta.size(); i++){
            carretaEngDesengTO = (CarretaEngDesengTO) listCarreta.get(i);
            mensagem = mensagem + "\nCAR " + carretaEngDesengTO.getPosCarreta() + ": " + carretaEngDesengTO.getNumCarreta();
        }

        textViewMsgDesengCarreta.setText(mensagem);

        buttonSimDesengate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CarretaEngDesengTO carretaEngDesengTO = new CarretaEngDesengTO();
                carretaEngDesengTO.deleteAll();
                if (ecmContext.getApontMotoMecTO().getTipoEngDeseng() < 5){
                    Intent it = new Intent(DesengateCarretaActivity.this, MenuMotoMecActivity.class);
                    startActivity(it);
                    finish();
                }
                else
                {
                    Intent it = new Intent(DesengateCarretaActivity.this, MotivoParadaActivity.class);
                    startActivity(it);
                    finish();
                }


                ManipDadosEnvio.getInstance().salvaMotoMec(ecmContext.getApontMotoMecTO());

            }
        });

        buttonNaoDesengate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ecmContext.getApontMotoMecTO().getTipoEngDeseng() < 5){
                    Intent it = new Intent(DesengateCarretaActivity.this, MenuMotoMecActivity.class);
                    startActivity(it);
                    finish();
                }
                else
                {
                    Intent it = new Intent(DesengateCarretaActivity.this, MotivoParadaActivity.class);
                    startActivity(it);
                    finish();
                }
            }
        });


    }

    public void onBackPressed()  {
    }

}
