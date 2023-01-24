package actors;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxy implements InvocationHandler {
    private Object target = null;
    private ActorProxy actor;

    public static Object intercept(Object target, ActorProxy actor) {
        Class targetClass = target.getClass();
        Class [] interfaces = targetClass.getInterfaces();
        return Proxy.newProxyInstance(targetClass.getClassLoader(), interfaces, new DynamicProxy(target, actor));
    }

    public DynamicProxy(Object target, ActorProxy actor) {
        this.target = target;
        this.actor = actor;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object invocationResult = null;

        return null;
    }
}
