#ifndef ${adapterClassName?upper_case}_H
#define ${adapterClassName?upper_case}_H

#include "${adapteeClassName}.h"
#include "${targetInterfaceName}.h"

class ${adapterClassName} : public ${targetInterfaceName} {
private:
    ${adapteeClassName} adaptee;
public:
    void request() override;
};

#endif

void ${adapterClassName}::request() {
    adaptee.${adapteeMethodName}();
}
