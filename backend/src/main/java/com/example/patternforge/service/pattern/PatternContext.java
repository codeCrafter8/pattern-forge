package com.example.patternforge.service.pattern;

import com.example.patternforge.service.pattern.adapter.AdapterContext;
import com.example.patternforge.service.pattern.factorymethod.FactoryMethodContext;
import com.example.patternforge.service.pattern.singleton.SingletonContext;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "patternName"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = SingletonContext.class, name = "singleton"),
        @JsonSubTypes.Type(value = AdapterContext.class, name = "adapter"),
        @JsonSubTypes.Type(value = FactoryMethodContext.class, name = "factory method")
})
public interface PatternContext {
}
