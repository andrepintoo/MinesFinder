import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JanelaDeJogo extends JFrame{
    private JPanel painelJogo; // painel do jogo. O nome é definido no modo  Design, em "field name"

    private BotaoCampoMinado[][] btns;
    private CampoMinado campoMinado;

    public JanelaDeJogo(String titulo, CampoMinado campoMinado) {
        super(titulo);
        this.campoMinado = campoMinado;
        int largura = campoMinado.getLargura();
        int altura = campoMinado.getAltura();
        this.btns = new BotaoCampoMinado[largura][altura];

//        painelJogo.setLayout(new GridLayout(altura,largura)); //alterei
        painelJogo.setLayout(new GridLayout(largura,altura));

        // Criar e adicionar os botões à janela
        for (int linha = 0; linha < largura; ++linha) {
            for (int coluna = 0; coluna < altura; ++coluna){
//                BotaoCampoMinado btn = new BotaoCampoMinado(linha,coluna);   // antes tinha-se --> JButton btn = new JButton();
                btns[linha][coluna] = new BotaoCampoMinado(linha,coluna); //btns[largura][altura]
                //btn.setText("C"+coluna+ " L"+linha);   //Serve só para testar o comportamento
//                btn.setEstado(linha);
//                btns[largura][altura].setEstado(linha);
                btns[linha][coluna].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
//                        BotaoCampoMinado btn = (BotaoCampoMinado) e.getSource(); //para descobrir qual o botao que despoletou este evento
                        var btn = (BotaoCampoMinado) e.getSource(); //para descobrir qual o botao que despoletou este evento
                        btn.setEstado(CampoMinado.MARCADO);
                        btn.setText(btn.getLinha()+ ":" + btn.getColuna());

                        campoMinado.revelarQuadricula(btn.getLinha(),btn.getColuna());
                    }
                });
                painelJogo.add(btns[linha][coluna]);
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
