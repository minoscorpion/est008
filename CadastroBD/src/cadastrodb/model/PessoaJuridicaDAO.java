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

public class PessoaJuridicaDAO {

    public PessoaJuridica getPessoa(int id) throws SQLException {
        PessoaJuridica pessoaJuridica = null;
        String sql = "SELECT * FROM Pessoa p JOIN PessoasJuridicas pj ON p.pessoaid = pj.pessoaid WHERE p.pessoaid  = ?";

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConectorBD.getConnection();
            stmt = ConectorBD.getPrepared(conn, sql);
            stmt.setInt(1, id);
            rs = ConectorBD.getSelect(stmt);

            if (rs.next()) {
                pessoaJuridica = new PessoaJuridica(
                    rs.getInt("pessoaid"),
                    rs.getString("nome"),
                    rs.getString("logradouro"),
                    rs.getString("cidade"),
                    rs.getString("estado"),
                    rs.getString("telefone"),
                    rs.getString("email"),
                    rs.getString("cnpj")
                );
            }
        } finally {
            ConectorBD.close(rs);
            ConectorBD.close(stmt);
            ConectorBD.close(conn);
        }

        return pessoaJuridica;
    }

    public List<PessoaJuridica> getPessoas() throws SQLException {
        List<PessoaJuridica> pessoas = new ArrayList<>();
        String sql = "SELECT * FROM Pessoa p JOIN PessoasJuridicas pj ON p.pessoaid = pj.pessoaid";

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConectorBD.getConnection();
            stmt = ConectorBD.getPrepared(conn, sql);
            rs = ConectorBD.getSelect(stmt);

            while (rs.next()) {
                PessoaJuridica pessoaJuridica = new PessoaJuridica(
                    rs.getInt("pessoaid"),
                    rs.getString("nome"),
                    rs.getString("logradouro"),
                    rs.getString("cidade"),
                    rs.getString("estado"),
                    rs.getString("telefone"),
                    rs.getString("email"),
                    rs.getString("cnpj")
                );
                pessoas.add(pessoaJuridica);
            }
        } finally {
            ConectorBD.close(rs);
            ConectorBD.close(stmt);
            ConectorBD.close(conn);
        }

        return pessoas;
    }

    public void incluir(PessoaJuridica pessoaJuridica) throws SQLException {
        String sqlPessoa = "INSERT INTO Pessoa (pessoaid, nome, logradouro, cidade, estado, telefone, email) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String sqlPessoaJuridica = "INSERT INTO PessoasJuridicas (pessoaid, cnpj) VALUES (?, ?)";

        Connection conn = null;
        PreparedStatement stmtPessoa = null;
        PreparedStatement stmtPessoaJuridica = null;

        try {
            conn = ConectorBD.getConnection();
            conn.setAutoCommit(false); 

            int novoId = (int) SequenceManager.getValue("Seq_PessoaID");

            stmtPessoa = ConectorBD.getPrepared(conn, sqlPessoa);
            stmtPessoa.setInt(1, novoId);
            stmtPessoa.setString(2, pessoaJuridica.getNome());
            stmtPessoa.setString(3, pessoaJuridica.getLogradouro());
            stmtPessoa.setString(4, pessoaJuridica.getCidade());
            stmtPessoa.setString(5, pessoaJuridica.getEstado());
            stmtPessoa.setString(6, pessoaJuridica.getTelefone());
            stmtPessoa.setString(7, pessoaJuridica.getEmail());
            stmtPessoa.executeUpdate();

            stmtPessoaJuridica = ConectorBD.getPrepared(conn, sqlPessoaJuridica);
            stmtPessoaJuridica.setInt(1, novoId);
            stmtPessoaJuridica.setString(2, pessoaJuridica.getCnpj());
            stmtPessoaJuridica.executeUpdate();

            conn.commit();

        } catch (SQLException e) {
            if (conn != null) conn.rollback(); 
            throw e;
        } finally {
            ConectorBD.close(stmtPessoaJuridica);
            ConectorBD.close(stmtPessoa);
            ConectorBD.close(conn);
        }
    }

    public void alterar(PessoaJuridica pessoaJuridica) throws SQLException {
        String sqlPessoa = "UPDATE Pessoa SET nome = ?, logradouro = ?, cidade = ?, estado = ?, telefone = ?, email = ? WHERE pessoaid = ?";
        String sqlPessoaJuridica = "UPDATE PessoasJuridicas SET cnpj = ? WHERE pessoaid = ?";

        Connection conn = null;
        PreparedStatement stmtPessoa = null;
        PreparedStatement stmtPessoaJuridica = null;

        try {
            conn = ConectorBD.getConnection();
            conn.setAutoCommit(false);

            stmtPessoa = ConectorBD.getPrepared(conn, sqlPessoa);
            stmtPessoa.setString(1, pessoaJuridica.getNome());
            stmtPessoa.setString(2, pessoaJuridica.getLogradouro());
            stmtPessoa.setString(3, pessoaJuridica.getCidade());
            stmtPessoa.setString(4, pessoaJuridica.getEstado());
            stmtPessoa.setString(5, pessoaJuridica.getTelefone());
            stmtPessoa.setString(6, pessoaJuridica.getEmail());
            stmtPessoa.setInt(7, pessoaJuridica.getId());
            stmtPessoa.executeUpdate();

            stmtPessoaJuridica = ConectorBD.getPrepared(conn, sqlPessoaJuridica);
            stmtPessoaJuridica.setString(1, pessoaJuridica.getCnpj());
            stmtPessoaJuridica.setInt(2, pessoaJuridica.getId());
            stmtPessoaJuridica.executeUpdate();

            conn.commit();

        } catch (SQLException e) {
            if (conn != null) conn.rollback();
            throw e;
        } finally {
            ConectorBD.close(stmtPessoaJuridica);
            ConectorBD.close(stmtPessoa);
            ConectorBD.close(conn);
        }
    }

    public void excluir(int id) throws SQLException {
        String sqlPessoaJuridica = "DELETE FROM PessoasJuridicas WHERE pessoaid = ?";
        String sqlPessoa = "DELETE FROM Pessoa WHERE pessoaid = ?";

        Connection conn = null;
        PreparedStatement stmtPessoaJuridica = null;
        PreparedStatement stmtPessoa = null;

        try {
            conn = ConectorBD.getConnection();
            conn.setAutoCommit(false);

            stmtPessoaJuridica = ConectorBD.getPrepared(conn, sqlPessoaJuridica);
            stmtPessoaJuridica.setInt(1, id);
            stmtPessoaJuridica.executeUpdate();
            
            stmtPessoa = ConectorBD.getPrepared(conn, sqlPessoa);
            stmtPessoa.setInt(1, id);
            stmtPessoa.executeUpdate();

            conn.commit();

        } catch (SQLException e) {
            if (conn != null) conn.rollback();
            throw e;
        } finally {
            ConectorBD.close(stmtPessoaJuridica);
            ConectorBD.close(stmtPessoa);
            ConectorBD.close(conn);
        }
    }
}