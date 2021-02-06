package br.com.drogaria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.drogaria.domain.Funcionario;
import br.com.drogaria.factory.ConexaoFactory;

public class FuncionarioDAO {

	public void Salvar(Funcionario funcionario) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO funcionario");
		sql.append("(nome, cpf, senha, funcao) ");
		sql.append("VALUES (?,?,?,?)");
		
		try {
			Connection conexao = ConexaoFactory.RetornaConexao();
			
			PreparedStatement prep = conexao.prepareStatement(sql.toString());
			prep.setString(1, funcionario.getNome());
			prep.setString(2, funcionario.getCpf());
			prep.setString(3, funcionario.getSenha());
			prep.setString(4, funcionario.getFuncao());
			prep.execute();
			conexao.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void Excluir(Funcionario funcionario) {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM funcionario ");
		sql.append("WHERE codigo = ?");
		
		try {
			Connection conexao = ConexaoFactory.RetornaConexao();
			PreparedStatement aff = conexao.prepareStatement(sql.toString());
			aff.setLong(1, funcionario.getCodigo());
			aff.execute();
			conexao.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void Editar(Funcionario funcionario) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE funcionario ");
		sql.append("SET nome = ? ");
		sql.append("SET cpf = ? ");
		sql.append("SET senha = ? ");
		sql.append("SET funcao = ? ");
		sql.append("WHERE codigo = ?");
		
		try {
			Connection con = ConexaoFactory.RetornaConexao();
			PreparedStatement pre = con.prepareStatement(sql.toString());
			pre.setString(1, funcionario.getNome());
			pre.setString(2, funcionario.getCpf());
			pre.setString(3, funcionario.getSenha());
			pre.setString(4, funcionario.getFuncao());
			pre.executeUpdate();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("finally")
	public Funcionario BuscarPorId(Long codigo) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, nome, cpf, senha, funcao ");
		sql.append("FROM funcionario ");
		sql.append("WHERE codigo = ?");
		
		Funcionario funcionario = null;
		
		try {
			Connection con = ConexaoFactory.RetornaConexao();
			PreparedStatement pre = con.prepareStatement(sql.toString());
			pre.setLong(1, codigo);
			
			ResultSet resultado = pre.executeQuery();

			if (resultado.next()) {
				funcionario = new Funcionario();
				funcionario.setCodigo(resultado.getLong("codigo"));
				funcionario.setNome(resultado.getString("nome"));
				funcionario.setCpf(resultado.getString("cpf"));
				funcionario.setSenha(resultado.getString("senha"));
				funcionario.setFuncao(resultado.getString("funcao"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return funcionario;
		}
	}
	
	@SuppressWarnings("finally")
	public List<Funcionario> Listar(){
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, nome, cpf, senha, funcao ");
		sql.append("FROM funcionario ");
		
		List<Funcionario> lista = new ArrayList<Funcionario>();
		
		try {
			Connection con = ConexaoFactory.RetornaConexao();
			PreparedStatement pre = con.prepareStatement(sql.toString());
			ResultSet re = pre.executeQuery();
			
			while(re.next()) {
				Funcionario funcionario = new Funcionario();
				funcionario.setCodigo(re.getLong("codigo"));
				funcionario.setNome(re.getString("nome"));
				funcionario.setCpf(re.getString("cpf"));
				funcionario.setSenha(re.getString("senha"));
				funcionario.setFuncao(re.getString("funcao"));
				lista.add(funcionario);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return lista;
		}
	}
	
	@SuppressWarnings("finally")
	public List<Funcionario> BuscarPelaDescricao(String nome){
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, nome, cpf, senha, funcao ");
		sql.append("FROM funcionario ");
		sql.append("WHERE nome LIKE ? ");
		sql.append("ORDER BY nome ASC");

		List<Funcionario> lista = new ArrayList<Funcionario>();
		
		try {
			Connection con = ConexaoFactory.RetornaConexao();
			PreparedStatement pre = con.prepareStatement(sql.toString());
			pre.setString(1, "%" + nome + "%");
			
			ResultSet resultado = pre.executeQuery();
			
			while(resultado.next()) {
				Funcionario funcionario = new Funcionario();
				funcionario.setCodigo(resultado.getLong("codigo"));
				funcionario.setNome(resultado.getString("nome"));
				funcionario.setCpf(resultado.getString("cpf"));
				funcionario.setSenha(resultado.getString("senha"));
				funcionario.setFuncao(resultado.getString("funcao"));
				lista.add(funcionario);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return lista;
		}
	}
}

