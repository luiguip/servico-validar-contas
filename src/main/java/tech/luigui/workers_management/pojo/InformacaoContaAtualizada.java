package tech.luigui.workers_management.pojo;

import java.math.BigDecimal;

import com.opencsv.bean.CsvBindByName;

public class InformacaoContaAtualizada extends InformacaoConta {
	@CsvBindByName
	private Boolean processado;
	@CsvBindByName
	private Boolean erro;

	public InformacaoContaAtualizada() {
		super();
	}

	public InformacaoContaAtualizada(String agencia, String conta, BigDecimal saldo, String status, Boolean processado, Boolean erro) {
		super(agencia, conta, saldo, status);
		this.processado = processado;
		this.erro = erro;
	}

	public Boolean getProcessado() {
		return processado;
	}

	public void setProcessado(Boolean processado) {
		this.processado = processado;
	}
	
	public Boolean getErro() {
		return erro;
	}

	public void setErro(Boolean erro) {
		this.erro = erro;
	}

	public static InformacaoContaAtualizada gerarInformacaoContaAtualizada(InformacaoConta informacaoConta, Boolean processado, Boolean erro) {
		return new InformacaoContaAtualizada(
				informacaoConta.getAgencia(), 
				informacaoConta.getConta(), 
				informacaoConta.getSaldo(),
				informacaoConta.getStatus(), 
				processado,
				erro);
	}

	@Override
	public String toString() {
		return "InformacaoContaAtualizada [processado=" + processado + ", erro=" + erro + ", " + super.toString() + "]";
	}
}
