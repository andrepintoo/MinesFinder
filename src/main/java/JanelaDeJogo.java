import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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

        painelJogo.setLayout(new GridLayout(largura,altura));

        // Criar e adicionar os botões à janela
        for (int linha = 0; linha < largura; ++linha) {
            for (int coluna = 0; coluna < altura; ++coluna){
//                BotaoCampoMinado btn = new BotaoCampoMinado(linha,coluna);   // antes tinha-se --> JButton btn = new JButton();
                btns[linha][coluna] = new BotaoCampoMinado(linha,coluna);
                //btn.setText("C"+coluna+ " L"+linha);   //Serve só para testar o comportamento
//                btn.setEstado(linha);
//                btns[largura][altura].setEstado(linha);
                btns[linha][coluna].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        var btn = (BotaoCampoMinado) e.getSource(); //para descobrir qual o botao que despoletou este evento
                        btn.setEstado(CampoMinado.MARCADO);
                        btn.setText(btn.getLinha()+ ":" + btn.getColuna());
                        var x = btn.getLinha();
                        var y = btn.getColuna();
                        campoMinado.revelarQuadricula(x,y);
                        actualizarEstadoBotoes();
                        if (campoMinado.isJogoTerminado()) {
                            if (campoMinado.isJogadorDerrotado()) {
                                JOptionPane.showMessageDialog(null, "Oh, rebentou uma mina",
                                        "Perdeu!", JOptionPane.INFORMATION_MESSAGE);
                            }
                            else {
                                JOptionPane.showMessageDialog(null, "Parabéns. Conseguiu descobrir todas as minas em " +
                                        (campoMinado.getDuracaoJogo() / 1000) + " segundos", "Vitória", JOptionPane.INFORMATION_MESSAGE);
                            }
                            setVisible(false);
                        }

                    }
                });
                MouseListener mouseListener=new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                    }
                    @Override
                    public void mousePressed(MouseEvent e) {
                        if (e.getButton() != MouseEvent.BUTTON3) {
                            return;
                        }
                        var botao = (BotaoCampoMinado) e.getSource();
                        var x = botao.getLinha();
                        var y = botao.getColuna();

                        var estadoQuadricula = campoMinado.getEstadoQuadricula(x, y);
                        if (estadoQuadricula == CampoMinado.TAPADO) {
                            campoMinado.marcarComoTendoMina(x, y);
                        } else if (estadoQuadricula == CampoMinado.MARCADO) {
                            campoMinado.marcarComoSuspeita(x, y);
                        } else if (estadoQuadricula == CampoMinado.DUVIDA) {
                            campoMinado.desmarcarQuadricula(x, y);
                        }
                        actualizarEstadoBotoes();
                    }
                    @Override
                    public void mouseReleased(MouseEvent e) {
                    }
                    @Override
                    public void mouseEntered(MouseEvent e) {
                    }
                    @Override
                    public void mouseExited(MouseEvent e) {
                    }
                };

                btns[linha][coluna].addMouseListener(mouseListener);
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

    private void actualizarEstadoBotoes() {
        for (int x = 0; x < campoMinado.getLargura(); x++) {
            for (int y = 0; y < campoMinado.getAltura(); y++) {
                btns[x][y].setEstado(campoMinado.getEstadoQuadricula(x, y));
            }
        }
    }
}
