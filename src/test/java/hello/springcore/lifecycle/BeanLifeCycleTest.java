package hello.springcore.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


// 스프링 라이프사이클 : 객체 생성 -> 의존관계 주입
// 스프링 빈의 이벤트 라이프사이클 : 스프링 컨테이너 생성 > 스프링 빈 생성 > 의존관계 주입 > 초기화 콜백 > 사용 > 소멸전 콜백 > 스프링 종료
public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest() {

        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close();
    }


   @Configuration
    static class LifeCycleConfig {

        // 설정 정보 사용하여 빈 등록 초기화, 소멸
        //   - 장점 : 메서드 이름을 자유롭게 줄 수 있음, 스프링 빈이 스프링 코드에 의존하지 않음.
        //           코드가 아니라 설정정보를 사용하기 때문에 코드를 고칠 수 없는 외부 라이브러리에도 초기화, 종료 메서드를 적용 할 수 있음
        // @Bean의 destoryMethod 기본값 = "(inferred)" (추론) : close, shutdown 이름의 메서드를 자동으로 호출
        // @Bean(initMethod = "init", destroyMethod = "close")
        @Bean
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }
}
