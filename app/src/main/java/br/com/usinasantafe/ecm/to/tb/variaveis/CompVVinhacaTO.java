package br.com.usinasantafe.ecm.to.tb.variaveis;

import br.com.usinasantafe.ecm.pst.Entidade;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="tbcompvvinhacavar")
public class CompVVinhacaTO extends Entidade {
	
	private static final long serialVersionUID = 1L;
	
	@DatabaseField(id=true)
	private Long idCompVVinhaca;
	@DatabaseField
    private Long apont;
	@DatabaseField
    private Long turno;
	@DatabaseField
    private Long local;
	@DatabaseField
    private Long cam;
	@DatabaseField
    private Long moto;
	@DatabaseField
    private Long os;
	@DatabaseField
    private String data;
	
	public CompVVinhacaTO() {
	}

	public Long getIdCompVVinhaca() {
		return idCompVVinhaca;
	}

	public void setIdCompVVinhaca(Long idCompVVinhaca) {
		this.idCompVVinhaca = idCompVVinhaca;
	}

	public Long getApont() {
		return apont;
	}

	public void setApont(Long apont) {
		this.apont = apont;
	}

	public Long getTurno() {
		return turno;
	}

	public void setTurno(Long turno) {
		this.turno = turno;
	}

	public Long getLocal() {
		return local;
	}

	public void setLocal(Long local) {
		this.local = local;
	}

	public Long getCam() {
		return cam;
	}

	public void setCam(Long cam) {
		this.cam = cam;
	}

	public Long getMoto() {
		return moto;
	}

	public void setMoto(Long moto) {
		this.moto = moto;
	}

	public Long getOs() {
		return os;
	}

	public void setOs(Long os) {
		this.os = os;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
}
