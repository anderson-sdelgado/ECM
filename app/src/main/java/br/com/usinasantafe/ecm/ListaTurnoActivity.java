package br.com.usinasantafe.ecm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.ecm.bo.Tempo;
import br.com.usinasantafe.ecm.to.tb.estaticas.CaminhaoTO;
import br.com.usinasantafe.ecm.to.tb.estaticas.ItemCheckListTO;
import br.com.usinasantafe.ecm.to.tb.estaticas.TurnoTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.CabecCheckListTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.ConfiguracaoTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.InfBoletimTO;

public class ListaTurnoActivity extends ActivityGeneric {

    private ListView lista;
    private ECMContext ecmContext;

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

                Intent it = new Intent(ListaTurnoActivity.this, CaminhaoTurnoActivity.class);
                startActivity(it);
                finish();
            }

        });

    }

    public void listarMenu() {

        TurnoTO turnoBD = new TurnoTO();
        final List listaTurno = turnoBD.all();
        ArrayList<String> itens = new ArrayList<String>();

        for (int i = 0; i < listaTurno.size(); i++) {
            turnoBD = (TurnoTO) listaTurno.get(i);
            itens.add(turnoBD.getDescTurno());
        }

        AdapterList adapterList = new AdapterList(this, itens);
        lista = (ListView) findViewById(R.id.listaTurno);
        lista.setAdapter(adapterList);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                TurnoTO turnoTO = (TurnoTO) listaTurno.get(position);

                InfBoletimTO infBoletimTO = new InfBoletimTO();
                List lTurno = infBoletimTO.all();
                infBoletimTO = (InfBoletimTO) lTurno.get(0);
                infBoletimTO.setTurno(turnoTO.getIdTurno());
                infBoletimTO.update();

                CaminhaoTO caminhaoTO = new CaminhaoTO();
                List caminhaoList = caminhaoTO.get("codCaminhao", infBoletimTO.getCam());
                caminhaoTO = (CaminhaoTO) caminhaoList.get(0);
                caminhaoList.clear();

                ConfiguracaoTO configTO = new ConfiguracaoTO();
                List configList = configTO.all();
                configTO = (ConfiguracaoTO) configList.get(0);

                if ((caminhaoTO.getIdChecklist() > 0) && (configTO.getUltTurnoCLConfig() != turnoTO.getIdTurno())) {

                    ecmContext.setPosChecklist(1L);

                    if (ecmContext.getVerAtualCL().equals("N_AC")) {

                        Intent it = new Intent(ListaTurnoActivity.this, PergAtualCheckListActivity.class);
                        startActivity(it);
                        finish();

                    } else {

                        ItemCheckListTO itemCheckListTO = new ItemCheckListTO();
                        List itemCheckList = itemCheckListTO.get("idChecklist", caminhaoTO.getIdChecklist());
                        Long qtde = (long) itemCheckList.size();
                        itemCheckList.clear();

                        CabecCheckListTO cabecCheckListTO = new CabecCheckListTO();
                        cabecCheckListTO.setDtCabecCheckList(Tempo.getInstance().datahora());
                        cabecCheckListTO.setEquipCabecCheckList(configTO.getCodCamConfig());
                        cabecCheckListTO.setFuncCabecCheckList(infBoletimTO.getCodigoMoto());
                        cabecCheckListTO.setTurnoCabecCheckList(infBoletimTO.getTurno());
                        cabecCheckListTO.setQtdeItemCabecCheckList(qtde);
                        cabecCheckListTO.setStatusCabecCheckList(1L);
                        cabecCheckListTO.setDtAtualCheckList("0");
                        cabecCheckListTO.insert();

                        configTO.setUltTurnoCLConfig(turnoTO.getIdTurno());
                        configTO.update();

                        Intent it = new Intent(ListaTurnoActivity.this, ItemChecklistActivity.class);
                        startActivity(it);
                        finish();

                    }

                } else {

                    Intent it = new Intent(ListaTurnoActivity.this, TipoFuncoesActivity.class);
                    startActivity(it);
                    finish();

                }


            }

        });


    }

    public void onBackPressed() {
    }

}
