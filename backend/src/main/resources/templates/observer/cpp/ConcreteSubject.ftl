#include "${subjectInterfaceName}.h"
#include "${observerInterfaceName}.h"
#include <algorithm>

class ${concreteSubjectClassName} : public ${subjectInterfaceName} {
private:
    std::vector<std::shared_ptr<${observerInterfaceName}>> observers;

public:
    void registerObserver(std::shared_ptr<${observerInterfaceName}> observer) override {
        observers.push_back(observer);
    }

    void removeObserver(std::shared_ptr<${observerInterfaceName}> observer) override {
        observers.erase(std::remove(observers.begin(), observers.end(), observer), observers.end());
    }

    void notifyObservers() override {
        for (auto& observer : observers) {
            observer->${updateMethodName}();
        }
    }
};
