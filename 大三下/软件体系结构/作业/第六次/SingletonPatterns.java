public class SingletonPatterns {

    /**
     * 1. 双重检查锁定的单例模式实现
     */
    public static class DoubleCheckSingleton {
        // volatile关键字确保instance变量的可见性
        private static volatile DoubleCheckSingleton instance;

        // 私有构造函数防止外部实例化
        private DoubleCheckSingleton() {
        }

        // 获取实例的公共方法
        public static DoubleCheckSingleton getInstance() {
            // 第一次检查，不加锁
            if (instance == null) {
                // 同步块，加锁
                synchronized (DoubleCheckSingleton.class) {
                    // 第二次检查，加锁
                    if (instance == null) {
                        instance = new DoubleCheckSingleton();
                    }
                }
            }
            return instance;
        }
    }

    /**
     * 2. 改进的可变实例数目单例模式实现
     */
    public static class LimitedInstanceSingleton {
        private static final int MAX_INSTANCES = 3; // 最大实例数
        private static final LimitedInstanceSingleton[] instances = new LimitedInstanceSingleton[MAX_INSTANCES];
        private static int availableInstances = 0; // 当前可用的实例数
        private static final Object lock = new Object();

        // 私有构造函数
        private LimitedInstanceSingleton() {
        }

        // 获取实例的公共方法
        public static LimitedInstanceSingleton getInstance() {
            synchronized (lock) {
                // 如果还有可用的实例，直接返回
                if (availableInstances < MAX_INSTANCES) {
                    if (instances[availableInstances] == null) {
                        instances[availableInstances] = new LimitedInstanceSingleton();
                    }
                    return instances[availableInstances++];
                }
                // 如果没有可用实例，抛出异常
                throw new IllegalStateException("已达到最大实例数限制");
            }
        }

        // 释放实例的方法
        public static void releaseInstance(LimitedInstanceSingleton instance) {
            synchronized (lock) {
                for (int i = 0; i < availableInstances; i++) {
                    if (instances[i] == instance) {
                        // 将实例移到最后
                        LimitedInstanceSingleton temp = instances[i];
                        instances[i] = instances[availableInstances - 1];
                        instances[availableInstances - 1] = temp;
                        availableInstances--;
                        return;
                    }
                }
                throw new IllegalArgumentException("实例不存在");
            }
        }

        // 获取当前可用实例数
        public static int getAvailableInstances() {
            return availableInstances;
        }
    }

    // 测试代码
    public static void main(String[] args) {
        // 测试双重检查锁定单例
        System.out.println("测试双重检查锁定单例：");
        DoubleCheckSingleton s1 = DoubleCheckSingleton.getInstance();
        DoubleCheckSingleton s2 = DoubleCheckSingleton.getInstance();
        System.out.println("s1 == s2: " + (s1 == s2));

        // 测试可变实例数目单例
        System.out.println("\n测试可变实例数目单例：");
        try {
            LimitedInstanceSingleton i1 = LimitedInstanceSingleton.getInstance();
            LimitedInstanceSingleton i2 = LimitedInstanceSingleton.getInstance();
            LimitedInstanceSingleton i3 = LimitedInstanceSingleton.getInstance();
            System.out.println("当前可用实例数: " + LimitedInstanceSingleton.getAvailableInstances());

            // 释放一个实例
            LimitedInstanceSingleton.releaseInstance(i2);
            System.out.println("释放一个实例后可用实例数: " + LimitedInstanceSingleton.getAvailableInstances());

            // 再次获取实例
            LimitedInstanceSingleton i4 = LimitedInstanceSingleton.getInstance();
            System.out.println("再次获取实例后可用实例数: " + LimitedInstanceSingleton.getAvailableInstances());

            // 尝试获取超过限制的实例
            LimitedInstanceSingleton i5 = LimitedInstanceSingleton.getInstance();
        } catch (IllegalStateException e) {
            System.out.println("捕获到预期异常: " + e.getMessage());
        }
    }
}