package springboot.web.dto;

import lombok.Getter;
import springboot.domain.posts.Posts;

@Getter
public class PostsResponseDto {

	private Long id;
	private String title;
	private String content;
	private String author;
	
      // 생성자의 파라미터로 엔티티 객체가 들어가 있는 이유
      // 레포지터리에서 실행된 쿼리 결과가 DTO로 전달되게 하기 위해서
	public PostsResponseDto(Posts entity) {
		this.id = entity.getId();
		this.title = entity.getTitle();
		this.content = entity.getContent();
		this.author = entity.getAuthor();
	}
	
}
