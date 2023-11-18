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

    public Dado rolarDados(){
        dados[0].roll();
        return dados[0];
    }

    public void imprimirDados(){
        for(int i=0; i<dados.length; i++){
            System.out.print(dados[i].getSideUp());
            if(i != 4)
                System.out.print("-");
        }
    }

    public int valorDado(int i){
        return dados[i].getSideUp();
    }

    //Nao eh ctz ainda, pq n ta somando nada 
    public int[] somarFacesSorteadas(Dado[] dados){
        int[] soma = new int[5];

        for(int i=0; i<dados.length; i++){
            soma[i] += dados[i].getSideUp();
        }

        return soma;
    }

    public abstract float executarRegrasJogo();
}
