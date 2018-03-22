package beanTask.bean;

public class BeanC {

    BeanD beanD;

    public BeanD getBeanD() {
        return beanD;
    }

    public void setBeanD(BeanD beanD) {
        this.beanD = beanD;
    }

    public String sayHello() {
        return "Hello I bean C";
    }

    public String sayHelloBeanD(){
        return beanD.sayHello();
    }
}
