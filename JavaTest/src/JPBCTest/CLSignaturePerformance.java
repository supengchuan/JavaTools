package JPBCTest;

import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Pairing;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;

public class CLSignaturePerformance {
    public static void main(String[] args) {
        CLSignature cl = new CLSignature("a.properties");
        Pairing p = PairingFactory.getPairing("a.properties");
        Element c = p.getZr().newElement(14);
        Element m = cl.getG().powZn(c);
        SignatureBody s = cl.sign(m);

        Element r = p.getZr().newElement(16);
        Element r1 = p.getZr().newElement(17);
        SignatureBody anonySb = cl.zkpfBody(s, r, r1);

        for (int i = 0; i < 1000; i++) {
            long startTime = System.currentTimeMillis();
            Element vxy = p.pairing(cl.getPK().getX(), anonySb.getB()).powZn(c);
            boolean res = cl.zkpfVerify(anonySb, r, vxy);
            long endTime = System.currentTimeMillis();
            System.out.println((endTime - startTime));
        }

    }

}
