package com.example;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ImportacaoService {
    private final LeitorCsv leitor = new LeitorCsv();
    private final TradicaoGauchaMapper mapper = new TradicaoGauchaMapper();

    public void importar(String arquivo, TradicaoGauchaDAO dao) {
        int importados = 0;
        int erros = 0;

        try {
            List<String> linhas = leitor.lerLinhas(arquivo);
            for (int i = 0; i < linhas.size(); i++) {
                try {
                    TradicaoGaucha tradicao = mapper.mapear(linhas.get(i));
                    dao.inserir(tradicao);
                    importados++;
                    System.out.printf("Registro %d importado: %d atributos preenchidos.%n",
                            tradicao.getId(), tradicao.quantidadeAtributosPreenchidos());
                } catch (IllegalArgumentException | SQLException e) {
                    erros++;
                    System.err.printf("Erro na linha %d: %s%n", i + 2, e.getMessage());
                }
            }
            System.out.printf("%nImportação concluída: %d registros importados e %d erros.%n",
                    importados, erros);
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo CSV: " + e.getMessage());
        }
    }
}
