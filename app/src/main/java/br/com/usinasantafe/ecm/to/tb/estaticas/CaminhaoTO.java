/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.usinasantafe.ecm.to.tb.estaticas;

/**
 *
 * @author anderson
 */

import br.com.usinasantafe.ecm.pst.Entidade;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="tbcaminhaoest")
public class CaminhaoTO extends Entidade {

	private static final long serialVersionUID = 1L;
	
	@DatabaseField(id=true)
    private Long idCaminhao;
	@DatabaseField
    private Long tipoCaminhao;

    public CaminhaoTO() {
    }

    public Long getIdCaminhao() {
        return idCaminhao;
    }

    public void setIdCaminhao(Long idCaminhao) {
        this.idCaminhao = idCaminhao;
    }

    public Long getTipoCaminhao() {
        return tipoCaminhao;
    }

    public void setTipoCaminhao(Long tipoCaminhao) {
        this.tipoCaminhao = tipoCaminhao;
    }

}
