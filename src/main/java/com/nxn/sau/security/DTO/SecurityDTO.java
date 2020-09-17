package com.nxn.sau.security.DTO;

import java.util.ArrayList;
import java.util.Date;


public class SecurityDTO implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6491564669318992174L;
	
	private String username;
	private String password;
	private String dtopassword;
	private String currentpassword;
	private String newpassword;
	private String confirmpassword;
	
	private String passwordhist1;
	private String passwordhist2;
	private String passwordhist3;
	private String passwordhist4;
	private String passwordhist5;
	
	private Integer numintentosfallidos;
	private Integer ultimo_acceso_interno;
	private Integer ultimo_acceso_externo;
	private Integer ultimo_cambio_password_interno;
	private Integer ultimo_cambio_password_externo;
	private Integer longitud_password;
	
	private String tipoUsuario;
			
	private String tokenAnterior;
	private Date tokenVencimiento;
	
	private ArrayList<String> roles;
	
	private String usuariomodificacion;

	
	public SecurityDTO(){}
	
	/**
	 * 
	 * @param username
	 * @param password
	 * @param newpassword
	 * @param confirmpassword
	 * @param tokenAnterior
	 * @param tokenVencimiento
	 * @param roles
	 */
	public SecurityDTO(String username, String password, String newpassword,
			String confirmpassword, String tokenAnterior, Date tokenVencimiento, ArrayList<String> roles) {
		super();
		this.username = username;
		this.password = password;
		this.newpassword = newpassword;
		this.confirmpassword = confirmpassword;
		this.tokenAnterior = tokenAnterior;
		this.tokenVencimiento = tokenVencimiento;
		this.roles = roles;
	}
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNewpassword() {
		return newpassword;
	}
	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}
	public String getConfirmpassword() {
		return confirmpassword;
	}
	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}
	public String getPasswordhist1() {
		return passwordhist1;
	}
	public void setPasswordhist1(String passwordhist1) {
		this.passwordhist1 = passwordhist1;
	}
	public String getPasswordhist2() {
		return passwordhist2;
	}
	public void setPasswordhist2(String passwordhist2) {
		this.passwordhist2 = passwordhist2;
	}

	public String getPasswordhist3() {
		return passwordhist3;
	}

	public void setPasswordhist3(String passwordhist3) {
		this.passwordhist3 = passwordhist3;
	}

	public String getPasswordhist4() {
		return passwordhist4;
	}

	public void setPasswordhist4(String passwordhist4) {
		this.passwordhist4 = passwordhist4;
	}

	public String getPasswordhist5() {
		return passwordhist5;
	}

	public void setPasswordhist5(String passwordhist5) {
		this.passwordhist5 = passwordhist5;
	}

	public ArrayList<String> getRoles() {
		return roles;
	}
	public void setRoles(ArrayList<String> roles) {
		this.roles = roles;
	}
	public String getDtopassword() {
		return dtopassword;
	}
	public void setDtopassword(String dtopassword) {
		this.dtopassword = dtopassword;
	}
	public String getCurrentpassword() {
		return currentpassword;
	}
	public void setCurrentpassword(String currentpassword) {
		this.currentpassword = currentpassword;
	}
	public Integer getNumintentosfallidos() {
		return numintentosfallidos;
	}
	public void setNumintentosfallidos(Integer numintentosfallidos) {
		this.numintentosfallidos = numintentosfallidos;
	}

	public Integer getUltimo_acceso_interno() {
		return ultimo_acceso_interno;
	}

	public void setUltimo_acceso_interno(Integer ultimo_acceso_interno) {
		this.ultimo_acceso_interno = ultimo_acceso_interno;
	}

	public Integer getUltimo_acceso_externo() {
		return ultimo_acceso_externo;
	}

	public void setUltimo_acceso_externo(Integer ultimo_acceso_externo) {
		this.ultimo_acceso_externo = ultimo_acceso_externo;
	}

	public Integer getUltimo_cambio_password_interno() {
		return ultimo_cambio_password_interno;
	}

	public void setUltimo_cambio_password_interno(
			Integer ultimo_cambio_password_interno) {
		this.ultimo_cambio_password_interno = ultimo_cambio_password_interno;
	}

	public Integer getUltimo_cambio_password_externo() {
		return ultimo_cambio_password_externo;
	}

	public void setUltimo_cambio_password_externo(
			Integer ultimo_cambio_password_externo) {
		this.ultimo_cambio_password_externo = ultimo_cambio_password_externo;
	}

	public Integer getLongitud_password() {
		return longitud_password;
	}

	public void setLongitud_password(Integer longitud_password) {
		this.longitud_password = longitud_password;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public String getTokenAnterior() {
		return tokenAnterior;
	}

	public void setTokenAnterior(String tokenAnterior) {
		this.tokenAnterior = tokenAnterior;
	}

	public Date getTokenVencimiento() {
		return tokenVencimiento;
	}

	public void setTokenVencimiento(Date tokenVencimiento) {
		this.tokenVencimiento = tokenVencimiento;
	}

	public String getUsuariomodificacion() {
		return usuariomodificacion;
	}

	public void setUsuariomodificacion(String usuariomodificacion) {
		this.usuariomodificacion = usuariomodificacion;
	}	
	
}
