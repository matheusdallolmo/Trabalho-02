import java.util.Scanner;
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
        Scanner teclado = new Scanner(System.in);

        System.out.println("Escolha o jogo que deseja jogar: ");
        System.out.println("1 - Jogo de Azar");
        System.out.println("2 - Jogo General");

        opcao = teclado.nextInt();
        teclado.nextLine();

        return opcao;
    }

    public void escolherJogada(){

    }
}
