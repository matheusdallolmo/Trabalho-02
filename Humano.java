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

    public Humano(String nome, String cpf){
        super(nome);
        this.cpf = cpf;
        this.agencia =  "Banco do Brasil";
        this.conta = "514563598-7";
        this.numeroBanco = 748;
    }

    public void iniciarJogoAzar(int rodada){
        Scanner sc = new Scanner(System.in);
        float valorAposta;

        // Perguntar e validar quanto o jogador deseja apostar
        System.out.println("Quanto deseja apostar? Seu saldo: " + super.getSaldo());
        do{
            valorAposta = sc.nextFloat();
            sc.nextLine();
            // Verificar se o valor da aposta eh maior que o saldo do jogador
            if(valorAposta > super.getSaldo())
                System.out.println("Valor de aposta maior que o saldo, digite um saldo valido!\n");
            // Verificar se o valor da aposta eh menor ou igual a zero
            else if(valorAposta <= 0)
                System.out.println("Valor de aposta invalido, digite um valor valido!\n");
        }while(valorAposta > super.getSaldo() || valorAposta <= 0);

        // Encerrar o Scanner
        sc.close();

        // Remover do saldo do jogador o valor apostado
        super.setSaldo(super.getSaldo() - valorAposta);

        // Executar jogo de Azar
        super.executarJogoAzar(rodada, valorAposta);
    }

    public void iniciarJogoGeneral(int rodada){
        Scanner sc = new Scanner(System.in);
        float valorAposta;

        // Perguntar e validar quanto o jogador deseja apostar
        System.out.println("Quanto deseja apostar? Seu saldo: " + super.getSaldo());
        do{
            valorAposta = sc.nextFloat();
            sc.nextLine();
            // Verificar se o valor da aposta eh maior que o saldo do jogador
            if(valorAposta > super.getSaldo())
                System.out.println("Valor de aposta maior que o saldo, digite um saldo valido!\n");
            // Verificar se o valor da aposta eh menor ou igual a zero
            else if(valorAposta <= 0)
                System.out.println("Valor de aposta invalido, digite um valor valido!\n");
        }while(valorAposta > super.getSaldo() || valorAposta <= 0);

        // Encerrar o Scanner
        sc.close();

        // Remover do saldo do jogador o valor apostado
        super.setSaldo(super.getSaldo() - valorAposta);

        // Executar jogo de General
        super.executarJogoGeneral(rodada, valorAposta, 1);
    }
}
