package br.com.usinasantafe.ecm.model.bean.variaveis;

import br.com.usinasantafe.ecm.model.pst.Entidade;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="tbboletimvar")
public class BoletimBean extends Entidade  {

	private static final long serialVersionUID = 1L;

	@DatabaseField(generatedId=true)
	private Long idBoletim;
	@DatabaseField
    private Long ultviagemIdBoleto;
	@DatabaseField
    private Long caminhaoBoleto;
	@DatabaseField
    private Long possuiSorteioBoleto;
	@DatabaseField
    private Long cecPaiBoleto;
	@DatabaseField
    private Long cdFrenteBoleto;
	@DatabaseField
    private String dthrEntradaBoleto;
	@DatabaseField
    private Long cecSorteado1Boleto;
	@DatabaseField
    private Long unidadeSorteada1Boleto;
	@DatabaseField
    private Long cecSorteado2Boleto;
	@DatabaseField
    private Long unidadeSorteada2Boleto;
	@DatabaseField
    private Long cecSorteado3Boleto;
	@DatabaseField
    private Long unidadeSorteada3Boleto;
	@DatabaseField
    private Double pesoLiquidoBoleto;
	
	public BoletimBean() {
	}

	public Long getIdBoletim() {
		return idBoletim;
	}

	public void setIdBoletim(Long idBoletim) {
		this.idBoletim = idBoletim;
	}

	public Long getUltviagemIdBoleto() {
		return ultviagemIdBoleto;
	}
//
//	public void setUltviagemIdBoleto(Long ultviagemIdBoleto) {
//		this.ultviagemIdBoleto = ultviagemIdBoleto;
//	}

	public Long getCaminhaoBoleto() {
		return caminhaoBoleto;
	}

	public void setCaminhaoBoleto(Long caminhaoBoleto) {
		this.caminhaoBoleto = caminhaoBoleto;
	}

	public Long getPossuiSorteioBoleto() {
		return possuiSorteioBoleto;
	}

	public void setPossuiSorteioBoleto(Long possuiSorteioBoleto) {
		this.possuiSorteioBoleto = possuiSorteioBoleto;
	}

	public Long getCecPaiBoleto() {
		return cecPaiBoleto;
	}

	public void setCecPaiBoleto(Long cecPaiBoleto) {
		this.cecPaiBoleto = cecPaiBoleto;
	}

	public Long getCdFrenteBoleto() {
		return cdFrenteBoleto;
	}

	public void setCdFrenteBoleto(Long cdFrenteBoleto) {
		this.cdFrenteBoleto = cdFrenteBoleto;
	}

	public String getDthrEntradaBoleto() {
		return dthrEntradaBoleto;
	}

	public void setDthrEntradaBoleto(String dthrEntradaBoleto) {
		this.dthrEntradaBoleto = dthrEntradaBoleto;
	}

	public Long getCecSorteado1Boleto() {
		return cecSorteado1Boleto;
	}

	public void setCecSorteado1Boleto(Long cecSorteado1Boleto) {
		this.cecSorteado1Boleto = cecSorteado1Boleto;
	}

	public Long getUnidadeSorteada1Boleto() {
		return unidadeSorteada1Boleto;
	}

	public void setUnidadeSorteada1Boleto(Long unidadeSorteada1Boleto) {
		this.unidadeSorteada1Boleto = unidadeSorteada1Boleto;
	}

	public Long getCecSorteado2Boleto() {
		return cecSorteado2Boleto;
	}

	public void setCecSorteado2Boleto(Long cecSorteado2Boleto) {
		this.cecSorteado2Boleto = cecSorteado2Boleto;
	}

	public Long getUnidadeSorteada2Boleto() {
		return unidadeSorteada2Boleto;
	}

	public void setUnidadeSorteada2Boleto(Long unidadeSorteada2Boleto) {
		this.unidadeSorteada2Boleto = unidadeSorteada2Boleto;
	}

	public Long getCecSorteado3Boleto() {
		return cecSorteado3Boleto;
	}

	public void setCecSorteado3Boleto(Long cecSorteado3Boleto) {
		this.cecSorteado3Boleto = cecSorteado3Boleto;
	}

	public Long getUnidadeSorteada3Boleto() {
		return unidadeSorteada3Boleto;
	}

	public void setUnidadeSorteada3Boleto(Long unidadeSorteada3Boleto) {
		this.unidadeSorteada3Boleto = unidadeSorteada3Boleto;
	}

	public Double getPesoLiquidoBoleto() {
		return pesoLiquidoBoleto;
	}

	public void setPesoLiquidoBoleto(Double pesoLiquidoBoleto) {
		this.pesoLiquidoBoleto = pesoLiquidoBoleto;
	}
	
}
