public abstract class ${creatorClassName} {

    public abstract ${productInterfaceName} ${creatorMethodName}();

    public void someOperation() {
        ${productInterfaceName} product = ${creatorMethodName}();
        product.${productMethodName}();
    }
}
