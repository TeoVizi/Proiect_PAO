package service;

import java.util.HashMap;
import java.util.Map;

public abstract class BaseService<T> implements CRUDService<T> {
    private static final Map<Class<?>, BaseService<?>> instances = new HashMap<>();

    protected BaseService() {}

    @SuppressWarnings("unchecked")
    public static <S extends BaseService<?>> S getInstance(Class<S> clazz) {
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
