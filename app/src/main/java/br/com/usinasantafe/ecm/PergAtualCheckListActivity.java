package br.com.usinasantafe.ecm;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

import br.com.usinasantafe.ecm.bo.ConexaoWeb;
import br.com.usinasantafe.ecm.bo.ManipDadosVerif;
import br.com.usinasantafe.ecm.bo.Tempo;
import br.com.usinasantafe.ecm.to.tb.estaticas.CaminhaoTO;
import br.com.usinasantafe.ecm.to.tb.estaticas.ItemCheckListTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.CabecCheckListTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.ConfiguracaoTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.InfBoletimTO;

public class PergAtualCheckListActivity extends AppCompatActivity {

    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perg_atual_check_list);

        Button buttonSimAtualCL = (Button) findViewById(R.id.buttonSimAtualCL);
        Button buttonNaoAtualCL = (Button) findViewById(R.id.buttonNaoAtualCL);

        buttonNaoAtualCL.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                InfBoletimTO infBoletimTO = new InfBoletimTO();
                List lTurno = infBoletimTO.all();
                infBoletimTO = (InfBoletimTO) lTurno.get(0);

                ConfiguracaoTO configTO = new ConfiguracaoTO();
                List listConfigTO = configTO.all();
                configTO = (ConfiguracaoTO) listConfigTO.get(0);

                CaminhaoTO caminhaoTO = new CaminhaoTO();
                List caminhaoList = caminhaoTO.get("idCaminhao", configTO.getIdCamConfig());
                caminhaoTO = (CaminhaoTO) caminhaoList.get(0);
                caminhaoList.clear();

                ItemCheckListTO itemCheckListTO = new ItemCheckListTO();
                List itemCheckList =  itemCheckListTO.get("idChecklist", caminhaoTO.getIdChecklist());
                Long qtde = (long) itemCheckList.size();
                itemCheckList.clear();

                CabecCheckListTO cabecCheckListTO = new CabecCheckListTO();
                cabecCheckListTO.setDtCabecCheckList(Tempo.getInstance().datahora());
                cabecCheckListTO.setEquipCabecCheckList(configTO.getCodCamConfig());
                cabecCheckListTO.setFuncCabecCheckList(infBoletimTO.getCodigoMoto());
                cabecCheckListTO.setTurnoCabecCheckList(infBoletimTO.getTurno());
                cabecCheckListTO.setQtdeItemCabecCheckList(qtde);
                cabecCheckListTO.setStatusCabecCheckList(1L);
                cabecCheckListTO.insert();

                Intent it = new Intent(  PergAtualCheckListActivity.this, ItemChecklistActivity.class);
                startActivity(it);
                finish();

            }
        });

        buttonSimAtualCL.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                ConexaoWeb conexaoWeb = new ConexaoWeb();

                if (conexaoWeb.verificaConexao(PergAtualCheckListActivity.this)) {

                    progressBar = new ProgressDialog(PergAtualCheckListActivity.this);
                    progressBar.setCancelable(true);
                    progressBar.setMessage("Atualizando CheckList...");
                    progressBar.show();

                    ManipDadosVerif.getInstance().verDados("", "CheckList"
                            , PergAtualCheckListActivity.this, ItemChecklistActivity.class, progressBar);

                } else {

                    AlertDialog.Builder alerta = new AlertDialog.Builder( PergAtualCheckListActivity.this);
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
}
