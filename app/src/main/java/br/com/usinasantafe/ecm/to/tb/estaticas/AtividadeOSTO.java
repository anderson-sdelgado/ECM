package br.com.usinasantafe.ecm.to.tb.estaticas;

import br.com.usinasantafe.ecm.pst.Entidade;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="tbatividadeosest")
public class AtividadeOSTO extends Entidade {

	private static final long serialVersionUID = 1L;
	
	@DatabaseField(id=true)
    private Long codigoAtivOS;
	@DatabaseField
    private Long nroOSAtivOS;
	@DatabaseField
    private String nomeFazendaAtivOS;
	@DatabaseField
	private Long codFazendaAtivOS;
    
    public AtividadeOSTO() {
	}

	public Long getCodigoAtivOS() {
		return codigoAtivOS;
	}

	public void setCodigoAtivOS(Long codigoativos) {
		this.codigoAtivOS = codigoativos;
	}
	
	public Long getNroOSAtivOS() {
		return nroOSAtivOS;
	}

	public void setNroOSAtivOS(Long nroosativos) {
		this.nroOSAtivOS = nroosativos;
	}

	public String getNomeFazendaAtivOS() {
		return nomeFazendaAtivOS;
	}

	public void setNomeFazendaAtivOS(String nomeativos) {
		this.nomeFazendaAtivOS = nomeativos;
	}

	public Long getCodFazendaAtivOS() {
		return codFazendaAtivOS;
	}

	public void setCodFazendaAtivOS(Long codFazendaAtivOS) {
		this.codFazendaAtivOS = codFazendaAtivOS;
	}
}
