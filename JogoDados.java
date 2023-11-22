public abstract class JogoDados implements Estatistica{
    private int nDados;
    private String nomeJogo;
    private float saldo;
    private Dado[] dados = new Dado[5];

    public JogoDados(int numdados, String nome, float saldo){
        this.nDados = numdados;
        this.nomeJogo = nome;
        this.saldo = saldo;
        for(int i = 0; i < nDados; i++){
            dados[i] = new Dado();
        }
    }

    public void rolarDados(){
        for(int i=0; i< nDados; i++)
            dados[i].roll();
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