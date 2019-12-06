package br.com.usinasantafe.ecm;

import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import br.com.usinasantafe.ecm.model.bean.variaveis.CarretaEngDesengBean;

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

        CarretaEngDesengBean carretaEngDesengBean = new CarretaEngDesengBean();
        String mensagem = "DESEJA DESENGATAR A(S) CARRETA(S):";
        List carretaList = carretaEngDesengBean.all();

        for(int i = 0; i < carretaList.size(); i++){
            carretaEngDesengBean = (CarretaEngDesengBean) carretaList.get(i);
            mensagem = mensagem + "\nCAR " + carretaEngDesengBean.getPosCarreta() + ": " + carretaEngDesengBean.getNumCarreta();
        }

        textViewMsgDesengCarreta.setText(mensagem);

        buttonSimDesengate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CarretaEngDesengBean carretaEngDesengBean = new CarretaEngDesengBean();
                carretaEngDesengBean.deleteAll();

                if (ecmContext.getVerPosTela() == 3){
                    Intent it = new Intent(DesengateCarretaActivity.this, ListaMotoMecActivity.class);
                    startActivity(it);
                    finish();
                }
                else if(ecmContext.getVerPosTela() == 6){
                    Intent it = new Intent(DesengateCarretaActivity.this, ListaParadaActivity.class);
                    startActivity(it);
                    finish();
                }

                ecmContext.getMotoMecCTR().salvaMotoMec(ecmContext.getMotoMecCTR().getDesengateCarreta());

            }
        });

        buttonNaoDesengate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ecmContext.getVerPosTela() == 3){
                    Intent it = new Intent(DesengateCarretaActivity.this, ListaMotoMecActivity.class);
                    startActivity(it);
                    finish();
                }
                else if(ecmContext.getVerPosTela() == 6){
                    Intent it = new Intent(DesengateCarretaActivity.this, ListaParadaActivity.class);
                    startActivity(it);
                    finish();
                }
            }
        });

    }

    public void onBackPressed()  {
    }

}
