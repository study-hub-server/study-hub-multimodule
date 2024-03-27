package com.example.core.study.dto.response;

import com.example.core.study.dao.StudyApplyDaoByUserId;
import com.example.core.study.dto.data.ApplyData;
import lombok.Getter;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import static com.example.core.common.util.Converter.convertDaoToData;

@Getter
public class FindMyApplyResponse {

    private final Long totalCount;
    private final Slice<ApplyData> applyData;

    public FindMyApplyResponse(Long totalCount, Slice<StudyApplyDaoByUserId> dao) {
        this.totalCount = totalCount;
        this.applyData = new SliceImpl<>(convertDaoToData(dao, ApplyData::new));
    }
}
