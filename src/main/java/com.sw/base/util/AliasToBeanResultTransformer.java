package com.sw.base.util;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.property.access.internal.PropertyAccessStrategyBasicImpl;
import org.hibernate.property.access.internal.PropertyAccessStrategyChainedImpl;
import org.hibernate.property.access.internal.PropertyAccessStrategyFieldImpl;
import org.hibernate.property.access.internal.PropertyAccessStrategyMapImpl;
import org.hibernate.property.access.spi.PropertyAccessStrategy;
import org.hibernate.property.access.spi.Setter;
import org.hibernate.transform.AliasedTupleSubsetResultTransformer;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;

/**
 * Result transformer that allows to transform a result to
 * a user specified class which will be populated via setter
 * methods or fields matching the alias names.
 * <p/>
 * <pre>
 * List resultWithAliasedBean = s.createCriteria(Enrolment.class)
 * 			.createAlias("student", "st")
 * 			.createAlias("course", "co")
 * 			.setProjection( Projections.projectionList()
 * 					.add( Projections.property("co.description"), "courseDescription" )
 * 			)
 * 			.setResultTransformer( new AliasToBeanResultTransformer(StudentDTO.class) )
 * 			.list();
 *
 *  StudentDTO dto = (StudentDTO)resultWithAliasedBean.get(0);
 * 	</pre>
 *
 * @author max
 */
public class AliasToBeanResultTransformer extends AliasedTupleSubsetResultTransformer {

    private final Class resultClass;
    private boolean isInitialized;
    private String[] aliases;
    private Setter[] setters;

    public AliasToBeanResultTransformer(Class resultClass) {
        if(resultClass == null) {
            throw new IllegalArgumentException("resultClass cannot be null");
        } else {
            this.isInitialized = false;
            this.resultClass = resultClass;
        }
    }

    public boolean isTransformedValueATupleElement(String[] aliases, int tupleLength) {
        return false;
    }

    public Object transformTuple(Object[] tuple, String[] aliases) {
        try {
            this.aliases = new String[aliases.length];
            for(int i = 0; i < aliases.length; ++i) {
                aliases[i] = StringUtils.toHumpName(aliases[i]);
                if(aliases[i] != null) {
                    this.aliases[i] = aliases[i];
                }
            }
            if(!this.isInitialized) {
                this.initialize(aliases);
            } else {
                this.check(aliases);
            }

            Object result = this.resultClass.newInstance();

            for (int i = 0; i < aliases.length; i++) {
                if (setters[i] != null) {
                    Class<?> aClass = setters[i].getMethod().getParameterTypes()[0];
                    Object temp;
                    if(tuple[i] instanceof BigDecimal) {
                        if(aClass == Integer.class) {
                            temp = ((BigDecimal)tuple[i]).intValue();
                        } else if(aClass == BigInteger.class) {
                            temp = ((BigDecimal)tuple[i]).toBigInteger();
                        } else if(aClass == Double.class) {
                            temp = ((BigDecimal)tuple[i]).doubleValue();
                        } else if(aClass == Float.class) {
                            temp = ((BigDecimal)tuple[i]).floatValue();
                        } else if(aClass == Long.class) {
                            temp = ((BigDecimal)tuple[i]).longValue();
                        } else if(aClass == Short.class) {
                            temp = ((BigDecimal)tuple[i]).shortValue();
                        } else if(aClass == Byte.class) {
                            temp = ((BigDecimal)tuple[i]).byteValue();
                        } else {
                            temp = tuple[i];
                        }
                    } else {
                        temp = tuple[i];
                    }
                    setters[i].set(result, temp, null);
                }
            }

            return result;
        } catch (InstantiationException var5) {
            throw new HibernateException("Could not instantiate resultclass: " + this.resultClass.getName());
        } catch (IllegalAccessException var6) {
            throw new HibernateException("Could not instantiate resultclass: " + this.resultClass.getName());
        }
    }

    private void initialize(String[] aliases) {
        PropertyAccessStrategyChainedImpl propertyAccessStrategy = new PropertyAccessStrategyChainedImpl(new PropertyAccessStrategy[]{PropertyAccessStrategyBasicImpl.INSTANCE, PropertyAccessStrategyFieldImpl.INSTANCE, PropertyAccessStrategyMapImpl.INSTANCE});
        this.setters = new Setter[aliases.length];

        for(int i = 0; i < aliases.length; ++i) {
            String alias = aliases[i];
            if(alias != null) {
                this.setters[i] = propertyAccessStrategy.buildPropertyAccess(this.resultClass, alias).getSetter();
            }
        }

        this.isInitialized = true;
    }

    private void check(String[] aliases) {
        if(!Arrays.equals(aliases, this.aliases)) {
            throw new IllegalStateException("aliases are different from what is cached; aliases=" + Arrays.asList(aliases) + " cached=" + Arrays.asList(this.aliases));
        }
    }

    public boolean equals(Object o) {
        if(this == o) {
            return true;
        } else if(o != null && this.getClass() == o.getClass()) {
            AliasToBeanResultTransformer that = (AliasToBeanResultTransformer)o;
            return !this.resultClass.equals(that.resultClass)?false:Arrays.equals(this.aliases, that.aliases);
        } else {
            return false;
        }
    }

    public int hashCode() {
        int result = this.resultClass.hashCode();
        result = 31 * result + (this.aliases != null?Arrays.hashCode(this.aliases):0);
        return result;
    }
}

