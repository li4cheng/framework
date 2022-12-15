package com.my.framework.customConfig.stringFormat;

import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Parser;
import org.springframework.format.Printer;

import java.util.HashSet;
import java.util.Set;

public class StringFormatFactory implements AnnotationFormatterFactory<StringFormat> {

    @Override
    public Set<Class<?>> getFieldTypes() {
        Set<Class<?>> set = new HashSet<>();
        set.add(String.class);
        return set;
    }

    @Override
    public Printer<?> getPrinter(StringFormat annotation, Class<?> fieldType) {
        return getFormatter(annotation);
    }

    @Override
    public Parser<?> getParser(StringFormat annotation, Class<?> fieldType) {
        return getFormatter(annotation);
    }

    private StringFormatter getFormatter(StringFormat stringFormat) {
        StringFormatter formatter = new StringFormatter();
        formatter.setFormatType(stringFormat.formatType());
        return formatter;
    }
}
