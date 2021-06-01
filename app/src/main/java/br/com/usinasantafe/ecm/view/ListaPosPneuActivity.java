package br.com.usinasantafe.ecm.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.ecm.ECMContext;
import br.com.usinasantafe.ecm.R;
import br.com.usinasantafe.ecm.model.bean.estaticas.REquipPneuBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.ItemPneuBean;

public class ListaPosPneuActivity extends ActivityGeneric {

    private ECMContext ecmContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pos_pneu);

        ecmContext = (ECMContext) getApplication();

        Button buttonAtualPosPneu = (Button) findViewById(R.id.buttonAtualPosPneu);

        buttonAtualPosPneu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        ArrayList<String> itens = new ArrayList<String>();

        List<REquipPneuBean> rEquipPneuList = ecmContext.getPneuCTR().rEquipPneuList();
        List<ItemPneuBean> itemMedPneuList = ecmContext.getPneuCTR().itemCalibPneuList();
        boolean verCad;
        for(REquipPneuBean rEquipPneuBean : rEquipPneuList){
            verCad = true;
            for(ItemPneuBean itemPneuBean : itemMedPneuList) {
                if(rEquipPneuBean.getIdPosConfPneu() == itemPneuBean.getPosItemPneu()){
                    verCad = false;
                }
            }
            if(verCad) {
                itens.add(rEquipPneuBean.getPosPneu());
            }
        }
        itemMedPneuList.clear();
        rEquipPneuList.clear();

        ArrayAdapter<String> adapterList = new ArrayAdapter<String>(this, R.layout.activity_item_lista, R.id.textViewItemList, itens);
        ListView listaPosPneu = (ListView) findViewById(R.id.listaPosPneu);
        listaPosPneu.setAdapter(adapterList);

        listaPosPneu.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                TextView textView = (TextView) v.findViewById(R.id.textViewItemList);
                String posPneu = textView.getText().toString();

                ecmContext.getPneuCTR().setItemPneuBean(ecmContext.getPneuCTR().getEquipPneu(posPneu).getIdPosConfPneu());

                Intent it = new Intent(ListaPosPneuActivity.this, PneuActivity.class);
                startActivity(it);
                finish();

            }

        });

    }

    public void onBackPressed() {
    }

}
