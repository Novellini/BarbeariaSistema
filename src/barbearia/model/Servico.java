
package barbearia.model;
import java.math.BigDecimal;

public class Servico {
    private int id_servico;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private int duracao_min;
    private boolean ativo;
    
    //Construtor para inserir serviço novo(sem id)
    public Servico(String nome, String descrcao, BigDecimal preco, int duracao_min, boolean ativo){
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.duracao_min = duracao_min;
        this.ativo = ativo;
    }
    
    //Construtor para Buscar no banco
    



}
