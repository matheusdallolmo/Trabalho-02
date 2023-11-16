public class Humano extends Jogador implements JogarComoHumano{
    private String cpf;
    private String agencia;
    private String conta;
    private int numeroBanco;

    public Humano(){
        super();
        cpf = " ";
        agencia = " ";
        conta = " ";
        numeroBanco = 0;
    }

    public Humano(String nome, String cpf, String agencia, String conta, int numeroBanco){
        super(nome);
        this.cpf = cpf;
        this.agencia = agencia;
        this.conta = conta;
        this.numeroBanco = numeroBanco;
    }

    public int escolherJogo(){ //tem que achar o lugar certo ainda
        int opcao = 0;

        System.out.println("Escolha o jogo: ");
        System.out.println("1 - Jogo de Azar");
        System.out.println("2 - Jogo de General");

        do{
            if(opcao != 1 && opcao != 2)
                System.out.println("Opcao invalida, digite novamente (1 ou 2)\n");
        }while(opcao != 1 && opcao != 2);

        return opcao;
    }

    public void escolherJogada(){

    }
}
