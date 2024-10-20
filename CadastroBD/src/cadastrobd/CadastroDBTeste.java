/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastrobd;

import cadastrodb.model.PessoaFisica;
import cadastrodb.model.PessoaFisicaDAO;
import cadastrodb.model.PessoaJuridica;
import cadastrodb.model.PessoaJuridicaDAO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author rafae
 */
public class CadastroDBTeste {
    
    public static void main(String[] args) {
        try {
            PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();
            PessoaFisica pessoaFisica = new PessoaFisica(0, "Rafael RT", "Rua A", "Cidade XZZ", "Estado SY", "1111-1111", "rafael@domain.com", "23524325887");
            pessoaFisicaDAO.incluir(pessoaFisica);
            System.out.println("Pessoa Física incluída com sucesso!");

            pessoaFisica.setNome("Rafael Silva 2");
            pessoaFisica.setEmail("rafael@domain.com.br");
            pessoaFisicaDAO.alterar(pessoaFisica);
            System.out.println("Pessoa Física alterada com sucesso!");

            List<PessoaFisica> pessoasFisicas = pessoaFisicaDAO.getPessoas();
            System.out.println("\nLista de Pessoas Físicas:");
            for (PessoaFisica pf : pessoasFisicas) {
                System.out.println("Nome: " + pf.getNome());
                System.out.println("logradouro: " + pf.getLogradouro());
                System.out.println("Cidade: " + pf.getCidade());
                System.out.println("Estado: " + pf.getEstado());
                System.out.println("Telefone: " + pf.getTelefone());
                System.out.println("E-mail: " + pf.getEmail());
                System.out.println("CPF: " + pf.getCpf());
                System.out.println("id: " + pf.getId());
            }

            pessoaFisicaDAO.excluir(pessoaFisica.getId());
            System.out.println("Pessoa Física excluída com sucesso!");

            PessoaJuridicaDAO pessoaJuridicaDAO = new PessoaJuridicaDAO();
            PessoaJuridica pessoaJuridica = new PessoaJuridica(0, "Empresa XYZ", "Av. Central", "Cidade Y", "Estado Z", "2222-2222", "contato@empresa.com", "11429978000199");
            pessoaJuridicaDAO.incluir(pessoaJuridica);
            System.out.println("Pessoa Jurídica incluída com sucesso!");

            pessoaJuridica.setNome("Empresa XYZ Ltda");
            pessoaJuridica.setEmail("contato@xyz.com");
            pessoaJuridicaDAO.alterar(pessoaJuridica);
            System.out.println("Pessoa Jurídica alterada com sucesso!");

            List<PessoaJuridica> pessoasJuridicas = pessoaJuridicaDAO.getPessoas();
            System.out.println("\nLista de Pessoas Jurídicas:");
            for (PessoaJuridica pj : pessoasJuridicas) {
                System.out.println("Nome: " + pj.getNome());
                System.out.println("logradouro: " + pj.getLogradouro());
                System.out.println("Cidade: " + pj.getCidade());
                System.out.println("Estado: " + pj.getEstado());
                System.out.println("Telefone: " + pj.getTelefone());
                System.out.println("E-mail: " + pj.getEmail());
                System.out.println("CNPJ: " + pj.getCnpj());
                System.out.println("id: " + pj.getId());
            }

            pessoaJuridicaDAO.excluir(pessoaJuridica.getId());
            System.out.println("Pessoa Juridica excluida com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }
}