/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.usinasantafe.ecm.model.bean.estaticas;

/**
 *
 * @author anderson
 */

import br.com.usinasantafe.ecm.model.bean.pst.Entidade;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="tbcarretaest")
public class CarretaBean extends Entidade {

	private static final long serialVersionUID = 1L;
	
	@DatabaseField(id=true)
    private Long idCarreta;
	@DatabaseField
	private Long codCarreta;
	@DatabaseField
    private Long tipoCarreta;

    public CarretaBean() {
    }

	public Long getIdCarreta() {
		return idCarreta;
	}

	public void setIdCarreta(Long idCarreta) {
		this.idCarreta = idCarreta;
	}

	public Long getCodCarreta() {
		return codCarreta;
	}

	public void setCodCarreta(Long codCarreta) {
		this.codCarreta = codCarreta;
	}

	public Long getTipoCarreta() {
		return tipoCarreta;
	}

	public void setTipoCarreta(Long tipoCarreta) {
		this.tipoCarreta = tipoCarreta;
	}
}
