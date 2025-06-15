#ifndef ${originatorClassName?upper_case}_H
#define ${originatorClassName?upper_case}_H

#include "${mementoClassName}.h"
#include <string>

class ${originatorClassName} {
private:
    std::string ${stateFieldName};

public:
    explicit ${originatorClassName}(const std::string& initialState)
        : ${stateFieldName}(initialState) {}

    ${mementoClassName} save() const {
        return ${mementoClassName}(${stateFieldName});
    }

    void restore(const ${mementoClassName}& memento) {
        ${stateFieldName} = memento.getState();
    }

    const std::string& get${stateFieldName?cap_first}() const {
        return ${stateFieldName};
    }

    void set${stateFieldName?cap_first}(const std::string& state) {
        ${stateFieldName} = state;
    }
};

#endif // ${originatorClassName?upper_case}_H
