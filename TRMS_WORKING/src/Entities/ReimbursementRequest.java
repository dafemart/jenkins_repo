package Entities;

import java.sql.Date;

public class ReimbursementRequest {
     private int RequestID;
     private int EmployeeID;
     private int EventID;
     private Date StartingDate;
     private String CourseLocation;
     private int CourseCost;
     private String Justification;
     private String CurrentIssuer;
     private int Status;
     
     private static final int UniversityCoursesEventID = 1;
     private static final int SeminarsEventID = 2;
     private static final int CertificationID = 4;
     private static final int TechnicalTrainingID = 5;
     private static final int OtherID = 6;
     private static final int CertPrepID = 3;
     
     private static final int ONHOLD = 0;
     private static final int APPROVED = 1;
     private static final int DENNIED = 2;
     
     private static final String BENCO = "BENCO";
     private static final String DEPARTMENTHEAD = "DEPARTMENTHEAD";
     private static final String DIRECTMANAGER = "DIRECTMANAGER";
     private static final String DIRECTSUPERVISOR = "DIRECTSUPERVISOR";
     private static final String EMPLOYEE = "EMPLOYEE";
     
     
     public ReimbursementRequest(
    		 int RequestID,
    		 int EmployeeID,
    		 int EventID,
    		 Date StartingDate,
    		 String CourseLocation,
    		 int CourseCost,
    		 String Justification,
    		 String CurrentIssuer,
    		 int Status){
    	
    	 this.RequestID = RequestID;
    	 this.EmployeeID = EmployeeID;
    	 this.EventID = EventID;
    	 this.StartingDate = StartingDate;
    	 this.CourseLocation = CourseLocation;
    	 this.CourseCost = CourseCost;
    	 this.Justification = Justification;
    	 this.CurrentIssuer = CurrentIssuer;
    	 this.Status = Status;
     }
     
     public int getRequestID(){
    	 return this.RequestID;
     }
     
     public int getEmployeeID(){
    	 return this.EmployeeID;
     }
     
     public int getEventID(){
    	 return this.EventID;
     }
     
     public Date getStartingDate(){
    	 return this.StartingDate;
     }
     
     public String getCourseLocation(){
    	 return this.CourseLocation;
     }
     
     public int getCourseCost(){
    	 return this.CourseCost;
     }
     
     public String getJustification(){
    	 return this.Justification;
     }
     
     public String getCurrentIssuer(){
    	 return this.CurrentIssuer;
     }
     
     public void setCurrentIssuer(String issuer){
    	 this.CurrentIssuer = issuer;
     }
     
     public int getStatus(){
    	 return this.Status;
     }
     
     public void setStatus(int status){
    	 this.Status = status;
     }
     
     public static int getUniversityCoursesEventID(){
    	 return UniversityCoursesEventID;
     }
     
     public static int getSeminarsEventID(){
    	 return SeminarsEventID;
     }
     
     public static int getCertificationID(){
    	 return CertificationID;
     }
     
     public static int getTechnicalTrainingID(){
    	 return TechnicalTrainingID;
     }
     
     public static int getOtherID(){
    	 return OtherID;
     }
     
     public static int getCertPrepID(){
    	 return CertPrepID;
     }
     
     public static int getOnHoldValue(){
    	 return ONHOLD;
     }
     
     public static int getApprovedValue(){
    	 return APPROVED;
     }
     
     public static int getDenniedValue(){
    	 return DENNIED;
     }
     
     public static String getBencoValue(){
    	 return BENCO;
     }
     
     public static String getDepartmentHeadValue(){
    	 return DEPARTMENTHEAD;
     }
     
     public static String getDirectManagerValue(){
    	 return DIRECTMANAGER;
     }
     
     public static String getDirectSupervisorValue(){
    	 return DIRECTSUPERVISOR;
     }
     
     public static String getEmployeeValue(){
    	 return EMPLOYEE;
     }
}
