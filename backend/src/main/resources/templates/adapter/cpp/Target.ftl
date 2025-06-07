#ifndef ${targetInterfaceName?upper_case}_H
#define ${targetInterfaceName?upper_case}_H

class ${targetInterfaceName} {
public:
    virtual ~${targetInterfaceName}() = default;
    virtual void request() = 0;
};

#endif
