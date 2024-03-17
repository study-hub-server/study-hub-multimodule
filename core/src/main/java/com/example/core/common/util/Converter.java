package com.example.core.common.util;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Converter {

    public static <T> Slice<T> toSlice(Pageable pageable, List<T> items) {
        if (items.size() > pageable.getPageSize()) {
            items.remove(items.size() - 1);
            return new SliceImpl<>(items, pageable, true);
        }
        return new SliceImpl<>(items, pageable, false);
    }

    public static <T, V> List<T> convertDaoToData(Slice<V> studyDaoByInquiry, Function<V, T> converter) {
        List<T> data = new ArrayList<>();
        for (V dao : studyDaoByInquiry) {
            T inquiry = converter.apply(dao);
            data.add(inquiry);
        }
        return data;
    }
}