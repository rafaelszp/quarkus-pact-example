package szp.rafael.pact.client;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import szp.rafael.pact.model.Employee;

import java.util.Set;

@ApplicationScoped
@RegisterRestClient(configKey = "employee-api")
@Path("/employees")
public interface EmployeeClient {

    @GET
    @Path("/department/{id: \\d+}")
    Set<Employee> findByDepartmentID(@PathParam("id") Long departmentId);
}
