package br.edu.java.dbconnectors;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Classe para conexão ao banco de dados Firebird
 * 
 * @author Rômulo Garcia
 * @since 26/03/2014
 * 
 */
public class BDFirebird {
	private static Connection conexao;

	private static Connection conecta() {
		if (conexao == null) {
			try {
				Class.forName("org.firebirdsql.jdbc.FBDriver");

				conexao = DriverManager.getConnection(DBConstants.urlFirebird());

				System.out.println("Conectado ao banco!");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				System.out.println("Driver de conexão com o banco não encontrado!");
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Falha ao se conectar com o banco de dados! Arquivo .fdb não encontrado!");
			}
		}

		return conexao;

	}

	public BDFirebird() {
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
