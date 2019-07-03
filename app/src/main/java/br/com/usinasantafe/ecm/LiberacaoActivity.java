package br.com.usinasantafe.ecm;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.ecm.bo.ConexaoWeb;
import br.com.usinasantafe.ecm.bo.ManipDadosVerif;
import br.com.usinasantafe.ecm.pst.EspecificaPesquisa;
import br.com.usinasantafe.ecm.to.tb.estaticas.LiberacaoTO;

public class LiberacaoActivity extends ActivityGeneric {

    private ECMContext ecmContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liberacao);

        ecmContext = (ECMContext) getApplication();

        Button buttonOkLiberacao = (Button) findViewById(R.id.buttonOkPadrao);
        Button buttonCancLiberacao = (Button) findViewById(R.id.buttonCancPadrao);

        buttonOkLiberacao.setOnClickListener(new View.OnClickListener() {

            @SuppressWarnings({ "rawtypes", "unchecked" })
            @Override
            public void onClick(View v) {

                if(!editTextPadrao.getText().toString().equals("")){

                    LiberacaoTO liberacaoTO = new LiberacaoTO();

                    ArrayList libPesqArrayList = new ArrayList();

                    EspecificaPesquisa pesquisa1 = new EspecificaPesquisa();
                    pesquisa1.setCampo("codigoLiberacao");
                    pesquisa1.setValor(Long.parseLong(editTextPadrao.getText().toString()));

                    libPesqArrayList.add(pesquisa1);

                    EspecificaPesquisa pesquisa2 = new EspecificaPesquisa();
                    pesquisa2.setCampo("nroOSLiberacao");
                    pesquisa2.setValor(ecmContext.getNroOS());

                    libPesqArrayList.add(pesquisa2);

                    List liberacaoList = liberacaoTO.get(libPesqArrayList);

                    if(liberacaoList.size() > 0){

                        ecmContext.setLiberacaoOS(Long.parseLong(editTextPadrao.getText().toString()));
                        Intent it = new Intent(LiberacaoActivity.this, MsgLiberacaoActivity.class);
                        startActivity(it);
                        finish();

                    }
                    else{

                        ConexaoWeb conexaoWeb = new ConexaoWeb();

                        ecmContext.setLiberacaoOS(Long.parseLong(editTextPadrao.getText().toString()));
                        ecmContext.setCodigoAtivOS(Long.parseLong(editTextPadrao.getText().toString()));

                        if (conexaoWeb.verificaConexao(LiberacaoActivity.this)) {

                        ManipDadosVerif.getInstance().verDados(editTextPadrao.getText().toString() + "_" + ecmContext.getNroOS(), "LiberacaoTO"
                                ,LiberacaoActivity.this ,MsgLiberacaoActivity.class);

                        } else {
                            AlertDialog.Builder alerta = new AlertDialog.Builder(LiberacaoActivity.this);
                            alerta.setTitle("ATENÇÃO");
                            alerta.setMessage("FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVER COM SINAL.");
                            alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });

                            alerta.show();
                        }

                    }


                }

            }
        });

        buttonCancLiberacao.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(editTextPadrao.getText().toString().length() > 0){
                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
                }
                else{
                    Intent it = new Intent(LiberacaoActivity.this, OSActivity.class);
                    startActivity(it);
                    finish();
                }
            }
        });


    }

    public void onBackPressed()  {
    }

}
