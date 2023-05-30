package application;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import entities.Aluno;
import jdbc.AlunoJDBC;

public class Programa {

	public static void main(String[] args) {
		
        try {
        	
            int opcao = 0;
            Scanner console = new Scanner(System.in);
            
            do {
                System.out.print("######## Menu ########"
                                 + "\n1- Cadastrar"
                                 + "\n2- Listar"
                                 + "\n3- Alterar"
                                 + "\n4- Excluir"
                                 + "\n5- Sair");
                System.out.print("\n\tOpção: ");
                opcao = Integer.parseInt(console.nextLine());
                System.out.println("\n\n\n\n");
                
                if (opcao == 1){
                    
                    Aluno a = new Aluno();
                    AlunoJDBC acao = new AlunoJDBC();
                    
                    System.out.print("\n*** Cadastrar Aluno ***\n\r");
                    System.out.print("Id: ");
                    a.setId(Integer.parseInt(console.nextLine()));
                    System.out.print("Nome: ");
                    a.setNome(console.nextLine());
                    System.out.print("Sexo: ");
                    a.setSexo(console.nextLine());
                    System.out.print("Data de nascimento: ");
                    a.setDt_nasc( new Date(console.nextLine()) );
                    
                    acao.salvar(a);
                    console.nextLine();
                    System.out.println("\n\n\n\n");
                }if (opcao == 2) {
                	System.out.print("\n*** Listar Alunos ***\n\r");
                	
                	List<Aluno> alunos = new ArrayList<Aluno>();
                    AlunoJDBC acao = new AlunoJDBC();
                    
                    alunos = acao.listar();
                    
                    
                    for(Aluno aluno : alunos) {
                    	System.out.println("Id: " + aluno.getId());
            			System.out.println("Nome: " + aluno.getNome());
            			System.out.println("Sexo: " + aluno.getSexo());
            			System.out.println("Data de Nascimento: " + aluno.getDt_nasc() + "\n");
                    }
                	
                }if (opcao == 3) {
                	
                	System.out.print("\n*** Alterar Aluno ***\n\r");
                	
                	String nome;
                	int id;
                	AlunoJDBC acao = new AlunoJDBC();
                	
                	System.out.println("Digite o id do aluno que deseja alterar o nome:");
                	id = Integer.parseInt(console.nextLine());
                	
                	System.out.println("Digite o novo nome do aluno:");
                	nome = console.nextLine();
                	
                	acao.alterar(id, nome);
                		
                	
                }if(opcao == 4) {
                	int id;
                	AlunoJDBC acao = new AlunoJDBC();
                	
                	System.out.print("\n*** Excluir Aluno ***\n\r");
                	System.out.println("Id do Aluno a ser excluído:");
                	id = Integer.parseInt(console.nextLine());
                	
                	acao.apagar(id);
                	console.nextLine();
                    System.out.println("\n\n\n\n");	
                }
            } while(opcao != 5);
        } catch (Exception e){
            System.out.println("Erro: " + e);
        }
		
		
	}
}
