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

    private ECMContext pmmContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pos_pneu);

        pmmContext = (ECMContext) getApplication();

        Button buttonAtualPosPneu = (Button) findViewById(R.id.buttonAtualPosPneu);

        buttonAtualPosPneu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        ArrayList<String> itens = new ArrayList<String>();

        REquipPneuBean rEquipPneuBean = new REquipPneuBean();
        List rEquipPneuList = rEquipPneuBean.all();

        List itemMedPneuList = pmmContext.getPneuCTR().getListItemCalibPneu();
        boolean verCad;
        for(int i = 0; i < rEquipPneuList.size(); i++){
            rEquipPneuBean = (REquipPneuBean) rEquipPneuList.get(i);
            verCad = true;
            for(int j = 0; j < itemMedPneuList.size(); j++) {
                ItemPneuBean itemPneuBean = (ItemPneuBean) itemMedPneuList.get(j);
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
                // TODO Auto-generated method stub

                TextView textView = (TextView) v.findViewById(R.id.textViewItemList);
                String posPneu = textView.getText().toString();

                REquipPneuBean rEquipPneuBean = new REquipPneuBean();
                List rEquipPneuList = rEquipPneuBean.get("posPneu", posPneu);
                rEquipPneuBean = (REquipPneuBean) rEquipPneuList.get(0);
                rEquipPneuList.clear();

                pmmContext.getPneuCTR().setItemPneuBean(rEquipPneuBean.getIdPosConfPneu());

                Intent it = new Intent(ListaPosPneuActivity.this, PneuActivity.class);
                startActivity(it);
                finish();

            }

        });

    }

    public void onBackPressed() {
    }

}
