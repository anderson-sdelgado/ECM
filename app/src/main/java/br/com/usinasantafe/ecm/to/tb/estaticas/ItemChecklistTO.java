package br.com.usinasantafe.ecm.to.tb.estaticas;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.ecm.pst.Entidade;

/**
 * Created by anderson on 29/03/2017.
 */

@DatabaseTable(tableName="tbitemchecklistest")
public class ItemChecklistTO extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(id=true)
    private Long idItemChecklist;
    @DatabaseField
    private Long seqItemChecklist;
    @DatabaseField
    private String descrItemChecklist;

    public ItemChecklistTO() {
    }

    public Long getIdItemChecklist() {
        return idItemChecklist;
    }

    public void setIdItemChecklist(Long idItemChecklist) {
        this.idItemChecklist = idItemChecklist;
    }

    public Long getSeqItemChecklist() {
        return seqItemChecklist;
    }

    public void setSeqItemChecklist(Long seqItemChecklist) {
        this.seqItemChecklist = seqItemChecklist;
    }

    public String getDescrItemChecklist() {
        return descrItemChecklist;
    }

    public void setDescrItemChecklist(String descrItemChecklist) {
        this.descrItemChecklist = descrItemChecklist;
    }

}
