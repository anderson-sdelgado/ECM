package br.com.usinasantafe.ecm.model.bean.estaticas;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.ecm.model.pst.Entidade;

@DatabaseTable(tableName="tbequipsegest")
public class EquipSegBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(id=true)
    private Long idEquip;
    @DatabaseField
    private Long nroEquip;
    @DatabaseField
    private Long codClasseEquip;
    @DatabaseField
    private Long descrClasseEquip;
    @DatabaseField
    private Long tipoEquip;

    public EquipSegBean() {
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

    public Long getDescrClasseEquip() {
        return descrClasseEquip;
    }

    public void setDescrClasseEquip(Long descrClasseEquip) {
        this.descrClasseEquip = descrClasseEquip;
    }

    public Long getCodClasseEquip() {
        return codClasseEquip;
    }

    public void setCodClasseEquip(Long codClasseEquip) {
        this.codClasseEquip = codClasseEquip;
    }
}
