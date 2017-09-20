package pattern.templatemethod;

public class Client {
    public static void main(String[] args) {
        new OnlineBankingLambda()
                .processCustomer(1337, (Customer c) -> System.out.println("Hello " + c.getName()));

    }
}
