package Singleton;

public class LanhanDanLi {
    private static LanhanDanLi danLi = null;

    private LanhanDanLi() {
    }

    public static synchronized LanhanDanLi getInstance() {
        if (danLi == null) {
            danLi = new LanhanDanLi();
        }
        return danLi;
    }
}
