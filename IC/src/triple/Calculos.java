package triple;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

/**
 *
 * @author Gabriel da Rosa
 */
public class Calculos {

    public static Double simLch(final int pathLength, final int maxLength) {
        return -Math.log(pathLength / (2 * maxLength));
    }

    public static Double simWup(final int profundidadeDoPai, final int profundidadeDoFilho, final int profundidadeDoFilho2) {
        return Double.valueOf(2 * profundidadeDoPai / (profundidadeDoFilho + profundidadeDoFilho2));
    }

    public static Double simRes(final BufferedReader bufferedReader, final String palavra) throws IOException {
        Double p = p(bufferedReader, palavra);
        return -Math.log(p);
    }

    public static Double simLin(final String lcs, final String palavra, String palavra2, final BufferedReader bufferedReader) throws IOException {
        return 2 * simRes(bufferedReader, lcs) / (simRes(bufferedReader, palavra) + simRes(bufferedReader, palavra2));
    }

    public static Double simJnc(final String lcs, final String palavra, String palavra2, final BufferedReader bufferedReader) throws IOException {
        return 1 / ((simRes(bufferedReader, palavra) + simRes(bufferedReader, palavra2)) - 2 * simRes(bufferedReader, lcs));
    }

    private static Double p(final BufferedReader bufferedReader, final String palavra) throws IOException {
        String linha;
        int total = 0;
        int ocorrencia = 0;
        while ((linha = bufferedReader.readLine()) != null) {
            String[] valores = linha.split(" ");
            if (valores[0].equals(palavra) || valores[2].equals(palavra)) {
                ocorrencia++;
            }
            total += 2;
        }
        return Double.valueOf(ocorrencia / total);
    }

}
