/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.usinasantafe.ecm.model.bean.estaticas;

/**
 *
 * @author anderson
 */

import br.com.usinasantafe.ecm.model.pst.Entidade;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="tbrlibosest")
public class RLibOSBean extends Entidade {
	
	private static final long serialVersionUID = 1L;
	
	@DatabaseField(id=true)
    private Long codLib;
	@DatabaseField
	private Long tipoLib;
	@DatabaseField
	private Long codFazenda;
	@DatabaseField
    private String descrFazenda;
	@DatabaseField
	private Long nroOS;

    public RLibOSBean(){
    }

	public Long getCodLib() {
		return codLib;
	}

	public void setCodLib(Long codigoliberacao) {
		this.codLib = codigoliberacao;
	}

	public Long getTipoLib() {
		return tipoLib;
	}

	public void setTipoLib(Long tipoliberacao) {
		this.tipoLib = tipoliberacao;
	}

	public String getDescrFazenda() {
		return descrFazenda;
	}

	public void setDescrFazenda(String nomeliberacao) {
		this.descrFazenda = nomeliberacao;
	}

	public Long getNroOS() {
		return nroOS;
	}

	public void setNroOS(Long nroosliberacao) {
		this.nroOS = nroosliberacao;
	}

	public Long getCodFazenda() {
		return codFazenda;
	}

	public void setCodFazenda(Long codFazenda) {
		this.codFazenda = codFazenda;
	}
}
