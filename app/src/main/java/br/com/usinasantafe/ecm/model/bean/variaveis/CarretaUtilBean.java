package br.com.usinasantafe.ecm.model.bean.variaveis;

import br.com.usinasantafe.ecm.model.bean.pst.Entidade;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="tbcarrutilvar")
public class CarretaUtilBean extends Entidade {
	
	private static final long serialVersionUID = 1L;

	@DatabaseField(generatedId=true)
	private Long idCarretaUtil;
	@DatabaseField
	private Long posCarreta;
	@DatabaseField
	private Long idEquip;
	@DatabaseField
	private Long tipoCarreta; //1 - Certificado; 2 - Engate e Desengate
	@DatabaseField
	private Long libCarreta;

	public CarretaUtilBean() {
	}

	public Long getIdCarretaUtil() {
		return idCarretaUtil;
	}

	public void setIdCarretaUtil(Long idCarretaUtil) {
		this.idCarretaUtil = idCarretaUtil;
	}

	public Long getPosCarreta() {
		return posCarreta;
	}

	public void setPosCarreta(Long posCarreta) {
		this.posCarreta = posCarreta;
	}

	public Long getIdEquip() {
		return idEquip;
	}

	public void setIdEquip(Long idEquip) {
		this.idEquip = idEquip;
	}

	public Long getTipoCarreta() {
		return tipoCarreta;
	}

	public void setTipoCarreta(Long tipoCarreta) {
		this.tipoCarreta = tipoCarreta;
	}

	public Long getLibCarreta() {
		return libCarreta;
	}

	public void setLibCarreta(Long libCarreta) {
		this.libCarreta = libCarreta;
	}
}
