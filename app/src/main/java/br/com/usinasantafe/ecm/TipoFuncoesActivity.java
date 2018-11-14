package br.com.usinasantafe.ecm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.ecm.to.tb.variaveis.InfBoletimTO;

public class TipoFuncoesActivity extends ActivityGeneric {

    private ListView lista;
    private ECMContext ecmContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_funcoes);

        ecmContext = (ECMContext) getApplication();

//        listarMenu();
//
//        Button buttonRetornar = (Button) findViewById(R.id.buttonRetListaTipoFuncCaminhao);
//
//        buttonRetornar.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//
//                Intent it = new Intent(TipoFuncoesActivity.this, CaminhaoTurnoActivity.class);
//                startActivity(it);
//                finish();
//            }
//
//        });

    }

//    public void listarMenu(){
//
//        ArrayList<String> itens = new ArrayList<String>();
//
//        TipoFuncCamTO tipoFuncCamBD = new TipoFuncCamTO();
//        final List listaTipoFuncCam =  tipoFuncCamBD.all();
//
//        for(int i = 0; i < listaTipoFuncCam.size(); i++){
//            tipoFuncCamBD = (TipoFuncCamTO) listaTipoFuncCam.get(i);
//            itens.add(tipoFuncCamBD.getDescrFuncaoCam());
//        }
//
//        AdapterList adapterList = new AdapterList(this, itens);
//        lista = (ListView) findViewById(R.id.listViewTipoFuncCaminhao);
//        lista.setAdapter(adapterList);
//
//        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> l, View v, int position,
//                                    long id) {
//
//                TipoFuncCamTO tipoFuncCamBD = (TipoFuncCamTO) listaTipoFuncCam.get(position);
//
//                if(position == 0) {
//
//                    InfBoletimTO infBoletimTO = new InfBoletimTO();
//                    List lTurno = infBoletimTO.all();
//                    infBoletimTO = (InfBoletimTO) lTurno.get(0);
//                    infBoletimTO.setTipoAtiv(tipoFuncCamBD.getTipoFuncaoCam());
//                    infBoletimTO.update();
//
//                    Intent it = new Intent(TipoFuncoesActivity.this, MenuMotoMecActivity.class);
//                    startActivity(it);
//                    finish();
//
//                }
//
//            }
//
//        });
//
//    }

    public void onBackPressed()  {
    }

}
