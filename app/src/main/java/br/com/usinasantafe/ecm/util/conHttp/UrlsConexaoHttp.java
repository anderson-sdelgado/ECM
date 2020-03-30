package br.com.usinasantafe.ecm.util.conHttp;

import br.com.usinasantafe.ecm.ECMContext;

public class UrlsConexaoHttp {

    public static String urlPrincipal = "http://www.usinasantafe.com.br/ecmdev/view/";
    public static String urlPrincEnvio = "http://www.usinasantafe.com.br/ecmdev/view/";

    public static String localPSTEstatica = "br.com.usinasantafe.ecm.model.bean.estaticas.";
    public static String localUrl = "br.com.usinasantafe.ecm.util.connHttp.UrlsConexaoHttp";

    public static String put = "?versao=" + ECMContext.versaoAplic.replace(".", "_");

    public static String ColabBean = urlPrincipal + "colab.php";
    public static String EquipBean = urlPrincipal + "equip.php";
    public static String FrenteTO = urlPrincipal + "frente.php";
    public static String ItemCLTO = urlPrincipal + "itemchecklist.php";
    public static String MotoMecTO = urlPrincipal + "motomec.php";
    public static String RAtivOSTO = urlPrincipal + "rativos.php";
    public static String RLibOSTO = urlPrincipal + "rlibos.php";
    public static String TurnoTO = urlPrincipal + "turno.php";

    public UrlsConexaoHttp() {
    }

    public String getsApontVCana() {
        return urlPrincEnvio + "apontvcana.php";
    }

    public String getsApontChecklist() {
        return urlPrincEnvio + "apontchecklist.php";
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
        if (classe.equals("RAtivOSBean")) {
            retorno = urlPrincipal + "verifativ.php";
        } else if (classe.equals("RLibOSBean")) {
            retorno = urlPrincipal + "veriflib.php";
        } else if (classe.equals("CECBean")) {
            retorno = urlPrincEnvio + "buscabol.php";
        } else if (classe.equals("BoletimTOViagem")) {
            retorno = urlPrincEnvio + "buscabolviag.php";
        } else if (classe.equals("Atualiza")) {
            retorno = urlPrincEnvio + "atualizaaplic.php";
        } else if (classe.equals("CheckList")) {
            retorno = urlPrincEnvio + "itemchecklist.php";
        }
        return retorno;
    }

}
