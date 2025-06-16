package szp.rafael.pact.client;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.DslPart;
import au.com.dius.pact.consumer.dsl.PactDslJsonArray;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.PactSpecVersion;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import io.quarkus.test.junit.QuarkusTest;
import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import szp.rafael.pact.model.Employee;

import java.net.URI;
import java.util.Set;

import static org.wildfly.common.Assert.assertNotNull;
import static org.wildfly.common.Assert.assertTrue;

@QuarkusTest
@ExtendWith(PactConsumerTestExt.class)
public class EmployeeClientContractTest {

    @Pact(provider = "employee-api", consumer = "department-api")
    public RequestResponsePact callFindByDepartment(PactDslWithProvider builder) {

        DslPart body = PactDslJsonArray.arrayEachLike(1)
                .integerType("id")
                .stringType("name")
                .stringType("email")
                .integerType("departmentId")
                .closeObject();

        return builder.given("findByDepartmentID")
                .uponReceiving("findByDepartmentID")
                .path("/employees/department/1")
                .method("GET")
                .willRespondWith()
                .status(200)
                .body(body)
                .toPact();
    }

    @Test
    @PactTestFor(providerName = "employee-api", pactVersion = PactSpecVersion.V3)
    public void testFindByDepartment(MockServer mockServer) {
        System.setProperty("pact_do_not_track", "true");

        EmployeeClient employeeClient = RestClientBuilder.newBuilder()
                .baseUri(URI.create(mockServer.getUrl()))
                .build(EmployeeClient.class);

        Set<Employee> employees = employeeClient.findByDepartmentID(1L);
        assertNotNull(employees);
        assertTrue(employees.size() > 0);
        assertNotNull(employees.iterator().next());

    }
}