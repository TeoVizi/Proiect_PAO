package service;

import java.util.HashMap;
import java.util.Map;

public abstract class SingletonService<T> {
    private static final Map<Class<?>, SingletonService<?>> instances = new HashMap<>();

    protected SingletonService() {}

    @SuppressWarnings("unchecked")
    public static <S extends SingletonService<?>> S getInstance(Class<S> clazz) {
        synchronized (instances) {
            return (S) instances.computeIfAbsent(clazz, k -> {
                try {
                    return clazz.getDeclaredConstructor().newInstance();
                } catch (Exception e) {
                    throw new RuntimeException("Failed to create instance of " + clazz.getSimpleName(), e);
                }
            });
        }
    }
}
