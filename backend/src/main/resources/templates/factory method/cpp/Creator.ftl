#ifndef ${creatorClassName?upper_case}_H
#define ${creatorClassName?upper_case}_H

#include "${productInterfaceName}.h"

class ${creatorClassName} {
public:
    virtual ~${creatorClassName}() = default;
    virtual ${productInterfaceName}* ${creatorMethodName}() = 0;

    void someOperation() {
        ${productInterfaceName}* product = ${creatorMethodName}();
        product->${productMethodName}();
        delete product;
    }
};

#endif
