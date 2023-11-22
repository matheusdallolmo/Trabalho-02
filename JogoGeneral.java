import java.io.Serializable;

public class JogoGeneral extends JogoDados implements Serializable{
    private int[] jogadas; 


    // Construtor para a classe
    public JogoGeneral(float valorAposta){
        // Iniciar a super classe
        super(5, "Jogo General", valorAposta);        

        // Iniciar o vetor de jogadas
        this.jogadas = new int[13];

        // Iniciar cada jogada
        for(int i = 0; i < 13; i++)
            jogadas[i] = -1;
    }

    public float jogar(int tipoJog){
        // Executar jogo para Humanos
        if(tipoJog == 1){

        }
        // Executar jogo para Maquinas
        else{

        }
        // Retorno para caso ocorra algum erro
        return 0;
    }

    //Funcao que retorna a pontuacao em determinada jogada
    public int getPontuacao(int jogada){ 
        return jogadas[jogada];
    }

    //Funcao que retorna a pontuacao na jogada, mas para funcoes especificas
    public int getJogada(int i){ 
        return jogadas[i-1];
    }

    // Funcao que retorna o total de pontos feitos pelo jogador exceto pela pontuacao da jogada 13 
    public int getTotal(int i){
        int soma = 0;
        for(i=0; i<12; i++)     //nao conta jogada aleatoria
            soma += jogadas[i];

        return soma;
    }

    // Funcao que valida se a jogada eh possivel e calcula os pontos marcados
    // Retorna 0 se a jogada nao for possivel ou os pontos marcados se ela for possivel
    public int executarRegrasJogo(int jogada){
        int i, quant = 0;
        // Verifica e calcula os pontos para a Jogada de 1's
        if(jogada == 1){
            for(i = 0; i < 5; i++)
                if(valorDado(i) == 1)
                    quant += 1;                                                   
            return quant;
        }
        // Verifica e calcula os pontos para a Jogada de 2's
        else if(jogada == 2){
            for(i = 0; i < 5; i++)
                if(valorDado(i) == 2)
                    quant += 2;
            return quant;
        }
        // Verifica e calcula os pontos para a Jogada de 3's
        else if(jogada == 3){
            for(i = 0; i < 5; i++)
                if(valorDado(i) == 3)
                    quant += 3;
            return quant;
        }
        // Verifica e calcula os pontos para a Jogada de 4's
        else if(jogada == 4){
            for(i = 0; i < 5; i++)
                if(valorDado(i) == 4)
                    quant += 4;
            return quant;
        }
        // Verifica e calcula os pontos para a Jogada de 5's
        else if(jogada == 5){
            for(i = 0; i < 5; i++)
                if(valorDado(i) == 5)
                    quant += 5;
            return quant;
        }
        // Verifica e calcula os pontos para a Jogada de 6's
        else if(jogada == 6){
            for(i = 0; i < 5; i++)
                if(valorDado(i) == 6)
                    quant += 6;
            return quant;
        }
        // Verifica e calcula os pontos para a Trinca(T)
        else if(jogada == 7){
            for(i = 0; i < 5; i++){
                quant = 0;
                for(int j = 0; j < 5; j++){
                    if(valorDado(i) == valorDado(j)){
                        quant += 1;
                        if(quant >= 3){ //Verifica se existem 3 dados iguais
                            quant = 0;
                            for(int k = 0; k < 5; k++)
                                quant += valorDado(k);   //se existirem, retorna a soma dos dados
                            return quant;
                        }
                            
                    }
                }
            }
            return 0;
        }

        // Verifica e calcula os pontos para a Quadra(Q)
        else if(jogada == 8){
            for(i = 0; i < 5; i++){
                quant = 0;
                for(int j = 0; j < 5; j++){
                    if(valorDado(i) == valorDado(j)){
                        quant += 1;
                        if(quant >= 4){  //Verifica se existem 4 dados iguais
                             quant = 0;
                            for(int k = 0; k < 5; k++)
                                quant += valorDado(k);   //se existirem, retorna a soma dos dados
                            return quant;
                        }  
                    }
                }
            }
            return 0;
        }

        // Verifica e calcula os pontos para Full-Hand(F)/Full-House(F)
        else if(jogada == 9){
            for(i=0; i<5; i++){
                for(int j=0; j<5; j++){
                    if(valorDado(i) != valorDado(j)){
                        for(int k =0; k<5; k++){
                            if((valorDado(k) != valorDado(i)) && (valorDado(k) != valorDado(j)))  //verifica se ha mais de dois numeros diferentes
                                return 0;                                              //verifica com os dois possiveis numeros do FH, caso haja mais de um num. diferente, retorna 0
                        }
                        return 25;
                    }
                }
            }
        }

        //Verifica e calcula os pontos para Sequencia Alta (S+)
        else if (jogada == 10){
            for(i = 0; i<5; i++){
                if(valorDado(i) == 1) //na seq. baixa nao pode haver 1, portanto, verifica
                        return 0;
                for(int j=0; j<5; j++){
                    if(i != j)
                        if(valorDado(i) == valorDado(j)) //em ambas as sequencias nao pode haver numeros iguais
                            return 0;
                }
            }
            return 30;
        }

        //Verifica e calcula os pontos para Sequencia Baixa (S-)
        else if (jogada == 11){
            for(i = 0; i<5; i++){ 
                if(valorDado(i) == 6)   //na seq. baixa nao pode haver 6, portanto, verifica
                        return 0;
                for(int j=0; j<5; j++){
                    if(i != j)
                        if(valorDado(i) == valorDado(j)) //em ambas as sequencias nao pode haver numeros iguais
                            return 0;
                }
            }
            
            return 40;
        }

        //Verifica e calcula os pontos para General(G)
        else if (jogada == 12){
            for(i=0; i<5; i++)
                for(int j=0; j<5; j++)
                    if(valorDado(i) != valorDado(j)) //Caso algum dado for diferente, nao eh general
                        return 0;
            
            return 50;      
        }
        
        //Verifica e calcula os pontos para Jogada Aleatoria(X)
        else if (jogada == 13){
            quant = 0;
            for(i = 0; i<5; i++)
                quant += valorDado(i);   //Retorna a soma de todos os dados

            return quant;
        }

        return -1;
    }

    // Funcao para atualizar a pontuacao do jogador
    public void pontuarJogada(int jogada, int pontos){
        jogadas[jogada - 1] = pontos;
    }
}
