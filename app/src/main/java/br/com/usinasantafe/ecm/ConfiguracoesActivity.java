package br.com.usinasantafe.ecm;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import br.com.usinasantafe.ecm.bo.ConexaoWeb;
import br.com.usinasantafe.ecm.bo.ManipDadosReceb;
import br.com.usinasantafe.ecm.to.tb.estaticas.CaminhaoTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.ConfiguracaoTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.InfBoletimTO;

public class ConfiguracoesActivity extends ActivityGeneric {

    private ProgressDialog progressBar;
    private int qtdetabela;
    private EditText editTextCamConfig;
    private EditText editTextSenhaConfig;
    private ECMContext ecmContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracoes);

        ecmContext = (ECMContext) getApplication();

        Button btOkConfig =  (Button) findViewById(R.id.buttonSalvarConfig );
        Button btCancConfig = (Button) findViewById(R.id.buttonCancConfig);
        Button btAtualBDConfig = (Button) findViewById(R.id.buttonAtualizarBD);
        editTextCamConfig = (EditText)  findViewById(R.id.editTextCamConfig );
        editTextSenhaConfig = (EditText)  findViewById(R.id.editTextSenhaConfig);

        if(ecmContext.isVerTabelaConfig()){
            editTextCamConfig.setText(String.valueOf(ecmContext.getEquipConfig()));
            editTextSenhaConfig.setText(ecmContext.getSenhaConfig());
        }


        btOkConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                if(!editTextCamConfig.getText().toString().equals("") &&
                        !editTextSenhaConfig.getText().toString().equals("")){

                    CaminhaoTO caminhaoTO = new CaminhaoTO();

                    if(caminhaoTO.hasElements()){

                        List caminhaoList = caminhaoTO.get("codCaminhao", Long.valueOf(editTextCamConfig.getText().toString()));

                        if(caminhaoList.size() > 0){

                            caminhaoTO = (CaminhaoTO) caminhaoList.get(0);
                            ConfiguracaoTO configuracaoTO = new ConfiguracaoTO();
                            configuracaoTO.setIdCamConfig(caminhaoTO.getIdCaminhao());
                            configuracaoTO.setCodCamConfig(Long.valueOf(editTextCamConfig.getText().toString()));
                            configuracaoTO.setSenhaConfig(editTextSenhaConfig.getText().toString());
                            configuracaoTO.setDtUltimoCheckListConfig("");
                            configuracaoTO.deleteAll();
                            configuracaoTO.insert();

                            InfBoletimTO infBoletimTO = new InfBoletimTO();
                            infBoletimTO.deleteAll();
                            infBoletimTO.setCam(Long.valueOf(editTextCamConfig.getText().toString()));
                            infBoletimTO.setDataSaidaUsina("");
                            infBoletimTO.setDataChegCampo("");
                            infBoletimTO.setDataSaidaCampo("");
                            infBoletimTO.insert();

                            Intent it = new Intent(ConfiguracoesActivity.this, PrincipalActivity.class);
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
                // TODO Auto-generated method stub

                Intent it = new Intent(ConfiguracoesActivity.this, PrincipalActivity.class);
                startActivity(it);
                finish();

            }
        });

        btAtualBDConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                ConexaoWeb conexaoWeb = new ConexaoWeb();

                if(conexaoWeb.verificaConexao(ConfiguracoesActivity.this)){
                    progressBar = new ProgressDialog(v.getContext());
                    progressBar.setCancelable(true);
                    progressBar.setMessage("ATUALIZANDO ...");
                    progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    progressBar.setProgress(0);
                    progressBar.setMax(100);
                    progressBar.show();
                    ManipDadosReceb.getInstance().atualizarBD(progressBar);
                    ManipDadosReceb.getInstance().setContext(ConfiguracoesActivity.this);
                }
                else{
                    AlertDialog.Builder alerta = new AlertDialog.Builder(ConfiguracoesActivity.this);
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
