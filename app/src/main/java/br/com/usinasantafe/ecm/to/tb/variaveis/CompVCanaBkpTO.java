package br.com.usinasantafe.ecm.to.tb.variaveis;

import br.com.usinasantafe.ecm.pst.Entidade;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="tbcompvcanabkpvar")
public class CompVCanaBkpTO extends Entidade{

	private static final long serialVersionUID = 1L;

	@DatabaseField
    private Long moto;
	@DatabaseField
    private Long carr1;
	@DatabaseField
    private Long carr2;
	@DatabaseField
    private Long carr3;
	@DatabaseField(id=true)
    private String dataSaidaCampo;
	@DatabaseField
    private Long noteiro;
	
	public CompVCanaBkpTO() {
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
	public Long getCarr2() {
		return carr2;
	}
	public void setCarr2(Long carr2) {
		this.carr2 = carr2;
	}
	public Long getCarr3() {
		return carr3;
	}
	public void setCarr3(Long carr3) {
		this.carr3 = carr3;
	}
	public String getDataSaidaCampo() {
		return dataSaidaCampo;
	}
	public void setDataSaidaCampo(String dataSaidaCampo) {
		this.dataSaidaCampo = dataSaidaCampo;
	}
	public Long getNoteiro() {
		return noteiro;
	}
	public void setNoteiro(Long noteiro) {
		this.noteiro = noteiro;
	}

}
