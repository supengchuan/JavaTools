package com.supc.Entity;

import java.io.Serializable;

public class Ticket implements Serializable {
    private int value;
    private long r;
    private long time;
    private byte[] cipher;


    public Ticket() {
    }

    public Ticket(int value, long r, long time, byte[] cipher) {
        this.value = value;
        this.r = r;
        this.time = time;
        this.cipher = cipher;
    }


    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public long getR() {
        return r;
    }

    public void setR(long r) {
        this.r = r;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public byte[] getCipher() {
        return cipher;
    }

    public void setCipher(byte[] cipher) {
        this.cipher = cipher;
    }
}
