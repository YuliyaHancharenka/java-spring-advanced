package beanTask.bean;

public class BeanD {

    BeanA beanA;

    public BeanA getBeanA() {
        return beanA;
    }

    public void setBeanA(BeanA beanA) {
        this.beanA = beanA;
    }

    public void sayHelloBeanA() {
        System.out.println("I am a bean D use bean A: " + beanA.getSayHello());
    }

    public String sayHello(){
        return "Hello, I'm bean D";
    }
}
