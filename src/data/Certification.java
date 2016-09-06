/*
 * The MIT License
 *
 * Copyright 2016 Jose Lopez.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package data;

import java.util.Date;

/**
 * Certification A class to manage the Certification objects Created from the
 * Udacity API /me/certification results
 *
 * @author Jose Lopez
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
    Boolean selected;

    public Certification() {
    }

    ;

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

    public void setSelected(Boolean selected) {
        this.selected = selected;
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

    public Boolean getActive() {
        return active;
    }

    public Boolean getSelected() {
        if (this.selected == null && this.status.equals("certified")) {
            this.setSelected(true);
        }
        return selected;
    }

    public int getProject_id() {
        return project_id;
    }
  
}
