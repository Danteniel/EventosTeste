package projeto;

import java.time.LocalDateTime;

public class Evento {
    private String nome;
    private String endereco;
    private String categoria;
    private LocalDateTime dataHora;
    private String descricao;

    public Evento(String nome, String endereco, String categoria, LocalDateTime dataHora, String descricao) {
        this.nome = nome;
        this.endereco = endereco;
        this.categoria = categoria;
        this.dataHora = dataHora;
        this.descricao = descricao;
    }

    public String getNome() { return nome; }
    public String getEndereco() { return endereco; }
    public String getCategoria() { return categoria; }
    public LocalDateTime getDataHora() { return dataHora; }
    public String getDescricao() { return descricao; }

    @Override
    public String toString() {
        return nome + " (" + categoria + ") em " + endereco + " no dia " + dataHora + "\nDescrição: " + descricao;
    }
}
