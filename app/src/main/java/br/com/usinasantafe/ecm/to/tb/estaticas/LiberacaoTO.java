/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.usinasantafe.ecm.to.tb.estaticas;

/**
 *
 * @author anderson
 */

import br.com.usinasantafe.ecm.pst.Entidade;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="tbliberacaoest")
public class LiberacaoTO extends Entidade {
	
	private static final long serialVersionUID = 1L;
	
	@DatabaseField(id=true)
    private Long codigoLiberacao;
	@DatabaseField
	private Long tipoLiberacao;
	@DatabaseField
	private Long codFazendaLiberacao;
	@DatabaseField
    private String nomeFazendaLiberacao;
	@DatabaseField
	private Long nroOSLiberacao;

    public LiberacaoTO(){
    }

	public Long getCodigoLiberacao() {
		return codigoLiberacao;
	}

	public void setCodigoLiberacao(Long codigoliberacao) {
		this.codigoLiberacao = codigoliberacao;
	}

	public Long getTipoLiberacao() {
		return tipoLiberacao;
	}

	public void setTipoLiberacao(Long tipoliberacao) {
		this.tipoLiberacao = tipoliberacao;
	}

	public String getNomeFazendaLiberacao() {
		return nomeFazendaLiberacao;
	}

	public void setNomeFazendaLiberacao(String nomeliberacao) {
		this.nomeFazendaLiberacao = nomeliberacao;
	}

	public Long getNroOSLiberacao() {
		return nroOSLiberacao;
	}

	public void setNroOSLiberacao(Long nroosliberacao) {
		this.nroOSLiberacao = nroosliberacao;
	}

	public Long getCodFazendaLiberacao() {
		return codFazendaLiberacao;
	}

	public void setCodFazendaLiberacao(Long codFazendaLiberacao) {
		this.codFazendaLiberacao = codFazendaLiberacao;
	}
}
