package com.sparta.week03test.PostService;

import com.sparta.week03test.PostRepository.PostRepository;
import com.sparta.week03test.domain.Post;
import com.sparta.week03test.Dto.PostRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public Long update(Long id, PostRequestDto requestDto) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        post.update(requestDto);
        return id;
    }

    public boolean Pwcheck(Long id, String password) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        if (Objects.equals(post.getPassword(), password)) {
            return true;
        } else {
            return false;
        }
    }
}
