package br.com.usinasantafe.ecm.to.tb.variaveis;

import br.com.usinasantafe.ecm.pst.Entidade;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="tbmotomecvar")
public class ApontMotoMecTO extends Entidade  {

	private static final long serialVersionUID = 1L;
	
	@DatabaseField(id=true)
	private Long id;
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
	private Long estado;
	@DatabaseField
	private Long tipoFuncao;
	@DatabaseField
	private Long frente;
	@DatabaseField
	private Long car1;
	@DatabaseField
	private Long car2;
	@DatabaseField
	private Long car3;

	@DatabaseField
	private Long tipoEngDeseng;
	
	public ApontMotoMecTO() {
		// TODO Auto-generated constructor stub
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Long getEstado() {
		return estado;
	}

	public void setEstado(Long estado) {
		this.estado = estado;
	}

	public Long getTipoFuncao() {
		return tipoFuncao;
	}

	public void setTipoFuncao(Long tipoFuncao) {
		this.tipoFuncao = tipoFuncao;
	}

	public Long getFrente() {
		return frente;
	}

	public void setFrente(Long frente) {
		this.frente = frente;
	}

	public Long getCar1() {
		return car1;
	}

	public void setCar1(Long car1) {
		this.car1 = car1;
	}

	public Long getCar2() {
		return car2;
	}

	public void setCar2(Long car2) {
		this.car2 = car2;
	}

	public Long getCar3() {
		return car3;
	}

	public void setCar3(Long car3) {
		this.car3 = car3;
	}

	public Long getTipoEngDeseng() {
		return tipoEngDeseng;
	}

	public void setTipoEngDeseng(Long tipoEngDeseng) {
		this.tipoEngDeseng = tipoEngDeseng;
	}

}
