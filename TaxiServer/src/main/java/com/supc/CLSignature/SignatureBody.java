package com.supc.CLSignature;


import it.unisa.dia.gas.jpbc.Element;

import java.io.Serializable;

public class SignatureBody implements Serializable {
    private Element a;
    private Element b;
    private Element c;

    public SignatureBody() {

    }

    public SignatureBody(Element a, Element b, Element c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public Element getA() {
        return a;
    }

    public void setA(Element a) {
        this.a = a;
    }

    public Element getB() {
        return b;
    }

    public void setB(Element b) {
        this.b = b;
    }

    public Element getC() {
        return c;
    }

    public void setC(Element c) {
        this.c = c;
    }
}
