package br.com.usinasantafe.ecm.model.bean.variaveis;

import br.com.usinasantafe.ecm.model.bean.pst.Entidade;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="tbconfigvar")
public class ConfigBean extends Entidade {
	
	private static final long serialVersionUID = 1L;
	
	@DatabaseField(id=true)
	private Long idEquipConfig;
	@DatabaseField
	private Long codEquipConfig;
	@DatabaseField
	private String senhaConfig;
	@DatabaseField
	private Long ultTurnoCLConfig;
	@DatabaseField
	private Long matricColabConfig;
	@DatabaseField
	private Long idTurnoConfig;
	
	public ConfigBean() {
	}

	public Long getIdEquipConfig() {
		return idEquipConfig;
	}

	public void setIdEquipConfig(Long idEquipConfig) {
		this.idEquipConfig = idEquipConfig;
	}

	public Long getCodEquipConfig() {
		return codEquipConfig;
	}

	public void setCodEquipConfig(Long camconfig) {
		this.codEquipConfig = camconfig;
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

	public Long getMatricColabConfig() {
		return matricColabConfig;
	}

	public void setMatricColabConfig(Long matricColabConfig) {
		this.matricColabConfig = matricColabConfig;
	}

	public Long getIdTurnoConfig() {
		return idTurnoConfig;
	}

	public void setIdTurnoConfig(Long idTurnoConfig) {
		this.idTurnoConfig = idTurnoConfig;
	}

}