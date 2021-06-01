package br.com.usinasantafe.ecm.model.bean.estaticas;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.ecm.model.pst.Entidade;

@DatabaseTable(tableName="tbequipest")
public class EquipBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(id=true)
    private Long idEquip;
    @DatabaseField
    private Long nroEquip;
    @DatabaseField
    private Long tipoEquip;
    @DatabaseField
    private Long codClasseEquip;
    @DatabaseField
    private String descrClasseEquip;
    @DatabaseField
    private Long idCheckList;
    @DatabaseField
    private Long codTurno;
    @DatabaseField
    private Double horimetroEquip;

    public EquipBean() {
    }

    public Long getIdEquip() {
        return idEquip;
    }

    public void setIdEquip(Long idEquip) {
        this.idEquip = idEquip;
    }

    public Long getNroEquip() {
        return nroEquip;
    }

    public void setNroEquip(Long nroEquip) {
        this.nroEquip = nroEquip;
    }

    public Long getTipoEquip() {
        return tipoEquip;
    }

    public void setTipoEquip(Long tipoEquip) {
        this.tipoEquip = tipoEquip;
    }

    public Long getCodClasseEquip() {
        return codClasseEquip;
    }

    public void setCodClasseEquip(Long codClasseEquip) {
        this.codClasseEquip = codClasseEquip;
    }

    public String getDescrClasseEquip() {
        return descrClasseEquip;
    }

    public void setDescrClasseEquip(String descrClasseEquip) {
        this.descrClasseEquip = descrClasseEquip;
    }

    public Long getIdCheckList() {
        return idCheckList;
    }

    public void setIdCheckList(Long idCheckList) {
        this.idCheckList = idCheckList;
    }

    public Long getCodTurno() {
        return codTurno;
    }

    public void setCodTurno(Long codTurno) {
        this.codTurno = codTurno;
    }

    public Double getHorimetroEquip() {
        return horimetroEquip;
    }

    public void setHorimetroEquip(Double horimetroEquip) {
        this.horimetroEquip = horimetroEquip;
    }
}
