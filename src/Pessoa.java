public abstract class Pessoa {
    private String nome;
    private String tipoSanguineo;
    private int idade;
    private String sexo;

    public Pessoa(String nome,
            String tipoSanguineo,
            int idade,
            String sexo) {
        this.nome = nome;
        this.tipoSanguineo = tipoSanguineo;
        this.idade = idade;
        this.sexo = sexo;
    }
}
