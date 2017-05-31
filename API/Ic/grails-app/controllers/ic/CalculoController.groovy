package ic

import configuracao.Configuracao
import grails.converters.JSON
import graph.Builder
import service.CalculoService


class CalculoController {

    static allowedMethods= [
        beInState: 'GET',
        hasHyponym: 'GET',
        nearAntonym: 'GET',
        nearSynonym: 'GET',
        seeAlsoWN15: 'GET',
        verbGroup: 'GET',
        rGloss: 'GET',
        relatedTo: 'GET',
        hasHyperonym: 'GET'
    ]

    Builder builder = Configuracao.builder
    CalculoService calculoService

    public def beInState(String word1, String word2){
        render(calculoService.calculo(builder.graphBeInState, word1, word2) as JSON)
    }

    public def hasHyponym(String word1, String word2){
        render(calculoService.calculo(builder.getGraphHasHyponym(), word1, word2) as JSON)
    }

    public def nearAntonym(String word1, String word2){
        render(calculoService.calculo(builder.getGraphNearAntonym(), word1, word2) as JSON)
    }

    public def nearSynonym(String word1, String word2){
        render(calculoService.calculo(builder.graphNearSynonym, word1, word2) as JSON)
    }

    public def seeAlsoWN15(String word1, String word2){
        render(calculoService.calculo(builder.graphSeeAlsoWN15, word1, word2) as JSON)
    }

    public def verbGroup(String word1, String word2){
        render(calculoService.calculo(builder.getGraphVerbGroup(), word1, word2) as JSON)
    }

    public def rGloss(String word1, String word2){
        render(calculoService.calculo(builder.getGraphRgloss(), word1, word2) as JSON)
    }

    public def relatedTo(String word1, String word2){
        render(calculoService.calculo(builder.getGraphRelatedTo(), word1, word2) as JSON)
    }

    public def hasHyperonym(String word1, String word2){
        render(calculoService.calculo(builder.graphHasHyperonym, word1, word2) as JSON)
    }
}
