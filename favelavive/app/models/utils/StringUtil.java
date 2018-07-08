package models.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.Normalizer;
import java.text.ParseException;
import java.util.UUID;

import com.google.common.base.CharMatcher;
import com.google.common.base.Strings;

public final class StringUtil {

    private static final CharMatcher ALNUM = CharMatcher.inRange('a', 'z')
            .or(CharMatcher.inRange('A', 'Z'))
            .or(CharMatcher.inRange('0', '9'))
            .or(CharMatcher.WHITESPACE)
            .precomputed();

    public static final String MESSAGE = "message";
    public static final String RESULT = "result";
    public static final String EMPTY = "";
    public static final String COMMA = ",";
    public static final String SPACE = " ";
    
    public static boolean isNullOrEmpty(String str){
    	return str == null || str.trim().isEmpty();
    }
    
    public static boolean isNullOrEmpty(String... strs){
    	if (strs == null || strs.length == 0) return true;
    	for (String s : strs) {
			if(s == null || s.trim().isEmpty()){
				return true;
			}
		}
    	return false;
    }

    public static String toOnlyNumbers(String text) {
        if (Strings.isNullOrEmpty(text))
            return text;

        return text.replaceAll("[\\D]", "");
    }
    
    public static BigDecimal toBigDecimal(String text) throws ParseException{
    	if (Strings.isNullOrEmpty(text))
            return new BigDecimal(0);
    	DecimalFormatSymbols symbols = new DecimalFormatSymbols();
    	symbols.setGroupingSeparator('.');
    	symbols.setDecimalSeparator(',');
    	String pattern = "#,##0.00";
    	DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);
    	decimalFormat.setParseBigDecimal(true);

    	BigDecimal bigDecimal = (BigDecimal) decimalFormat.parse(text);
    	return bigDecimal;
    }

    public static String removeSpecialCharacters(String input) {
        return ALNUM.retainFrom(input);
    }
    
    public static String removeAccents(String str) {
        return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }
    
    public static String removePipe(String str) {
    	if(!isNullOrEmpty(str)) {
    		return str.replaceAll("\"", "").replaceAll("\\|", "");
    	}
        return str;
    }
    
    /**
     * Gera protocolo unico
     * @param creditorLegalDocument CNPJ do credor
     * @param debtorLegalDocument CNPJ do devedor
     * @param docNumber	numero do documento
     * @param dueDateFormatted data de vencimento formatada*/
    public static String createUUIDProtocol(String creditorLegalDocument, String debtorLegalDocument, String docNumber, String dueDateFormatted){    	
    	return UUID.nameUUIDFromBytes((
    			creditorLegalDocument+
        		debtorLegalDocument+
        		docNumber+
        		dueDateFormatted).getBytes())
    			.toString();
    }
   
}
