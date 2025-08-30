package projeto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class SistemaEventos {

    private static final String ARQ_EVENTOS = "events.data";
    private static final String ARQ_USUARIOS = "users.data";

    private final List<Usuario> usuarios = new ArrayList<>();
    private final List<Evento> eventos   = new ArrayList<>();

    public SistemaEventos() {
        Repositorio.carregarEventos(ARQ_EVENTOS, eventos);
        Repositorio.carregarUsuarios(ARQ_USUARIOS, usuarios, eventos);
    }

   
    public void cadastrarUsuario(String nome, String email, int idade, String telefone, String senha) {
        Usuario u = new Usuario(nome, email, idade, telefone, senha);
        usuarios.add(u);
        System.out.println("Usuário cadastrado com sucesso!");
        System.out.println(u);
        salvarUsuarios(); 
    }

   
    public void cadastrarEvento(String nome, String endereco, String categoria,
                                int ano, int mes, int dia, int hora, int minuto, String descricao) {
        LocalDateTime dataHora = LocalDateTime.of(ano, mes, dia, hora, minuto);
        Evento e = new Evento(nome, endereco, categoria, dataHora, descricao);
        eventos.add(e);
        System.out.println("Evento cadastrado com sucesso!");
        salvarEventos();
    }

    
    public void listarEventosTodos() {
        if (eventos.isEmpty()) {
            System.out.println("Nenhum evento cadastrado.");
            return;
        }
        for (int i = 0; i < eventos.size(); i++) {
            System.out.println("[" + i + "] " + eventos.get(i));
        }
    }

    public void listarEventosPorCategoria(String categoria) {
        boolean achou = false;
        for (Evento e : eventos) {
            if (e.getCategoria().equalsIgnoreCase(categoria)) {
                System.out.println(e);
                achou = true;
            }
        }
        if (!achou) System.out.println("Nenhum evento encontrado na categoria: " + categoria);
    }

    public void listarEventosPassados() {
        boolean achou = false;
        LocalDate hoje = LocalDate.now();
        for (Evento e : eventos) {
            if (e.getDataHora().toLocalDate().isBefore(hoje)) {
                System.out.println(e);
                achou = true;
            }
        }
        if (!achou) System.out.println("Nenhum evento passado encontrado.");
    }

    public void listarEventosHoje() {
        boolean achou = false;
        LocalDate hoje = LocalDate.now();
        for (Evento e : eventos) {
            if (e.getDataHora().toLocalDate().isEqual(hoje)) {
                System.out.println(e);
                achou = true;
            }
        }
        if (!achou) System.out.println("Nenhum evento em andamento hoje.");
    }

    public void listarEventosFuturos() {
        boolean achou = false;
        LocalDate hoje = LocalDate.now();
        for (Evento e : eventos) {
            if (e.getDataHora().toLocalDate().isAfter(hoje)) {
                System.out.println(e);
                achou = true;
            }
        }
        if (!achou) System.out.println("Nenhum evento futuro encontrado.");
    }


    public void participarEventoViaConsole(Scanner scanner) {
        if (usuarios.isEmpty() || eventos.isEmpty()) {
            System.out.println("Cadastre pelo menos um usuário e um evento primeiro.");
            return;
        }

        System.out.println("Escolha o usuário:");
        for (int i = 0; i < usuarios.size(); i++) {
            System.out.println("[" + i + "] " + usuarios.get(i).getNome());
        }
        int iUsuario = lerIndice(scanner, usuarios.size());
        Usuario usuario = usuarios.get(iUsuario);

        System.out.println("Escolha o evento:");
        for (int i = 0; i < eventos.size(); i++) {
            Evento e = eventos.get(i);
            System.out.println("[" + i + "] " + e.getNome() + " - " + e.getDataHora());
        }
        int iEvento = lerIndice(scanner, eventos.size());
        Evento evento = eventos.get(iEvento);

        usuario.participarEvento(evento);
        salvarUsuarios(); 
    }

    public void cancelarParticipacaoViaConsole(Scanner scanner) {
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
            return;
        }

        System.out.println("Escolha o usuário:");
        for (int i = 0; i < usuarios.size(); i++) {
            System.out.println("[" + i + "] " + usuarios.get(i).getNome());
        }
        int iUsuario = lerIndice(scanner, usuarios.size());
        Usuario usuario = usuarios.get(iUsuario);

        if (usuario.getEventosParticipando().isEmpty()) {
            System.out.println("Esse usuário não está inscrito em nenhum evento.");
            return;
        }

        System.out.println("Escolha o evento para cancelar:");
        for (int i = 0; i < usuario.getEventosParticipando().size(); i++) {
            System.out.println("[" + i + "] " + usuario.getEventosParticipando().get(i).getNome());
        }
        int iEvento = lerIndice(scanner, usuario.getEventosParticipando().size());
        Evento evento = usuario.getEventosParticipando().get(iEvento);

        usuario.cancelarParticipacao(evento);
        salvarUsuarios();
    }

    public void listarEventosUsuarioViaConsole(Scanner scanner) {
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
            return;
        }

        System.out.println("Escolha o usuário:");
        for (int i = 0; i < usuarios.size(); i++) {
            System.out.println("[" + i + "] " + usuarios.get(i).getNome());
        }
        int iUsuario = lerIndice(scanner, usuarios.size());
        Usuario usuario = usuarios.get(iUsuario);

        if (usuario.getEventosParticipando().isEmpty()) {
            System.out.println("Esse usuário não está participando de nenhum evento.");
        } else {
            System.out.println("Eventos de " + usuario.getNome() + ":");
            for (Evento e : usuario.getEventosParticipando()) {
                System.out.println(" - " + e.getNome() + " em " + e.getDataHora());
            }
        }
    }

    private int lerIndice(Scanner s, int limite) {
        int idx = -1;
        while (idx < 0 || idx >= limite) {
            while (!s.hasNextInt()) {
                System.out.print("Digite um número válido: ");
                s.next();
            }
            idx = s.nextInt();
            s.nextLine(); 
            if (idx < 0 || idx >= limite) System.out.print("Índice fora da faixa. Tente novamente: ");
        }
        return idx;
    }
    
    private void salvarEventos() {
        Repositorio.salvarEventos(ARQ_EVENTOS, eventos);
    }

    private void salvarUsuarios() {
        Repositorio.salvarUsuarios(ARQ_USUARIOS, usuarios);
    }

    public void salvarTudo() {
        salvarEventos();
        salvarUsuarios();
    }
}
