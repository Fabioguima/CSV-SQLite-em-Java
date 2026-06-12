package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class LeitorCsv {
    public List<String> lerLinhas(String nomeArquivo) throws IOException {
        List<String> linhas = new ArrayList<>();

        try (BufferedReader leitor = Files.newBufferedReader(
                Path.of(nomeArquivo), StandardCharsets.UTF_8)) {
            leitor.readLine();
            String linha;
            while ((linha = leitor.readLine()) != null) {
                if (!linha.isBlank()) {
                    linhas.add(linha);
                }
            }
        }
        return linhas;
    }
}
