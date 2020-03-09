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
    private Long classeEquip;
    @DatabaseField
    private Long idCheckListEquip;
    @DatabaseField
    private Long idTurnoEquip;

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

    public Long getIdCheckListEquip() {
        return idCheckListEquip;
    }

    public void setIdCheckListEquip(Long idCheckListEquip) {
        this.idCheckListEquip = idCheckListEquip;
    }

    public Long getIdTurnoEquip() {
        return idTurnoEquip;
    }

    public void setIdTurnoEquip(Long idTurnoEquip) {
        this.idTurnoEquip = idTurnoEquip;
    }

    public Long getClasseEquip() {
        return classeEquip;
    }

    public void setClasseEquip(Long classeEquip) {
        this.classeEquip = classeEquip;
    }
}
