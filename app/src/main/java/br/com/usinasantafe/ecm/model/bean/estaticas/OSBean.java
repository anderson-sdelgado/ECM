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

@DatabaseTable(tableName="tbosest")
public class OSBean extends Entidade {

	private static final long serialVersionUID = 1L;
	
	@DatabaseField(id=true)
    private Long idOS;
	@DatabaseField
    private Long idAtivOS;

    public OSBean() {
    }

	public Long getIdOS() {
		return idOS;
	}

	public void setIdOS(Long idos) {
		this.idOS = idos;
	}

	public Long getIdAtivOS() {
		return idAtivOS;
	}

	public void setIdAtivOS(Long idativos) {
		this.idAtivOS = idativos;
	}
	
}
