#ifndef ${subjectInterfaceName?upper_case}_H
#define ${subjectInterfaceName?upper_case}_H

#include <vector>
#include <memory>

class ${observerInterfaceName};

class ${subjectInterfaceName} {
public:
    virtual ~${subjectInterfaceName}() = default;

    virtual void registerObserver(std::shared_ptr<${observerInterfaceName}> observer) = 0;
    virtual void removeObserver(std::shared_ptr<${observerInterfaceName}> observer) = 0;
    virtual void notifyObservers() = 0;
};

#endif // ${subjectInterfaceName?upper_case}_H
