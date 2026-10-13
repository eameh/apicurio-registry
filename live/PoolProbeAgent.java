import java.lang.instrument.Instrumentation;
import java.lang.reflect.Method;

public class PoolProbeAgent {
    private static Object call(Object object, String name) throws Exception {
        Method method = object.getClass().getMethod(name);
        method.setAccessible(true);
        return method.invoke(object);
    }

    public static void premain(String args, Instrumentation instrumentation) {
        Thread thread = new Thread(() -> {
            Throwable last = null;
            for (int attempt = 0; attempt < 120; attempt++) {
                try {
                    Thread.sleep(1000);
                    for (Class<?> type : instrumentation.getAllLoadedClasses()) {
                        if (type.getName().equals("io.quarkus.agroal.runtime.DataSources")) {
                            Object source = type.getMethod("fromName", String.class).invoke(null, "postgresql");
                            Object configuration = call(call(source, "getConfiguration"), "connectionPoolConfiguration");
                            StringBuilder result = new StringBuilder("DOCS_GAP_POOL_PROBE datasource=postgresql");
                            for (String method : new String[] {"initialSize", "minSize", "maxSize", "acquisitionTimeout", "validationTimeout", "idleValidationTimeout", "maxLifetime"}) {
                                result.append(" ").append(method).append("=").append(call(configuration, method));
                            }
                            System.out.println(result);
                            return;
                        }
                    }
                } catch (Throwable error) {
                    last = error;
                }
            }
            System.out.println("DOCS_GAP_POOL_PROBE_FAILED " + last);
        }, "docs-gap-pool-observer");
        thread.setDaemon(true);
        thread.start();
    }
}
