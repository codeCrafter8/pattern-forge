public class ${className} {
    private static volatile ${className} instance;

    private ${className}() {}

    public static ${className} getInstance() {
        if (instance == null) {
            synchronized (${className}.class) {
                if (instance == null) {
                    instance = new ${className}();
                }
            }
        }
        return instance;
    }
}
