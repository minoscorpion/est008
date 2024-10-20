/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastrodb.model;

/**
 *
 * @author rafae
 */

public class Pessoa {
    private int id;
    private String nome;
    private String logradouro;
    private String cidade;
    private String estado;
    private String telefone;
    private String email;

    public Pessoa() {
    }

    public Pessoa(int id, String nome, String logradouro, String cidade, String estado, String telefone, String email) {
        this.id = id;
        this.nome = nome;
        this.logradouro = logradouro;
        this.cidade = cidade;
        this.estado = estado;
        this.telefone = telefone;
        this.email = email;
    }

    public void exibir() {
        System.out.println("ID: " + id);
        System.out.println("Nome: " + nome);
        System.out.println("Logradouro: " + logradouro);
        System.out.println("Cidade: " + cidade);
        System.out.println("Estado: " + estado);
        System.out.println("Telefone: " + telefone);
        System.out.println("Email: " + email);
    }
    
    public int getId()
    {
        return id;
    }
     
    public String getNome()
    {
        return nome;
    }
    
    public void setNome(String nome)
    {
        nome = this.nome;
    }
    
    public String getLogradouro()
    {
        return logradouro;
    }
    
    public void setLogradouro(String logradouro)
    {
         logradouro = this.logradouro;
    }
    
    public String getCidade()
    {
        return cidade;
    }
    
    public void setCidade(String cidade)
    {
        cidade = this.cidade;
    }
      
    public String getEstado()
    {
        return estado;
    }
    
    public void setEstado(String estado)
    {
        estado = this.estado;
    }
    
    public String getTelefone()
    {
        return telefone;
    }
    
    public void setTelefone(String telefone)
    {
        telefone = this.telefone;
    }
    
    public String getEmail()
    {
        return email;
    }
    
    public void setEmail(String email)
    {
        email =  this.email;
    }
}