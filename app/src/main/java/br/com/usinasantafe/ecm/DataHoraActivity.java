package br.com.usinasantafe.ecm;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.usinasantafe.ecm.control.ConfigCTR;
import br.com.usinasantafe.ecm.model.bean.variaveis.ConfigBean;
import br.com.usinasantafe.ecm.util.Tempo;

public class DataHoraActivity extends ActivityGeneric {

    private ECMContext ecmContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_hora);

        ecmContext = (ECMContext) getApplication();

        Button buttonOkDataHora = (Button) findViewById(R.id.buttonOkPadrao);
        Button buttonCancDataHora = (Button) findViewById(R.id.buttonCancPadrao);
        TextView textViewDataHora = (TextView) findViewById(R.id.textViewPadrao);

        switch (ecmContext.getContDataHora()) {
            case 1:
                textViewDataHora.setText("DIA:");
                break;
            case 2:
                textViewDataHora.setText("MÊS:");
                break;
            case 3:
                textViewDataHora.setText("ANO:");
                break;
            case 4:
                textViewDataHora.setText("HORA:");
                break;
            case 5:
                textViewDataHora.setText("MINUTOS:");
                break;
        }

        buttonOkDataHora.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (!editTextPadrao.getText().toString().equals("")) {

                    int valor = Integer.parseInt(editTextPadrao.getText().toString());

                    Intent it;
                    switch (ecmContext.getContDataHora()) {
                        case 1:
                            if((valor <= 31)){
                                ecmContext.setDia(valor);
                                ecmContext.setContDataHora(ecmContext.getContDataHora() + 1);
                                it = new Intent(DataHoraActivity.this, DataHoraActivity.class);
                                startActivity(it);
                                finish();
                            }
                            else{
                                AlertDialog.Builder alerta = new AlertDialog.Builder( DataHoraActivity.this);
                                alerta.setTitle("ATENÇÃO");
                                alerta.setMessage("DIA INCORRETO! FAVOR VERIFICAR.");
                                alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });
                                alerta.show();
                            }
                            break;
                        case 2:
                            if((valor <= 12)){
                                ecmContext.setMes(valor);
                                ecmContext.setContDataHora(ecmContext.getContDataHora() + 1);
                                it = new Intent(DataHoraActivity.this, DataHoraActivity.class);
                                startActivity(it);
                                finish();
                            }
                            else{
                                AlertDialog.Builder alerta = new AlertDialog.Builder( DataHoraActivity.this);
                                alerta.setTitle("ATENÇÃO");
                                alerta.setMessage("MÊS INCORRETO! FAVOR VERIFICAR.");
                                alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });
                                alerta.show();
                            }
                            break;
                        case 3:
                            if((valor >= 2020) && (valor <= 3000)){
                                ecmContext.setAno(valor);
                                ecmContext.setContDataHora(ecmContext.getContDataHora() + 1);
                                it = new Intent(DataHoraActivity.this, DataHoraActivity.class);
                                startActivity(it);
                                finish();
                            }
                            else{
                                AlertDialog.Builder alerta = new AlertDialog.Builder( DataHoraActivity.this);
                                alerta.setTitle("ATENÇÃO");
                                alerta.setMessage("ANO INCORRETO! FAVOR VERIFICAR.");
                                alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });
                                alerta.show();
                            }
                            break;
                        case 4:
                            if(valor <= 23){
                                ecmContext.setHora(valor);
                                ecmContext.setContDataHora(ecmContext.getContDataHora() + 1);
                                it = new Intent(DataHoraActivity.this, DataHoraActivity.class);
                                startActivity(it);
                                finish();
                            }
                            else{
                                AlertDialog.Builder alerta = new AlertDialog.Builder( DataHoraActivity.this);
                                alerta.setTitle("ATENÇÃO");
                                alerta.setMessage("HORA INCORRETA! FAVOR VERIFICAR.");
                                alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });
                                alerta.show();
                            }

                            break;
                        case 5:
                            if(valor <= 59){
                                ecmContext.setMinuto(valor);
                                Long dif = Tempo.getInstance().difDthr(ecmContext.getDia(), ecmContext.getMes(), ecmContext.getAno()
                                        , ecmContext.getHora(), ecmContext.getMinuto());

                                ConfigCTR configCTR = new ConfigCTR();

                                ConfigBean configBean = configCTR.getConfig();
                                configBean.setDifDthrConfig(dif);
                                configBean.update();

                                if (ecmContext.getVerPosTela() == 1) {
                                    it = new Intent(DataHoraActivity.this, OSActivity.class);
                                    startActivity(it);
                                    finish();
                                }
                                else {
                                    it = new Intent(DataHoraActivity.this, MenuMotoMecActivity.class);
                                    startActivity(it);
                                    finish();
                                }
                            }
                            else{
                                AlertDialog.Builder alerta = new AlertDialog.Builder( DataHoraActivity.this);
                                alerta.setTitle("ATENÇÃO");
                                alerta.setMessage("MINUTO INCORRETO! FAVOR VERIFICAR.");
                                alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });
                                alerta.show();
                            }
                            break;
                    }

                }

            }
        });

        buttonCancDataHora.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (editTextPadrao.getText().toString().length() > 0) {
                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
                }
            }
        });

    }

    public void onBackPressed()  {
        if(ecmContext.getContDataHora() > 1){
            ecmContext.setContDataHora(ecmContext.getContDataHora() - 1);
            Intent it = new Intent(DataHoraActivity.this, DataHoraActivity.class);
            startActivity(it);
            finish();
        }
    }

}
