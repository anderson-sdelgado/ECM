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

@DatabaseTable(tableName="tbmotomecest")
public class MotoMecBean extends Entidade {
	
	private static final long serialVersionUID = 1L;
	
	@DatabaseField(id=true)
    private Long idOperMotoMec;
	@DatabaseField
	private Long codOperMotoMec;
	@DatabaseField
	private String descrOperMotoMec;
	@DatabaseField
	private Long codFuncaoOperMotoMec;
	@DatabaseField
	private Long posOperMotoMec;
	@DatabaseField
	private Long tipoOperMotoMec;
	@DatabaseField
	private Long aplicOperMotoMec;

    public MotoMecBean() {
    }

	public Long getIdOperMotoMec() {
		return idOperMotoMec;
	}

	public void setIdOperMotoMec(Long idOperMotoMec) {
		this.idOperMotoMec = idOperMotoMec;
	}

	public Long getCodOperMotoMec() {
		return codOperMotoMec;
	}

	public void setCodOperMotoMec(Long codOperMotoMec) {
		this.codOperMotoMec = codOperMotoMec;
	}

	public String getDescrOperMotoMec() {
		return descrOperMotoMec;
	}

	public void setDescrOperMotoMec(String descrOperMotoMec) {
		this.descrOperMotoMec = descrOperMotoMec;
	}

	public Long getCodFuncaoOperMotoMec() {
		return codFuncaoOperMotoMec;
	}

	public void setCodFuncaoOperMotoMec(Long codFuncaoOperMotoMec) {
		this.codFuncaoOperMotoMec = codFuncaoOperMotoMec;
	}

	public Long getPosOperMotoMec() {
		return posOperMotoMec;
	}

	public void setPosOperMotoMec(Long posOperMotoMec) {
		this.posOperMotoMec = posOperMotoMec;
	}

	public Long getTipoOperMotoMec() {
		return tipoOperMotoMec;
	}

	public void setTipoOperMotoMec(Long tipoOperMotoMec) {
		this.tipoOperMotoMec = tipoOperMotoMec;
	}

	public Long getAplicOperMotoMec() {
		return aplicOperMotoMec;
	}

	public void setAplicOperMotoMec(Long aplicOperMotoMec) {
		this.aplicOperMotoMec = aplicOperMotoMec;
	}
}
