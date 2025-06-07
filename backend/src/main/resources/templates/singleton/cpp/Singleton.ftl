class ${className} {
private:
    static ${className}* instance;
    ${className}() {}
    ${className}(const ${className}&) = delete;
    ${className}& operator=(const ${className}&) = delete;

public:
    static ${className}* getInstance() {
        if (instance == nullptr) {
            instance = new ${className}();
        }
        return instance;
    }
};

${className}* ${className}::instance = nullptr;
