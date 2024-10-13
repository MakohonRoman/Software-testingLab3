package labs;/*
@author   $Makohon_Roman
@project   $Company service
@class  $444A
@version  1.0.0
@since 02.10.2024 - 14.21
*/

public class Company {
    private Company parent; // Батьківська компанія
    private long employeeCount; // Кількість працівників

    // Конструктор
    public Company(Company parent, long employeeCount) {
        this.parent = parent;
        this.employeeCount = employeeCount;
    }

    // Геттери та сеттери
    public Company getParent() {
        return parent;
    }

    public void setParent(Company parent) {
        this.parent = parent;
    }

    public long getEmployeeCount() {
        return employeeCount;
    }

    public void setEmployeeCount(long employeeCount) {
        this.employeeCount = employeeCount;
    }
}

