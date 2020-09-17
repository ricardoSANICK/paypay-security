package com.nxn.sau.security;

import java.util.regex.Pattern;

public interface ErrorCodes {

	Pattern SUB_ERROR_CODE = Pattern.compile(".*data\\s([0-9a-f]{3,4}).*");
	
	int USERNAME_NOT_FOUND = 0x525;
	int INVALID_PASSWORD = 0x52e;
	int INVALID_PASSWORD_CAPTCHA = 0x52c;
	int NOT_PERMITTED = 0x530;
	int PASSWORD_EXPIRED = 0x532;
	int ACCOUNT_DISABLED = 0x533;
	int ACCOUNT_EXPIRED = 0x701;
	int PASSWORD_NEEDS_RESET = 0x773;
	int ACCOUNT_LOCKED = 0x775;
	int ACCOUNT_IS_USING = 902;
	int CONNECTION_REFUSED = 903;
	int USER_OK = 1;
	
	int INVALID_PASSWORD_RULES = 9999;
	int INVALID_PASSWORD_RULES_MIN_LEN = 99991;
	int INVALID_PASSWORD_RULES_ALFANUMERIC = 99992;
	int INVALID_PASSWORD_RULES_HIST = 99993;
	int INVALID_PASSWORD_RULES_SAME_OLD = 99994;
	int INVALID_PASSWORD_RULES_SAME_NEW = 99995;
	
	
	/**
	 * Descripción del mensaje cargados en message.properties
	 */
	String DIFF_OLD_PASSWORD = "DIFF_OLD_PASSWORD";//"La contraseña actual es incorrecta";
	String SAME_NEW_AND_OLD_PASSWORD =  "SAME_NEW_AND_OLD_PASSWORD";//"La contraseña no puede ser la misma que la anterior";
	String SAME_NEW_NEW_PASSWORD = "SAME_NEW_NEW_PASSWORD"; // "La confirmación de contraseña no coincide";
	String SAME_NEW_HIST_PASSWORD =  "SAME_NEW_HIST_PASSWORD";//"La contraseña no debe coincidir con las ultimas 5 registradas";
	String INVALID_PASSWORD_RULES_DESC = "INVALID_PASSWORD_RULES_DESC";// "La nueva contraseña no cumple con las reglas de seguridad";
	String INVALID_PASSWORD_MIN_LEN = "INVALID_PASSWORD_MIN_LEN";//"La longitud mínima de la contraseña es de 8 caracteres";
	String INVALID_PASSWORD_ALFANUMERIC = "INVALID_PASSWORD_ALFANUMERIC";//"La contraseña debe contener al menos un número y una letra";
	String STR_ACCOUNT_LOCKED = "ACCOUNT_LOCKED";
	
	/**
	 * JWT
	 */
	String UNTRUSTED_JWT = "UNTRUSTED_JWT"; // "Token no confiable"
	int	   I_UNTRUSTED_JWT = 11110000;
	String MALFORMED_JWT = "MALFORMED_JWT"; // "Token no malformado"
	int    I_MALFORMED_JWT = 11110001;
	String EXPIRED_JWT = "EXPIRED_JWT"; // "Token expirado"
	int    I_EXPIRED_JWT = 11110002;
	
}
