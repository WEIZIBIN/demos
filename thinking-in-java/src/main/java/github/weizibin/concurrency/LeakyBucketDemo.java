package github.weizibin.concurrency;

public class LeakyBucketDemo {
    //时间刻度
    private static int water = 0;
    private static int size = 3;
    private static int rate = 3;

    public static boolean grant() {
        if (water < size) {
            // 进入漏桶
            ++water;
            // 控制时间
            return true;
        } else {
            return false;
        }
    }

    public static void removeWaterPerSecond() {
        water = Math.max(0, water - rate);
    }

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1 * 1000);
                    removeWaterPerSecond();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        for (int i = 0; i < 500; i++) {
            Thread.sleep(100);
            new Thread(() -> {
                if (grant()) {
                    System.out.println("执行业务逻辑");
                } else {
                    System.out.println("限流");
                }
             }).start();
        }
    }
}
