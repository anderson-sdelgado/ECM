package br.com.usinasantafe.ecm.util.connHttp;

import br.com.usinasantafe.ecm.ECMContext;

public class UrlsConexaoHttp {

    public static String urlPrincipal = "http://www.usinasantafe.com.br/pmmdev/view/";
    public static String urlPrincEnvio = "http://www.usinasantafe.com.br/pmmdev/view/";

    public static String localPSTEstatica = "br.com.usinasantafe.ecm.model.bean.estaticas.";
    public static String localUrl = "br.com.usinasantafe.ecm.util.connHttp.UrlsConexaoHttp";

    public static String put = "?versao=" + ECMContext.versaoAplic.replace(".", "_");

    public static String AtividadeBean = urlPrincipal + "atividade.php" + put;
    public static String EquipSegBean = urlPrincipal + "equipsegecm.php" + put;
    public static String FuncBean = urlPrincipal + "funcionario.php" + put;
    public static String FrenteBean = urlPrincipal + "frente.php" + put;
    public static String ItemCheckListBean = urlPrincipal + "itemchecklist.php" + put;
    public static String MotoMecBean = urlPrincipal + "motomec.php" + put;
    public static String OSBean = urlPrincipal + "osecm.php" + put;
    public static String TurnoBean = urlPrincipal + "turno.php" + put;

    public UrlsConexaoHttp() {
    }

    public String getsInsertPreCEC() {
        return urlPrincEnvio + "inserirprecec.php" + put;
    }

    public String getsInsertChecklist() {
        return urlPrincEnvio + "inserirchecklist.php" + put;
    }

    public String getsInsertApontaMM() {
        return urlPrincEnvio + "inserirapontmm.php" + put;
    }

    public String getsInsertBolAbertoMM() {
        return urlPrincEnvio + "inserirbolabertomm.php" + put;
    }

    public String getsInsertBolFechadoMM() {
        return urlPrincEnvio + "inserirbolfechadomm.php" + put;
    }

    public String urlVerifica(String classe) {
        String retorno = "";
        if (classe.equals("Equip")) {
            retorno = urlPrincipal + "equipecm.php" + put;
        } else if (classe.equals("OS")) {
            retorno = urlPrincEnvio + "verosecm.php" + put;
        } else if (classe.equals("CEC")) {
            retorno = urlPrincEnvio + "retcec.php" + put;
        }else if (classe.equals("Atualiza")) {
            retorno = urlPrincEnvio + "atualaplicecm.php" + put;
        } else if (classe.equals("CheckList")) {
            retorno = urlPrincEnvio + "itemchecklist.php" + put;
        }
        return retorno;
    }

}
