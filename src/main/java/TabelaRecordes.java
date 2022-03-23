public class TabelaRecordes {
    private String nome_jogador;
    private long duracao_jogo;

    public TabelaRecordes() {
        nome_jogador = "Anónimo";
        duracao_jogo = 9999999;
    }

    public void setRecorde(String nome_jogador, long duracao_jogo){
        if(duracao_jogo < this.duracao_jogo){
            this.nome_jogador = nome_jogador;
            this.duracao_jogo = duracao_jogo;
        }
    }

    public String getNome_jogador() {
        return nome_jogador;
    }

    public long getDuracao_jogo() {
        return duracao_jogo;
    }
}
