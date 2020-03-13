package br.com.usinasantafe.ecm.model.pst;
 
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import br.com.usinasantafe.ecm.model.bean.estaticas.ColabBean;
import br.com.usinasantafe.ecm.model.bean.estaticas.EquipBean;
import br.com.usinasantafe.ecm.model.bean.estaticas.FrenteBean;
import br.com.usinasantafe.ecm.model.bean.estaticas.ItemCLBean;
import br.com.usinasantafe.ecm.model.bean.estaticas.MotoMecBean;
import br.com.usinasantafe.ecm.model.bean.estaticas.RLibOSBean;
import br.com.usinasantafe.ecm.model.bean.estaticas.TurnoBean;

import br.com.usinasantafe.ecm.model.bean.variaveis.ApontMotoMecBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.BoletimBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.BoletimBkpBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.CabecCLBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.CarretaUtilBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.PreCECBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.CertifCanaBkpBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.ConfigBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.RespItemCLBean;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

	public static final String FORCA_DB_NAME = "ecm_db";
	public static final int FORCA_BD_VERSION = 1;

	private static DatabaseHelper instance;
	
	public static DatabaseHelper getInstance(){
		return instance;
	}
	
	public DatabaseHelper(Context context) {
		
		super(context, FORCA_DB_NAME,
				null, FORCA_BD_VERSION);

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

			TableUtils.createTable(cs, ColabBean.class);
			TableUtils.createTable(cs, EquipBean.class);
			TableUtils.createTable(cs, FrenteBean.class);
			TableUtils.createTable(cs, ItemCLBean.class);
			TableUtils.createTable(cs, MotoMecBean.class);
			TableUtils.createTable(cs, RLibOSBean.class);
			TableUtils.createTable(cs, TurnoBean.class);

			TableUtils.createTable(cs, ApontMotoMecBean.class);
			TableUtils.createTable(cs, BoletimBean.class);
			TableUtils.createTable(cs, BoletimBkpBean.class);
			TableUtils.createTable(cs, CabecCLBean.class);
			TableUtils.createTable(cs, CarretaUtilBean.class);
			TableUtils.createTable(cs, PreCECBean.class);
			TableUtils.createTable(cs, CertifCanaBkpBean.class);
			TableUtils.createTable(cs, ConfigBean.class);
			TableUtils.createTable(cs, RespItemCLBean.class);

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
			}
			
			
		} catch (Exception e) {
			Log.e(DatabaseHelper.class.getName(), "Erro atualizando banco de dados...", e);
		}
		
	}

}
