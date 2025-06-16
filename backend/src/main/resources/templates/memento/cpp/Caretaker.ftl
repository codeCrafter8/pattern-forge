#ifndef ${caretakerClassName?upper_case}_H
#define ${caretakerClassName?upper_case}_H

#include <stack>
#include "${mementoClassName}.h"
#include "${originatorClassName}.h"

class ${caretakerClassName} {
private:
    std::stack<${mementoClassName}> mementoStack;

public:
    void saveState(const ${originatorClassName}& originator) {
        mementoStack.push(originator.save());
    }

    <#if undoEnabled>
    void undo(${originatorClassName}& originator) {
        if (!mementoStack.empty()) {
            originator.restore(mementoStack.top());
            mementoStack.pop();
        }
    }

    bool canUndo() const {
        return !mementoStack.empty();
    }
    </#if>
};

#endif // ${caretakerClassName?upper_case}_H
