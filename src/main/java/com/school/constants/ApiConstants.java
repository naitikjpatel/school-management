package com.school.constants;

public class ApiConstants {

    // Base API Path
    public static final String BASE_API = "/api";

    // User Endpoints
    public static final String USERS = BASE_API + "/users";
    public static final String ADD_USER = "/addUser";
    public static final String GET_ALL_USERS = "/getAllUser";
    public static final String UPDATE_USER = "/updateUser";
    public static final String USER_BY_ID = "/{userId}";
    public static final String DELETE_USER_BY_ID = "/{userId}";
    public static final String UPDATE_USER_BY_ID = "/{userId}";
    public static final String ADD_USER_COURSE_ID = "/addUser/{courseId}";
    public static final String MAIL_SEND="mail/{userId}/{email}";
    // Exam Endpoints
    public static final String EXAM = BASE_API + "/exam";
    public static final String ADD_EXAM = "/addExam";
    public static final String GET_ALL_EXAMS = "/getAllExam";
    public static final String EXAM_BY_ID = "/{examId}";
    public static final String DELETE_EXAM_BY_ID = "/{examId}";

    // Course Endpoints
    public static final String COURSE = BASE_API + "/course";
    public static final String ADD_COURSE = "/addCourse";
    public static final String GET_ALL_COURSES = "/getAllCourse";
    public static final String UPDATE_COURSE = "/updateCourse";
    public static final String COURSE_BY_ID = "/{courseId}";
    public static final String DELETE_COURSE_BY_ID = "/{courseId}";

    // Subject Endpoints
    public static final String SUBJECT = BASE_API + "/subject";
    public static final String ADD_SUBJECT = "/addSubject/{courseId}";
    public static final String GET_ALL_SUBJECTS = "/getAllSubject";
    public static final String SUBJECT_BY_ID = "/{subjectId}";
    public static final String DELETE_SUBJECT_BY_ID = "/{subjectId}";
    // User Details Endpoints
    public static final String USER_DETAILS = BASE_API + "/usersDetails";
    public static final String ADD_USER_DETAILS = "/addUserDetails";
    public static final String GET_ALL_USER_DETAILS = "/getAllUserDetails";
    public static final String UPDATE_USER_DETAILS = "/updateUserDetails";

    // User Type Endpoints
    public static final String USER_TYPE = BASE_API + "/userType";
    public static final String ADD_USER_TYPE = "/addUserType";
    public static final String GET_ALL_USER_TYPES = "/getAllUserType";
    public static final String UPDATE_USER_TYPE = "/updateUserType";
    public static final String USER_TYPE_BY_ID = "/{id}";
    public static final String DELETE_USER_TYPE_BY_ID = "/{id}";

    // Exam Type Endpoints
    public static final String EXAM_TYPE = BASE_API + "/examType";
    public static final String ADD_EXAM_TYPE = "/addExamType";
    public static final String GET_ALL_EXAM_TYPES = "/getAllExamType";
    public static final String UPDATE_EXAM_TYPE = "/updateExamType";
    public static final String EXAM_TYPE_BY_ID = "/{examTypeId}";
    public static final String DELETE_EXAM_TYPE_BY_ID = "/{examTypeId}";

    // Result Endpoints
    public static final String RESULT = BASE_API + "/result";
    public static final String ADD_RESULT = "/addResult";
    public static final String GET_ALL_RESULTS = "/getAllResult";
    public static final String RESULT_BY_ID = "/{resultId}";
    public static final String DELETE_RESULT_BY_ID = "/{resultId}";
    public static final String RESULT_BY_STUDENT_ID="/student/{userId}";
    public static final String RESULT_BY_EXAM_ID="/teacher/{examId}";
}
