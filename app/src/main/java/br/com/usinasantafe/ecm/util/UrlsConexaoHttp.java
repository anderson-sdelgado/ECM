package br.com.usinasantafe.ecm.util;

public class UrlsConexaoHttp {

    public static String urlPrincipal = "http://www.usinasantafe.com.br/ecmdev/view/";
    public static String urlPrincEnvio = "http://www.usinasantafe.com.br/ecmdev/view/";

    public static String localPSTEstatica = "br.com.usinasantafe.ecm.model.bean.estaticas.";
    public static String localUrl = "br.com.usinasantafe.ecm.util.UrlsConexaoHttp";

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
        return urlPrincEnvio + "apontvcana2.php";
    }

    public String getsInsertMotoMec() {
        return urlPrincEnvio + "apontmotomec2.php";
    }

    public String getsApontChecklist() {
        return urlPrincEnvio + "apontchecklist2.php";
    }

    public String urlVerifica(String classe) {
        String retorno = "";
        if (classe.equals("RAtivOSBean")) {
            retorno = urlPrincipal + "verifativ2.php";
        } else if (classe.equals("RLibOSBean")) {
            retorno = urlPrincipal + "veriflib2.php";
        } else if (classe.equals("BoletimBean")) {
            retorno = urlPrincEnvio + "buscabol2.php";
        } else if (classe.equals("BoletimTOViagem")) {
            retorno = urlPrincEnvio + "buscabolviag2.php";
        } else if (classe.equals("Atualiza")) {
            retorno = urlPrincEnvio + "atualizaaplic2.php";
        } else if (classe.equals("CheckList")) {
            retorno = urlPrincEnvio + "itemchecklist2.php";
        }
        return retorno;
    }

}
