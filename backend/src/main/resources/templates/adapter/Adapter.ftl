public class ${adapterClassName} implements ${targetInterfaceName} {
    private final ${adapteeClassName} adaptee = new ${adapteeClassName}();

    @Override
    public void request() {
        adaptee.${adapteeMethodName}();
    }
}
