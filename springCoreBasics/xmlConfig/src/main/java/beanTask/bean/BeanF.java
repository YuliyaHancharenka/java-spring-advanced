package beanTask.bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class BeanF implements InitializingBean, DisposableBean {

    public BeanF() {
        System.out.println("I am in the Constructor");
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("I am after properties set");
    }

    public void init() {
        System.out.println("I am in simple init");
    }

    public void destroySimple() {
        System.out.println("I am in simple destroy");
    }

    @Override
    public void destroy() {
        System.out.println("I implemented destroy");
    }
}
