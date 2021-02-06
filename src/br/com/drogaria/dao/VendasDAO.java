package br.com.drogaria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.drogaria.domain.Vendas;
import br.com.drogaria.factory.ConexaoFactory;

public class VendasDAO {
	
	public void Salvar(Vendas vendas) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO vendas");
		sql.append("(horario) ");
		sql.append("(venda_valor_total) ");
		sql.append("VALUES (?, ?)");
		
		try {
			Connection conexao = ConexaoFactory.RetornaConexao();
			
			PreparedStatement prep = conexao.prepareStatement(sql.toString());
			prep.setDate(1, (java.sql.Date) vendas.getHorario());
			prep.setDouble(2, vendas.getVenda_valor_total());
			prep.execute();
			conexao.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void Excluir(Vendas vendas) {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM vendas ");
		sql.append("WHERE codigo = ?");
		
		try {
			Connection conexao = ConexaoFactory.RetornaConexao();
			PreparedStatement aff = conexao.prepareStatement(sql.toString());
			aff.setLong(1, vendas.getCodigo());
			aff.execute();
			conexao.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void Editar(Vendas vendas) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE vendas ");
		sql.append("SET horario = ? ");
		sql.append("SET venda_valor_total = ? ");
		sql.append("WHERE codigo = ?");
		
		try {
			Connection con = ConexaoFactory.RetornaConexao();
			PreparedStatement pre = con.prepareStatement(sql.toString());
			pre.setDate(1, (java.sql.Date) vendas.getHorario());
			pre.setDouble(2, vendas.getVenda_valor_total());
			pre.executeUpdate();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("finally")
	public Vendas BuscarPorId(Long codigo) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, horario, venda_valor_total");
		sql.append("FROM vendas ");
		sql.append("WHERE codigo = ?");
		
		Vendas vendas = null;
		
		try {
			Connection con = ConexaoFactory.RetornaConexao();
			PreparedStatement pre = con.prepareStatement(sql.toString());
			pre.setLong(1, codigo);
			
			ResultSet resultado = pre.executeQuery();

			if (resultado.next()) {
				vendas = new Vendas();
				vendas.setCodigo(resultado.getLong("codigo"));
				vendas.setHorario(resultado.getDate("horario"));
				vendas.setVenda_valor_total(resultado.getDouble("venda_valor_total"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return vendas;
		}
	}
	
	
	@SuppressWarnings("finally")
	public List<Vendas> Listar(){
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, horario, venda_valor_total");
		sql.append("FROM vendas ");
		
		List<Vendas> lista = new ArrayList<Vendas>();
		
		try {
			Connection con = ConexaoFactory.RetornaConexao();
			PreparedStatement pre = con.prepareStatement(sql.toString());
			ResultSet re = pre.executeQuery();
			
			while(re.next()) {
				Vendas vendas = new Vendas();
				vendas.setCodigo(re.getLong("codigo"));
				vendas.setHorario(re.getDate("horario"));
				vendas.setVenda_valor_total(re.getDouble("venda_valor_total"));
				lista.add(vendas);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return lista;
		}
	}
	
}
