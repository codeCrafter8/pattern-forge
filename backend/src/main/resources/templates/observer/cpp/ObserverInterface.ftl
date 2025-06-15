#ifndef ${observerInterfaceName?upper_case}_H
#define ${observerInterfaceName?upper_case}_H

class ${observerInterfaceName} {
public:
    virtual ~${observerInterfaceName}() = default;
    virtual void ${updateMethodName}() = 0;
};

#endif // ${observerInterfaceName?upper_case}_H
