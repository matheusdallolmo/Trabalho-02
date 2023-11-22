import java.io.Serializable;
import java.util.Random;

public class Dado implements Serializable{
    private int sideUp;

    // Contrutor padrao para o dado
    public Dado(){
        sideUp = 1; 
    }

    // Funcao para retorno da face atual
    public int getSideUp(){
        return sideUp;
    }

    // Funcao para rolar os dados
    public void roll(){
        Random x = new Random();
        sideUp = x.nextInt(6) + 1;
    }

}