package br.com.usinasantafe.ecm;

//import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import br.com.usinasantafe.ecm.util.Tempo;
import br.com.usinasantafe.ecm.model.bean.variaveis.BoletimBkpBean;

public class BoletimBKPActivity extends ActivityGeneric {

    private int contador;
    private List listBoletim;
    private TextView textViewBkpBoletim;
    private ECMContext ecmContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boletim_bkp);

        ecmContext = (ECMContext) getApplication();

        textViewBkpBoletim = (TextView) findViewById(R.id.textViewBkpBoletim);
        Button buttonAntBkpBoletim = (Button) findViewById(R.id.buttonAntBkpBoletim);
        Button buttonProxBkpBoletim = (Button) findViewById(R.id.buttonProxBkpBoletim);
        Button buttonRetornarBkpBoletim = (Button) findViewById(R.id.buttonRetornarBkpBoletim);

        BoletimBkpBean boletimTO = new BoletimBkpBean();
        listBoletim = boletimTO.all();

        contador = listBoletim.size() - 1;

        boletimTO = (BoletimBkpBean) listBoletim.get(contador);
        textViewBkpBoletim.setText(visBoletim(boletimTO));

        buttonAntBkpBoletim.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(contador < listBoletim.size() - 1){
                    contador = contador + 1;
                }

                BoletimBkpBean boletimTO = new BoletimBkpBean();
                boletimTO = (BoletimBkpBean) listBoletim.get(contador);
                textViewBkpBoletim.setText(visBoletim(boletimTO));

            }
        });

        buttonProxBkpBoletim.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(contador > 0){
                    contador = contador - 1;
                }

                BoletimBkpBean boletimTO = new BoletimBkpBean();
                boletimTO = (BoletimBkpBean) listBoletim.get(contador);
                textViewBkpBoletim.setText(visBoletim(boletimTO));

            }
        });

        buttonRetornarBkpBoletim.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent it = new Intent(BoletimBKPActivity.this, MenuCertifActivity.class);
                startActivity(it);
                finish();

            }
        });


    }

    public String visBoletim(BoletimBkpBean boleto){

        String retorno = "";

        int analisar = (int) boleto.getPossuiSorteioBoleto().longValue();

        if(analisar == 0){

            retorno = retorno + "NÃO FOI SORTEADO \n";
            retorno = retorno + "PARA ANALISE! \n";
            retorno = retorno + "PESO LIQ:  "  + boleto.getPesoLiquidoBoleto() + "\n";
            retorno = retorno + "---------------- \n";
            retorno = retorno + "" + Tempo.getInstance().dataHoraCTZ(boleto.getDthrEntradaBoleto()) + " \n";

        }
        else if(analisar == 1){

            if((boleto.getUnidadeSorteada1Boleto() != 0) &&
                    (boleto.getUnidadeSorteada2Boleto() != 0) &&
                    (boleto.getUnidadeSorteada3Boleto() != 0)){

                retorno = retorno + "Cargas Sorteadas \n";
                retorno = retorno + "" + boleto.getUnidadeSorteada1Boleto() + "       " + boleto.getUnidadeSorteada2Boleto() + " \n";
                retorno = retorno + "CEC: " + boleto.getCecSorteado1Boleto() + "  " + boleto.getCecSorteado2Boleto() + " \n";
                retorno = retorno + "Frente: " + boleto.getCdFrenteBoleto() + " \n";
                retorno = retorno + "Peso Liq:  "  + boleto.getPesoLiquidoBoleto() + " \n";
                retorno = retorno + "" + Tempo.getInstance().dataHoraCTZ(boleto.getDthrEntradaBoleto()) + " \n";

            }
            else if((boleto.getUnidadeSorteada1Boleto() == 0) &&
                    (boleto.getUnidadeSorteada2Boleto() != 0) &&
                    (boleto.getUnidadeSorteada3Boleto() != 0)){

                retorno = retorno + "Cargas Sorteadas \n";
                retorno = retorno + "" + boleto.getUnidadeSorteada2Boleto() + "       " + boleto.getUnidadeSorteada3Boleto() + " \n";
                retorno = retorno + "CEC: " + boleto.getCecSorteado2Boleto() + "  " + boleto.getCecSorteado3Boleto() + " \n";
                retorno = retorno + "Frente: " + boleto.getCdFrenteBoleto() + " \n";
                retorno = retorno + "Peso Liq:  "  + boleto.getPesoLiquidoBoleto() + " \n";
                retorno = retorno + "" + Tempo.getInstance().dataHoraCTZ(boleto.getDthrEntradaBoleto()) + " \n";

            }
            else if((boleto.getUnidadeSorteada1Boleto() != 0) &&
                    (boleto.getUnidadeSorteada2Boleto() == 0) &&
                    (boleto.getUnidadeSorteada3Boleto() != 0)){

                retorno = retorno + "Cargas Sorteadas \n";
                retorno = retorno + "" + boleto.getUnidadeSorteada1Boleto() + "       " + boleto.getUnidadeSorteada3Boleto() + " \n";
                retorno = retorno + "CEC: " + boleto.getCecSorteado1Boleto() + "  " + boleto.getCecSorteado3Boleto() + " \n";
                retorno = retorno + "Frente: " + boleto.getCdFrenteBoleto() + " \n";
                retorno = retorno + "Peso Liq:  "  + boleto.getPesoLiquidoBoleto() + " \n";
                retorno = retorno + "" + Tempo.getInstance().dataHoraCTZ(boleto.getDthrEntradaBoleto()) + " \n";

            }
            else if((boleto.getUnidadeSorteada1Boleto() != 0) &&
                    (boleto.getUnidadeSorteada2Boleto() != 0) &&
                    (boleto.getUnidadeSorteada3Boleto() == 0)){

                retorno = retorno + "Cargas Sorteadas \n";
                retorno = retorno + "" + boleto.getUnidadeSorteada1Boleto() + "       " + boleto.getUnidadeSorteada2Boleto() + " \n";
                retorno = retorno + "CEC: " + boleto.getCecSorteado1Boleto() + "  " + boleto.getCecSorteado2Boleto() + " \n";
                retorno = retorno + "Frente: " + boleto.getCdFrenteBoleto() + " \n";
                retorno = retorno + "Peso Liq:  "  + boleto.getPesoLiquidoBoleto() + " \n";
                retorno = retorno + "" + Tempo.getInstance().dataHoraCTZ(boleto.getDthrEntradaBoleto()) + " \n";

            }
            else if((boleto.getUnidadeSorteada1Boleto() != 0) &&
                    (boleto.getUnidadeSorteada2Boleto() == 0) &&
                    (boleto.getUnidadeSorteada3Boleto() == 0)){

                retorno = retorno + "Cargas Sorteadas \n";
                retorno = retorno + ""+ boleto.getUnidadeSorteada1Boleto() + " \n";
                retorno = retorno + "CEC: " + boleto.getCecSorteado1Boleto() + " \n";
                retorno = retorno + "Frente: " + boleto.getCdFrenteBoleto() + " \n";
                retorno = retorno + "Peso Liq:  "  + boleto.getPesoLiquidoBoleto() + " \n";
                retorno = retorno + "" + Tempo.getInstance().dataHoraCTZ(boleto.getDthrEntradaBoleto()) + " \n";

            }
            else if((boleto.getUnidadeSorteada1Boleto() == 0) &&
                    (boleto.getUnidadeSorteada2Boleto() != 0) &&
                    (boleto.getUnidadeSorteada3Boleto() == 0)){

                retorno = retorno + "Cargas Sorteadas \n";
                retorno = retorno + ""+ boleto.getUnidadeSorteada2Boleto() + " \n";
                retorno = retorno + "CEC: " + boleto.getCecSorteado2Boleto() + " \n";
                retorno = retorno + "Frente: " + boleto.getCdFrenteBoleto() + " \n";
                retorno = retorno + "Peso Liq:  "  + boleto.getPesoLiquidoBoleto() + " \n";
                retorno = retorno + "" + Tempo.getInstance().dataHoraCTZ(boleto.getDthrEntradaBoleto()) + " \n";

            }
            else if((boleto.getUnidadeSorteada1Boleto() == 0) &&
                    (boleto.getUnidadeSorteada2Boleto() == 0) &&
                    (boleto.getUnidadeSorteada3Boleto() != 0)){



                retorno = retorno + "Cargas Sorteadas \n";
                retorno = retorno + ""+ boleto.getUnidadeSorteada3Boleto() + " \n";
                retorno = retorno + "CEC: " + boleto.getCecSorteado3Boleto() + " \n";
                retorno = retorno + "Frente: " + boleto.getCdFrenteBoleto() + " \n";
                retorno = retorno + "Peso Liq:  "  + boleto.getPesoLiquidoBoleto() + " \n";
                retorno = retorno + "" + Tempo.getInstance().dataHoraCTZ(boleto.getDthrEntradaBoleto()) + " \n";

            }

        }

        return retorno;

    }

    public void onBackPressed()  {
    }

}
