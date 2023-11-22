import java.util.Scanner;

public class usaCampeonato{
    public static void main(String[] args){
        Campeonato camp = new Campeonato();
        Scanner sc = new Scanner(System.in);
        char opcao;

        // Menu Interativo
        do{
            System.out.println("\n..:: Menu Interativo ::..");
            System.out.println("a -> Incluir Jogador");
            System.out.println("b -> Remover Jogador");
            System.out.println("c -> Iniciar Jogo");
            System.out.println("d -> Imprimir Saldos");
            System.out.println("e -> Imprimir extratos dos resultados");
            System.out.println("f -> Imprimir estatisticas");
            System.out.println("g -> Gravar os dados do campeonato em arquivo");
            System.out.println("h -> Ler os dados do campeonato do arquivo");
            System.out.println("i -> Sair\n");

            System.out.println("Escolha uma opcao: ");
            opcao = sc.next().charAt(0);

            // Switch que roda a funcao escolhida atraves do menu interativo
            switch(opcao){
                case 'a':
                    camp.incluirJogador();
                break;
                case 'b':
                    camp.removerJogador();
                break;
                case 'c':
                    camp.iniciarJogo();
                break;
                case 'd':
                    camp.imprimirSaldos();
                break;
                case 'e':
                    camp.imprimirResultados();
                break;
                case 'f':
                    camp.imprimirEstatisticas();
                break;
                case 'g':
                    camp.gravarEmArquivo();
                break;
                case 'h':
                    camp.lerDoArquivo();
                break;
                case 'i':
                    break;
                default:
                    System.out.println("Opcao invalida. Tente novamente!");
            }
        }while(opcao != 'i');
        sc.close();
    }
}