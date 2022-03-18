import javax.swing.*;
import java.awt.*;

public class BotaoCampoMinado extends JButton {
    private int estado;
    private int linha; //para saber em que linha e coluna se encontra o botao
    private int coluna;

    public BotaoCampoMinado(int linha, int coluna){
        this.estado = CampoMinado.TAPADO; //quando o botao inicia, está tapado
        this.linha = linha;
        this.coluna = coluna;
    }

    public void setEstado(int estado) {
        this.estado = estado;
        switch (estado){
            case CampoMinado.VAZIO:
                setText("");
                setBackground(Color.LIGHT_GRAY);
                break;
            case CampoMinado.TAPADO:
                setText("");
                setBackground(null);
                break;
            case CampoMinado.DUVIDA:
                setText("?");
                setBackground(Color.yellow);
                break;
            case CampoMinado.REBENTADO:
                setText("!");
                setBackground(Color.RED);
                break;
            case CampoMinado.MARCADO:
                setText("*");
                setBackground(Color.ORANGE);
                break;
            default:
                setText(String.valueOf(estado));
                setBackground(Color.LIGHT_GRAY);
                break;
        }
    }

    public int getLinha() {
        return linha;
    }

    public int getColuna() {
        return coluna;
    }
}
