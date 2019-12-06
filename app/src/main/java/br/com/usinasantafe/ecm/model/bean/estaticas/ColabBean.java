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

@DatabaseTable(tableName="tbmotoristaest")
public class ColabBean extends Entidade {

	private static final long serialVersionUID = 1L;
	
	@DatabaseField(id=true)
    private Long matricColab;
	@DatabaseField
    private String nomeColab;

    public ColabBean() {
    }

	public Long getMatricColab() {
		return matricColab;
	}

	public void setMatricColab(Long codmotorista) {
		this.matricColab = codmotorista;
	}

	public String getNomeColab() {
		return nomeColab;
	}

	public void setNomeColab(String nomemotorista) {
		this.nomeColab = nomemotorista;
	}

}
