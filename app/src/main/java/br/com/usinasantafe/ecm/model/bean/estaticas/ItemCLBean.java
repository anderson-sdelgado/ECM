package br.com.usinasantafe.ecm.model.bean.estaticas;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.ecm.model.pst.Entidade;

/**
 * Created by anderson on 29/03/2017.
 */

@DatabaseTable(tableName="tbitemchecklistest")
public class ItemCLBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(id=true)
    private Long idItemCheckList;
    @DatabaseField
    private Long idCheckList;
    @DatabaseField
    private Long seqItemCheckList;
    @DatabaseField
    private String descrItemCheckList;

    public ItemCLBean() {
    }

    public Long getIdItemCheckList() {
        return idItemCheckList;
    }

    public void setIdItemCheckList(Long idItemCheckList) {
        this.idItemCheckList = idItemCheckList;
    }

    public Long getIdCheckList() {
        return idCheckList;
    }

    public void setIdCheckList(Long idCheckList) {
        this.idCheckList = idCheckList;
    }

    public Long getSeqItemCheckList() {
        return seqItemCheckList;
    }

    public void setSeqItemCheckList(Long seqItemCheckList) {
        this.seqItemCheckList = seqItemCheckList;
    }

    public String getDescrItemCheckList() {
        return descrItemCheckList;
    }

    public void setDescrItemCheckList(String descrItemCheckList) {
        this.descrItemCheckList = descrItemCheckList;
    }
}
