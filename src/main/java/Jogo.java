import modelo.Tabuleiro;
import visao.TabuleiroConsole;

public class Jogo {
    public static void main(String[] args) {

        Tabuleiro tabuleiro = new Tabuleiro(6,6,6);
        new TabuleiroConsole(tabuleiro);

    }
}
