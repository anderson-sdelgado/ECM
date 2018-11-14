package br.com.usinasantafe.ecm.to.tb.estaticas;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.ecm.pst.Entidade;

@DatabaseTable(tableName="tbnoteiroest")
public class NoteiroTO extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(id=true)
    private Long codNoteiro;

    public NoteiroTO() {
    }

    public Long getCodNoteiro() {
        return codNoteiro;
    }

    public void setCodNoteiro(Long codNoteiro) {
        this.codNoteiro = codNoteiro;
    }
}
