package br.com.usinasantafe.ecm.model.bean.variaveis;

import br.com.usinasantafe.ecm.model.bean.pst.Entidade;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="tbcarrengdesengvar")
public class CarretaEngDesengBean extends Entidade {
	
	private static final long serialVersionUID = 1L;
	
	@DatabaseField(id=true)	
	private Long posCarreta;
	@DatabaseField
	private Long numCarreta;
	
	public CarretaEngDesengBean() {
	}

	public Long getPosCarreta() {
		return posCarreta;
	}

	public void setPosCarreta(Long posCarreta) {
		this.posCarreta = posCarreta;
	}

	public Long getNumCarreta() {
		return numCarreta;
	}

	public void setNumCarreta(Long numCarreta) {
		this.numCarreta = numCarreta;
	}

}
