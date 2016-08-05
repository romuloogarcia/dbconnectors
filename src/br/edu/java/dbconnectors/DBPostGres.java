package br.edu.java.dbconnectors;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Classe para conexão ao banco de dados PostGres
 * 
 * @author Rômulo Garcia
 * @since 26/03/2014
 * 
 */
public class DBPostGres {
	private static Connection conexao;

	private static Connection conecta() {
		if (conexao == null) {
			try {
				Class.forName("org.postgresql.Driver");

				conexao = DriverManager.getConnection(DBConstants.urlPostGres(), DBConstants.getUsuario(),
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

	public DBPostGres() {
		conecta();
		String sql = "SELECT ?";
		try {
			PreparedStatement pstmt = conexao.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {

			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro ao efetuar a consulta ao banco! Verifique a sintaxe da string SQL.");
		}
	}
}
