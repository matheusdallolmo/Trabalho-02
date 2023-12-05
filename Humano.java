
public class Humano extends Jogador implements JogarComoHumano {
    private  String cpf;
    private final String agencia;
    private final String conta;
    private final int numeroBanco;

    public Humano() {
        super();
        cpf = " ";
        agencia = " ";
        conta = " ";
        numeroBanco = 0;
    }

    public Humano(String nome, String cpf) {
        super(nome);
        this.cpf = cpf;
        this.agencia = "Banco do Brasil";
        this.conta = "514563598-7";
        this.numeroBanco = 748;
    }

    public void iniciarJogoAzar(int rodada, float valorAposta) {

        // Remover do saldo do jogador o valor apostado
        super.setSaldo(super.getSaldo() - valorAposta);

        // Executar jogo de Azar
        super.executarJogoAzar(rodada, valorAposta);
    }

    public void iniciarJogoGeneral(int rodada, float valorAposta) {

        // Remover do saldo do jogador o valor apostado
        super.setSaldo(super.getSaldo() - valorAposta);

        // Executar jogo de General
        super.executarJogoGeneral(rodada, valorAposta, 1);
    }

    public int getJogada(int rodada, int jogada) {
        return super.getJogada(rodada, jogada);
    }

    // Funcao para imprimir o extrato do jogo General
    public void imprimirExtratoGeneral(int rodada) {
        // Mostrar resultados
        for (int i = 1; i <= 13; i++) {
            if (i==1)
                System.out.println();
            if (i <= 6)
                System.out.printf("%s", i + "\t");
            else if (i == 7)
                System.out.printf("%s", i + "(T)\t");
            else if (i == 8)
                System.out.printf("%s", i + "(Q)\t");
            else if (i == 9)
                System.out.printf("%s", i + "(F)\t");
            else if (i == 10)
                System.out.printf("%s", i + "(S+)\t");
            else if (i == 11)
                System.out.printf("%s", i + "(S-)\t");
            else if (i == 12)
                System.out.printf("%s", i + "(G)\t");
            else if (i == 13)
                System.out.printf("%s", i + "(X)\t");

            for (int j = 0; j < 1; j++) {
                System.out.printf("%10s", getJogada(rodada, i) + "\t");
            }
            System.out.println();
        }

        for (int i = 0; i < 1; i++)
            System.out.print("-----------------");

        System.out.print("\nTotal\t");
        for (int i = 0; i < 1; i++) {
            System.out.printf("%10s", getTotal(rodada) + "\t");
        }
        System.out.println();
        System.out.print("Aposta: \t");
        for (int i = 0; i < 1; i++)
            System.out.printf("%.2f\t", getValorAposta(rodada));

        System.out.print("\nResultado: ");
        for (int i = 0; i < 1; i++) {
            if (getValorFinal(rodada) > 0)
                System.out.printf("Ganhou\t");
            else
                System.out.printf("Perdeu\t");
        }
        System.out.println("\n");
    }

    // metodo para mostrar o extrato do jogo azar
    public void imprimirExtratoAzar(int rodada) {
        System.out.println("Jogo de Azar");
        System.out.print("Aposta: \t");
        for (int i = 0; i < 1; i++)
            System.out.printf("%.2f\t", getValorAposta(rodada));

        System.out.print("\nResultado: ");
        for (int i = 0; i < 1; i++) {
            if (getValorFinal(rodada) > 0)
                System.out.printf("Ganhou\t");
            else
                System.out.printf("Perdeu\t");
        }
        System.out.println("\n");
    }
}
