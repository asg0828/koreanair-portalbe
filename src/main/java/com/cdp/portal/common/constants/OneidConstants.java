package com.cdp.portal.common.constants;

public enum OneidConstants {
    //성공
    SUCCESS("success", 20000, "정상 처리 되었습니다."),
    INSERT_SUCCESS("success", 20001, "등록이 완료되었습니다."),
    UPDATE_SUCCESS("success", 20002, "수정이 완료되었습니다."),
    DELETE_SUCCESS("success", 20003, "삭제가 완료되었습니다."),
    //실패
    FAILURE("failure", 50000, "처리 중 오류가 발생하였습니다."),
    INSERT_FAILURE("failure", 50001, "등록 중 오류가 발생하였습니다."),
    UPDATE_FAILURE("failure", 50002, "수정 중 오류가 발생하였습니다."),
    DELETE_FAILURE("failure", 50003, "삭제 중 오류가 발생하였습니다.");

    String result;
    int code;
    String message;

    private OneidConstants(String result, int code, String message) {
        this.result = result;
        this.code = code;
        this.message = message;
    }

    public String getResult() {
        return result;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
