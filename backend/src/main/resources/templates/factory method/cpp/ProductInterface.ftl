#ifndef ${productInterfaceName?upper_case}_H
#define ${productInterfaceName?upper_case}_H

class ${productInterfaceName} {
public:
    virtual ~${productInterfaceName}() = default;
    virtual void ${productMethodName}() = 0;
};

#endif
