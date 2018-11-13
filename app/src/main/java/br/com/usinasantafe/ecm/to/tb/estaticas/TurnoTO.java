package br.com.usinasantafe.ecm.to.tb.estaticas;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.ecm.pst.Entidade;

@DatabaseTable(tableName="tbturnoest")
public class TurnoTO extends Entidade {
	
	private static final long serialVersionUID = 1L;
	
	@DatabaseField(id=true)
	private Long idTurno;
	@DatabaseField
	private String descTurno;
	
	public TurnoTO() {
		// TODO Auto-generated constructor stub
	}
	
	public Long getIdTurno() {
		return idTurno;
	}
	
	public void setIdTurno(Long idTurno) {
		this.idTurno = idTurno;
	}
	
	public String getDescTurno() {
		return descTurno;
	}
	
	public void setDescTurno(String descTurno) {
		this.descTurno = descTurno;
	}
	
}
