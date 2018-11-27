package br.com.usinasantafe.ecm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.ecm.bo.ManipDadosEnvio;
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
    private ConfiguracaoTO configTO;
    private List listTurno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_turno);

        ecmContext = (ECMContext) getApplication();
        Button buttonRetListaTurno = (Button) findViewById(R.id.buttonRetListaTurno);

        configTO = new ConfiguracaoTO();
        List configList = configTO.all();
        configTO = (ConfiguracaoTO) configList.get(0);

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

        CaminhaoTO caminhaoTO = new CaminhaoTO();
        List caminhaoList = caminhaoTO.get("idCaminhao", configTO.getIdCamConfig());
        caminhaoTO = (CaminhaoTO) caminhaoList.get(0);

        TurnoTO turnoTO = new TurnoTO();
        listTurno = turnoTO.get("codTurno", caminhaoTO.getCodTurno());

        ArrayList<String> itens = new ArrayList<String>();

        for(int i = 0; i < listTurno.size(); i++){
            turnoTO = (TurnoTO) listTurno.get(i);
            itens.add(turnoTO.getDescTurno());
        }

        AdapterList adapterList = new AdapterList(this, itens);
        lista = (ListView) findViewById(R.id.listaTurno);
        lista.setAdapter(adapterList);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                TurnoTO turnoTO = (TurnoTO) listTurno.get(position);

                InfBoletimTO infBoletimTO = new InfBoletimTO();
                List lTurno = infBoletimTO.all();
                infBoletimTO = (InfBoletimTO) lTurno.get(0);
                infBoletimTO.setTurno(turnoTO.getNroTurno());
                infBoletimTO.setTipoAtiv(1L);
                infBoletimTO.update();

                CaminhaoTO caminhaoTO = new CaminhaoTO();
                List caminhaoList = caminhaoTO.get("codCaminhao", infBoletimTO.getCam());
                caminhaoTO = (CaminhaoTO) caminhaoList.get(0);
                caminhaoList.clear();

                if ((caminhaoTO.getIdChecklist() > 0) && (configTO.getUltTurnoCLConfig() != turnoTO.getIdTurno())) {

                    ecmContext.setPosChecklist(1L);

                    if (ecmContext.getVerAtualCL().equals("N_AC")) {

                        ecmContext.getApontMotoMecTO().setOpcor(180L);
                        ManipDadosEnvio.getInstance().salvaMotoMec(ecmContext.getApontMotoMecTO());

                        Intent it = new Intent(ListaTurnoActivity.this, PergAtualCheckListActivity.class);
                        startActivity(it);
                        finish();

                    } else {

                        ItemCheckListTO itemCheckListTO = new ItemCheckListTO();
                        List itemCheckList = itemCheckListTO.get("idCheckList", caminhaoTO.getIdChecklist());
                        Long qtde = (long) itemCheckList.size();
                        itemCheckList.clear();

                        CabecCheckListTO cabecCheckListTO = new CabecCheckListTO();
                        cabecCheckListTO.setDtCabecCheckList(Tempo.getInstance().datahora());
                        cabecCheckListTO.setEquipCabecCheckList(configTO.getCodCamConfig());
                        cabecCheckListTO.setFuncCabecCheckList(infBoletimTO.getCodigoMoto());
                        cabecCheckListTO.setTurnoCabecCheckList(turnoTO.getIdTurno());
                        cabecCheckListTO.setQtdeItemCabecCheckList(qtde);
                        cabecCheckListTO.setStatusCabecCheckList(1L);
                        cabecCheckListTO.insert();

                        configTO.setUltTurnoCLConfig(turnoTO.getIdTurno());
                        configTO.update();

                        ecmContext.getApontMotoMecTO().setOpcor(180L);
                        ManipDadosEnvio.getInstance().salvaMotoMec(ecmContext.getApontMotoMecTO());

                        Intent it = new Intent(ListaTurnoActivity.this, ItemChecklistActivity.class);
                        startActivity(it);
                        finish();

                    }

                } else {

                    Intent it = new Intent(ListaTurnoActivity.this, MenuMotoMecActivity.class);
                    startActivity(it);
                    finish();

                }


            }

        });


    }

    public void onBackPressed() {
    }

}
