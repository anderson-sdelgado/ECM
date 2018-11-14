package br.com.usinasantafe.ecm;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import br.com.usinasantafe.ecm.bo.ManipDadosEnvio;
import br.com.usinasantafe.ecm.bo.Tempo;
import br.com.usinasantafe.ecm.to.tb.variaveis.ConfiguracaoTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.InfBoletimTO;

public class VerMotoristaActivity extends ActivityGeneric {

    private ListView lista;
    private ECMContext ecmContext;
    private InfBoletimTO infBoletimTO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_motorista);

        ecmContext = (ECMContext) getApplication();

        Button buttonManterMotorista = (Button) findViewById(R.id.buttonManterMotorista);
        Button buttonAlterarCabecalho = (Button) findViewById(R.id.buttonAlterarCabecalho);

        TextView textViewCodMotorista = (TextView) findViewById(R.id.textViewCodMotorista);
        TextView textViewNomeMotorista = (TextView) findViewById(R.id.textViewNomeMotorista);

        infBoletimTO = new InfBoletimTO();
        List lTurno = infBoletimTO.all();
        infBoletimTO = (InfBoletimTO) lTurno.get(0);

        textViewCodMotorista.setText(String.valueOf(infBoletimTO.getCodigoMoto()));
        textViewNomeMotorista.setText(String.valueOf(infBoletimTO.getNomeMoto()));

        buttonManterMotorista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(infBoletimTO.getTipoAtiv() == 1){

                    AlertDialog.Builder alerta = new AlertDialog.Builder(VerMotoristaActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("A VIAGEM FOI FINALIZADA E SERÁ ENVIADA AUTOMATICAMENTE. FAVOR ENTREGAR O CELULAR PARA O MOTORISTA.");
                    alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {


                            ManipDadosEnvio.getInstance().salvaViagemCana();

                            Intent it = new Intent(VerMotoristaActivity.this, MenuMotoMecActivity.class);
                            startActivity(it);
                            finish();

                        }
                    });
                    alerta.show();

                }
                else if(infBoletimTO.getTipoAtiv() == 2){

                    ecmContext.getCompVVinhacaTO().setMoto(infBoletimTO.getCodigoMoto());
                    ecmContext.getCompVVinhacaTO().setData(Tempo.getInstance().datahora());

                    ConfiguracaoTO configuracaoTO = new ConfiguracaoTO();
                    List listConfig = configuracaoTO.all();

                    configuracaoTO = (ConfiguracaoTO) listConfig.get(0);

                    ecmContext.getCompVVinhacaTO().setCam(configuracaoTO.getCodCamConfig());

                    Intent it = new Intent(VerMotoristaActivity.this, MenuMotoMecActivity.class);
                    startActivity(it);
                    finish();

                    ManipDadosEnvio.getInstance().salvaViagemVinhaca(ecmContext.getCompVVinhacaTO());

                }

            }
        });

        buttonAlterarCabecalho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ecmContext.setAltMotoL(2);
                Intent it = new Intent(VerMotoristaActivity.this, MotoristaActivity.class);
                startActivity(it);
                finish();

            }
        });

    }

    public void onBackPressed()  {
    }

}
