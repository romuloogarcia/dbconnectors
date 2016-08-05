package br.edu.java.dbconnectors;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Classe para conexão ao banco de dados Oracle
 * 
 * @author Rômulo Garcia
 * @since 26/03/2014
 * 
 */
public class DBOracle {
	private static Connection conexao;

	private static Connection conecta() {
		if (conexao == null) {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");

				conexao = DriverManager.getConnection(DBConstants.urlOracle(), DBConstants.getUsuario(),
						DBConstants.getSenha());

				System.out.println("Conectado ao banco!");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				System.out.println("Driver de conexão com o banco não encontrado!");
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Falha ao se conectar com o banco de dados! Usuário e/ou senha incorretos!");
			}
		}

		return conexao;

	}

	public DBOracle() {
		conecta();
		String sql = "SELECT ?";
		try {
			PreparedStatement psmt = conexao.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();
			if (rs.next()) {

			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro ao efetuar a consulta ao banco! Verifique a sintaxe da string SQL.");
		}
	}
}
