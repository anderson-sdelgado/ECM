package br.com.usinasantafe.ecm.model.bean.variaveis;

import br.com.usinasantafe.ecm.model.bean.pst.Entidade;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="tbmotomecvar")
public class ApontMotoMecBean extends Entidade  {

	private static final long serialVersionUID = 1L;

	@DatabaseField(generatedId=true)
	private Long idApontMM;
	@DatabaseField
	private Long codEquip;
	@DatabaseField
	private Long matricColab;
	@DatabaseField
	private Long opCor;
	@DatabaseField
	private String dthr;
	@DatabaseField
	private Long caux;
	@DatabaseField
	private Long tipoEngDeseng;
	
	public ApontMotoMecBean() {
	}

	public Long getIdApontMM() {
		return idApontMM;
	}

	public void setIdApontMM(Long idApontMM) {
		this.idApontMM = idApontMM;
	}

	public Long getCodEquip() {
		return codEquip;
	}

	public void setCodEquip(Long codEquip) {
		this.codEquip = codEquip;
	}

	public Long getMatricColab() {
		return matricColab;
	}

	public void setMatricColab(Long matricColab) {
		this.matricColab = matricColab;
	}

	public Long getOpCor() {
		return opCor;
	}

	public void setOpCor(Long opCor) {
		this.opCor = opCor;
	}

	public String getDthr() {
		return dthr;
	}

	public void setDthr(String dthr) {
		this.dthr = dthr;
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
