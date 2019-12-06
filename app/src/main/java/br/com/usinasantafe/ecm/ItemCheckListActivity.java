package br.com.usinasantafe.ecm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.usinasantafe.ecm.model.bean.estaticas.ItemCLBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.RespCheckListBean;

public class ItemCheckListActivity extends ActivityGeneric {

    private ECMContext ecmContext;
    private ItemCLBean itemCLBean;

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

        ItemCLBean itemCLBean = new ItemCLBean();
        itemCLBean = ecmContext.getCheckListCTR().getItemCheckList(ecmContext.getPosChecklist());

        textViewItemChecklist.setText(itemCLBean.getSeqItemCheckList() + " - " + itemCLBean.getDescrItemCheckList());

        buttonConforme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                proximaTela(1L);
            }
        });

        buttonNaoConforme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                proximaTela(2L);
            }
        });

        buttonReparo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                proximaTela(3L);
            }
        });

        buttonCancChecklist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retornoTela();
            }
        });


    }

    public void proximaTela(Long opcao){

        RespCheckListBean respCheckListBean = new RespCheckListBean();
        respCheckListBean.setIdItItemCheckList(itemCLBean.getIdItemCheckList());
        respCheckListBean.setOpcaoItemCheckList(opcao);
        ecmContext.getCheckListCTR().insResp(respCheckListBean);

        if(ecmContext.getCheckListCTR().getQtdeItemCabecAberto() == ecmContext.getPosChecklist()){
            ecmContext.getCheckListCTR().fechaCabec();
            if((ecmContext.getVerPosTela() == 1) || (ecmContext.getVerPosTela() == 2)){
                Intent it = new Intent(ItemCheckListActivity.this, ListaMotoMecActivity.class);
                startActivity(it);
                finish();
            }
        }
        else{
            ecmContext.setPosChecklist(ecmContext.getPosChecklist() + 1);
            Intent it = new Intent(ItemCheckListActivity.this, ItemCheckListActivity.class);
            startActivity(it);
            finish();
        }

    }

    public void retornoTela(){
        if(ecmContext.getPosChecklist() > 1){
            ecmContext.setPosChecklist(ecmContext.getPosChecklist() - 1);
            Intent it = new Intent(ItemCheckListActivity.this, ItemCheckListActivity.class);
            startActivity(it);
            finish();
        }
    }

    public void onBackPressed()  {
    }

}
