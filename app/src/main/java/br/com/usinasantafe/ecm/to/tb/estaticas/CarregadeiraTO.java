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

@DatabaseTable(tableName="tbcarregadeiraest")
public class CarregadeiraTO extends Entidade {
	
	private static final long serialVersionUID = 1L;
	
	@DatabaseField(id=true)
    private Long idCarregadeira;

    public CarregadeiraTO() {
    }

	public Long getIdCarregadeira() {
		return idCarregadeira;
	}

	public void setIdCarregadeira(Long idcarregadeira) {
		this.idCarregadeira = idcarregadeira;
	}
	
}
