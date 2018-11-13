package br.com.usinasantafe.ecm.pst;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import android.util.Log;
import br.com.usinasantafe.ecm.to.tb.estaticas.MotoMecTO;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
import com.j256.ormlite.stmt.query.OrderBy;

public class MotoMecPST {

	private static final long serialVersionUID = 1L;
	private Dao dao;
	

	public MotoMecPST() {
		// TODO Auto-generated constructor stub
	}
	

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List get(ArrayList lista) {
		
		try {
	
			DatabaseHelper instance = DatabaseHelper.getInstance();
			dao = instance.getDao(MotoMecTO.class);
			
			QueryBuilder<String, Object> queryBuilder =
					this.dao.queryBuilder();
			Where<String, Object> where = queryBuilder.where();
			
			EspecificaPesquisa pesquisa;
			
			pesquisa = (EspecificaPesquisa) lista.get(1);
			where.eq(pesquisa.getCampo(), pesquisa.getValor());
			where.eq("cargoMotoMec", (long) 0);
			where.or();
			
			pesquisa = (EspecificaPesquisa) lista.get(0);
			where.eq(pesquisa.getCampo(), pesquisa.getValor());
			where.and(2);
			
			queryBuilder.orderBy("posicaoMotoMec", true);
			
			PreparedQuery preparedQuery = queryBuilder.prepare();
			
			return this.dao.query(preparedQuery);
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
