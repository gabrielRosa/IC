package graph;

import java.io.IOException;

public class Calculos {

    public static Double simLch(final Double pathLength, final Double maxLength) {
        return -Math.log(pathLength / (2 * maxLength));
    }

    public static Double simWup(final Double profundidadeDoPai, final Double profundidadeDoFilho, final Double profundidadeDoFilho2) {
        return (2 * profundidadeDoPai / (profundidadeDoFilho + profundidadeDoFilho2));
    }

    public static Double simRes(final Double ocorrencia, final Double total) throws IOException {
        Double p = p(ocorrencia, total);
        return -Math.log(p);
    }

    public static Double simLin(final Double ocorrencia, final Double total) throws IOException {
        return 2 * simRes(ocorrencia, total) / (simRes(ocorrencia, total) + simRes(ocorrencia, total));
    }

    public static Double simJnc(final Double ocorrencia, final Double total) throws IOException {
        return 1 / ((simRes(ocorrencia, total) + simRes(ocorrencia, total)) - 2 * simRes(ocorrencia, total));
    }

    private static Double p(final Double ocorrencia, final Double total) {
        return ocorrencia / total;
    }

}
