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
}
