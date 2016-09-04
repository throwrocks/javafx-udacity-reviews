/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import util.Utilities;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author josel
 */
public class JSONParser {

    public static final String UTIL_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    public static ArrayList<Certification> parseCertifications(String reviewsJSON) {
        ArrayList<Certification> contentValues = new ArrayList<>();
        if (reviewsJSON != null) {
            try {
                JSONArray certificationsArray = new JSONArray(reviewsJSON);
                int certificationsQty = certificationsArray.length();
                for (int i = 0; i < certificationsQty; i++) {
                    JSONObject certificationRecord = certificationsArray.getJSONObject(i);
                    Certification certification = new Certification();
                    try {
                        int id = certificationRecord.optInt("id");
                        String status = certificationRecord.optString("status");
                        int project_id = certificationRecord.optInt("project_id");
                        int grader_id = certificationRecord.optInt("grader_id");
                        int trainings_count = certificationRecord.optInt("trainings_count");
                        // Get date strings and convert them to date objects
                        String created_at = certificationRecord.optString("created_at");
                        String updated_at = certificationRecord.optString("updated_at");
                        String waitlisted_at = certificationRecord.optString("waitlisted_at");
                        String certified_at = certificationRecord.optString("certified_at");
                        Date created_at_date;
                        Date updated_at_date;
                        Date waitlisted_at_date;
                        Date certified_at_date;
                        created_at_date = Utilities.getStringAsDate(created_at, UTIL_DATE_FORMAT, null);
                        updated_at_date = Utilities.getStringAsDate(updated_at, UTIL_DATE_FORMAT, null);
                        waitlisted_at_date = Utilities.getStringAsDate(waitlisted_at, UTIL_DATE_FORMAT, null);
                        certified_at_date = Utilities.getStringAsDate(certified_at, UTIL_DATE_FORMAT, null);
                        Boolean active = certificationRecord.optBoolean("active");
                        String notes = certificationRecord.optString("notes");
                        certification.setId(id);
                        certification.setStatus(status);
                        certification.setProject_id(project_id);
                        certification.setGrader_id(grader_id);
                        certification.setTrainings_count(trainings_count);
                        certification.setCreated_at(created_at_date);
                        certification.setUpdated_at(updated_at_date);
                        certification.setWaitlisted_at(waitlisted_at_date);
                        certification.setCertified_at(certified_at_date);
                        certification.setActive(active);
                        certification.setNotes(notes);
                        //----------------------------------------------------------------------------------
                        // Project Node
                        //----------------------------------------------------------------------------------
                        JSONObject projectNode = certificationRecord.getJSONObject("project");
                        String project_name = projectNode.optString("name");
                        double project_price = projectNode.getDouble("price");
                        int project_udacity_key = projectNode.optInt("udacity_key");
                        String project_created_at = projectNode.optString("created_at");
                        String project_updated_at = projectNode.optString("updated_at");
                        Date project_created_at_date = Utilities.getStringAsDate(project_created_at, UTIL_DATE_FORMAT, null);
                        Date project_updated_at_date = Utilities.getStringAsDate(project_updated_at, UTIL_DATE_FORMAT, null);
                        String project_description = projectNode.optString("description");
                        String project_required_skills = projectNode.optString("required_skills");
                        Boolean project_visible = projectNode.getBoolean("visible");
                        int project_awaiting_review_count = projectNode.optInt("awaiting_review_count");
                        Boolean project_wailist = projectNode.getBoolean("waitlist");
                        String project_nanodegree_key = projectNode.optString("nanodegree_key");
                        int project_audit_project_id = projectNode.optInt("audit_project_id");
                        Boolean project_nomination_eligible = projectNode.getBoolean("nomination_eligible");
                        String project_hashtag = projectNode.optString("hashtag");
                        certification.setProject_name(project_name);
                        certification.setProject_price(project_price);
                        certification.setProject_udacity_key(project_udacity_key);
                        certification.setProject_created_at(project_created_at_date);
                        certification.setProject_updated_at(project_updated_at_date);
                        certification.setProject_description(project_description);
                        certification.setProject_required_skills(project_required_skills);
                        certification.setProject_visible(project_visible);
                        certification.setProject_awaiting_review_count(project_awaiting_review_count);
                        certification.setProject_waitlist(project_wailist);
                        certification.setProject_nanodegree_key(project_nanodegree_key);
                        certification.setProject_audit_project_id(project_audit_project_id);
                        certification.setProject_nomination_eligible(project_nomination_eligible);
                        certification.setProject_hashtag(project_hashtag);
                        //----------------------------------------------------------------------------------
                        // Add the ContentValues to the Array
                        //----------------------------------------------------------------------------------
                        contentValues.add(certification);
                    } catch (JSONException e) {
                        System.out.println("JSONException: " + e);
                    }
                }
            } catch (JSONException e) {
                System.out.println(e);
            }
        }
        return contentValues;
    }
}
