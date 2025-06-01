public class Filme {

    // atributos
    private int id;
    private String titulo;
    private String genero;
    private int duracaoMinutos;
    private String diretor;
    private int anoLancamento;

    // construtor
    public Filme(int id, String titulo, String genero, int duracaoMinutos, String diretor, int anoLancamento) {
        this.id = id;
        this.titulo = titulo;
        this.genero = genero;
        this.duracaoMinutos = duracaoMinutos;
        this.diretor = diretor;
        this.anoLancamento = anoLancamento;
    }

    // getters e setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getDuracaoMinutos() {
        return duracaoMinutos;
    }

    public void setDuracaoMinutos(int duracaoMinutos) {
        this.duracaoMinutos = duracaoMinutos;
    }

    public String getDiretor() {
        return diretor;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    public int getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(int anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    // método tostring
    @Override
    public String toString() {
        return "Filme{" +
                "ID=" + id +
                ", Título='" + titulo + '\'' +
                ", Gênero='" + genero + '\'' +
                ", Duração=" + duracaoMinutos + " minutos" +
                ", Diretor='" + diretor + '\'' +
                ", Ano de Lançamento=" + anoLancamento +
                '}';
    }

    // método de validação
    public void validar() throws Exception {
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new Exception("O título não pode ser vazio.");
        }
        if (genero == null || genero.trim().isEmpty()) {
            throw new Exception("O gênero não pode ser vazio.");
        }
        if (diretor == null || diretor.trim().isEmpty()) {
            throw new Exception("O diretor não pode ser vazio.");
        }
        if (anoLancamento < 1888 || anoLancamento > java.time.Year.now().getValue()) {
            throw new Exception("Ano de lançamento inválido.");
        }
        if (duracaoMinutos <= 60) {
            throw new Exception("A duração deve ser maior que 60 minutos.");
        }
    }
}
