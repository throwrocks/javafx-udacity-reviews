/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udacityreviews;
import java.util.Date;
/**
 *
 * @author josel
 */
public class Certification {
    int id;
    String status;
    int project_id;
    int grader_id;
    Date created_at;
    Date updated_at;
    Date waitlisted_at;
    Date certified_at;
    int trainings_count;
    Boolean active;
    String notes;
    String project_languages_to_recruit;
    String project_name;
    double project_price;
    int project_udacity_key;
    Date project_created_at;
    Date project_updated_at;
    String project_description;
    String project_required_skills;
    Boolean project_visible;
    int project_awaiting_review_count;
    Boolean project_waitlist;
    String project_nanodegree_key;
    int project_audit_project_id;
    Boolean project_nomination_eligible;
    String project_hashtag;

    public Certification(){};

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void setCertified_at(Date certified_at) {
        this.certified_at = certified_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public void setGrader_id(int grader_id) {
        this.grader_id = grader_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setProject_visible(Boolean project_visible) {
        this.project_visible = project_visible;
    }

    public void setProject_audit_project_id(int project_audit_project_id) {
        this.project_audit_project_id = project_audit_project_id;
    }

    public void setProject_awaiting_review_count(int project_awaiting_review_count) {
        this.project_awaiting_review_count = project_awaiting_review_count;
    }

    public void setProject_created_at(Date project_created_at) {
        this.project_created_at = project_created_at;
    }

    public void setProject_description(String project_description) {
        this.project_description = project_description;
    }

    public void setProject_hashtag(String project_hashtag) {
        this.project_hashtag = project_hashtag;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public void setProject_languages_to_recruit(String project_languages_to_recruit) {
        this.project_languages_to_recruit = project_languages_to_recruit;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public void setProject_nanodegree_key(String project_nanodegree_key) {
        this.project_nanodegree_key = project_nanodegree_key;
    }

    public void setProject_nomination_eligible(Boolean project_nomination_eligible) {
        this.project_nomination_eligible = project_nomination_eligible;
    }

    public void setProject_price(double project_price) {
        this.project_price = project_price;
    }

    public void setProject_required_skills(String project_required_skills) {
        this.project_required_skills = project_required_skills;
    }

    public void setProject_udacity_key(int project_udacity_key) {
        this.project_udacity_key = project_udacity_key;
    }

    public void setProject_updated_at(Date project_updated_at) {
        this.project_updated_at = project_updated_at;
    }

    public void setProject_waitlist(Boolean project_waitlist) {
        this.project_waitlist = project_waitlist;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTrainings_count(int trainings_count) {
        this.trainings_count = trainings_count;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public void setWaitlisted_at(Date waitlisted_at) {
        this.waitlisted_at = waitlisted_at;
    }

    public String getProject_name() {
        return project_name;
    }

    public double getProject_price() {
        return project_price;
    }

    public String getStatus() {
        return status;
    }
   
         
}
