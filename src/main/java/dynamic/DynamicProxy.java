package dynamic;

import actors.ActorProxy;
import messages.AddInsultMessage;
import messages.GetAllInsultsMessage;
import messages.GetInsultMessage;
import messages.Message;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

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
        List<Object> result = new ArrayList<>();
        try
        {
            System.out.println("Method " + method.getName() + " mediated by DynamicProxy");
            switch (method.getName()) {
                case "getAllInsults" -> actor.send(new GetAllInsultsMessage(actor));
                case "getInsult" -> actor.send(new GetInsultMessage(actor));
                case "addInsult" -> actor.send(new AddInsultMessage((String) args[0]));
                default -> System.out.println("This method does not exist.");
            }
            if(method.getReturnType() != void.class) {
                do {
                    try {
                        result.add(actor.receive().getText());
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                while (!actor.getQueue().isEmpty());
            }
        }
        catch(Exception e)
        {
            System.err.println("Invocation of " + method.getName() + " failed");
        }
        finally{
            return result.size() == 0 ? null:result.size() == 1 ? result.get(0):result;
        }

    }
}
