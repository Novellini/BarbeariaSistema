
package barbearia.model;


public class Barbeiro {
    private int id_barbeiro;
    private String nome;
    private String telefone;
    private String especialidade;
    private boolean ativo;
    
    //Construtor para inserir novo barbeiro
    public Barbeiro (String nome, String telefone, String especialidade, boolean ativo){
            this.nome =     nome;
            this.telefone = telefone;
            this.especialidade = especialidade;
            this.ativo  = ativo;
            
}
    //Construtor para buscar algum barbeiro
    public Barbeiro(int id_barbeiro, String nome,String telefone, String especialidade, boolean ativo){
        this.id_barbeiro = id_barbeiro;
        this.nome = nome;
        this.telefone = telefone;
        this.especialidade = especialidade;
        this.ativo = ativo;
    }
    public int      getId_barbeiro() {return id_barbeiro;}
    public String   getNome() {return nome;}
    public String   getTelefone() {return telefone;}
    public String   getEspecialidade() {return especialidade;}
    public boolean   isAtivo()  {return ativo;}
    
    public void setNome(String nome) {this.nome = nome;}
    public void setTelefone(String telefone) {this.telefone = telefone;}
    public void setEspecialidade(String especialidade) {this.especialidade = especialidade;}
    public void setAtvio(boolean ativo) {this.ativo = ativo;}
    public void setIdBarbeiro(int idBarbeiro) {this.id_barbeiro = idBarbeiro;}
    
    @Override
    public String toString() {
        return nome + " — " + telefone;
    }

}
