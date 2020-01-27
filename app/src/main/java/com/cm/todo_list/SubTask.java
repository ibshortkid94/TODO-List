package com.cm.todo_list;
public class SubTask {
    String title;
    Boolean completed;
    public SubTask(){
        title = "";
        completed = false;
    }
    public void setTitle(String t){
        title = t;
    }
    public void setCompleted(boolean c){
        completed = c;
    }
    public String getTitle(){
        return title;
    }
    public boolean getCompleted(){
        return completed;
    }
}
