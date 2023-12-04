public interface JogarComoHumano {

    void iniciarJogoAzar(int rodada, float valorAposta);
    void iniciarJogoGeneral(int rodada, float valorAposta);
    void imprimirExtratoGeneral(int rodada, int quantJog);
    void imprimirExtratoAzar(int rodada, int quantJog);
} 