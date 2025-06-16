package szp.rafael.pact.server.model;

import java.time.LocalDate;

public record Employee(Long id, String name, String email, Long departmentId) {
//public record Employee(Long id, String name, String email, Long departmentId,LocalDate hireDate) {
//public record Employee(Long id, String name, String email, Long departmentId,LocalDate hireDate,Boolean active) {

    public Employee {

    }
}
//public record Employee(Long id, String name, String email) {
