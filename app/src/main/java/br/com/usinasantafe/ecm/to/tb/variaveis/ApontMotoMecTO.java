package br.com.usinasantafe.ecm.to.tb.variaveis;

import br.com.usinasantafe.ecm.pst.Entidade;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="tbmotomecvar")
public class ApontMotoMecTO extends Entidade  {

	private static final long serialVersionUID = 1L;

	@DatabaseField(generatedId=true)
	private Long idApontMM;
	@DatabaseField
	private Long veic;
	@DatabaseField
	private Long motorista;
	@DatabaseField
	private Long opcor;
	@DatabaseField
	private String dihi;
	@DatabaseField
	private Long caux;
	@DatabaseField
	private Long tipoEngDeseng;
	
	public ApontMotoMecTO() {
	}

	public Long getIdApontMM() {
		return idApontMM;
	}

	public void setIdApontMM(Long idApontMM) {
		this.idApontMM = idApontMM;
	}

	public Long getVeic() {
		return veic;
	}

	public void setVeic(Long veic) {
		this.veic = veic;
	}

	public Long getMotorista() {
		return motorista;
	}

	public void setMotorista(Long motorista) {
		this.motorista = motorista;
	}

	public Long getOpcor() {
		return opcor;
	}

	public void setOpcor(Long opcor) {
		this.opcor = opcor;
	}

	public String getDihi() {
		return dihi;
	}

	public void setDihi(String dihi) {
		this.dihi = dihi;
	}

	public Long getCaux() {
		return caux;
	}

	public void setCaux(Long caux) {
		this.caux = caux;
	}

	public Long getTipoEngDeseng() {
		return tipoEngDeseng;
	}

	public void setTipoEngDeseng(Long tipoEngDeseng) {
		this.tipoEngDeseng = tipoEngDeseng;
	}

}
