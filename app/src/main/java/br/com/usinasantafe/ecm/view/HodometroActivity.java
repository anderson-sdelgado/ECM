package br.com.usinasantafe.ecm.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import br.com.usinasantafe.ecm.ECMContext;
import br.com.usinasantafe.ecm.R;
import br.com.usinasantafe.ecm.control.CheckListCTR;
import br.com.usinasantafe.ecm.util.ConexaoWeb;

public class HodometroActivity extends ActivityGeneric {

    private ECMContext ecmContext;
    private Double horimetroNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hodometro);

        ecmContext = (ECMContext) getApplication();

        Button buttonOkHorimetro = (Button) findViewById(R.id.buttonOkPadrao);
        Button buttonCancHorimetro = (Button) findViewById(R.id.buttonCancPadrao);

        TextView textViewHorimetro = (TextView) findViewById(R.id.textViewPadrao);
        if (ecmContext.getVerPosTela() == 1) {
            textViewHorimetro.setText("HODOMETRO INICIAL");
        }
        else if (ecmContext.getVerPosTela() == 8) {
            textViewHorimetro.setText("HODOMETRO FINAL");
        }
        else if (ecmContext.getVerPosTela() == 9) {
            textViewHorimetro.setText("HODOMETRO FINAL");
        }
        else if (ecmContext.getVerPosTela() == 10) {
            textViewHorimetro.setText("HODOMETRO INICIAL");
        }

        buttonOkHorimetro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!editTextPadrao.getText().toString().equals("")) {

                    String horimetro = editTextPadrao.getText().toString();
                    horimetroNum = Double.valueOf(horimetro.replace(",", "."));

                    if (horimetroNum >= ecmContext.getConfigCTR().getConfig().getHorimetroConfig()) {
                        verTela();
                    } else {

                        AlertDialog.Builder alerta = new AlertDialog.Builder(HodometroActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("O HODOMETRO DIGITADO " + horimetroNum + " É MENOR QUE O HODOMETRO ANTERIOR DA MAQUINA " + ecmContext.getConfigCTR().getConfig().getHorimetroConfig() + ". DESEJA MANTER ESSE VALOR?");

                        alerta.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                verTela();
                            }

                        });

                        alerta.setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                        alerta.show();

                    }

                }

            }
        });

        buttonCancHorimetro.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (editTextPadrao.getText().toString().length() > 0) {
                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
                }
            }
        });


    }

    public void verTela(){
        if (ecmContext.getVerPosTela() == 1) {
            salvarBoletimAberto();
        }
        else if (ecmContext.getVerPosTela() == 8) {
            salvarBoletimFechado();
        }
        else if (ecmContext.getVerPosTela() == 9) {
            salvarBoletimFechado();
        }
        else if (ecmContext.getVerPosTela() == 10) {
            salvarBoletimAberto();
        }
    }

    public void salvarBoletimAberto() {

        ecmContext.getConfigCTR().setHorimetroConfig(horimetroNum);

        ecmContext.getMotoMecCTR().setHodometroInicialBol(horimetroNum, getLongitude(), getLatitude());
        ecmContext.getMotoMecCTR().salvarBolAbertoMM();

        CheckListCTR checkListCTR = new CheckListCTR();
        if(checkListCTR.verAberturaCheckList(ecmContext.getMotoMecCTR().getTurno())){
            Long statusCon;
            ConexaoWeb conexaoWeb = new ConexaoWeb();
            if (conexaoWeb.verificaConexao(HodometroActivity.this)) {
                statusCon = 1L;
            }
            else{
                statusCon = 0L;
            }
            ecmContext.getMotoMecCTR().insParadaCheckList(getLongitude(), getLatitude(), statusCon);
            ecmContext.setPosCheckList(1);
            checkListCTR.createCabecAberto(ecmContext.getMotoMecCTR());
            if (ecmContext.getConfigCTR().getConfig().getAtualCheckList().equals(1L)) {
                Intent it = new Intent(HodometroActivity.this, PergAtualCheckListActivity.class);
                startActivity(it);
                finish();
            } else {
                Intent it = new Intent(HodometroActivity.this, ItemCheckListActivity.class);
                startActivity(it);
                finish();
            }
        }
        else{
            if (ecmContext.getVerPosTela() == 1) {
                Intent it = new Intent(HodometroActivity.this, MenuMotoMecActivity.class);
                startActivity(it);
                finish();
            } else if (ecmContext.getVerPosTela() == 10) {
                Intent it = new Intent(HodometroActivity.this, VerMotoristaActivity.class);
                startActivity(it);
                finish();
            }
        }
    }

    public void salvarBoletimFechado() {

        ecmContext.getConfigCTR().setHorimetroConfig(horimetroNum);
        ecmContext.getMotoMecCTR().setHodometroFinalBol(horimetroNum);
        ecmContext.getMotoMecCTR().salvarBolFechadoMM();

        if (ecmContext.getVerPosTela() == 8) {
            Intent it = new Intent(HodometroActivity.this, MenuInicialActivity.class);
            startActivity(it);
            finish();
        } else if (ecmContext.getVerPosTela() == 9) {
            ecmContext.setVerPosTela(10);
            Intent it = new Intent(HodometroActivity.this, FuncionarioActivity.class);
            startActivity(it);
            finish();
        }

    }

    public void onBackPressed() {
        if (ecmContext.getVerPosTela() == 1) {
            Intent it = new Intent(HodometroActivity.this, ListaAtividadeActivity.class);
            startActivity(it);
            finish();
        }
        else {
            Intent it = new Intent(HodometroActivity.this, MenuMotoMecActivity.class);
            startActivity(it);
            finish();
        }
    }

}
