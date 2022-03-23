import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JanelaDeJogo extends JFrame{
    private JPanel painelJogo; // painel do jogo. O nome é definido no modo  Design, em "field name"

    private BotaoCampoMinado[][] btns;
    private CampoMinado campoMinado;
    private TabelaRecordes recordes;

    public JanelaDeJogo(String titulo, CampoMinado campoMinado, TabelaRecordes recordes) {
        super(titulo);
        this.campoMinado = campoMinado;
        int largura = campoMinado.getLargura();
        int altura = campoMinado.getAltura();
        this.recordes = recordes;
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
                            boolean novoRecorde = campoMinado.getDuracaoJogo() < recordes.getDuracao_jogo();
                            if (novoRecorde) {
                                String nome=JOptionPane.showInputDialog("Introduza o seu nome");
                                recordes.setRecorde(nome, campoMinado.getDuracaoJogo());
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

//                        var estadoQuadricula = campoMinado.getEstadoQuadricula(x, y);
//                        if (estadoQuadricula == CampoMinado.TAPADO) {
//                            campoMinado.marcarComoTendoMina(x, y);
//                        } else if (estadoQuadricula == CampoMinado.MARCADO) {
//                            campoMinado.marcarComoSuspeita(x, y);
//                        } else if (estadoQuadricula == CampoMinado.DUVIDA) {
//                            campoMinado.desmarcarQuadricula(x, y);
//                        }
                        marcarQuadricula(x, y, campoMinado);
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

                KeyListener keyListener = new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                    }
                    @Override
                    public void keyPressed(KeyEvent e) {
                        var botao = (BotaoCampoMinado) e.getSource();
                        var linha = botao.getLinha();
                        var coluna = botao.getColuna();
                        switch (e.getKeyCode()) {
                            case KeyEvent.VK_LEFT -> btns[linha][--coluna < 0 ? altura - 1 :
                                    coluna].requestFocus();
                            case KeyEvent.VK_RIGHT -> btns[linha][(coluna + 1) %
                                    altura].requestFocus();
                            case KeyEvent.VK_UP -> btns[--linha < 0 ? largura - 1 :
                                    linha][coluna].requestFocus();
                            case KeyEvent.VK_DOWN -> btns[(linha + 1) %
                                    largura][coluna].requestFocus();
                            case KeyEvent.VK_M -> {
                                marcarQuadricula(linha, coluna, campoMinado);
                                actualizarEstadoBotoes();
                            }
                        }
                    }
                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                };

                btns[linha][coluna].addMouseListener(mouseListener);
                btns[linha][coluna].addKeyListener(keyListener);
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

    private void marcarQuadricula(int linha, int coluna, CampoMinado campoMinado) {
        switch (campoMinado.getEstadoQuadricula(linha, coluna)) {
            case CampoMinado.TAPADO ->
                    campoMinado.marcarComoTendoMina(linha, coluna);
            case CampoMinado.MARCADO ->
                    campoMinado.marcarComoSuspeita(linha, coluna);
            case CampoMinado.DUVIDA ->
                    campoMinado.desmarcarQuadricula(linha, coluna);
        }
    }

    private void actualizarEstadoBotoes() {
        for (int x = 0; x < campoMinado.getLargura(); x++) {
            for (int y = 0; y < campoMinado.getAltura(); y++) {
                btns[x][y].setEstado(campoMinado.getEstadoQuadricula(x, y));
            }
        }
    }
}
