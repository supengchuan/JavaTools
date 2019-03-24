package JPBCTest;

import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Pairing;

public class CLPK {
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
