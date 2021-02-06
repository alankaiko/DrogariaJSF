package br.com.drogaria.domain;

import java.util.Date;

public class Vendas {
	private Long codigo;
	private Date horario;
	private Double venda_valor_total;
	private Funcionario funcionario;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Date getHorario() {
		return horario;
	}

	public void setHorario(Date horario) {
		this.horario = horario;
	}

	public Double getVenda_valor_total() {
		return venda_valor_total;
	}

	public void setVenda_valor_total(Double venda_valor_total) {
		this.venda_valor_total = venda_valor_total;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

}
