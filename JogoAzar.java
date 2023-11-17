public class JogoAzar extends JogoDados{
    private float valorAposta = 0;
    private Dado[] dados = new Dado[2];
    private int soma = 0;

    public JogoAzar(float aposta){
        this.valorAposta = aposta;
        for(int i=0; i<2; i++)
            dados[i] = new Dado();
    }

    public void executarRegrasJogo(){
        int num = 0;

        System.out.println("Primeiro lancamento: ");
        for(int i=0; i<2; i++)
            rolarDados();

        soma = dados[0].getSideUp() + dados[1].getSideUp();
        System.out.println(dados[0].getSideUp() + " e " + dados[1].getSideUp() + " = " + soma);

        if(soma == 7 || soma == 11){
            System.out.println("Jogador ganhou!\n");
            this.valorAposta = valorAposta * 2;
        }

        else if(soma == 2 || soma == 3 || soma == 12){
            System.out.println("Jogador perdeu!\n");
            this.valorAposta = 0;
        }
        else{
            do{
                for(int i=0; i<2; i++)
                    rolarDados();
                
                num = dados[0].getSideUp() + dados[1].getSideUp();
                System.out.println(dados[0].getSideUp() + " e " + dados[1].getSideUp() + " = " + num);

                if(num == soma){
                    System.out.println("Jogador ganhou!\n");
                    this.valorAposta = valorAposta * 2;
                    break;
                }
                else if(soma == 2 || soma == 3 || soma == 12){
                    System.out.println("Jogador perdeu!\n");
                    this.valorAposta = 0;
                    break;
                }
            }while(num != soma); 
        }
    }

    public float getValorAposta(){
        return valorAposta;
    }
}
