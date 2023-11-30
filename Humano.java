
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

    public int getJogada(int rodada, int jogada){
        return super.getJogada(rodada, jogada);
    }

    // Funcao para imprimir o extrato do jogo General
    public void imprimirExtratoGeneral(int rodada, int quantJog){
        // Mostrar resultados
        for(int i=1; i<=13; i++){
            if(i<=6)
                System.out.printf("%s",  i + "\t");
            else if(i == 7)
                System.out.printf("%s",  i + "(T)\t");
            else if(i == 8)
                System.out.printf("%s",  i + "(Q)\t");
            else if(i == 9)
                System.out.printf("%s",  i + "(F)\t");
            else if(i == 10)
                System.out.printf("%s",  i + "(S+)\t");
            else if(i == 11)
                System.out.printf("%s",  i + "(S-)\t");
            else if(i == 12)
                System.out.printf("%s",  i + "(G)\t");
            else if(i == 13)
                System.out.printf("%s",  i + "(X)\t");

            for(int j=0; j<quantJog; j++){
                System.out.printf("%10s",  getJogada(rodada, i) + "\t");
            }
            System.out.println();
        }
        for(int i=0; i<quantJog; i++)
            System.out.print("-----------------");

        System.out.print("\nTotal\t" );
        for(int i=0; i<quantJog; i++){
            System.out.printf("%10s", getTotal(rodada) + "\t");
        }

        System.out.println("\nApostas\t");
        for(int i=0; i<quantJog; i++)
            System.out.printf("%10s" + getValorAposta(rodada) + "\t");

        System.out.println("\nResultados\t");
        for(int i=0; i<quantJog; i++){
            if(getValorFinal(rodada) > 0)
                System.out.printf("%10s", "Ganhou " + getValorFinal(rodada) + "\t");
            else
                System.out.printf("%10s", "Perdeu " + getValorFinal(rodada) + "\t");
        }
    }

    public void imprimirExtratoAzar(int rodada, int quantJog){
       
    }
}
