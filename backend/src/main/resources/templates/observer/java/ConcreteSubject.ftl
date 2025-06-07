import java.util.ArrayList;
import java.util.List;

public class ${concreteSubjectClassName} implements ${subjectInterfaceName} {
    private final List<${observerInterfaceName}> observers = new ArrayList<>();

    @Override
    public void registerObserver(${observerInterfaceName} observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(${observerInterfaceName} observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (${observerInterfaceName} observer : observers) {
            observer.${updateMethodName}();
        }
    }
}
