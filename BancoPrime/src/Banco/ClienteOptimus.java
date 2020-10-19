package Banco;

public class ClienteOptimus extends Conta {
	//construtor padr�o
	public ClienteOptimus(int idConta, int senha, String nome, String cpf, double saldo, double rendaMensal, double credito) {
		super(idConta, senha, nome, cpf, saldo, rendaMensal, credito);
	}
	//construtor default
	public ClienteOptimus() {
	}
	
	//metodo de exibi��o do empr�stimo
	public void exibirEmprestimo(){
		if(getCredito() == 0) {
			System.out.println("Limite de emprestimo disponivel:");
			setCredito(getRendaMensal() * 0.7);
			System.out.println("R$"+getCredito());
			setCredito(0);
		}
		else {
			System.out.println("N�o h� limite dispon�vel");
		}
	}
	
	//metodo de solicita��o do empr�stimo
	public void solicitarEmprestimo(){
		System.out.println("Opera��o realizada com sucesso!");
		setCredito(getRendaMensal() * 0.7);
		setSaldo(getSaldo()+getCredito());
	}
}
