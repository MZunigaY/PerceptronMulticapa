package com.demo.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Util {
	
	public static double redondear(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
	
	public static Date fromStringToDate(String fecha) throws ParseException {
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		return format.parse(fecha);
	}
	
	public static String fromDateToString(Date fecha) throws ParseException {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		return df.format(fecha);
	}
	
	public static double[] convertirAPrimitivo(List<Double> lista) {
		double[] target = new double[lista.size()];
		 for (int i = 0; i < target.length; i++) {
		    target[i] = lista.get(i); 
		 }
		 return target;
	}
	
	public static String convertirListaAString(List<Double> listaDouble) {
		String cadena = "";
		List<Integer> lista = new ArrayList<>();
		
		for (int i=0;i<listaDouble.size();i++) {
			lista.add(listaDouble.get(i).intValue());
			if (i+1 != listaDouble.size())
				cadena += lista.get(i)+",";
			else 
				cadena += lista.get(i)+"\n";
		}
		return cadena;
	}

}
