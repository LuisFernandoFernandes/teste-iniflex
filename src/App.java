import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class App {

    private static final DateTimeFormatter DF = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final NumberFormat NF;
    static {
        Locale localeBR = Locale.of("pt", "BR");
        NF = NumberFormat.getNumberInstance(localeBR);
        NF.setMinimumFractionDigits(2);
        NF.setMaximumFractionDigits(2);
    }

    private static final BigDecimal AUMENTO_10 = new BigDecimal("0.10");
    private static final BigDecimal SAL_MIN = new BigDecimal("1212.00");

    private static final String COL_NOME = "Nome", COL_NASC = "Nascimento", COL_SAL = "Salario",
            COL_FUN = "Funcao", COL_IDADE = "Idade", COL_SM = "SalariosMinimos";

    public static void main(String[] args) {

        Map<String, Boolean> cols = colunasPadrao();

        List<Funcionario> funcionarios = seed();

        removePorNome(funcionarios, "João");

        criaTitulo("Funcionários");
        funcionarios.forEach(f -> printFuncionario(f, cols));

        aplicaAumentoSalario(funcionarios, AUMENTO_10);
        criaTitulo("Funcionários (Após aumento de 10%)");
        funcionarios.forEach(f -> printFuncionario(f, cols));

        criaTitulo("Funcionários (Agrupados por função)");
        Map<String, List<Funcionario>> porFuncao = agrupaPorFuncao(funcionarios);
        porFuncao.forEach((funcao, lista) -> {
            System.out.println("\n- " + funcao + ":");
            lista.forEach(f -> printFuncionario(f, cols));
        });

        criaTitulo("Aniversariantes (outubro e dezembro)");
        aniversariantes(funcionarios, Set.of(10, 12))
                .forEach(f -> printFuncionario(f, cols));

        cols.put(COL_SAL, false);
        cols.put(COL_NASC, false);
        cols.put(COL_FUN, false);
        cols.put(COL_IDADE, true);
        criaTitulo("Mais velho");
        maisVelho(funcionarios).ifPresent(f -> printFuncionario(f, cols));

        cols.clear();
        cols.putAll(colunasPadrao());
        criaTitulo("Ordem alfabética");
        ordenarPorNome(funcionarios).forEach(f -> printFuncionario(f, cols));

        criaTitulo("Total dos salários");
        System.out.println("R$ " + NF.format(totalSalarios(funcionarios)));

        cols.put(COL_SM, true);
        criaTitulo("Salários mínimos por funcionário");
        funcionarios.forEach(f -> printFuncionario(f, cols));
    }

    private static Map<String, Boolean> colunasPadrao() {
        Map<String, Boolean> m = new LinkedHashMap<>();
        m.put(COL_NOME, true);
        m.put(COL_NASC, true);
        m.put(COL_SAL, true);
        m.put(COL_FUN, true);
        m.put(COL_IDADE, false);
        m.put(COL_SM, false);
        return m;
    }

    private static List<Funcionario> seed() {
        return new ArrayList<>(Arrays.asList(
                new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"),
                new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"),
                new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"),
                new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"),
                new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"),
                new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"),
                new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"),
                new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"),
                new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"),
                new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente")));
    }

    private static void removePorNome(List<Funcionario> list, String nome) {
        list.removeIf(f -> f.getNome().equalsIgnoreCase(nome));
    }

    private static void printFuncionario(Funcionario f, Map<String, Boolean> colunas) {
        StringBuilder sb = new StringBuilder();

        if (colunas.getOrDefault(COL_NOME, false)) {
            sb.append(String.format("%-12s | ", f.getNome()));
        }
        if (colunas.getOrDefault(COL_NASC, false)) {
            sb.append(String.format("%s | ", DF.format(f.getDataNascimento())));
        }
        if (colunas.getOrDefault(COL_SAL, false)) {
            sb.append(String.format("%12s | ", NF.format(f.getSalario())));
        }
        if (colunas.getOrDefault(COL_FUN, false)) {
            sb.append(String.format("%-15s | ", f.getFuncao()));
        }
        if (colunas.getOrDefault(COL_IDADE, false)) {
            int idade = Period.between(f.getDataNascimento(), LocalDate.now()).getYears();
            sb.append(String.format("%d anos | ", idade));
        }
        if (colunas.getOrDefault(COL_SM, false)) {
            BigDecimal qtd = f.getSalario().divide(SAL_MIN, 2, RoundingMode.HALF_UP);
            sb.append(String.format("%s salários mínimos | ", NF.format(qtd)));
        }

       
        if (sb.length() > 3)
            sb.setLength(sb.length() - 3);

        System.out.println(sb.toString());
    }

    private static void aplicaAumentoSalario(List<Funcionario> list, BigDecimal percent) {
        BigDecimal fator = BigDecimal.ONE.add(percent);
        list.forEach(f -> f.setSalario(
                f.getSalario().multiply(fator).setScale(2, RoundingMode.HALF_UP)));
    }

    private static Map<String, List<Funcionario>> agrupaPorFuncao(List<Funcionario> list) {
        return list.stream().collect(Collectors.groupingBy(
                Funcionario::getFuncao, LinkedHashMap::new, Collectors.toList()));
    }

    private static List<Funcionario> aniversariantes(List<Funcionario> list, Set<Integer> meses) {
        return list.stream()
                .filter(f -> meses.contains(f.getDataNascimento().getMonthValue()))
                .collect(Collectors.toList());
    }

    private static Optional<Funcionario> maisVelho(List<Funcionario> list) {
        return list.stream().min(Comparator.comparing(Funcionario::getDataNascimento));
    }

    private static List<Funcionario> ordenarPorNome(List<Funcionario> list) {
        return list.stream().sorted(Comparator.comparing(Funcionario::getNome)).collect(Collectors.toList());
    }

    private static BigDecimal totalSalarios(List<Funcionario> list) {
        return list.stream().map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private static void criaTitulo(String t) {
        System.out.println("\n=== " + t + " ===");
    }
}