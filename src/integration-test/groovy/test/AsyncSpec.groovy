package test


import grails.test.mixin.integration.Integration
import grails.transaction.*
import spock.lang.*
import grails.async.Promises
import static grails.async.Promises.*

@Integration
@Rollback
class AsyncSpec extends Specification {

    def setup() {
    		// to prevent org.grails.async.factory.reactor.ReactorPromiseFactory 
    		// being used which fails to find dispatchers
    		//Promises.promiseFactory = new org.grails.async.factory.gpars.GparsPromiseFactory()
    }

    def cleanup() {
    }

    void "test promises"() {
    		def p1 = task { 2 * 2 }
    		def p2 = task { 4 * 4 }
    		def p3 = task { 8 * 8 }
    		expect: [4,16,64] == waitAll(p1, p2, p3)
    }
}
