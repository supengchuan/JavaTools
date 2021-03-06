package JPBCTest;

import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Pairing;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;

public class ClTest {
    public static void main(String[] args) {
        CLSignature cl = new CLSignature("a.properties");

        Pairing p = PairingFactory.getPairing("a.properties");
        Element c = p.getZr().newElement(14);
        Element d = p.getZr().newElement(15);
        Element m = cl.getG().powZn(c);

        SignatureBody s = cl.sign(m);

        boolean res = cl.userVerify(s, c);

        System.out.println(res);

        Element r = p.getZr().newElement(16);
        Element r1 = p.getZr().newElement(17);
        SignatureBody anonySb = cl.zkpfBody(s, r, r1);
        Element vxy = p.pairing(cl.getPK().getX(), anonySb.getB()).powZn(c);

        System.out.println(cl.zkpfVerify(anonySb, r, vxy));
    }


}
