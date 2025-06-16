public class ${originatorClassName} {
    private String ${stateFieldName};

    public ${originatorClassName}(String initialState) {
        this.${stateFieldName} = initialState;
    }

    public ${mementoClassName} save() {
        return new ${mementoClassName}(${stateFieldName});
    }

    public void restore(${mementoClassName} memento) {
        this.${stateFieldName} = memento.getState();
    }

    public String get${stateFieldName?cap_first}() {
        return ${stateFieldName};
    }

    public void set${stateFieldName?cap_first}(String state) {
        this.${stateFieldName} = state;
    }
}
