package barbearia.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Cliente {

    private int           idCliente;
    private String        nome;
    private String        telefone;
    private String        email;
    private LocalDate     dataNascimento;
    private LocalDateTime criadoEm;

    // Construtor para INSERIR novo cliente (sem id)
    public Cliente(String nome, String telefone, String email, LocalDate dataNascimento) {
        this.nome           = nome;
        this.telefone       = telefone;
        this.email          = email;
        this.dataNascimento = dataNascimento;
    }

    // Construtor para quando BUSCAR do banco (com id)
    public Cliente(int idCliente, String nome, String telefone, String email,
                   LocalDate dataNascimento, LocalDateTime criadoEm) {
        this.idCliente      = idCliente;
        this.nome           = nome;
        this.telefone       = telefone;
        this.email          = email;
        this.dataNascimento = dataNascimento;
        this.criadoEm       = criadoEm;
    }

    public int           getIdCliente()      { return idCliente; }
    public String        getNome()           { return nome; }
    public String        getTelefone()       { return telefone; }
    public String        getEmail()          { return email; }
    public LocalDate     getDataNascimento() { return dataNascimento; }
    public LocalDateTime getCriadoEm()       { return criadoEm; }

    public void setNome(String nome)           { this.nome = nome; }
    public void setTelefone(String telefone)   { this.telefone = telefone; }
    public void setEmail(String email)         { this.email = email; }
    public void setDataNascimento(LocalDate d) { this.dataNascimento = d; }

    @Override
    public String toString() {
        return nome + " — " + telefone;
    }
}