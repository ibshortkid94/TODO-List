package com.cm.todo_list;

import android.os.Parcel;
import android.os.Parcelable;

import java.nio.charset.Charset;
import java.util.Random;

public class SubTask implements Parcelable {
    String title;
    Boolean completed;
    String key;
    public SubTask(){
        title = "";
        completed = false;
        key = genKey();
    }

    protected SubTask(Parcel in) {
        title = in.readString();
        byte tmpCompleted = in.readByte();
        completed = tmpCompleted == 0 ? null : tmpCompleted == 1;
        key = in.readString();
    }

    public static final Creator<SubTask> CREATOR = new Creator<SubTask>() {
        @Override
        public SubTask createFromParcel(Parcel in) {
            return new SubTask(in);
        }

        @Override
        public SubTask[] newArray(int size) {
            return new SubTask[size];
        }
    };

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
        dest.writeByte((byte) (completed == null ? 0 : completed ? 1 : 2));
        dest.writeString(key);
    }
}
