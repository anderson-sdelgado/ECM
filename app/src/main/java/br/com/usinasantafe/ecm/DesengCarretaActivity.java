package br.com.usinasantafe.ecm;

import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import br.com.usinasantafe.ecm.model.bean.variaveis.CarretaBean;
import br.com.usinasantafe.ecm.util.ConexaoWeb;

public class DesengCarretaActivity extends ActivityGeneric {

    private ECMContext ecmContext;
    private TextView textViewMsgDesengCarreta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deseng_carreta);

        textViewMsgDesengCarreta = (TextView) findViewById(R.id.textViewMsgDesengCarreta);

        ecmContext = (ECMContext) getApplication();

        Button buttonSimDesengate = (Button) findViewById(R.id.buttonSimDesengate);
        Button buttonNaoDesengate = (Button) findViewById(R.id.buttonNaoDesengate);

        textViewMsgDesengCarreta.setText(ecmContext.getMotoMecCTR().getDescrCarreta());

        buttonSimDesengate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ecmContext.getMotoMecCTR().delCarreta();

                if (ecmContext.getVerPosTela() == 3){
                    Intent it = new Intent(DesengCarretaActivity.this, MenuMotoMecActivity.class);
                    startActivity(it);
                    finish();
                }
                else if(ecmContext.getVerPosTela() == 6){
                    Intent it = new Intent(DesengCarretaActivity.this, ListaParadaActivity.class);
                    startActivity(it);
                    finish();
                }

                Long statusCon;
                ConexaoWeb conexaoWeb = new ConexaoWeb();
                if (conexaoWeb.verificaConexao(DesengCarretaActivity.this)) {
                    statusCon = 1L;
                }
                else{
                    statusCon = 0L;
                }

                ecmContext.getMotoMecCTR().insApontMM(getLongitude(), getLatitude(), statusCon);

            }
        });

        buttonNaoDesengate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ecmContext.getVerPosTela() == 3){
                    Intent it = new Intent(DesengCarretaActivity.this, MenuMotoMecActivity.class);
                    startActivity(it);
                    finish();
                }
                else if(ecmContext.getVerPosTela() == 6){
                    Intent it = new Intent(DesengCarretaActivity.this, ListaParadaActivity.class);
                    startActivity(it);
                    finish();
                }
            }
        });

    }

    public void onBackPressed()  {
    }

}
