package com.supc.CLSignature;

import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Pairing;

import java.io.Serializable;

public class CLPK implements Serializable {
    private Element g;
    private Element X;
    private Element Y;
    private Pairing pairing;

    public CLPK(Pairing pairing, Element g, Element X, Element Y) {
        this.pairing = pairing;
        this.g = g;
        this.X = X;
        this.Y = Y;
    }

    public void setG(Element g) {
        this.g = g;
    }

    public void setX(Element x) {
        X = x;
    }

    public void setY(Element y) {
        Y = y;
    }

    public void setPairing(Pairing pairing) {
        this.pairing = pairing;
    }

    public Element getG() {
        return g;
    }

    public Element getX() {
        return X;
    }

    public Element getY() {
        return Y;
    }

    public Pairing getPairing() {
        return pairing;
    }
}
