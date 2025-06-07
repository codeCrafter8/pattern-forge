#include "${observerInterfaceName}.h"
#include <iostream>

class ${concreteObserverClassName} : public ${observerInterfaceName} {
public:
    void ${updateMethodName}() override {
        std::cout << "${concreteObserverClassName} notified." << std::endl;
    }
};
