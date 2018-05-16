package com.antonioleiva.mvpexample.app.bean;

public class Rule {
    public String getRule_Name() {
        return Rule_Name;
    }

    public String getRule_Main() {
        return Rule_Main;
    }

    public void setRule_Name(String rule_Name) {
        Rule_Name = rule_Name;
    }

    public void setRule_Main(String rule_Main) {
        Rule_Main = rule_Main;
    }

    private String Rule_Name;
    private String Rule_Main;

    public int getRule_Id() {
        return Rule_Id;
    }

    public void setRule_Id(int rule_Id) {
        Rule_Id = rule_Id;
    }

    private int Rule_Id;

}
