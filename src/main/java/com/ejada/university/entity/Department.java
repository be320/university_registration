package com.ejada.university.entity;

import javax.persistence.*;
import java.util.List;

@Table(name = "department")
@Entity
public class Department {

    private int department_id;
    private String name;
    private Instructor manager;
    private List<Course> courses;
    private List<Instructor> instructors;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "department_id")
    public int getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(int department_id) {
        this.department_id = department_id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "manager_id")
    public Instructor getManager() {
        return manager;
    }

    public void setManager(Instructor manager) {
        this.manager = manager;
    }

    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    public List<Instructor> getInstructors() {
        return instructors;
    }

    public void setInstructors(List<Instructor> instructors) {
        this.instructors = instructors;
    }
}
