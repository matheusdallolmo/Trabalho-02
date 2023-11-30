
public class JogoAzar extends JogoDados{
    private float valorAposta, valorFinal;
    private int soma;

    // Construtor para a classe sem argumentos
    public JogoAzar(){
        super(2, "Jogo de Azar", 0);
        this.soma = 0;
        this.valorAposta = 0;
        this.valorFinal = 0;
    }

    // Construtor para a classe com argumentos (sobrecarga)
    public JogoAzar(float valorAposta){
        super(2, "Jogo de Azar", valorAposta);
        this.soma = 0;
        this.valorAposta = valorAposta;
        this.valorFinal = 0;
    }

    //Funcao para retornar o valor da aposta
    public float getValorAposta(){
        return valorAposta;
    }

    //Funcao para retornar o resultado do jogo
    public float getValorFinal(){
        return valorFinal;
    }

    // Executar o Jogo de Azar
    public float jogar(int tipoJog){
        int num = 0, lancamento = 1;

        // Informar que eh o primeiro lancamento
        System.out.println("Primeiro lancamento: ");

        // Rolar os dados
        for (int i = 0; i < 2; i++)
            super.rolarDados();

        // Realizar a soma dos dados e mostra-la ao jogador
        soma = super.valorDado(0) + super.valorDado(1);
        System.out.println(super.valorDado(0) + " e " + super.valorDado(1) + " = " + soma);

        // Regra para o jogador ganhar na primeira rodada
        if (soma == 7 || soma == 11) {
            this.valorFinal = valorAposta * 2;
            System.out.println("Jogador ganhou! Valor ganho: " + valorFinal + "\n");
            return valorFinal;
        }

        // Regra para o jogador perder na primeira rodada
        else if (soma == 2 || soma == 3 || soma == 12) {
            this.valorFinal = 0;
            System.out.println("Jogador perdeu: -" + valorAposta + "\n");
            return valorFinal;
        }
        // Realizar as rodadas seguintes caso a primeira rodada nao resolva
        else {
            // Laco do while para realizar rodadas ate que se chegue em um resultado
            do {
                // Informar qual lancamento esta sendo executado
                lancamento++;
                System.out.println("Lancamento numero " + lancamento);

                // Rolar os dados
                for (int i = 0; i < 2; i++)
                    super.rolarDados();

                // Realizar a soma dos resultado e mostra-la ao jogador
                num = super.valorDado(0) + super.valorDado(1);
                System.out.println(super.valorDado(0) + " e " + super.valorDado(1) + " = " + num);

                // Regra para ganhar no jogo
                if (num == soma) {
                    this.valorFinal = valorAposta * 2;
                    System.out.println("Jogador ganhou! Valor ganho: " + valorFinal + "\n");
                    return valorFinal;
                }

                // Regra para perder no jogoD
                else if (num == 2 || num == 3 || num == 12) {
                    this.valorFinal = 0;
                    System.out.println("Jogador perdeu: -" + valorAposta + "\n");
                    return valorFinal;
                }
            } while (num != soma);
        }
        // Retorno para caso aconteca algum imprevisto
        return -1F;
    }    
}
