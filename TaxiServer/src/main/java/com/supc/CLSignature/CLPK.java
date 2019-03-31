package com.supc.CLSignature;

import it.unisa.dia.gas.jpbc.Element;

import java.io.Serializable;

public class CLPK implements Serializable {
    private byte[] g;
    private byte[] X;
    private byte[] Y;

    public CLPK(Element g, Element X, Element Y) {

        this.g = g.toBytes();
        this.X = X.toBytes();
        this.Y = Y.toBytes();
    }

    public byte[] getG() {
        return g;
    }

    public void setG(byte[] g) {
        this.g = g;
    }

    public byte[] getX() {
        return X;
    }

    public void setX(byte[] x) {
        X = x;
    }

    public byte[] getY() {
        return Y;
    }

    public void setY(byte[] y) {
        Y = y;
    }
}
