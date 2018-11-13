package br.com.usinasantafe.ecm.to.tb.estaticas;

import br.com.usinasantafe.ecm.pst.Entidade;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="tblocalest")
public class LocalTO extends Entidade {
	
	private static final long serialVersionUID = 1L;
	
	@DatabaseField(id=true)
	private Long idLocal;
	@DatabaseField
	private String descLocal;

	public LocalTO() {
		// TODO Auto-generated constructor stub
	}

	public Long getIdLocal() {
		return idLocal;
	}

	public void setIdLocal(Long idLocal) {
		this.idLocal = idLocal;
	}

	public String getDescLocal() {
		return descLocal;
	}

	public void setDescLocal(String descLocal) {
		this.descLocal = descLocal;
	}
	
}
