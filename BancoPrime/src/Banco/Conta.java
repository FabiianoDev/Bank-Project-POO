package Banco;

public class Conta {
	private int idConta;
	private int senha;
	private String nome;
	private String cpf;
	private double saldo;
	private double rendaMensal;
	private double credito;
	
	//construtor padrão
	public Conta(int idConta, int senha, String nome, String cpf, double saldo, double rendaMensal, double credito) {
		this.idConta = idConta;
		this.senha = senha;
		this.nome = nome;
		this.cpf = cpf;
		this.saldo = saldo;
		this.rendaMensal = rendaMensal;
		this.credito = credito;
	}
	
	//construtor default
	public Conta() {
	}
	
	//getters e setters
	public int getIdConta() {
		return idConta;
	}
	public void setIdConta(int idConta) {
		this.idConta = idConta;
	}
	public int getSenha() {
		return senha;
	}
	public void setSenha(int senha) {
		this.senha = senha;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public double getSaldo() {
		return saldo;
	}
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	public double getRendaMensal() {
		return rendaMensal;
	}
	public void setRendaMensal(double rendaMensal) {
		this.rendaMensal = rendaMensal;
	}
	public double getCredito() {
		return credito;
	}
	public void setCredito(double credito) {
		this.credito = credito;
	}

	//metodo exibição do saldo
	public void exibirSaldo(){
		System.out.println("Saldo atual: R$" + saldo);
	}
	
	//metodo de saque
	public void sacar(double valor){
		if(saldo+credito >= valor){
			saldo -= valor;
			System.out.println("Sacado: R$" + valor);
			System.out.println("Novo saldo: R$" + saldo + "\n");
		} 
		else {
			System.out.println("Saldo insuficiente. Faça um depósito\n");
		}
	}
	
	//metodo de exibição do empréstimo
	public void exibirEmprestimo(){
		if(credito == 0) {
		System.out.println("Limite de emprestimo disponivel:");
		credito = rendaMensal * 0.5;
		System.out.println("R$"+credito);
		credito = 0;
		}
		else {
			System.out.println("Não há limite disponível");
			System.out.println("");
		}
	}
	
	//metodo de solicitação do empréstimo
	public void solicitarEmprestimo(){
		System.out.println("Operação realizada com sucesso!");
		credito = rendaMensal * 0.5;
		saldo = saldo+credito;
	}
		
	//metodo de deposito
	public void depositar(double valor) {
        saldo += valor;
        System.out.println("Depositado: R$" + valor);
        System.out.println("");
	}
		
}
