package com.example.core.bookmark.service;

import com.example.core.bookmark.domain.BookmarkEntity;
import com.example.core.bookmark.repository.BookmarkRepository;
import com.example.core.study.service.StudyValidator;
import com.example.core.user.service.UserValidater;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.atomic.AtomicBoolean;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookmarkService {

    private final BookmarkRepository bookmarkRepository;
    private final UserValidater userValidater;
    private final StudyValidator studyValidator;

    @Transactional
    public boolean doBookMark(Long userId, Long studyId) {
        AtomicBoolean created = new AtomicBoolean(false);
        userValidater.validateExistUserId(userId);
        studyValidator.validateStudyExist(studyId);
        bookmarkRepository.findByUserIdAndStudyId(userId, studyId).ifPresentOrElse(
                bookMark -> {
                    bookmarkRepository.delete(bookMark);
                    created.set(false);
                },
                () -> {
                    BookmarkEntity bookmark = new BookmarkEntity(studyId, userId);
                    bookmarkRepository.save(bookmark);
                    created.set(true);
                }
        );
        return created.get();
    }

    public boolean checkBookmarked(Long userId, Long studyId) {
        userValidater.validateExistUserId(userId);
        studyValidator.validateStudyExist(studyId);
        return bookmarkRepository.findByUserIdAndStudyId(userId, studyId).isPresent();
    }

    public Long getBookmarkCountByUserId(Long userId) {
        return bookmarkRepository.countByUserId(userId);
    }

    @Transactional
    public void deleteAllBookmark(Long userId) {
        userValidater.validateExistUserId(userId);
        bookmarkRepository.deleteAllBookmarksByUserId(userId);
    }
}
