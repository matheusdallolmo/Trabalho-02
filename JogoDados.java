public abstract class JogoDados implements Estatistica{
    private int nDados;
    private String nomeJogo;
    private float saldo;
    private Dado[] dados = new Dado[5];

    public JogoDados(){
        nDados = 0;
        nomeJogo = " ";
        saldo = 0.0F;
        for(int i = 0; i < 5; i++){
            dados[i] = new Dado();
        }
    }
}
