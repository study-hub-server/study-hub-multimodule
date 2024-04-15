package com.example.webclient.api.user;

import com.example.core.user.service.*;
import com.example.webclient.api.user.dto.common.UserId;
import com.example.webclient.api.user.dto.request.*;
import com.example.webclient.api.user.dto.response.JwtResponseDto;
import com.example.webclient.api.user.dto.response.UserDetailResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;
    private final UserUpdateService userUpdateService;
    private final UserValidater userValidater;

    @Operation(summary = "회원가입")
    @PostMapping("/v1/users/signup")
    public ResponseEntity<HttpStatus> registerUser(@Valid @RequestBody SignUpRequest request) {
        userService.registerUser(request.toService());
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "로그인", description = "바디에 {email, password} 를 json 형식으로 보내주시면 됩니다. " +
            "email 은 꼭 inu email 형식으로 보내주셔야 합니다")
    @PostMapping("/v1/users/login")
    public ResponseEntity<JwtResponseDto> login(@RequestBody SignInRequest signInRequest) {
        System.out.println("들어왔어");
        JwtResponseDto jwtResponseDto = new JwtResponseDto(userService.loginUser(signInRequest.toSignInData()));
        return ResponseEntity.ok(jwtResponseDto);
    }

    @Operation(summary = "회원 단건 조회(상세정보)", description = "Http 헤더에 JWT를 보내주세요. 마이페이지에 사용됩니다.")
    @GetMapping("/v1/users")
    public ResponseEntity<UserDetailResponse> getUser(UserId userId) {
        System.out.println("아이디 : " + userId.getId());
        UserDetailResponse userDetailResponse = new UserDetailResponse(userService.getUser(userId.getId()));
        return ResponseEntity.ok(userDetailResponse);
    }

    @Operation(summary = "닉네임 수정", description = "Http 헤더에 JWT를 보내주세요.")
    @PutMapping("/v1/users/nickname")
    public ResponseEntity<HttpStatus> updateNickname(@Valid @RequestBody UpdateNicknameRequest request, UserId userId) {
        userUpdateService.updateNickname(request.toService(userId.getId()));
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "학과 수정", description = "Http 헤더에 JWT를 보내주세요.")
    @PutMapping("/v1/users/major")
    public ResponseEntity<HttpStatus> updateMajor(@Valid @RequestBody UpdateMajorRequest request, UserId userId) {
        userUpdateService.updateMajor(request.toService(userId.getId()));
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "비밀번호 수정", description = "Http 헤더에 JWT를 보내주세요.")
    @PutMapping("/v2/users/password")
    public ResponseEntity<HttpStatus> updatePassword(@Valid @RequestBody UpdatePasswordRequest request) {
        userUpdateService.updatePassword(request.toService());
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "회원 탈퇴", description = "Http 헤더에 JWT를 보내주세요.")
    @DeleteMapping("/v1/users")
    public ResponseEntity<HttpStatus> deleteUser(UserId userId) {
        userService.deleteUser(userId.getId());
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "비밀번호 검증", description = "Http 헤더에 JWT를 보내주세요.")
    @PostMapping("/v1/users/password/verify")
    public ResponseEntity<HttpStatus> verifyPassword(@RequestBody VerifyPasswordRequest request, UserId userId) {
        userValidater.verifyPassword(userId.getId(), request.getPassword());
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "닉네임 중복 검사")
    @GetMapping("/v1/users/duplication-nickname")
    public ResponseEntity<HttpStatus> nicknameDuplicationValid(@RequestParam String nickname) {
        userValidater.validateExistUserEmail(nickname);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "회원 사진 저장", description = "Http 헤더에 JWT를 보내주세요")
    @PutMapping("/v1/users/image")
    public ResponseEntity<HttpStatus> uploadImage(UserId userId, @RequestPart(name = "image", required = false) MultipartFile image) throws IOException {
        userService.uploadUserImage(userId.getId() ,image);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "회원 사진 삭제", description = "Http 헤더에 JWT를 보내주세요.")
    @DeleteMapping("/v1/users/image")
    public ResponseEntity<HttpStatus> deleteImage(UserId userId) {
        userService.deleteUserImage(userId.getId());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
