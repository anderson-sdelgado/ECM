package br.com.usinasantafe.ecm.model.bean.estaticas;

import br.com.usinasantafe.ecm.model.bean.pst.Entidade;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="tbrativosest")
public class RAtivOSBean extends Entidade {

	private static final long serialVersionUID = 1L;
	
	@DatabaseField(id=true)
    private Long idRAtivOS;
	@DatabaseField
    private Long nroOS;
	@DatabaseField
	private Long codFazenda;
	@DatabaseField
    private String descrFazenda;
    
    public RAtivOSBean() {
	}

	public Long getIdRAtivOS() {
		return idRAtivOS;
	}

	public void setIdRAtivOS(Long codigoativos) {
		this.idRAtivOS = codigoativos;
	}
	
	public Long getNroOS() {
		return nroOS;
	}

	public void setNroOS(Long nroosativos) {
		this.nroOS = nroosativos;
	}

	public String getDescrFazenda() {
		return descrFazenda;
	}

	public void setDescrFazenda(String nomeativos) {
		this.descrFazenda = nomeativos;
	}

	public Long getCodFazenda() {
		return codFazenda;
	}

	public void setCodFazenda(Long codFazenda) {
		this.codFazenda = codFazenda;
	}
}
