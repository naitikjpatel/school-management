package com.school.constants;
public class ApiConstants {

    // Base API Path
    public static final String BASE_API = "/api";

    // User Endpoints
    public static final String USERS = BASE_API + "/users";
    public static final String ADD_USER = USERS + "/addUser";
    public static final String GET_ALL_USERS = USERS + "/getAllUser";
    public static final String UPDATE_USER = USERS + "/updateUser";
    public static final String USER_BY_ID = USERS + "/{userId}";

    // Exam Endpoints
    public static final String EXAM = BASE_API + "/exam";
    public static final String ADD_EXAM = EXAM + "/addExam";
    public static final String GET_ALL_EXAMS = EXAM + "/getAllExam";
    public static final String EXAM_BY_ID = EXAM + "/{examId}";

    // Course Endpoints
    public static final String COURSE = BASE_API + "/course";
    public static final String ADD_COURSE = COURSE + "/addCourse";
    public static final String GET_ALL_COURSES = COURSE + "/getAllCourse";
    public static final String UPDATE_COURSE = COURSE + "/updateCourse";
    public static final String COURSE_BY_ID = COURSE + "/{courseId}";

    // Subject Endpoints
    public static final String SUBJECT = BASE_API + "/subject";
    public static final String ADD_SUBJECT = SUBJECT + "/addSubject";
    public static final String GET_ALL_SUBJECTS = SUBJECT + "/getAllSubject";
    public static final String SUBJECT_BY_ID = SUBJECT + "/{subjectId}";

    // User Details Endpoints
    public static final String USER_DETAILS = BASE_API + "/usersDetails";
    public static final String ADD_USER_DETAILS = USER_DETAILS + "/addUserDetails";
    public static final String GET_ALL_USER_DETAILS = USER_DETAILS + "/getAllUserDetails";
    public static final String UPDATE_USER_DETAILS = USER_DETAILS + "/updateUserDetails";

    // User Type Endpoints
    public static final String USER_TYPE = BASE_API + "/userType";
    public static final String ADD_USER_TYPE = USER_TYPE + "/addUserType";
    public static final String GET_ALL_USER_TYPES = USER_TYPE + "/getAllUserType";
    public static final String UPDATE_USER_TYPE = USER_TYPE + "/updateUserType";
    public static final String USER_TYPE_BY_ID = USER_TYPE + "/{id}";

    // Exam Type Endpoints
    public static final String EXAM_TYPE = BASE_API + "/examType";
    public static final String ADD_EXAM_TYPE = EXAM_TYPE + "/addExamType";
    public static final String GET_ALL_EXAM_TYPES = EXAM_TYPE + "/getAllExamType";
    public static final String UPDATE_EXAM_TYPE = EXAM_TYPE + "/updateExamType";
    public static final String EXAM_TYPE_BY_ID = EXAM_TYPE + "/{examTypeId}";

    // Result Endpoints
    public static final String RESULT = BASE_API + "/result";
    public static final String ADD_RESULT = RESULT + "/addResult";
    public static final String GET_ALL_RESULTS = RESULT + "/getAllResult";
    public static final String RESULT_BY_ID = RESULT + "/{resultId}";
}
