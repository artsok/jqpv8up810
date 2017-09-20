package pattern.templatemethod;


import java.util.function.Consumer;

class OnlineBankingLambda {

    public void processCustomer(int id, Consumer<Customer> makeCustomerHappy) {
        Customer c = Database.getCustomerWithId(id);
        makeCustomerHappy.accept(c);
    }


}
