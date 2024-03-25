package demo;

import java.io.Serializable;

public class Employee implements Serializable
{
    private String name;
    private int empno;
    private String designation;

    public Employee(String name, int empno, String designation) {
        this.name = name;
        this.empno = empno;
        this.designation = designation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEmpno() {
        return empno;
    }

    public void setEmpno(int empno) {
        this.empno = empno;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    @Override
    public String toString() {
        return "Employee [name=" + name + ", empno=" + empno + ", designation=" + designation + "]";
    }
    
}
