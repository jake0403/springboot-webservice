package springboot.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

//	@Getter : 선언된 필드의 get 메서드를 생성
//	@RequiredArgsConstructor : final로 선언된 필드가 포함된 생성자를 생성
//  예) public HelloResponseDto(String name, int amount) { ... }
@Getter
@RequiredArgsConstructor
public class HelloResponseDto {
	private final String name;
	private final int amount;
}
