package com.supc.Entity;

import java.io.Serializable;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public class AnonymousUser implements Serializable {
    private String AnonyName;
    private RSAPublicKey key_pub;
    private RSAPrivateKey key_pri;


    public AnonymousUser() {
    }

    public AnonymousUser(String name) {
        this.AnonyName = name;
    }

    public AnonymousUser(String name, RSAPublicKey key_pub) {
        this.AnonyName = name;
        this.key_pub = key_pub;
    }

    public AnonymousUser(String name, RSAPublicKey key_pub, RSAPrivateKey key_pri) {
        this.AnonyName = name;
        this.key_pub = key_pub;
        this.key_pri = key_pri;
    }

    public RSAPrivateKey getKey_pri() {
        return key_pri;
    }

    public void setKey_pri(RSAPrivateKey key_pri) {
        this.key_pri = key_pri;
    }

    public String getAnonyName() {
        return AnonyName;
    }

    public void setAnonyName(String anonyName) {
        AnonyName = anonyName;
    }

    public RSAPublicKey getKey_pub() {
        return key_pub;
    }

    public void setKey_pub(RSAPublicKey key_pub) {
        this.key_pub = key_pub;
    }
}
