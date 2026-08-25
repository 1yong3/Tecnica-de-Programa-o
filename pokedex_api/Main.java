import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int opcao;

        do {

            System.out.println();
            System.out.println("================================");
            System.out.println("          POKEAPI JAVA");
            System.out.println("================================");
            System.out.println("1 - Listar Pokemons");
            System.out.println("2 - Ver detalhes do Pokemon");
            System.out.println("3 - Adicionar Pokemon favorito");
            System.out.println("4 - Listar Pokemon favorito");
            System.out.println("5 - Editar Pokemon favorito");
            System.out.println("6 - Deletar Pokemon favorito");
            System.out.println("0 - Sair");
            System.out.println("================================");
            System.out.print("Escolha: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {

                case 1:

                    Api.listarPokemons();

                    break;


                case 2:

                    System.out.print(
                            "Digite o nome ou numero do Pokemon: "
                    );

                    String pokemon = scanner.nextLine();

                    Api.buscarPokemon(pokemon);

                    break;


                case 3:

                    System.out.print(
                            "Digite o nome ou numero do Pokemon favorito: "
                    );

                    String favorito = scanner.nextLine();

                    Api.adicionarFavorito(favorito);

                    break;


                case 4:

                    Api.listarFavoritos();

                    break;


                case 5:

                    Api.listarFavoritos();

                    if (favoritosVazio()) {
                        break;
                    }

                    System.out.print(
                            "Digite o numero do favorito que deseja editar: "
                    );

                    int indiceEditar = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print(
                            "Digite o novo Pokemon favorito: "
                    );

                    String novoPokemon = scanner.nextLine();

                    Api.editarFavorito(
                            indiceEditar,
                            novoPokemon
                    );

                    break;


                case 6:

                    Api.listarFavoritos();

                    if (favoritosVazio()) {
                        break;
                    }

                    System.out.print(
                            "Digite o numero do favorito que deseja deletar: "
                    );

                    int indiceDeletar = scanner.nextInt();
                    scanner.nextLine();

                    Api.deletarFavorito(indiceDeletar);

                    break;


                case 0:

                    System.out.println("Programa encerrado!");

                    break;


                default:

                    System.out.println("Opcao invalida!");
            }

        } while (opcao != 0);

        scanner.close();
    }

    private static boolean favoritosVazio() {

        return false;
    }
}
