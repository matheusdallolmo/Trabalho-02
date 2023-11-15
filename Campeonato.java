import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import java.io.File;

public class Campeonato {
    private Scanner teclado = new Scanner(System.in);
    private File arquivo = new File("JogoGeneral.dat");
    private Jogador[] jogadores = new Jogador[10];
    private int quantJog = 0, i;

    //Funcao para incluir jogador
    public void incluirJogador(){ 
        // Validacao do limite de jogadores
        if(quantJog != 10){ 
            String nome;
            char tipoJogador;
            
            // Solicitar nome do jogador
            System.out.println("-> Informe o nome do novo Jogador: ");
            nome = teclado.nextLine();
            
            // Solicitar tipo do jogador
            do{
                System.out.println("Informe o tipo do jogador [H -> Humano ou M -> Maquina]:");
                tipoJogador = teclado.next().charAt(0);
                teclado.nextLine();
                if(tipoJogador != 'h' && tipoJogador != 'H' && tipoJogador != 'm' && tipoJogador != 'M')
                    System.out.println("Tipo de jogador incorreto, digite a opcao novamente (H,M) ou (h,m)\n");

            }while(tipoJogador != 'h' && tipoJogador != 'H' && tipoJogador != 'm' && tipoJogador != 'M'); //Do while para que a pessoa selecione apenas opcoes corretas H ou M (tanto maiusculo quanto minusculo)

            // Iniciar o jogador de acordo com seu tipo
            if(tipoJogador != 'h' || tipoJogador != 'H')
                jogadores[quantJog] = new Humano(nome);
            else
                jogadores[quantJog] = new Maquina(nome);

            // Salvar a quantidade de jogadores ja adicionados
            quantJog++;
            System.out.println("Jogador "+nome+" adicionado com sucesso!\n");
            
        }else
            System.out.println("Limite de jogadores alcancado! Inicie o campeonato.\n");
    }       

    //Funcao para remover jogador
    public void removerJogador(){ 
        String remover;
        int opcao = -1;

        // Mostrar a lista de jogadores
        System.out.println("-> Lista de Jogadores: ");
        for(i = 1; i <= quantJog; i++){ 
            System.out.println(jogadores[i - 1].getNome());
        }

        do{
            System.out.print("Indique o nome do jogador a ser removido: ");
            remover = teclado.nextLine();
            for(i = 0; i < quantJog; i++) 
                if(remover == jogadores[i].getNome());
                    opcao = i;
            if(opcao == -1)
                System.out.println("Jogador não encontrado!");
        }while(opcao == -1);
        
        // Remover o jogador
        System.out.println("O jogador "+remover+" foi removido com sucesso!\n");
        for(i = opcao; i < quantJog; i++){ 
            if(i == 9)
                jogadores[i] = null;
            else
                jogadores[i] = jogadores[i + 1];
        }
        // Ajuste do tamanho do vetor de jogadores
        quantJog--; 
        // Limpando buffer para que apos a remocao seja possivel a implementacao de outro jogador sem erros
        teclado.nextLine(); 
    }

    // Funcao para executar rodada(s) de aposta(s)
    public void iniciarJogo(){
        
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