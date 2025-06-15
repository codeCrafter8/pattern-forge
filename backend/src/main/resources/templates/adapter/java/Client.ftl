public class ${clientClassName} {
    public static void main(String[] args) {
        ${targetInterfaceName} target = new ${adapterClassName}();
        target.request();
    }
}
