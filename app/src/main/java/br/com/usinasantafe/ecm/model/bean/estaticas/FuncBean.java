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

@DatabaseTable(tableName="tbmotoristaest")
public class FuncBean extends Entidade {

	private static final long serialVersionUID = 1L;
	
	@DatabaseField(id=true)
    private Long matricFunc;
	@DatabaseField
    private String nomeFunc;

    public FuncBean() {
    }

	public Long getMatricFunc() {
		return matricFunc;
	}

	public void setMatricFunc(Long codmotorista) {
		this.matricFunc = codmotorista;
	}

	public String getNomeFunc() {
		return nomeFunc;
	}

	public void setNomeFunc(String nomemotorista) {
		this.nomeFunc = nomemotorista;
	}

}
