package models.utils;

import com.google.common.base.Strings;

import exceptions.AppException;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public final class MaskUtil {
	
	public static String formatLegalDocument(String ld) {
        if (Strings.isNullOrEmpty(ld))
            return ld;

        ld = StringUtil.toOnlyNumbers(ld);
        
        if(ld.length() == 14) {
        	return String.format("%s.%s.%s/%s-%s", ld.substring(0, 2), ld.substring(2, 5), ld.substring(5, 8), ld.substring(8, 12), ld.substring(12));
        }else {
        	return String.format("%s.%s.%s-%s", ld.substring(0, 3), ld.substring(3, 6), ld.substring(6, 9), ld.substring(9));
        }
    }

    public static String formatToCPF(String cpf) {
        if (Strings.isNullOrEmpty(cpf))
            return cpf;

        return String.format("%s.%s.%s-%s", cpf.substring(0, 3), cpf.substring(3, 6), cpf.substring(6, 9), cpf.substring(9));
    }

    public static String formatToCNPJ(String cnpj) {
        if (Strings.isNullOrEmpty(cnpj))
            return cnpj;

        cnpj = StringUtil.toOnlyNumbers(cnpj);

        return String.format("%s.%s.%s/%s-%s", cnpj.substring(0, 2), cnpj.substring(2, 5), cnpj.substring(5, 8), cnpj.substring(8, 12), cnpj.substring(12));
    }

    public static String formatToMonetary(BigInteger valueInCents) {
        if (valueInCents == null) return null;
        try {
        	DecimalFormat df = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));
        	return String.format("R$%s", df.format(MathUtil.divide(new BigDecimal(valueInCents), MathUtil.HUNDRED)));
        } catch (AppException e) {
            DebugUtil.e(e);
            return "R$ -";
        }
    }
    
    public static String formatToMonetary(long value) {
    	if (value < 0) value = 0;
        return formatToMonetary(BigInteger.valueOf(value));
    }
    
    public static String formatToMonetary(BigDecimal value) {
        if (value == null) return "";
        DecimalFormat df = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));
        return String.format("R$%s", df.format(value.doubleValue()));
    }

    public static String formatToCellphone(String cellphone) {
        if (Strings.isNullOrEmpty(cellphone))
            return cellphone;

        cellphone = StringUtil.toOnlyNumbers(cellphone);
        int length = cellphone.length();
        if (length == 8)
            return String.format("%s-%s", cellphone.substring(0, 4), cellphone.substring(4));
        else if (length == 9)
            return String.format("%s-%s", cellphone.substring(0, 5), cellphone.substring(5));
        else if (length == 10)
            return String.format("(%s) %s-%s", cellphone.substring(0, 2), cellphone.substring(2, 6), cellphone.substring(6, 10));
        else if (length == 11)
            return String.format("(%s) %s-%s", cellphone.substring(0, 2), cellphone.substring(2, 7), cellphone.substring(7));
        else
            return cellphone;
    }
    
    /** Retorna em [0] o codigo da area e em [1] o telefone */
    public static String[] splitCellphone(String cellphone) {
    	String str[] = new String[2];
    	str[0] = "";
    	str[1] = "";
    	cellphone = StringUtil.toOnlyNumbers(cellphone);
    	if(!Strings.isNullOrEmpty(cellphone)){  	
	    	str[0] = cellphone.substring(0, 2);
	    	str[1] = cellphone.substring(2, cellphone.length());
    	}
    	return str;
    }
    
    public static String formatToDate(Date date){
    	if(date == null)
    		return "";
    	String s = new SimpleDateFormat("dd/MM/yyyy").format(date);
    	return s;
    }
    
    public static String[] formatToFullDate(Date date){
    	String s[] = new String[2];
    	
    	if(date == null){
    		s[0] = s[1] = "";
    	}else{
    		s[0] = new SimpleDateFormat("dd/MM/yyyy").format(date);
        	s[1] = new SimpleDateFormat("HH:mm").format(date);
    	}
    	return s;
    }

}
