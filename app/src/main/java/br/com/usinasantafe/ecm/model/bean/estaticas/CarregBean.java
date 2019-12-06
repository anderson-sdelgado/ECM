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

@DatabaseTable(tableName="tbcarregadeiraest")
public class CarregBean extends Entidade {
	
	private static final long serialVersionUID = 1L;
	
	@DatabaseField(id=true)
    private Long idCarregadeira;

    public CarregBean() {
    }

	public Long getIdCarregadeira() {
		return idCarregadeira;
	}

	public void setIdCarregadeira(Long idcarregadeira) {
		this.idCarregadeira = idcarregadeira;
	}
	
}
