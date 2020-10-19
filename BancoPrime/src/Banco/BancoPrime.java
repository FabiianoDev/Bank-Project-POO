package Banco;

//importa as classes
import java.util.Scanner;

public class BancoPrime {
	public static void main(String[] args) {
		
		//declara as variaveis
		double valor;
		int opc, opc2, opc3, cont = 0, contaEscolhida = -1, senhaDigitada, contaTransf, emprestimo;
		boolean autorizacao = false;
		
		//inst�ncia os objetos
		Scanner entrada = new Scanner(System.in);
		Conta[] arrayConta = new Conta[10];
        Conta c = null;
        ClienteOptimus op = null;
      //escolhe se o usu�rio quer acessar o sistema
		exibePrincipal();
		opc = entrada.nextInt();
		switch(opc) {
			case 1:
				do {
					//exibe o menu do bancoe j� escolhe o caso
					exibeMenu();
					opc2 = entrada.nextInt();
					switch(opc2) {
						
						// cadastra as contas do banco
						case 1:
							if(cont<10) {
								System.out.println("");
								System.out.println("::::::Cadastrando o cliente::::::");
								
								//recebe e seta as informa��es da conta
								c = new Conta();
								c.setIdConta(cont+1);
								c.setSaldo(0);
								System.out.println("Digite o nome do titular da conta "+c.getIdConta()+": ");
								c.setNome(entrada.next());
								//verifica se o cpf tem o tamanho correto
								do {
									System.out.println("Digite o CPF do titular: ");
									c.setCpf(entrada.next());
				             			if(c.getCpf().length() != 11) {
				             				System.out.println("CPF inv�lido! digite novamente...");
				             			}
								}while(c.getCpf().length() != 11);
								System.out.println("Digite a senha num�rica da conta: ");
								c.setSenha(entrada.nextInt());
								System.out.println("Digite sua renda mensal: ");
								c.setRendaMensal(entrada.nextDouble());
								c.setCredito(0);
								
								//define se a conta ser� uma conta comum ou uma conta Optimus
								if(c.getRendaMensal() >= 10000) {
									//cria o objeto ClienteOptimus
									op = new ClienteOptimus(c.getIdConta(), c.getSenha(), c.getNome(), 
									c.getCpf(), c.getSaldo(), c.getRendaMensal(), c.getCredito());
									//atribui a posi��o do vetor o objeto ClienteOptimus 
									arrayConta[cont] = op;
								}
								else {
									//atribui a posi��o do vetor o objeto Conta
									arrayConta[cont] = c;
								}
								
								limpaConsole();
								
								System.out.println("Cadastro realizado com sucesso!");

								cont++;
							}
							//informa��o de contas preenchidas
							else {
								System.out.println("Numero de contas limite alcan�ado!");
							}
							break;
				        
				        //acessa uma conta
						case 2:
							//identifica a conta a ser acessada
							System.out.println("Qual o id da conta desejada?");
							contaEscolhida = entrada.nextInt();
							
							//cria um loop de verifica��o at� que a senha esteja correta
							do {
								System.out.println("Digite a senha da conta "+ contaEscolhida+": ");
								senhaDigitada = entrada.nextInt();
								//compara o resultado digitado com a senha cadastrada
								if(senhaDigitada == arrayConta[contaEscolhida-1].getSenha()) {
									autorizacao = true;
								}
								//imprime um resultado de erro
								else {
									System.err.println("A senha digitada est� incorreta, tente novamente.");
								}
							}while(autorizacao == false);
							
							//exibe o menu da conta
							System.out.println("\n:::Bem vindo "+ arrayConta[contaEscolhida-1].getNome()+":::\n");
							do {
								exibeMenuConta();
								opc3 = entrada.nextInt();
								
								switch(opc3){
									
									//exibe o Saldo
									case 1:
										arrayConta[contaEscolhida-1].exibirSaldo();
										break;
								
									//seleciona a op��o saque
									case 2:
										System.out.println("Insira o valor para saque");
										//captura apenas valores positivos para o saque
										do {
											valor = entrada.nextDouble();
											if(valor<0) {
												System.err.println("Saque negativo!");
												System.out.println("Digite o valor correto:");
											}
										}while(valor< 0);
										
										//chama o metodo do saque 
										arrayConta[contaEscolhida-1].sacar(valor);
										break;
										
									//metodo de transferencia
									case 3:
										System.out.println("N� da conta para a qual deseja transferir:");
										contaTransf = entrada.nextInt();
										System.out.println("Valor a transferir: ");
										valor = entrada.nextDouble();
										if(arrayConta[contaTransf-1] != null) {
											if(valor <= arrayConta[contaEscolhida-1].getSaldo() + arrayConta[contaEscolhida-1].getCredito()){
												arrayConta[contaEscolhida-1].setSaldo(arrayConta[contaEscolhida-1].getSaldo() - valor);
												arrayConta[contaTransf-1].setSaldo(arrayConta[contaTransf-1].getSaldo() + valor);											
											}
											else {
												System.out.println("O saldo � insulficiente");
											}
										}
										else {
											System.err.println("Conta n�o existente.");
										}
										break;
									//metodo de solicita��o de cr�dito
									case 4:
										//exibe o valor de empr�stimo
										arrayConta[contaEscolhida-1].exibirEmprestimo();
										if(arrayConta[contaEscolhida-1].getCredito() == 0) {
											System.out.println("Voc� gostaria de solicitar este empr�stimo?");
											System.out.println("1 - Sim || 2 - N�o");
											emprestimo = entrada.nextInt();
											if(emprestimo == 1) {
												arrayConta[contaEscolhida-1].solicitarEmprestimo();
											}
										}
										break;
									//imprime a sa�da da conta
									case 5:
										System.err.println("Voc� saiu da conta!");
										break;
								}
							}while(opc3 != 5);
							break;
						case 3:
								System.out.println("Insira a conta para deposito:");
								contaEscolhida = entrada.nextInt();
								if(arrayConta[contaEscolhida-1].getIdConta() == contaEscolhida) {
									System.out.println("Qual o valor a ser depositado?");
									do {
										valor = entrada.nextDouble();
										if(valor<0) {
											System.err.println("Valor negativo?! voc� � um decepticon? '-' ");
											System.out.println("Digite o valor correto:");
										}
									}while(valor< 0);
									arrayConta[contaEscolhida-1].depositar(valor);
								}
								else {
									System.out.println("Desculpe, essa conta n�o existe!");
									System.out.println("Digite uma conta v�lida.");
								}
						break;
						case 4:
							System.out.println("Obrigado por utilizar o banco Prime :D");
							System.exit(0);							
						break;	
					}
					}while(opc2 != 4);
					break;
				case 2:
					System.out.println("Obrigado por utilizar o banco Prime :D");
				break;
		}
	}
	
	//metodo exibi��o do primeiro menu
	static void exibePrincipal(){
		System.out.println(":::::::::::::::Bem vindo ao Banco Prime:::::::::::::::");
		System.out.println("");
		System.out.println("Voc� gostaria de utilizar o nosso sistema?");
		System.out.println("1 - Sim || 2 - N�o");
	}
	
	//metodo exibi��o meno do sistema
	static void exibeMenu(){
		System.out.println("");
		System.out.println("O que deseja fazer?");
		System.out.println("1 - Cadastrar uma conta");
		System.out.println("2 - Acessar uma conta");
		System.out.println("3 - Deposito");
		System.out.println("4 - Sair");
	}
	
	//metodo exibi��o menu da conta
	static void exibeMenuConta(){
		System.out.println("~MEU PRIME~");
		System.out.println("1 - Exibir Saldo");
		System.out.println("2 - Sacar");
		System.out.println("3 - Transferir");
		System.out.println("4 - Solicitar empr�stimo");
		System.out.println("5 - Sair da conta");
	}
	
	//metodo para limpar o console
	static void limpaConsole() {
		for(int x=0;x<100000;x++) {
		System.out.println("");
		}
	}

}
