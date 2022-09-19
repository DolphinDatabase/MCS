package com.cms.backend.util;

public enum Nivel {
    EASY ("Fácil"),
    MEDIUM ("Médio"),
    HARD ("Difícil");

    private String label;

    Nivel(String label){
        this.label = label;
    }

    public String getLabel(){
        return this.label;
    }
}
