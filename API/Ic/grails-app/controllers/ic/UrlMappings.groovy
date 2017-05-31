package ic

class UrlMappings {

    static mappings = {

        group('/api'){
            "/be_in_state/${word1}/${word2}"(controller: 'calculo', action: 'beInState')
            "/has_hyponym/${word1}/${word2}"(controller: 'calculo', action: 'hasHyponym')
            "/near_antonym/${word1}/${word2}"(controller: 'calculo', action: 'nearAntonym')
            "/near_synonym/${word1}/${word2}"(controller: 'calculo', action: 'nearSynonym')
            "/see_also_wn15/${word1}/${word2}"(controller: 'calculo', action: 'seeAlsoWN15')
            "/verb_group/${word1}/${word2}"(controller: 'calculo', action: 'verbGroup')
            "/rgloss/${word1}/${word2}"(controller: 'calculo', action: 'rGloss')
            "/related_to/${word1}/${word2}"(controller: 'calculo', action: 'relatedTo')
            "/has_hyperonym/${word1}/${word2}"(controller: 'calculo', action: 'hasHyperonym')
        }

        "/"(controller: 'application', action:'index')
        "500"(view: '/error')
        "404"(view: '/notFound')
    }
}
