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

    
}
