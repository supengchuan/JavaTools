package com.supc.CLSignature;

import it.unisa.dia.gas.jpbc.Element;

import java.io.Serializable;

public class ZKPFBody implements Serializable {
    private SignatureBody signatureBody;
    private Element r;
    private Element vxy;

    public ZKPFBody() {
    }

    public ZKPFBody(SignatureBody signatureBody, Element r, Element vxy) {
        this.signatureBody = signatureBody;
        this.r = r;
        this.vxy = vxy;
    }

    public SignatureBody getSignatureBody() {
        return signatureBody;
    }

    public void setSignatureBody(SignatureBody signatureBody) {
        this.signatureBody = signatureBody;
    }

    public Element getR() {
        return r;
    }

    public void setR(Element r) {
        this.r = r;
    }

    public Element getVxy() {
        return vxy;
    }

    public void setVxy(Element vxy) {
        this.vxy = vxy;
    }
}
