package br.com.usinasantafe.ecm.model.bean.variaveis;

import br.com.usinasantafe.ecm.model.pst.Entidade;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="tbcarrutilvar")
public class CarretaUtilBean extends Entidade {
	
	private static final long serialVersionUID = 1L;

	@DatabaseField(generatedId=true)
	private Long idCarretaUtil;
	@DatabaseField
	private Long idCertif;
	@DatabaseField
	private Long posCarreta;
	@DatabaseField
	private Long nroEquip;
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

	public Long getNroEquip() {
		return nroEquip;
	}

	public void setNroEquip(Long nroEquip) {
		this.nroEquip = nroEquip;
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

	public Long getIdCertif() {
		return idCertif;
	}

	public void setIdCertif(Long idCertif) {
		this.idCertif = idCertif;
	}
}
