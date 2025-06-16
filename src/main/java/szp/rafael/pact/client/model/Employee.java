package szp.rafael.pact.client.model;

import java.time.LocalDate;

public record Employee(Long id, String name, String email, Long departmentId, LocalDate hireDate) {

    public Employee {

    }
}
