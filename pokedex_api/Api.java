import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Api {

    public static void buscarPokemon(String pokemon) {

        try {

            String url = "https://pokeapi.co/api/v2/pokemon/" + pokemon;

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

            // Informações do Pokemon
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

    // Pega o nome principal do Pokemon
    public static String pegarNomePrincipal(String json) {

        /*
         * Procuramos o trecho:
         *
         * "base_experience":62,
         * ...
         * "name":"charmander"
         *
         * O name do Pokemon aparece depois das informações
         * principais.
         */

        int posicaoBase = json.indexOf("\"base_experience\":");

        if (posicaoBase == -1) {
            return "Nao encontrado";
        }

        int posicaoNome = json.indexOf("\"name\":\"", posicaoBase);

        if (posicaoNome == -1) {
            return "Nao encontrado";
        }

        posicaoNome += "\"name\":\"".length();

        int fim = json.indexOf("\"", posicaoNome);

        return json.substring(posicaoNome, fim);
    }

    // Pega números do JSON
    public static String pegarNumero(String json, String campo) {

        int inicio = json.indexOf(campo);

        if (inicio == -1) {
            return "Nao encontrado";
        }

        inicio += campo.length();

        int fim = json.indexOf(",", inicio);

        if (fim == -1) {
            fim = json.indexOf("}", inicio);
        }

        return json.substring(inicio, fim).trim();
    }
}