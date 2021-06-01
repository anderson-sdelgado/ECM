package br.com.usinasantafe.ecm.model.dao;

import java.util.List;

import br.com.usinasantafe.ecm.model.bean.estaticas.TurnoBean;

public class TurnoDAO {

    public TurnoDAO() {
    }

    public List getTurnoList(Long codTurno){
        TurnoBean turnoBean = new TurnoBean();
        return turnoBean.get("codTurno", codTurno);
    }

}
