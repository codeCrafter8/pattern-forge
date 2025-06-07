public class ${concreteCreatorClassName} extends ${creatorClassName} {

    @Override
    public ${productInterfaceName} ${creatorMethodName}() {
        return new ${productClassName}();
    }
}
