package br.com.usinasantafe.ecm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.usinasantafe.ecm.model.bean.estaticas.ItemCheckListBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.RespItemCLBean;

public class ItemCheckListActivity extends ActivityGeneric {

    private ECMContext ecmContext;
    private ItemCheckListBean itemCheckListBean;

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

        ItemCheckListBean itemCheckListBean = new ItemCheckListBean();
        itemCheckListBean = ecmContext.getCheckListCTR().getItemCheckList(ecmContext.getPosCheckList());

        textViewItemChecklist.setText(itemCheckListBean.getSeqItemCheckList() + " - " + itemCheckListBean.getDescrItemCheckList());

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

        RespItemCLBean respItemCLBean = new RespItemCLBean();
        respItemCLBean.setIdItBDItCL(itemCheckListBean.getIdItemCheckList());
        respItemCLBean.setOpItCL(opcao);
        ecmContext.getCheckListCTR().insResp(respItemCLBean);

        if(ecmContext.getCheckListCTR().qtdeItemCheckList() == ecmContext.getPosCheckList()){
            ecmContext.getConfigCTR().setCheckListConfig(ecmContext.getMotoMecCTR().getTurno());
            ecmContext.getCheckListCTR().fechaCabec();
            Intent it = new Intent(ItemCheckListActivity.this, MenuMotoMecActivity.class);
            startActivity(it);
            finish();

        }
        else{
            ecmContext.setPosCheckList(ecmContext.getPosCheckList() + 1);
            Intent it = new Intent(ItemCheckListActivity.this, ItemCheckListActivity.class);
            startActivity(it);
            finish();
        }

    }

    public void retornoTela(){
        if(ecmContext.getPosCheckList() > 1){
            ecmContext.setPosCheckList(ecmContext.getPosCheckList() - 1);
            Intent it = new Intent(ItemCheckListActivity.this, ItemCheckListActivity.class);
            startActivity(it);
            finish();
        }
    }

    public void onBackPressed()  {
    }

}
