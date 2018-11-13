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

@DatabaseTable(tableName="tbfrenteest")
public class FrenteTO extends Entidade {
	
	private static final long serialVersionUID = 1L;
	
	@DatabaseField(id=true)
    private Long codigoFrente;
	@DatabaseField
    private String nomeFrente;
	@DatabaseField
    private Long tipoFrente;

    public FrenteTO() {
    }

	public Long getCodigoFrente() {
		return codigoFrente;
	}

	public void setCodigoFrente(Long codigofrente) {
		this.codigoFrente = codigofrente;
	}

	public String getNomeFrente() {
		return nomeFrente;
	}

	public void setNomeFrente(String nomefrente) {
		this.nomeFrente = nomefrente;
	}

	public Long getTipoFrente() {
		return tipoFrente;
	}

	public void setTipoFrente(Long tipofrente) {
		this.tipoFrente = tipofrente;
	}
	
}
