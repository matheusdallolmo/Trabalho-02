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

            } while (tipoJogador != 'h' && tipoJogador != 'H' && tipoJogador != 'm' && tipoJogador != 'M'); // Do while
                                                                                                            // para que
                                                                                                            // a pessoa
                                                                                                            // selecione
                                                                                                            // apenas
                                                                                                            // opcoes
                                                                                                            // corretas
                                                                                                            // H ou M
                                                                                                            // (tanto
                                                                                                            // maiusculo
                                                                                                            // quanto
                                                                                                            // minusculo)

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
        int aux, opcao;
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
                                    opcao = teclado.nextInt();
                                    teclado.nextLine();
                                    if (opcao != 1 && opcao != 2) {
                                        System.out.println("Opcao invalida, digite novamente! (1 ou 2)\n");
                                    }
                                } while (opcao != 1 && opcao != 2);
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
                                if (opcao == 1) {
                                    jogadores[r].iniciarJogoAzar(i, valorAposta);
                                } else {
                                    jogadores[r].iniciarJogoGeneral(i, valorAposta);
                                }
                            }
                            // Fazer a logica da maquina
                            else {
                                if (i % 2 == 0) {
                                    opcao = 1;
                                } else {
                                    opcao = 2;
                                }
                                // Iniciar o jogo escolhido pela maquina
                                if (opcao == 1) {
                                    jogadores[r].iniciarJogoAzar(i, 0);
                                } else {
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
        }
    }

    // Funcao para mostrar os saldos dos jogadores
    public void imprimirSaldos(){
        char opcao;
        int contChecagem = 0;

        // Validar se existe pelo menos 1 jogador
        if(quantJog == 0)
            System.out.println("Nao ha jogadores para mostrar os saldos!\n");
        else{
            System.out.println("Saldo de qual tipo de jogador voce deseja imprimir? [H -> Humano ou M -> Maquina ou T -> Todos]:");

            // Validar se a opcao informada eh valida
            do{
                opcao = teclado.next().charAt(0);
                teclado.nextLine();
                if(opcao != 'h' && opcao != 'H' && opcao != 'm' && opcao != 'M' && opcao != 't' && opcao != 'T')
                    System.out.println("Tipo de jogador incorreto, digite a opcao novamente (H,M,T) ou (h,m,t)\n");
                else{
                    for(i = 0; i < quantJog; i++){
                        if(opcao == 'h' || opcao == 'H'){
                            if(jogadores[i] instanceof Humano){
                                System.out.println("Saldo do jogador (H): " + jogadores[i].getNome() + " -> " +jogadores[i].getSaldo() +"$");
                                contChecagem++;
                            }
                            else{
                                if(contChecagem == 0 && i == quantJog - 1) //contador para saber se ha jogadores humanos
                                    System.out.println("Nao ha jogadores humanos!\n");
                            }
                        }
    
                        else if(opcao == 'm' || opcao == 'M'){
                            if(jogadores[i] instanceof Maquina){
                                System.out.println("Saldo do jogador (M): " + jogadores[i].getNome() + " -> " +jogadores[i].getSaldo() +"$");
                                contChecagem++;
                            }
                            else{
                                if(contChecagem == 0 && i == quantJog - 1)
                                    System.out.println("Nao ha jogadores maquinas!\n");
                            }    
                        }

                        else{
                            System.out.println("Saldo do jogador: " + jogadores[i].getNome() + " -> " +jogadores[i].getSaldo() +"$");
                        }
                    }
                }
                    
            }while(opcao != 'h' && opcao != 'H' && opcao != 'm' && opcao != 'M' && opcao != 't' && opcao != 'T'); //Do while para que a pessoa selecione apenas opcoes corretas H ou M (tanto maiusculo quanto minusculo)

            /*// For para cada jogador incluido no jogo
            for (int r = 0; r < quantJog; r++){

                // Separar humanos de maquinas
                if(opcao == 'h' || opcao == 'H'){
                    if(jogadores[r] instanceof Humano){
                        System.out.println("Saldo do jogador (H): " + jogadores[r].getNome() + " -> " +jogadores[r].getSaldo() +"$");
                    }
                    else{
                        System.out.println("Nao ha jogadores humanos!\n");
                    }
                }
                else if(opcao == 'm' || opcao == 'M'){
                    if(jogadores[r] instanceof Maquina){
                        System.out.println("Saldo do jogador (M): " + jogadores[r].getNome() + " -> " +jogadores[r].getSaldo() +"$");
                    }
                }
                else{
                    System.out.println("Saldo do jogador: " + jogadores[r].getNome() + " -> " +jogadores[r].getSaldo() +"$");
                }
            }*/
        }
    }

    // Funcao para imprimir extratos dos resultados (valores das jogadas [jogo
    // general], valor apostado, ganho ou perda) dos jogos, na(s) aposta(s) feitas
    // pelos jogadores
    public void imprimirResultados() {
        System.out.println("\nExtrato dos resultados: ");
        for (i = 0; i < quantJog; i++) {
            System.out.println(jogadores[i].getNome() + " -> " + jogadores[i].getSaldo() + "$\n");
        }
    }

    // Funcao para exibir quantas vezes cada face de cada dado ja fora sorteada
    public void imprimirEstatisticas() {

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