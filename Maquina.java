public class Maquina extends Jogador implements JogarComoMaquina {
    public Maquina(String nome) {
        super(nome);
    }

    public void iniciarJogoAzar(int rodada, float valorAposta) {

        if (super.getSaldo() < 30) {
            valorAposta = super.getSaldo();
        } else {
            valorAposta = super.getSaldo() / 2;
        }

        // Remover do saldo do jogador o valor apostado
        super.setSaldo(super.getSaldo() - valorAposta);

        // Executar jogo de Azar
        super.executarJogoAzar(rodada, valorAposta);
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
        System.out.print("Aposta: ");
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
        System.out.print("Apostas: ");
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

    // Funcao para iniciar JogoGeneral
    public void iniciarJogoGeneral(int rodada, float valorAposta) {

        if (super.getSaldo() < 30) {
            valorAposta = super.getSaldo();
        } else {
            valorAposta = super.getSaldo() / 2;
        }

        // Remover do saldo do jogador o valor apostado
        super.setSaldo(super.getSaldo() - valorAposta);

        // Executar jogo de Azar
        super.executarJogoGeneral(rodada, valorAposta, 2);
    }
}
