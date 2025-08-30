package projeto;

import java.io.*;
import java.time.LocalDateTime;
import java.util.List;

public class Repositorio {

    public static void salvarEventos(String caminho, List<Evento> eventos) {
        try (BufferedWriter w = new BufferedWriter(new FileWriter(caminho))) {
            for (Evento e : eventos) {
                String linha = String.join(";",
                        e.getNome(),
                        e.getEndereco(),
                        e.getCategoria(),
                        e.getDataHora().toString(),
                        e.getDescricao());
                w.write(linha);
                w.newLine();
            }
        } catch (IOException ex) {
            System.out.println("Erro ao salvar eventos: " + ex.getMessage());
        }
    }

    public static void carregarEventos(String caminho, List<Evento> eventos) {
        File f = new File(caminho);
        if (!f.exists()) return;

        eventos.clear();
        try (BufferedReader r = new BufferedReader(new FileReader(f))) {
            String linha;
            while ((linha = r.readLine()) != null) {
                String[] p = linha.split(";", 5);
                if (p.length < 5) continue;
                String nome = p[0];
                String endereco = p[1];
                String categoria = p[2];
                LocalDateTime dataHora = LocalDateTime.parse(p[3]);
                String descricao = p[4];
                eventos.add(new Evento(nome, endereco, categoria, dataHora, descricao));
            }
        } catch (IOException ex) {
            System.out.println("Erro ao carregar eventos: " + ex.getMessage());
        }
    }

    public static void salvarUsuarios(String caminho, List<Usuario> usuarios) {
        try (BufferedWriter w = new BufferedWriter(new FileWriter(caminho))) {
            for (Usuario u : usuarios) {
                StringBuilder sb = new StringBuilder();
                sb.append(u.getNome()).append(";")
                  .append(u.getEmail()).append(";")
                  .append(u.getIdade()).append(";")
                  .append(u.getTelefone()).append(";")
                  .append(u.getSenha()).append(";");

                for (int i = 0; i < u.getEventosParticipando().size(); i++) {
                    Evento e = u.getEventosParticipando().get(i);
                    sb.append(e.getNome()).append("@").append(e.getDataHora().toString());
                    if (i < u.getEventosParticipando().size() - 1) sb.append(",");
                }

                w.write(sb.toString());
                w.newLine();
            }
        } catch (IOException ex) {
            System.out.println("Erro ao salvar usuários: " + ex.getMessage());
        }
    }

    public static void carregarUsuarios(String caminho, List<Usuario> usuarios, List<Evento> eventos) {
        File f = new File(caminho);
        if (!f.exists()) return;

        usuarios.clear();
        try (BufferedReader r = new BufferedReader(new FileReader(f))) {
            String linha;
            while ((linha = r.readLine()) != null) {
                String[] p = linha.split(";", 6);
                if (p.length < 5) continue;

                String nome = p[0];
                String email = p[1];
                int idade = Integer.parseInt(p[2]);
                String telefone = p[3];
                String senha = p[4];

                Usuario u = new Usuario(nome, email, idade, telefone, senha);

                if (p.length == 6 && !p[5].isBlank()) {
                    String[] tokens = p[5].split(",");
                    for (String token : tokens) {
                        token = token.trim();
                        String nomeEvt;
                        LocalDateTime dataEvt = null;

                        if (token.contains("@")) {
                            String[] nx = token.split("@", 2);
                            nomeEvt = nx[0].trim();
                            try {
                                dataEvt = LocalDateTime.parse(nx[1].trim());
                            } catch (Exception ignored) {  }
                        } else {
                            nomeEvt = token;
                        }

                        Evento encontrado = null;

                        if (dataEvt != null) {
                            for (Evento e : eventos) {
                                if (e.getNome().equalsIgnoreCase(nomeEvt)
                                        && e.getDataHora().equals(dataEvt)) {
                                    encontrado = e;
                                    break;
                                }
                            }
                        }

                        if (encontrado == null) {
                            for (Evento e : eventos) {
                                if (e.getNome().equalsIgnoreCase(nomeEvt)) {
                                    encontrado = e;
                                    break;
                                }
                            }
                        }

                        if (encontrado != null) {
                            u.participarEvento(encontrado);
                        }
                    }
                }

                usuarios.add(u);
            }
        } catch (IOException ex) {
            System.out.println("Erro ao carregar usuários: " + ex.getMessage());
        }
    }
}
