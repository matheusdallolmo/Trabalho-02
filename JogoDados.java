import java.io.Serializable;
public abstract class JogoDados implements Estatistica, Serializable{
    private int nDados;
    private String nomeJogo;
    private float saldo;
    private Dado[] dados;
    private int[] numDados;

    // Construtor da classe 
    public JogoDados(int numdados, String nome, float valorAposta){
        // Iniciar os atributos
        this.nDados = numdados;
        this.saldo = valorAposta;
        this.nomeJogo = nome;
        this.saldo = 0;
        // Iniciar o tamanho do vetor de dados
        this.dados = new Dado[nDados];
        this.numDados = new int[6];
        // Iniciar os dados
        for(int i = 0; i < nDados; i++){
            dados[i] = new Dado();
        }
        // Iniciar o vetor
        for(int i = 0; i < 6; i++){
            numDados[i] = 0;
        }
    }

    // Funcao para rolar os dados do jogo
    public void rolarDados(){
        for(int i=0; i< nDados; i++){
            dados[i].roll();
            switch(valorDado(i)){
                case 1 :
                    numDados[0]++;
                case 2 :
                    numDados[1]++;
                case 3 :
                    numDados[2]++;
                case 4 :
                    numDados[3]++;
                case 5 :
                    numDados[4]++;
                case 6 :
                    numDados[5]++;
            }
        }
    }

    public int ocorrenciaDeFace(int n){
        return numDados[n];
    }

    // Imprimir as faces dos dados
    public void imprimirDados(){
        for(int i=0; i<dados.length; i++){
            System.out.print(dados[i].getSideUp());
            if(i != 4)
                System.out.print("-");
        }
    }

    // Funcao para atualizar o valor da aposta
    public void setSaldo(float aposta){
        this.saldo = aposta;
    }

    public String getNome(){
        return nomeJogo;
    }

    // Funcao que retorna o valor do dado
    public int valorDado(int i){
        return dados[i].getSideUp();
    }

    public abstract float jogar(int tipoJog);
}