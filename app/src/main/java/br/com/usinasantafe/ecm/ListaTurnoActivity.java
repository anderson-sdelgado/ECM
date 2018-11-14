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
import br.com.usinasantafe.ecm.to.tb.estaticas.ItemChecklistTO;
import br.com.usinasantafe.ecm.to.tb.estaticas.TurnoTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.ConfiguracaoTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.InfBoletimTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.RespItemCheckListTO;

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

                TurnoTO turnoBD = (TurnoTO) listaTurno.get(position);

                InfBoletimTO infBoletimTO = new InfBoletimTO();
                List lTurno = infBoletimTO.all();
                infBoletimTO = (InfBoletimTO) lTurno.get(0);
                infBoletimTO.setTurno(turnoBD.getIdTurno());
                infBoletimTO.update();

                if (turnoBD.getIdTurno() == 2) {

                    ConfiguracaoTO configTO = new ConfiguracaoTO();
                    List listaConfig = configTO.all();
                    configTO = (ConfiguracaoTO) listaConfig.get(0);

                    if(!configTO.getDtUltimoCheckListConfig().equals(Tempo.getInstance().dataSHora())) {

                        ecmContext.setListRespChecklist(new ArrayList<RespItemCheckListTO>());

                        ItemChecklistTO itemChecklistTO = new ItemChecklistTO();
                        List items = itemChecklistTO.all();

                        for (int i = 0; i < items.size(); i++) {

                            RespItemCheckListTO respItemCheckListTO = new RespItemCheckListTO();
                            itemChecklistTO = (ItemChecklistTO) items.get(i);

                            respItemCheckListTO.setIdItItemCheckList(itemChecklistTO.getIdItemChecklist());
                            ecmContext.getListRespChecklist().add(respItemCheckListTO);

                        }

                        ecmContext.setPosChecklist(0);
                        Intent it = new Intent(ListaTurnoActivity.this, ItemChecklistActivity.class);
                        startActivity(it);
                        finish();

                    }else{

                        Intent it = new Intent(ListaTurnoActivity.this, TipoFuncoesActivity.class);
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

    public void onBackPressed()  {
    }

}
