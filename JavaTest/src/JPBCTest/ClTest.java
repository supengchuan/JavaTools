package JPBCTest;

import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Pairing;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;

public class ClTest {
    public static void main(String [] args) {
        CLSignature cl = new CLSignature("a.properties");

        Pairing p = PairingFactory.getPairing("a.properties");
        Element c = p.getZr().newElement(14);
        Element d = p.getZr().newElement(15);
        Element m = cl.getG().powZn(c);

        SignatureBody s = cl.sign(m);

        boolean res = cl.userVerify(s, d);

        System.out.println(res);
    }


}
