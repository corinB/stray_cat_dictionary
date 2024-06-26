package com.example.butler.controller;

import com.example.butler.dto.PostDto;
import com.example.butler.dto.UserDto;
import com.example.butler.service.PostService;
import com.example.butler.service.UserService;
import com.example.butler.util.ImgUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/page")
@Slf4j
public class PageController {
    private final PostService postService;
    private final ImgUtil imgUtil;
    private final UserService userService;
    @GetMapping("/post/dto")
    public String postDto() {
        return "PostTest";
    }


//    @PostMapping("/post/dto/test")
//    public String postTest(@ModelAttribute PostDto postDto, Model model) {
//        postService.writePost(postDto);
//        log.info("{}", postDto);
//        model.addAttribute("dto", postDto);
//        return "PostDtoPage";
//    }

    /**
     * 새 게시물을 작성하는 POST 요청을 처리합니다.
     *
     * @param postDto 게시물 세부 정보를 포함하는 데이터 전송 객체
     * @param postImgFiles 업로드된 이미지 파일들
     * @param model 뷰에서 사용할 속성을 추가할 모델
     * @return 렌더링할 뷰의 이름
     */
    @PostMapping("/post/dto/test")
    public String postTest(@ModelAttribute PostDto postDto,
                           @RequestParam("postImgFiles") List<MultipartFile> postImgFiles,
                           Model model) {
        var result = postService.writePost(postDto, postImgFiles);
        log.info("PostDto 수신: {}", postDto);
        model.addAttribute("dto", result);
        return "PostDtoPage";
    }

    @GetMapping("/post/img/{img_path}")
    public ResponseEntity<byte[]> postImg(@PathVariable("img_path") String imgPath) throws IOException {
        return imgUtil.getImgStream(imgPath);
    }

    @GetMapping("/map")
    public String map() {
        return "Map";
    }

//    @PostMapping("/map/check")
//    public String mapCheck(@ModelAttribute PostDto.LatLonDto latLonDto, Model model) {
//        log.info("{}", latLonDto);
//        model.addAttribute("dto", latLonDto);
//        return "test";
//    }

    @GetMapping("/home")
    public String home(@PageableDefault(page = 0,size = 10, sort = "updateAt", direction = Sort.Direction.DESC)
                           Pageable pageable, Model model) {
        var page = postService.getPage(pageable);
        model.addAttribute("dtoList", page);
        return "landingPage";
    }

    @GetMapping("/user/singin")
    public String singIn() {
        return "SingIn";
    }

    @GetMapping("/user/singin/go")
    public String singUp(@RequestParam("nick") String nick,
                         @RequestParam("email") String email,
                         @RequestParam("password") String password) {

        if (!userService.emailCheck(email)){
            return "EmailError";
        }
        var user = UserDto.builder()
                .nick(nick)
                .email(email)
                .password(password)
                .build();
        if (userService.singIn(user)) {
            return "landingPage";
        }
        return "SingInError";
    }

    @GetMapping("/user/login")
    public String login() {
        return "Login";
    }

    @GetMapping("/user/login/go")
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password,
                        @PageableDefault(page = 0,size = 10, sort = "updateAt", direction = Sort.Direction.DESC)
                            Pageable pageable, Model model) {
        if (userService.findMe(email, password) == null) {
            return "SingInError";
        }
        var page = postService.getPage(pageable);
        model.addAttribute("dtoList", page);
        return "landingPage";
    }
}
