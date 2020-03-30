package br.com.usinasantafe.ecm;

//import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import br.com.usinasantafe.ecm.model.bean.variaveis.CECBean;
import br.com.usinasantafe.ecm.util.AtualDadosServ;
import br.com.usinasantafe.ecm.util.Tempo;

public class CECActivity extends ActivityGeneric {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cec);

        TextView textViewBoletim = (TextView) findViewById(R.id.textViewBoletim);
        Button buttonOkBoletim = (Button) findViewById(R.id.buttonOkBoletim);

        CECBean cecBean = new CECBean();
        List listCEC = cecBean.orderBy("idCEC", false);
        cecBean = (CECBean) listCEC.get(0);

        delCEC();

        String boletim = visCEC(cecBean);
        textViewBoletim.setText(boletim);

        buttonOkBoletim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent(CECActivity.this, MenuMotoMecActivity.class);
                startActivity(it);

                AtualDadosServ.getInstance().atualTodasTabBD();
                Tempo.getInstance().setEnvioDado(true);

            }
        });


    }

    public String visCEC(CECBean cecBean){

        String retorno = "";

        int analisar = (int) cecBean.getPossuiSorteioCEC().longValue();

        if(analisar == 0){

            retorno = retorno + "NÃO FOI SORTEADO \n";
            retorno = retorno + "PARA ANALISE! \n";
            retorno = retorno + "PESO LIQ:  "  + cecBean.getPesoLiquidoCEC() + "\n";
            retorno = retorno + "---------------- \n";
            retorno = retorno + "" + cecBean.getDthrEntradaCEC() + " \n";

        }
        else if(analisar == 1){

            if((cecBean.getUnidadeSorteada1CEC() != 0) &&
                    (cecBean.getUnidadeSorteada2CEC() != 0) &&
                    (cecBean.getUnidadeSorteada3CEC() != 0)){

                retorno = retorno + "CARGAS SORTEADAS \n";
                retorno = retorno + "" + cecBean.getUnidadeSorteada1CEC() + "       " + cecBean.getUnidadeSorteada2CEC() + " \n";
                retorno = retorno + "CEC: " + cecBean.getCecSorteado1CEC() + "  " + cecBean.getCecSorteado2CEC() + " \n";
                retorno = retorno + "FRENTE: " + cecBean.getCodFrenteCEC() + " \n";
                retorno = retorno + "PESO LIQ:  "  + cecBean.getPesoLiquidoCEC() + " \n";
                retorno = retorno + "" + cecBean.getDthrEntradaCEC() + " \n";

            }
            else if((cecBean.getUnidadeSorteada1CEC() == 0) &&
                    (cecBean.getUnidadeSorteada2CEC() != 0) &&
                    (cecBean.getUnidadeSorteada3CEC() != 0)){

                retorno = retorno + "CARGAS SORTEADAS \n";
                retorno = retorno + "" + cecBean.getUnidadeSorteada2CEC() + "       " + cecBean.getUnidadeSorteada3CEC() + " \n";
                retorno = retorno + "CEC: " + cecBean.getCecSorteado2CEC() + "  " + cecBean.getCecSorteado3CEC() + " \n";
                retorno = retorno + "FRENTE: " + cecBean.getCodFrenteCEC() + " \n";
                retorno = retorno + "PESO LIQ:  "  + cecBean.getPesoLiquidoCEC() + " \n";
                retorno = retorno + "" + cecBean.getDthrEntradaCEC() + " \n";

            }
            else if((cecBean.getUnidadeSorteada1CEC() != 0) &&
                    (cecBean.getUnidadeSorteada2CEC() == 0) &&
                    (cecBean.getUnidadeSorteada3CEC() != 0)){

                retorno = retorno + "CARGAS SORTEADAS \n";
                retorno = retorno + "" + cecBean.getUnidadeSorteada1CEC() + "       " + cecBean.getUnidadeSorteada3CEC() + " \n";
                retorno = retorno + "CEC: " + cecBean.getCecSorteado1CEC() + "  " + cecBean.getCecSorteado3CEC() + " \n";
                retorno = retorno + "FRENTE: " + cecBean.getCodFrenteCEC() + " \n";
                retorno = retorno + "PESO LIQ:  "  + cecBean.getPesoLiquidoCEC() + " \n";
                retorno = retorno + "" + cecBean.getDthrEntradaCEC() + " \n";

            }
            else if((cecBean.getUnidadeSorteada1CEC() != 0) &&
                    (cecBean.getUnidadeSorteada2CEC() != 0) &&
                    (cecBean.getUnidadeSorteada3CEC() == 0)){

                retorno = retorno + "CARGAS SORTEADAS \n";
                retorno = retorno + "" + cecBean.getUnidadeSorteada1CEC() + "       " + cecBean.getUnidadeSorteada2CEC() + " \n";
                retorno = retorno + "CEC: " + cecBean.getCecSorteado1CEC() + "  " + cecBean.getCecSorteado2CEC() + " \n";
                retorno = retorno + "FRENTE: " + cecBean.getCodFrenteCEC() + " \n";
                retorno = retorno + "PESO LIQ:  "  + cecBean.getPesoLiquidoCEC() + " \n";
                retorno = retorno + "" + cecBean.getDthrEntradaCEC() + " \n";

            }
            else if((cecBean.getUnidadeSorteada1CEC() != 0) &&
                    (cecBean.getUnidadeSorteada2CEC() == 0) &&
                    (cecBean.getUnidadeSorteada3CEC() == 0)){

                retorno = retorno + "CARGAS SORTEADAS \n";
                retorno = retorno + ""+ cecBean.getUnidadeSorteada1CEC() + " \n";
                retorno = retorno + "CEC: " + cecBean.getCecSorteado1CEC() + " \n";
                retorno = retorno + "FRENTE: " + cecBean.getCodFrenteCEC() + " \n";
                retorno = retorno + "PESO LIQ:  "  + cecBean.getPesoLiquidoCEC() + " \n";
                retorno = retorno + "" + cecBean.getDthrEntradaCEC() + " \n";

            }
            else if((cecBean.getUnidadeSorteada1CEC() == 0) &&
                    (cecBean.getUnidadeSorteada2CEC() != 0) &&
                    (cecBean.getUnidadeSorteada3CEC() == 0)){

                retorno = retorno + "CARGAS SORTEADAS \n";
                retorno = retorno + ""+ cecBean.getUnidadeSorteada2CEC() + " \n";
                retorno = retorno + "CEC: " + cecBean.getCecSorteado2CEC() + " \n";
                retorno = retorno + "FRENTE: " + cecBean.getCodFrenteCEC() + " \n";
                retorno = retorno + "PESO LIQ:  "  + cecBean.getPesoLiquidoCEC() + " \n";
                retorno = retorno + "" + cecBean.getDthrEntradaCEC() + " \n";

            }
            else if((cecBean.getUnidadeSorteada1CEC() == 0) &&
                    (cecBean.getUnidadeSorteada2CEC() == 0) &&
                    (cecBean.getUnidadeSorteada3CEC() != 0)){

                retorno = retorno + "CARGAS SORTEADAS \n";
                retorno = retorno + ""+ cecBean.getUnidadeSorteada3CEC() + " \n";
                retorno = retorno + "CEC: " + cecBean.getCecSorteado3CEC() + " \n";
                retorno = retorno + "FRENTE: " + cecBean.getCodFrenteCEC() + " \n";
                retorno = retorno + "PESO LIQ:  "  + cecBean.getPesoLiquidoCEC() + " \n";
                retorno = retorno + "" + cecBean.getDthrEntradaCEC() + " \n";

            }

        }
        return retorno;

    }

    public void onBackPressed()  {
    }

    public void delCEC(){

        CECBean cecBean = new CECBean();
        List cecList =  cecBean.orderBy("idCEC", true);
        int qtdeCEC = cecList.size();
        if (qtdeCEC > 10) {
            CECBean cecBeanDel = (CECBean) cecList.get(0);
            cecBeanDel.delete();
        }

    }

}
