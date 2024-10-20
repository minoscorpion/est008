/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastrodb.model;

import cadastro.model.util.ConectorBD;
import cadastro.model.util.SequenceManager;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rafae
 */


public class PessoaFisicaDAO {

    public PessoaFisica getPessoa(int id) throws SQLException {
        PessoaFisica pessoaFisica = null;
        String sql = "SELECT * FROM Pessoa p JOIN PessoasFisicas pf ON p.pessoaid = pf.pessoaid WHERE p.pessoaid = ?";

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConectorBD.getConnection();
            stmt = ConectorBD.getPrepared(conn, sql);
            stmt.setInt(1, id);
            rs = ConectorBD.getSelect(stmt);

            if (rs.next()) {
                pessoaFisica = new PessoaFisica(
                    rs.getInt("pessoaid"),
                    rs.getString("nome"),
                    rs.getString("logradouro"),
                    rs.getString("cidade"),
                    rs.getString("estado"),
                    rs.getString("telefone"),
                    rs.getString("email"),
                    rs.getString("cpf")
                );
            }
        } finally {
            ConectorBD.close(rs);
            ConectorBD.close(stmt);
            ConectorBD.close(conn);
        }

        return pessoaFisica;
    }
   
    public List<PessoaFisica> getPessoas() throws SQLException {
        List<PessoaFisica> pessoas = new ArrayList<>();
        String sql = "SELECT * FROM Pessoa p JOIN PessoasFisicas pf ON p.pessoaid = pf.pessoaid";

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConectorBD.getConnection();
            stmt = ConectorBD.getPrepared(conn, sql);
            rs = ConectorBD.getSelect(stmt);

            while (rs.next()) {
                PessoaFisica pessoaFisica = new PessoaFisica(
                    rs.getInt("pessoaid"),
                    rs.getString("nome"),
                    rs.getString("logradouro"),
                    rs.getString("cidade"),
                    rs.getString("estado"),
                    rs.getString("telefone"),
                    rs.getString("email"),
                    rs.getString("cpf")
                );
                pessoas.add(pessoaFisica);
            }
        } finally {
            ConectorBD.close(rs);
            ConectorBD.close(stmt);
            ConectorBD.close(conn);
        }

        return pessoas;
    }

    public void incluir(PessoaFisica pessoaFisica) throws SQLException {
        String sqlPessoa = "INSERT INTO Pessoa (pessoaid, nome, logradouro, cidade, estado, telefone, email) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String sqlPessoaFisica = "INSERT INTO PessoasFisicas (pessoaid, cpf) VALUES (?, ?)";

        Connection conn = null;
        PreparedStatement stmtPessoa = null;
        PreparedStatement stmtPessoaFisica = null;

        try {
            conn = ConectorBD.getConnection();
            conn.setAutoCommit(false);

            int novoId = (int) SequenceManager.getValue("Seq_PessoaID");
           
            stmtPessoa = ConectorBD.getPrepared(conn, sqlPessoa);
            stmtPessoa.setInt(1, novoId);
            stmtPessoa.setString(2, pessoaFisica.getNome());
            stmtPessoa.setString(3, pessoaFisica.getLogradouro());
            stmtPessoa.setString(4, pessoaFisica.getCidade());
            stmtPessoa.setString(5, pessoaFisica.getEstado());
            stmtPessoa.setString(6, pessoaFisica.getTelefone());
            stmtPessoa.setString(7, pessoaFisica.getEmail());
            stmtPessoa.executeUpdate();

            stmtPessoaFisica = ConectorBD.getPrepared(conn, sqlPessoaFisica);
            stmtPessoaFisica.setInt(1, novoId);
            stmtPessoaFisica.setString(2, pessoaFisica.getCpf());
            stmtPessoaFisica.executeUpdate();

            conn.commit(); 

        } catch (SQLException e) {
            if (conn != null) conn.rollback(); 
            throw e;
        } finally {
            ConectorBD.close(stmtPessoaFisica);
            ConectorBD.close(stmtPessoa);
            ConectorBD.close(conn);
        }
    }

    public void alterar(PessoaFisica pessoaFisica) throws SQLException {
        String sqlPessoa = "UPDATE Pessoa SET nome = ?, logradouro = ?, cidade = ?, estado = ?, telefone = ?, email = ? WHERE pessoaid = ?";
        String sqlPessoaFisica = "UPDATE PessoasFisicas SET cpf = ? WHERE pessoaid = ?";

        Connection conn = null;
        PreparedStatement stmtPessoa = null;
        PreparedStatement stmtPessoaFisica = null;

        try {
            conn = ConectorBD.getConnection();
            conn.setAutoCommit(false);

            stmtPessoa = ConectorBD.getPrepared(conn, sqlPessoa);
            stmtPessoa.setString(1, pessoaFisica.getNome());
            stmtPessoa.setString(2, pessoaFisica.getLogradouro());
            stmtPessoa.setString(3, pessoaFisica.getCidade());
            stmtPessoa.setString(4, pessoaFisica.getEstado());
            stmtPessoa.setString(5, pessoaFisica.getTelefone());
            stmtPessoa.setString(6, pessoaFisica.getEmail());
            stmtPessoa.setInt(7, pessoaFisica.getId());
            stmtPessoa.executeUpdate();

            stmtPessoaFisica = ConectorBD.getPrepared(conn, sqlPessoaFisica);
            stmtPessoaFisica.setString(1, pessoaFisica.getCpf());
            stmtPessoaFisica.setInt(2, pessoaFisica.getId());
            stmtPessoaFisica.executeUpdate();

            conn.commit();

        } catch (SQLException e) {
            if (conn != null) conn.rollback();
            throw e;
        } finally {
            ConectorBD.close(stmtPessoaFisica);
            ConectorBD.close(stmtPessoa);
            ConectorBD.close(conn);
        }
    }

    public void excluir(int id) throws SQLException {
        String sqlPessoaFisica = "DELETE FROM PessoasFisicas WHERE pessoaid = ?";
        String sqlPessoa = "DELETE FROM Pessoa WHERE pessoaid = ?";

        Connection conn = null;
        PreparedStatement stmtPessoaFisica = null;
        PreparedStatement stmtPessoa = null;

        try {
            conn = ConectorBD.getConnection();
            conn.setAutoCommit(false);

            stmtPessoaFisica = ConectorBD.getPrepared(conn, sqlPessoaFisica);
            stmtPessoaFisica.setInt(1, id);
            stmtPessoaFisica.executeUpdate();

            stmtPessoa = ConectorBD.getPrepared(conn, sqlPessoa);
            stmtPessoa.setInt(1, id);
            stmtPessoa.executeUpdate();

            conn.commit();

        } catch (SQLException e) {
            if (conn != null) conn.rollback();
            throw e;
        } finally {
            ConectorBD.close(stmtPessoaFisica);
            ConectorBD.close(stmtPessoa);
            ConectorBD.close(conn);
        }
    }
}