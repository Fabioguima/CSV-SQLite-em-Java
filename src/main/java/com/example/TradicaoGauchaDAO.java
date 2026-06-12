package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class TradicaoGauchaDAO implements AutoCloseable {
    private final Connection conexao;

    public TradicaoGauchaDAO(String url) throws SQLException {
        conexao = DriverManager.getConnection(url);
        criarTabela();
    }

    private void criarTabela() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS tradicao_gaucha ("
                + "id INTEGER PRIMARY KEY, "
                + "nome TEXT, categoria TEXT, cidade TEXT, ano_origem INTEGER)";

        try (Statement statement = conexao.createStatement()) {
            statement.execute(sql);
        }
    }

    public void inserir(TradicaoGaucha tradicao) throws SQLException {
        String sql = "INSERT OR REPLACE INTO tradicao_gaucha "
                + "(id, nome, categoria, cidade, ano_origem) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setInt(1, tradicao.getId());
            statement.setString(2, tradicao.getNome());
            statement.setString(3, tradicao.getCategoria());
            statement.setString(4, tradicao.getCidade());
            if (tradicao.getAnoOrigem() == null) {
                statement.setNull(5, Types.INTEGER);
            } else {
                statement.setInt(5, tradicao.getAnoOrigem());
            }
            statement.executeUpdate();
        }
    }

    public List<TradicaoGaucha> listarPorNome() throws SQLException {
        return listar("nome COLLATE NOCASE, id");
    }

    public List<TradicaoGaucha> listarPorCategoria() throws SQLException {
        return listar("categoria COLLATE NOCASE, nome COLLATE NOCASE, id");
    }

    private List<TradicaoGaucha> listar(String ordenacao) throws SQLException {
        List<TradicaoGaucha> lista = new ArrayList<>();
        String sql = "SELECT * FROM tradicao_gaucha ORDER BY " + ordenacao;

        try (Statement statement = conexao.createStatement();
             ResultSet resultado = statement.executeQuery(sql)) {
            while (resultado.next()) {
                Integer ano = resultado.getObject("ano_origem") == null
                        ? null : resultado.getInt("ano_origem");
                lista.add(new TradicaoGaucha(
                        resultado.getInt("id"),
                        resultado.getString("nome"),
                        resultado.getString("categoria"),
                        resultado.getString("cidade"),
                        ano));
            }
        }
        return lista;
    }

    @Override
    public void close() throws SQLException {
        conexao.close();
    }
}
