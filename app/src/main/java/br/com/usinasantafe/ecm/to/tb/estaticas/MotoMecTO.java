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

@DatabaseTable(tableName="tbmotomecest")
public class MotoMecTO extends Entidade {
	
	private static final long serialVersionUID = 1L;
	
	@DatabaseField(id=true)
    private Long codigoMotoMec;
	@DatabaseField
	private Long opcorMotoMec;
	@DatabaseField
	private String nomeMotoMec;
	@DatabaseField
	private Long posicaoMotoMec;
	@DatabaseField
	private Long tipoMotoMec;
	@DatabaseField
	private Long funcaoMotoMec;
	@DatabaseField
	private Long cargoMotoMec;

    public MotoMecTO() {
    }

	public Long getCodigoMotoMec() {
		return codigoMotoMec;
	}

	public void setCodigoMotoMec(Long codigomotomec) {
		this.codigoMotoMec = codigomotomec;
	}

	public Long getOpcorMotoMec() {
		return opcorMotoMec;
	}

	public void setOpcorMotoMec(Long opcormotomec) {
		this.opcorMotoMec = opcormotomec;
	}

	public String getNomeMotoMec() {
		return nomeMotoMec;
	}

	public void setNomeMotoMec(String nomemotomec) {
		this.nomeMotoMec = nomemotomec;
	}

	public Long getPosicaoMotoMec() {
		return posicaoMotoMec;
	}

	public void setPosicaoMotoMec(Long posicaomotomec) {
		this.posicaoMotoMec = posicaomotomec;
	}

	public Long getTipoMotoMec() {
		return tipoMotoMec;
	}

	public void setTipoMotoMec(Long tipomotomec) {
		this.tipoMotoMec = tipomotomec;
	}

	public Long getFuncaoMotoMec() {
		return funcaoMotoMec;
	}

	public void setFuncaoMotoMec(Long funcamotomec) {
		this.funcaoMotoMec = funcamotomec;
	}

	public Long getCargoMotoMec() {
		return cargoMotoMec;
	}

	public void setCargoMotoMec(Long cargomotomec) {
		this.cargoMotoMec = cargomotomec;
	}

}
