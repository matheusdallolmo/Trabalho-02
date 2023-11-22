import java.io.Serializable;

public abstract class Jogador implements Serializable{
    private String nome = new String();
    private JogoDados[] jogo = new JogoDados[10];
    private float saldo;

    public Jogador(){
        this.nome = " ";
        this.saldo = 100F;
    }
    
    public Jogador(String nome){
        this.nome = nome;
        this.saldo = 100F;
    }

    abstract void iniciarJogoAzar(int rodada);

    abstract void iniciarJogoGeneral(int rodada);

    public String getNome(){
        return nome;
    }

    public float getSaldo(){
        return saldo;
    }

    public void setSaldo(float saldo){
        this.saldo = saldo;
    }

    // Funcao que vai rodar o jogo de azar 
    public void executarJogoAzar(int rodada, float aposta){
        jogo[rodada] = new JogoAzar(aposta);
        saldo += jogo[rodada].jogar(0);
    }

    public void executarJogoGeneral(int rodada, float aposta, int tipoJogador){
        jogo[rodada] = new JogoGeneral(aposta);
        saldo += jogo[rodada].jogar(tipoJogador);
    }

}