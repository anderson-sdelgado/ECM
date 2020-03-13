package br.com.usinasantafe.ecm;

import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import br.com.usinasantafe.ecm.model.bean.variaveis.CarretaUtilBean;
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

        CarretaUtilBean carretaUtilBean = new CarretaUtilBean();
        String mensagem = "DESEJA DESENGATAR A(S) CARRETA(S):";
        List carretaList = ecmContext.getCECCTR().carretaList(2L);
        for(int i = 0; i < carretaList.size(); i++){
            carretaUtilBean = (CarretaUtilBean) carretaList.get(i);
            mensagem = mensagem + "\nCAR " + carretaUtilBean.getPosCarreta() + ": " + carretaUtilBean.getNroEquip();
        }
        carretaList.clear();

        textViewMsgDesengCarreta.setText(mensagem);

        buttonSimDesengate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ecmContext.getCECCTR().delCarreta(2L);

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
