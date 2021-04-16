package br.com.usinasantafe.ecm.view;

//import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import br.com.usinasantafe.ecm.ECMContext;
import br.com.usinasantafe.ecm.R;
import br.com.usinasantafe.ecm.model.bean.variaveis.PreCECBean;
import br.com.usinasantafe.ecm.util.Tempo;

public class BackupPreCECActivity extends ActivityGeneric {

    private int contador;
    private List precCECList;
    private TextView textViewBkpViagemCana;
    private ECMContext ecmContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backup_pre_cec);

        ecmContext = (ECMContext) getApplication();

        textViewBkpViagemCana = (TextView) findViewById(R.id.textViewBkpViagemCana);
        Button buttonAntBkpViagemCana = (Button) findViewById(R.id.buttonAntBkpViagemCana);
        Button buttonProxBkpViagemCana = (Button) findViewById(R.id.buttonProxBkpViagemCana);
        Button buttonRetornarBkpViagemCana = (Button) findViewById(R.id.buttonRetornarBkpViagemCana);

        precCECList = ecmContext.getCECCTR().getPreCECFechadoList();

        contador = precCECList.size() - 1;

        PreCECBean preCECBean = (PreCECBean) precCECList.get(contador);
        textViewBkpViagemCana.setText(exibirPreCEC(preCECBean));

        buttonAntBkpViagemCana.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(contador < precCECList.size() - 1){
                    contador = contador + 1;
                }

                PreCECBean preCECBean = (PreCECBean) precCECList.get(contador);
                textViewBkpViagemCana.setText(exibirPreCEC(preCECBean));

            }
        });

        buttonProxBkpViagemCana.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(contador > 0){
                    contador = contador - 1;
                }

                PreCECBean preCECBean = (PreCECBean) precCECList.get(contador);
                textViewBkpViagemCana.setText(exibirPreCEC(preCECBean));

            }
        });

        buttonRetornarBkpViagemCana.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent it = new Intent(BackupPreCECActivity.this, MenuCertifActivity.class);
                startActivity(it);
                finish();

            }
        });


    }

    public String exibirPreCEC(PreCECBean preCECBean){

        String retorno = "";

        retorno = retorno + "    VIAGEM    \n";
        retorno = retorno + "MOTORISTA = " + preCECBean.getMoto() + "\n";
        if(preCECBean.getCarr1() != 0){
            retorno = retorno + "CARRETA 1 = " + preCECBean.getCarr1() + "\n";
        }
        if(preCECBean.getCarr2() != 0){
            retorno = retorno + "CARRETA 2 = " + preCECBean.getCarr2() + "\n";
        }
        if(preCECBean.getCarr3() != 0){
            retorno = retorno + "CARRETA 3 = " + preCECBean.getCarr3() + "\n";
        }
        retorno = retorno + "SAÍDA DO CAMPO = " + Tempo.getInstance().dataHoraCTZ(preCECBean.getDataSaidaCampo()) + "\n";

        return retorno;

    }

    public void onBackPressed()  {
    }

}
