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

@DatabaseTable(tableName="tbcarretaest")
public class CarretaTO extends Entidade {

	private static final long serialVersionUID = 1L;
	
	@DatabaseField(id=true)
    private Long idCarreta;
	@DatabaseField
    private Long tipoCarreta;

    public CarretaTO() {
    }

	public Long getIdCarreta() {
		return idCarreta;
	}

	public void setIdCarreta(Long idcarreta) {
		this.idCarreta = idcarreta;
	}

	public Long getTipoCarreta() {
		return tipoCarreta;
	}

	public void setTipoCarreta(Long tipocarreta) {
		this.tipoCarreta = tipocarreta;
	}
	
}
