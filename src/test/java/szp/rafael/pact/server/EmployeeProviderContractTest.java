package szp.rafael.pact.server;

import au.com.dius.pact.provider.junit5.HttpTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import au.com.dius.pact.provider.junitsupport.Provider;
import au.com.dius.pact.provider.junitsupport.State;
import au.com.dius.pact.provider.junitsupport.loader.PactBroker;
import au.com.dius.pact.provider.junitsupport.target.TestTarget;
import io.quarkus.test.junit.QuarkusTest;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import szp.rafael.pact.model.Employee;

import java.util.Set;

@QuarkusTest
@Provider("employee-api")
@PactBroker(url = "http://localhost:9292")
public class EmployeeProviderContractTest {

    @ConfigProperty(name="quarkus.http.test-port")
    int port;

    @TestTarget
    HttpTestTarget target = new HttpTestTarget("localhost", this.port);

    @TestTemplate
    @ExtendWith(PactVerificationInvocationContextProvider.class)
    void pactVerificationTestTemplate(PactVerificationContext context){
        context.verifyInteraction();
        System.setProperty("pact.provider.version", "1.2");
        System.setProperty("pact.verifier.publishResults", "true");
        System.setProperty("pact_do_not_track", "true");
    }

    @BeforeEach
    void beforeEach(PactVerificationContext context) {
        context.setTarget(new HttpTestTarget("localhost",
                this.port));


    }

    @State("findByDepartmentID")//Same defined in consumer class on builder.given() method
    void findByDepartmentID() {

    }


}
