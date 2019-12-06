package br.com.usinasantafe.ecm;

//import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import br.com.usinasantafe.ecm.model.bean.variaveis.CertifCanaBkpBean;
import br.com.usinasantafe.ecm.util.Tempo;

public class BackupViagemCanaActivity extends ActivityGeneric {

    private int contador;
    private List listViagemCana;
    private TextView textViewBkpViagemCana;
    private ECMContext ecmContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backup_viagem_cana);

        ecmContext = (ECMContext) getApplication();

        textViewBkpViagemCana = (TextView) findViewById(R.id.textViewBkpViagemCana);
        Button buttonAntBkpViagemCana = (Button) findViewById(R.id.buttonAntBkpViagemCana);
        Button buttonProxBkpViagemCana = (Button) findViewById(R.id.buttonProxBkpViagemCana);
        Button buttonRetornarBkpViagemCana = (Button) findViewById(R.id.buttonRetornarBkpViagemCana);

        CertifCanaBkpBean certifCanaBkpBean = new CertifCanaBkpBean();
        listViagemCana = certifCanaBkpBean.all();

        contador = listViagemCana.size() - 1;

        certifCanaBkpBean = (CertifCanaBkpBean) listViagemCana.get(contador);
        textViewBkpViagemCana.setText(visViagemCana(certifCanaBkpBean));

        buttonAntBkpViagemCana.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(contador < listViagemCana.size() - 1){
                    contador = contador + 1;
                }

                CertifCanaBkpBean certifCanaBkpBean = new CertifCanaBkpBean();
                certifCanaBkpBean = (CertifCanaBkpBean) listViagemCana.get(contador);
                textViewBkpViagemCana.setText(visViagemCana(certifCanaBkpBean));

            }
        });

        buttonProxBkpViagemCana.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(contador > 0){
                    contador = contador - 1;
                }

                CertifCanaBkpBean certifCanaBkpBean = new CertifCanaBkpBean();
                certifCanaBkpBean = (CertifCanaBkpBean) listViagemCana.get(contador);
                textViewBkpViagemCana.setText(visViagemCana(certifCanaBkpBean));

            }
        });

        buttonRetornarBkpViagemCana.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent it = new Intent(BackupViagemCanaActivity.this, MenuInicialApontActivity.class);
                startActivity(it);
                finish();

            }
        });


    }

    public String visViagemCana(CertifCanaBkpBean certifCanaBkpBean){

        String retorno = "";

        retorno = retorno + "    VIAGEM    \n";
        retorno = retorno + "MOTORISTA = " + certifCanaBkpBean.getMoto() + "\n";
        if(certifCanaBkpBean.getCarr1() != 0){
            retorno = retorno + "CARRETA 1 = " + certifCanaBkpBean.getCarr1() + "\n";
        }
        if(certifCanaBkpBean.getCarr2() != 0){
            retorno = retorno + "CARRETA 2 = " + certifCanaBkpBean.getCarr2() + "\n";
        }
        if(certifCanaBkpBean.getCarr3() != 0){
            retorno = retorno + "CARRETA 3 = " + certifCanaBkpBean.getCarr3() + "\n";
        }
        retorno = retorno + "SAÍDA DO CAMPO = " + Tempo.getInstance().dataHoraCTZ(certifCanaBkpBean.getDataSaidaCampo()) + "\n";

        return retorno;

    }

    public void onBackPressed()  {
    }

}
