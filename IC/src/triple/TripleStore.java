package triple;

import enums.Relacao;
import enums.Status;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TripleStore {

    private final BTree<Palavra> ARVORE;

    public TripleStore() throws FileNotFoundException {
        this.ARVORE = new BTree<>();
    }

    public void carregaPalavras(final BufferedReader bufferedReader) throws FileNotFoundException, IOException {
        String linha;
        while ((linha = bufferedReader.readLine()) != null) {
            String[] valores = linha.split(" ");
            ARVORE.insert(new Palavra(valores[0]));
            ARVORE.insert(new Palavra(valores[2]));
        }
    }

    public void montarRelacoes(final BufferedReader bufferedReader) throws IOException {
        String linha;
        while ((linha = bufferedReader.readLine()) != null) {
            String[] valores = linha.split(" ");
            Palavra palavraEsquerda = ARVORE.getElement(new Palavra(valores[0]));
            Palavra palavraDireita = ARVORE.getElement(new Palavra(valores[2]));
            palavraEsquerda.addNovaRelacao(Relacao.HIPONOMIA_DE, palavraDireita);
            palavraDireita.addNovaRelacao(Relacao.HIPERONIMO_DE, palavraEsquerda);
        }
    }

    public Map<String, String> estruturar(String palavra, String palavra2, Relacao relacao) {
        Palavra p = ARVORE.getElement(new Palavra(palavra));
        p.setStatus(Status.PRESENTE);

        List<Estrutura> estruturas = new ArrayList<>(2500);
        estruturas.add(new Estrutura(0));

        List<Map<String, String>> list = new ArrayList<>();

        for (Palavra pal : p.getRelacoes().get(relacao)) {
            if (pal.getStatus().equals(Status.AUSENTE)) {
                estruturas.get(0).adicionarPalavra(pal);
                pal.setStatus(Status.PRESENTE);
                if (pal.getPalavra().equals(palavra2)) {
                    Map<String, String> m = new HashMap<>();
                    m.put("PathLength", String.valueOf(estruturas.get(0).getNivel() + 1));
                    m.put("FirstCommonFather", palavra);
                    list.add(m);
                }
                estruturar(estruturas, pal.toString(), palavra2, 0, list, relacao);
            }
        }

        Map<String, String> map = new HashMap<>(4);

        map.put("FirstWord", palavra);
        map.put("EndWord", palavra2);

        Map<String, String> menorPathLength = list.isEmpty() ? null : list.get(0);
        for (Map<String, String> x : list) {
            for (Map<String, String> y : list) {
                if (Integer.parseInt(x.get("PathLength")) <= Integer.parseInt(y.get("PathLength"))) {
                    menorPathLength = x;
                }
            }
        }

        if (menorPathLength != null) {
            map.put("PathLength", this.getPrimeiroPaiEmComum(palavra, palavra2, relacao));
        } else {
            map.put("PathLength", null);
        }

        map.put("FirstCommonFather", menorPathLength.get("FirstCommonFather"));
        map.put("LengthOfNodo", String.valueOf(estruturas.size()));

        this.limparPrensecaPalavras();

        return map;
    }

    private List<Map<String, String>> estruturar(List<Estrutura> estruturas, String palavra, String palavra2, int level, List<Map<String, String>> list, Relacao relacao) {
        if (!estruturas.contains(new Estrutura(level + 1))) {
            estruturas.add(new Estrutura(level + 1));
        }

        List<Palavra> p = ARVORE.getElement(new Palavra(palavra)).getRelacoes().get(relacao);
        if (p != null) {
            for (Palavra pal : p) {
                if (pal.getStatus().equals(Status.AUSENTE)) {
                    if (pal.getPalavra().equals(palavra2)) {
                        Map<String, String> map = new HashMap<>();
                        map.put("PathLength", String.valueOf(level + 1));
                        list.add(map);
                    } else {
                        pal.setStatus(Status.PRESENTE);
                    }
                    estruturas.get(level).adicionarPalavra(pal);
                    estruturar(estruturas, pal.toString(), palavra2, level + 1, list, relacao);
                }
            }
        }
        return list;
    }

    public String getPrimeiroPaiEmComum(String palavra, String palavra2, Relacao relacao) {
        if (relacao.equals(Relacao.HIPERONIMO_DE)) {
            Palavra p = ARVORE.getElement(new Palavra(palavra));
            List<Palavra> palavras = p.getRelacoes().get(Relacao.HIPONOMIA_DE);
            if (palavras.isEmpty()) {
                return palavra;
            }
            return palavras.get(0).getPalavra();
        }

        Palavra p = ARVORE.getElement(new Palavra(palavra2));
        List<Palavra> palavras = p.getRelacoes().get(Relacao.HIPONOMIA_DE);
        if (palavras.isEmpty()) {
            return palavra;
        }
        return palavras.get(0).getPalavra();
    }

    public void getNivel(String palavra) {
        Palavra pal = ARVORE.getElement(new Palavra(palavra));
        List<Integer> nivel = new ArrayList<>(1000);

        for (Palavra p : pal.getRelacoes().get(Relacao.HIPERONIMO_DE)) {
            getNivel(p.getPalavra(), nivel, 1);
        }

        Collections.sort(nivel);
        int a = nivel.get(0);
    }

    public BTree<Palavra> getArvore() {
        return ARVORE;
    }

    private void limparPrensecaPalavras() {
        for (Palavra palavra : ARVORE.getAllAsList()) {
            palavra.setStatus(Status.AUSENTE);
        }
    }

    private void getNivel(String palavra, List<Integer> nivel, Integer cont) {
        List<Palavra> p = ARVORE.getElement(new Palavra(palavra)).getRelacoes().get(Relacao.HIPERONIMO_DE);
        if (p != null) {
            for (Palavra pal : p) {
                if (pal.getStatus().equals(Status.AUSENTE)) {
                    getNivel(pal.getPalavra(), nivel, cont + 1);
                }else{
                    pal.setStatus(Status.PRESENTE);
                }
            }
        } else {
            nivel.add(cont);
        }
    }

}
