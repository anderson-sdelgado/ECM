package br.com.usinasantafe.ecm.model.bean.variaveis;

import br.com.usinasantafe.ecm.model.pst.Entidade;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="tbhodometrovar")
public class HodometroBean extends Entidade {

	private static final long serialVersionUID = 1L;
		
	@DatabaseField(id=true)
    private Long hodometro;
	
    public HodometroBean() {
    }

	public Long getHodometro() {
		return hodometro;
	}

	public void setHodometro(Long hodometro) {
		this.hodometro = hodometro;
	}
    
}
