package modelo;

import excecao.ExplosaoException;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Tabuleiro {

    private int qtLinhas;
    private int qtColunas;
    private int qtMinas;

    private final List<Campo> campos = new ArrayList<>();

    public Tabuleiro(int qtLinhas, int qtColunas, int qtMinas) {
        this.qtLinhas = qtLinhas;
        this.qtColunas = qtColunas;
        this.qtMinas = qtMinas;

        gerarCampos();
        vincularVizinhos();
        colocarMinas();
    }

    public void abrir(int linha, int coluna) {
        try {
            campos.parallelStream().filter(c -> c.getLinha() == linha && c.getColuna() == coluna)
                    .findFirst()
                    .ifPresent(c -> c.abrir());
        }catch (ExplosaoException e){
            campos.forEach(c -> c.setAberto(true));

            throw e;
        }

    }

    public void marcar(int linha, int coluna) {
        campos.parallelStream().filter(c -> c.getLinha() == linha && c.getColuna() == coluna)
                .findFirst()
                .ifPresent(c -> c.alternarMarcacao());
    }

    private void gerarCampos() {
        for (int i = 0; i < qtLinhas; i++) {
            for (int j = 0; j < qtColunas; j++) {
                campos.add(new Campo(i, j));
            }
        }
    }

    private void vincularVizinhos() {
        for (Campo c1 : campos) {
            for (Campo c2 : campos) {
                c1.adicionarVizinho(c2);
            }
        }
    }

    private void colocarMinas() {
        int minasArmadas = 0;
        Predicate<Campo> minado = c -> c.isMinado();
        do {
            int aleatorio = (int) (Math.random() * campos.size());
            campos.get(aleatorio).minar();
            minasArmadas = (int) campos.stream().filter(minado).count();
        } while (minasArmadas < qtMinas);
    }

    public boolean objetivoAlcancado() {
        return campos.stream().allMatch(c -> c.objetivoAlcancado());

    }

    public void reiniciar() {
        campos.stream().forEach(c -> c.reiniciar());
        colocarMinas();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("IN");
        for (int c = 0; c < qtColunas; c++) {
            sb.append(" ");
            sb.append(c);
            sb.append(" ");

        }
        sb.append("\n");

        int k = 0;
        for (int i = 0; i < qtLinhas; i++) {
            sb.append(i);
            sb.append(" ");
            for (int j = 0; j < qtColunas; j++) {
                sb.append(" ");
                sb.append(campos.get(k));
                sb.append(" ");
                k++;
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}
