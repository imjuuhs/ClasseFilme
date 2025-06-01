import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        FilmeService service = new FilmeService();
        int opcao;

        do {
            System.out.println("1. Criar novo filme");
            System.out.println("2. Listar todos os filmes");
            System.out.println("3. Buscar filme por ID");
            System.out.println("4. Editar filme");
            System.out.println("5. Excluir filme");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine(); // limpar buffer

            switch (opcao) {
                case 1:
                    service.criarFilme();
                    break;
                case 2:
                    service.listarFilmes();
                    break;
                case 3:
                    service.buscarFilme();
                    break;
                case 4:
                    service.editarFilme();
                    break;
                case 5:
                    service.excluirFilme();
                    break;
                case 6:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 6);

        sc.close();
    }
}
