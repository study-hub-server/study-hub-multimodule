package com.example.webclient.api.study;

import com.example.core.study.dto.response.*;
import com.example.core.study.service.StudyApplyService;
import com.example.core.study.service.StudyFindService;
import com.example.core.study.service.StudyService;
import com.example.webclient.api.study.dto.request.*;
import com.example.webclient.api.study.resolver.QueryStringArgResolver;
import com.example.webclient.api.user.dto.common.UserId;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class StudyController {

    private final StudyService studyService;
    private final StudyFindService studyFindService;
    private final StudyApplyService studyApplyService;

    @Operation(summary = "스터디 참여 신청", description = "studyId와 신청자가 작성한 글을 json 형태로 입력해주세요.")
    @PostMapping("/v1/study")
    public ResponseEntity<HttpStatus> enrollStudy(UserId userId, EnrollApplyRequest request) {
        studyApplyService.enroll(request.toEnrollApplyData(), userId.getId());
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "스터디 참여 신청 거절", description = "JWT 헤더에 보내주시면 됩니다!!")
    @PutMapping("/v1/study-reject")
    public ResponseEntity<Long> rejectApply(@RequestBody @Valid RejectApplyRequest rejectApplyRequest, UserId userId) {
        studyApplyService.rejectApply(rejectApplyRequest.toRejectEntity(), userId.getId());
        return ResponseEntity.ok().body(userId.getId());
    }

    @Operation(summary = "스터디 참여 신청 거절 사유 조회", description = "JWT와 studyId를 보내주세요.")
    @GetMapping("/v1/reject")
    public ResponseEntity<RejectReasonResponse> enrollStudy(UserId userId, @RequestParam Long studyId) {
        return ResponseEntity.ok(studyApplyService.findRejectReason(userId.getId(), studyId));
    }

    @Operation(summary = "스터디 참여 신청 수락", description = "JWT 헤더에 보내주시면 됩니다!")
    @PutMapping("/v1/study-accept")
    public ResponseEntity<HttpStatus> acceptApply(@RequestBody @Valid AcceptApplyRequest acceptApplyRequest, UserId userId) {
        studyApplyService.acceptApply(acceptApplyRequest.toAcceptApplyData(), userId.getId());
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "회원이 신청한 스터디 정보 조회", description = "해당 스터디 Id, 신청 정보를 파라미터로 보내주세요.")
    @GetMapping("/v1/study-request")
    public FindMyApplyResponse findStudyEnroll(UserId userId, @RequestParam int page, @RequestParam int size) {
        return studyApplyService.findApply(userId.getId(), page, size);
    }

    @Operation(summary = "내가 신청한 스터디 삭제", description = "헤더에 JWT와 함께 studyId를 보내주시면 됩니다.")
    @DeleteMapping("/v1/study/{studyId}")
    public ResponseEntity<HttpStatus> deleteApply(UserId userId, @PathVariable("studyId") Long studyId) {
        studyApplyService.deleteMyStudy(userId.getId(), studyId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "스터디 게시글 생성",
            description = "Http 헤더에 JWT accessToken 과 함께 " +
                    "제목, 내용, 채팅방 URI, 관련 학과, 스터디 정원, 벌금, 필터 성별, 스터디 방식, 스터디 시작 날짜, 스터디 종료 날짜를 Json 형식으로 입력해주세요.")
    @PostMapping("/v1/study-posts")
    public ResponseEntity<Long> createPost(@Valid @RequestBody CreateStudyRequest request, UserId userId) {
        Long postId = studyService.createStudy(request.toCreateStudyDate(), userId.getId());
        return ResponseEntity.ok(postId);
    }

    @Operation(summary = "스터디 게시글 수정", description = "Http 헤더에 JWT accessToken 과 함께 " +
            "게시글 Id, 제목, 내용, 채팅방 URI, 관련 학과, 스터디 정원, 벌금, 필터 성별, 스터디 방식, 스터디 시작 날짜, 스터디 종료 날짜를 Json 형식으로 입력해주세요.")
    @PutMapping("/v1/study-posts")
    public ResponseEntity<Long> updatePost(@Valid @RequestBody UpdateStudyRequest request, UserId userId) {
        Long postId = studyService.updateStudy(request.toService(userId.getId()));
        return ResponseEntity.ok(postId);
    }

    @Operation(summary = "스터디 게시글 삭제", description = "Http 헤더에 JWT accessToken 과 함께 게시글 Id를 보내주시면 됩니다.")
    @DeleteMapping("/v1/study-posts/{postId}")
    public ResponseEntity<HttpStatus> deletePost(@PathVariable("postId") Long postId, UserId userId) {
        studyService.deletePost(postId, userId.getId());
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "내가 북마크한 스터디 조회")
    @GetMapping("/v1/study-posts/bookmarked")
    public ResponseEntity<FindStudyResponseByBookmark> getBookmarkedPosts(@RequestParam int page, @RequestParam int size, UserId userId) {
        FindStudyResponseByBookmark findPostResponseByBookmark = studyFindService.getBookmarkedPosts(page, size, userId.getId());
        return ResponseEntity.ok().body(findPostResponseByBookmark);
    }

    @Operation(summary = "스터디 단건 조회", description = "url 끝에 postId를 넣어주세요")
    @GetMapping("/v2/study-posts/{postId}")
    public ResponseEntity<FindStudyResponseById> findPostById(@PathVariable Long postId, UserId userId) {
        FindStudyResponseById findPostResponseById = studyFindService.findStudyById(postId, userId.getId());
        return ResponseEntity.ok(findPostResponseById);
    }

    @Operation(summary = "내가 작성한 스터디 조회")
    @GetMapping("/v1/study-posts/mypost")
    public ResponseEntity<FindStudyResponseByUserId> getMyPosts(@RequestParam int page, @RequestParam int size, UserId userId) {
        FindStudyResponseByUserId findPostResponseByUserId = studyFindService.getMyPosts(page, size, userId.getId());
        return ResponseEntity.ok(findPostResponseByUserId);
    }

    @Operation(summary = "스터디 전체 조회", description = "parameter 칸에 조회할 내용을 입력해주세요")
    @GetMapping("/v2/study-posts")
    public ResponseEntity<FindStudyResponseByInquiry> findPostByAllString(@QueryStringArgResolver InquiryRequest inquiryRequest, @RequestParam int page, @RequestParam int size, UserId userId) {
        FindStudyResponseByInquiry findPostResponseByInquiries = studyFindService.findPostResponseByInquiry(inquiryRequest.toInquiryData(), page, size, userId.getId());
        return ResponseEntity.ok(findPostResponseByInquiries);
    }

    @Operation(summary = "스터디 마감처리", description = "postId와 헤더에 jwt토큰 보내주시면 됩니다!")
    @PutMapping("/v1/study-posts/{post-id}/close")
    public ResponseEntity<HttpStatus> closePost(@PathVariable("post-id") Long postId, UserId userId) {
        studyService.closePost(postId, userId.getId());
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "회원이 작성한 게시글 전체 삭제", description = "헤더에 userId 보내주시면 됩니다.")
    @DeleteMapping("/v1/all/study-post")
    public ResponseEntity<HttpStatus> deleteAllPosts(final UserId userId) {
        studyService.deleteAllStudyByUserId(userId.getId());
        return ResponseEntity.ok().build();
    }

//    @Operation(summary = "내가 참여한 스터디 목록", description = "헤더에 JWT 보내주시면 됩니다.")
//    @GetMapping("/v1/participated-study")
//    public ResponseEntity<FindParticipateStudyResponse> getParticipateApply(UserId userId, @RequestParam int page, @RequestParam int size) {
//        return ResponseEntity.ok().body(studyApplyService.getParticipateStudy(userId.getId(), page, size));
//    }
}
