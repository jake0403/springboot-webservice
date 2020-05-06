package springboot.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import springboot.domain.posts.Posts;
import springboot.domain.posts.PostsRepository;
import springboot.web.dto.PostsListResponseDto;
import springboot.web.dto.PostsResponseDto;
import springboot.web.dto.PostsSaveRequestDto;
import springboot.web.dto.PostsUpdateRequestDto;

@RequiredArgsConstructor
@Service
public class PostsService {

	private final PostsRepository postsRepository;
	
	@Transactional
	public void delete(Long id) {
		postsRepository.deleteById(id);
	}

	
	@Transactional(readOnly = true)
	public List<PostsListResponseDto> findAllDesc() {
		/* 옛날 방식 */
		/*
		List results = new ArrayList();
		List<Posts> list = postsRepository.findAllDesc();
		for (int i = 0; i < list.size(); i++) {
			Posts post = list.get(i);
			PostsListResponseDto dto = new PostsListResponseDto(post);
			results.add(dto);
		}
		return results;
		*/
		
		return postsRepository.findAllDesc().stream().map(posts -> new PostsListResponseDto(posts)).collect(Collectors.toList());
		/* 한번 더 축약 */
		// return postsRepository.findAllDesc().stream().map(PostsListResponseDto::new).collect(Collectors.toList());
	}

	@Transactional
	public Long save(PostsSaveRequestDto requestDto) {
		return postsRepository.save(requestDto.toEntity()).getId();
	}

	public PostsResponseDto findById(Long id) {
		Optional<Posts> optional = postsRepository.findById(id);
		if (optional.isPresent()) {
			Posts entity = optional.get();
			return new PostsResponseDto(entity);
		} else {
			throw new IllegalArgumentException("일치하는 정보가 존재하지 않습니다.");
		}
	}

	@Transactional
	public Long update(Long id, PostsUpdateRequestDto requestDto) {
		Optional<Posts> optional = postsRepository.findById(id);
		if (optional.isPresent()) {
			Posts entity = optional.get();
			entity.update(requestDto.getTitle(), requestDto.getContent());
			return postsRepository.save(entity).getId();
		} else {
			throw new IllegalArgumentException("일치하는 정보가 존재하지 않습니다.");
		}
	}
	
}
