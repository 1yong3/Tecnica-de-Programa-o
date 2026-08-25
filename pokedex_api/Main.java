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

                case 0:

                    System.out.println("Programa encerrado!");

                    break;

                default:

                    System.out.println("Opcao invalida!");

            }

        } while (opcao != 0);

        scanner.close();
    }
}