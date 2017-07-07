package com.fasterxml.jackson.datatype.guava;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeBindings;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.type.TypeModifier;
import com.google.common.collect.Multimap;

import java.lang.reflect.Type;

public class MultimapTypeModifier extends TypeModifier
{
    @Override
    public JavaType modifyType(JavaType type, Type jdkType, TypeBindings context, TypeFactory typeFactory) {
        if (Multimap.class.isAssignableFrom(type.getRawClass()))
        {
            JavaType keyType = type.containedType(0);
            JavaType contentType = type.containedType(1);

            if (keyType == null)
            {
                keyType = typeFactory.constructType(String.class);
            }

            if (contentType == null)
            {
                contentType = typeFactory.constructType(Object.class);
            }

            return typeFactory.constructMapLikeType(type.getRawClass(), keyType, contentType);
        }

        return type;
    }

}
