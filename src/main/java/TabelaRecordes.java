import java.util.ArrayList;

public class TabelaRecordes {
    private String nome_jogador;
    private long duracao_jogo;
    private ArrayList<TabelaRecordesListener> listeners;

    public TabelaRecordes() {
        nome_jogador = "Anónimo";
        duracao_jogo = 9999999;
        listeners = new ArrayList<>();
    }

    public void setRecorde(String nome_jogador, long duracao_jogo){
        if(duracao_jogo < this.duracao_jogo){
            this.nome_jogador = nome_jogador;
            this.duracao_jogo = duracao_jogo;
            notifyRecordesActualizados();
        }
    }

    private void notifyRecordesActualizados() {
        for (TabelaRecordesListener list:listeners)
            list.recordesActualizados(this);
    }

    public void addTabelaRecordesListener(TabelaRecordesListener list) {
        listeners.add(list);
    }
    public void removeTabelaRecordesListener(TabelaRecordesListener list) {
        listeners.remove(list);
    }


    public String getNome_jogador() {
        return nome_jogador;
    }

    public long getDuracao_jogo() {
        return duracao_jogo;
    }
}
