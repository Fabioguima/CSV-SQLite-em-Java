package com.example;

public class TradicaoGauchaMapper {

    public TradicaoGaucha mapear(String linha) {
        String[] campos = linha.split(";", -1);

        if (campos.length != 5) {
            throw new IllegalArgumentException(
                    "linha incompleta: esperado id e 4 colunas de dados");
        }

        for (String campo : campos) {
            if (campo.trim().isEmpty()) {
                throw new IllegalArgumentException(
                        "linha incompleta: todos os campos devem estar preenchidos");
            }
        }

        int id = Integer.parseInt(campos[0].trim());
        String nome = campos[1].trim();
        String categoria = campos[2].trim();
        String cidade = campos[3].trim();
        int anoOrigem = Integer.parseInt(campos[4].trim());

        return new TradicaoGaucha(
                id,
                nome,
                categoria,
                cidade,
                anoOrigem
        );
    }
}