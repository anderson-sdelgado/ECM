package br.com.usinasantafe.ecm.to.tb.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.ecm.pst.Entidade;

/**
 * Created by anderson on 31/03/2017.
 */
@DatabaseTable(tableName="tbcabchecklistvar")
public class CabecCheckListTO extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(id=true)
    private Long idCabecCheckList;
    @DatabaseField
    private Long equipCabecCheckList;
    @DatabaseField
    private String dtCabecCheckList;
    @DatabaseField
    private Long funcCabecCheckList;
    @DatabaseField
    private Long turnoCabecCheckList;

    public CabecCheckListTO() {
    }

    public Long getIdCabecCheckList() {
        return idCabecCheckList;
    }

    public void setIdCabecCheckList(Long idCabecCheckList) {
        this.idCabecCheckList = idCabecCheckList;
    }

    public Long getEquipCabecCheckList() {
        return equipCabecCheckList;
    }

    public void setEquipCabecCheckList(Long equipCabecCheckList) {
        this.equipCabecCheckList = equipCabecCheckList;
    }

    public String getDtCabecCheckList() {
        return dtCabecCheckList;
    }

    public void setDtCabecCheckList(String dtCabecCheckList) {
        this.dtCabecCheckList = dtCabecCheckList;
    }

    public Long getFuncCabecCheckList() {
        return funcCabecCheckList;
    }

    public void setFuncCabecCheckList(Long funcCabecCheckList) {
        this.funcCabecCheckList = funcCabecCheckList;
    }

    public Long getTurnoCabecCheckList() {
        return turnoCabecCheckList;
    }

    public void setTurnoCabecCheckList(Long turnoCabecCheckList) {
        this.turnoCabecCheckList = turnoCabecCheckList;
    }

}
