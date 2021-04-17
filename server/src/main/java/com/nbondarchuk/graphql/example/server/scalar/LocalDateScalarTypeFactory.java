package com.nbondarchuk.graphql.example.server.scalar;

import graphql.language.StringValue;
import graphql.schema.*;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class LocalDateScalarTypeFactory {

    public static GraphQLScalarType create() {
        return GraphQLScalarType.newScalar()
                .name("Date")
                .description("Java 8 LocalDate as scalar.")
                .coercing(new Coercing<LocalDate, String>() {
                    @Override
                    public String serialize(final Object dataFetcherResult) {
                        if (dataFetcherResult == null) {
                            return "";
                        }

                        if (dataFetcherResult instanceof LocalDate) {
                            return dataFetcherResult.toString();
                        }
                        throw new CoercingSerializeException("Expected a LocalDate object.");
                    }

                    @Override
                    public LocalDate parseValue(final Object input) {
                        if (input == null) {
                            return null;
                        }

                        if (input instanceof String) {
                            if (StringUtils.isEmpty((String) input)) {
                                return null;
                            }
                            try {
                                return LocalDate.parse((String) input);
                            } catch (DateTimeParseException e) {
                                throw new CoercingParseValueException(String.format("Not a valid date: '%s'.", input), e);
                            }
                        }
                        throw new CoercingParseValueException("Expected a String");
                    }

                    @Override
                    public LocalDate parseLiteral(final Object input) {
                        if (input == null) {
                            return null;
                        }

                        if (input instanceof StringValue) {
                            if (StringUtils.isEmpty(((StringValue) input).getValue())) {
                                return null;
                            }
                            try {
                                return LocalDate.parse(((StringValue) input).getValue());
                            } catch (DateTimeParseException e) {
                                throw new CoercingParseLiteralException(e);
                            }
                        }
                        throw new CoercingParseLiteralException("Expected a StringValue.");
                    }
                }).build();
    }
}
