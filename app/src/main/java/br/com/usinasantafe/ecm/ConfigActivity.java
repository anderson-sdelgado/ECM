package br.com.usinasantafe.ecm;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.usinasantafe.ecm.model.bean.estaticas.EquipBean;
import br.com.usinasantafe.ecm.util.ConexaoWeb;
import br.com.usinasantafe.ecm.util.AtualDadosServ;
import br.com.usinasantafe.ecm.model.bean.variaveis.ConfigBean;

public class ConfigActivity extends ActivityGeneric {

    private ProgressDialog progressBar;
    private EditText editTextCamConfig;
    private EditText editTextSenhaConfig;
    private ECMContext ecmContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        ecmContext = (ECMContext) getApplication();

        Button btOkConfig =  (Button) findViewById(R.id.buttonSalvarConfig );
        Button btCancConfig = (Button) findViewById(R.id.buttonCancConfig);
        Button btAtualBDConfig = (Button) findViewById(R.id.buttonAtualizarBD);
        editTextCamConfig = (EditText)  findViewById(R.id.editTextCamConfig );
        editTextSenhaConfig = (EditText)  findViewById(R.id.editTextSenhaConfig);

        if(ecmContext.getConfigCTR().hasElements()){

            ConfigBean configBean = ecmContext.getConfigCTR().getConfig();

            editTextCamConfig.setText(String.valueOf(configBean.getCodEquipConfig()));
            editTextSenhaConfig.setText(configBean.getSenhaConfig());
        }


        btOkConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!editTextCamConfig.getText().toString().equals("") &&
                        !editTextSenhaConfig.getText().toString().equals("")){

                    EquipBean equipBean = new EquipBean();

                    if(equipBean.hasElements()){

                        if(ecmContext.getConfigCTR().verCaminhao(Long.valueOf(editTextCamConfig.getText().toString()))){

                            equipBean = ecmContext.getConfigCTR().getCaminhao(Long.valueOf(editTextCamConfig.getText().toString()));

                            ConfigBean configBean = new ConfigBean();
                            configBean.setIdEquipConfig(equipBean.getIdEquip());
                            configBean.setCodEquipConfig(equipBean.getNroEquip());
                            configBean.setSenhaConfig(editTextSenhaConfig.getText().toString());

                            ecmContext.getConfigCTR().insConfig(configBean);

                            Intent it = new Intent(ConfigActivity.this, MenuInicialActivity.class);
                            startActivity(it);
                            finish();

                        }

                    }

                }

            }
        });

        btCancConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent(ConfigActivity.this, MenuInicialActivity.class);
                startActivity(it);
                finish();

            }
        });

        btAtualBDConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ConexaoWeb conexaoWeb = new ConexaoWeb();

                if(conexaoWeb.verificaConexao(ConfigActivity.this)){
                    progressBar = new ProgressDialog(v.getContext());
                    progressBar.setCancelable(true);
                    progressBar.setMessage("ATUALIZANDO ...");
                    progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    progressBar.setProgress(0);
                    progressBar.setMax(100);
                    progressBar.show();

                    ecmContext.getConfigCTR().atualTodasTabelas(ConfigActivity.this, progressBar);

                }
                else{
                    AlertDialog.Builder alerta = new AlertDialog.Builder(ConfigActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.");
                    alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                    alerta.show();
                }
            }
        });

    }

    public void onBackPressed()  {
    }

}
