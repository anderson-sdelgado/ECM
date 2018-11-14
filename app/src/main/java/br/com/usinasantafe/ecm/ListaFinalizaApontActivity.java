package br.com.usinasantafe.ecm;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ListaFinalizaApontActivity extends ActivityGeneric {

    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_finaliza_apont);

        listarMenu();

    }

    public void listarMenu(){

        ArrayList<String> itens = new ArrayList<String>();

        itens.add("FINALIZAR APONTAMENTO");
        itens.add("DESFAZER APONTAMENTO");

        AdapterList adapterList = new AdapterList(this, itens);
        lista = (ListView) findViewById(R.id.listViewFinalizaApont);
        lista.setAdapter(adapterList);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {


                if(position == 0){
                    Intent it = new Intent(ListaFinalizaApontActivity.this, MsgSaidaCampoActivity.class);
                    startActivity(it);
                    finish();
                }

                else if(position == 1){
                    Intent it = new Intent(ListaFinalizaApontActivity.this, MenuInicialApontActivity.class);
                    startActivity(it);
                    finish();
                }

            }

        });

    }

    public void onBackPressed()  {
    }

}
