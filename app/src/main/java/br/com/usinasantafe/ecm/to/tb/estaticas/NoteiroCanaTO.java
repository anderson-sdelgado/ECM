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

@DatabaseTable(tableName="tbnoteirocanaest")
public class NoteiroCanaTO extends Entidade {

	private static final long serialVersionUID = 1L;
	
	@DatabaseField(id=true)
	private Long codNoteiroCana;

	public NoteiroCanaTO() {
    }
	
    public Long getCodNoteiroCana() {
		return codNoteiroCana;
	}

	public void setCodNoteiroCana(Long codnoteirocana) {
		this.codNoteiroCana = codnoteirocana;
	}
	
}
