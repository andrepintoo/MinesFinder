import javax.swing.*;
import java.awt.*;

public class JanelaDeJogo extends JFrame{
    private JPanel painelJogo; // painel do jogo. O nome é definido no modo  Design, em "field name"

    public JanelaDeJogo(String titulo, CampoMinado campoMinado) {
        super(titulo);

        int largura = campoMinado.getLargura();
        int altura = campoMinado.getAltura();

        painelJogo.setLayout(new GridLayout(altura,largura));

        // Criar e adicionar os botões à janela
        for (int linha = 0; linha < altura; ++linha) {
            for (int coluna = 0; coluna < largura; ++coluna) {
//                JButton btn = new JButton();
                BotaoCampoMinado btn = new BotaoCampoMinado();
                /* Serve só para testar o comportamento */
                btn.setText("C"+coluna+ " L"+linha);
                btn.setEstado(linha);


                painelJogo.add(btn);
            }
        }
//        for (int i = 0; i < 150; ++i) {
//            JButton btn = new JButton();
//            btn.setText(String.valueOf(i)); // ou (i + "") -> só para ver o texto em cada botao
//            painelJogo.add(btn);
//        }


        setContentPane(painelJogo);
        // Destrói esta janela, removendo-a completamente da memória.
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack(); //de inicio nao faz nada pq ainda nao se tem nenhum controlo dentro da vista
        //setVisible(true); // opcional. Pode optar por fazer depois. -> fez-se na classe MinesFinder
    }
}
