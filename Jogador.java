import java.io.Serializable;

public abstract class Jogador implements Serializable{
    private String nome = new String();
    private JogoDados[] jogo = new JogoDados[10];

    public Jogador(){
        nome = " ";
    }
}
