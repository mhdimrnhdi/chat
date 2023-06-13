/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package facemash;

import java.util.ArrayList;
import java.util.List;

public class JobList {
    private List<String> jobs;

    public void initializeJobs() {
        jobs = new ArrayList<>();
        jobs.add("Software Developer");
        jobs.add("Nurse");
        jobs.add("Teacher");
        jobs.add("Accountant");
        jobs.add("Marketing Manager");
        jobs.add("Graphic Designer");
        jobs.add("Doctor");
        jobs.add("Sales Representative");
        jobs.add("Project Manager");
        jobs.add("Financial Analyst");
        jobs.add("Data Scientist");
        jobs.add("Civil Engineer");
        jobs.add("Human Resources Manager");
        jobs.add("Chef");
        jobs.add("Electrician");
        jobs.add("Writer");
        jobs.add("Architect");
        jobs.add("Physical Therapist");
        jobs.add("Police Officer");
        jobs.add("Web Designer");
    }

    public List<String> getJobs() {
        return jobs;
    }
}

