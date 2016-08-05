package br.edu.java.dbconnectors;

/**
 * Classe com as constantes necessárias para conexão.
 * 
 * @author Rômulo Garcia
 * @since 26/03/2014
 * 
 */
public class DBConstants {
	private static String HOST;
	private static Integer PORTA;
	private static String USUARIO;
	private static String SENHA;
	private static String BASE;

	public static String getHost() {
		return HOST;
	}

	public static void setHost(String host) {
		DBConstants.HOST = host;
	}

	public static Integer getPorta() {
		return PORTA;
	}

	public static void setPorta(Integer porta) {
		DBConstants.PORTA = porta;
	}

	public static String getUsuario() {
		return USUARIO;
	}

	public static void setUsuario(String usuario) {
		DBConstants.USUARIO = usuario;
	}

	public static String getSenha() {
		return SENHA;
	}

	public static void setSenha(String senha) {
		DBConstants.SENHA = senha;
	}

	public static String getBase() {
		return BASE;
	}

	public static void setBase(String base) {
		DBConstants.BASE = base;
	}

	public static String urlMySQL() {
		return "jdbc:mysql://" + getHost() + ":" + getPorta() + "/" + getBase();
	}

	public static String urlOracle() {
		return "jdbc:oracle:thin:@//" + getHost() + ":" + getPorta() + "/" + getBase();
	}

	public static String urlPostGres() {
		return "jdbc:postgresql://" + getHost() + ":" + getPorta() + "/" + getBase();
	}

	public static String urlFirebird() {
		return "jdbc:firebirdsql:" + getHost() + "/" + getPorta() + ":" + getBase();
	}

}
