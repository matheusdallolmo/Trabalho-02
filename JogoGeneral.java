import java.util.Scanner;

public class JogoGeneral extends JogoDados{
    private int[] jogadas;
    float valorAposta, valorFinal;


    // Construtor para a classe
    public JogoGeneral(float valorAposta){
        // Iniciar a super classe
        super(5, "Jogo General", valorAposta);  
        this.valorAposta = valorAposta;      
        this.valorFinal = 0;

        // Iniciar o vetor de jogadas
        this.jogadas = new int[13];

        // Iniciar cada jogada
        for(int i = 0; i < 13; i++)
            jogadas[i] = -1;
    }

    //Funcao getter para o valor da aposta
    public float getValorAposta(){
        return valorAposta;
    }

    //Funcao getter para retornar o resultado do jogo (se 1 ganhou se 0 perdeu)
    public float getValorFinal(){
        return valorFinal;
    }

    // Executar o Jogo de General
    public float jogar(int tipoJog){
        Scanner teclado = new Scanner(System.in);
        int aux, total;
        // Executar jogo para Humanos
        if(tipoJog == 1){
            // For para as 13 jogadas
            for(int j = 0; j < 13; j++){
                super.rolarDados();
                int escolha = 0;
                // For para mostrar os resultados que o jogador conseguiu nos dados
                System.out.println("Valores obtidos: ");
                for(int i=0; i<5; i++){
                    System.out.print(valorDado(i));
                    if(i != 4)
                        System.out.print("-");
                }
                System.out.println("\n-> Para qual jogada deseja marcar [1 - 13]?");
                System.out.printf("%s","1\t2\t3\t4\t5\t6\t7(T)\t8(Q)\t9(F)\t10(S+)\t11(S-)\t12(G)\t13(X)\n");  
                // For para imprimir todos os resultados que o jogador ja possui          
                for(int k = 0; k < 13; k++){
                    aux = jogadas[k];
                    if(aux >= 0)
                        System.out.printf("%s",aux+"\t");
                    else
                        System.out.printf("%s","-\t");
                }
                do{
                    System.out.print("\nOpcao: ");        
                    escolha = teclado.nextInt();
                    if(escolha<1 || escolha>13)
                        System.out.println("Escolha incorreta, selecione uma escolha valida: [1-13]");
                }while(escolha < 1 || escolha > 13); // Do while para que a pessoa selecione apenas jogadas validas, entre 1 e 13
                if(jogadas[escolha - 1] == -1) 
                    executarRegrasJogo(escolha);
                else{
                    while(jogadas[escolha - 1] != -1){
                        System.out.println("Jogada invalida, jogue novamente!");
    
                        escolha = teclado.nextInt();
                    }
                    executarRegrasJogo(escolha);
                }
                // Mensagem para informar que a rodada foi zerada
                if(jogadas[escolha - 1] == 0)
                    System.out.println("Voce zerou a jogada!!\n");
                // Mensagem que informa a quantidade de pontos feitos na rodada
                else
                    System.out.println("Voce fez "+jogadas[(escolha - 1)]+" na jogada "+ escolha + "\n");
            }
            total = 0;
            for(int i = 0; i < 12; i++) //For para verificar todas as jogadas entre 1-12 e somar os pontos
                total += jogadas[i];
            if(total > (jogadas[12] * 2)){
                System.out.println("Voce ganhou " + (valorAposta * 2) + " nesta rodada");
                valorFinal = 1; 
                return (valorAposta * 2);
            }
            else{
                System.out.println("Voce perdeu!!");
                valorFinal = 0;
                return 0;
            }
        }
        // Executar jogo para Maquinas
        else{
            System.out.printf("A maquina vai apostar %.2f nesta rodada!\n", valorAposta);
            // For para as 13 jogadas
            for(int j = 0; j < 13; j++){
                // Rolar os dados
                super.rolarDados();
                // For para mostrar os resultados que o jogador conseguiu nos dados
                System.out.println("Valores obtidos: ");
                for(int i=0; i<5; i++){
                    System.out.print(valorDado(i));
                    if(i != 4)
                        System.out.print("-");
                }
                // Funcao que calcula a melhor jogada para a maquina(jogadaMaquina)
                int escolhaMaq = jogadaMaquina(); 
                System.out.println("\nJogada escolhida: " + escolhaMaq);

                int pontos = validarJogada(escolhaMaq);
                // Mensagem que informa que a maquina zerou a jogada
                if(pontos == 0){ 
                    System.out.println("Maquina zerou a jogada!!\n");
                    jogadas[escolhaMaq - 1] = pontos;
                }
                // Mensagem que informa quantos pontos a maquina marcou na rodada
                else
                    System.out.println("Maquina fez "+pontos+" na jogada "+ escolhaMaq);
                    jogadas[escolhaMaq - 1] = pontos;

                System.out.printf("%s","1\t2\t3\t4\t5\t6\t7(T)\t8(Q)\t9(F)\t10(S+)\t11(S-)\t12(G)\t13(X)\n"); 
                // For para imprimir todos os resultados que o jogador ja possui          
                    for(int k = 0; k < 13; k++){
                        aux = jogadas[k];
                        if(aux >= 0)
                            System.out.printf("%s",aux+"\t");
                        else
                            System.out.printf("%s","-\t");
                    }
                System.out.println();
            }
            total = 0;
            for(int i = 0; i < 12; i++) //for para verificar todas as jogadas entre 1-12 e somar os pontos
                total += jogadas[i];
            if(total > (jogadas[12] * 2)){
                System.out.printf("A maquina ganhou %.2f nesta rodada!!\n", (valorAposta * 2));
                valorFinal = 1;
                return (valorAposta * 2);
            }
            else{
                System.out.printf("A maquina perdeu: -%.2f\n", valorAposta);
                valorFinal = 0;
                return 0;
            }
        }
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
    public int getTotal(){
        int soma = 0;
        for(int i=0; i<13; i++)    
            soma += jogadas[i];

        return soma;
    }

    // Funcao para atualizar a pontuacao do jogador
    public void pontuarJogada(int jogada, int pontos){
        jogadas[jogada - 1] = pontos;
    }

    // Funcao que valida se a jogada eh possivel e calcula os pontos marcados
    // Retorna 0 se a jogada nao for possivel ou os pontos marcados se ela for possivel
    public void executarRegrasJogo(int jogada){
        int i, quant = 0, valida;
        // Verifica e calcula os pontos para a Jogada de 1's
        if(jogada == 1){
            for(i = 0; i < 5; i++)
                if(valorDado(i) == 1)
                    quant += 1;                                                   
            jogadas[jogada - 1] = quant;
        }
        // Verifica e calcula os pontos para a Jogada de 2's
        else if(jogada == 2){
            for(i = 0; i < 5; i++)
                if(valorDado(i) == 2)
                    quant += 2;
            jogadas[jogada - 1] = quant;
        }
        // Verifica e calcula os pontos para a Jogada de 3's
        else if(jogada == 3){
            for(i = 0; i < 5; i++)
                if(valorDado(i) == 3)
                    quant += 3;
            jogadas[jogada - 1] = quant;
        }
        // Verifica e calcula os pontos para a Jogada de 4's
        else if(jogada == 4){
            for(i = 0; i < 5; i++)
                if(valorDado(i) == 4)
                    quant += 4;
            jogadas[jogada - 1] = quant;
        }
        // Verifica e calcula os pontos para a Jogada de 5's
        else if(jogada == 5){
            for(i = 0; i < 5; i++)
                if(valorDado(i) == 5)
                    quant += 5;
            jogadas[jogada - 1] = quant;
        }
        // Verifica e calcula os pontos para a Jogada de 6's
        else if(jogada == 6){
            for(i = 0; i < 5; i++)
                if(valorDado(i) == 6)
                    quant += 6;
            jogadas[jogada - 1] = quant;
        }
        // Verifica e calcula os pontos para a Trinca(T)
        else if(jogada == 7){
            for(i = 0; i < 5; i++){
                quant = 0;
                for(int j = 0; j < 5; j++){
                    if(valorDado(j) == i){
                        quant++;
                    }
                }
                if(quant >= 3){
                    quant = 0;
                    for(int k = 0; k < 5; k++)
                        quant += valorDado(k);
                    jogadas[jogada - 1] = quant;
                }
            }
            jogadas[jogada - 1] = 0;
        }

        // Verifica e calcula os pontos para a Quadra(Q)
        else if(jogada == 8){
            for(i = 0; i < 5; i++){
                quant = 0;
                for(int j = 0; j < 5; j++){
                    if(valorDado(j) == i){
                        quant++;
                    }
                }
                if(quant >= 4){
                    quant = 0;
                    for(int k = 0; k < 5; k++)
                        quant += valorDado(k);
                    jogadas[jogada - 1] = quant;
                }
            }
            jogadas[jogada - 1] = 0;
        }

        // Verifica e calcula os pontos para Full-Hand(F)/Full-House(F)
        else if(jogada == 9){
            int a = -1, b = -1, contA = 0, contB = 0;
            a = valorDado(0);
            for(i = 0; i < 5; i++){
                if(valorDado(i) == a)
                    contA++;
                else if(b == -1)
                    b = valorDado(i);
                if(valorDado(i) == b)
                    contB++;
            }
            if((contA == 3 && contB == 2) || (contA == 2 && contB == 3))
                jogadas[jogada - 1] = 25;
            else
                jogadas[jogada - 1] = 0;
            
        }

        //Verifica e calcula os pontos para Sequencia Alta (S+)
        else if (jogada == 10){
            int encontrado;
            valida = 0;
            for(i = 2; i <= 6; i++){
                encontrado = 0;
                for(int j = 0; j < 5; j++){
                    if(valorDado(j) == i)
                        encontrado = 1;
                }
                if(encontrado == 0){
                    jogadas[jogada -1] = 0;
                    valida++;
                }
            }
            if(valida == 0)
                jogadas[jogada - 1] = 30;
        }

        //Verifica e calcula os pontos para Sequencia Baixa (S-)
        else if (jogada == 11){
            int encontrado;
            valida = 0;
            for(i = 1; i <= 5; i++){
                encontrado = 0;
                for(int j = 0; j < 5; j++){
                    if(valorDado(j) == i)
                        encontrado = 1;
                }
                if(encontrado == 0){
                    jogadas[jogada -1] = 0;
                    valida++;
                }
            }
            if(valida == 0)
                jogadas[jogada - 1] = 40;
        }

        //Verifica e calcula os pontos para General(G)
        else if (jogada == 12){
            valida = 0;
            for(i=0; i<5; i++)
                for(int j=0; j<5; j++)
                    if(valorDado(i) != valorDado(j)){  //Caso algum dado for diferente, nao eh general
                        jogadas[jogada - 1] = 0;
                        valida++;
                        break;
                    }
            if(valida == 0){
                jogadas[jogada - 1] = 50;
            }   
        }
        
        //Verifica e calcula os pontos para Jogada Aleatoria(X)
        else if (jogada == 13){
            quant = 0;
            for(i = 0; i<5; i++)
                quant += valorDado(i);   //Retorna a soma de todos os dados

            jogadas[jogada - 1] = quant;
        }

        //Retorna 0 caso a jogada nao seja possivel
        else
            jogadas[jogada - 1] = 0; 
    }

    public int validarJogada(int jogada){
        int i, quant = 0, valida;
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
            for(i = 1; i <= 6; i++){
                quant = 0;
                for(int j = 0; j < 5; j++){
                    if(valorDado(j) == i){
                        quant++;
                    }
                }
                if(quant >= 3){
                    quant = 0;
                    for(int k = 0; k < 5; k++)
                        quant += valorDado(k);
                        return quant;
                }
            }
            return 0;
        }

        // Verifica e calcula os pontos para a Quadra(Q)
        else if(jogada == 8){
            for(i = 1; i <= 6; i++){
                quant = 0;
                for(int j = 0; j < 5; j++){
                    if(valorDado(j) == i){
                        quant++;
                    }
                }
                if(quant >= 4){
                    quant = 0;
                    for(int k = 0; k < 5; k++)
                        quant += valorDado(k);
                        return quant;
                }
            }
            return 0;
        }

        // Verifica e calcula os pontos para Full-Hand(F)/Full-House(F)
        else if(jogada == 9){
            int a = -1, b = -1, contA = 0, contB = 0;
            a = valorDado(0);
            for(i = 0; i < 5; i++){
                if(valorDado(i) == a)
                    contA++;
                else if(b == -1)
                    b = valorDado(i);
                if(valorDado(i) == b)
                    contB++;
            }
            if((contA == 3 && contB == 2) || (contA == 2 && contB == 3))
                return 25;
            else
                return 0;
            
        }

        //Verifica e calcula os pontos para Sequencia Alta (S+)
        else if (jogada == 10){
            int encontrado;
            valida = 0;
            for(i = 2; i <= 6; i++){
                encontrado = 0;
                for(int j = 0; j < 5; j++){
                    if(valorDado(j) == i)
                        encontrado = 1;
                }
                if(encontrado == 0){
                    return 0;
                }
            }
            return 30;
        }

        //Verifica e calcula os pontos para Sequencia Baixa (S-)
        else if (jogada == 11){
            int encontrado;
            valida = 0;
            for(i = 1; i <= 5; i++){
                encontrado = 0;
                for(int j = 0; j < 5; j++){
                    if(valorDado(j) == i)
                        encontrado = 1;
                }
                if(encontrado == 0){
                    return 0;
                }
            }
            return 40;
        }

        //Verifica e calcula os pontos para General(G)
        else if (jogada == 12){
            valida = 0;
            for(i=0; i<5; i++)
                for(int j=0; j<5; j++)
                    if(valorDado(i) != valorDado(j)){  //Caso algum dado for diferente, nao eh general
                        valida++;
                        break;
                    }
            if(valida == 0){
                return 50;
            }
            else
                return 0;   
        }
        
        //Verifica e calcula os pontos para Jogada Aleatoria(X)
        else if (jogada == 13){
            quant = 0;
            for(i = 0; i<5; i++)
                quant += valorDado(i);   //Retorna a soma de todos os dados

            return quant;
        }

        //Retorna 0 caso a jogada nao seja possivel
        else
            return 0;
    }

    public int jogadaMaquina(){
        if ((jogadas[11] == -1) && (validarJogada(12) == 50))
            return 12;
        else if ((jogadas[10] == -1) && (validarJogada(11) == 40))
            return 11;
        else if ((jogadas[9] == -1) && (validarJogada(10) == 30))
            return 10;
        else if ((jogadas[8] == -1) && (validarJogada(9) == 25))
            return 9;
        else if ((jogadas[7] == -1) && (validarJogada(8) != 0))
            return 8;
        else if ((jogadas[6] == -1) && (validarJogada(7) != 0))
            return 7;
        else if ((jogadas[12] == -1) && (validarJogada(13) != 0))
            return 13;
        else if ((jogadas[5] == -1) && (validarJogada(6) != 0))
            return 6;
        else if ((jogadas[4] == -1) && (validarJogada(5) != 0))
            return 5;
        else if ((jogadas[3] == -1) && (validarJogada(4) != 0))
            return 4;
        else if ((jogadas[2] == -1) && (validarJogada(3) != 0))
            return 3;
        else if ((jogadas[1] == -1) && (validarJogada(2) != 0))
            return 2;
        else if ((jogadas[0] == -1) && (validarJogada(1) != 0))
            return 1;
        else{  //Caso nao seja possivel pontuar em nenhuma das jogadas, comeca zerando de tras pra frente as jogadas
            for(int i=13; i>0; i--){
                if(getJogada(i) == -1){
                        return i;
                }
            }
            return 0;
        }
    }
}