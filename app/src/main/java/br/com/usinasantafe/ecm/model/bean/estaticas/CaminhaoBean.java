/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.usinasantafe.ecm.model.bean.estaticas;

/**
 *
 * @author anderson
 */

import br.com.usinasantafe.ecm.model.bean.pst.Entidade;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="tbcaminhaoest")
public class CaminhaoBean extends Entidade {

	private static final long serialVersionUID = 1L;
	
	@DatabaseField(id=true)
    private Long idCaminhao;
    @DatabaseField
    private Long codCaminhao;
	@DatabaseField
    private Long tipoCaminhao;
    @DatabaseField
    private Long idChecklist;
    @DatabaseField
    private Long codTurno;

    public CaminhaoBean() {
    }

    public Long getIdCaminhao() {
        return idCaminhao;
    }

    public void setIdCaminhao(Long idCaminhao) {
        this.idCaminhao = idCaminhao;
    }

    public Long getCodCaminhao() {
        return codCaminhao;
    }

    public void setCodCaminhao(Long codCaminhao) {
        this.codCaminhao = codCaminhao;
    }

    public Long getTipoCaminhao() {
        return tipoCaminhao;
    }

    public void setTipoCaminhao(Long tipoCaminhao) {
        this.tipoCaminhao = tipoCaminhao;
    }

    public Long getIdChecklist() {
        return idChecklist;
    }

    public void setIdChecklist(Long idChecklist) {
        this.idChecklist = idChecklist;
    }

    public Long getCodTurno() {
        return codTurno;
    }

    public void setCodTurno(Long codTurno) {
        this.codTurno = codTurno;
    }
}
