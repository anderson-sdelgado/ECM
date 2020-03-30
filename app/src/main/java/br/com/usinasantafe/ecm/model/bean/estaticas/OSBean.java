/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.usinasantafe.ecm.model.bean.estaticas;

/**
 *
 * @author anderson
 */

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.ecm.model.pst.Entidade;

@DatabaseTable(tableName="tbosest")
public class OSBean extends Entidade {

	private static final long serialVersionUID = 1L;
	
	@DatabaseField(id=true)
    private Long idAtivOS;
	@DatabaseField
	private Long nroOS;
	@DatabaseField
	private Long idLibOS;
	@DatabaseField
	private Long idProprAgr;
	@DatabaseField
	private String descrProprAgr;
	@DatabaseField
	private Long idAtiv;

    public OSBean() {
    }

	public Long getIdAtivOS() {
		return idAtivOS;
	}

	public void setIdAtivOS(Long idAtivOS) {
		this.idAtivOS = idAtivOS;
	}

	public Long getNroOS() {
		return nroOS;
	}

	public void setNroOS(Long nroOS) {
		this.nroOS = nroOS;
	}

	public Long getIdLibOS() {
		return idLibOS;
	}

	public void setIdLibOS(Long idLibOS) {
		this.idLibOS = idLibOS;
	}

	public Long getIdProprAgr() {
		return idProprAgr;
	}

	public void setIdProprAgr(Long idProprAgr) {
		this.idProprAgr = idProprAgr;
	}

	public String getDescrProprAgr() {
		return descrProprAgr;
	}

	public void setDescrProprAgr(String descrProprAgr) {
		this.descrProprAgr = descrProprAgr;
	}

	public Long getIdAtiv() {
		return idAtiv;
	}

	public void setIdAtiv(Long idAtiv) {
		this.idAtiv = idAtiv;
	}
}
