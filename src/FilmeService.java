import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FilmeService {
    private List<Filme> listaFilmes = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);
    private static final String CAMINHO_ARQUIVO = "filmes.txt";

    public FilmeService() {
        carregarFilmesDoArquivo();
    }

    // método para criar filme
    public void criarFilme() {
        try {
            System.out.println("=== Cadastro de Filme ===");

            System.out.print("ID: ");
            int id = Integer.parseInt(sc.nextLine());

            System.out.print("Título: ");
            String titulo = sc.nextLine();

            System.out.print("Gênero: ");
            String genero = sc.nextLine();

            System.out.print("Duração (em minutos): ");
            int duracaoMinutos = Integer.parseInt(sc.nextLine());

            System.out.print("Diretor: ");
            String diretor = sc.nextLine();

            System.out.print("Ano de Lançamento: ");
            int anoLancamento = Integer.parseInt(sc.nextLine());

            Filme filme = new Filme(id, titulo, genero, duracaoMinutos, diretor, anoLancamento);
            filme.validar();

            listaFilmes.add(filme);
            salvarFilmesNoArquivo();
            System.out.println("Filme cadastrado com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro ao cadastrar filme: " + e.getMessage());
        }
    }

    // método para listar todos os filmes
    public void listarFilmes() {
        System.out.println("=== Lista de Filmes ===");
        if (listaFilmes.isEmpty()) {
            System.out.println("Nenhum filme cadastrado.");
        } else {
            for (Filme f : listaFilmes) {
                System.out.println(f);
            }
        }
    }

    // método para buscar filme por ID
    public void buscarFilme() {
        System.out.print("Informe o ID do filme: ");
        int id = Integer.parseInt(sc.nextLine());

        Filme filme = encontrarFilmePorId(id);
        if (filme != null) {
            System.out.println(filme);
        } else {
            System.out.println("Filme não encontrado.");
        }
    }

    // método para editar filme
    public void editarFilme() {
        System.out.print("Informe o ID do filme para editar: ");
        int id = Integer.parseInt(sc.nextLine());

        Filme filme = encontrarFilmePorId(id);
        if (filme != null) {
            try {
                System.out.println("Deixe em branco se não quiser alterar o campo.");

                System.out.print("Novo título (" + filme.getTitulo() + "): ");
                String titulo = sc.nextLine();
                if (!titulo.isEmpty())
                    filme.setTitulo(titulo);

                System.out.print("Novo gênero (" + filme.getGenero() + "): ");
                String genero = sc.nextLine();
                if (!genero.isEmpty())
                    filme.setGenero(genero);

                System.out.print("Nova duração (" + filme.getDuracaoMinutos() + "): ");
                String duracaoStr = sc.nextLine();
                if (!duracaoStr.isEmpty())
                    filme.setDuracaoMinutos(Integer.parseInt(duracaoStr));

                System.out.print("Novo diretor (" + filme.getDiretor() + "): ");
                String diretor = sc.nextLine();
                if (!diretor.isEmpty())
                    filme.setDiretor(diretor);

                System.out.print("Novo ano de lançamento (" + filme.getAnoLancamento() + "): ");
                String anoStr = sc.nextLine();
                if (!anoStr.isEmpty())
                    filme.setAnoLancamento(Integer.parseInt(anoStr));

                filme.validar();
                salvarFilmesNoArquivo();
                System.out.println("Filme atualizado com sucesso!");

            } catch (Exception e) {
                System.out.println("Erro ao editar filme: " + e.getMessage());
            }

        } else {
            System.out.println("Filme não encontrado.");
        }
    }

    // método para excluir filme
    public void excluirFilme() {
        System.out.print("Informe o ID do filme para excluir: ");
        int id = Integer.parseInt(sc.nextLine());

        Filme filme = encontrarFilmePorId(id);
        if (filme != null) {
            listaFilmes.remove(filme);
            salvarFilmesNoArquivo();
            System.out.println("Filme excluído com sucesso!");
        } else {
            System.out.println("Filme não encontrado.");
        }
    }

    // método auxiliar para encontrar filme por ID
    private Filme encontrarFilmePorId(int id) {
        for (Filme f : listaFilmes) {
            if (f.getId() == id) {
                return f;
            }
        }
        return null;
    }

    // método para carregar filmes do arquivo
    private void carregarFilmesDoArquivo() {
        File arquivo = new File(CAMINHO_ARQUIVO);
        if (!arquivo.exists())
            return;

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length == 6) {
                    int id = Integer.parseInt(partes[0]);
                    String titulo = partes[1];
                    String genero = partes[2];
                    int duracao = Integer.parseInt(partes[3]);
                    String diretor = partes[4];
                    int ano = Integer.parseInt(partes[5]);

                    Filme f = new Filme(id, titulo, genero, duracao, diretor, ano);
                    listaFilmes.add(f);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar filmes do arquivo: " + e.getMessage());
        }
    }

    // método para salvar filmes no arquivo
    private void salvarFilmesNoArquivo() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(CAMINHO_ARQUIVO))) {
            for (Filme f : listaFilmes) {
                bw.write(f.getId() + ";" + f.getTitulo() + ";" + f.getGenero() + ";" +
                        f.getDuracaoMinutos() + ";" + f.getDiretor() + ";" + f.getAnoLancamento());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar filmes no arquivo: " + e.getMessage());
        }
    }
}
