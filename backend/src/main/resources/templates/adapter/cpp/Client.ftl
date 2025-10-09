#include <iostream>
#include "${adapterClassName}.h"

int main() {
    ${targetInterfaceName}* target = new ${adapterClassName}();
    target->request();
    delete target;
    return 0;
}
