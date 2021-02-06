package br.com.drogaria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.drogaria.domain.Produto;
import br.com.drogaria.factory.ConexaoFactory;

public class ProdutoDAO {
	
	public void Salvar(Produto produto) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO produto");
		sql.append("(descricao) ");
		sql.append("(quantidade) ");
		sql.append("(preco) ");
		sql.append("VALUES (?,?,?)");
		
		try {
			Connection conexao = ConexaoFactory.RetornaConexao();
			
			PreparedStatement prep = conexao.prepareStatement(sql.toString());
			prep.setString(1, produto.getDescricao());
			prep.setLong(2, produto.getQuantidade());
			prep.setDouble(3, produto.getPreco());
			prep.execute();
			conexao.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void Excluir(Produto produto) {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM produto ");
		sql.append("WHERE codigo = ?");
		
		try {
			Connection conexao = ConexaoFactory.RetornaConexao();
			PreparedStatement aff = conexao.prepareStatement(sql.toString());
			aff.setLong(1, produto.getCodigo());
			aff.execute();
			conexao.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void Editar(Produto produto) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE produto ");
		sql.append("SET descricao = ? ");
		sql.append("SET quantidade = ? ");
		sql.append("SET preco = ? ");
		sql.append("WHERE codigo = ?");
		
		try {
			Connection con = ConexaoFactory.RetornaConexao();
			PreparedStatement pre = con.prepareStatement(sql.toString());
			pre.setString(1, produto.getDescricao());
			pre.setLong(2, produto.getQuantidade());
			pre.setDouble(3, produto.getPreco());
			pre.executeUpdate();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("finally")
	public Produto BuscarPorId(Long codigo) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao, quantidade, preco ");
		sql.append("FROM produto ");
		sql.append("WHERE codigo = ?");
		
		Produto produto = null;
		
		try {
			Connection con = ConexaoFactory.RetornaConexao();
			PreparedStatement pre = con.prepareStatement(sql.toString());
			pre.setLong(1, codigo);
			
			ResultSet resultado = pre.executeQuery();

			if (resultado.next()) {
				produto = new Produto();
				produto.setCodigo(resultado.getLong("codigo"));
				produto.setDescricao(resultado.getString("descricao"));
				produto.setQuantidade(resultado.getLong("quantidade"));
				produto.setPreco(resultado.getDouble("preco"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return produto;
		}
	}
	
	@SuppressWarnings("finally")
	public List<Produto> Listar(){
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao, quantidade, preco ");
		sql.append("FROM produto ");
		sql.append("ORDER BY descricao ASC");
		
		List<Produto> lista = new ArrayList<Produto>();
		
		try {
			Connection con = ConexaoFactory.RetornaConexao();
			PreparedStatement pre = con.prepareStatement(sql.toString());
			ResultSet re = pre.executeQuery();
			
			while(re.next()) {
				Produto produto = new Produto();
				produto.setCodigo(re.getLong("codigo"));
				produto.setDescricao(re.getString("descricao"));
				produto.setQuantidade(re.getLong("quantidade"));
				produto.setPreco(re.getDouble("preco"));
				lista.add(produto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return lista;
		}
	}
	
	@SuppressWarnings("finally")
	public List<Produto> BuscarPelaDescricao(String descricao){
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao, quantidade, preco ");
		sql.append("FROM produto ");
		sql.append("WHERE descricao LIKE ? ");
		sql.append("ORDER BY descricao ASC");

		List<Produto> lista = new ArrayList<Produto>();
		
		try {
			Connection con = ConexaoFactory.RetornaConexao();
			PreparedStatement pre = con.prepareStatement(sql.toString());
			pre.setString(1, "%" + descricao + "%");
			
			ResultSet resultado = pre.executeQuery();
			
			while(resultado.next()) {
				Produto produto = new Produto();
				produto.setCodigo(resultado.getLong("codigo"));
				produto.setDescricao(resultado.getString("descricao"));
				produto.setQuantidade(resultado.getLong("quantidade"));
				produto.setPreco(resultado.getDouble("preco"));
				lista.add(produto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return lista;
		}
	}
}
