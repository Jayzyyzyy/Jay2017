package Chapter3;

/**
 *  动态对象年龄判断
 *
 */
public class maxTenuringThresholdTest3 {
    private static final int _1MB = 1024 * 1024;

    /**
     * VM参数：-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=15
     -XX:+PrintTenuringDistribution
     */
    @SuppressWarnings("unused")
    public static void testTenuringThreshold() {
        byte[] allocation1, allocation2, allocation3, allocation4;
        // allocation1+allocation2大于survivor空间一半
        allocation1 = new byte[_1MB / 4];
        allocation2 = new byte[_1MB / 4];
        allocation3 = new byte[4 * _1MB];
        allocation4 = new byte[4 * _1MB];  //第一次Gc
        allocation4 = null;
        allocation4 = new byte[4 * _1MB];  //第二次GC
    }

    public static void main(String[] args) {
        testTenuringThreshold();
    }

}
