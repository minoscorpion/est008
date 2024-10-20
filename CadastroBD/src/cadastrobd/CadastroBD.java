/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cadastrobd;

import cadastrodb.model.PessoaFisica;
import cadastrodb.model.PessoaFisicaDAO;
import cadastrodb.model.PessoaJuridica;
import cadastrodb.model.PessoaJuridicaDAO;
import java.util.List;
import java.util.Scanner;
import java.sql.SQLException;

/**
 *
 * @author rafae
 */
public class CadastroBD {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();
        PessoaJuridicaDAO pessoaJuridicaDAO = new PessoaJuridicaDAO();

        try {
            int opcao;
            do {
                System.out.println("\n--- Menu de Opcoes ---");
                System.out.println("1. Incluir");
                System.out.println("2. Alterar");
                System.out.println("3. Excluir");
                System.out.println("4. Exibir pelo ID");
                System.out.println("5. Exibir todos");
                System.out.println("0. Sair");
                System.out.print("Escolha uma opcao: ");
                opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1: 
                        incluir(scanner, pessoaFisicaDAO, pessoaJuridicaDAO);
                        break;
                    case 2: 
                        alterar(scanner, pessoaFisicaDAO, pessoaJuridicaDAO);
                        break;
                    case 3: 
                        excluir(scanner, pessoaFisicaDAO, pessoaJuridicaDAO);
                        break;
                    case 4: 
                        exibirPorId(scanner, pessoaFisicaDAO, pessoaJuridicaDAO);
                        break;
                    case 5: 
                        exibirTodos(scanner, pessoaFisicaDAO, pessoaJuridicaDAO);
                        break;
                    case 0: 
                        System.out.println("Encerrando o sistema...");
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } while (opcao != 0);

        } catch (SQLException e) {
            System.err.println("Erro de SQL: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    private static void incluir(Scanner scanner, PessoaFisicaDAO pessoaFisicaDAO, PessoaJuridicaDAO pessoaJuridicaDAO) throws SQLException {
        System.out.println("Incluir Pessoa (1. Fisica, 2. Juridica): ");
        int tipo = scanner.nextInt();
        scanner.nextLine(); 

        if (tipo == 1) {
            System.out.println("Inserir nova Pessoa Fisica");
            PessoaFisica pf = capturarPessoaFisica(scanner);
            pessoaFisicaDAO.incluir(pf);
            System.out.println("Pessoa Fisica incluída com sucesso!");
        } else if (tipo == 2) {
            System.out.println("Inserir nova Pessoa Juridica");
            PessoaJuridica pj = capturarPessoaJuridica(scanner);
            pessoaJuridicaDAO.incluir(pj);
            System.out.println("Pessoa Juridica incluída com sucesso!");
        } else {
            System.out.println("Tipo inválido!");
        }
    }

    private static void alterar(Scanner scanner, PessoaFisicaDAO pessoaFisicaDAO, PessoaJuridicaDAO pessoaJuridicaDAO) throws SQLException {
        System.out.println("Alterar Pessoa (1. Fisica, 2. Juridica): ");
        int tipo = scanner.nextInt();
        scanner.nextLine(); 

        if (tipo == 1) {
            System.out.print("Informe o ID da Pessoa Fisica: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            PessoaFisica pf = pessoaFisicaDAO.getPessoa(id);
            if (pf != null) {
                System.out.println("Dados atuais: " + pf);
                System.out.println("Digite os novos dados:");
                pf = capturarPessoaFisica(scanner, pf);
                pessoaFisicaDAO.alterar(pf);
                System.out.println("Pessoa Fisica alterada com sucesso!");
            } else {
                System.out.println("Pessoa Fisica nao encontrada.");
            }
        } else if (tipo == 2) {
            System.out.print("Informe o ID da Pessoa Juridica: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            PessoaJuridica pj = pessoaJuridicaDAO.getPessoa(id);
            if (pj != null) {
                System.out.println("Dados atuais: " + pj);
                System.out.println("Digite os novos dados:");
                pj = capturarPessoaJuridica(scanner, pj);
                pessoaJuridicaDAO.alterar(pj);
                System.out.println("Pessoa Juridica alterada com sucesso!");
            } else {
                System.out.println("Pessoa Juridica nao encontrada.");
            }
        } else {
            System.out.println("Tipo inválido!");
        }
    }

    private static void excluir(Scanner scanner, PessoaFisicaDAO pessoaFisicaDAO, PessoaJuridicaDAO pessoaJuridicaDAO) throws SQLException {
        System.out.println("Excluir Pessoa (1. Fisica, 2. Juridica): ");
        int tipo = scanner.nextInt();
        scanner.nextLine(); 

        if (tipo == 1) {
            System.out.print("Informe o ID da Pessoa Fisica a ser excluída: ");
            int id = scanner.nextInt();
            pessoaFisicaDAO.excluir(id);
            System.out.println("Pessoa Fisica excluída com sucesso!");
        } else if (tipo == 2) {
            System.out.print("Informe o ID da Pessoa Juridica a ser excluída: ");
            int id = scanner.nextInt();
            pessoaJuridicaDAO.excluir(id);
            System.out.println("Pessoa Juridica excluída com sucesso!");
        } else {
            System.out.println("Tipo inválido!");
        }
    }

    private static void exibirPorId(Scanner scanner, PessoaFisicaDAO pessoaFisicaDAO, PessoaJuridicaDAO pessoaJuridicaDAO) throws SQLException {
        System.out.println("Exibir Pessoa por ID (1. Fisica, 2. Juridica): ");
        int tipo = scanner.nextInt();
        scanner.nextLine();

        if (tipo == 1) {
            System.out.print("Informe o ID da Pessoa Fisica: ");
            int id = scanner.nextInt();
            PessoaFisica pf = pessoaFisicaDAO.getPessoa(id);
            if (pf != null) {
                System.out.println("Pessoa Fisica: " + pf);
            } else {
                System.out.println("Pessoa Fisica nao encontrada.");
            }
        } else if (tipo == 2) {
            System.out.print("Informe o ID da Pessoa Juridica: ");
            int id = scanner.nextInt();
            PessoaJuridica pj = pessoaJuridicaDAO.getPessoa(id);
            if (pj != null) {
                System.out.println("Pessoa Juridica: " + pj);
            } else {
                System.out.println("Pessoa Juridica nao encontrada.");
            }
        } else {
            System.out.println("Tipo invalido!");
        }
    }

    private static void exibirTodos(Scanner scanner, PessoaFisicaDAO pessoaFisicaDAO, PessoaJuridicaDAO pessoaJuridicaDAO) throws SQLException {
        System.out.println("Exibir todas as Pessoas (1. Fisica, 2. Juridica): ");
        int tipo = scanner.nextInt();
        scanner.nextLine(); 

        if (tipo == 1) {
            List<PessoaFisica> pessoasFisicas = pessoaFisicaDAO.getPessoas();
            System.out.println("Lista de Pessoas Fisicas:");
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
        } else if (tipo == 2) {
            List<PessoaJuridica> pessoasJuridicas = pessoaJuridicaDAO.getPessoas();
            System.out.println("Lista de Pessoas Juridicas:");
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
        } else {
            System.out.println("Tipo inválido!");
        }
    }

    private static PessoaFisica capturarPessoaFisica(Scanner scanner) {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Logradouro: ");
        String logradouro = scanner.nextLine();
        System.out.print("Cidade: ");
        String cidade = scanner.nextLine();
        System.out.print("Estado: ");
        String estado = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        return new PessoaFisica(0, nome, logradouro, cidade, estado, telefone, email, cpf);
    }
   
    private static PessoaJuridica capturarPessoaJuridica(Scanner scanner) {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Logradouro: ");
        String logradouro = scanner.nextLine();
        System.out.print("Cidade: ");
        String cidade = scanner.nextLine();
        System.out.print("Estado: ");
        String estado = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("CNPJ: ");
        String cnpj = scanner.nextLine();
        return new PessoaJuridica(0, nome, logradouro, cidade, estado, telefone, email, cnpj);
    }

    private static PessoaFisica capturarPessoaFisica(Scanner scanner, PessoaFisica pf) {
        System.out.print("Nome (" + pf.getNome() + "): ");
        pf.setNome(scanner.nextLine());
        System.out.print("Logradouro (" + pf.getLogradouro() + "): ");
        pf.setLogradouro(scanner.nextLine());
        System.out.print("Cidade (" + pf.getCidade() + "): ");
        pf.setCidade(scanner.nextLine());
        System.out.print("Estado (" + pf.getEstado() + "): ");
        pf.setEstado(scanner.nextLine());
        System.out.print("Telefone (" + pf.getTelefone() + "): ");
        pf.setTelefone(scanner.nextLine());
        System.out.print("Email (" + pf.getEmail() + "): ");
        pf.setEmail(scanner.nextLine());
        System.out.print("CPF (" + pf.getCpf() + "): ");
        pf.setCpf(scanner.nextLine());
        return pf;
    }

    private static PessoaJuridica capturarPessoaJuridica(Scanner scanner, PessoaJuridica pj) {
        System.out.print("Nome (" + pj.getNome() + "): ");
        pj.setNome(scanner.nextLine());
        System.out.print("Logradouro (" + pj.getLogradouro() + "): ");
        pj.setLogradouro(scanner.nextLine());
        System.out.print("Cidade (" + pj.getCidade() + "): ");
        pj.setCidade(scanner.nextLine());
        System.out.print("Estado (" + pj.getEstado() + "): ");
        pj.setEstado(scanner.nextLine());
        System.out.print("Telefone (" + pj.getTelefone() + "): ");
        pj.setTelefone(scanner.nextLine());
        System.out.print("Email (" + pj.getEmail() + "): ");
        pj.setEmail(scanner.nextLine());
        System.out.print("CNPJ (" + pj.getCnpj() + "): ");
        pj.setCnpj(scanner.nextLine());
        return pj;
    }
    
}
