package triple;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Gabriel da Rosa
 */
public class Relacão {

    private final Map<String, Map<String, Resultado>> relacao;
    
    public Relacão(){
        relacao = new HashMap<>();
    }
    
    public void adicionar(final String primeiraPalavra, final String segundaPalavra, final Resultado resultados){
        final Map<String, Resultado> obj = new HashMap<>();
        obj.put(segundaPalavra, resultados);
        relacao.put(primeiraPalavra, obj);
    }
    
    public Resultado getResultados(final String primeiraPalavra, final String segundaPalavra){
        return this.relacao.get(primeiraPalavra).get(segundaPalavra);
    }
}
