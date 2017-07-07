package com.fasterxml.jackson.datatype.guava.deser;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.google.common.collect.ImmutableList;

public class ImmutableListDeserializer extends
        GuavaImmutableCollectionDeserializer<ImmutableList<Object>> {
    public ImmutableListDeserializer(CollectionType type,
            TypeDeserializer typeDeser, JsonDeserializer<?> deser) {
        super(type, typeDeser, deser);
    }

    @Override
    public ImmutableListDeserializer withResolved(TypeDeserializer typeDeser,
            JsonDeserializer<?> valueDeser) {
        return new ImmutableListDeserializer(_containerType, typeDeser,
                valueDeser);
    }

    /*
    /**********************************************************
    /* Deserialization
    /**********************************************************
     */

    @Override
    protected ImmutableList.Builder<Object> createBuilder() {
        ImmutableList.Builder<Object> builder = ImmutableList.builder();
        return builder;
    }
}
