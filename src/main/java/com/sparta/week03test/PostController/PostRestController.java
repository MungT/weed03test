package com.sparta.week02test.PostController;

import com.sparta.week02test.PostRepository.PostRepository;
import com.sparta.week02test.PostService.PostService;
import com.sparta.week02test.domain.Post;
import com.sparta.week02test.domain.PostRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor // final로 선언된 멤버 변수를 자동으로 생성합니다.
@RestController // JSON으로 데이터를 주고받음을 선언합니다.
public class PostRestController {

    private final PostRepository postRepository;
    private final PostService postService;

    @GetMapping("/api/post")
    public List<Post> readPost() {
        return postRepository.findAllByOrderByCreatedAtDesc();
    }

    @PostMapping("/api/post")
    public Post createPost(@RequestBody PostRequestDto requestDto) {
        Post post = new Post(requestDto);
        return postRepository.save(post);
    }

    @GetMapping("/api/post/{id}")
    public List<Post> inquiryPost(@PathVariable Long id) {
        return postRepository.findAllById(Collections.singleton(id));
    }

    @PutMapping("/api/post/{id}") // (/api/post/{id}?password="password")
    public Long updatePost(@PathVariable Long id, @RequestParam String password, @RequestBody PostRequestDto requestDto) {
        if (postService.Pwcheck(id, password)) {
            postService.update(id, requestDto);
        } else {
            System.out.println("비밀번호가 다릅니다.");
        }
            return id;
    }

    @DeleteMapping("/api/post/{id}")
    public Long deletePost(@PathVariable Long id, @RequestParam String password) {
        if (postService.Pwcheck(id, password)) {
            postRepository.deleteById(id);
        } else {
           System.out.println("비밀번호가 다릅니다.");
        }
        return id;
    }

}