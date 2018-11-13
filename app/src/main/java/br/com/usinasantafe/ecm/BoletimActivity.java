package br.com.usinasantafe.ecm;

//import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import br.com.usinasantafe.ecm.bo.ManipDadosEnvio;
import br.com.usinasantafe.ecm.bo.ManipDadosReceb;
import br.com.usinasantafe.ecm.bo.Tempo;
import br.com.usinasantafe.ecm.to.tb.variaveis.BoletimBkpTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.BoletimTO;

public class BoletimActivity extends ActivityGeneric {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boletim);

        TextView textViewBoletim = (TextView) findViewById(R.id.textViewBoletim);
        Button buttonOkBoletim = (Button) findViewById(R.id.buttonOkBoletim);

        BoletimTO boletimTO = new BoletimTO();
        List listBoletim = boletimTO.all();
        boletimTO = (BoletimTO) listBoletim.get(0);

        String boletim = visBoletim(boletimTO);

        textViewBoletim.setText(boletim);

        buttonOkBoletim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent it = new Intent(BoletimActivity.this, MenuMotoMecActivity.class);
                startActivity(it);

                ManipDadosReceb.getInstance().atualizarBD();
                Tempo.getInstance().setEnvioDado(true);

            }
        });


    }

    public String visBoletim(BoletimTO boletimTO){

        String retorno = "";

        int analisar = (int) boletimTO.getPossuiSorteioBoleto().longValue();

        if(analisar == 0){

            retorno = retorno + "NÃO FOI SORTEADO \n";
            retorno = retorno + "PARA ANALISE! \n";
            retorno = retorno + "PESO LIQ:  "  + boletimTO.getPesoLiquidoBoleto() + "\n";
            retorno = retorno + "---------------- \n";
            retorno = retorno + "" + boletimTO.getDthrEntradaBoleto() + " \n";

        }
        else if(analisar == 1){

            if((boletimTO.getUnidadeSorteada1Boleto() != 0) &&
                    (boletimTO.getUnidadeSorteada2Boleto() != 0) &&
                    (boletimTO.getUnidadeSorteada3Boleto() != 0)){

                retorno = retorno + "Cargas Sorteadas \n";
                retorno = retorno + "" + boletimTO.getUnidadeSorteada1Boleto() + "       " + boletimTO.getUnidadeSorteada2Boleto() + " \n";
                retorno = retorno + "CEC: " + boletimTO.getCecSorteado1Boleto() + "  " + boletimTO.getCecSorteado2Boleto() + " \n";
                retorno = retorno + "Frente: " + boletimTO.getCdFrenteBoleto() + " \n";
                retorno = retorno + "Peso Liq:  "  + boletimTO.getPesoLiquidoBoleto() + " \n";
                retorno = retorno + "" + boletimTO.getDthrEntradaBoleto() + " \n";

            }
            else if((boletimTO.getUnidadeSorteada1Boleto() == 0) &&
                    (boletimTO.getUnidadeSorteada2Boleto() != 0) &&
                    (boletimTO.getUnidadeSorteada3Boleto() != 0)){

                retorno = retorno + "Cargas Sorteadas \n";
                retorno = retorno + "" + boletimTO.getUnidadeSorteada2Boleto() + "       " + boletimTO.getUnidadeSorteada3Boleto() + " \n";
                retorno = retorno + "CEC: " + boletimTO.getCecSorteado2Boleto() + "  " + boletimTO.getCecSorteado3Boleto() + " \n";
                retorno = retorno + "Frente: " + boletimTO.getCdFrenteBoleto() + " \n";
                retorno = retorno + "Peso Liq:  "  + boletimTO.getPesoLiquidoBoleto() + " \n";
                retorno = retorno + "" + boletimTO.getDthrEntradaBoleto() + " \n";

            }
            else if((boletimTO.getUnidadeSorteada1Boleto() != 0) &&
                    (boletimTO.getUnidadeSorteada2Boleto() == 0) &&
                    (boletimTO.getUnidadeSorteada3Boleto() != 0)){

                retorno = retorno + "Cargas Sorteadas \n";
                retorno = retorno + "" + boletimTO.getUnidadeSorteada1Boleto() + "       " + boletimTO.getUnidadeSorteada3Boleto() + " \n";
                retorno = retorno + "CEC: " + boletimTO.getCecSorteado1Boleto() + "  " + boletimTO.getCecSorteado3Boleto() + " \n";
                retorno = retorno + "Frente: " + boletimTO.getCdFrenteBoleto() + " \n";
                retorno = retorno + "Peso Liq:  "  + boletimTO.getPesoLiquidoBoleto() + " \n";
                retorno = retorno + "" + boletimTO.getDthrEntradaBoleto() + " \n";

            }
            else if((boletimTO.getUnidadeSorteada1Boleto() != 0) &&
                    (boletimTO.getUnidadeSorteada2Boleto() != 0) &&
                    (boletimTO.getUnidadeSorteada3Boleto() == 0)){

                retorno = retorno + "Cargas Sorteadas \n";
                retorno = retorno + "" + boletimTO.getUnidadeSorteada1Boleto() + "       " + boletimTO.getUnidadeSorteada2Boleto() + " \n";
                retorno = retorno + "CEC: " + boletimTO.getCecSorteado1Boleto() + "  " + boletimTO.getCecSorteado2Boleto() + " \n";
                retorno = retorno + "Frente: " + boletimTO.getCdFrenteBoleto() + " \n";
                retorno = retorno + "Peso Liq:  "  + boletimTO.getPesoLiquidoBoleto() + " \n";
                retorno = retorno + "" + boletimTO.getDthrEntradaBoleto() + " \n";

            }
            else if((boletimTO.getUnidadeSorteada1Boleto() != 0) &&
                    (boletimTO.getUnidadeSorteada2Boleto() == 0) &&
                    (boletimTO.getUnidadeSorteada3Boleto() == 0)){

                retorno = retorno + "Cargas Sorteadas \n";
                retorno = retorno + ""+ boletimTO.getUnidadeSorteada1Boleto() + " \n";
                retorno = retorno + "CEC: " + boletimTO.getCecSorteado1Boleto() + " \n";
                retorno = retorno + "Frente: " + boletimTO.getCdFrenteBoleto() + " \n";
                retorno = retorno + "Peso Liq:  "  + boletimTO.getPesoLiquidoBoleto() + " \n";
                retorno = retorno + "" + boletimTO.getDthrEntradaBoleto() + " \n";

            }
            else if((boletimTO.getUnidadeSorteada1Boleto() == 0) &&
                    (boletimTO.getUnidadeSorteada2Boleto() != 0) &&
                    (boletimTO.getUnidadeSorteada3Boleto() == 0)){

                retorno = retorno + "Cargas Sorteadas \n";
                retorno = retorno + ""+ boletimTO.getUnidadeSorteada2Boleto() + " \n";
                retorno = retorno + "CEC: " + boletimTO.getCecSorteado2Boleto() + " \n";
                retorno = retorno + "Frente: " + boletimTO.getCdFrenteBoleto() + " \n";
                retorno = retorno + "Peso Liq:  "  + boletimTO.getPesoLiquidoBoleto() + " \n";
                retorno = retorno + "" + boletimTO.getDthrEntradaBoleto() + " \n";

            }
            else if((boletimTO.getUnidadeSorteada1Boleto() == 0) &&
                    (boletimTO.getUnidadeSorteada2Boleto() == 0) &&
                    (boletimTO.getUnidadeSorteada3Boleto() != 0)){

                retorno = retorno + "Cargas Sorteadas \n";
                retorno = retorno + ""+ boletimTO.getUnidadeSorteada3Boleto() + " \n";
                retorno = retorno + "CEC: " + boletimTO.getCecSorteado3Boleto() + " \n";
                retorno = retorno + "Frente: " + boletimTO.getCdFrenteBoleto() + " \n";
                retorno = retorno + "Peso Liq:  "  + boletimTO.getPesoLiquidoBoleto() + " \n";
                retorno = retorno + "" + boletimTO.getDthrEntradaBoleto() + " \n";

            }

        }

        salvarBkp(boletimTO);
        boletimTO.deleteAll();
        return retorno;

    }

    public void onBackPressed()  {
    }

    public void salvarBkp(BoletimTO boletimTO){

        BoletimBkpTO boletimBkpTO = new BoletimBkpTO();

        boletimBkpTO.setCaminhaoBoleto(boletimTO.getCaminhaoBoleto());
        boletimBkpTO.setPossuiSorteioBoleto(boletimTO.getPossuiSorteioBoleto());
        boletimBkpTO.setCecPaiBoleto(boletimTO.getCecPaiBoleto());
        boletimBkpTO.setCdFrenteBoleto(boletimTO.getCdFrenteBoleto());
        boletimBkpTO.setDthrEntradaBoleto(boletimTO.getDthrEntradaBoleto());
        boletimBkpTO.setCecSorteado1Boleto(boletimTO.getCecSorteado1Boleto());
        boletimBkpTO.setUnidadeSorteada1Boleto(boletimTO.getUnidadeSorteada1Boleto());
        boletimBkpTO.setCecSorteado2Boleto(boletimTO.getCecSorteado2Boleto());
        boletimBkpTO.setUnidadeSorteada2Boleto(boletimTO.getUnidadeSorteada2Boleto());
        boletimBkpTO.setCecSorteado3Boleto(boletimTO.getCecSorteado3Boleto());
        boletimBkpTO.setUnidadeSorteada3Boleto(boletimTO.getUnidadeSorteada3Boleto());
        boletimBkpTO.setPesoLiquidoBoleto(boletimTO.getPesoLiquidoBoleto());

        List listaBoleto =  boletimBkpTO.all();
        int qtdeBoleto = listaBoleto.size();
        if (qtdeBoleto >= 10) {
            BoletimBkpTO boletimBkpTODel = (BoletimBkpTO) listaBoleto.get(0);
            boletimBkpTODel.delete();
        }

        boletimBkpTO.insert();

    }

}
