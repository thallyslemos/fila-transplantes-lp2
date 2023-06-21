import java.util.ArrayDeque;
import java.util.Date;
import java.util.Queue;
import java.util.Scanner;

public class App {
    public static void cadastraPaciente(Scanner input) {
        System.out.println("Cadastrar Paciente");
        System.out.println("Insira o nome do paciente: ");
        input.nextLine();
        System.out.println("Insira o CPF do paciente: ");
        input.nextLine();
        System.out.println("Insira o sexo do paciente: ");
        input.nextLine();
        System.out.println("Insira a data de nascimento do paciente: ");
        input.nextLine();
        System.out.println("Insira nome do orgão a ser transplantado: ");
        input.nextLine();
    }

    public static void programa2() {
        System.out.println("Programa 2");
    }

    public static void programa3() {
        System.out.println("Programa 3");
    }

    public static void exibirMenuOperacoes() {
        System.out.println("---------------Opções------------------");
        System.out.println("* 1 - Cadastrar Paciente");
        System.out.println("* 2 - Programa 2");
        System.out.println("* 3 - Programa 3 ");
        System.out.println("* 4 - Sair");
        System.out.println("---------------------------------------");
    }

    public static void selecionarFila(ArrayDeque<Paciente> pacientes) {

        Scanner sc = new Scanner(System.in);

        String orgaosFila;

        System.out.println("Selecione a fila de orgãos: ");
        System.out.println("Opções: coracao, rim, pulmao, figado");

        orgaosFila = sc.next();

        for (Orgaos o : Orgaos.values()) {

            switch (o.getOrgaos().toLowerCase()) {
                case "coracao":
                    if (o.getOrgaos().toLowerCase() == orgaosFila.toLowerCase()) {
                        for (Paciente p : pacientes) {
                            System.out.println("Fila espera de " + orgaosFila.toLowerCase() + " " + p + "\r\n");
                        }

                    }

                    break;
                case "rim":

                    if (o.getOrgaos().toLowerCase() == orgaosFila.toLowerCase()) {
                        for (Paciente p : pacientes) {
                            System.out.println("Fila espera de " + orgaosFila.toLowerCase() + " " + p + "\r\n");
                        }
                    }

                    break;
                case "pulmao":
                    if (o.getOrgaos().toLowerCase() == orgaosFila.toLowerCase()) {
                        for (Paciente p : pacientes) {
                            System.out.println("Fila espera de " + orgaosFila.toLowerCase() + " " + p + "\r\n");
                        }
                    }

                    break;
                case "figado":
                    if (o.getOrgaos().toLowerCase() == orgaosFila.toLowerCase()) {
                        for (Paciente p : pacientes) {
                            System.out.println("Fila espera de " + orgaosFila.toLowerCase() + " " + p + "\r\n");
                        }
                    }

                    break;

            }

        }

        sc.close();

    }

    private static void verificarCPF(Queue<String> cpfFila, String novoCpf) {
        for (String cpf : cpfFila) {
            if (cpf.equals(novoCpf)) {
                System.out.println("CPF já existe na fila.");
                return;
            }
        }
        System.out.println("CPF adicionado com sucesso!");
        return;
    }

    public static void qtdPacientesPorFila(Queue<Paciente> Coracao, Queue<Paciente> Rim, Queue<Paciente> Pulmao,
            Queue<Paciente> Figado) {
        int PacientesCoracao = 0, PacientesRim = 0, PacientesPulmao = 0, PacientesFigado = 0;
        for (Paciente p : Coracao) {
            PacientesCoracao++;
        }
        System.out.println("Número de pacientes na fila de transplante de coração: " + PacientesCoracao);
        for (Paciente p : Rim) {
            PacientesRim++;
        }
        System.out.println("Número de pacientes na fila de transplante de rim: " + PacientesRim);
        for (Paciente p : Pulmao) {
            PacientesPulmao++;
        }
        System.out.println("Número de pacientes na fila de transplante de pulmão: " + PacientesPulmao);
        for (Paciente p : Figado) {
            PacientesFigado++;
        }
        System.out.println("Número de pacientes na fila de transplante de fígado: " + PacientesFigado);
    }

    public static void main(String[] args) throws Exception {
        Queue<Paciente> filaCoracao = new ArrayDeque<>();
        Queue<Paciente> filaRim = new ArrayDeque<>();
        Queue<Paciente> filaPulmao = new ArrayDeque<>();
        Queue<Paciente> filaFigado = new ArrayDeque<>();
        int op;

        Scanner scanner = new Scanner(System.in); // entrada da opção desejada

        do {
            exibirMenuOperacoes();

            System.out.print("Operação [1-4]: ");
            op = scanner.nextInt(); // leitura da opção desejada
            scanner.nextLine(); // descarte de caracteres

            System.out.println();

            switch (op) {
                case 1:
                    cadastraPaciente(scanner);
                    break;
                case 2:
                    programa2();
                    break;
                case 3:
                    programa3();
                    break;

            }

            System.out.println();
        } while (op != 4); // Mudar de acordo a quantidade de métodos

        scanner.close();
    }

}
