package br.com.usinasantafe.ecm.to.tb.variaveis;

import br.com.usinasantafe.ecm.pst.Entidade;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="tbconfigvar")
public class ConfiguracaoTO extends Entidade {
	
	private static final long serialVersionUID = 1L;
	
	@DatabaseField(id=true)	
	private Long camConfig;
	@DatabaseField
	private String senhaConfig;
	@DatabaseField
	private String dtUltimoCheckListConfig;
	
	public ConfiguracaoTO() {
		// TODO Auto-generated constructor stub
	}

	public Long getCamConfig() {
		return camConfig;
	}

	public void setCamConfig(Long camconfig) {
		this.camConfig = camconfig;
	}

	public String getSenhaConfig() {
		return senhaConfig;
	}

	public void setSenhaConfig(String senhaconfig) {
		this.senhaConfig = senhaconfig;
	}

	public String getDtUltimoCheckListConfig() {
		return dtUltimoCheckListConfig;
	}

	public void setDtUltimoCheckListConfig(String dtUltimoCheckListConfig) {
		this.dtUltimoCheckListConfig = dtUltimoCheckListConfig;
	}

}