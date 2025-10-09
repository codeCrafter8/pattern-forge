public interface ${subjectInterfaceName} {
    void registerObserver(${observerInterfaceName} observer);
    void removeObserver(${observerInterfaceName} observer);
    void notifyObservers();
}
