package br.com.usinasantafe.ecm.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.usinasantafe.ecm.ECMContext;
import br.com.usinasantafe.ecm.R;

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
                TextView textView = v.findViewById(R.id.textViewItemList);
                String text = textView.getText().toString();

                if (text.equals("FINALIZAR CERTIFICADO")) {
                    Intent it = new Intent(ListaFinalizaApontActivity.this, MsgSaidaCampoActivity.class);
                    startActivity(it);
                    finish();
                }
                else if (text.equals("DESFAZER CERTIFICADO")) {
                    ecmContext.getCECCTR().clearPreCECAberto();
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
