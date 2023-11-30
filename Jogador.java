import java.io.Serializable;

public abstract class Jogador implements Serializable{
    private String nome = new String();
    private JogoDados[] jogo = new JogoDados[10];
    private float saldo;

    //Método construtor padrão para jogador
    public Jogador(){
        this.nome = " ";
        this.saldo = 100F;
    }
    
    //Método construtor para jogador
    public Jogador(String nome){
        this.nome = nome;
        this.saldo = 100F;
    }

    //Função abstrata para iniciar jogo de azar
    abstract void iniciarJogoAzar(int rodada, float valorAposta);

    //Função abstrata para iniciar jogo de general
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
        saldo += jogo[rodada].jogar(0);
    }

    // Funcao que vai rodar o jogo de general e atualizar o saldo do jogador
    public void executarJogoGeneral(int rodada, float aposta, int tipoJogador){
        jogo[rodada] = new JogoGeneral(aposta);
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

    public abstract void imprimirExtratoAzar(int quantJog);
    public abstract void imprimirExtratoGeneral(int rodada, int quantJog);
    
}