package main;

import enums.Relacao;
import java.io.BufferedReader;
import java.io.FileReader;
import triple.TripleStore;
import java.io.IOException;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {
        TripleStore t = new TripleStore();
        t.carregaPalavras(new BufferedReader(new FileReader("C:\\Users\\Gabriel da Rosa\\Downloads\\relacoes_final_HIPERONIMIA.txt")));
        t.montarRelacoes(new BufferedReader(new FileReader("C:\\Users\\Gabriel da Rosa\\Downloads\\relacoes_final_HIPERONIMIA.txt")));
        Map<String, String> a = t.estruturar("região", "espaço", Relacao.HIPERONIMO_DE);
        t.getNivel("região");
    }

}
