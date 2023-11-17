import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import java.io.File;

public class Campeonato{
    private Scanner teclado = new Scanner(System.in);
    private File arquivo = new File("JogoDeApostas.dat");
    private Jogador[] jogadores = new Jogador[10];
    private int quantJog = 0, i;

    //Funcao para incluir jogador
    public void incluirJogador(){ 
        // Caso o limite de jogadores seja atingido, imprime uma mensagem de limite de jogadores e roda o menu novamente
        if(quantJog != 10){ 
            String nome;
            char tipoJogador;
            
            // Solicitar nome do jogador
            System.out.println("->Informe o nome do novo Jogador: ");
            nome = teclado.nextLine();
            
            // Solicitar tipo do jogador
            do{
                System.out.println("Informe o tipo do jogador [H -> Humano ou M -> Maquina]:");
                tipoJogador = teclado.next().charAt(0);
                teclado.nextLine();
                if(tipoJogador != 'h' && tipoJogador != 'H' && tipoJogador != 'm' && tipoJogador != 'M')
                    System.out.println("Tipo de jogador incorreto, digite a opcao novamente (H,M) ou (h,m)\n");

            }while(tipoJogador != 'h' && tipoJogador != 'H' && tipoJogador != 'm' && tipoJogador != 'M'); //Do while para que a pessoa selecione apenas opcoes corretas H ou M (tanto maiusculo quanto minusculo)

            if(tipoJogador == 'h' || tipoJogador == 'H'){
                System.out.println("Informe o CPF do jogador: ");
                String cpf = teclado.nextLine();
                System.out.println("Informe a agencia do jogador: ");
                String agencia = teclado.nextLine();
                System.out.println("Informe a conta do jogador: ");
                String conta = teclado.nextLine();
                System.out.println("Informe o numero do banco do jogador: ");
                int numeroBanco = teclado.nextInt();
                teclado.nextLine();

                jogadores[quantJog] = new Humano(nome, cpf, agencia, conta, numeroBanco);
            }else{
                jogadores[quantJog] = new Maquina(nome);
            }
                
            quantJog++;
            System.out.println("Jogador "+nome+" adicionado com sucesso!\n");
            
            
        }else
            System.out.println("Limite de jogadores alcancado! Inicie o campeonato.\n");
    }

    //Funcao para remover jogador
    public void removerJogador(){
        // Caso nao haja jogadores, imprime uma mensagem de erro e roda o menu novamente
        if(quantJog == 0)
            System.out.println("Nao ha jogadores para remover!\n");
        else{
            String nome;
            int indice = -1;

            System.out.println("-> Lista de Jogadores: ");
            for(i = 1; i <= quantJog; i++){ 
                System.out.println(i + "-> " + jogadores[i - 1].getNome());
            }

            // Solicitar nome do jogador
            do{
                System.out.println("Informe o nome do jogador que deseja remover: ");
                nome = teclado.nextLine();

                // Procurar o jogador no vetor de jogadores
                for(i=0; i<quantJog; i++){
                    if(jogadores[i].getNome().equals(nome)){
                        indice = i; //recebe a posicao do nome selecionado para remover do vetor de jogadores
                        break;
                    }
                }
                // Caso o jogador nao seja encontrado, imprime uma mensagem de erro e roda o menu novamente
                if(indice == -1)
                    System.out.println("Jogador nao encontrado!\n");

            }while(indice == -1);

                for(i=indice; i<quantJog-1; i++)
                    jogadores[i] = jogadores[i+1];
                
                quantJog--;
                System.out.println("Jogador "+nome+" removido com sucesso!\n");
        }
    }

    // Funcao para executar rodada(s) de aposta(s)
    public void iniciarJogo(){
        int opcao = 0;
        float valorAposta = 0;

        // For para as 10 rodadas
        for(i = 0; i < 10; i++){
            // Informar os jogadores sempre que uma rodada comecar
            System.out.println("Iniciando a rodada "+(i+1)+"!\n");
            // For para cada jogador incluido no jogo
            for(int r = 0; r < quantJog; r++){
                // Separar entre jogadores Humanos e Maquinas
                if(jogadores[r] instanceof Humano){
                    // Perguntar e validar em qual jogo o jogador quer apostar
                    System.out.println("Qual tipo de jogo voce deseja apostar? [1 -> Jogo de Azar ou 2 -> Jogo de General]");
                    do{
                        opcao = teclado.nextInt();
                        if(opcao != 1 && opcao != 2)
                            System.out.println("Opcao invalida, digite novamente! (1 ou 2)\n");
                    }while(opcao != 1 && opcao != 2);
                    
                    // Perguntar e validar quanto o jogador deseja apostar
                    System.out.println("Quanto deseja apostar? Seu saldo: " + jogadores[i].getSaldo());
                    do{
                        valorAposta = teclado.nextFloat();
                        
                        // Verificar se o valor da aposta eh maior que o saldo do jogador
                        if(valorAposta > jogadores[i].getSaldo()) 
                            System.out.println("Valor de aposta maior que o saldo, digite um saldo valido!\n");
                        // Verificar se o valor da aposta eh menor ou igual a zero
                        else if (valorAposta <= 0) 
                            System.out.println("Valor de aposta invalido, digite um valor valido!\n");
                        else
                            jogadores[i].setSaldo(jogadores[i].getSaldo() - valorAposta); // Atualizar o saldo do jogador
                    }while(valorAposta > jogadores[i].getSaldo() || valorAposta <= 0);

                    if(opcao == 1){
                        jogadores[r].iniciarJogoAzar(valorAposta, i);
                    }else{
                        jogadores[r].iniciarJogoGeneral(valorAposta, i);
                    }
                // Realizar a logica para a aposta da Maquina
                }else{

                }
            }
        }
    }

    // Funcao para mostrar os saldos dos jogadores
    public void imprimirSaldos(){ 
        
    }

    // Funcao para imprimir extratos dos resultados (valores das jogadas [jogo general], valor apostado, ganho ou perda) dos jogos, na(s) aposta(s) feitas pelos jogadores
    public void imprimirResultados(){

    }

    // Funcao para exibir quantas vezes cada face de cada dado ja fora sorteada
    public void imprimirEstatisticas(){

    }

    //Funcao para gravar em arquivo
    public void gravarEmArquivo(){ 
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

    //Funcao para ler do arquivo
    public void lerDoArquivo(){ 
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
            for(Jogador jog : jogadores){
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