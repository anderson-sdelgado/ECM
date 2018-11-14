package br.com.usinasantafe.ecm.pst;
 
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import br.com.usinasantafe.ecm.to.tb.estaticas.AtividadeOSTO;
import br.com.usinasantafe.ecm.to.tb.estaticas.CaminhaoTO;
import br.com.usinasantafe.ecm.to.tb.estaticas.CarregadeiraTO;
import br.com.usinasantafe.ecm.to.tb.estaticas.CarretaTO;
import br.com.usinasantafe.ecm.to.tb.estaticas.DataTO;
import br.com.usinasantafe.ecm.to.tb.estaticas.FrenteTO;
import br.com.usinasantafe.ecm.to.tb.estaticas.ItemChecklistTO;
import br.com.usinasantafe.ecm.to.tb.estaticas.LiberacaoTO;
import br.com.usinasantafe.ecm.to.tb.estaticas.LocalTO;
import br.com.usinasantafe.ecm.to.tb.estaticas.MotoMecTO;
import br.com.usinasantafe.ecm.to.tb.estaticas.MotoristaTO;
import br.com.usinasantafe.ecm.to.tb.estaticas.OSTO;
import br.com.usinasantafe.ecm.to.tb.estaticas.TurnoTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.ApontMotoMecTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.AtividadeOsTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.BoletimBkpTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.BoletimTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.CabecCheckListTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.CarretaEngDesengTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.CompVCanaBkpTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.CompVCanaTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.CompVVinhacaTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.ConfiguracaoTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.HodometroTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.InfBoletimTO;
import br.com.usinasantafe.ecm.to.tb.variaveis.RespItemCheckListTO;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
	
	private static DatabaseHelper instance;
	
	public static DatabaseHelper getInstance(){
		return instance;
	}
	
	public DatabaseHelper(Context context) {
		
		super(context, Database.FORCA_DB_NAME,
				null, Database.FORCA_BD_VERSION);
		
		// TODO Auto-generated constructor stub
		instance = this;
		
	}

	@Override
	public void close() {

		super.close();
		
		instance = null;
		
	}
	
	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource cs) {

		
		try{
			
			TableUtils.createTable(cs, AtividadeOSTO.class);
			TableUtils.createTable(cs, CaminhaoTO.class);
			TableUtils.createTable(cs, CarregadeiraTO.class);
			TableUtils.createTable(cs, CarretaTO.class);
			TableUtils.createTable(cs, FrenteTO.class);
			TableUtils.createTable(cs, LiberacaoTO.class);
			TableUtils.createTable(cs, MotoMecTO.class);
			TableUtils.createTable(cs, MotoristaTO.class);
			TableUtils.createTable(cs, OSTO.class);
			TableUtils.createTable(cs, TurnoTO.class);
			TableUtils.createTable(cs, LocalTO.class);
			TableUtils.createTable(cs, ItemChecklistTO.class);
			TableUtils.createTable(cs, CabecCheckListTO.class);
			TableUtils.createTable(cs, RespItemCheckListTO.class);
			TableUtils.createTable(cs, DataTO.class);

			TableUtils.createTable(cs, ConfiguracaoTO.class);
			TableUtils.createTable(cs, CarretaEngDesengTO.class);
			TableUtils.createTable(cs, AtividadeOsTO.class);
			TableUtils.createTable(cs, CompVCanaTO.class);
			TableUtils.createTable(cs, ApontMotoMecTO.class);
			TableUtils.createTable(cs, CompVCanaBkpTO.class);
			TableUtils.createTable(cs, BoletimTO.class);
			TableUtils.createTable(cs, BoletimBkpTO.class);
			TableUtils.createTable(cs, CompVVinhacaTO.class);
			TableUtils.createTable(cs, HodometroTO.class);
			TableUtils.createTable(cs, InfBoletimTO.class);
			
		}
		catch(Exception e){
			Log.e(DatabaseHelper.class.getName(),
					"Erro criando banco de dados...",
					e);
		}
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db,
			ConnectionSource cs,
			int oldVersion,
			int newVersion) {
		
		try {
			
			if(oldVersion == 1 && newVersion == 2){
				TableUtils.createTable(cs, ConfiguracaoTO.class);
				oldVersion = 2;
			}
			
			
		} catch (Exception e) {
			Log.e(DatabaseHelper.class.getName(), "Erro atualizando banco de dados...", e);
		}
		
	}

}
