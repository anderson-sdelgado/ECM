package br.com.usinasantafe.ecm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import br.com.usinasantafe.ecm.to.tb.variaveis.ConfiguracaoTO;

public class SenhaActivity extends ActivityGeneric {

    private EditText editTextSenha;
    private ECMContext ecmContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_senha);

        ecmContext = (ECMContext) getApplication();

        editTextSenha = (EditText)  findViewById(R.id.editTextSenha);
        Button btOkSenha =  (Button) findViewById(R.id.buttonOkSenha);
        Button btCancSenha = (Button) findViewById(R.id.buttonCancSenha);

        btOkSenha.setOnClickListener(new View.OnClickListener() {

            @SuppressWarnings("unchecked")
            @Override
            public void onClick(View v) {


                ConfiguracaoTO configuracaoTO = new ConfiguracaoTO();

                if (!configuracaoTO.hasElements()) {

                    ecmContext.setVerTabelaConfig(false);

                    Intent it = new Intent(SenhaActivity.this, ConfiguracoesActivity.class);
                    startActivity(it);
                    finish();

                } else {

                    List<ConfiguracaoTO> lista = configuracaoTO.get("senhaConfig", editTextSenha.getText().toString());

                    if (lista.size() > 0) {

                        configuracaoTO.setCodCamConfig(((ConfiguracaoTO) lista.get(0)).getCodCamConfig());

                        ecmContext.setVerTabelaConfig(true);
                        ecmContext.setEquipConfig(configuracaoTO.getCodCamConfig());
                        ecmContext.setSenhaConfig(editTextSenha.getText().toString());

                        Intent it = new Intent(SenhaActivity.this, ConfiguracoesActivity.class);
                        startActivity(it);
                        finish();

                    }

                }

            }
        });

        btCancSenha.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent it = new Intent(SenhaActivity.this, PrincipalActivity.class);
                startActivity(it);
                finish();
            }

        });

    }

    public void onBackPressed()  {
    }

}
