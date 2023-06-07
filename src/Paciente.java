import java.util.Date;

public class Paciente {
    private String nome;
    private String CPF;
    private String sexo;
    private Date data_de_nascimento;
    private Date data_entrada;
    private Date data_saida;
    private Orgaos orgao;
    
    public Paciente(String nome, String CPF, String sexo, Date data_de_nascimento, Orgaos orgao) {
        this.nome = nome;
        this.CPF = CPF;
        this.sexo = sexo;
        this.data_de_nascimento = data_de_nascimento;
        this.orgao = orgao;
        this.data_entrada = new Date();

    }

    public void setData_saida(Date data_saida) {
        this.data_saida = data_saida;
    }

    public String getCPF() {
        return CPF;
    }

    public Date getData_saida() {
        return data_saida;
    }

    public Date getData_entrada() {
        return data_entrada;
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
