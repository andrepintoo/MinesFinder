import java.util.Random;

public class CampoMinado {
    public static final int VAZIO = 0;
    /* de 1 a 8 são o número de minas à volta */
    public static final int TAPADO = 9;
    public static final int DUVIDA = 10;
    public static final int MARCADO = 11;
    public static final int REBENTADO = 12;

    private boolean[][] minas;
    private int[][] estado;

    private final int largura;
    private final int altura;
    private final int numMinas;
    private boolean primeiraJogada;
    private boolean jogadorDerrotado;
    private boolean jogoTerminado;
    private long instanteInicioJogo;
    private long duracaoJogo;

    public CampoMinado(int largura, int altura, int numMinas) {
        this.largura = largura;
        this.altura = altura;
        this.numMinas = numMinas;
        minas = new boolean[largura][altura]; // Valores começam a false
        estado = new int[largura][altura]; // Valores começam a 0
        for (var x = 0; x < largura; ++x) {
            for (var y = 0; y < altura; ++y) {
                estado[x][y] = TAPADO;
            }
        }
        primeiraJogada = true;
        jogoTerminado = false;
        jogadorDerrotado = false;
    }

    public void revelarQuadricula(int x, int y) { //FALTA
        if (jogoTerminado || estado[x][y] < TAPADO) {
            return;
        }
        if(primeiraJogada){
            primeiraJogada = false;
            colocarMinas(x,y);

            instanteInicioJogo = System.currentTimeMillis();
        }

//        if (hasMina(x,y)){
        if (minas[x][y]){
            estado[x][y] = REBENTADO;
            jogadorDerrotado = true;
            jogoTerminado = true;

            duracaoJogo = System.currentTimeMillis() - instanteInicioJogo;
        }else{
            int minasVizinhas = contarMinasVizinhas(x,y);
            if(minasVizinhas == 0){
                estado[x][y] = VAZIO;
                revelarQuadriculasVizinhas(x,y);
            }else{
                estado[x][y] = minasVizinhas;
            }
            if(isVitoria()){
                jogoTerminado = true;
                jogadorDerrotado = false;

                duracaoJogo = System.currentTimeMillis() - instanteInicioJogo;
            }
        }
    }

    private void revelarQuadriculasVizinhas(int x, int y){ //não sei se está bem implementada
        for (var i = Math.max(0, x - 1); i < Math.min(largura, x + 2); ++i) {
            for (var j = Math.max(0, y - 1); j < Math.min(altura, y + 2); ++j) {
                revelarQuadricula(i,j);
            }
        }
    }

    public long getDuracaoJogo(){
        if (primeiraJogada) {
            return 0;
        }
        if (!jogoTerminado) { //entra neste if caso não se acabe o jogo?
            return System.currentTimeMillis() - instanteInicioJogo;
        }
        return duracaoJogo;
    }

    public void marcarComoTendoMina(int x, int y){
        if(estado[x][y] == TAPADO || estado[x][y] == DUVIDA){
            estado[x][y] = MARCADO;
        }
    }

    public void marcarComoSuspeita(int x, int y){
        if(estado[x][y] == TAPADO || estado[x][y] == MARCADO){
            estado[x][y] = DUVIDA;
        }
    }

    public void desmarcarQuadricula(int x, int y){
        if(estado[x][y] == MARCADO || estado[x][y] == DUVIDA){
            estado[x][y] = TAPADO;
        }
    }

    private int contarMinasVizinhas(int x, int y) { //FALTA
        var numMinasVizinhas = 0;
        for (var i = Math.max(0, x - 1); i < Math.min(largura, x + 2); ++i) {
            for (var j = Math.max(0, y - 1); j < Math.min(altura, y + 2); ++j) {
                if (minas[i][j]) {
                    ++numMinasVizinhas;
                }
            }
        }
        return numMinasVizinhas;
    }

    private void colocarMinas(int exceptoX, int exceptoY) {
        var aleatorio = new Random();
        var x = 0;
        var y = 0;
        for (var i = 0; i < numMinas; ++i) {
            do {
                x = aleatorio.nextInt(largura);
                y = aleatorio.nextInt(altura);
            } while (minas[x][y] || (x == exceptoX && y == exceptoY));
            minas[x][y] = true;
        }
    }

    public int getLargura() {
        return largura;
    }

    public int getAltura() {
        return altura;
    }

    public int getEstadoQuadricula(int x, int y) {
        return estado[x][y];
    }

    public boolean hasMina(int x, int y) {
        return minas[x][y];
    }

    public boolean isJogadorDerrotado() {
        return jogadorDerrotado;
    }

    public boolean isJogoTerminado() {
        return jogoTerminado;
    }

    private boolean isVitoria() {
        for (int i = 0; i < largura; ++i) {
            for (var j = 0 ; j < altura; ++j) {
                if (!minas[i][j] && estado[i][j] >= TAPADO) { //se houver alguma botao sem mina que ainda esteja tapado, ainda não se ganhou
                    return false;
                }
            }
        }
        return true;
    }

}
