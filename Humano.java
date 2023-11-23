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

    public void iniciarJogoAzar(int rodada, float valorAposta){

        // Remover do saldo do jogador o valor apostado
        super.setSaldo(super.getSaldo() - valorAposta);

        // Executar jogo de Azar
        super.executarJogoAzar(rodada, valorAposta);
    }

    public void iniciarJogoGeneral(int rodada, float valorAposta){
        
        // Remover do saldo do jogador o valor apostado
        super.setSaldo(super.getSaldo() - valorAposta);

        // Executar jogo de General
        super.executarJogoGeneral(rodada, valorAposta, 1);
    }
}
