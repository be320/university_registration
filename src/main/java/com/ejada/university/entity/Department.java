package com.ejada.university.entity;

import javax.persistence.*;
import java.util.List;

@Table(name = "department")
@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "department_id")
    private int departmentId;

    @Column(name = "name")
    private String name;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "manager_id")
    private Instructor manager;

    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    private List<Course> courses;

    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    private List<Instructor> instructors;


    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Instructor getManager() {
        return manager;
    }

    public void setManager(Instructor manager) {
        this.manager = manager;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public List<Instructor> getInstructors() {
        return instructors;
    }

    public void setInstructors(List<Instructor> instructors) {
        this.instructors = instructors;
    }
}