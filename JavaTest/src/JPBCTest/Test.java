package JPBCTest;

import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Pairing;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;

public class Test {

    public static void main(String[] args) {

        long startTime = System.currentTimeMillis();


        Pairing pairing = PairingFactory.getPairing("a.properties");
        //获取一个随机数r
        Element r = pairing.getZr().newRandomElement().getImmutable();
        //System.out.println(r);
        //获得群G中的元素
        Element g = pairing.getG1().newRandomElement().getImmutable();
        System.out.println(g);
        Element h = pairing.getG1().newRandomElement().getImmutable();
        byte [] bytes = g.toBytes();

        Element k = pairing.getG1().newElementFromBytes(bytes);

        System.out.println(k);

        //做幂运算
        Element gr = g.powZn(r).getImmutable();
        Element hr = h.powZn(r).getImmutable();

        //判读等不等要用equals
        if (pairing.pairing(g, hr).equals(pairing.pairing(gr, h))) {
            System.out.println("true");
        }

        long endTime = System.currentTimeMillis();

        System.out.println(endTime - startTime);
    }
}
