package br.com.usinasantafe.ecm.model.bean.variaveis;

import br.com.usinasantafe.ecm.model.pst.Entidade;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="tbcarrutilvar")
public class CarretaBean extends Entidade {
	
	private static final long serialVersionUID = 1L;

	@DatabaseField(generatedId=true)
	private Long idCarretaUtil;
	@DatabaseField
	private Long posCarreta;
	@DatabaseField
	private Long nroEquip;

	public CarretaBean() {
	}

	public Long getIdCarretaUtil() {
		return idCarretaUtil;
	}

	public void setIdCarretaUtil(Long idCarretaUtil) {
		this.idCarretaUtil = idCarretaUtil;
	}

	public Long getPosCarreta() {
		return posCarreta;
	}

	public void setPosCarreta(Long posCarreta) {
		this.posCarreta = posCarreta;
	}

	public Long getNroEquip() {
		return nroEquip;
	}

	public void setNroEquip(Long nroEquip) {
		this.nroEquip = nroEquip;
	}

}
