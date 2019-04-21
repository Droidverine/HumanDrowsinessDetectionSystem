package com.droidverine.hdds.hdds.models;

/**
 * Created by DELL on 25-11-2017.
 */

public class Messages {
    public String msghead,msgtext,uid;
    long msgtime;
    public Messages()
    {

    }

    public Messages(String uid, String msghead, String msgtext, long msgtime) {
        this.uid=uid;
        this.msghead = msghead;
        this.msgtext = msgtext;
        this.msgtime = msgtime;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUid() {
        return uid;
    }

    public long getMsgtime() {
        return msgtime;
    }

    public void setMsgtime(long msgtime) {
        this.msgtime = msgtime;
    }

    public String getMsghead() {
        return msghead;
    }

    public void setMsghead(String msghead) {
        this.msghead = msghead;
    }

    public String getMsgtext() {
        return msgtext;
    }

    public void setMsgtext(String msgtext) {
        this.msgtext = msgtext;
    }
}
