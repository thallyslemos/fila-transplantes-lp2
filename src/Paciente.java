import java.time.LocalDate;
import java.time.LocalDateTime;

public class Paciente {
    private String nome;
    private String CPF;
    private String sexo;
    private LocalDate data_de_nascimento;
    private LocalDateTime data_entrada;
    private LocalDateTime data_saida;
    private Orgaos orgao;
    
    public Paciente(String nome, String CPF, String sexo, LocalDate data_de_nascimento, Orgaos orgao2) {
        this.nome = nome;
        this.CPF = CPF;
        this.sexo = sexo;
        this.data_de_nascimento = data_de_nascimento;
        this.orgao = orgao2;
        this.data_entrada = LocalDateTime.now();

    }

    public void setData_saida() {
        this.data_saida = LocalDateTime.now();
    }

    public String getCPF() {
        return CPF;
    }

    public LocalDateTime getData_saida() {
        return data_saida;
    }

    public LocalDateTime getData_entrada() {
        return data_entrada;
    }

    public Orgaos getOrgao() {
        return orgao;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return  
        "\nDados do Paciente: "+
        "\nNome: " + nome +
        "\nCFP: " + CPF +
        "\nSexo: " + sexo +
        "\nData de Nascimento: " + data_de_nascimento +
        "\nOrgao: " + orgao;
    }


}
