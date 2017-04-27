package triple;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Gabriel da Rosa
 */
public class Estrutura {
    private final List<Palavra> palavras;
    private final Integer nivel;

    public Estrutura(Integer nivel) {
        this.palavras = new ArrayList<>();
        this.nivel = nivel;
    }

    public List<Palavra> getPalavras() {
        return palavras;
    }

    public Integer getNivel() {
        return nivel;
    }
    
    public void adicionarPalavra(Palavra palavra){
        palavras.add(palavra);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.nivel);
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
        final Estrutura other = (Estrutura) obj;
        if (!Objects.equals(this.nivel, other.nivel)) {
            return false;
        }
        return true;
    }
    
}
