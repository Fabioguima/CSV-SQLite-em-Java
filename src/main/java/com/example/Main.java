package com.example;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String BANCO = "jdbc:sqlite:tradicoes.db";
    private static final String CSV = "tradicoes.csv";

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in);
             TradicaoGauchaDAO dao = new TradicaoGauchaDAO(BANCO)) {
            ImportacaoService importacao = new ImportacaoService();
            int opcao;

            do {
                mostrarMenu();
                opcao = lerOpcao(scanner);

                switch (opcao) {
                    case 1:
                        importacao.importar(CSV, dao);
                        break;
                    case 2:
                        mostrarLista(dao.listarPorNome());
                        break;
                    case 3:
                        mostrarLista(dao.listarPorCategoria());
                        break;
                    case 0:
                        System.out.println("Programa encerrado.");
                        break;
                    default:
                        System.out.println("Opção inválida.");
                }
            } while (opcao != 0);
        } catch (SQLException e) {
            System.err.println("Erro no banco de dados: " + e.getMessage());
        }
    }

    private static void mostrarMenu() {
        System.out.println("\n=== Tradições Gaúchas ===");
        System.out.println("1. Importar arquivo CSV");
        System.out.println("2. Listar por nome");
        System.out.println("3. Listar por categoria");
        System.out.println("0. Sair");
        System.out.print("Escolha: ");
    }

    private static int lerOpcao(Scanner scanner) {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void mostrarLista(List<TradicaoGaucha> lista) {
        System.out.println("\nID  | NOME                         | CATEGORIA      | CIDADE               | ANO");
        for (TradicaoGaucha tradicao : lista) {
            System.out.println(tradicao);
        }
        System.out.println("Total listado: " + lista.size());
    }
}
