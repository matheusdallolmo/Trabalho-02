import java.io.Serializable;

public class JogoAzar extends JogoDados implements Serializable{
    private float valorAposta, valorFinal;
    private Dado[] dados = new Dado[2];
    private int soma = 0;

    // Construtor para a classe
    public JogoAzar(float aposta){
        this.valorAposta = aposta;
        for(int i=0; i<2; i++)
            dados[i] = new Dado();
    }

    // Executar o Jogo de Azar
    public float executarRegrasJogo(){
        int num = 0, lancamento = 1;

        // Informar que eh o primeiro lancamento
        System.out.println("Primeiro lancamento: ");
        // Rolar os dados
        for(int i=0; i<2; i++)
            dados[i].roll();
        // Realizar a soma dos dados e mostra-la ao jogador
        soma = dados[0].getSideUp() + dados[1].getSideUp();
        System.out.println(dados[0].getSideUp() + " e " + dados[1].getSideUp() + " = " + soma);

        // Regra para o jogador ganhar na primeira rodada
        if(soma == 7 || soma == 11){
            System.out.println("Jogador ganhou!\n");
            this.valorFinal = valorAposta * 2;
            return valorFinal;
        }
        // Regra para o jogador perder na primeira rodada
        else if(soma == 2 || soma == 3 || soma == 12){
            System.out.println("Jogador perdeu!\n");
            this.valorFinal = 0;
            return valorFinal;
        }
        // Realizar as rodadas seguintes caso a primeira rodada nao resolva
        else{
            // Laco do while para realizar rodadas ate que se chegue em um resultado
            do{
                // Informar qual lancamento esta sendo executado
                lancamento++;
                System.out.println("Lancamento numero "+lancamento);
                // Rolar os dados
                for(int i=0; i<2; i++)
                    dados[i].roll();
                // Realizar a soma dos resultado e mostra-la ao jogador
                num = dados[0].getSideUp() + dados[1].getSideUp();
                System.out.println(dados[0].getSideUp() + " e " + dados[1].getSideUp() + " = " + num);

                // Regra para ganhar no jogo
                if(num == soma){
                    System.out.println("Jogador ganhou!\n");
                    this.valorFinal = valorAposta * 2;
                    return valorFinal;
                }
                // Regra para perder no jogoD
                else if(num == 2 || num == 3 || num == 12){
                    System.out.println("Jogador perdeu!\n");
                    this.valorFinal = 0;
                    return valorFinal;
                }
            }while(num != soma); 
        }
        // Retorno para caso aconteca algum imprevisto
        return -1F;
    }

    // Funcao para retornar o valor apostado nesse jogo
    public float getValorAposta(){
        return valorAposta;
    }
}
