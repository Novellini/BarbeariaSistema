
package barbearia.model;

import java.time.LocalDateTime;

public class Agendamento {
    private int idAgendamento;
    private int idCliente;
    private int idBarbeiro;
    private LocalDateTime dataHora;
    private String status;
    private String observacao;
    
    //Construtor para inserir agendamento
public Agendamento(int idCliente, int idBarbeiro, LocalDateTime dataHora, String status, String observacao){
    this.idCliente = idCliente;
    this.idBarbeiro = idBarbeiro;
    this.dataHora = dataHora;
    this.status = status;
    this.observacao = observacao;
}

    //Construtor para buscar
public Agendamento(int idAgendamento, int idCliente, int idBarbeiro, LocalDateTime dataHora, String status, String observacao){
    this.idAgendamento = idAgendamento;
    this.idCliente = idCliente;
    this.idBarbeiro = idBarbeiro;
    this.dataHora = dataHora;
    this.observacao = observacao;
    this.status = status;
}


public int getIdAgendamento() {return idAgendamento;}
public int getIdCliente() {return idCliente;}
public int getIdBarbeiro() {return idBarbeiro;}
public LocalDateTime getDataHora() {return dataHora; }
public String getObservacao() {return observacao;}
public String getStatus() {return status;}


public void setIdAgendamento(int idAgendamento) {this.idAgendamento = idAgendamento;}
public void setIdCliente(int idCliente) {this.idCliente = idCliente;}
public void setIdBarbeiro(int idBarbeiro) {this.idBarbeiro = idBarbeiro;}
public void setDataHora(LocalDateTime dataHora) {this.dataHora = dataHora;}
public void setStatus(String status) {this.status = status;}
public void setObservacao(String observacao) {this.observacao = observacao;}

@Override
public String toString() {
    return "Agendamento " +idAgendamento + "-" + status;
}

}
