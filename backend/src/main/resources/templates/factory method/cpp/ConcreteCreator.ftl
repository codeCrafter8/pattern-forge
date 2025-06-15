#include "${concreteCreatorClassName}.h"
#include "${productClassName}.h"

${productInterfaceName}* ${concreteCreatorClassName}::${creatorMethodName}() {
    return new ${productClassName}();
}
