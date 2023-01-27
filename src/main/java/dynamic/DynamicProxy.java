package dynamic;

import actors.ActorProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
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
        try
        {
            System.out.println("Method " + method.getName() + " mediated by DynamicProxy");
            invocationResult = method.invoke(this.target, args);
        }
        catch(InvocationTargetException ite)
        {
            throw ite.getTargetException();
        }
        catch(Exception e)
        {
            System.err.println("Invocation of " + method.getName() + " failed");
        }
        finally{
            return invocationResult;
        }
    }
}
