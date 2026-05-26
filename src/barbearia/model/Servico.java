package barbearia.model;

import java.math.BigDecimal;

public class Servico {
    private int        idServico;
    private String     nome;
    private String     descricao;
    private BigDecimal preco;
    private int        duracaoMin;
    private boolean    ativo;

    // Construtor para Inserir 
    public Servico(String nome, String descricao, BigDecimal preco, int duracaoMin, boolean ativo) {
        this.nome       = nome;
        this.descricao  = descricao;
        this.preco      = preco;
        this.duracaoMin = duracaoMin;
        this.ativo      = ativo;
    }

    // Construtor para Buscar
    public Servico(int idServico, String nome, String descricao, BigDecimal preco, int duracaoMin, boolean ativo) {
        this.idServico  = idServico;
        this.nome       = nome;
        this.descricao  = descricao;
        this.preco      = preco;
        this.duracaoMin = duracaoMin;
        this.ativo      = ativo;
    }

   
    public int        getIdServico()  { return idServico; }
    public String     getNome()       { return nome; }
    public String     getDescricao()  { return descricao; }
    public BigDecimal getPreco()      { return preco; }
    public int        getDuracaoMin() { return duracaoMin; }
    public boolean    isAtivo()       { return ativo; }

    
    public void setIdServico(int idServico)    { this.idServico  = idServico; }
    public void setNome(String nome)           { this.nome       = nome; }
    public void setDescricao(String descricao) { this.descricao  = descricao; }
    public void setPreco(BigDecimal preco)     { this.preco      = preco; }
    public void setDuracaoMin(int duracaoMin)  { this.duracaoMin = duracaoMin; }
    public void setAtivo(boolean ativo)        { this.ativo      = ativo; }

    @Override
    public String toString() {
        return nome + " — R$ " + preco;
    }
}