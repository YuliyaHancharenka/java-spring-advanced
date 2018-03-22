package beanTask.client;

import beanTask.bean.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ClientClass {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        BeanA beanA = context.getBean("beanA", BeanA.class);
        System.out.println(beanA.getSayHello());

        BeanB beanB = context.getBean("beanB", BeanB.class);
        System.out.println("Placeholder result: " + beanB.getBeanA().getSayHello());

        BeanC beanC = context.getBean("beanC", BeanC.class);
        beanC.sayHelloBeanD();

        BeanD beanD = context.getBean("beanD", BeanD.class);
        beanD.sayHello();

        BeanE beanE = context.getBean("beanE", BeanE.class);
        System.out.println("Result after method replacement: " + beanE.sum(2,3));

        context.close();
    }
}
