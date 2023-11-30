import java.io.Serializable;
public abstract class JogoDados implements Estatistica, Serializable{
    private int nDados;
    private String nomeJogo;
    private float saldo;
    private Dado[] dados;

    // Construtor da classe 
    public JogoDados(int numdados, String nome, float valorAposta){
        // Iniciar os atributos
        this.nDados = numdados;
        this.saldo = valorAposta;
        this.nomeJogo = nome;
        this.saldo = 0;
        // Iniciar o tamanho do vetor de dados
        this.dados = new Dado[nDados];
        // Iniciar os dados
        for(int i = 0; i < nDados; i++){
            dados[i] = new Dado();
        }
    }

    // Funcao para rolar os dados do jogo
    public void rolarDados(){
        for(int i=0; i< nDados; i++)
            dados[i].roll();
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

    // Funcao que retorna o valor do dado
    public int valorDado(int i){
        return dados[i].getSideUp();
    }

    public abstract float jogar(int tipoJog);
}
