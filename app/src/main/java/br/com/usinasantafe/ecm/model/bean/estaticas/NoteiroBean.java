package br.com.usinasantafe.ecm.model.bean.estaticas;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.ecm.model.bean.pst.Entidade;

@DatabaseTable(tableName="tbnoteiroest")
public class NoteiroBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(id=true)
    private Long codNoteiro;

    public NoteiroBean() {
    }

    public Long getCodNoteiro() {
        return codNoteiro;
    }

    public void setCodNoteiro(Long codNoteiro) {
        this.codNoteiro = codNoteiro;
    }
}
