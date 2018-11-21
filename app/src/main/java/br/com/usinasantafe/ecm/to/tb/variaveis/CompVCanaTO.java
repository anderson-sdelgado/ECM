package br.com.usinasantafe.ecm.to.tb.variaveis;

import br.com.usinasantafe.ecm.pst.Entidade;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="tbcompvcanavar")
public class CompVCanaTO extends Entidade{

	private static final long serialVersionUID = 1L;

	@DatabaseField(generatedId=true)
	private Long idCompVCana;
	@DatabaseField
    private Long cam;
	@DatabaseField
    private Long libCam;
	@DatabaseField
    private Long maqCam;
	@DatabaseField
    private Long opCam;
	@DatabaseField
    private Long moto;
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
    private String dataChegCampo;
	@DatabaseField
    private String dataSaidaCampo;
	@DatabaseField
    private String dataSaidaUsina;
	@DatabaseField
    private Long noteiro;
	@DatabaseField
    private Long turno;
    
    public CompVCanaTO(){
    }

	public Long getIdCompVCana() {
		return idCompVCana;
	}

	public void setIdCompVCana(Long idCompVCana) {
		this.idCompVCana = idCompVCana;
	}

//	public void setId(Long id) {
//		this.id = id;
//	}

	public Long getCam() {
		return cam;
	}

	public void setCam(Long cam) {
		this.cam = cam;
	}

	public Long getLibCam() {
		return libCam;
	}

	public void setLibCam(Long libcam) {
		this.libCam = libcam;
	}

	public Long getMaqCam() {
		return maqCam;
	}

	public void setMaqCam(Long maqcam) {
		this.maqCam = maqcam;
	}

	public Long getOpCam() {
		return opCam;
	}

	public void setOpCam(Long opcam) {
		this.opCam = opcam;
	}

	public Long getMoto() {
		return moto;
	}

	public void setMoto(Long moto) {
		this.moto = moto;
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

	public void setLibCarr1(Long libcarr1) {
		this.libCarr1 = libcarr1;
	}

	public Long getMaqCarr1() {
		return maqCarr1;
	}

	public void setMaqCarr1(Long maqcarr1) {
		this.maqCarr1 = maqcarr1;
	}

	public Long getOpCarr1() {
		return opCarr1;
	}

	public void setOpCarr1(Long opcarr1) {
		this.opCarr1 = opcarr1;
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

	public void setLibCarr2(Long libcarr2) {
		this.libCarr2 = libcarr2;
	}

	public Long getMaqCarr2() {
		return maqCarr2;
	}

	public void setMaqCarr2(Long maqcarr2) {
		this.maqCarr2 = maqcarr2;
	}

	public Long getOpCarr2() {
		return opCarr2;
	}

	public void setOpCarr2(Long opcarr2) {
		this.opCarr2 = opcarr2;
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

	public void setLibCarr3(Long libcarr3) {
		this.libCarr3 = libcarr3;
	}

	public Long getMaqCarr3() {
		return maqCarr3;
	}

	public void setMaqCarr3(Long maqcarr3) {
		this.maqCarr3 = maqcarr3;
	}

	public Long getOpCarr3() {
		return opCarr3;
	}

	public void setOpCarr3(Long opcarr3) {
		this.opCarr3 = opcarr3;
	}

	public String getDataChegCampo() {
		return dataChegCampo;
	}

	public void setDataChegCampo(String dataccamp) {
		this.dataChegCampo = dataccamp;
	}

	public String getDataSaidaCampo() {
		return dataSaidaCampo;
	}

	public void setDataSaidaCampo(String datascamp) {
		this.dataSaidaCampo = datascamp;
	}

	public String getDataSaidaUsina() {
		return dataSaidaUsina;
	}

	public void setDataSaidaUsina(String datasusina) {
		this.dataSaidaUsina = datasusina;
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

}
