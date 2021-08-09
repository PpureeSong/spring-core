package hello.springcore;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;


// ComponentScan : 설정 정보가 없어도 자동으로 스프링 빈을 등록하는 기능
//  - @Component 애노테이션이 붙은 클래스를 스캔해서 스프링 빈으로 등록
@Configuration
@ComponentScan(
        // 지정하지 않으면 @ComponentScan이 붙은 설정 정보 클래스의 패키지가 시작 위치가 된다
        // 패키지 위치를 지정하지 않고, 설정 정보 클래스의 위치를 프로젝트 최상단에 둔다.
        // 스프링 부트의 대표 시작 정보인 @SpringBootApplication를 프로젝트 시작 루트 위치에 두는 것이 관례 (@ComponentScan이 들어있음)
        // basePackages = "hello.springcore",
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

}
