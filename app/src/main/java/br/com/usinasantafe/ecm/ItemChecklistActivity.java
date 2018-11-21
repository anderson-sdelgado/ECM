package br.com.usinasantafe.ecm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.ecm.bo.ManipDadosEnvio;
import br.com.usinasantafe.ecm.pst.EspecificaPesquisa;
import br.com.usinasantafe.ecm.to.tb.estaticas.CaminhaoTO;
import br.com.usinasantafe.ecm.to.tb.estaticas.ItemCheckListTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.CabecCheckListTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.ConfiguracaoTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.InfBoletimTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.RespItemCheckListTO;

public class ItemChecklistActivity extends ActivityGeneric {

    private ECMContext ecmContext;
    private RespItemCheckListTO respItemCheckListTO;
    private ItemCheckListTO itemCheckListTO;
    private CabecCheckListTO cabecCheckListTO;

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

        cabecCheckListTO = new CabecCheckListTO();
        List cabecCheckListLista = cabecCheckListTO.get("statusCabecCheckList", 1L);
        cabecCheckListTO = (CabecCheckListTO) cabecCheckListLista.get(0);
        cabecCheckListLista.clear();

        InfBoletimTO infBoletimTO = new InfBoletimTO();
        List lTurno = infBoletimTO.all();
        infBoletimTO = (InfBoletimTO) lTurno.get(0);

        CaminhaoTO caminhaoTO = new CaminhaoTO();
        List caminhaoList = caminhaoTO.get("codCaminhao", infBoletimTO.getCam());
        caminhaoTO = (CaminhaoTO) caminhaoList.get(0);
        caminhaoList.clear();

        itemCheckListTO = new ItemCheckListTO();
        ArrayList itemListPesq = new ArrayList();

        EspecificaPesquisa pesq3 = new EspecificaPesquisa();
        pesq3.setCampo("seqItemChecklist");
        pesq3.setValor(ecmContext.getPosChecklist());
        itemListPesq.add(pesq3);

        EspecificaPesquisa pesq4 = new EspecificaPesquisa();
        pesq4.setCampo("idChecklist");
        pesq4.setValor(caminhaoTO.getIdChecklist());
        itemListPesq.add(pesq4);

        List itemCheckListLista = itemCheckListTO.get(itemListPesq);
        itemCheckListTO = (ItemCheckListTO) itemCheckListLista.get(0);
        itemCheckListLista.clear();

        ArrayList respPesq = new ArrayList();
        EspecificaPesquisa pesq1 = new EspecificaPesquisa();
        pesq1.setCampo("idItItemCheckList");
        pesq1.setValor(itemCheckListTO.getIdItemChecklist());
        respPesq.add(pesq1);

        EspecificaPesquisa pesq2 = new EspecificaPesquisa();
        pesq2.setCampo("idCabecItemCheckList");
        pesq2.setValor(cabecCheckListTO.getIdCabecCheckList());
        respPesq.add(pesq2);

        respItemCheckListTO = new RespItemCheckListTO();
        List respList = respItemCheckListTO.get(respPesq);
        if(respList.size() > 0){
            respItemCheckListTO = (RespItemCheckListTO) respList.get(0);
            respItemCheckListTO.delete();
        }

        textViewItemChecklist.setText(itemCheckListTO.getSeqItemChecklist() + " - " + itemCheckListTO.getDescrItemChecklist());

        buttonConforme.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
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

        respItemCheckListTO.setIdCabecItemCheckList(cabecCheckListTO.getIdCabecCheckList());
        respItemCheckListTO.setIdItItemCheckList(itemCheckListTO.getIdItemChecklist());
        respItemCheckListTO.setOpcaoItemCheckList(opcao);
        respItemCheckListTO.insert();

        if(cabecCheckListTO.getQtdeItemCabecCheckList() == ecmContext.getPosChecklist()){

            Intent it = new Intent(ItemChecklistActivity.this, TipoFuncoesActivity.class);
            startActivity(it);
            finish();

            ManipDadosEnvio.getInstance().salvaCheckList();

        }
        else{

            ecmContext.setPosChecklist(ecmContext.getPosChecklist() + 1);
            Intent it = new Intent(ItemChecklistActivity.this, ItemChecklistActivity.class);
            startActivity(it);
            finish();

        }

    }

    public void retornoTela(){

        if(ecmContext.getPosChecklist() != 0){
            ecmContext.setPosChecklist(ecmContext.getPosChecklist() - 1);
            Intent it = new Intent(ItemChecklistActivity.this, ItemChecklistActivity.class);
            startActivity(it);
            finish();
        }

    }

    public void onBackPressed()  {
    }

}
