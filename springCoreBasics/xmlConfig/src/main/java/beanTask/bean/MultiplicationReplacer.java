package beanTask.bean;

import org.springframework.beans.factory.support.MethodReplacer;

import java.lang.reflect.Method;

public class MultiplicationReplacer implements MethodReplacer {

    @Override
    public Object reimplement(Object obj, Method method, Object[] args) throws Throwable {
        int firstParam = (Integer)args[0];
        int secondParam = (Integer)args[1];
        return firstParam * secondParam;
    }
}
