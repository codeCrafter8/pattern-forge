package com.example.patternforge.generator.context;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "patternName"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = SingletonContext.class, name = "singleton"),
        @JsonSubTypes.Type(value = AdapterContext.class, name = "adapter")
})
public interface CodeGenerationContext {
}
