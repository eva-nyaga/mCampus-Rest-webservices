/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.springframework.security.oauth.api.service.healthcare.clients;

import java.util.List;
import org.json.JSONArray;
import org.springframework.security.oauth.api.model.healthcare.clients.AdminProgress;
import org.springframework.security.oauth.api.model.healthcare.clients.CampusList;
import org.springframework.security.oauth.api.model.healthcare.clients.ChangeCourse;
import org.springframework.security.oauth.api.model.healthcare.clients.CompletedUnits;
import org.springframework.security.oauth.api.model.healthcare.clients.CourseworkMarks;
import org.springframework.security.oauth.api.model.healthcare.clients.CourseworkTimetable;
import org.springframework.security.oauth.api.model.healthcare.clients.DefermentRecords;
import org.springframework.security.oauth.api.model.healthcare.clients.ExamCard;
import org.springframework.security.oauth.api.model.healthcare.clients.FeeStatement;
import org.springframework.security.oauth.api.model.healthcare.clients.FeeStructure;
import org.springframework.security.oauth.api.model.healthcare.clients.PendingUnits;
import org.springframework.security.oauth.api.model.healthcare.clients.Reply;
import org.springframework.security.oauth.api.model.healthcare.clients.RetakesModel;
import org.springframework.security.oauth.api.model.healthcare.clients.UndoneUnits;
import org.springframework.security.oauth.api.model.healthcare.clients.UnitsModel;
import org.springframework.security.oauth.api.model.healthcare.clients.UnitsProgramme;

/**
 *
 * @author Eva
 */
public interface IStudentsService {
    public List<UnitsModel> getUnits(String semester);
    public List<Reply> saveUnitsRegistration(String stdId, String programme, String stage, String unit);
    public List<UnitsProgramme>  getUnitsProgrammes(String stagecode, String desc);
    public List<CourseworkTimetable>  getCourseworkTimetable(String day, String stage, String programme);
    public List<CourseworkTimetable>  getExamTimetable(String day, String stage, String programme);
    public List<CourseworkMarks>  getCourseworkMarks(String stdId, String resit);
    public JSONArray fetchCmarks(String stdId, String resit);
    public List<CompletedUnits>  getCompletedUnits(String stdId, String resit);
    public List<PendingUnits>  getRetakeUnits(String stdId, String programme);
    public List<UndoneUnits>  getUndoneUnits(String stdId, String programme);
    public List<ExamCard>  getExamCard(String stdId, String stage, String programme);
    public List<FeeStructure>  getFeeStructure(String programme, String stage);
    public List<FeeStatement>  getFeeStatement(String stdId);
    public List<ChangeCourse>  getCourses(String programme);
    public List<Reply> saveAdminRequest(String stdId, String requisition_type, String programme_desc, String stage, String deferment_period,String start_date);
    public List<AdminProgress> getAdminProgress(String stdId, String requisition_type);
    public List<CampusList> getCampuses();
    public List<DefermentRecords> getDefermentRecords(String stdId);
    public List<Reply> extendDeferment(String stdId, String period);
    public List<RetakesModel> getRetakeCount(String stdId);
    public List<Reply> changePassword(String stdId, String newpassword);
}
