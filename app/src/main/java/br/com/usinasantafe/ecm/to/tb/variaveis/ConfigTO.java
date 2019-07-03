package br.com.usinasantafe.ecm.to.tb.variaveis;

import br.com.usinasantafe.ecm.pst.Entidade;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="tbconfigvar")
public class ConfigTO extends Entidade {
	
	private static final long serialVersionUID = 1L;
	
	@DatabaseField(id=true)
	private Long idCamConfig;
	@DatabaseField
	private Long codCamConfig;
	@DatabaseField
	private String senhaConfig;
	@DatabaseField
	private Long ultTurnoCLConfig;
	@DatabaseField
	private Long crachaMotoConfig;
	@DatabaseField
	private Long nroTurnoConfig;
	
	public ConfigTO() {
	}

	public Long getIdCamConfig() {
		return idCamConfig;
	}

	public void setIdCamConfig(Long idCamConfig) {
		this.idCamConfig = idCamConfig;
	}

	public Long getCodCamConfig() {
		return codCamConfig;
	}

	public void setCodCamConfig(Long camconfig) {
		this.codCamConfig = camconfig;
	}

	public String getSenhaConfig() {
		return senhaConfig;
	}

	public void setSenhaConfig(String senhaconfig) {
		this.senhaConfig = senhaconfig;
	}

	public Long getUltTurnoCLConfig() {
		return ultTurnoCLConfig;
	}

	public void setUltTurnoCLConfig(Long ultTurnoCLConfig) {
		this.ultTurnoCLConfig = ultTurnoCLConfig;
	}

	public Long getCrachaMotoConfig() {
		return crachaMotoConfig;
	}

	public void setCrachaMotoConfig(Long crachaMotoConfig) {
		this.crachaMotoConfig = crachaMotoConfig;
	}

	public Long getNroTurnoConfig() {
		return nroTurnoConfig;
	}

	public void setNroTurnoConfig(Long nroTurnoConfig) {
		this.nroTurnoConfig = nroTurnoConfig;
	}

}