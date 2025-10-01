class Book {
    private String title;
    private double price;

    public Book(String title, double price) {
        this.title = title;
        this.price = price;
    }

    public double getPrice() { return price; }
    public String getTitle() { return title; }
}

class PaymentGateway {
    public boolean processPayment(double amount) {
        System.out.println("支付网关：正在处理支付金额 $" + amount);
        // 模拟支付成功或失败
        return amount > 0 && Math.random() > 0.1; // 假设大部分支付成功
    }
}

class InventorySystem {
    public void updateStock(Book book, int quantityChange) {
        System.out.println("库存系统：更新书籍 '" + book.getTitle() + "' 库存，数量变化: " + quantityChange);
    }
}

// 主要类与LoD应用
class ShoppingCart {
    private java.util.List<Book> items = new java.util.ArrayList<>();
    private PaymentGateway paymentGateway; // 直接朋友 (成员变量)

    public ShoppingCart(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway; // 通过构造函数注入的朋友
    }

    public void addBook(Book book) { // book 是方法参数，是此方法的朋友
        this.items.add(book); // this 是自身，可以调用自己的方法
        System.out.println("购物车：'" + book.getTitle() + "' 已添加到购物车。");
    }

    public double calculateTotal() {
        double total = 0;
        for (Book item : this.items) { // item 是在方法内迭代产生的局部变量，其类型Book是items集合的元素类型
            total += item.getPrice(); // 调用了 item (Book类型) 的方法，Book是ShoppingCart认识的类型
        }
        return total;
    }

    // checkout 方法演示了与直接朋友 paymentGateway 的交互
    public boolean checkout(InventorySystem inventory) { // inventory 是方法参数，是此方法的朋友
        double total = this.calculateTotal(); // 调用自身方法
        System.out.println("购物车：准备结账，总金额：$" + total);

        // 调用直接朋友 paymentGateway 的方法
        boolean paymentSuccessful = this.paymentGateway.processPayment(total);

        if (paymentSuccessful) {
            System.out.println("购物车：支付成功！");
            for (Book item : this.items) {
                // 调用参数朋友 inventory 的方法
                inventory.updateStock(item, -1); // 假设每买一本库存减一
            }
            this.items.clear(); // 调用自身方法
            return true;
        } else {
            System.out.println("购物车：支付失败。");
            return false;
        }
    }
}

class Customer {
    private String name;
    private ShoppingCart cart; // 直接朋友 (成员变量)

    public Customer(String name, ShoppingCart cart) {
        this.name = name;
        this.cart = cart; // 通过构造函数注入的朋友
    }

    public void addItemToCart(Book book) { // book 是方法参数，是此方法的朋友
        // Customer 调用其直接朋友 cart 的方法
        this.cart.addBook(book);
    }

    public boolean placeOrder(InventorySystem inventory) { // inventory 是方法参数，是此方法的朋友
        System.out.println("顾客 " + this.name + " 正在下单...");
        // Customer 调用其直接朋友 cart 的方法
        // inventory 被传递给 cart.checkout，对于 cart.checkout 来说是参数朋友
        return this.cart.checkout(inventory);
    }

    public String getName() { return name; }
}


public class LoDDemo {
    public static void main(String[] args) {
        // 1. 创建朋友对象
        PaymentGateway pg = new PaymentGateway(); // 在main方法内创建的对象
        InventorySystem invSys = new InventorySystem(); // 在main方法内创建的对象
        ShoppingCart myCart = new ShoppingCart(pg); // pg 是 myCart 的构造函数参数朋友，并成为其成员变量朋友
        Customer alice = new Customer("Alice", myCart); // myCart 是 alice 的构造函数参数朋友，并成为其成员变量朋友

        Book book1 = new Book("Effective Java", 45.0); // 在main方法内创建的对象
        Book book2 = new Book("Clean Code", 50.0);   // 在main方法内创建的对象

        // 2. 顾客与她的直接朋友（购物车）交互
        alice.addItemToCart(book1); // book1 是 addItemToCart 方法的参数朋友
        alice.addItemToCart(book2);

        // 3. 顾客下单，购物车会和它的直接朋友（支付网关）以及参数朋友（库存系统）交互
        boolean orderPlaced = alice.placeOrder(invSys); // invSys 是 placeOrder 方法的参数朋友

        if (orderPlaced) {
            System.out.println(alice.getName() + " 的订单已成功处理。");
        } else {
            System.out.println(alice.getName() + " 的订单处理失败。");
        }

        // 错误示例：违反LoD (不应该这样做)
        // 假设Customer想直接操作PaymentGateway，这是不推荐的
        // PaymentGateway customerPg = alice.getCart().getPaymentGateway(); // 假设有这样的getter链
        // customerPg.processPayment(10.0); // Customer通过购物车的朋友的朋友来通信，是“陌生人”
        // 正确的做法是，Customer只和ShoppingCart通信，由ShoppingCart去和PaymentGateway通信。
    }
}