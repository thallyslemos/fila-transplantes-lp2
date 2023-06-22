import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

public class App {
    public static Paciente cadastraPaciente(Scanner input, Map<String, Paciente> cadastroPacientes) {
        Paciente novoPaciente = null;
        do {
            try {
                System.out.println("Cadastrar Paciente");
                System.out.println("Insira o nome do paciente: ");
                String nome = input.nextLine();
                System.out.println("Insira o CPF do paciente: ");
                String CPF = input.nextLine();
                verificarCpfExistente(CPF, cadastroPacientes);
                System.out.println("Insira o sexo do paciente: ");
                String sexo = input.nextLine();
                System.out.println("Insira a data de nascimento do paciente (dd/mm/aaaa): ");
                LocalDate dataNascimento = getLocalDateForString(input.nextLine());
                System.out.println("Insira nome do orgão a ser transplantado: ");
                Orgaos orgao = selecionaOrgao(input);
                novoPaciente = new Paciente(nome, CPF, sexo, dataNascimento, orgao);
                System.out.println(novoPaciente.getData_entrada());

                cadastroPacientes.put(CPF, novoPaciente);
                break;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } while (true);
        return novoPaciente;
    }

    private static final String PATTERN_DATE = "dd/MM/yyyy";

    public static LocalDate getLocalDateForString(final String string) {
        return LocalDate.parse(string,
                DateTimeFormatter.ofPattern(PATTERN_DATE));
    }

    public static void programa2() {
        System.out.println("Programa 2");

    }

    public static void chamarProximo(Scanner input, Queue<Paciente> filaCoracao, Queue<Paciente> filaRim,
            Queue<Paciente> filaPulmao,
            Queue<Paciente> filaFigado) {
        int qntOrgaos = Orgaos.values().length;

        System.out.println("Chamar próximo paciente da fila de: ");
        for (int i = 0; i < qntOrgaos; i++) {
            System.out.println((i + 1) + " " + Orgaos.values()[i]);

        }

        System.out.print("Selecione a fila [1-4]: ");
        int orgao = input.nextInt();
        Paciente paciente = null;

        switch (orgao) {
            case 1:
                System.out.println("Procedimento - Trnasplnate de Coração:\n");
                if (filaCoracao.size() > 0)
                    paciente = filaCoracao.remove();
                break;
            case 2:
                System.out.println("Procedimento - Trnasplnate de Rim.");
                if (filaRim.size() > 0)
                    paciente = filaRim.remove();
                break;
            case 3:
                System.out.println("Procedimento - Trnasplnate de Pulmao.");
                if (filaPulmao.size() > 0)
                    paciente = filaPulmao.remove();
                break;
            case 4:
                System.out.println("Procedimento - Trnasplnate de Figado.");
                if (filaFigado.size() > 0)
                    paciente = filaFigado.remove();
                break;
            default:
                System.out.println("Opçõa não encontrada");
                break;
        }
        if (paciente != null) {
            paciente.setData_saida();
            exibirDiferencaTempo(paciente);
            System.out.println(paciente.toString());
        } else {
            System.out.println("Não há pacientes nessa fila.");
        }
    }

    public static void verificarCpfExistente(String cpf, Map cadastroPacientes) throws Exception {
        if (cadastroPacientes.containsKey(cpf)) {
            throw new Exception("Já existe paciente cadastrado com esse CPF");
        }
    }

    public static Orgaos selecionaOrgao(Scanner input) throws Exception {
        Orgaos orgao = null;
        int qntOrgaos = Orgaos.values().length;
        for (int i = 0; i < qntOrgaos; i++) {
            System.out.println((i + 1) + " " + Orgaos.values()[i]);

        }
        int num = input.nextInt();
        if (num > (qntOrgaos)) {
            input.nextLine();
            throw new Exception("Opção inválida");
        }
        for (int i = 0; i < qntOrgaos; i++) {
            if (num == (i + 1))
                orgao = Orgaos.values()[i];

        }
        return orgao;
    }

    public static void programa3() {
        System.out.println("Programa 3");
    }

    public static void exibirMenuOperacoes() {
        System.out.println("---------------Opções------------------");
        System.out.println("* 1 - Cadastrar Paciente");
        System.out.println("* 2 - Chamar paciente para transplante");
        System.out.println("* 3 - Consultar dados do próximo paciente ");
        System.out.println("* 4 - Sair");
        System.out.println("---------------------------------------");
    }

    /*
     * Adiona paciente na fila correta com base no orgão a ser recebido
     */
    public static void adicionaPacienteNaFila(Paciente paciente, Queue<Paciente> filaCoracao, Queue<Paciente> filaRim,
            Queue<Paciente> filaPulmao,
            Queue<Paciente> filaFigado) {

        String orgao = paciente.getOrgao().toString();

        switch (orgao) {
            case "CORACAO":
                filaCoracao.add(paciente);
                break;
            case "RIM":
                filaRim.add(paciente);
                break;
            case "PULMAO":
                filaPulmao.add(paciente);
                break;
            case "FIGADO":
                filaFigado.add(paciente);
                break;
        }
    }

    public static void exibirDiferencaTempo(Paciente paciente) {

        long diferencaEmMinutos = ChronoUnit.MINUTES.between(paciente.getData_entrada(), paciente.getData_saida());
        long diferencaEmDias = diferencaEmMinutos / 1440;
        long diferencaMinutos = diferencaEmMinutos % 1440;
        long diferencaHoras = diferencaMinutos / 60;
        diferencaMinutos = diferencaMinutos % 60;

        System.out.println(
                "Tempo de espera: " + diferencaEmDias + " dia(s) " + diferencaHoras + " hora(s) e " + diferencaMinutos
                        + " minuto(s).");
    }

    public static void selecionarFila(Queue<Paciente> pacientes) {

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

    public static void proximoFila(Queue<Paciente> proximoFila) {

        Scanner sc = new Scanner(System.in);

        String orgaosFila;

        System.out.println("Selecione a fila de orgãos: ");
        System.out.println("Opções: coracao, rim, pulmao, figado");

        orgaosFila = sc.next();

        for (Orgaos o : Orgaos.values()) {

            switch (o.getOrgaos().toLowerCase()) {
                case "coracao":
                    if (o.getOrgaos().toLowerCase() == orgaosFila.toLowerCase()) {
                        for (Paciente p : proximoFila) {
                            System.out.println("Proximo da fila: " + p + " " + proximoFila.peek());
                        }

                    }

                    break;
                case "rim":

                    if (o.getOrgaos().toLowerCase() == orgaosFila.toLowerCase()) {
                        for (Paciente p : proximoFila) {
                            System.out.println("Proximo da fila: " + p + " " + proximoFila.peek());
                        }
                    }

                    break;
                case "pulmao":
                    if (o.getOrgaos().toLowerCase() == orgaosFila.toLowerCase()) {
                        for (Paciente p : proximoFila) {
                            System.out.println("Proximo da fila: " + p + " " + proximoFila.peek());
                        }
                    }

                    break;
                case "figado":
                    if (o.getOrgaos().toLowerCase() == orgaosFila.toLowerCase()) {
                        for (Paciente p : proximoFila) {
                            System.out.println("Proximo da fila: " + p + " " + proximoFila.peek());
                        }
                    }

                    break;
            }

        }

        sc.close();

    }

    // private static void verificarCPF(Queue<String> cpfFila, String novoCpf) {
    // for (String cpf : cpfFila) {
    // if (cpf.equals(novoCpf)) {
    // System.out.println("CPF já existe na fila.");
    // return;
    // }
    // }
    // System.out.println("CPF adicionado com sucesso!");
    // return;
    // }

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
        Map<String, Paciente> basePacientes = new HashMap<>();
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
                    Paciente novoPaciente = cadastraPaciente(scanner, basePacientes);
                    adicionaPacienteNaFila(novoPaciente, filaCoracao, filaRim, filaPulmao, filaFigado);
                    break;
                case 2:
                    chamarProximo(scanner, filaCoracao, filaRim, filaPulmao, filaFigado);
                    break;
                case 3:
                    // mostrarProximoPaciente();
                    break;

            }

            System.out.println();
        } while (op != 4); // Mudar de acordo a quantidade de métodos

        scanner.close();
    }

}