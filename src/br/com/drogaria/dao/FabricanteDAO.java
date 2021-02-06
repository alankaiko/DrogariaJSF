package br.com.drogaria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.factory.ConexaoFactory;

public class FabricanteDAO {
	
	public void Salvar(Fabricante fabricante) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO fabricante");
		sql.append("(descricao) ");
		sql.append("VALUES (?)");
		
		try {
			Connection conexao = ConexaoFactory.RetornaConexao();
			
			PreparedStatement prep = conexao.prepareStatement(sql.toString());
			prep.setString(1, fabricante.getDescricao());
			prep.execute();
			conexao.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void Excluir(Fabricante fabricante) {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM fabricante ");
		sql.append("WHERE codigo = ?");
		
		try {
			Connection conexao = ConexaoFactory.RetornaConexao();
			PreparedStatement aff = conexao.prepareStatement(sql.toString());
			aff.setLong(1, fabricante.getCodigo());
			aff.execute();
			conexao.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void Editar(Fabricante fabricante) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE fabricante ");
		sql.append("SET descricao = ? ");
		sql.append("WHERE codigo = ?");
		
		try {
			Connection con = ConexaoFactory.RetornaConexao();
			PreparedStatement pre = con.prepareStatement(sql.toString());
			pre.setString(1, fabricante.getDescricao());
			pre.setLong(2, fabricante.getCodigo());
			pre.executeUpdate();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@SuppressWarnings("finally")
	public Fabricante BuscarPorId(Long codigo) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao ");
		sql.append("FROM fabricante ");
		sql.append("WHERE codigo = ?");
		
		Fabricante fab = null;
		
		try {
			Connection con = ConexaoFactory.RetornaConexao();
			PreparedStatement pre = con.prepareStatement(sql.toString());
			pre.setLong(1, codigo);
			
			ResultSet resultado = pre.executeQuery();

			if (resultado.next()) {
				fab = new Fabricante();
				fab.setCodigo(resultado.getLong("codigo"));
				fab.setDescricao(resultado.getString("descricao"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return fab;
		}
	}
	
	
	@SuppressWarnings("finally")
	public List<Fabricante> Listar(){
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao ");
		sql.append("FROM fabricante ");
		sql.append("ORDER BY descricao ASC");
		
		List<Fabricante> lista = new ArrayList<Fabricante>();
		
		try {
			Connection con = ConexaoFactory.RetornaConexao();
			PreparedStatement pre = con.prepareStatement(sql.toString());
			ResultSet re = pre.executeQuery();
			
			while(re.next()) {
				Fabricante fa = new Fabricante();
				fa.setCodigo(re.getLong("codigo"));
				fa.setDescricao(re.getString("descricao"));
				lista.add(fa);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return lista;
		}
	}
	
	@SuppressWarnings("finally")
	public List<Fabricante> BuscarPelaDescricao(String descricao){
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao ");
		sql.append("FROM fabricante ");
		sql.append("WHERE descricao LIKE ? ");
		sql.append("ORDER BY descricao ASC");

		List<Fabricante> lista = new ArrayList<Fabricante>();
		
		try {
			Connection con = ConexaoFactory.RetornaConexao();
			PreparedStatement pre = con.prepareStatement(sql.toString());
			pre.setString(1, "%" + descricao + "%");
			
			ResultSet resultado = pre.executeQuery();
			
			while(resultado.next()) {
				Fabricante fab = new Fabricante();
				fab.setCodigo(resultado.getLong("codigo"));
				fab.setDescricao(resultado.getString("descricao"));
				lista.add(fab);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return lista;
		}
	}
}
