#ifndef ${adapteeClassName?upper_case}_H
#define ${adapteeClassName?upper_case}_H

#include <iostream>

class ${adapteeClassName} {
public:
    void ${adapteeMethodName}();
};

#endif

void ${adapteeClassName}::${adapteeMethodName}() {
    std::cout << "Called adaptee method." << std::endl;
}
