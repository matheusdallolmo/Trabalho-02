import java.io.Serializable;

public abstract class Jogador implements Serializable{
    private String nome = new String();
    private JogoDados[] jogo;
    private float saldo;
    private int[] dados;
    private int numJogos;

    //Método construtor padrão para jogador
    public Jogador(){
        this.nome = " ";
        this.saldo = 100F;
    }
    
    //Metodo construtor para jogador
    public Jogador(String nome){
        this.nome = nome;
        this.saldo = 100F;
        this.numJogos = 0;
        jogo = new JogoDados[10];
        dados = new int[6];
        for(int i = 0; i < 6; i++){
            dados[i] = 0;
        }
    }

    //Funcao abstrata para iniciar jogo de azar
    abstract void iniciarJogoAzar(int rodada, float valorAposta);

    //Funcao abstrata para iniciar jogo de general
    abstract void iniciarJogoGeneral(int rodada, float valorAposta);

    //Getter de nome
    public String getNome(){
        return nome;
    }

    //Getter de saldo
    public float getSaldo(){
        return saldo;
    }

    //Setter de saldo
    public void setSaldo(float saldo){
        this.saldo = saldo;
    }

    //Getter de valor de aposta
    public float getValorAposta(int rodada){
        if (jogo[rodada] instanceof JogoGeneral)
            return ((JogoGeneral)jogo[rodada]).getValorAposta();
        else
            return ((JogoAzar)jogo[rodada]).getValorAposta();
    }

    //Getter de valor final
    public float getValorFinal(int rodada){
        if (jogo[rodada] instanceof JogoGeneral)
            return ((JogoGeneral)jogo[rodada]).getValorFinal();
        else
            return ((JogoAzar)jogo[rodada]).getValorFinal();
    }

    //Funcao para verificar qual jogo foi executado
    public boolean verificaQualJogo(int rodada){
        if(jogo[rodada] instanceof JogoGeneral) //Retorna true para General e falso para Azar
            return true;
        else
            return false;
    }

    // Funcao que vai rodar o jogo de azar 
    public void executarJogoAzar(int rodada, float aposta){
        jogo[rodada] = new JogoAzar(aposta);
        numJogos++;
        saldo += jogo[rodada].jogar(0);
    }

    // Funcao que vai rodar o jogo de general e atualizar o saldo do jogador
    public void executarJogoGeneral(int rodada, float aposta, int tipoJogador){
        jogo[rodada] = new JogoGeneral(aposta);
        numJogos++;
        saldo += jogo[rodada].jogar(tipoJogador);
    }

    // Funcao que retorna a pontuacao em determinada jogada
    public int getJogada(int rodada, int jogada){
        if(jogo[rodada] instanceof JogoGeneral)
            return ((JogoGeneral)jogo[rodada]).getJogada(jogada);
        /*else if (jogo[rodada] instanceof JogoAzar){
            return ((JogoAzar)jogo[rodada]).getJogada(jogada);
        }*/
        else
            return -1; //caso ocorra algum erro
    }

    //Funcao que retorna o total de pontos do Jogo General em determinada rodada
    public int getTotal(int rodada){
        if(jogo[rodada] instanceof JogoGeneral)
            return ((JogoGeneral)jogo[rodada]).getTotal();
        else
            return -1; //caso ocorra algum erro
    }

    // Estatisticas dos dados de todos os jogos do jogador
    public int estatisticasJogador(int n){
        int estatisticaN = 0;

        for (int i = 0; i < numJogos; i++){
            estatisticaN += jogo[i].ocorrenciaDeFace(n);
        }

        return estatisticaN;
    }

    // Estatisticas dos dados dos Jogos de Azar
    public int estatisticasJogoDeAzar(int n){
        int quant = 0;

        for(int i = 0; i < numJogos; i++)
            if (jogo[i] instanceof JogoAzar)
                quant += jogo[i].ocorrenciaDeFace(n);
        return quant;
    }

    // Estatisticas dos dados dos Jogos de Azar
    public int estatisticasJogoGeneral(int n){
        int quant = 0;

        for(int i = 0; i < numJogos; i++)
            if (jogo[i] instanceof JogoGeneral)
                quant += jogo[i].ocorrenciaDeFace(n);
        return quant;
    }

    public void estatisticasCampeonato(){
        System.out.println("Estatisticas do jogador "+nome+" para cada jogo:\n");

        for(int i = 0; i < numJogos; i++){
            int total = 0;
            System.out.println((i + 1)+" -> "+jogo[i].getNome() + ":");
            for (int j = 0; j < 6; j++){
                System.out.println("Face "+(j + 1) +": "+jogo[i].ocorrenciaDeFace(j));
                total += jogo[i].ocorrenciaDeFace(j);
            }
            System.out.println("Total de faces sorteadas: "+ total+"\n");
        }
    }

    public abstract void imprimirExtratoAzar(int quantJog);
    public abstract void imprimirExtratoGeneral(int rodada, int quantJog);
    
}