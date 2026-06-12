package com.example;

public class TradicaoGauchaMapper {
    public TradicaoGaucha mapear(String linha) {
        String[] campos = linha.split(";", -1);
        if (campos.length < 1 || campos.length > 5) {
            throw new IllegalArgumentException("quantidade de campos inválida");
        }

        int id = Integer.parseInt(campos[0].trim());

        switch (campos.length) {
            case 1:
                return new TradicaoGaucha(id);
            case 2:
                return new TradicaoGaucha(id, texto(campos[1]));
            case 3:
                return new TradicaoGaucha(id, texto(campos[1]), texto(campos[2]));
            case 4:
                return new TradicaoGaucha(id, texto(campos[1]), texto(campos[2]), texto(campos[3]));
            default:
                return new TradicaoGaucha(id, texto(campos[1]), texto(campos[2]),
                        texto(campos[3]), inteiro(campos[4]));
        }
    }

    private String texto(String valor) {
        String texto = valor.trim();
        return texto.isEmpty() ? null : texto;
    }

    private Integer inteiro(String valor) {
        String texto = valor.trim();
        return texto.isEmpty() ? null : Integer.parseInt(texto);
    }
}
