package com.cms.backend.SummaryModel;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseSummaryModel {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date timestamp;
    private int code;
    private Boolean success;
    private String message;
    private Object data;

    public void setAll(int code,Boolean success, String message,Object data){
        this.timestamp = new Date();
        this.code = code;
        this.success = success;
        this.message = message;
        this.data = data;
    }

}
