package com.cms.backend.util;

public enum Status {
    OPEN ("Iniciado"),
    IN_PROGRESS ("Em progresso"),
    FINISHED ("Finalizado");

    private String label;

    Status(String label){
        this.label = label;
    }

    public String getLabel(){
        return this.label;
    }

}
