package szp.rafael.pact.server;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import szp.rafael.pact.model.Employee;

import java.util.Set;

@Path("/employees")
public class EmployeeResource {

    @GET
    @Path("/department/{id: \\d+}")
    public Set<Employee> findByDepartmentID(@PathParam("id") Long departmentId) {
        Employee employee = new Employee(1L,"John Doe", "john.doe@example.com",departmentId);
        return Set.of(employee);
    }
}
