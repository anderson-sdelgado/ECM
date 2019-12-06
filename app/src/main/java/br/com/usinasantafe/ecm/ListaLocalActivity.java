package br.com.usinasantafe.ecm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.ecm.model.bean.estaticas.LocalBean;

public class ListaLocalActivity extends ActivityGeneric {

    private ListView lista;
    private ECMContext ecmContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_local);

        ecmContext = (ECMContext) getApplication();
        Button buttonRetListaLocal = (Button) findViewById(R.id.buttonRetListaLocal);

        listarMenu();

        buttonRetListaLocal.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent it = new Intent(ListaLocalActivity.this, ListaTurnoActivity.class);
                startActivity(it);
                finish();
            }

        });

    }

    public void listarMenu(){

        LocalBean localBD = new LocalBean();
        final List listaLocal =  localBD.all();
        ArrayList<String> itens = new ArrayList<String>();

        for(int i = 0; i < listaLocal.size(); i++){
            localBD = (LocalBean) listaLocal.get(i);
            itens.add(localBD.getDescLocal());
        }

        AdapterList adapterList = new AdapterList(this, itens);
        lista = (ListView) findViewById(R.id.listaLocal);
        lista.setAdapter(adapterList);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {


                LocalBean localBD = (LocalBean) listaLocal.get(position);
                ecmContext.getCompVVinhacaBean().setLocal(localBD.getIdLocal());
                Intent it = new Intent(ListaLocalActivity.this, OSActivity.class);
                startActivity(it);
                finish();
            }

        });


    }

    public void onBackPressed()  {
    }

}
