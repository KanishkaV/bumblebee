package com.bumble.bee.app.models.error;

public class ErrorCodes {

    public static final String PARSE_ERROR = "400000";
    public static final String MISSING_MANDATORY = "400001";
    public static final String NOT_VALID_ITERATOR = "400002";
    public static final String NOT_VALID_INPUT_TYPE = "400003";
    public static final String NOT_VALID_OUTPUT_TYPE = "400004";
    public static final String NOT_VALID_MONITORING_SCOPE_DELETE_REQUEST = "400005";
    public static final String NOT_VALID_ENABLED_PARAM = "400006";
    public static final String SINGLE_SUBJECT_ERROR = "400007";
    public static final String UNAUTHORIZED = "401000";
    public static final String INVALID_TOKEN = "401002";
    public static final String TOKEN_EXPIRED = "401003";
    public static final String FORBIDDEN = "403000";
    public static final String NOT_ALLOWED_DATA_CLASSES = "403002";
    public static final String NOT_FOUND = "404000";
    public static final String METHOD_NOT_ALLOWED = "405000";
    public static final String SETTINGS_NOT_FOUND = "404003";
    public static final String WRONG_CONTENT_TYPE = "412001";
    public static final String INPUT_FILE_TOO_LONG = "413001";
    public static final String MONITORING_SET_DISABLED = "422001";
    public static final String REJECTED_IMPORT_PROCESS = "429001";
    public static final String SINGLE_SUBJECT_STALLED_ERROR = "429003";
    public static final String REJECTED_PARALLEL_IMPORT_PROCESS = "429002";
    public static final String MONITORING_SET_WITHOUT_DATA_CLASSES = "422002";
    public static final String INTERNAL_SERVER_ERROR = "500000";
    public static final String SERVICE_UNAVAILABLE = "503000";
}
