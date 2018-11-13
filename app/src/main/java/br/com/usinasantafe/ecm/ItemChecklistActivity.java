package br.com.usinasantafe.ecm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import br.com.usinasantafe.ecm.bo.ManipDadosEnvio;
import br.com.usinasantafe.ecm.to.tb.estaticas.ItemChecklistTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.RespItemCheckListTO;

public class ItemChecklistActivity extends ActivityGeneric {

    private ECMContext ecmContext;
    private RespItemCheckListTO respItemCheckListTO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_checklist);

        ecmContext = (ECMContext) getApplication();

        TextView textViewItemChecklist = (TextView) findViewById(R.id.textViewItemChecklist);
        Button buttonConforme = (Button) findViewById(R.id.buttonConforme);
        Button buttonNaoConforme = (Button) findViewById(R.id.buttonNaoConforme);
        Button buttonReparo = (Button) findViewById(R.id.buttonReparo);
        Button buttonCancChecklist = (Button) findViewById(R.id.buttonCancChecklist);

        respItemCheckListTO = ecmContext.getListRespChecklist().get(ecmContext.getPosChecklist());

        ItemChecklistTO itemChecklistTO = new ItemChecklistTO();
        List list = itemChecklistTO.get("idItemChecklist", respItemCheckListTO.getIdItItemCheckList());
        itemChecklistTO = (ItemChecklistTO) list.get(0);

        textViewItemChecklist.setText(itemChecklistTO.getSeqItemChecklist() + " - " + itemChecklistTO.getDescrItemChecklist());

        buttonConforme.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                respItemCheckListTO.setOpcaoItemCheckList(1L);
                proximaTela();

            }

        });

        buttonNaoConforme.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                respItemCheckListTO.setOpcaoItemCheckList(2L);
                proximaTela();
            }

        });

        buttonReparo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                respItemCheckListTO.setOpcaoItemCheckList(3L);
                proximaTela();
            }

        });

        buttonCancChecklist.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                retornoTela();
            }

        });


    }

    public void proximaTela(){

        ecmContext.setPosChecklist(ecmContext.getPosChecklist() + 1);

        if(ecmContext.getListRespChecklist().size() == ecmContext.getPosChecklist()){

            ManipDadosEnvio.getInstance().salvaCheckList(ecmContext.getListRespChecklist());

            Intent it = new Intent(ItemChecklistActivity.this, TipoFuncoesActivity.class);
            startActivity(it);
            finish();

        }
        else{
            Intent it = new Intent(ItemChecklistActivity.this, ItemChecklistActivity.class);
            startActivity(it);
            finish();
        }

    }

    public void retornoTela(){

        if(ecmContext.getPosChecklist() == 0){
            Intent it = new Intent(ItemChecklistActivity.this, ListaTurnoActivity.class);
            startActivity(it);
            finish();
        }
        else{
            ecmContext.setPosChecklist(ecmContext.getPosChecklist() - 1);
            Intent it = new Intent(ItemChecklistActivity.this, ItemChecklistActivity.class);
            startActivity(it);
            finish();
        }

    }

    public void onBackPressed()  {
    }

}
