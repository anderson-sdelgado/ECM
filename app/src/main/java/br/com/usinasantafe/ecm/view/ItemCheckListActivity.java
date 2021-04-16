package br.com.usinasantafe.ecm.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import br.com.usinasantafe.ecm.ECMContext;
import br.com.usinasantafe.ecm.R;
import br.com.usinasantafe.ecm.model.bean.estaticas.ItemCheckListBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.RespItemCLBean;

public class ItemCheckListActivity extends ActivityGeneric {

    private ECMContext ecmContext;
    private TextView textViewItemChecklist;
    private List itemCheckListList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_checklist);

        ecmContext = (ECMContext) getApplication();

        textViewItemChecklist = (TextView) findViewById(R.id.textViewItemChecklist);
        Button buttonConforme = (Button) findViewById(R.id.buttonConforme);
        Button buttonNaoConforme = (Button) findViewById(R.id.buttonNaoConforme);
        Button buttonReparo = (Button) findViewById(R.id.buttonReparo);
        Button buttonCancChecklist = (Button) findViewById(R.id.buttonCancChecklist);

        itemCheckListList = ecmContext.getCheckListCTR().getItemList();

        ItemCheckListBean itemCheckListBean = (ItemCheckListBean) itemCheckListList.get(ecmContext.getPosCheckList() - 1);
        textViewItemChecklist.setText(ecmContext.getPosCheckList() + " - " + itemCheckListBean.getDescrItemCheckList());

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

        ItemCheckListBean itemCheckListBean = (ItemCheckListBean) itemCheckListList.get(ecmContext.getPosCheckList() - 1);
        RespItemCLBean respItemCLBean = new RespItemCLBean();
        respItemCLBean.setIdItBDItCL(itemCheckListBean.getIdItemCheckList());
        respItemCLBean.setOpItCL(opcao);
        ecmContext.getCheckListCTR().insResp(respItemCLBean);

        if(itemCheckListList.size() == ecmContext.getPosCheckList()){
            ecmContext.getConfigCTR().setCheckListConfig(ecmContext.getMotoMecCTR().getTurno());
            ecmContext.getCheckListCTR().fechaCabec();
            itemCheckListList.clear();
            if (ecmContext.getVerPosTela() == 1) {
                Intent it = new Intent(ItemCheckListActivity.this, MenuMotoMecActivity.class);
                startActivity(it);
                finish();
            } else if (ecmContext.getVerPosTela() == 9) {
                Intent it = new Intent(ItemCheckListActivity.this, VerMotoristaActivity.class);
                startActivity(it);
                finish();
            }

        }
        else{
            ecmContext.setPosCheckList(ecmContext.getPosCheckList() + 1);
            itemCheckListBean = (ItemCheckListBean) itemCheckListList.get(ecmContext.getPosCheckList() - 1);
            textViewItemChecklist.setText(ecmContext.getPosCheckList() + " - " + itemCheckListBean.getDescrItemCheckList());
        }

    }

    public void retornoTela(){
        if(ecmContext.getPosCheckList() > 1){
            ecmContext.setPosCheckList(ecmContext.getPosCheckList() - 1);
            ItemCheckListBean itemCheckListBean = (ItemCheckListBean) itemCheckListList.get(ecmContext.getPosCheckList() - 1);
            textViewItemChecklist.setText(ecmContext.getPosCheckList() + " - " + itemCheckListBean.getDescrItemCheckList());
        }
    }

    public void onBackPressed()  {
    }

}
