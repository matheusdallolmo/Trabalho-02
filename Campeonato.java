import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import java.io.File;

public class Campeonato {
    private Scanner teclado = new Scanner(System.in);
    private File arquivo = new File("JogoDeApostas.dat");
    private Jogador[] jogadores = new Jogador[10];
    private int quantJog = 0, i;

    // Funcao para incluir jogador
    public void incluirJogador() {
        // Caso o limite de jogadores seja atingido, imprime uma mensagem de limite de
        // jogadores e roda o menu novamente
        if (quantJog != 10) {
            String nome;
            char tipoJogador;

            // Solicitar nome do jogador
            System.out.println("->Informe o nome do novo Jogador: ");
            nome = teclado.nextLine();

            // Solicitar tipo do jogador
            do {
                System.out.println("Informe o tipo do jogador [H -> Humano ou M -> Maquina]:");
                tipoJogador = teclado.next().charAt(0);
                teclado.nextLine();
                if (tipoJogador != 'h' && tipoJogador != 'H' && tipoJogador != 'm' && tipoJogador != 'M')
                    System.out.println("Tipo de jogador incorreto, digite a opcao novamente (H,M) ou (h,m)\n");

            } while (tipoJogador != 'h' && tipoJogador != 'H' && tipoJogador != 'm' && tipoJogador != 'M');
            // Do while para que a pessoa selecione apenas opcoes corretas H ou M (tanto
            // maiusculo quanto minusculo)

            if (tipoJogador == 'h' || tipoJogador == 'H') {
                System.out.println("Informe o CPF do jogador: ");
                String cpf = teclado.nextLine();

                jogadores[quantJog] = new Humano(nome, cpf);
            } else {
                jogadores[quantJog] = new Maquina(nome);
            }

            quantJog++;
            System.out.println("Jogador " + nome + " adicionado com sucesso!\n");

        } else
            System.out.println("Limite de jogadores alcancado! Inicie o campeonato.\n");
    }

    // Funcao para remover jogador
    public void removerJogador() {
        // Caso nao haja jogadores, imprime uma mensagem de erro e roda o menu novamente
        if (quantJog == 0)
            System.out.println("Nao ha jogadores para remover!\n");
        else {
            String nome;
            int indice = -1;

            System.out.println("-> Lista de Jogadores: ");
            for (i = 1; i <= quantJog; i++) {
                System.out.println(i + "-> " + jogadores[i - 1].getNome());
            }

            // Solicitar nome do jogador
            do {
                System.out.println("Informe o nome do jogador que deseja remover: ");
                nome = teclado.nextLine();

                // Procurar o jogador no vetor de jogadores
                for (i = 0; i < quantJog; i++) {
                    if (jogadores[i].getNome().equals(nome)) {
                        indice = i; // recebe a posicao do nome selecionado para remover do vetor de jogadores
                        break;
                    }
                }
                // Caso o jogador nao seja encontrado, imprime uma mensagem de erro e roda o
                // menu novamente
                if (indice == -1)
                    System.out.println("Jogador nao encontrado!\n");

            } while (indice == -1);

            for (i = indice; i < quantJog - 1; i++)
                jogadores[i] = jogadores[i + 1];

            quantJog--;
            System.out.println("Jogador " + nome + " removido com sucesso!\n");
        }
    }

    // Funcao para executar rodada(s) de aposta(s)
    public void iniciarJogo() {
        int aux, opcaoTipo;
        float valorAposta;

        // Validar se existe pelo menos 1 jogador
        if (quantJog == 0)
            System.out.println("Nao ha jogadores para iniciar o jogo!\n");
        else {
            // For para as 10 rodadas
            for (i = 0; i < 10; i++) {
                aux = 0;
                // Verificar se ainda restam jogadores com saldo para apostar
                for (int r = 0; r < quantJog; r++) {
                    if (jogadores[r].getSaldo() > 0) {
                        aux++;
                        break;
                    }
                }
                if (aux > 0) {
                    // Informar os jogadores sempre que uma rodada comecar
                    System.out.println("Iniciando a rodada " + (i + 1) + "!\n");

                    // For para cada jogador incluido no jogo
                    for (int r = 0; r < quantJog; r++) {
                        // Validar se o jogador possui saldo para apostar
                        if (jogadores[r].getSaldo() > 0) {
                            // Separar humanos de maquinas
                            if (jogadores[r] instanceof Humano) {
                                // Pedir em qual jogo o jogador deseja apostar
                                System.out.println(jogadores[r].getNome()
                                        + ", qual tipo de jogo voce deseja apostar? [1 -> Jogo de Azar ou 2 -> Jogo de General]");
                                // Validar se a opcao informada eh valida
                                do {
                                    opcaoTipo = teclado.nextInt();
                                    teclado.nextLine();
                                    if (opcaoTipo != 1 && opcaoTipo != 2) {
                                        System.out.println("Opcao invalida, digite novamente! (1 ou 2)\n");
                                    }
                                } while (opcaoTipo != 1 && opcaoTipo != 2);
                                // Perguntar e validar quanto o jogador deseja apostar
                                System.out.println("Quanto deseja apostar? Seu saldo: " + jogadores[r].getSaldo());
                                do {
                                    valorAposta = teclado.nextFloat();
                                    teclado.nextLine();
                                    // Verificar se o valor da aposta eh maior que o saldo do jogador
                                    if (valorAposta > jogadores[r].getSaldo())
                                        System.out.println(
                                                "Valor de aposta maior que o saldo, digite um saldo valido!\n");
                                    // Verificar se o valor da aposta eh menor ou igual a zero
                                    else if (valorAposta <= 0)
                                        System.out.println("Valor de aposta invalido, digite um valor valido!\n");
                                } while (valorAposta > jogadores[r].getSaldo() || valorAposta <= 0);
                                // Iniciar o jogo escolhido pelo jogador
                                if (opcaoTipo == 1) {
                                    jogadores[r].iniciarJogoAzar(i, valorAposta);
                                } else {
                                    jogadores[r].iniciarJogoGeneral(i, valorAposta);
                                }
                            }

                            // Fazer a logica da maquina
                            else {
                                if (i % 2 == 0) {
                                    opcaoTipo = 1;
                                } else {
                                    opcaoTipo = 2;
                                }
                                // Iniciar o jogo escolhido pela maquina
                                if (opcaoTipo == 1) {
                                    System.out.println("A maquina " + jogadores[r].getNome()
                                            + " escolheu apostar no Jogo de Azar");
                                    jogadores[r].iniciarJogoAzar(i, 0);
                                } else {
                                    System.out.println("A maquina " + jogadores[r].getNome()
                                            + " escolheu apostar no Jogo General");
                                    jogadores[r].iniciarJogoGeneral(i, 0);
                                }
                            }
                        }
                        // Informar que o jogador teve a rodada pulada por falta de saldo
                        else {
                            System.out
                                    .println("Jogador " + jogadores[r].getNome() + " nao possui saldo para apostar!!");
                        }
                    }
                }
                // Encerrar o jogo caso nenhum jogador tenha saldo para apostar
                else {
                    System.out.println("Nenhum jogador possui saldo para apostar!!");
                    break;
                }
            }
            if (i == 10) {
                System.out.println("Fim do jogo!\n");
            }
        }
    }

    // Funcao para mostrar os saldos dos jogadores
    public void imprimirSaldos() {
        char opcaoTipo;
        int contChecagem = 0;

        // Validar se existe pelo menos 1 jogador
        if (quantJog == 0)
            System.out.println("Nao ha jogadores para mostrar os saldos!\n");
        else {
            System.out.println(
                    "Saldo de qual tipo de jogador voce deseja imprimir? [H -> Humano ou M -> Maquina ou T -> Todos]:");

            // Validar se a opcaoTipo informada eh valida
            do {
                opcaoTipo = teclado.next().charAt(0);
                teclado.nextLine();

                if (opcaoTipo != 'h' && opcaoTipo != 'H' && opcaoTipo != 'm' && opcaoTipo != 'M' && opcaoTipo != 't'
                        && opcaoTipo != 'T') // verificando se foi fornecido o tipo de jogador correto
                    System.out.println("Tipo de jogador incorreto, digite a opcaoTipo novamente (H,M,T) ou (h,m,t)\n");
                else {
                    if (opcaoTipo == 'h' || opcaoTipo == 'H')
                        System.out.println("Saldo final de todos os jogadores Humanos(H): ");
                    else if (opcaoTipo == 'm' || opcaoTipo == 'M')
                        System.out.println("Saldo final de todos os jogadores Maquinas(M): ");
                    else
                        System.out.println("Saldo de final de todos os jogadores Humanos(H) e Maquinas(M): ");
                    for (i = 0; i < quantJog; i++) {
                        if (opcaoTipo == 'h' || opcaoTipo == 'H') {
                            if (jogadores[i] instanceof Humano) { // polimorfismo, verificando se o jogador eh humano
                                System.out.println("Saldo final do jogador: " + jogadores[i].getNome() + " (H) -> "
                                        + String.format("%.2f", jogadores[i].getSaldo()) + "$");
                                contChecagem++;
                            } else {
                                if (contChecagem == 0 && i == quantJog - 1) // contador para saber se ha jogadores
                                                                            // humanos apos percorrer todo o vetor
                                    System.out.println("Nao ha jogadores humanos!\n");
                            }
                        }

                        else if (opcaoTipo == 'm' || opcaoTipo == 'M') {
                            if (jogadores[i] instanceof Maquina) { // polimorfismo, verificando se o jogador eh maquina
                                System.out.println("Saldo final do jogador: " + jogadores[i].getNome() + " (M) -> "
                                        + String.format("%.2f", jogadores[i].getSaldo()) + "$");
                                contChecagem++;
                            } else {
                                if (contChecagem == 0 && i == quantJog - 1) // contador para saber se ha jogadores
                                                                            // maquina apos percorrer todo o vetor
                                    System.out.println("Nao ha jogadores maquinas!\n");
                            }
                        }

                        else { // Imprimindo o saldo final de todos os jogadores Humanos e Maquinas
                            System.out.print("Saldo final do jogador: ");
                            if (jogadores[i] instanceof Humano)
                                System.out.print(jogadores[i].getNome() + " (H) -> "
                                        + String.format("%.2f", jogadores[i].getSaldo()) + "$\n");
                            else
                                System.out.print(jogadores[i].getNome() + " (M) -> "
                                        + String.format("%.2f", jogadores[i].getSaldo()) + "$\n");
                        }
                    }
                }

            } while (opcaoTipo != 'h' && opcaoTipo != 'H' && opcaoTipo != 'm' && opcaoTipo != 'M' && opcaoTipo != 't'
                    && opcaoTipo != 'T'); // Do while para que a pessoa selecione apenas opcoes corretas H ou M (tanto
                                          // maiusculo quanto minusculo)
        }
    }

    // Funcao para imprimir extratos dos resultados (valores das jogadas [jogo
    // general], valor apostado, ganho ou perda) dos jogos, na(s) aposta(s) feitas
    // pelos jogadores
    public void imprimirExtratos() {
        char opcaoTipo, opcaoJogo;
        int contChecagem = 0, contChecagem2 = 0, aux = 0;

        if (quantJog == 0)
            System.out.println("Nao ha jogadores para mostrar os extratos!\n");
        else {
            System.out.println(
                    "Extrato de qual tipo de jogador voce deseja imprimir? [H -> Humano - M -> Maquina - T -> Todos]:");

            // Do while para que a pessoa selecione apenas opcoes corretas para tipo de
            // jogador H, M ou T (tanto maiusculo quanto minusculo)
            do {
                opcaoTipo = teclado.next().charAt(0);
                teclado.nextLine();
                if (opcaoTipo != 'h' && opcaoTipo != 'H' && opcaoTipo != 'm' && opcaoTipo != 'M' && opcaoTipo != 't'
                        && opcaoTipo != 'T')
                    System.out.println("Tipo de jogador incorreto, digite a opcao novamente (H,M,T) ou (h,m,t)");
            } while (opcaoTipo != 'h' && opcaoTipo != 'H' && opcaoTipo != 'm'
                    && opcaoTipo != 'M' && opcaoTipo != 't' && opcaoTipo != 'T');

            // Do while para que a pessoa selecione apenas opcoes corretas para tipo de jogo
            // G, A ou T (tanto maiusculo quanto minusculo)
            System.out.println(
                    "Qual jogo voce deseja imprimir os extratos? [G-> Jogo General - A-> Jogo de azar - T-> Todos]:");
            do {
                opcaoJogo = teclado.next().charAt(0);
                teclado.nextLine();
                if (opcaoJogo != 'g' && opcaoJogo != 'G' && opcaoJogo != 'a' && opcaoJogo != 'A' && opcaoJogo != 't'
                        && opcaoJogo != 'T')
                    System.out.println("Tipo de jogo incorreto, digite a opcao novamente (G,A,T) ou (g,a,t)\n");
                else {
                    for (int r = 0; r < 10; r++) { // For para percorrer as 10 rodadas
                        System.out.println("Rodada " + (r + 1) + ": "); // mostra todos que jogaram tal tipo de jogo na
                                                                        // rodada r
                        for (i = 0; i < quantJog; i++) { // For para percorrer todos os jogadores
                            if (opcaoTipo == 'h' || opcaoTipo == 'H') {
                                if (jogadores[i] instanceof Humano) {
                                    contChecagem++; // contador para saber se ha jogadores humanos

                                    if (opcaoJogo == 'g' || opcaoJogo == 'G') {
                                        if (jogadores[i].verificaQualJogo(r) == true) { // Se for General é retornado
                                                                                        // true e executa o if
                                            System.out
                                                    .println("Extrato do jogador " + jogadores[i].getNome() + " (H): ");
                                            contChecagem2++; // contador tipo jogo
                                            System.out.println("-- Cartela de Resultados --\n");
                                            System.out.printf("%s", "\t");
                                            System.out.printf("%10s", jogadores[i].getNome() + "(H)\t");
                                            System.out.println();

                                            jogadores[i].imprimirExtratoGeneral(r);
                                        } else {
                                            aux = contChecagem2;
                                            contChecagem2 = 0;
                                            if (aux == 0 && i == quantJog - 1) // contador para saber se ha jogadores
                                                                               // que jogaram General na rodada
                                                System.out.println(
                                                        "Nao ha jogadores humanos que jogaram General nessa rodada!\n");

                                        }
                                    } else if (opcaoJogo == 'a' || opcaoJogo == 'A') {

                                        if (jogadores[i].verificaQualJogo(r) == false) { // Se for Azar é retornado
                                                                                         // false e executa o if
                                            contChecagem2++;
                                            System.out.printf("%s", "\t");

                                            System.out.print(jogadores[i].getNome() + "(H)\t");
                                            System.out.println();
                                            jogadores[i].imprimirExtratoAzar(r);
                                        } else {
                                            aux = contChecagem2;
                                            contChecagem2 = 0;
                                            if (aux == 0 && i == quantJog - 1) // contador para saber se ha jogadores
                                                                               // que jogaram Azar na rodada
                                                System.out.println(
                                                        "Nao ha jogadores humanos que jogaram Azar nessa rodada!\n");

                                        }
                                    } else {
                                        if (jogadores[i].verificaQualJogo(r) == true) { // Se for General é retornado
                                                                                        // true e executa o if - false
                                                                                        // para Azar
                                            jogadores[i].imprimirExtratoGeneral(r);
                                        } else
                                            jogadores[i].imprimirExtratoAzar(r);
                                    }
                                } else {
                                    if (contChecagem == 0 && i == quantJog - 1)
                                        System.out.println("Nao ha jogadores humanos para imprimir extratos!\n");
                                }
                            } else if (opcaoTipo == 'm' || opcaoTipo == 'M') {
                                if (jogadores[i] instanceof Maquina) {
                                    contChecagem++; // contador para saber se ha jogadores maquinas
                                    System.out.println("Extrato do jogador " + jogadores[i].getNome() + " (M): ");

                                    if (opcaoJogo == 'g' || opcaoJogo == 'G') {
                                        if (jogadores[i].verificaQualJogo(r) == true) {// Se for General é retornado
                                                                                       // true e executa o if
                                            System.out.println("-- Cartela de Resultados --\n");
                                            System.out.printf("%10s", jogadores[i].getNome() + "(M)\t");
                                            System.out.printf("%s", "\t");
                                            jogadores[i].imprimirExtratoGeneral(r);
                                        } else
                                            System.out.println(
                                                    "\nNao ha jogadores maquinas que jogaram General nessa rodada!\n");

                                    } // Nao ha necessidade de verificar se teve algum jogador Maquina que jogou
                                      // General, pois devido a estrategia
                                      // implementada todas as maquinas jogam General e Azar

                                    else if (opcaoJogo == 'a' || opcaoJogo == 'A') {
                                        System.out.printf("%s", "\t");

                                        // Mostrar nomes dos jogadores
                                        for (int j = 0; j < quantJog; j++)
                                            System.out.printf("%10s", jogadores[j].getNome() + "(M)\t");

                                        if (jogadores[i].verificaQualJogo(r) == false) // Se for Azar é retornado false
                                                                                       // e executa o if
                                            jogadores[i].imprimirExtratoAzar(r);
                                        else
                                            System.out.println(
                                                    "\nNao ha jogadores maquinas que jogaram Azar nessa rodada!\n");
                                    }

                                    else {
                                        if (jogadores[i].verificaQualJogo(r) == true) { // Se for General é retornado
                                                                                        // true e executa o if - false
                                                                                        // para Azar
                                            jogadores[i].imprimirExtratoGeneral(r);
                                        } else
                                            jogadores[i].imprimirExtratoAzar(r);
                                    }
                                } else {
                                    if (contChecagem == 0 && i == quantJog - 1)
                                        System.out.println("Nao ha jogadores maquinas para imprimir extratos!\n");
                                }
                            } else {
                                contChecagem2 = 0;
                                aux = 0;
                                if (opcaoJogo == 'g' || opcaoJogo == 'G') {
                                    System.out.print("Extrato do jogador: " + jogadores[i].getNome());
                                    if (jogadores[i] instanceof Humano)
                                        System.out.print(" (H):\n");
                                    else
                                        System.out.print(" (M):\n");

                                    if (jogadores[i].verificaQualJogo(r) == true) { // Se for General é retornado true e
                                                                                    // executa o if
                                        contChecagem2++;
                                        System.out.println("-- Cartela de Resultados --\n");
                                        System.out.printf("%s", "\t");
                                        System.out.printf("%10s", jogadores[i].getNome() + "\t");

                                        System.out.println();
                                        jogadores[i].imprimirExtratoGeneral(r);
                                    } else {
                                        aux = contChecagem2;
                                        contChecagem2 = 0;
                                        if (aux == 0 && i == quantJog - 1) // contador para saber se ha
                                                                           // jogadores que jogaram General na
                                                                           // rodada
                                            System.out
                                                    .println("Jogador(es) nao jogou(jogaram) General nessa rodada!\n");

                                    }
                                } else if (opcaoJogo == 'a' || opcaoJogo == 'A') {
                                    System.out.print("Extrato do jogador: " + jogadores[i].getNome());
                                    if (jogadores[i] instanceof Humano)
                                        System.out.print(" (H):\n");
                                    else
                                        System.out.print(" (M):\n");

                                    if (jogadores[i].verificaQualJogo(r) == false) { // Se for Azar é retornado false e
                                                                                     // executa o if
                                        contChecagem2++;
                                        System.out.printf("%s", "\t");

                                        // Mostrar nomes dos jogadores
                                        for (int j = 0; j < quantJog; j++)
                                            System.out.printf("%10s", jogadores[j].getNome() + "\t");

                                        System.out.println();

                                        jogadores[i].imprimirExtratoAzar(r);
                                    } else {
                                        aux = contChecagem2;
                                        contChecagem2 = 0;
                                        if (aux == 0 && i == quantJog - 1) // contador para saber se ha
                                                                           // jogadores que jogaram Azar na
                                                                           // rodada
                                            System.out.println("Jogador(es) nao jogou(jogaram) Azar nessa rodada!\n");

                                    }
                                } else {
                                    if (jogadores[i].verificaQualJogo(r) == true) { // Se for General é retornado true e
                                                                                    // executa o if - false para Azar
                                        System.out.print("Extrato do jogador: " + jogadores[i].getNome());
                                        if (jogadores[i] instanceof Humano)
                                            System.out.print(" (H):\n");
                                        else
                                            System.out.print(" (M):\n");
                                        jogadores[i].imprimirExtratoGeneral(r);
                                    } else
                                        System.out.print("Extrato do jogador: " + jogadores[i].getNome());
                                    if (jogadores[i] instanceof Humano)
                                        System.out.print(" (H):\n");
                                    else
                                        System.out.print(" (M):\n");
                                    jogadores[i].imprimirExtratoAzar(r);
                                }
                            }
                        }
                    }
                }
            } while (opcaoJogo != 'g' && opcaoJogo != 'G' && opcaoJogo != 'a'
                    && opcaoJogo != 'A' && opcaoJogo != 't' && opcaoJogo != 'T');
        }
    }

    // Funcao para exibir quantas vezes cada face de cada dado ja fora sorteada
    public void imprimirEstatisticas() {
        int opcao1, opcao2, i, total, quant;
        int[] ocorrenciaDados = new int[6];
        if (quantJog == 0)
            System.out.println("Nao ha jogadores para mostrar as estatisticas!\n");
        else {
            // Iniciar o vetor para contar os dados
            for (i = 0; i < 5; i++) {
                ocorrenciaDados[i] = 0;
            }

            // Realizar o laco para pedir, validar e mostrar a opcao que o usuario escolher
            do {
                // Solicitar qual estatistica mostrar
                System.out.println("Escolha pelo numero qual tipo de estatisticas voce deseja ver:");
                System.out.println("1 -> Por tipo de Jogador\n" +
                        "2 -> Por tipo de Jogo\n" +
                        "3 -> Total por Jogos\n" +
                        "4 -> Total do Campeonato\n");
                opcao1 = teclado.nextInt();
                teclado.nextLine();

                // Validar se a opcao eh valida
                if (opcao1 > 0 && opcao1 < 5) {
                    // Realizar a segunda solicitacao de acordo com a primeira
                    switch (opcao1) {
                        // Por tipo de Jogador
                        case 1:
                            do {
                                System.out.println("Para qual tipo de jogador voce quer mostrar: ");
                                System.out.println("1 -> Para todos os jogadores Humanos\n" +
                                        "2 -> Para todas os jogadores Maquinas\n");
                                opcao2 = teclado.nextInt();
                                teclado.nextLine();
                                if (opcao2 != 1 && opcao2 != 2) {
                                    System.out.println("Opcao invalida, por favor escolha novamente!");
                                }
                            } while (opcao2 < 1 || opcao2 > 2);

                            switch (opcao2) {
                                // Humanos
                                case 1:
                                    quant = 0;
                                    for(i = 0; i < quantJog; i++)
                                        if(jogadores[i] instanceof Humano)
                                            quant++;
                                    if(quant > 0){
                                        for (int j = 0; j < quantJog; j++) {
                                            if (jogadores[j] instanceof Humano) {
                                                for (int k = 0; k < 6; k++) {
                                                    ocorrenciaDados[k] += jogadores[j].estatisticasJogador(k);
                                                }
                                            }
                                        }
                                        total = 0;

                                        for (i = 0; i < 6; i++) {
                                            total += ocorrenciaDados[i];
                                        }
                                        System.out.println(
                                                "Total de vezes que cada face dos dados foi sorteada para jogadores humanos:\n");
                                        for (i = 0; i < 6; i++) {
                                            System.out.println("Face " + (i + 1) + ": " + ocorrenciaDados[i] + " vezes");
                                        }
                                        System.out.println("Total de faces sorteadas: " + total);
                                        break;
                                    }
                                    else{
                                        System.out.println("O jogo nao conta com nenhum jogador humano!!");
                                    }
                                    break;
                                // Maquinas
                                case 2:
                                    quant = 0;
                                    for(i = 0; i < quantJog; i++)
                                        if(jogadores[i] instanceof Maquina)
                                            quant++;
                                    if(quant > 0){
                                        for (int j = 0; j < quantJog; j++) {
                                            if (jogadores[j] instanceof Maquina) {
                                                for (int k = 0; k < 6; k++) {
                                                    ocorrenciaDados[k] += jogadores[j].estatisticasJogador(k);
                                                }
                                            }
                                        }
                                        total = 0;

                                        for (i = 0; i < 6; i++) {
                                            total += ocorrenciaDados[i];
                                        }
                                        System.out.println(
                                                "Total de vezes que cada face dos dados foi sorteada para jogadores maquinas:\n");
                                        for (i = 0; i < 6; i++) {
                                            System.out.println("Face " + (i + 1) + ": " + ocorrenciaDados[i] + " vezes");
                                        }
                                        System.out.println("Total de faces sorteadas: " + total);
                                        break;
                                    }
                                    else{
                                        System.out.println("O jogo nao conta com nenhum jogador maquina!!");
                                    }
                                    break;
                            }
                            break;

                        // Por tipo de Jogo
                        case 2:
                            do {
                                System.out.println("Para qual tipo de jogo voce quer mostrar: ");
                                System.out.println("1 -> Para o Jogo de Azar\n" +
                                        "2 -> Para o Jogo General\n");
                                opcao2 = teclado.nextInt();
                                teclado.nextLine();
                                if (opcao2 != 1 && opcao2 != 2) {
                                    System.out.println("Opcao invalida, por favor escolha novamente!");
                                }
                            } while (opcao2 < 1 || opcao2 > 2);

                            switch (opcao2) {
                                // para o Jogo de Azar
                                case 1:
                                    quant = 0;
                                    for(i = 0; i < quantJog; i++){
                                        for(int j = 0; j < 10; j++){
                                            quant += jogadores[i].validaAzar(j);
                                        }
                                    }
                                    if(quant > 0){
                                            for (int j = 0; j < quantJog; j++) {
                                            for (int k = 0; k < 6; k++) {
                                                ocorrenciaDados[k] += jogadores[j].estatisticasJogoDeAzar(k);
                                            }
                                        }
                                        total = 0;
                                        for (i = 0; i < 6; i++) {
                                            total += ocorrenciaDados[i];
                                        }
                                        System.out.println(
                                                "Total de vezes que cada face dos dados foi sorteada para Jogo de Azar:\n");
                                        for (i = 0; i < 6; i++) {
                                            System.out.println("Face " + (i + 1) + ": " + ocorrenciaDados[i]);
                                        }
                                        System.out.println("Total de faces sorteadas: " + total);
                                        break;
                                    }
                                    else{
                                        System.out.println("Nenhuma rodada de Jogo de Azar foi disputada!!");
                                    }
                                // para o Jogo General
                                case 2:
                                    quant = 0;
                                    for(i = 0; i < quantJog; i++){
                                        for(int j = 0; j < 10; j++){
                                            quant += jogadores[i].validaGeneral(j);
                                        }
                                    }
                                    if(quant > 0){
                                        for (int j = 0; j < quantJog; j++) {
                                            for (int k = 0; k < 6; k++) {
                                                ocorrenciaDados[k] += jogadores[j].estatisticasJogoGeneral(k);
                                            }
                                        }
                                        total = 0;
                                        for (i = 0; i < 6; i++) {
                                            total += ocorrenciaDados[i];
                                        }
                                        System.out.println(
                                                "Total de vezes que cada face dos dados foi sorteada para Jogo de Azar:\n");
                                        for (i = 0; i < 6; i++) {
                                            System.out.println("Face " + (i + 1) + ": " + ocorrenciaDados[i]);
                                        }
                                        System.out.println("Total de faces sorteadas: " + total);
                                        break;
                                    }
                                    else{
                                        System.out.println("Nenhuma rodada de Jogo General foi disputada!!");
                                    }
                            }
                            break;

                        // Total por Jogo
                        case 3:
                            for (int j = 0; j < quantJog; j++) {
                                jogadores[j].estatisticasCampeonato();
                            }
                            break;

                        // Total do Campeonato
                        case 4:
                            for (i = 0; i < quantJog; i++) {
                                for (int j = 0; j < 6; j++) {
                                    ocorrenciaDados[j] += jogadores[i].estatisticasJogador(j);
                                }
                            }

                            total = 0;
                            for (i = 0; i < 6; i++) {
                                total += ocorrenciaDados[i];
                            }
                            System.out
                                    .println(
                                            "Total de vezes que cada face dos dados foi sorteada em todo o Campeonato:\n");
                            for (i = 0; i < 6; i++) {
                                System.out.println("Face " + (i + 1) + ": " + ocorrenciaDados[i]);
                            }
                            System.out.println("Total de rolagens: " + total);
                            break;
                    }
                }
                // Informar que a opcao eh invalida e pedir novamente para o usuario escolher
                else {
                    System.out.println("Opcao invalida, por favor escolha novamente!");
                }
            } while (opcao1 < 1 || opcao1 > 4);
        }

    }

    // Funcao para gravar em arquivo
    public void gravarEmArquivo() {
        try {
            FileOutputStream fout = new FileOutputStream(arquivo);
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            // gravando o vetor de pessoas no arquivo
            oos.writeObject(jogadores);
            oos.flush();
            oos.close();
            fout.close();

            System.out.println("Arquivo gravado com sucesso!");
        } catch (Exception ex) {
            System.err.println("erro: " + ex.toString());
        }
    }

    // Funcao para ler do arquivo
    public void lerDoArquivo() {
        try {
            FileInputStream fin = new FileInputStream(arquivo);
            ObjectInputStream oin = new ObjectInputStream(fin);
            /*
             * Lendo os objetos de um arquivo e fazendo a
             * coercao de tipos
             */

            Jogador[] jogadoresArq = (Jogador[]) oin.readObject();
            oin.close();
            fin.close();

            jogadores = jogadoresArq;

            // atulizar a quantidade de jogadores que foram lidos
            quantJog = 0;
            for (Jogador jog : jogadores) {
                if (jog != null)
                    quantJog++;
            }

            // Informar que o arquivo foi lido com sucesso
            System.out.println("Arquivo lido com sucesso!\n");

        } catch (Exception ex) {
            System.err.println("erro: " + ex.toString());
        }
    }

}