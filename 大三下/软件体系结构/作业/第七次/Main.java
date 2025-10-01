// 旧系统接口
interface OldSystem {
    String oldMethod();
}

// 新系统接口
interface NewSystem {
    String newMethod();
}

// 旧系统具体实现
class ConcreteOldSystem implements OldSystem {
    @Override
    public String oldMethod() {
        return "这是旧系统的方法";
    }
}

// 新系统具体实现
class ConcreteNewSystem implements NewSystem {
    @Override
    public String newMethod() {
        return "这是新系统的方法";
    }
}

// 双向适配器
class BidirectionalAdapter implements OldSystem, NewSystem {
    private OldSystem oldSystem;
    private NewSystem newSystem;

    public BidirectionalAdapter(OldSystem oldSystem, NewSystem newSystem) {
        this.oldSystem = oldSystem;
        this.newSystem = newSystem;
    }

    @Override
    public String oldMethod() {
        if (oldSystem != null) {
            return oldSystem.oldMethod();
        }
        return "适配器调用旧系统方法";
    }

    @Override
    public String newMethod() {
        if (newSystem != null) {
            return newSystem.newMethod();
        }
        return "适配器调用新系统方法";
    }
}

// 主类
public class Main {
    public static void main(String[] args) {
        // 创建具体系统实例
        OldSystem oldSystem = new ConcreteOldSystem();
        NewSystem newSystem = new ConcreteNewSystem();

        // 创建双向适配器
        BidirectionalAdapter adapter = new BidirectionalAdapter(oldSystem, newSystem);

        // 通过适配器调用旧系统方法
        System.out.println("通过适配器调用旧系统方法: " + adapter.oldMethod());
        
        // 通过适配器调用新系统方法
        System.out.println("通过适配器调用新系统方法: " + adapter.newMethod());
    }
} 