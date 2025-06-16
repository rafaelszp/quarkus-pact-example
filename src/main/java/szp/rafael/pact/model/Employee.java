package szp.rafael.pact.model;

import org.apache.commons.lang3.StringUtils;

public record Employee(Long id, String name, String email, Long departmentId) {

    public Employee {
        if(id==null || id < 0) {
            throw new IllegalArgumentException("ID must be a non-negative number");
        }
        if (StringUtils.isBlank(name)) {
            throw new IllegalArgumentException("Name cannot be null or blank");
        }
        if (StringUtils.isBlank(email)) {
            throw new IllegalArgumentException("Email cannot be null or blank");
        }
    }
}
