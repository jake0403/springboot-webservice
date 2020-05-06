package springboot.domain;

import java.time.LocalDateTime;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;

@Getter
// 해당 클래스(BaseTimeEntity)에 정의된 필드(createdDate, modifiedDate)가 
// 해당 클래스를 상속받은 JPA 엔티티 클래스에서 컬럼으로 인식되도록 지정
@MappedSuperclass
// 해당 클래스(BaseTimeEntity)에 Auditing 기능을 포함
@EntityListeners(AuditingEntityListener.class)
public class BaseTimeEntity {
	
	// 엔티티가 생성되어 저장될 때 시간이 자동으로 설정
	@CreatedDate
	private LocalDateTime createdDate;
	
	// 조회한 엔티티의 값을 변경할 때 시간이 자동으로 설정
	@LastModifiedDate
	private LocalDateTime modifiedDate;
}
