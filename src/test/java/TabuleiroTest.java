import modelo.Tabuleiro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TabuleiroTest {
    private Tabuleiro tabuleiro;

    @BeforeEach
    void iniciarTabuleiro(){
        tabuleiro = new Tabuleiro(5,5,5);
    }

    @Test
    void testeTabuleiro(){
        iniciarTabuleiro();
    }
}
