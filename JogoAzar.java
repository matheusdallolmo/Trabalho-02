public class JogoAzar extends JogoDados{
    private float valorAposta;
    private Dado[] dados = new Dado[2];
    private int soma = 0;

    public JogoAzar(){
        valorAposta = 0;
    }

    public void executarRegrasJogo(){
        int num = 0;

        System.out.println("Primeiro lançamento: ");
        for(int i=0; i<2; i++)
            rolarDados();

        
        soma = dados[0].getSideUp() + dados[1].getSideUp();
        System.out.println(dados[0].getSideUp() + " e " + dados[1].getSideUp() + " = " + soma);

        if(soma == 7 || soma == 11)
            System.out.println("Jogador ganhou!\n");
        else if(soma == 2 || soma == 3 || soma == 12)
            System.out.println("Jogador perdeu!\n");
        else{
            do{
                for(int i=0; i<2; i++)
                    rolarDados();
                
                num = dados[0].getSideUp() + dados[1].getSideUp();
                System.out.println(dados[0].getSideUp() + " e " + dados[1].getSideUp() + " = " + num);

                if(num == soma){
                    System.out.println("Jogador ganhou!\n");
                    break;
                }
                else if(soma == 2 || soma == 3 || soma == 12)
                    System.out.println("Jogador perdeu!\n");

            }while(num != soma);
        }
    }
}
