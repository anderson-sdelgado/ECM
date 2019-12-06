package br.com.usinasantafe.ecm.util;

public class UrlsConexaoHttp {

    public static String urlPrincipal = "http://www.usinasantafe.com.br/ecmdev/view/";
    public static String urlPrincEnvio = "http://www.usinasantafe.com.br/ecmdev/view/";

    public static String localPSTEstatica = "br.com.usinasantafe.ecm.model.bean.estaticas.";
    public static String localUrl = "br.com.usinasantafe.ecm.util.UrlsConexaoHttp";

//    public static String AtividadeOSTO = urlPrincipal + "atividadeos2.php";
//    public static String CaminhaoTO = urlPrincipal + "caminhao2.php";
//    public static String CarregadeiraTO = urlPrincipal + "carregadeira2.php";
//    public static String CarretaTO = urlPrincipal + "carreta2.php";
    public static String MotoristaTO = urlPrincipal + "colab.php";
    public static String FrenteTO = urlPrincipal + "frente.php";
    public static String ItemCheckListTO = urlPrincipal + "itemchecklist.php";
    public static String MotoMecTO = urlPrincipal + "motomec.php";
    public static String TurnoTO = urlPrincipal + "turno.php";
//    public static String LiberacaoTO = urlPrincipal + "liberacao2.php";
//    public static String OSTO = urlPrincipal + "os2.php";
//    public static String LocalTO = urlPrincipal + "local.php";


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
        if (classe.equals("AtividadeOSBean")) {
            retorno = urlPrincipal + "verifativ2.php";
        } else if (classe.equals("LiberacaoBean")) {
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
