package br.com.usinasantafe.ecm;

//import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import br.com.usinasantafe.ecm.util.AtualDadosServ;
import br.com.usinasantafe.ecm.util.Tempo;
import br.com.usinasantafe.ecm.model.bean.variaveis.BoletimBkpBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.BoletimBean;

public class BoletimActivity extends ActivityGeneric {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boletim);

        TextView textViewBoletim = (TextView) findViewById(R.id.textViewBoletim);
        Button buttonOkBoletim = (Button) findViewById(R.id.buttonOkBoletim);

        BoletimBean boletimBean = new BoletimBean();
        List listBoletim = boletimBean.all();
        boletimBean = (BoletimBean) listBoletim.get(0);

        String boletim = visBoletim(boletimBean);

        textViewBoletim.setText(boletim);

        buttonOkBoletim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent(BoletimActivity.this, MenuMotoMecActivity.class);
                startActivity(it);

                AtualDadosServ.getInstance().atualTodasTabBD();
                Tempo.getInstance().setEnvioDado(true);

            }
        });


    }

    public String visBoletim(BoletimBean boletimBean){

        String retorno = "";

        int analisar = (int) boletimBean.getPossuiSorteioBoleto().longValue();

        if(analisar == 0){

            retorno = retorno + "NÃO FOI SORTEADO \n";
            retorno = retorno + "PARA ANALISE! \n";
            retorno = retorno + "PESO LIQ:  "  + boletimBean.getPesoLiquidoBoleto() + "\n";
            retorno = retorno + "---------------- \n";
            retorno = retorno + "" + boletimBean.getDthrEntradaBoleto() + " \n";

        }
        else if(analisar == 1){

            if((boletimBean.getUnidadeSorteada1Boleto() != 0) &&
                    (boletimBean.getUnidadeSorteada2Boleto() != 0) &&
                    (boletimBean.getUnidadeSorteada3Boleto() != 0)){

                retorno = retorno + "Cargas Sorteadas \n";
                retorno = retorno + "" + boletimBean.getUnidadeSorteada1Boleto() + "       " + boletimBean.getUnidadeSorteada2Boleto() + " \n";
                retorno = retorno + "CEC: " + boletimBean.getCecSorteado1Boleto() + "  " + boletimBean.getCecSorteado2Boleto() + " \n";
                retorno = retorno + "Frente: " + boletimBean.getCdFrenteBoleto() + " \n";
                retorno = retorno + "Peso Liq:  "  + boletimBean.getPesoLiquidoBoleto() + " \n";
                retorno = retorno + "" + boletimBean.getDthrEntradaBoleto() + " \n";

            }
            else if((boletimBean.getUnidadeSorteada1Boleto() == 0) &&
                    (boletimBean.getUnidadeSorteada2Boleto() != 0) &&
                    (boletimBean.getUnidadeSorteada3Boleto() != 0)){

                retorno = retorno + "Cargas Sorteadas \n";
                retorno = retorno + "" + boletimBean.getUnidadeSorteada2Boleto() + "       " + boletimBean.getUnidadeSorteada3Boleto() + " \n";
                retorno = retorno + "CEC: " + boletimBean.getCecSorteado2Boleto() + "  " + boletimBean.getCecSorteado3Boleto() + " \n";
                retorno = retorno + "Frente: " + boletimBean.getCdFrenteBoleto() + " \n";
                retorno = retorno + "Peso Liq:  "  + boletimBean.getPesoLiquidoBoleto() + " \n";
                retorno = retorno + "" + boletimBean.getDthrEntradaBoleto() + " \n";

            }
            else if((boletimBean.getUnidadeSorteada1Boleto() != 0) &&
                    (boletimBean.getUnidadeSorteada2Boleto() == 0) &&
                    (boletimBean.getUnidadeSorteada3Boleto() != 0)){

                retorno = retorno + "Cargas Sorteadas \n";
                retorno = retorno + "" + boletimBean.getUnidadeSorteada1Boleto() + "       " + boletimBean.getUnidadeSorteada3Boleto() + " \n";
                retorno = retorno + "CEC: " + boletimBean.getCecSorteado1Boleto() + "  " + boletimBean.getCecSorteado3Boleto() + " \n";
                retorno = retorno + "Frente: " + boletimBean.getCdFrenteBoleto() + " \n";
                retorno = retorno + "Peso Liq:  "  + boletimBean.getPesoLiquidoBoleto() + " \n";
                retorno = retorno + "" + boletimBean.getDthrEntradaBoleto() + " \n";

            }
            else if((boletimBean.getUnidadeSorteada1Boleto() != 0) &&
                    (boletimBean.getUnidadeSorteada2Boleto() != 0) &&
                    (boletimBean.getUnidadeSorteada3Boleto() == 0)){

                retorno = retorno + "Cargas Sorteadas \n";
                retorno = retorno + "" + boletimBean.getUnidadeSorteada1Boleto() + "       " + boletimBean.getUnidadeSorteada2Boleto() + " \n";
                retorno = retorno + "CEC: " + boletimBean.getCecSorteado1Boleto() + "  " + boletimBean.getCecSorteado2Boleto() + " \n";
                retorno = retorno + "Frente: " + boletimBean.getCdFrenteBoleto() + " \n";
                retorno = retorno + "Peso Liq:  "  + boletimBean.getPesoLiquidoBoleto() + " \n";
                retorno = retorno + "" + boletimBean.getDthrEntradaBoleto() + " \n";

            }
            else if((boletimBean.getUnidadeSorteada1Boleto() != 0) &&
                    (boletimBean.getUnidadeSorteada2Boleto() == 0) &&
                    (boletimBean.getUnidadeSorteada3Boleto() == 0)){

                retorno = retorno + "Cargas Sorteadas \n";
                retorno = retorno + ""+ boletimBean.getUnidadeSorteada1Boleto() + " \n";
                retorno = retorno + "CEC: " + boletimBean.getCecSorteado1Boleto() + " \n";
                retorno = retorno + "Frente: " + boletimBean.getCdFrenteBoleto() + " \n";
                retorno = retorno + "Peso Liq:  "  + boletimBean.getPesoLiquidoBoleto() + " \n";
                retorno = retorno + "" + boletimBean.getDthrEntradaBoleto() + " \n";

            }
            else if((boletimBean.getUnidadeSorteada1Boleto() == 0) &&
                    (boletimBean.getUnidadeSorteada2Boleto() != 0) &&
                    (boletimBean.getUnidadeSorteada3Boleto() == 0)){

                retorno = retorno + "Cargas Sorteadas \n";
                retorno = retorno + ""+ boletimBean.getUnidadeSorteada2Boleto() + " \n";
                retorno = retorno + "CEC: " + boletimBean.getCecSorteado2Boleto() + " \n";
                retorno = retorno + "Frente: " + boletimBean.getCdFrenteBoleto() + " \n";
                retorno = retorno + "Peso Liq:  "  + boletimBean.getPesoLiquidoBoleto() + " \n";
                retorno = retorno + "" + boletimBean.getDthrEntradaBoleto() + " \n";

            }
            else if((boletimBean.getUnidadeSorteada1Boleto() == 0) &&
                    (boletimBean.getUnidadeSorteada2Boleto() == 0) &&
                    (boletimBean.getUnidadeSorteada3Boleto() != 0)){

                retorno = retorno + "Cargas Sorteadas \n";
                retorno = retorno + ""+ boletimBean.getUnidadeSorteada3Boleto() + " \n";
                retorno = retorno + "CEC: " + boletimBean.getCecSorteado3Boleto() + " \n";
                retorno = retorno + "Frente: " + boletimBean.getCdFrenteBoleto() + " \n";
                retorno = retorno + "Peso Liq:  "  + boletimBean.getPesoLiquidoBoleto() + " \n";
                retorno = retorno + "" + boletimBean.getDthrEntradaBoleto() + " \n";

            }

        }

        salvarBkp(boletimBean);
        boletimBean.deleteAll();
        return retorno;

    }

    public void onBackPressed()  {
    }

    public void salvarBkp(BoletimBean boletimBean){

        BoletimBkpBean boletimBkpBean = new BoletimBkpBean();

        boletimBkpBean.setCaminhaoBoleto(boletimBean.getCaminhaoBoleto());
        boletimBkpBean.setPossuiSorteioBoleto(boletimBean.getPossuiSorteioBoleto());
        boletimBkpBean.setCecPaiBoleto(boletimBean.getCecPaiBoleto());
        boletimBkpBean.setCdFrenteBoleto(boletimBean.getCdFrenteBoleto());
        boletimBkpBean.setDthrEntradaBoleto(boletimBean.getDthrEntradaBoleto());
        boletimBkpBean.setCecSorteado1Boleto(boletimBean.getCecSorteado1Boleto());
        boletimBkpBean.setUnidadeSorteada1Boleto(boletimBean.getUnidadeSorteada1Boleto());
        boletimBkpBean.setCecSorteado2Boleto(boletimBean.getCecSorteado2Boleto());
        boletimBkpBean.setUnidadeSorteada2Boleto(boletimBean.getUnidadeSorteada2Boleto());
        boletimBkpBean.setCecSorteado3Boleto(boletimBean.getCecSorteado3Boleto());
        boletimBkpBean.setUnidadeSorteada3Boleto(boletimBean.getUnidadeSorteada3Boleto());
        boletimBkpBean.setPesoLiquidoBoleto(boletimBean.getPesoLiquidoBoleto());

        List listaBoleto =  boletimBkpBean.all();
        int qtdeBoleto = listaBoleto.size();
        if (qtdeBoleto >= 10) {
            BoletimBkpBean boletimBkpBeanDel = (BoletimBkpBean) listaBoleto.get(0);
            boletimBkpBeanDel.delete();
        }

        boletimBkpBean.insert();

    }

}
