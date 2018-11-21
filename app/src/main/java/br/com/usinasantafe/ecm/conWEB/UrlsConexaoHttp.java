package br.com.usinasantafe.ecm.conWEB;

public class UrlsConexaoHttp {

    private int tipoEnvio = 2;

    public static String datahorahttp = "http://www.usinasantafe.com.br/ecm/datahora.php";

    public static String urlPrincipal = "http://www.usinasantafe.com.br/ecmdev/";
    //public static String urlPrincipal = "http://www.usinasantafe.com.br/ecmdesenv/";

    public static String urlPrincEnvio = "http://www.usinasantafe.com.br/ecmdev/";
    //public static String urlPrincEnvio = "http://www.usinasantafe.com.br/ecmdesenv/";

    //public static String localPSTVariavel = "br.com.usinasantafe.ecm.to.tb.variaveis.";
    public static String localPSTEstatica = "br.com.usinasantafe.ecm.to.tb.estaticas.";
    public static String localUrl = "br.com.usinasantafe.ecm.conWEB.UrlsConexaoHttp";

    public static String AtividadeOSTO = urlPrincipal + "atividadeos.php";
    public static String CaminhaoTO = urlPrincipal + "caminhao.php";
    public static String CarregadeiraTO = urlPrincipal + "carregadeira.php";
    public static String CarretaTO = urlPrincipal + "carreta.php";
    public static String FrenteTO = urlPrincipal + "frente.php";
    public static String LiberacaoTO = urlPrincipal + "liberacao.php";
    public static String MotoMecTO = urlPrincipal + "motomec.php";
    public static String MotoristaTO = urlPrincipal + "motorista.php";
    public static String OSTO = urlPrincipal + "os.php";
    public static String TurnoTO = urlPrincipal + "turno.php";
    public static String LocalTO = urlPrincipal + "local.php";
    public static String ItemCheckListTO = urlPrincipal + "itemchecklist.php";

    public UrlsConexaoHttp() {
    }

    public String getsApontVCana() {
        return urlPrincEnvio + "apontvcana.php";
    }

    public String getsApontVVinhaca() {
        return urlPrincEnvio + "apontvvinhaca.php";
    }

    public String getsInsertMotoMec() {
        return urlPrincEnvio + "apontmotomec.php";
    }

    public String getsApontChecklist() {
        return urlPrincEnvio + "apontchecklist.php";
    }

    public String getsData() {
        return urlPrincEnvio + "datahora.php";
    }

    public String urlVerifica(String classe) {
        String retorno = "";
        if (classe.equals("AtividadeOSTO")) {
            retorno = urlPrincipal + "verifatividade.php";
        } else if (classe.equals("LiberacaoTO")) {
            retorno = urlPrincipal + "verifliberacao.php";
        } else if (classe.equals("BoletimTO")) {
            retorno = urlPrincEnvio + "buscaboletim.php";
        } else if (classe.equals("BoletimTOViagem")) {
            retorno = urlPrincEnvio + "buscaboletimv.php";
        } else if (classe.equals("Atualiza")) {
            retorno = urlPrincEnvio + "atualizaaplic.php";
        } else if (classe.equals("CheckList")) {
            retorno = urlPrincEnvio + "itemchecklist.php";
        }
        return retorno;
    }

}
