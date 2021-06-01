package br.com.usinasantafe.ecm.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import java.util.List;

import br.com.usinasantafe.ecm.ECMContext;
import br.com.usinasantafe.ecm.R;
import br.com.usinasantafe.ecm.model.bean.estaticas.PneuBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.ItemPneuBean;
import br.com.usinasantafe.ecm.util.ConexaoWeb;
import br.com.usinasantafe.ecm.util.VerifDadosServ;

public class PneuActivity extends ActivityGeneric {

    private ECMContext ecmContext;
    private ProgressDialog progressBar;
    private Handler customHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pneu);

        ecmContext = (ECMContext) getApplication();

        Button buttonOkPneu = (Button) findViewById(R.id.buttonOkPadrao);
        Button buttonCancPneu = (Button) findViewById(R.id.buttonCancPadrao);

        buttonOkPneu.setOnClickListener(new View.OnClickListener() {
            @SuppressWarnings("rawtypes")
            @Override
            public void onClick(View v) {

                if (!editTextPadrao.getText().toString().equals("")) {

                    ecmContext.getPneuCTR().getItemPneuBean().setNroPneuItemPneu(editTextPadrao.getText().toString());

                    if(ecmContext.getPneuCTR().verPneu(editTextPadrao.getText().toString())){

                        VerifDadosServ.getInstance().setVerTerm(true);

                        boolean verCad = true;

                        List<ItemPneuBean> itemMedPneuList = ecmContext.getPneuCTR().itemCalibPneuList();
                        for(ItemPneuBean itemPneuBean : itemMedPneuList) {
                            if(editTextPadrao.getText().toString().equals(itemPneuBean.getNroPneuItemPneu())){
                                verCad = false;
                            }
                        }
                        itemMedPneuList.clear();

                        if(verCad){
                            Intent it = new Intent(PneuActivity.this, PressaoEncPneuActivity.class);
                            startActivity(it);
                        }
                        else{

                            AlertDialog.Builder alerta = new AlertDialog.Builder(PneuActivity.this);
                            alerta.setTitle("ATENÇÃO");
                            alerta.setMessage("PNEU REPETIDO! FAVOR CALIBRAR OUTRO PNEU.");

                            alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                            alerta.show();

                        }

                    }
                    else{

                        ConexaoWeb conexaoWeb = new ConexaoWeb();

                        if (conexaoWeb.verificaConexao(PneuActivity.this)) {

                            progressBar = new ProgressDialog(PneuActivity.this);
                            progressBar.setCancelable(true);
                            progressBar.setMessage("Atualizando Pneu...");
                            progressBar.show();

                            customHandler.postDelayed(updateTimerThread, 10000);

                            ecmContext.getPneuCTR().verPneu(editTextPadrao.getText().toString()
                                    , PneuActivity.this, PressaoEncPneuActivity.class, progressBar);

                        }
                        else{

                            Intent it = new Intent(PneuActivity.this, PressaoEncPneuActivity.class);
                            startActivity(it);

                        }

                    }

                }

            }

        });

        buttonCancPneu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (editTextPadrao.getText().toString().length() > 0) {
                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
                }
            }
        });

    }

    public void onBackPressed() {
        Intent it = new Intent(PneuActivity.this, ListaPosPneuActivity.class);
        startActivity(it);
        finish();
    }

    private Runnable updateTimerThread = new Runnable() {

        public void run() {

            if(!VerifDadosServ.getInstance().isVerTerm()) {

                VerifDadosServ.getInstance().cancelVer();
                if (progressBar.isShowing()) {
                    progressBar.dismiss();
                }
                Intent it = new Intent(PneuActivity.this, PressaoEncPneuActivity.class);
                startActivity(it);
                finish();

            }

        }
    };

}
