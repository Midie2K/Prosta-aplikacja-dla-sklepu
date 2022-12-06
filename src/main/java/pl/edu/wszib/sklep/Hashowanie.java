package pl.edu.wszib.sklep;

import org.apache.commons.codec.digest.DigestUtils;
import pl.edu.wszib.sklep.core.Authenticator;

public class Hashowanie {
    public static void main(String[] args) {
        Authenticator authenticator = Authenticator.getIstance();
        System.out.println(DigestUtils.md5Hex("admin" + authenticator.seed));
    }
}
