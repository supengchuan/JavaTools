package com.supc.CLSignature;

import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Pairing;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;

public class CLParamters {
    private static CLParamters paramters = null;
    private Pairing pairing;
    private Element x;
    private Element y;
    private Element g;
    private Element alpha;

    private CLParamters() {
        String properties = "a.properties";
        this.pairing = PairingFactory.getPairing(properties);
        this.alpha = pairing.getZr().newRandomElement().getImmutable();
        this.x = pairing.getZr().newRandomElement().getImmutable();
        this.y = pairing.getZr().newRandomElement().getImmutable();
        this.g = pairing.getG1().newRandomElement().getImmutable();
    }

    public static CLParamters getInstance() {
        if (paramters == null) {
            synchronized (CLParamters.class) {
                if (paramters == null)
                    paramters = new CLParamters();
            }
        }

        return paramters;
    }


    public Pairing getPairing() {
        return pairing;
    }

    public Element getX() {
        return x;
    }

    public Element getY() {
        return y;
    }

    public Element getG() {
        return g;
    }

    public Element getAlpha() {
        return alpha;
    }
}
