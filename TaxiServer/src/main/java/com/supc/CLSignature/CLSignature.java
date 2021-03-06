package com.supc.CLSignature;

import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Pairing;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;

public class CLSignature {
    private Element g;
    private Element alpha;
    private Element sx;
    private Element sy;
    private Element X;
    private Element Y;
    private Pairing pairing;


    public CLSignature() {
    }

    public CLSignature(Pairing pairing, Element g, Element sx, Element sy, Element alpha) {
        this.g = g;
        this.alpha = alpha;
        this.sx = sx;
        this.sy = sy;
        this.pairing = pairing;
        this.X = g.powZn(sx).getImmutable();
        this.Y = g.powZn(sy).getImmutable();
    }

    public CLSignature(String properties) {
        this.pairing = PairingFactory.getPairing(properties);
        this.alpha = pairing.getZr().newRandomElement().getImmutable();
        this.sx = pairing.getZr().newRandomElement().getImmutable();
        this.sy = pairing.getZr().newRandomElement().getImmutable();
        this.g = pairing.getG1().newRandomElement().getImmutable();
        this.X = g.powZn(sx).getImmutable();
        this.Y = g.powZn(sy).getImmutable();
    }

    //g,X,Y,pairing
    public CLPK getPK() {
        return new CLPK(this.g, this.X, this.Y);
    }

    public void setCLPK(Pairing pairing, Element g, Element X, Element Y) {
        this.pairing = pairing;
        this.g = g;
        this.X = X;
        this.Y = Y;
    }

    /**
     * @param m 需要签名的消息，但是是g的c次方，需要签名的数据为c
     * @return 签名三元组
     */
    public SignatureBody sign(Element m) {
        Element a = g.powZn(alpha).getImmutable();
        //System.out.println(a.toBytes());
        Element b = a.powZn(sy).getImmutable();
        Element c = a.powZn(sx).mul(m.powZn(alpha.mul(sx).mul(sy))).getImmutable();
        return new SignatureBody(a, b, c);
    }

    public boolean serverVerify() {
        boolean res = false;

        return res;
    }

    /**
     * @param signatureBody 服务器颁发的签名 sigma
     * @param c             需要签名的数据 c
     * @return 验证服务的签名是否为正确的
     */
    public boolean userVerify(SignatureBody signatureBody, Element c) {
        boolean res = true;

        res &= verifyAYGB(signatureBody.getA(), signatureBody.getB());
        System.out.println(res);
        if (!res) return false;
        res &= pairing.pairing(X, signatureBody.getA()).mul(pairing.pairing(X, signatureBody.getB()).powZn(c))
                .equals(pairing.pairing(g, signatureBody.getC()));
        return res;
    }

    /**
     * @param signatureBody 领知识证明的签名三元组
     * @param r             随机数
     * @param vxy           证明者计算的v_{xy}^c
     * @return 验证是否真的具有签名
     */
    public boolean zkpfVerify(SignatureBody signatureBody, Element r, Element vxy) {
        boolean res = true;

        res &= verifyAYGB(signatureBody.getA(), signatureBody.getB());
        if (!res) {
            return false;
        }

        Element vs = pairing.pairing(g, signatureBody.getC());
        Element vx = pairing.pairing(X, signatureBody.getA());

        res &= vs.equals(vx.mul(vxy).powZn(r));

        return res;
    }

    public SignatureBody zkpfBody(SignatureBody signatureBody, Element r, Element r1) {
        Element a = signatureBody.getA().powZn(r1).getImmutable();
        Element b = signatureBody.getB().powZn(r1).getImmutable();
        Element c = signatureBody.getC().powZn(r1).powZn(r).getImmutable();
        SignatureBody retBody = new SignatureBody(a, b, c);
        return retBody;
    }

    private boolean verifyAYGB(Element a, Element b) {
        return pairing.pairing(a, Y).equals(
                pairing.pairing(g, b));
    }

    public Element getAlpha() {
        return alpha;
    }

    public void setAlpha(Element alpha) {
        this.alpha = alpha;
    }

    public Element getG() {
        return g;
    }

    public void setG(Element g) {
        this.g = g;
    }

    public Element getSx() {
        return sx;
    }

    public void setSx(Element sx) {
        this.sx = sx;
    }

    public Element getSy() {
        return sy;
    }

    public void setSy(Element sy) {
        this.sy = sy;
    }

    public Pairing getPairing() {
        return pairing;
    }

    public void setPairing(Pairing pairing) {
        this.pairing = pairing;
    }

    public Element getX() {
        return X;
    }

    public void setX(Element x) {
        X = x;
    }

    public Element getY() {
        return Y;
    }

    public void setY(Element y) {
        Y = y;
    }
}
