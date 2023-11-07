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
        
    }

    //Funcao para remover jogador
    public void removerJogador(){ 
        
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