package br.com.usinasantafe.ecm.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.usinasantafe.ecm.ECMContext;
import br.com.usinasantafe.ecm.R;
import br.com.usinasantafe.ecm.model.bean.variaveis.CECBean;
import br.com.usinasantafe.ecm.util.AtualDadosServ;
import br.com.usinasantafe.ecm.util.Tempo;

public class CECActivity extends ActivityGeneric {

    private ECMContext ecmContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cec);

        TextView textViewBoletim = (TextView) findViewById(R.id.textViewBoletim);
        Button buttonOkBoletim = (Button) findViewById(R.id.buttonOkBoletim);

        ecmContext = (ECMContext) getApplication();

        ecmContext.getCECCTR().delCEC();

        String boletim = visCEC(ecmContext.getCECCTR().getCEC());
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

            String CEC1 = "";
            String CEC2 = "";
            String CEC3 = "";

            retorno = retorno + "CARGAS SORTEADAS \n";

            if(cecBean.getUnidadeSorteada1CEC() != 0){
                CEC1 = "CARRETA " + cecBean.getUnidadeSorteada1CEC() + "-> N. CEC = " + cecBean.getCecSorteado1CEC() + "\n";
            }

            if(cecBean.getUnidadeSorteada2CEC() != 0){
                CEC2 = "CARRETA " + cecBean.getUnidadeSorteada2CEC() + "-> N. CEC = " + cecBean.getCecSorteado2CEC() + "\n";
            }

            if(cecBean.getUnidadeSorteada3CEC() != 0){
                CEC3 = "CARRETA " + cecBean.getUnidadeSorteada3CEC() + "-> N. CEC = " + cecBean.getCecSorteado3CEC() + "\n";
            }

            retorno = retorno + "CARGAS SORTEADAS \n";
            retorno = retorno + "" + CEC1 + CEC2 + CEC3;
            retorno = retorno + "FRENTE: " + cecBean.getCodFrenteCEC() + " \n";
            retorno = retorno + "PESO LIQ:  "  + cecBean.getPesoLiquidoCEC() + " \n";
            retorno = retorno + "" + cecBean.getDthrEntradaCEC() + " \n";

        }

        return retorno;

    }

    public void onBackPressed()  {
    }

}
