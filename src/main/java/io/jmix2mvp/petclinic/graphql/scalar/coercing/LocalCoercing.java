package io.jmix2mvp.petclinic.graphql.scalar.coercing;

import graphql.language.StringValue;
import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;

import java.util.Locale;
import java.util.StringTokenizer;

public class LocalCoercing implements Coercing {
    @Override
    public Object serialize(Object dataFetcherResult) throws CoercingSerializeException {
        StringTokenizer tempStringTokenizer = new StringTokenizer((String) dataFetcherResult,",");
        String locale = "";
        String country = "";
        if(tempStringTokenizer.hasMoreTokens()) {
            locale = (String) tempStringTokenizer.nextElement();
        }
        if(tempStringTokenizer.hasMoreTokens()) {
            country = (String) tempStringTokenizer.nextElement();
        }
        return new Locale(locale, country);
    }

    @Override
    public Object parseValue(Object input) throws CoercingParseValueException {
        return ((Locale) input).toString();
    }

    @Override
    public Object parseLiteral(Object input) throws CoercingParseLiteralException {
        return (input instanceof StringValue) ? parseValue(((StringValue) input).getValue()) : null;
    }
}
