package ic

import configuracao.Configuracao
import grails.boot.GrailsApp
import grails.boot.config.GrailsAutoConfiguration

class Application extends GrailsAutoConfiguration {

    static void main(String[] args) {
        Configuracao.run()

        GrailsApp.run(Application, args)
    }
}