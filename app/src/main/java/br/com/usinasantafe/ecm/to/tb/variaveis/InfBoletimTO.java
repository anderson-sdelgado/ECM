package br.com.usinasantafe.ecm.to.tb.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.ecm.pst.Entidade;

@DatabaseTable(tableName="tbinfbolvar")
public class InfBoletimTO extends Entidade {

    private static final long serialVersionUID = 1L;

	@DatabaseField(generatedId=true)
	private Long idInfBoletim;
	@DatabaseField
	private Long cam;
	@DatabaseField
    private Long codigoMoto;
    @DatabaseField
    private String nomeMoto;
    @DatabaseField
    private Long tipoAtiv;
	@DatabaseField
	private Long libCam;
	@DatabaseField
	private Long maqCam;
	@DatabaseField
	private Long opCam;
	@DatabaseField
	private Long carr1;
	@DatabaseField
	private Long libCarr1;
	@DatabaseField
	private Long maqCarr1;
	@DatabaseField
	private Long opCarr1;
	@DatabaseField
	private Long carr2;
	@DatabaseField
	private Long libCarr2;
	@DatabaseField
	private Long maqCarr2;
	@DatabaseField
	private Long opCarr2;
	@DatabaseField
	private Long carr3;
	@DatabaseField
	private Long libCarr3;
	@DatabaseField
	private Long maqCarr3;
	@DatabaseField
	private Long opCarr3;
	@DatabaseField
	private Long carr4;
	@DatabaseField
	private Long libCarr4;
	@DatabaseField
	private Long maqCarr4;
	@DatabaseField
	private Long opCarr4;
	@DatabaseField
	private String dataChegCampo;
	@DatabaseField
	private String dataSaidaCampo;
	@DatabaseField
	private String dataSaidaUsina;
	@DatabaseField
	private Long noteiro;
	@DatabaseField
	private Long turno;
	@DatabaseField
	private Long frente;
    
    public InfBoletimTO() {
	}

	public Long getIdInfBoletim() {
		return idInfBoletim;
	}

	public Long getCodigoMoto() {
		return codigoMoto;
	}

	public void setCodigoMoto(Long codigoMoto) {
		this.codigoMoto = codigoMoto;
	}

	public String getNomeMoto() {
		return nomeMoto;
	}

	public void setNomeMoto(String nomeMoto) {
		this.nomeMoto = nomeMoto;
	}

	public Long getTipoAtiv() {
		return tipoAtiv;
	}

	public void setTipoAtiv(Long tipoAtiv) {
		this.tipoAtiv = tipoAtiv;
	}

	public Long getCam() {
		return cam;
	}

	public void setCam(Long cam) {
		this.cam = cam;
	}

	public Long getLibCam() {
		return libCam;
	}

	public void setLibCam(Long libCam) {
		this.libCam = libCam;
	}

	public Long getMaqCam() {
		return maqCam;
	}

	public void setMaqCam(Long maqCam) {
		this.maqCam = maqCam;
	}

	public Long getOpCam() {
		return opCam;
	}

	public void setOpCam(Long opCam) {
		this.opCam = opCam;
	}

	public Long getCarr1() {
		return carr1;
	}

	public void setCarr1(Long carr1) {
		this.carr1 = carr1;
	}

	public Long getLibCarr1() {
		return libCarr1;
	}

	public void setLibCarr1(Long libCarr1) {
		this.libCarr1 = libCarr1;
	}

	public Long getMaqCarr1() {
		return maqCarr1;
	}

	public void setMaqCarr1(Long maqCarr1) {
		this.maqCarr1 = maqCarr1;
	}

	public Long getOpCarr1() {
		return opCarr1;
	}

	public void setOpCarr1(Long opCarr1) {
		this.opCarr1 = opCarr1;
	}

	public Long getCarr2() {
		return carr2;
	}

	public void setCarr2(Long carr2) {
		this.carr2 = carr2;
	}

	public Long getLibCarr2() {
		return libCarr2;
	}

	public void setLibCarr2(Long libCarr2) {
		this.libCarr2 = libCarr2;
	}

	public Long getMaqCarr2() {
		return maqCarr2;
	}

	public void setMaqCarr2(Long maqCarr2) {
		this.maqCarr2 = maqCarr2;
	}

	public Long getOpCarr2() {
		return opCarr2;
	}

	public void setOpCarr2(Long opCarr2) {
		this.opCarr2 = opCarr2;
	}

	public Long getCarr3() {
		return carr3;
	}

	public void setCarr3(Long carr3) {
		this.carr3 = carr3;
	}

	public Long getLibCarr3() {
		return libCarr3;
	}

	public void setLibCarr3(Long libCarr3) {
		this.libCarr3 = libCarr3;
	}

	public Long getMaqCarr3() {
		return maqCarr3;
	}

	public void setMaqCarr3(Long maqCarr3) {
		this.maqCarr3 = maqCarr3;
	}

	public Long getOpCarr3() {
		return opCarr3;
	}

	public void setOpCarr3(Long opCarr3) {
		this.opCarr3 = opCarr3;
	}

	public Long getCarr4() {
		return carr4;
	}

	public void setCarr4(Long carr4) {
		this.carr4 = carr4;
	}

	public Long getLibCarr4() {
		return libCarr4;
	}

	public void setLibCarr4(Long libCarr4) {
		this.libCarr4 = libCarr4;
	}

	public Long getMaqCarr4() {
		return maqCarr4;
	}

	public void setMaqCarr4(Long maqCarr4) {
		this.maqCarr4 = maqCarr4;
	}

	public Long getOpCarr4() {
		return opCarr4;
	}

	public void setOpCarr4(Long opCarr4) {
		this.opCarr4 = opCarr4;
	}

	public String getDataChegCampo() {
		return dataChegCampo;
	}

	public void setDataChegCampo(String dataChegCampo) {
		this.dataChegCampo = dataChegCampo;
	}

	public String getDataSaidaCampo() {
		return dataSaidaCampo;
	}

	public void setDataSaidaCampo(String dataSaidaCampo) {
		this.dataSaidaCampo = dataSaidaCampo;
	}

	public String getDataSaidaUsina() {
		return dataSaidaUsina;
	}

	public void setDataSaidaUsina(String dataSaidaUsina) {
		this.dataSaidaUsina = dataSaidaUsina;
	}

	public Long getNoteiro() {
		return noteiro;
	}

	public void setNoteiro(Long noteiro) {
		this.noteiro = noteiro;
	}

	public Long getTurno() {
		return turno;
	}

	public void setTurno(Long turno) {
		this.turno = turno;
	}

	public Long getFrente() {
		return frente;
	}

	public void setFrente(Long frente) {
		this.frente = frente;
	}

}
