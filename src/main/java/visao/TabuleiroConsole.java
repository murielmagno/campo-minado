package visao;

import excecao.ExplosaoException;
import excecao.SairException;
import modelo.Tabuleiro;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class TabuleiroConsole {
    private Tabuleiro tabuleiro;
    private Scanner entrada = new Scanner(System.in);

    public TabuleiroConsole(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;
        executarJogo();
    }

    private void executarJogo() {
        try {
            boolean continuar = true;

            while (continuar) {
                loopDoJogo();

                System.out.println("Outra partida? (S/n) ");
                String resposta = entrada.nextLine();

                if ("n".equalsIgnoreCase(resposta)) {
                    continuar = false;
                } else {
                    tabuleiro.reiniciar();
                }
            }
        } catch (SairException e) {
            System.out.println("Bye");
        } finally {
            entrada.close();
        }
    }

    private void loopDoJogo() {
        try {

            while (!tabuleiro.objetivoAlcancado()) {
                System.out.println(tabuleiro);

                String digitado = valorDigitado("Digite x,y: ");

                Iterator<Integer> xy = Arrays.stream(digitado.split(","))
                        .map(d -> Integer.parseInt(d.trim())).iterator();

                digitado = valorDigitado("1 - Abrir ou 2 - (Des)Marcar: ");
                if ("1".equalsIgnoreCase(digitado)) {
                    tabuleiro.abrir(xy.next(), xy.next());
                } else if ("2".equalsIgnoreCase(digitado)) {
                    tabuleiro.marcar(xy.next(), xy.next());
                }
            }
            System.out.println(tabuleiro);
            System.out.println("Você ganhouuu! Parabens!");
        } catch (ExplosaoException e) {
            System.out.println(tabuleiro);
            System.out.println("Você explodiu! :3");
        }
    }

    private String valorDigitado(String texto) {
        System.out.println(texto);
        String digitado = entrada.nextLine();

        if ("sair".equalsIgnoreCase(digitado)) {
            throw new SairException();
        }
        return digitado;
    }

}
