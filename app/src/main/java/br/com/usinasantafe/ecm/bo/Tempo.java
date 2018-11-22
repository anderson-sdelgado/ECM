package br.com.usinasantafe.ecm.bo;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.usinasantafe.ecm.to.tb.estaticas.DataTO;

public class Tempo {

	private Timer timerAtual = new Timer();
    private TimerTask task;
    private final Handler handler = new Handler();
    private Date datahora;
    private final long REFRESH_TIME = 30000;
    
    private static Tempo instance = null;
    private boolean envioDado;
	private Context context;
    
	private int conBoletim;
	
	public Tempo() {
	}
	
    public static Tempo getInstance() {
        if (instance == null)
        instance = new Tempo();
        return instance;
    }

	public void manipDataHora(String data){

        try{

            JSONObject jObj = new JSONObject(data.trim());
            JSONArray jsonArray = jObj.getJSONArray("dados");

            JSONObject objeto = jsonArray.getJSONObject(0);
            Gson gson = new Gson();
            DataTO dataTO = gson.fromJson(objeto.toString(), DataTO.class);

            StringBuffer dtServ = new StringBuffer(dataTO.getData());

            Log.i("ECM", "DATA HORA: " + dtServ);

            dtServ.delete(10, 11);
            dtServ.insert(10, " ");

            String dtStr = String.valueOf(dtServ);

            String diaStr = dtStr.substring(0, 2);
            String mesStr = dtStr.substring(3, 5);
            String anoStr = dtStr.substring(6, 10);
            String horaStr= dtStr.substring(11, 13);
            String minutoStr= dtStr.substring(14, 16);

            Log.i("ECM", "Dia: "+ diaStr);
            Log.i("ECM", "Mes: "+ mesStr);
            Log.i("ECM", "Ano: "+ anoStr);
            Log.i("ECM", "Hora: "+ horaStr);
            Log.i("ECM", "Minuto: "+ minutoStr);

            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(diaStr));
            cal.set(Calendar.MONTH, Integer.parseInt(mesStr) - 1);
            cal.set(Calendar.YEAR, Integer.parseInt(anoStr));
            cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(horaStr));
            cal.set(Calendar.MINUTE, Integer.parseInt(minutoStr));

            Date dataServ = cal.getTime();
            datahora = dataServ;

            dataTO.insert();

        }
        catch (Exception e) {
            Log.i("ERRO", "Erro Manip = " + e);
        }

	}

    public String datahora(){

        String dataCerta = "";

        TimeZone tz = TimeZone.getDefault();
        Date dataHora = new Date();
        Date d = new Date();
        Calendar cal = Calendar.getInstance();
        Long dt =  dataHora.getTime() - tz.getOffset(d.getTime());
        cal.setTimeInMillis(dt);

        int mes = cal.get(Calendar.MONTH);
        int dia = cal.get(Calendar.DAY_OF_MONTH);
        int ano = cal.get(Calendar.YEAR);
        int horas = cal.get(Calendar.HOUR_OF_DAY);
        int minutos = cal.get(Calendar.MINUTE);
        mes = mes + 1;

        String mesStr = "";
        if(mes < 10){
            mesStr = "0" + mes;
        }
        else{
            mesStr = String.valueOf(mes);
        }

        String diaStr = "";
        if(dia < 10){
            diaStr = "0" + dia;
        }
        else{
            diaStr = String.valueOf(dia);
        }

        String horasStr = "";
        if(horas < 10){
            horasStr = "0" + horas;
        }
        else{
            horasStr = String.valueOf(horas);
        }

        String minutosStr = "";
        if(minutos < 10){
            minutosStr = "0" + minutos;
        }
        else{
            minutosStr = String.valueOf(minutos);
        }
        
        dataCerta = ""+diaStr+"/"+mesStr+"/"+ano+" "+horasStr+":"+minutosStr;

        return dataCerta;

    }

    public String dataSHora(){

        String dataCerta = "";

        TimeZone tz = TimeZone.getDefault();
        Date dataHora = new Date();
        Date d = new Date();
        Calendar cal = Calendar.getInstance();
        Long dt =  dataHora.getTime() - tz.getOffset(d.getTime());
        cal.setTimeInMillis(dt);

        int mes = cal.get(Calendar.MONTH);
        int dia = cal.get(Calendar.DAY_OF_MONTH);
        int ano = cal.get(Calendar.YEAR);
        mes = mes + 1;

        String mesStr = "";
        if(mes < 10){
            mesStr = "0" + mes;
        }
        else{
            mesStr = String.valueOf(mes);
        }

        String diaStr = "";
        if(dia < 10){
            diaStr = "0" + dia;
        }
        else{
            diaStr = String.valueOf(dia);
        }

        dataCerta = ""+diaStr+"/"+mesStr+"/"+ano;

        return dataCerta;

    }

	public boolean isEnvioDado() {
		return envioDado;
	}

	public void setEnvioDado(boolean envioDado) {
		this.envioDado = envioDado;
	}

    public Date getDatahora() {
        return datahora;
    }

    public void setDatahora(Date datahora) {
        this.datahora = datahora;
    }
}
