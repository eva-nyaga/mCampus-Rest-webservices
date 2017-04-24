/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.springframework.security.oauth.api.controller.healthcare.clients;

import java.util.List;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth.api.model.healthcare.clients.AdminProgress;
import org.springframework.security.oauth.api.model.healthcare.clients.CampusList;
import org.springframework.security.oauth.api.model.healthcare.clients.ChangeCourse;
import org.springframework.security.oauth.api.model.healthcare.clients.CompletedUnits;
import org.springframework.security.oauth.api.model.healthcare.clients.PendingUnits;
import org.springframework.security.oauth.api.model.healthcare.clients.CourseworkTimetable;
import org.springframework.security.oauth.api.model.healthcare.clients.DefermentRecords;
import org.springframework.security.oauth.api.model.healthcare.clients.ExamCard;
import org.springframework.security.oauth.api.model.healthcare.clients.FeeStructure;
import org.springframework.security.oauth.api.model.healthcare.clients.FeeStatement;
import org.springframework.security.oauth.api.utils.SpringMVCUtils;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.oauth.api.model.healthcare.clients.Reply;
import org.springframework.security.oauth.api.model.healthcare.clients.RetakesModel;
import org.springframework.security.oauth.api.model.healthcare.clients.UndoneUnits;
import org.springframework.security.oauth.api.model.healthcare.clients.UnitsModel;
import org.springframework.security.oauth.api.model.healthcare.clients.UnitsProgramme;
import org.springframework.security.oauth.api.service.healthcare.clients.IStudentsService;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
/**
 * This class has the logic for acting on web requests.
 * 
 * @author Eva
 *
 */
public class StudentsController {

	@Autowired
	private IStudentsService studentsService;

        @RequestMapping(value = "/units/unitlist", method = RequestMethod.GET)
	public ModelAndView unitList(
			
			@RequestParam(value = "semester") String semester
			
                      
			) {
            List<UnitsModel>  p = studentsService.getUnits(semester);
                 
                return SpringMVCUtils.getOutputModel(p);
	}
        
         @RequestMapping(value = "/units/unitprogrammes", method = RequestMethod.GET)
	public ModelAndView unitProgrammes(
			
			@RequestParam(value = "stagecode") String stagecode,
                        @RequestParam(value = "desc") String desc
			
                      
			) {
            List<UnitsProgramme>  p = studentsService.getUnitsProgrammes(stagecode, desc);
                 
                return SpringMVCUtils.getOutputModel(p);
	}
        
        
       
        @RequestMapping(value = "/units/register", method = RequestMethod.GET)
        public ModelAndView unitRegistration(
			
			@RequestParam(value = "stdId") String stdId,
			@RequestParam(value = "programme") String programme,
                        @RequestParam(value = "stage") String stage,
                        @RequestParam(value = "unit") String unit
                       
			) {

           
		
		List<Reply>  p = studentsService.saveUnitsRegistration(stdId, programme, stage, unit);
                 
                return SpringMVCUtils.getOutputModel(p);
	}
        
         @RequestMapping(value = "/timetables/ctimetable", method = RequestMethod.GET)
        public ModelAndView getTimetable(
			
			@RequestParam(value = "day") String day,
                        @RequestParam(value = "stage") String stage,
                        @RequestParam(value = "programme") String programme
                       
			) {

           
		
		List<CourseworkTimetable>  p = studentsService.getCourseworkTimetable(day, stage,programme);
                 
                return SpringMVCUtils.getOutputModel(p);
	}
        
         @RequestMapping(value = "/timetables/etimetable", method = RequestMethod.GET)
        public ModelAndView getExamTimetable(
			
			@RequestParam(value = "day") String day,
                        @RequestParam(value = "stage") String stage,
                        @RequestParam(value = "programme") String programme
                       
			) {

           
		
		List<CourseworkTimetable>  p = studentsService.getExamTimetable(day, stage,programme);
                 
                return SpringMVCUtils.getOutputModel(p);
	}
        
          @RequestMapping(value = "/coursework/marks", method = RequestMethod.GET ,produces = "application/json")
        public @ResponseBody String getMarks(
			
			@RequestParam(value = "stdId") String stdId,
                        @RequestParam(value = "resit") String resit
                       
			) {

                return studentsService.fetchCmarks(stdId, resit).toString();
	}
        
        @RequestMapping(value = "/units/completed", method = RequestMethod.GET)
        public ModelAndView getCompletedUnits(
			
			@RequestParam(value = "stdId") String stdId,
                        @RequestParam(value = "resit") String resit
                       
			) {

           
		
		List<CompletedUnits>  p = studentsService.getCompletedUnits(stdId, resit);
                 
                return SpringMVCUtils.getOutputModel(p);
	}
        
        @RequestMapping(value = "/units/retake", method = RequestMethod.GET)
        public ModelAndView getPendingUnits(
			
			@RequestParam(value = "stdId") String stdId,
                        @RequestParam(value = "programme") String programme
                       
			) {

           
		
		List<PendingUnits>  p = studentsService.getRetakeUnits(stdId, programme);
                 
                return SpringMVCUtils.getOutputModel(p);
	}
        
        @RequestMapping(value = "/units/undone", method = RequestMethod.GET)
        public ModelAndView getUndoneUnits(
			
			@RequestParam(value = "stdId") String stdId,
                        @RequestParam(value = "programme") String programme
                       
			) {

           
		
		List<UndoneUnits>  p = studentsService.getUndoneUnits(stdId, programme);
                 
                return SpringMVCUtils.getOutputModel(p);
	}
        
         @RequestMapping(value = "/timetables/examcard", method = RequestMethod.GET)
        public ModelAndView getExamCard(
			
			@RequestParam(value = "stdId") String stdId,
                        @RequestParam(value = "stage") String stage,
                        @RequestParam(value = "programme") String programme
                       
			) {

           
		
		List<ExamCard>  p = studentsService.getExamCard(stdId,stage,programme);
                 
                return SpringMVCUtils.getOutputModel(p);
	}
        
         @RequestMapping(value = "/accounts/feestructure", method = RequestMethod.GET)
        public ModelAndView getFeeStructure(
			
                        @RequestParam(value = "programme") String programme,
                        @RequestParam(value = "stage") String stage
                       
			) {

           
		
		List<FeeStructure>  p = studentsService.getFeeStructure(programme,stage);
                 
                return SpringMVCUtils.getOutputModel(p);
	}
        
         @RequestMapping(value = "/accounts/feestatement", method = RequestMethod.GET)
        public ModelAndView getFeeStatement(
			
                        @RequestParam(value = "stdId") String stdId
                       
			) {

           
		
		List<FeeStatement>  p = studentsService.getFeeStatement(stdId);
                 
                return SpringMVCUtils.getOutputModel(p);
	}
        
         @RequestMapping(value = "/admin/fetch/changecourse", method = RequestMethod.GET)
        public ModelAndView getCourses(
			
                        @RequestParam(value = "programme") String programme
                       
			) {

           
		
		List<ChangeCourse>  p = studentsService.getCourses(programme);
                 
                return SpringMVCUtils.getOutputModel(p);
	}
        
           @RequestMapping(value = "/admin/submitrequest", method = RequestMethod.GET)
        public ModelAndView submitRequest(
			
			@RequestParam(value = "stdId") String stdId,
			@RequestParam(value = "requisition_type") String requisition_type,
                        @RequestParam(value = "programme_desc", defaultValue = "0") String programme_desc,
                        @RequestParam(value = "stage") String stage,
                        @RequestParam(value = "deferment_period", defaultValue = "0") String deferment_period,
                        @RequestParam(value = "start_date", defaultValue = "0") String start_date
                       
			) {

           
		
		List<Reply>  p = studentsService.saveAdminRequest(stdId,requisition_type,programme_desc,stage,deferment_period,start_date);
                 
                return SpringMVCUtils.getOutputModel(p);
	}
        
        @RequestMapping(value = "/admin/defermentrecords", method = RequestMethod.GET)
        public ModelAndView getDefermentRecords(
			
			@RequestParam(value = "stdId") String stdId
			    
			) {

           
		
		List<DefermentRecords>  p = studentsService.getDefermentRecords(stdId);
                 
                return SpringMVCUtils.getOutputModel(p);
	}
        
         @RequestMapping(value = "/admin/extenddeferment", method = RequestMethod.GET)
        public ModelAndView extendDeferment(
			
			@RequestParam(value = "stdId") String stdId,
                        @RequestParam(value = "period") String period
			    
			) {

           
		
		List<Reply>  p = studentsService.extendDeferment(stdId,period);
                 
                return SpringMVCUtils.getOutputModel(p);
	}
        
         @RequestMapping(value = "/admin/progress", method = RequestMethod.GET)
        public ModelAndView CheckProgress(
			
			@RequestParam(value = "stdId") String stdId,
			@RequestParam(value = "requisition_type") String requisition_type
                      
                       
			) {

           
		
		List<AdminProgress>  p = studentsService.getAdminProgress(stdId,requisition_type);
                 
                return SpringMVCUtils.getOutputModel(p);
	}
        
         @RequestMapping(value = "/admin/campuses", method = RequestMethod.GET)
        public ModelAndView getCampus(
			
			
			) {

           
		List<CampusList>  p = studentsService.getCampuses();
                 
                return SpringMVCUtils.getOutputModel(p);
	}
        
         @RequestMapping(value = "/units/retakecount", method = RequestMethod.GET)
        public ModelAndView getRetakeCount(
			
			@RequestParam(value = "stdId") String stdId
			) {

           
		List<RetakesModel>  p = studentsService.getRetakeCount(stdId);
                 
                return SpringMVCUtils.getOutputModel(p);
	}
        
         @RequestMapping(value = "/changepassword", method = RequestMethod.GET)
        public ModelAndView changePassword(
			
			@RequestParam(value = "stdId") String stdId,
                        @RequestParam(value = "newpassword") String newpassword
			) {

           
		List<Reply>  p = studentsService.changePassword(stdId,newpassword);
                 
                return SpringMVCUtils.getOutputModel(p);
	}
        
}
