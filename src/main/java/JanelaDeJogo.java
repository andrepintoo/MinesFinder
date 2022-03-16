import javax.swing.*;

public class JanelaDeJogo extends JFrame{
    private JPanel painelJogo; // painel do jogo. O nome é definido no modo  Design, em "field name"

    public JanelaDeJogo(String titulo) {
        super(titulo);
        setContentPane(painelJogo);
        // Destrói esta janela, removendo-a completamente da memória.
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack(); //ainda nao faz nada pq ainda nao se tem nenhum controlo dentro da vista
        //setVisible(true); // opcional. Pode optar por fazer depois. -> fez-se na classe MinesFinder
    }
}
