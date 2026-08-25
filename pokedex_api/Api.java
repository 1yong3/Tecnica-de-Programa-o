import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

public class Api {
    private static ArrayList<String> favoritos = new ArrayList<>();

    public static void buscarPokemon(String pokemon) {

        try {

            String url =
                    "https://pokeapi.co/api/v2/pokemon/"
                            + pokemon.toLowerCase();

            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(
                    request,
                    HttpResponse.BodyHandlers.ofString()
            );

            if (response.statusCode() != 200) {

                System.out.println("Pokemon nao encontrado!");
                return;
            }

            String json = response.body();

            String nome = pegarNomePrincipal(json);
            String altura = pegarNumero(json, "\"height\":");
            String peso = pegarNumero(json, "\"weight\":");
            String experiencia = pegarNumero(json, "\"base_experience\":");

            System.out.println();
            System.out.println("================================");
            System.out.println("        INFORMACOES POKEMON");
            System.out.println("================================");
            System.out.println("Nome:             " + nome);
            System.out.println("Altura:           " + altura);
            System.out.println("Peso:             " + peso);
            System.out.println("Experiencia base: " + experiencia);
            System.out.println("================================");

        } catch (Exception e) {

            System.out.println("Erro ao acessar a API.");
        }
    }

    public static void listarPokemons() {

        try {

            String url =
                    "https://pokeapi.co/api/v2/pokemon?limit=20";

            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(
                    request,
                    HttpResponse.BodyHandlers.ofString()
            );

            String json = response.body();

            System.out.println();
            System.out.println("================================");
            System.out.println("        LISTA DE POKEMONS");
            System.out.println("================================");

            String[] partes = json.split("\"name\":\"");

            int contador = 1;

            for (int i = 1; i < partes.length; i++) {

                String nome = partes[i].split("\"")[0];

                System.out.println(contador + " - " + nome);

                contador++;
            }

            System.out.println("================================");

        } catch (Exception e) {

            System.out.println("Erro ao acessar a API.");
        }
    }

    public static void adicionarFavorito(String pokemon) {

        try {

            String url =
                    "https://pokeapi.co/api/v2/pokemon/"
                            + pokemon.toLowerCase();

            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(
                    request,
                    HttpResponse.BodyHandlers.ofString()
            );

            if (response.statusCode() != 200) {

                System.out.println("Pokemon nao encontrado!");
                return;
            }

            if (favoritos.contains(pokemon.toLowerCase())) {

                System.out.println(
                        "Esse Pokemon ja esta nos favoritos!"
                );

                return;
            }

            favoritos.add(pokemon.toLowerCase());

            System.out.println();
            System.out.println("Pokemon adicionado aos favoritos!");
            System.out.println("Pokemon: " + pokemon.toLowerCase());

        } catch (Exception e) {

            System.out.println("Erro ao acessar a API.");
        }
    }

    public static void listarFavoritos() {

        System.out.println();
        System.out.println("================================");
        System.out.println("       POKEMONS FAVORITOS");
        System.out.println("================================");

        if (favoritos.isEmpty()) {

            System.out.println("Nenhum Pokemon favorito.");

        } else {

            for (int i = 0; i < favoritos.size(); i++) {

                String pokemon = favoritos.get(i);

                try {

                    String url =
                            "https://pokeapi.co/api/v2/pokemon/"
                                    + pokemon.toLowerCase();

                    HttpClient client = HttpClient.newHttpClient();

                    HttpRequest request = HttpRequest.newBuilder()
                            .uri(URI.create(url))
                            .GET()
                            .build();

                    HttpResponse<String> response = client.send(
                            request,
                            HttpResponse.BodyHandlers.ofString()
                    );

                    if (response.statusCode() == 200) {

                        String json = response.body();

                        // 3 informacoes do Pokemon
                        String nome = pegarNomePrincipal(json);
                        String altura = pegarNumero(json, "\"height\":");
                        String peso = pegarNumero(json, "\"weight\":");

                        System.out.println();
                        System.out.println(
                                (i + 1) + " - Pokemon"
                        );
                        System.out.println("Nome:   " + nome);
                        System.out.println("Altura: " + altura);
                        System.out.println("Peso:   " + peso);

                    } else {

                        System.out.println();
                        System.out.println(
                                (i + 1)
                                        + " - Pokemon nao encontrado!"
                        );
                    }

                } catch (Exception e) {

                    System.out.println();
                    System.out.println(
                            "Erro ao buscar dados do Pokemon: "
                                    + pokemon
                    );
                }
            }
        }

        System.out.println();
        System.out.println("================================");
    }

    public static void editarFavorito(
            int indice,
            String novoPokemon
    ) {

        try {

            if (indice < 1 || indice > favoritos.size()) {

                System.out.println(
                        "Favorito nao encontrado!"
                );

                return;
            }

            String url =
                    "https://pokeapi.co/api/v2/pokemon/"
                            + novoPokemon.toLowerCase();

            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(
                    request,
                    HttpResponse.BodyHandlers.ofString()
            );

            if (response.statusCode() != 200) {

                System.out.println(
                        "Pokemon nao encontrado!"
                );

                return;
            }

            if (favoritos.contains(novoPokemon.toLowerCase())) {

                System.out.println(
                        "Esse Pokemon ja esta nos favoritos!"
                );

                return;
            }

            String antigo = favoritos.get(indice - 1);

            favoritos.set(
                    indice - 1,
                    novoPokemon.toLowerCase()
            );

            System.out.println();
            System.out.println("================================");
            System.out.println("Favorito atualizado!");
            System.out.println("Antigo: " + antigo);
            System.out.println(
                    "Novo: " + novoPokemon.toLowerCase()
            );
            System.out.println("================================");

        } catch (Exception e) {

            System.out.println("Erro ao acessar a API.");
        }
    }

    public static void deletarFavorito(int indice) {

        if (indice < 1 || indice > favoritos.size()) {

            System.out.println(
                    "Favorito nao encontrado!"
            );

            return;
        }

        String pokemon = favoritos.remove(indice - 1);

        System.out.println();
        System.out.println("================================");
        System.out.println("Pokemon removido dos favoritos!");
        System.out.println("Pokemon: " + pokemon);
        System.out.println("================================");
    }

    public static String pegarNomePrincipal(String json) {

        int posicaoBase =
                json.indexOf("\"base_experience\":");

        if (posicaoBase == -1) {

            return "Nao encontrado";
        }

        int posicaoNome =
                json.indexOf("\"name\":\"", posicaoBase);

        if (posicaoNome == -1) {

            return "Nao encontrado";
        }

        posicaoNome += "\"name\":\"".length();

        int fim =
                json.indexOf("\"", posicaoNome);

        return json.substring(
                posicaoNome,
                fim
        );
    }

//api
    public static String pegarNumero(
            String json,
            String campo
    ) {

        int inicio =
                json.indexOf(campo);

        if (inicio == -1) {

            return "Nao encontrado";
        }

        inicio += campo.length();

        int fim =
                json.indexOf(",", inicio);

        if (fim == -1) {

            fim = json.indexOf("}", inicio);
        }

        return json.substring(
                inicio,
                fim
        ).trim();
    }
}

