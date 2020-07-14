package br.com.usinasantafe.ecm.model.pst;
 
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import br.com.usinasantafe.ecm.model.bean.estaticas.AtividadeBean;
import br.com.usinasantafe.ecm.model.bean.estaticas.FuncBean;
import br.com.usinasantafe.ecm.model.bean.estaticas.EquipBean;
import br.com.usinasantafe.ecm.model.bean.estaticas.EquipSegBean;
import br.com.usinasantafe.ecm.model.bean.estaticas.FrenteBean;
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
import br.com.usinasantafe.ecm.model.bean.variaveis.ConfigBean;
import br.com.usinasantafe.ecm.model.bean.variaveis.RespItemCLBean;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

	public static final String FORCA_DB_NAME = "ecm_db";
	public static final int FORCA_BD_VERSION = 2;

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

			TableUtils.createTable(cs, AtividadeBean.class);
			TableUtils.createTable(cs, EquipBean.class);
			TableUtils.createTable(cs, EquipSegBean.class);
			TableUtils.createTable(cs, FrenteBean.class);
			TableUtils.createTable(cs, FuncBean.class);
			TableUtils.createTable(cs, ItemCheckListBean.class);
			TableUtils.createTable(cs, MotoMecBean.class);
			TableUtils.createTable(cs, OSBean.class);
			TableUtils.createTable(cs, PneuBean.class);
			TableUtils.createTable(cs, REquipAtivBean.class);
			TableUtils.createTable(cs, REquipPneuBean.class);
			TableUtils.createTable(cs, TurnoBean.class);

			TableUtils.createTable(cs, ApontImpleMMBean.class);
			TableUtils.createTable(cs, ApontMMBean.class);
			TableUtils.createTable(cs, BoletimMMBean.class);
			TableUtils.createTable(cs, CabecCLBean.class);
			TableUtils.createTable(cs, CabecPneuBean.class);
			TableUtils.createTable(cs, CarretaBean.class);
			TableUtils.createTable(cs, CECBean.class);
			TableUtils.createTable(cs, ConfigBean.class);
			TableUtils.createTable(cs, InfColheitaBean.class);
			TableUtils.createTable(cs, InfPlantioBean.class);
			TableUtils.createTable(cs, ItemPneuBean.class);
			TableUtils.createTable(cs, PreCECBean.class);
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

				TableUtils.dropTable(cs, AtividadeBean.class, true);
				TableUtils.dropTable(cs, EquipBean.class, true);
				TableUtils.dropTable(cs, EquipSegBean.class, true);
				TableUtils.dropTable(cs, FrenteBean.class, true);
				TableUtils.dropTable(cs, FuncBean.class, true);
				TableUtils.dropTable(cs, ItemCheckListBean.class, true);
				TableUtils.dropTable(cs, MotoMecBean.class, true);
				TableUtils.dropTable(cs, OSBean.class, true);
				TableUtils.dropTable(cs, PneuBean.class, true);
				TableUtils.dropTable(cs, REquipAtivBean.class, true);
				TableUtils.dropTable(cs, REquipPneuBean.class, true);
				TableUtils.dropTable(cs, TurnoBean.class, true);

				TableUtils.dropTable(cs, ApontImpleMMBean.class, true);
				TableUtils.dropTable(cs, ApontMMBean.class, true);
				TableUtils.dropTable(cs, BoletimMMBean.class, true);
				TableUtils.dropTable(cs, CabecCLBean.class, true);
				TableUtils.dropTable(cs, CabecPneuBean.class, true);
				TableUtils.dropTable(cs, CarretaBean.class, true);
				TableUtils.dropTable(cs, CECBean.class, true);
				TableUtils.dropTable(cs, ConfigBean.class, true);
				TableUtils.dropTable(cs, InfColheitaBean.class, true);
				TableUtils.dropTable(cs, InfPlantioBean.class, true);
				TableUtils.dropTable(cs, ItemPneuBean.class, true);
				TableUtils.dropTable(cs, PreCECBean.class, true);
				TableUtils.dropTable(cs, RespItemCLBean.class, true);

				TableUtils.createTable(cs, AtividadeBean.class);
				TableUtils.createTable(cs, EquipBean.class);
				TableUtils.createTable(cs, EquipSegBean.class);
				TableUtils.createTable(cs, FrenteBean.class);
				TableUtils.createTable(cs, FuncBean.class);
				TableUtils.createTable(cs, ItemCheckListBean.class);
				TableUtils.createTable(cs, MotoMecBean.class);
				TableUtils.createTable(cs, OSBean.class);
				TableUtils.createTable(cs, PneuBean.class);
				TableUtils.createTable(cs, REquipAtivBean.class);
				TableUtils.createTable(cs, REquipPneuBean.class);
				TableUtils.createTable(cs, TurnoBean.class);

				TableUtils.createTable(cs, ApontImpleMMBean.class);
				TableUtils.createTable(cs, ApontMMBean.class);
				TableUtils.createTable(cs, BoletimMMBean.class);
				TableUtils.createTable(cs, CabecCLBean.class);
				TableUtils.createTable(cs, CabecPneuBean.class);
				TableUtils.createTable(cs, CarretaBean.class);
				TableUtils.createTable(cs, CECBean.class);
				TableUtils.createTable(cs, ConfigBean.class);
				TableUtils.createTable(cs, InfColheitaBean.class);
				TableUtils.createTable(cs, InfPlantioBean.class);
				TableUtils.createTable(cs, ItemPneuBean.class);
				TableUtils.createTable(cs, PreCECBean.class);
				TableUtils.createTable(cs, RespItemCLBean.class);

			}
			
			
		} catch (Exception e) {
			Log.e(DatabaseHelper.class.getName(), "Erro atualizando banco de dados...", e);
		}
		
	}

}
