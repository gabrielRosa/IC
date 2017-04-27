package triple;

import enums.Relacao;
import enums.Status;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Palavra implements Comparable<Palavra> {

    private final String palavra;
    private final Map<Relacao, List<Palavra>> relacoes;
    private Status status;

    public Palavra(String palavra) {
        this.palavra = palavra;
        this.relacoes = new HashMap<>();
        this.status = Status.AUSENTE;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getPalavra() {
        return palavra;
    }

    public Map<Relacao, List<Palavra>> getRelacoes() {
        return relacoes;
    }

    public void addNovaRelacao(Relacao relacao, Palavra palavra) {
        if (!this.relacoes.containsKey(relacao)) {
            this.relacoes.put(relacao, new ArrayList<>());
        }
        this.relacoes.get(relacao).add(palavra);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Palavra other = (Palavra) obj;
        return Objects.equals(this.palavra, other.palavra);
    }

    @Override
    public String toString() {
        return palavra;
    }

    @Override
    public int compareTo(Palavra outraPalavra) {
        return this.palavra.compareTo(outraPalavra.getPalavra());
    }

}
