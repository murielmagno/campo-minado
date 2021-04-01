import excecao.ExplosaoException;
import modelo.Campo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CampoTest {
    private Campo campo;

    @BeforeEach
    void iniciarCampo(){
        campo = new Campo(3, 3);
    }

    @Test
    void testeVizinhoEsquerda() {
        Campo vizinho = new Campo(3, 2);
        boolean resultado = campo.adicionarVizinho(vizinho);
        Assertions.assertTrue(resultado);
    }

    @Test
    void testeVizinhoDireita() {
        Campo vizinho = new Campo(3, 4);
        boolean resultado = campo.adicionarVizinho(vizinho);
        Assertions.assertTrue(resultado);
    }

    @Test
    void testeVizinhoCima() {
        Campo vizinho = new Campo(2, 3);
        boolean resultado = campo.adicionarVizinho(vizinho);
        Assertions.assertTrue(resultado);
    }

    @Test
    void testeVizinhoBaixo() {
        Campo vizinho = new Campo(4, 3);
        boolean resultado = campo.adicionarVizinho(vizinho);
        Assertions.assertTrue(resultado);
    }

    @Test
    void testeVizinhoDistanciaDois() {
        Campo vizinho = new Campo(2, 2);
        boolean resultado = campo.adicionarVizinho(vizinho);
        Assertions.assertTrue(resultado);
    }

    @Test
    void testeNaoVizinho() {
        Campo vizinho = new Campo(1, 1);
        boolean resultado = campo.adicionarVizinho(vizinho);
        Assertions.assertFalse(resultado);
    }

    @Test
    void testePadraoMarcacao() {
        Assertions.assertFalse(campo.isMarcado());
    }

    @Test
    void testeMarcacao() {
        campo.alternarMarcacao();
        Assertions.assertTrue(campo.isMarcado());
    }

    @Test
    void testeAlterarMarcacao() {
        campo.alternarMarcacao();
        campo.alternarMarcacao();
        Assertions.assertFalse(campo.isMarcado());
    }

    @Test
    void testeAbrirNaoMinadoNaoMarcado(){
        Assertions.assertTrue(campo.abrir());
    }

    @Test
    void testeAbrirNaoMinadoEMarcado(){
        campo.alternarMarcacao();
        Assertions.assertFalse(campo.abrir());
    }

    @Test
    void testeAbrirMinadoEMarcado(){
        campo.alternarMarcacao();
        campo.minar();
        Assertions.assertFalse(campo.abrir());
    }

    @Test
    void testeAbrirMinadoNaoMarcado(){
        campo.minar();
        Assertions.assertThrows(ExplosaoException.class,() -> {
            campo.abrir();
        });
    }

    @Test
    void testeAbrirComVizinhos(){
        Campo campo11 = new Campo(1,1);
        Campo campo22 = new Campo(2,2);

        campo22.adicionarVizinho(campo11);
        campo.adicionarVizinho(campo22);
        campo.abrir();

        Assertions.assertTrue(campo22.isAberto() && campo11.isAberto());
    }

    @Test
    void testeAbrirComVizinhos2(){
        Campo campo11 = new Campo(1,1);
        Campo campo12 = new Campo(1,1);
        campo12.minar();
        Campo campo22 = new Campo(2,2);

        campo22.adicionarVizinho(campo11);
        campo22.adicionarVizinho(campo12);

        campo.adicionarVizinho(campo22);
        campo.abrir();

        Assertions.assertTrue(campo22.isAberto() && campo11.isFechado());
    }

    @Test
    void testaGetLinha(){
        Assertions.assertEquals(3,campo.getLinha());
    }

    @Test
    void testaGetColuna(){
        Assertions.assertEquals(3,campo.getColuna());
    }

    @Test
    void testaDesvendado(){
        campo.abrir();
        Assertions.assertEquals(3,campo.getColuna());
    }

    @Test
    void testaObjetivo1(){
        campo.abrir();
        Assertions.assertTrue(campo.objetivoAlcancado());
    }

    @Test
    void testaObjetivo2(){
        campo.alternarMarcacao();
        campo.minar();
        Assertions.assertTrue(campo.objetivoAlcancado());
    }

    @Test
    void testaReiniciar(){
        campo.abrir();
        campo.reiniciar();
        Assertions.assertFalse(campo.isAberto());
    }

    @Test
    void testeToStringMarcado(){
        campo.alternarMarcacao();
        Assertions.assertEquals("x",campo.toString());
    }

    @Test
    void testeToStringAbertoEMinado(){
        campo.abrir();
        campo.minar();
        Assertions.assertEquals("*",campo.toString());
    }

    @Test
    void testeToStringAberto(){
        campo.abrir();
        Assertions.assertEquals(" ",campo.toString());
    }

    @Test
    void testeToStringAbertoEMinasVizinhanca(){
        Campo campo1 = new Campo(2,3);
        campo1.minar();
        campo.adicionarVizinho(campo1);
        campo.minasNaVizinhanca();
        campo.abrir();
        Assertions.assertEquals("1",campo.toString());
    }

    @Test
    void testeToStringFechado(){
        Assertions.assertEquals("?",campo.toString());
    }

    @Test
    void testeIsMinado(){

    }
}
