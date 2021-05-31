package com.company.nsharova.extractor;

public interface Extractor<E, T> {
    E extractFrom(T source);
}
