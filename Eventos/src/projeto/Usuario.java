package projeto;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String nome;
    private String email;
    private int idade;
    private String telefone;
    private String senha;
    private List<Evento> eventosParticipando;

    public Usuario(String nome, String email, int idade, String telefone, String senha) {
        this.nome = nome;
        this.email = email;
        this.idade = idade;
        this.telefone = telefone;
        this.senha = senha;
        this.eventosParticipando = new ArrayList<>();
    }

    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public int getIdade() { return idade; }
    public String getTelefone() { return telefone; }
    public String getSenha() { return senha; }
    public List<Evento> getEventosParticipando() { return eventosParticipando; }

    public void participarEvento(Evento evento) {
        if (!eventosParticipando.contains(evento)) {
            eventosParticipando.add(evento);
            System.out.println(nome + " confirmado no evento: " + evento.getNome());
        } else {
            System.out.println("Usuário já está inscrito nesse evento.");
        }
    }

    public void cancelarParticipacao(Evento evento) {
        if (eventosParticipando.remove(evento)) {
            System.out.println("Participação cancelada no evento: " + evento.getNome());
        } else {
            System.out.println("Usuário não estava inscrito nesse evento.");
        }
    }

    @Override
    public String toString() {
        return "Usuário: " + nome
                + " | Email: " + email
                + " | Idade: " + idade
                + " | Telefone: " + telefone;
    }
}
