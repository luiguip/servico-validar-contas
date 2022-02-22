package tech.luigui.workers_management.pojo;

import java.math.BigDecimal;
import java.util.Objects;

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
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(erro, processado);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		InformacaoContaAtualizada other = (InformacaoContaAtualizada) obj;
		return Objects.equals(erro, other.erro) && Objects.equals(processado, other.processado);
	}

	@Override
	public String toString() {
		return "InformacaoContaAtualizada [processado=" + processado + ", erro=" + erro + ", " + super.toString() + "]";
	}
}
