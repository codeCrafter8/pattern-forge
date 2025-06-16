#ifndef ${mementoClassName?upper_case}_H
#define ${mementoClassName?upper_case}_H

#include <string>

class ${mementoClassName} {
private:
    std::string state;

public:
    explicit ${mementoClassName}(const std::string& state)
        : state(state) {}

    const std::string& getState() const {
        return state;
    }
};

#endif // ${mementoClassName?upper_case}_H
