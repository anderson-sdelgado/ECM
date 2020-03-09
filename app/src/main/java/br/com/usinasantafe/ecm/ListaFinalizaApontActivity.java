package br.com.usinasantafe.ecm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ListaFinalizaApontActivity extends ActivityGeneric {

    private ListView finalizarListView;
    private ECMContext ecmContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_finaliza_apont);

        ecmContext = (ECMContext) getApplication();
        listarMenu();

    }

    public void listarMenu(){

        ArrayList<String> itens = new ArrayList<String>();

        itens.add("FINALIZAR CERTIFICADO");
        itens.add("DESFAZER CERTIFICADO");

        AdapterList adapterList = new AdapterList(this, itens);
        finalizarListView = (ListView) findViewById(R.id.listViewFinalizaApont);
        finalizarListView.setAdapter(adapterList);

        finalizarListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                if(position == 0){
                    Intent it = new Intent(ListaFinalizaApontActivity.this, MsgSaidaCampoActivity.class);
                    startActivity(it);
                    finish();
                }
                else if(position == 1){

                    ecmContext.getCertifCanaCTR().delCertifAberto();
                    Intent it = new Intent(ListaFinalizaApontActivity.this, MenuCertifActivity.class);
                    startActivity(it);
                    finish();
                }

            }

        });

    }

    public void onBackPressed()  {
    }

}
