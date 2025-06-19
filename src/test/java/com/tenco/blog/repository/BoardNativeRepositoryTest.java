package com.tenco.blog.repository;


import com.tenco.blog.model.Board;
import com.tenco.blog.model.repasitory.BoardNativeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

// @Import - 테스트에 사용 할 수 있도록 해당 클래스 로드 한다
@Import(BoardNativeRepository.class)
@DataJpaTest // JPA 관련 테스트만 로드 하는 테스트
public class BoardNativeRepositoryTest {

    @Autowired // ID 처리 (의존성 주입)
    private BoardNativeRepository br;

    // DI - 의존성 주입
//    public BoardNativeRepositoryTest(BoardNativeRepository br) {
//        this.br = br;
//    }
    @Test
    public void findAll_test() {
        // given - 테스트를 위한 준비 단계
        // 계시글 목록 조회 정상 작동 하는지 확인 --> data.sql 파일에 데이터 이미 준비

        // when - 실제 테스트를 하는 행위 (전체 게시글 목록 조회)
        List<Board> boardList = br.findAll();

        // then : 결과 검증 (예상하는대로 동작하는지 검증)

        Assertions.assertThat(boardList.size()).isEqualTo(4);
        Assertions.assertThat(boardList.get(3).getUsername()).isEqualTo("ssar");
    }

}
