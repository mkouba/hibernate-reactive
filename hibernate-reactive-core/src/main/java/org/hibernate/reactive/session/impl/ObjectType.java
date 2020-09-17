/* Hibernate, Relational Persistence for Idiomatic Java
 *
 * SPDX-License-Identifier: LGPL-2.1-or-later
 * Copyright: Red Hat Inc. and Hibernate Authors
 */
package org.hibernate.reactive.session.impl;

import org.hibernate.type.AbstractSingleColumnStandardBasicType;
import org.hibernate.type.descriptor.WrapperOptions;
import org.hibernate.type.descriptor.java.JavaTypeDescriptor;
import org.hibernate.type.descriptor.sql.SqlTypeDescriptorRegistry.ObjectSqlTypeDescriptor;

import java.sql.Types;

/**
 * This was a temporary workaround for #336, and could be removed.
 */
class ObjectType extends AbstractSingleColumnStandardBasicType<Object> {
    public ObjectType() {
        super(new ObjectSqlTypeDescriptor(Types.JAVA_OBJECT),
                new JavaTypeDescriptor<Object>() {
                    @Override
                    public Class<Object> getJavaTypeClass() {
                        return Object.class;
                    }

                    @Override
                    public Object fromString(String string) {
                        throw new UnsupportedOperationException();
                    }

                    @Override
                    public <X> X unwrap(Object value, Class<X> type, WrapperOptions options) {
                        return (X) value;
                    }

                    @Override
                    public <X> Object wrap(X value, WrapperOptions options) {
                        return value;
                    }
                }
        );
    }

    @Override
    public String getName() {
        return "object";
    }
}
