package com.it.reservation.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Constants {

    public static final String INITIALS_NAME_PROJECT = "BSTR";

    // messageKey
    public static final String MESSAGE_KEY_ENG = "successfully!!";
    public static final String MESSAGE_KEY_TH = "ทำรายการสำเร็จ!!";

    public static final String MESSAGE_KEYS_SAVE_ENG = "Saved. successfully!!";
    public static final String MESSAGE_KEYS_SAVE_TH = "บันทึกข้อมูลสำเร็จ!!";

    public static final String STATUS_NORMAL = "1";
    
    public static final String STATUS_CODE_SUCESS = "00";
    public static final String STATUS_CODE_UNSUCESS = "99";
    
    public final class USER {

        public static final String CUSTOMER_PREFIX = "REV-CST-";
        public static final String EMPLOYEE_PREFIX = "REV-EMP-";

        public static final Long ROLE_ADMIN = 1L;
        public static final Long ROLE_EMPLOYEE = 2L;
        public static final Long ROLE_CUSTOMER = 3L;

    }
    
    public final class RESERVATION {

        public static final List<String> REV_NO_ALL = new ArrayList<>(Arrays.asList("A","B","C","D","E","F"));
        public static final Integer REV_MAXIMUM = 999;
        public static final Integer REV_CANCEL_MAXIMUM = 3;
        public static final String REV_STATUS_WAITING = "1";  
        public static final String REV_STATUS_DONE = "2";
        public static final String REV_STATUS_USER_CANCEL = "3";
        public static final String REV_STATUS_ADMIN_CANCEL = "4";
        

    }
    
    public final class REPORT {
        public static final String TYPE_STATUS_ALL = "ALL";
        public static final String TYPE_STATUS_SUCCESS = "SUCCESS";
        public static final String TYPE_STATUS_CANCEL = "CANCEL";
        public static final String PATH_FOLDER_REPORT_EXCEL = "report/excel/template";
    }
}
