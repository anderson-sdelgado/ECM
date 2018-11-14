package br.com.usinasantafe.ecm.to.tb.estaticas;
/**
 *
 * @author anderson
 */
import br.com.usinasantafe.ecm.pst.Entidade;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="tbtipofunccamest")
public class TipoFuncCamTO extends Entidade {
    private static final long serialVersionUID = 1L;

    @DatabaseField(id=true)
    private Long idFuncaoCam;
    @DatabaseField
    private String descrFuncaoCam;
    @DatabaseField
    private Long tipoFuncaoCam;

    public TipoFuncCamTO() {
    }

    public Long getIdFuncaoCam() {
        return idFuncaoCam;
    }
    public void setIdFuncaoCam(Long numtipofunccam) {
        this.idFuncaoCam = numtipofunccam;
    }
    public String getDescrFuncaoCam() {
        return descrFuncaoCam;
    }
    public void setDescrFuncaoCam(String desctipofunccam) {
        this.descrFuncaoCam = desctipofunccam;
    }
    public Long getTipoFuncaoCam() {
        return tipoFuncaoCam;
    }
    public void setTipoFuncaoCam(Long tipoFuncaoCam) {
        this.tipoFuncaoCam = tipoFuncaoCam;
    }

}
