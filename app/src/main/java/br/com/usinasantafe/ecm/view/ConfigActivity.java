package br.com.usinasantafe.ecm.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.usinasantafe.ecm.ECMContext;
import br.com.usinasantafe.ecm.R;
import br.com.usinasantafe.ecm.control.ConfigCTR;
import br.com.usinasantafe.ecm.model.bean.estaticas.AtividadeBean;
import br.com.usinasantafe.ecm.model.bean.estaticas.EquipBean;
import br.com.usinasantafe.ecm.model.bean.estaticas.EquipSegBean;
import br.com.usinasantafe.ecm.model.bean.estaticas.FrenteBean;
import br.com.usinasantafe.ecm.model.bean.estaticas.FuncBean;
import br.com.usinasantafe.ecm.model.bean.estaticas.ItemCheckListBean;
import br.com.usinasantafe.ecm.model.bean.estaticas.MotoMecBean;
import br.com.usinasantafe.ecm.model.bean.estaticas.OSBean;
import br.com.usinasantafe.ecm.model.bean.estaticas.PneuBean;
import br.com.usinasantafe.ecm.model.bean.estaticas.REquipAtivBean;
import br.com.usinasantafe.ecm.model.bean.estaticas.REquipPneuBean;
import br.com.usinasantafe.ecm.model.bean.estaticas.TurnoBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.ApontImpleMMBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.ApontMMBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.BoletimMMBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.CECBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.CabecCLBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.CabecPneuBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.CarretaBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.InfColheitaBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.InfPlantioBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.ItemPneuBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.PreCECBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.RespItemCLBean;
import br.com.usinasantafe.ecm.util.ConexaoWeb;
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
        Button buttonLimparBD = (Button) findViewById(R.id.buttonLimparBD);
        editTextCamConfig = (EditText)  findViewById(R.id.editTextCamConfig );
        editTextSenhaConfig = (EditText)  findViewById(R.id.editTextSenhaConfig);

        if(ecmContext.getConfigCTR().hasElements()){

            ConfigBean configBean = ecmContext.getConfigCTR().getConfig();
            editTextCamConfig.setText(String.valueOf(ecmContext.getConfigCTR().getEquip().getNroEquip()));
            editTextSenhaConfig.setText(configBean.getSenhaConfig());

        }

        btOkConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!editTextCamConfig.getText().toString().equals("") &&
                        !editTextSenhaConfig.getText().toString().equals("")){

                    progressBar = new ProgressDialog(v.getContext());
                    progressBar.setCancelable(true);
                    progressBar.setMessage("Pequisando o Equipamento...");
                    progressBar.show();

                    ConfigCTR configCTR = new ConfigCTR();
                    configCTR.salvarConfig(editTextSenhaConfig.getText().toString().trim());
                    configCTR.verEquipConfig(editTextCamConfig.getText().toString().trim(), ConfigActivity.this ,MenuInicialActivity.class, progressBar);

                }

            }
        });


        buttonLimparBD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AtividadeBean atividadeBean = new AtividadeBean();
                atividadeBean.deleteAll();

                EquipBean equipBean = new EquipBean();
                equipBean.deleteAll();

                EquipSegBean equipSegBean = new EquipSegBean();
                equipSegBean.deleteAll();

                FrenteBean frenteBean = new FrenteBean();
                frenteBean.deleteAll();

                FuncBean funcBean = new FuncBean();
                funcBean.deleteAll();

                ItemCheckListBean itemCheckListBean = new ItemCheckListBean();
                itemCheckListBean.deleteAll();

                MotoMecBean motoMecBean = new MotoMecBean();
                motoMecBean.deleteAll();

                OSBean osBean = new OSBean();
                osBean.deleteAll();

                PneuBean pneuBean = new PneuBean();
                pneuBean.deleteAll();

                REquipAtivBean rEquipAtivBean = new REquipAtivBean();
                rEquipAtivBean.deleteAll();

                REquipPneuBean rEquipPneuBean = new REquipPneuBean();
                rEquipPneuBean.deleteAll();

                TurnoBean turnoBean = new TurnoBean();
                turnoBean.deleteAll();

                ApontImpleMMBean apontImpleMMBean = new ApontImpleMMBean();
                apontImpleMMBean.deleteAll();

                ApontMMBean apontMMBean = new ApontMMBean();
                apontMMBean.deleteAll();

                BoletimMMBean boletimMMBean = new BoletimMMBean();
                boletimMMBean.deleteAll();

                CabecCLBean cabecCLBean = new CabecCLBean();
                cabecCLBean.deleteAll();

                CabecPneuBean cabecPneuBean = new CabecPneuBean();
                cabecPneuBean.deleteAll();

                CarretaBean carretaBean = new CarretaBean();
                carretaBean.deleteAll();

                CECBean cecBean = new CECBean();
                cecBean.deleteAll();

                ConfigBean configBean = new ConfigBean();
                configBean.deleteAll();

                InfColheitaBean infColheitaBean = new InfColheitaBean();
                infColheitaBean.deleteAll();

                InfPlantioBean infPlantioBean = new InfPlantioBean();
                infPlantioBean.deleteAll();

                ItemPneuBean itemPneuBean = new ItemPneuBean();
                itemPneuBean.deleteAll();

                PreCECBean preCECBean = new PreCECBean();
                preCECBean.deleteAll();

                RespItemCLBean respItemCLBean = new RespItemCLBean();
                respItemCLBean.deleteAll();

                AlertDialog.Builder alerta = new AlertDialog.Builder(ConfigActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("TODOS OS DADOS FORAM APAGADOS!");
                alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                alerta.show();

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
