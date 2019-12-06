package br.com.usinasantafe.ecm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.ecm.model.bean.estaticas.TurnoBean;

public class ListaTurnoActivity extends ActivityGeneric {

    private ListView turnoListView;
    private ECMContext ecmContext;
    private List turnoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_turno);

        ecmContext = (ECMContext) getApplication();
        Button buttonRetListaTurno = (Button) findViewById(R.id.buttonRetListaTurno);

        listarMenu();

        buttonRetListaTurno.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent it = new Intent(ListaTurnoActivity.this, CaminhaoActivity.class);
                startActivity(it);
                finish();
            }

        });

    }

    public void listarMenu() {

        TurnoBean turnoBean = new TurnoBean();
        turnoList = turnoBean.get("codTurno", ecmContext.getConfigCTR().getEquip().getIdTurnoEquip());

        ArrayList<String> itens = new ArrayList<String>();

        for(int i = 0; i < turnoList.size(); i++){
            turnoBean = (TurnoBean) turnoList.get(i);
            itens.add(turnoBean.getDescTurno());
        }

        AdapterList adapterList = new AdapterList(this, itens);
        turnoListView = (ListView) findViewById(R.id.turnoListView);
        turnoListView.setAdapter(adapterList);

        turnoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                TurnoBean turnoBean = (TurnoBean) turnoList.get(position);

                ecmContext.getConfigCTR().setIdTurnoConfig(turnoBean.getIdTurno());

                if ((ecmContext.getConfigCTR().getEquip().getIdCheckListEquip() > 0)
                        && (ecmContext.getConfigCTR().getConfig().getUltTurnoCLConfig() != turnoBean.getIdTurno())) {

                    ecmContext.setPosChecklist(1);
                    ecmContext.getMotoMecCTR().salvaMotoMec(ecmContext.getMotoMecCTR().getCheckList());

                    if (ecmContext.getVerAtualCL().equals("N_AC")) {

                        Intent it = new Intent(ListaTurnoActivity.this, PergAtualCheckListActivity.class);
                        startActivity(it);
                        finish();

                    } else {

                        ecmContext.getCheckListCTR().insCabec();
                        ecmContext.getConfigCTR().setUltTurnoCLConfig();

                        Intent it = new Intent(ListaTurnoActivity.this, ItemCheckListActivity.class);
                        startActivity(it);
                        finish();

                    }

                } else {

                    Intent it = new Intent(ListaTurnoActivity.this, ListaMotoMecActivity.class);
                    startActivity(it);
                    finish();

                }


            }

        });


    }

    public void onBackPressed() {
    }

}
