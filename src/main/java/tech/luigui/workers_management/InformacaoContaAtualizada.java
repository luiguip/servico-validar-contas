package tech.luigui.workers_management;

import java.math.BigDecimal;

import com.opencsv.bean.CsvBindByName;

public class InformacaoContaAtualizada extends InformacaoConta {
	@CsvBindByName
	private Boolean processado;

	public InformacaoContaAtualizada() {
		super();
	}

	
	public InformacaoContaAtualizada(String agencia, String conta, BigDecimal saldo, String status, Boolean processado) {
		super(agencia, conta, saldo, status);
		this.processado = processado;
	}


	public Boolean getProcessado() {
		return processado;
	}

	public void setProcessado(Boolean processado) {
		this.processado = processado;
	}
	
	public static InformacaoContaAtualizada gerarInformacaoContaAtualizada(InformacaoConta informacaoConta, Boolean processado) {
		return new InformacaoContaAtualizada(
				informacaoConta.getAgencia(), 
				informacaoConta.getConta(), 
				informacaoConta.getSaldo(),
				informacaoConta.getStatus(), 
				processado);
	}


	@Override
	public String toString() {
		return "InformacaoContaAtualizada [processado=" + processado + ", " + super.toString() + "]";
	}
	
}
