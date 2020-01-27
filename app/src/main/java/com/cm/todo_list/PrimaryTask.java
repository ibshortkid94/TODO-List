package com.cm.todo_list;

import java.util.ArrayList;

public class PrimaryTask {
    String title;
    String description;
    boolean complete;
    SubTask sub;
    ArrayList <SubTask>subTask;
    public PrimaryTask(){
        title = "";
        description = "";
        complete = false;
        subTask = new ArrayList();
        sub = new SubTask();
    }
    public void setTitle(String t){
        title = t;
    }
    public void setDescription(String d){
        description = d;
    }
    public void addSub(String title){
        subTask.add(new SubTask());
    }
    public void setComplete(Boolean c){
        complete = c;
    }
    public void setSubComplete(int index){
        subTask.get(index).setCompleted(true);
    }
}
