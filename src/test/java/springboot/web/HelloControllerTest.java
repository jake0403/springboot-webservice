package springboot.web;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import springboot.config.auth.SecurityConfig;

// 스프링부트 테스트와 JUnit 사이에 연결자 역할
@RunWith(SpringRunner.class)
// @Controller, @ControllerAdvice 와 같은 애노테이션을 사용할 수 있도록 지원
@WebMvcTest(controllers = HelloController.class, 
	excludeFilters = {
		@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)
	}
)
public class HelloControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@WithMockUser(roles = "USER")
	@Test
	public void hello_리턴된다() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/hello"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().string("hello"));
	}
	
	@WithMockUser(roles = "USER")
	@Test
	public void helloDto가_리턴된다() throws Exception {
		final String name = "hello";
		final int amount = 1000;
		
		mockMvc.perform(MockMvcRequestBuilders.get("/hello/dto").param("name", name).param("amount", String.valueOf(amount)))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is(name)))
		.andExpect(MockMvcResultMatchers.jsonPath("$.amount", Matchers.is(amount)));
	}

}
