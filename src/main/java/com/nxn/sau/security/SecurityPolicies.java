package com.nxn.sau.security;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.nxn.sau.security.DTO.SecurityDTO;
import com.nxn.sau.security.util.PasswordGenerator;
import com.nxn.sau.security.util.Properties;


@SuppressWarnings("unused")
public class SecurityPolicies {
	
	private final Properties props = new Properties();
	
	private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	private final String file = "/security-policies.properties";
		
	private Integer prop_numintentosfallidos;
	private Integer prop_numintentosfallidoscaptcha;
	private Integer prop_dias_vencimiento_usuario_externo;
	private Integer prop_dias_vencimiento_usuario_interno;
	private Integer prop_dias_vencimiento_password_externo;
	private Integer prop_dias_vencimiento_password_interno;
	private Integer prop_longitud_minima_password;
	
	HashMap<String, String> mayusminusMap = new HashMap<String, String>();
	HashMap<String, String> numberMap = new HashMap<String, String>();
					
	public SecurityPolicies(){
		super();

		this.prop_numintentosfallidos = Integer.parseInt(props.getProperties(file).getProperty("numero.intentos.fallidos"));
		this.prop_numintentosfallidoscaptcha = Integer.parseInt(props.getProperties(file).getProperty("numero.intentos.fallidos.captcha"));
		this.prop_dias_vencimiento_usuario_externo = Integer.parseInt(props.getProperties(file).getProperty("dias.vencimiento.usuario.externo"));
		this.prop_dias_vencimiento_usuario_interno = Integer.parseInt(props.getProperties(file).getProperty("dias.vencimiento.usuario.interno"));
		this.prop_dias_vencimiento_password_externo = Integer.parseInt(props.getProperties(file).getProperty("dias.vencimiento.password.externo"));
		this.prop_dias_vencimiento_password_interno = Integer.parseInt(props.getProperties(file).getProperty("dias.vencimiento.password.interno"));
		this.prop_longitud_minima_password = Integer.parseInt(props.getProperties(file).getProperty("longitud.minima.password"));
		
		
		for(String str : PasswordGenerator.mayusminus){
			mayusminusMap.put(str, str);
		}
		for(String str : PasswordGenerator.numbers){
			numberMap.put(str, str);
		}
				
	}
	
	
	public int validatePasswordPoliciesNew(SecurityDTO securityDTO, boolean isPasswordEncoded){
		
		int res = ErrorCodes.USER_OK;
		
		boolean mayusminus = false;
		boolean number = false;
		
		String password = securityDTO.getDtopassword();
		
		if(isPasswordEncoded ? !passwordEncoder.matches(securityDTO.getPassword(), securityDTO.getCurrentpassword()) : false)
		{
			return ErrorCodes.INVALID_PASSWORD;
		}
		
		if(passwordEncoder.matches(securityDTO.getNewpassword(), securityDTO.getCurrentpassword()))
		{
			return ErrorCodes.INVALID_PASSWORD_RULES_SAME_OLD;
		}

		if(securityDTO.getNewpassword().compareTo(securityDTO.getConfirmpassword()) != 0){
			return ErrorCodes.INVALID_PASSWORD_RULES_SAME_NEW;
		}	
		
		if(password.length() < prop_longitud_minima_password){
			return ErrorCodes.INVALID_PASSWORD_RULES_MIN_LEN;
		}
		
		for (int x=0;x<password.length();x++){
			String str = password.charAt(x)+"";
			if(mayusminusMap.get(str) != null){
				mayusminus = true;
			}
			if(numberMap.get(str) != null){
				number = true;
			}
		}

		if(!mayusminus || !number){
			return ErrorCodes.INVALID_PASSWORD_RULES_ALFANUMERIC;
		}
		
		/**
		 * REVISIÓN CONTRA HISTÓRICOS
		 */
		
		if(passwordEncoder.matches(securityDTO.getNewpassword(), securityDTO.getPasswordhist1()))
		{
			return ErrorCodes.INVALID_PASSWORD_RULES_HIST;
		}
		
		if(passwordEncoder.matches(securityDTO.getNewpassword(), securityDTO.getPasswordhist2())) 
		{
			return ErrorCodes.INVALID_PASSWORD_RULES_HIST;
		}
		
		if(passwordEncoder.matches(securityDTO.getNewpassword(), securityDTO.getPasswordhist3())) 
		{
			return ErrorCodes.INVALID_PASSWORD_RULES_HIST;
		}
		
		if(passwordEncoder.matches(securityDTO.getNewpassword(), securityDTO.getPasswordhist4())) 
		{
			return ErrorCodes.INVALID_PASSWORD_RULES_HIST;
		}
		
		if(passwordEncoder.matches(securityDTO.getNewpassword(), securityDTO.getPasswordhist5())) 
		{
			return ErrorCodes.INVALID_PASSWORD_RULES_HIST;
		}
			
		
		return res;
	}
	
	
	public int validatePasswordPolicies(SecurityDTO securityDTO){
		
		int res = ErrorCodes.USER_OK;
		
		boolean mayusminus = false;
		boolean number = false;
		
		String password = securityDTO.getDtopassword();
		
		if(securityDTO.getCurrentpassword().compareTo(securityDTO.getPassword()) != 0){
			return ErrorCodes.INVALID_PASSWORD;
		}
		
		if(securityDTO.getCurrentpassword().compareTo(securityDTO.getNewpassword()) == 0){
			return ErrorCodes.INVALID_PASSWORD_RULES_SAME_OLD;
		}
		
		if(securityDTO.getNewpassword().compareTo(securityDTO.getConfirmpassword()) != 0){
			return ErrorCodes.INVALID_PASSWORD_RULES_SAME_NEW;
		}
		
		if(password.length() < prop_longitud_minima_password){
			return ErrorCodes.INVALID_PASSWORD_RULES_MIN_LEN;
		}
		
		for (int x=0;x<password.length();x++){
			String str = password.charAt(x)+"";
			if(mayusminusMap.get(str) != null){
				mayusminus = true;
			}
			if(numberMap.get(str) != null){
				number = true;
			}
		}

		if(!mayusminus || !number){
			return ErrorCodes.INVALID_PASSWORD_RULES_ALFANUMERIC;
		}
					
		if(securityDTO.getNewpassword().compareTo(securityDTO.getPasswordhist1()) == 0){
			return ErrorCodes.INVALID_PASSWORD_RULES_HIST;
		}
		
		if(securityDTO.getNewpassword().compareTo(securityDTO.getPasswordhist2()) == 0){
			return ErrorCodes.INVALID_PASSWORD_RULES_HIST;
		}
		
		if(securityDTO.getNewpassword().compareTo(securityDTO.getPasswordhist3()) == 0){
			return ErrorCodes.INVALID_PASSWORD_RULES_HIST;
		}
		
		if(securityDTO.getNewpassword().compareTo(securityDTO.getPasswordhist4()) == 0){
			return ErrorCodes.INVALID_PASSWORD_RULES_HIST;
		}
		
		if(securityDTO.getNewpassword().compareTo(securityDTO.getPasswordhist5()) == 0){
			return ErrorCodes.INVALID_PASSWORD_RULES_HIST;
		}
			
		
		return res;
	}
	
	public int validateUserPolicies(SecurityDTO securityDTO){
		return validateUserPoliciesExt(securityDTO, 0);
	}
	
	public int validateUserPolicies(SecurityDTO securityDTO, int handler){
		return validateUserPoliciesExt(securityDTO, handler);
	}
	
	public int validateUserPoliciesExt(SecurityDTO securityDTO, int handler)
	{
		int res = ErrorCodes.USER_OK;
		
		if(securityDTO.getNumintentosfallidos() != null){
			if(securityDTO.getNumintentosfallidos() >= this.prop_numintentosfallidos){
				return ErrorCodes.ACCOUNT_LOCKED;
			}
			if(securityDTO.getNumintentosfallidos() >= this.prop_numintentosfallidoscaptcha && handler == 1){
				return ErrorCodes.INVALID_PASSWORD_CAPTCHA;
			}
		}
		
		if(securityDTO.getTipoUsuario() != null){
			if(securityDTO.getTipoUsuario().toLowerCase().compareTo("interno") == 0){
				
				if(securityDTO.getUltimo_acceso_interno() != null){
					if(securityDTO.getUltimo_acceso_interno() >= this.prop_dias_vencimiento_usuario_interno){
						return ErrorCodes.ACCOUNT_EXPIRED;
					}
				}
				
				if(securityDTO.getUltimo_cambio_password_interno() != null){
					if(securityDTO.getUltimo_cambio_password_interno() >= this.prop_dias_vencimiento_password_interno){
						return ErrorCodes.PASSWORD_EXPIRED;
					}
				}
				
			} else if(securityDTO.getTipoUsuario().toLowerCase().compareTo("externo") == 0){
				if(securityDTO.getUltimo_acceso_externo() != null){ 
					if(securityDTO.getUltimo_acceso_externo() >= this.prop_dias_vencimiento_usuario_externo){
						return ErrorCodes.ACCOUNT_EXPIRED;
					}
				}
				if(securityDTO.getUltimo_cambio_password_externo() != null){
					if(securityDTO.getUltimo_cambio_password_externo() >= this.prop_dias_vencimiento_password_externo){
						return ErrorCodes.PASSWORD_EXPIRED;
					}
				}
			}
		}
		
		return res;

	}
	
	public Date getFechaVencimiento(Date fechaUltimoCambio, String tipoUsuario){
		
		Date fechaVencimiento = null;
		int diasSumar= 0;
		Calendar cal = Calendar.getInstance();
		String dateFormat = "dd/MM/yyyy";
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		
		switch(tipoUsuario.toLowerCase()){
		
			case "interno":
				diasSumar=prop_dias_vencimiento_password_interno;
				break;
			case "externo":
				diasSumar=prop_dias_vencimiento_password_externo;
				break;
		}
		
		try {
			cal.setTime(fechaUltimoCambio);
			cal.add(Calendar.DATE, diasSumar);
			fechaVencimiento = cal.getTime();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return fechaVencimiento;
	}


	public Integer getProp_dias_vencimiento_usuario_externo() {
		return prop_dias_vencimiento_usuario_externo;
	}

	public Integer getProp_dias_vencimiento_usuario_interno() {
		return prop_dias_vencimiento_usuario_interno;
	}

	public Integer getProp_dias_vencimiento_password_externo() {
		return prop_dias_vencimiento_password_externo;
	}

	public Integer getProp_dias_vencimiento_password_interno() {
		return prop_dias_vencimiento_password_interno;
	}

		
}
