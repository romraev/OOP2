package org.example;

import java.util.*;

interface HumanBehaviour{

    void setMakeOrder(boolean choice);
    void setTakeOrder(boolean choice);

}
abstract class Human implements HumanBehaviour{
    protected String _name;
    protected int _age;
    boolean isMakeOrder = false;
    boolean isTakeOrder = false;

    public Human(String name, int age){
        this._name = name;
        this._age = age;
    }
    public String GetName(){
        return _name;
    }
    public int GetAge(){
        return _age;
    }
    
}

class Customer extends Human{

    public Customer(String name, int age) {
        super(name, age);
    }

    @Override
    public void setMakeOrder(boolean choice) {
        this.isMakeOrder = choice;
    }

    @Override
    public void setTakeOrder(boolean choice) {
        this.isTakeOrder = choice;
    }

    @Override
    public String toString() {
        return _name + " " + _age + " лет " + "забрать заказ - " + isTakeOrder + " / создать заказ - " + isMakeOrder;
    }
}

interface QueueBehaviour{
    void takeInQueue(Customer cust);
    void takeOrders();
    void giveOrders();
    void releaseFromQueue();
}

interface MarketBehaviour{
    void acceptToMarket(Customer cust);
    void releaseFromMarket();
    void update();
}

class Market implements QueueBehaviour, MarketBehaviour{
    protected List<Customer> list = new ArrayList<>();
    protected List<Customer> line = new ArrayList<>();

    @Override
    public void takeInQueue(Customer cust) {
        line.add(cust);
    }

    @Override
    public void takeOrders() {
        if (line.get(0) != null) {
            if (list.get(0).isMakeOrder) {
                Customer customer = list.get(0);
                customer.setMakeOrder(false);
                list.set(0, customer);
            } else {
                giveOrders();
            }
        }
    }

    @Override
    public void giveOrders() {
        if (line.get(0) != null) {
            if (list.get(0).isMakeOrder) {
                Customer customer = list.get(0);
                customer.setTakeOrder(false);
                list.set(0, customer);
            }
        }
    }

    @Override
    public void releaseFromQueue() {
        line.remove(0);
    }

    @Override
    public void acceptToMarket(Customer cust) {
         list.add(cust);
    }

    @Override
    public void releaseFromMarket() {
        list.remove(0);
    }

    @Override
    public void update() {
        System.out.println(line);
        System.out.println(list);
    }
}
public class Main {
    public static void main(String[] args) {
        Customer cust1 = new Customer("Иван", 23);
        Customer cust2 = new Customer("Илона", 24);
        Customer cust3 = new Customer("Игорь", 43);
        Customer cust4 = new Customer("Олег", 33);
        Customer cust5 = new Customer("Роман", 27);
        Customer cust6 = new Customer("Иванка", 55);
        Customer cust7 = new Customer("Олег", 23);
        Customer cust8 = new Customer("Иван", 26);

        cust1.setMakeOrder(true);
        cust2.setTakeOrder(true);
        cust3.setMakeOrder(true);
        cust4.setTakeOrder(true);
        cust5.setMakeOrder(true);
        cust6.setTakeOrder(true);
        cust7.setMakeOrder(true);
        cust8.setTakeOrder(true);


        Market market = new Market();
        market.acceptToMarket(cust1);
        market.acceptToMarket(cust2);
        market.acceptToMarket(cust3);
        market.acceptToMarket(cust4);
        market.acceptToMarket(cust5);
        market.acceptToMarket(cust6);
        market.acceptToMarket(cust7);
        market.acceptToMarket(cust8);

        market.takeInQueue(cust1);
        market.takeInQueue(cust2);
        market.takeInQueue(cust3);
        market.takeInQueue(cust4);
        market.takeInQueue(cust5);
        market.takeInQueue(cust6);
        market.takeInQueue(cust7);
        market.takeInQueue(cust8);

        market.update();

        Iterator iterator = market.list.iterator();
        while(iterator.hasNext()){
            market.takeOrders();
            market.giveOrders();
            market.update();
            market.releaseFromQueue();
            market.update();
            market.releaseFromMarket();
        }




    }
}