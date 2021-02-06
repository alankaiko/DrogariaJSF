package br.com.drogaria.factory;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexaoFactory {
	private static final String USUARIO = "root";
	private static final String SENHA = "123456";
	private static final String  URL = "jdbc:mysql://localhost:3306/drogaria?useTimezone=true&serverTimezone=UTC&useSSL=false";
	
	public static Connection RetornaConexao() {
		
		try {
			return DriverManager.getConnection(URL, USUARIO, SENHA);
		} catch (Exception e) {
			System.out.println("ERRO NA CLASSE CONEXAOFACTORY no package br.com.drogaria.factory");
			e.printStackTrace();
			return null;
		}
	}


}