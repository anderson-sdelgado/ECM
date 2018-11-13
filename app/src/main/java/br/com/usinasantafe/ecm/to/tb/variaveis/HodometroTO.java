package br.com.usinasantafe.ecm.to.tb.variaveis;

import br.com.usinasantafe.ecm.pst.Entidade;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="tbhodometrovar")
public class HodometroTO extends Entidade {

	private static final long serialVersionUID = 1L;
		
	@DatabaseField(id=true)
    private Long hodometro;
	
    public HodometroTO() {
    }

	public Long getHodometro() {
		return hodometro;
	}

	public void setHodometro(Long hodometro) {
		this.hodometro = hodometro;
	}
    
}
