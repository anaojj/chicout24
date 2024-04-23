package br.com;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Atv24{
    public static void main(String[] args) {
        try {
            List<Funcionario> funcionarios = lerFuncionariosDoCSV("C:\\Users\\JoanaDarc\\OneDrive\\ESTUDO\\JAVA\\aulachicout19\\funcionarios.csv");

            try (Scanner scanner = new Scanner(System.in)) {
                System.out.println("Filtrar funcionários:");
                System.out.println("1. Por cargo");
                System.out.println("2. Por salário mínimo");
                System.out.print("Escolha a opção: ");
                int opcao = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer

                switch (opcao) {
                    case 1:
                        System.out.print("Digite o cargo para filtrar: ");
                        String cargo = scanner.nextLine();
                        filtrarPorCargo(funcionarios, cargo);
                        break;
                    case 2:
                        System.out.print("Digite o salário mínimo para filtrar: ");
                        double salarioMinimo = scanner.nextDouble();
                        filtrarPorSalarioMinimo(funcionarios, salarioMinimo);
                        break;
                    default:
                        System.out.println("Opção inválida.");
                }
            }

        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
    }

    private static List<Funcionario> lerFuncionariosDoCSV(String arquivo) throws IOException, CsvException {
        List<Funcionario> funcionarios = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(arquivo))) {
            List<String[]> linhas = reader.readAll();

            for (String[] linha : linhas) {
                String nome = linha[0];
                String cargo = linha[1];
                double salario = Double.parseDouble(linha[2]);
                funcionarios.add(new Funcionario(nome, cargo, salario));
            }
        }

        return funcionarios;
    }

    private static void filtrarPorCargo(List<Funcionario> funcionarios, String cargo) {
        System.out.println("Funcionários com o cargo de " + cargo + ":");
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getCargo().equalsIgnoreCase(cargo)) {
                System.out.println(funcionario);
            }
        }
    }

    private static void filtrarPorSalarioMinimo(List<Funcionario> funcionarios, double salarioMinimo) {
        System.out.println("Funcionários com salário igual ou superior a " + salarioMinimo + ":");
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getSalario() >= salarioMinimo) {
                System.out.println(funcionario);
            }
        }
    }
}