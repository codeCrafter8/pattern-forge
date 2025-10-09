import java.util.Stack;

public class ${caretakerClassName} {
    private final Stack<${mementoClassName}> mementoStack = new Stack<>();

    public void saveState(${originatorClassName} originator) {
        mementoStack.push(originator.save());
    }

    <#if undoEnabled>
    public void undo(${originatorClassName} originator) {
        if (!mementoStack.isEmpty()) {
            originator.restore(mementoStack.pop());
        }
    }

    public boolean canUndo() {
        return !mementoStack.isEmpty();
    }
    </#if>
}
