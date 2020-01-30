package com.cm.todo_list;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.EditText;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Random;

public class PrimaryTask implements Parcelable {
    String title;
    String description;
    boolean complete;
    SubTask sub;
    ArrayList <SubTask>subTask;
    String key;
    public PrimaryTask(){
        title = "Title";
        description = "Description";
        complete = false;
        subTask = new ArrayList();
        sub = new SubTask();
        key = genKey();
    }

    protected PrimaryTask(Parcel in) {
        title = in.readString();
        description = in.readString();
        complete = in.readByte() != 0;
        sub = in.readParcelable(SubTask.class.getClassLoader());
        subTask = in.createTypedArrayList(SubTask.CREATOR);
        key = in.readString();
    }

    public static final Creator<PrimaryTask> CREATOR = new Creator<PrimaryTask>() {
        @Override
        public PrimaryTask createFromParcel(Parcel in) {
            return new PrimaryTask(in);
        }

        @Override
        public PrimaryTask[] newArray(int size) {
            return new PrimaryTask[size];
        }
    };

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

    private String genKey(){
        byte[] array = new byte[7];
        new Random().nextBytes(array);
        String out = new String(array, Charset.forName("UTF-8"));
        return out;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(description);
        dest.writeByte((byte) (complete ? 1 : 0));
        dest.writeParcelable(sub, flags);
        dest.writeTypedList(subTask);
        dest.writeString(key);
    }
}
