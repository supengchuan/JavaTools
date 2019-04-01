package com.supc.CLSignature;

import java.io.Serializable;

public class SignatureBodyBytes implements Serializable {
    private byte[] a;
    private byte[] b;
    private byte[] c;

    public SignatureBodyBytes() {
    }

    public SignatureBodyBytes(byte[] a, byte[] b, byte[] c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public byte[] getA() {
        return a;
    }

    public void setA(byte[] a) {
        this.a = a;
    }

    public byte[] getB() {
        return b;
    }

    public void setB(byte[] b) {
        this.b = b;
    }

    public byte[] getC() {
        return c;
    }

    public void setC(byte[] c) {
        this.c = c;
    }
}
