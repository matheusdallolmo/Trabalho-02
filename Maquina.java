public class Maquina extends Jogador implements JogarComoMaquina{
    public Maquina(String nome){
        super(nome);
    }

    public void iniciarJogoAzar(int rodada, float valorAposta){

        if(super.getSaldo() < 30){
            valorAposta = super.getSaldo();
        }
        else{
            valorAposta = super.getSaldo() / 2;
        }

        // Remover do saldo do jogador o valor apostado
        super.setSaldo(super.getSaldo() - valorAposta);

        // Executar jogo de Azar
        super.executarJogoAzar(rodada, valorAposta);
    }

    public void iniciarJogoGeneral(int rodada, float valorAposta){

        if(super.getSaldo() < 30){
            valorAposta = super.getSaldo();
        }
        else{
            valorAposta = super.getSaldo() / 2;
        }

        // Remover do saldo do jogador o valor apostado
        super.setSaldo(super.getSaldo() - valorAposta);

        // Executar jogo de Azar
        super.executarJogoGeneral(rodada, valorAposta, 2);
    }
}
