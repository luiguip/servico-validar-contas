package tech.luigui.workers_management;

import java.math.BigDecimal;

import com.opencsv.bean.CsvBindByName;

public class InformacaoConta {
	@CsvBindByName(required = true)
	private String agencia;
	@CsvBindByName(required = true)
	private String conta;
	@CsvBindByName(required = true, locale = "pt-BR")
	private BigDecimal saldo;
	@CsvBindByName(required = true)
	private String status;

	public InformacaoConta() {
	}

	public InformacaoConta(String agencia, String conta, BigDecimal saldo, String status) {
		this.agencia = agencia;
		this.conta = conta;
		this.saldo = saldo;
		this.status = status;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getConta() {
		return conta;
	}

	public void setConta(String conta) {
		this.conta = conta;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "InformacaoConta [agencia=" + agencia + ", conta=" + conta + ", saldo=" + saldo + ", status=" + status
				+ "]";
	}
}
