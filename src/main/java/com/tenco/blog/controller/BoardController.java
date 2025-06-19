package com.tenco.blog.controller;

import com.tenco.blog.model.Board;
import com.tenco.blog.model.repasitory.BoardNativeRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller // IoC 대상 - 싱글톤 패턴으로 관리 됨
public class BoardController {

    private BoardNativeRepository boardNativeRepository;

    // DI 의존성 주입 : 스프링이 자동으로 객체를 주입
    public BoardController(BoardNativeRepository boardNativeRepository) {
        this.boardNativeRepository = boardNativeRepository;
    }

    // username, title, content <--- DTO 를 설계해서 받는 방법, 기본 데이터 타입 설정해서 받는 방법
    // form 태그에서 넘어오는 데이터 받기
    // form 태그의 name 속성에 key 값 동일 해야 함
    // http://localhost:8080/board/save
    // 스프링 부트 기본 파싱 전략 - key = value (form)
    @PostMapping("/board/save")
    public String save(@RequestParam("username") String username,
                       @RequestParam("title") String title,
                       @RequestParam("content") String content) {
        System.out.println("title : " + title);
        System.out.println("content : " + content);
        System.out.println("username : " + username);

        boardNativeRepository.save(title,content,username);

        return "redirect:/";
    }


    @GetMapping({"/", "/index"})
    public String index(HttpServletRequest request) {
        // DB 에 접근해서 select 결과값을 받아서 mustache 파일에 데이터 바인딩 처리
        List<Board> boardList = boardNativeRepository.findAll();
        // 뷰에 데이터를 전달 -> Model 사용 가능 public String index(Model model)
        request.setAttribute("boardList",boardList);

        return "index";
    }

    @GetMapping("/board/save-form")
    public String saveForm() {
        // templates / board
        // templates / board /

        return "board/save-form";
    }
    // board/1

    /**
     * 게시물 상세보기 요청
     * board/1
     */
    @GetMapping("/board/{id}")
    public String detail(@PathVariable(name = "id") int id) {
        // URL 에서 받은 id 값을 사용해서 특정 게시글 상세보기 조회
        // 실제로는 이 id 값으로 데이터베이스에 있는 게시글 조회 하고
        // 머스테치 파일로 데이터를 내려 주어야 함 (Model)
        return "board/detail";
    }

}
