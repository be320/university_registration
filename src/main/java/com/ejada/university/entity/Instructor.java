package com.ejada.university.entity;

import javax.persistence.*;
import java.util.List;

@Table(name = "instructor")
@Entity
@PrimaryKeyJoinColumn(name = "instructor_id")
public class Instructor extends Person {

    @Column(name = "salary")
    private double salary;

    @Column(name = "years_of_experience")
    private int yearsOfExperience;


    @OneToMany(mappedBy = "instructor", fetch = FetchType.LAZY)
    private List<Course> courses;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id")
    private Department department;


    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}