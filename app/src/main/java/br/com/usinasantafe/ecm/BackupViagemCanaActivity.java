package br.com.usinasantafe.ecm;

import android.app.Activity;
//import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import br.com.usinasantafe.ecm.bo.Tempo;
import br.com.usinasantafe.ecm.to.tb.variaveis.CompVCanaBkpTO;

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

        CompVCanaBkpTO compVCanaBkpTO = new CompVCanaBkpTO();
        listViagemCana = compVCanaBkpTO.all();

        contador = listViagemCana.size() - 1;

        compVCanaBkpTO = (CompVCanaBkpTO) listViagemCana.get(contador);
        textViewBkpViagemCana.setText(visViagemCana(compVCanaBkpTO));

        buttonAntBkpViagemCana.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(contador < listViagemCana.size() - 1){
                    contador = contador + 1;
                }

                CompVCanaBkpTO compVCanaBkpTO = new CompVCanaBkpTO();
                compVCanaBkpTO = (CompVCanaBkpTO) listViagemCana.get(contador);
                textViewBkpViagemCana.setText(visViagemCana(compVCanaBkpTO));

            }
        });

        buttonProxBkpViagemCana.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(contador > 0){
                    contador = contador - 1;
                }

                CompVCanaBkpTO compVCanaBkpTO = new CompVCanaBkpTO();
                compVCanaBkpTO = (CompVCanaBkpTO) listViagemCana.get(contador);
                textViewBkpViagemCana.setText(visViagemCana(compVCanaBkpTO));

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

    public String visViagemCana(CompVCanaBkpTO compVCanaBkpTO){

        String retorno = "";

        retorno = retorno + "    VIAGEM    \n";
        retorno = retorno + "MOTORISTA = " + compVCanaBkpTO.getMoto() + "\n";
        if(compVCanaBkpTO.getCarr1() != 0){
            retorno = retorno + "CARRETA 1 = " + compVCanaBkpTO.getCarr1() + "\n";
        }
        if(compVCanaBkpTO.getCarr2() != 0){
            retorno = retorno + "CARRETA 2 = " + compVCanaBkpTO.getCarr2() + "\n";
        }
        if(compVCanaBkpTO.getCarr3() != 0){
            retorno = retorno + "CARRETA 3 = " + compVCanaBkpTO.getCarr3() + "\n";
        }
        retorno = retorno + "SAÍDA DO CAMPO = " + Tempo.getInstance().dataHoraCTZ(compVCanaBkpTO.getDataSaidaCampo()) + "\n";

        return retorno;

    }

    public void onBackPressed()  {
    }

}
