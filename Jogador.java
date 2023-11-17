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

    public String getNome(){
        return nome;
    }

    public float getSaldo(){
        return saldo;
    }

    public void setSaldo(float saldo){
        this.saldo = saldo;
    }

    // Funcao que vai rodar o jogo de azar para Humanos e Maquinas
    public void iniciarJogoAzar(float aposta, int rodada){
        jogo[rodada] = new JogoAzar(aposta);
        jogo[rodada].executarRegrasJogo();
    }
}