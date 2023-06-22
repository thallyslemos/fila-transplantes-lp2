import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Iterator;
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
                LocalDate dataNascimento = conversaoDataData(input.nextLine(), padraoEntrada);
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

    // padrão de entrada de data
    private static final String padraoEntrada = "dd/MM/yyyy";
    private static final String padraoSaida = "yyyy-MM-dd";
    private static final String padraoSaidaCompleto = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS";

    // conversão de string em data
    // public static LocalDate conversaoDataDat(final String string, String
    // padraoEntrada) {
    // return LocalDate.parse(string,
    // DateTimeFormatter.ofPattern(padraoEntrada));
    // }
    public static LocalDateTime conversaoDataDataHora(final String string, String padraoEntrada) {
        return LocalDateTime.parse(string,
                DateTimeFormatter.ofPattern(padraoEntrada));
    }

    public static LocalDate conversaoDataData(final String string, String padraoEntrada) {
        return LocalDate.parse(string,
                DateTimeFormatter.ofPattern(padraoEntrada));
    }

    /*
     * Permite selecionar uma dentre as quatro filas
     * Exibe o próximo paciente da fila de doação selecionada
     * marca a data e horário de saída
     * exibe os dados do paciente e o tempo em fila
     */
    public static void mostrarProximoPaciente(Scanner input, Queue<Paciente> filaCoracao, Queue<Paciente> filaRim,
            Queue<Paciente> filaPulmao,
            Queue<Paciente> filaFigado) {
        int qntOrgaos = Orgaos.values().length;

        System.out.println("Ver próximo paciente da fila de: ");
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
                    paciente = filaCoracao.peek();
                break;
            case 2:
                System.out.println("Procedimento - Trnasplnate de Rim.");
                if (filaRim.size() > 0)
                    paciente = filaRim.peek();
                break;
            case 3:
                System.out.println("Procedimento - Trnasplnate de Pulmao.");
                if (filaPulmao.size() > 0)
                    paciente = filaPulmao.peek();
                break;
            case 4:
                System.out.println("Procedimento - Trnasplnate de Figado.");
                if (filaFigado.size() > 0)
                    paciente = filaFigado.peek();
                break;
            default:
                System.out.println("Opçõa não encontrada");
                break;
        }
        if (paciente != null) {
            System.out.println("O próximo da fila é: ");
            System.out.println(paciente.toString());
            exibirDiferencaTempo(CalculaDiferencaTempo(paciente));
        } else {
            System.out.println("Não há pacientes nessa fila.");
        }

    }

    /*
     * Permite selecionar uma dentre as quatro filas
     * Remove o próximo paciente da fila de doação selecionada
     * marca a data e horário de saída
     * exibe os dados do paciente e o tempo em fila
     */
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
            System.out.println(paciente.toString());
            exibirDiferencaTempo(CalculaDiferencaTempo(paciente));
        } else {
            System.out.println("Não há pacientes nessa fila.");
        }
    }

    /*
     * verificação de CPF - usado no cadastro do paciente
     */
    public static void verificarCpfExistente(String cpf, Map cadastroPacientes) throws Exception {
        if (cadastroPacientes.containsKey(cpf)) {
            throw new Exception("Já existe paciente cadastrado com esse CPF");
        }
    }

    /*
     * Seleção de orgãos - usado no cadastro de paciente
     */
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

    public static void exibirMenuOperacoes() {
        System.out.println("---------------Opções------------------");
        System.out.println("* 1 - Cadastrar Paciente");
        System.out.println("* 2 - Chamar paciente para transplante");
        System.out.println("* 3 - Consultar dados do próximo paciente ");
        System.out.println("* 4 - Consultas estatísticas sobre filas");
        System.out.println("* 5 - Sair");
        System.out.println("---------------------------------------");
    }

    public static void exibirMenuEstatistica() {
        System.out.println("-------------Estatísticas--------------");
        System.out.println("* 1 - Quantidade de pacientes por fila");
        System.out.println("* 2 - Percentual por tipo de transplante");
        System.out.println("* 3 - Tempo médio de espera por fila ");
        System.out.println("* 4 - Sair");
        System.out.println("---------------------------------------");
    }

    /*
     * Adiciona paciente na fila correta com base no orgão a ser recebido
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

    /*
     * Calcula a diferença entre tempo de cadastro e tempo de saída da fila em
     * minutos
     * caso o paciente ainda não tenha saído dafila o calculo sera entre a data de
     * entrada e a data corrente da consulta
     */
    public static long CalculaDiferencaTempo(Paciente paciente) {
        long diferencaEmMinutos;
        if (paciente.getData_saida() != null) {
            // para calculo de pacientes removidos da fila de transplante
            diferencaEmMinutos = ChronoUnit.MINUTES.between(paciente.getData_entrada(), paciente.getData_saida());
        } else {
            // para pacientes que ainda estão na fila
            diferencaEmMinutos = ChronoUnit.MINUTES.between(paciente.getData_entrada(), LocalDateTime.now());
        }

        return diferencaEmMinutos;
    }

    /*
     * Recebe um intervalo de tempo dado em minutos
     * Exibe esse intervalo em dias, horas e minutos
     */
    public static void exibirDiferencaTempo(long diferencaEmMinutos) {

        long diferencaEmDias = diferencaEmMinutos / 1440;
        long diferencaMinutos = diferencaEmMinutos % 1440;
        long diferencaHoras = diferencaMinutos / 60;
        diferencaMinutos = diferencaMinutos % 60;

        System.out.println(
                "Tempo de espera: " + diferencaEmDias + " dia(s) " + diferencaHoras + " hora(s) e " + diferencaMinutos
                        + " minuto(s).");
    }

    // public static void selecionarFila(Queue<Paciente> pacientes) {

    // Scanner sc = new Scanner(System.in);

    // String orgaosFila;

    // System.out.println("Selecione a fila de orgãos: ");
    // System.out.println("Opções: coracao, rim, pulmao, figado");

    // orgaosFila = sc.next();

    // for (Orgaos o : Orgaos.values()) {

    // switch (o.getOrgaos().toLowerCase()) {
    // case "coracao":
    // if (o.getOrgaos().toLowerCase() == orgaosFila.toLowerCase()) {
    // for (Paciente p : pacientes) {
    // System.out.println("Fila espera de " + orgaosFila.toLowerCase() + " " + p +
    // "\r\n");
    // }

    // }

    // break;
    // case "rim":

    // if (o.getOrgaos().toLowerCase() == orgaosFila.toLowerCase()) {
    // for (Paciente p : pacientes) {
    // System.out.println("Fila espera de " + orgaosFila.toLowerCase() + " " + p +
    // "\r\n");
    // }
    // }

    // break;
    // case "pulmao":
    // if (o.getOrgaos().toLowerCase() == orgaosFila.toLowerCase()) {
    // for (Paciente p : pacientes) {
    // System.out.println("Fila espera de " + orgaosFila.toLowerCase() + " " + p +
    // "\r\n");
    // }
    // }

    // break;
    // case "figado":
    // if (o.getOrgaos().toLowerCase() == orgaosFila.toLowerCase()) {
    // for (Paciente p : pacientes) {
    // System.out.println("Fila espera de " + orgaosFila.toLowerCase() + " " + p +
    // "\r\n");
    // }
    // }

    // break;
    // }

    // }

    // sc.close();

    // }

    // public static void proximoFila(Queue<Paciente> proximoFila) {

    // Scanner sc = new Scanner(System.in);

    // String orgaosFila;

    // System.out.println("Selecione a fila de orgãos: ");
    // System.out.println("Opções: coracao, rim, pulmao, figado");

    // orgaosFila = sc.next();

    // for (Orgaos o : Orgaos.values()) {

    // switch (o.getOrgaos().toLowerCase()) {
    // case "coracao":
    // if (o.getOrgaos().toLowerCase() == orgaosFila.toLowerCase()) {
    // for (Paciente p : proximoFila) {
    // System.out.println("Proximo da fila: " + p + " " + proximoFila.peek());
    // }

    // }

    // break;
    // case "rim":

    // if (o.getOrgaos().toLowerCase() == orgaosFila.toLowerCase()) {
    // for (Paciente p : proximoFila) {
    // System.out.println("Proximo da fila: " + p + " " + proximoFila.peek());
    // }
    // }

    // break;
    // case "pulmao":
    // if (o.getOrgaos().toLowerCase() == orgaosFila.toLowerCase()) {
    // for (Paciente p : proximoFila) {
    // System.out.println("Proximo da fila: " + p + " " + proximoFila.peek());
    // }
    // }

    // break;
    // case "figado":
    // if (o.getOrgaos().toLowerCase() == orgaosFila.toLowerCase()) {
    // for (Paciente p : proximoFila) {
    // System.out.println("Proximo da fila: " + p + " " + proximoFila.peek());
    // }
    // }

    // break;
    // }

    // }

    // sc.close();

    // }

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
    // public static void consultasEstatisticas(Scanner input, Queue<Paciente>
    // filaCoracao, Queue<Paciente> filaRim,
    // Queue<Paciente> filaPulmao,
    // Queue<Paciente> filaFigado) {
    // int op = 0;
    // do {
    // exibirMenuEstatistica();

    // op = input.nextInt();

    // switch (op) {
    // case 1:
    // qtdPacientesPorFila(filaCoracao, filaRim, filaPulmao, filaFigado);
    // break;
    // case 2:
    // break;
    // case 3:
    // break;

    // }

    // System.out.println();
    // } while (op != 4);

    // }

    public static void estatiscas(Scanner input, Queue<Paciente> filaCoracao, Queue<Paciente> filaRim,
            Queue<Paciente> filaPulmao,
            Queue<Paciente> filaFigado) {
        int op = 0;
        do {
            int pacientesCoracao = 0, pacientesRim = 0, pacientesPulmao = 0, pacientesFigado = 0;
            long tempoEsperaCoracao = 0, tempoEsperaRim = 0, tempoEsperaPulmao = 0, tempoEsperaFigado = 0;
            for (Paciente p : filaCoracao) {
                tempoEsperaCoracao += CalculaDiferencaTempo(p);
                pacientesCoracao++;
                // System.out.println(CalculaDiferencaTempo(p));
            }
            for (Paciente p : filaRim) {
                tempoEsperaRim += CalculaDiferencaTempo(p);
                pacientesRim++;
                // System.out.println(CalculaDiferencaTempo(p));
            }
            for (Paciente p : filaPulmao) {
                tempoEsperaPulmao += CalculaDiferencaTempo(p);
                pacientesPulmao++;
                // System.out.println(CalculaDiferencaTempo(p));
            }
            for (Paciente p : filaFigado) {
                tempoEsperaFigado += CalculaDiferencaTempo(p);
                pacientesFigado++;
                // System.out.println(CalculaDiferencaTempo(p));
            }
            int totalPacientes = pacientesCoracao + pacientesFigado + pacientesPulmao + pacientesRim;

            exibirMenuEstatistica();

            op = input.nextInt();

            switch (op) {
                case 1:
                    System.out.println("Número de pacientes na fila de transplante de coração: " + pacientesCoracao);
                    System.out.println("Número de pacientes na fila de transplante de rim: " + pacientesRim);
                    System.out.println("Número de pacientes na fila de transplante de pulmão: " + pacientesPulmao);
                    System.out.println("Número de pacientes na fila de transplante de fígado: " + pacientesFigado);
                    break;
                case 2:
                    System.out.println("Percentual de pacientes por fila: ");
                    if (totalPacientes > 0) {
                        System.out.println("Fila de transplante de coração: "
                                + formataPercentual((double) pacientesCoracao / totalPacientes));
                        System.out.println(
                                "Fila de transplante de rim: "
                                        + formataPercentual((double) pacientesRim / totalPacientes));
                        System.out.println(
                                "Fila de transplante de pulmão: "
                                        + formataPercentual((double) pacientesPulmao / totalPacientes));
                        System.out.println(
                                "Fila de transplante de fígado: "
                                        + formataPercentual((double) pacientesFigado / totalPacientes));
                    } else {
                        System.out.println("Não há pacientes em espera.");
                    }
                    break;
                case 3:
                    System.out.println("Tempo médio de espera");
                    System.out.println("Fila de transplante de coração: ");
                    if (pacientesCoracao > 0) {
                        exibirDiferencaTempo(tempoEsperaCoracao / pacientesCoracao);
                    } else {
                        System.out.println("Fila vazia...");
                    }
                    System.out.println("Fila de transplante de rim: ");
                    if (pacientesRim > 0) {
                        exibirDiferencaTempo(tempoEsperaRim / pacientesRim);
                    } else {
                        System.out.println("Fila vazia...");
                    }
                    System.out.println("Fila de transplante de pulmão: ");
                    if (pacientesPulmao > 0) {
                        exibirDiferencaTempo(tempoEsperaPulmao / pacientesPulmao);
                    } else {
                        System.out.println("Fila vazia...");
                    }
                    System.out.println("Fila de transplante de fígado: ");
                    if (pacientesFigado > 0) {
                        exibirDiferencaTempo(tempoEsperaFigado / pacientesFigado);
                    } else {
                        System.out.println("Fila vazia...");
                    }
                    break;

            }

            System.out.println();
        } while (op != 4);

    }

    public static String formataPercentual(double value) {
        DecimalFormat df = new DecimalFormat("#0.00%");
        return df.format(value);
    }

    public static void recuperaDadosDaFila(Queue<Paciente> fila, String caminho_arquivo) {
        System.out.println("Carregando dados...");

        try {
            FileReader leitor_arquivo = new FileReader(caminho_arquivo);
            Scanner fluxo_leitura = new Scanner(leitor_arquivo);
            while (fluxo_leitura.hasNext()) {

                String linha = fluxo_leitura.nextLine();

                String[] campos = linha.split("[|]");

                String nome = campos[0];
                String cpf = campos[1];
                String sexo = campos[2];
                LocalDate nascimento = conversaoDataData(campos[3], padraoSaida);
                Orgaos orgao = Orgaos.fromString(campos[4]);
                LocalDateTime data_entrada = null;
                if (campos[5].compareTo("null") != 0) {
                    data_entrada = conversaoDataDataHora(campos[5], padraoSaidaCompleto);
                }
                LocalDateTime data_saida = null;
                if (campos[6].compareTo("null") != 0) {
                    data_saida = conversaoDataDataHora(campos[6], padraoSaidaCompleto);
                }
                fila.add(new Paciente(nome, cpf, sexo, nascimento, data_entrada, data_saida, orgao));
            }
            leitor_arquivo.close();
            fluxo_leitura.close();
        } catch (Exception e) {
            System.out.println("Erro ao salvar dados: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void salvDadosDaFila(Queue<Paciente> fila, String caminho_arquivo) {
        try {
            Iterator<Paciente> it = fila.iterator();
            System.out.println("SALVANDO DADOS...");
            PrintWriter escrita_arquivo = new PrintWriter(caminho_arquivo);

            while (it.hasNext()) {
                Paciente p = it.next();
                // escrita_arquivo.write("Moeda: " + p.getValor() + " | Quantidade: " +
                // p.getQuantidade() + " | Valor total: " +p.getTotalPunhado() + "\n");
                escrita_arquivo
                        .write(p.getNome() + "|" + p.getCPF() + "|" + p.getSexo() + "|" + p.getData_de_nascimento()
                                + "|" + p.getOrgao() + "|" + p.getData_entrada() + "|" + p.getData_saida() + "\n");
            }

            escrita_arquivo.close();
            System.out.println("Dados Salvos com sucesso");
        } catch (IOException e) {
            System.out.println("Erro ao salvar dados: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public static Map<String, Paciente> getPacientesDeTodasFilas(Queue<Paciente> fila1, Queue<Paciente> fila2, Queue<Paciente> fila3, Queue<Paciente> fila4) {
        Map<String, Paciente> mapaPacientes = new HashMap<>();
        
        adicionarPacientesDaFila(mapaPacientes, fila1);
        adicionarPacientesDaFila(mapaPacientes, fila2);
        adicionarPacientesDaFila(mapaPacientes, fila3);
        adicionarPacientesDaFila(mapaPacientes, fila4);
        
        return mapaPacientes;
    }
    
    public static void adicionarPacientesDaFila(Map<String, Paciente> mapaPacientes, Queue<Paciente> fila) {
        for (Paciente paciente : fila) {
            mapaPacientes.put(paciente.getCPF(), paciente);
        }
    }

    public static void main(String[] args) throws Exception {
        Queue<Paciente> filaCoracao = new ArrayDeque<>();
        Queue<Paciente> filaRim = new ArrayDeque<>();
        Queue<Paciente> filaPulmao = new ArrayDeque<>();
        Queue<Paciente> filaFigado = new ArrayDeque<>();
        String caminhoArquivoCoracao = "coracao.txt", caminhoArquivoRim = "rim.txt",
                caminhoArquivoPulmao = "pulmao.txt", caminhoArquivoFigado = "figado.txt";
        Map<String, Paciente> basePacientes = new HashMap<>();

        recuperaDadosDaFila(filaFigado, caminhoArquivoFigado);
        recuperaDadosDaFila(filaPulmao, caminhoArquivoPulmao);
        recuperaDadosDaFila(filaRim, caminhoArquivoRim);
        recuperaDadosDaFila(filaCoracao, caminhoArquivoCoracao);
        basePacientes = getPacientesDeTodasFilas(filaCoracao, filaRim, filaPulmao, filaFigado);
        System.out.println("\n");

        int op;
        // Dados de teste - apagar *
        // filaCoracao.add(new Paciente("Rogerinho", "123", "m", null, Orgaos.CORACAO));
        // filaFigado.add(new Paciente("Ana", "132", "f", null, Orgaos.FIGADO));
        // filaPulmao.add(new Paciente("Thallys", "321", "m", null, Orgaos.PULMAO));
        // filaRim.add(new Paciente("Luis", "159", "m", null, Orgaos.RIM));
        // filaCoracao.add(new Paciente("Henrique", "951", "m", null, Orgaos.CORACAO));
        // filaFigado.add(new Paciente("Carlos", "777", "m", null, Orgaos.FIGADO));
        // Paciente rogerin = filaCoracao.peek();
        // rogerin.setData_saida();

        Scanner scanner = new Scanner(System.in); // entrada da opção desejada

        do {
            exibirMenuOperacoes();

            System.out.print("Operação [1-5]: ");
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
                    mostrarProximoPaciente(scanner, filaCoracao, filaRim, filaPulmao, filaFigado);
                    ;
                    break;
                case 4:
                    estatiscas(scanner, filaCoracao, filaRim, filaPulmao, filaFigado);
                    break;

            }

            System.out.println();
        } while (op != 5); // Mudar de acordo a quantidade de métodos

        salvDadosDaFila(filaFigado, caminhoArquivoFigado);
        salvDadosDaFila(filaCoracao, caminhoArquivoCoracao);
        salvDadosDaFila(filaRim, caminhoArquivoRim);
        salvDadosDaFila(filaPulmao, caminhoArquivoPulmao);

        scanner.close();
    }

}