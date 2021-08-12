package hello.springcore.lifecycle;


import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

// implements InitializingBean, DisposableBean 초기화, 소멸 인터페이스
//  - 단점 : 스프링 의존, 초기화, 소멸 메서드의 이름을 변경할 수 없음, 코드를 고칠 수 없는 외부 라이브러리에 적용할 수 없음
//  > 초창기에 나온 방법으로 잘 사용하지 않음.

// @PostConstruct, @PreDestory 권장하는 방법
// 스프링에 종속적인 기술이 아니라 자바 표준이다.
// 유일한 단점은 외부라이브러리에는 적용하지 못한다는 것 > 외부 라이브러리를 초기화, 종료 해야하면 @Bean의 (initMethod, destoryMethod)기능을 사용아자
public class NetworkClient  {

    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //서비스 시작시 호출
    public void connect() {
        System.out.println("connect: " + url);
    }

    public void call(String message) {
        System.out.println("call: " + url + " message = " + message);
    }

    //서비스 종료시 호출
    public void disconnect() {
        System.out.println("close: " + url);
    }

    @PostConstruct
    public void init() {
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메시지");
    }

    @PreDestroy
    public void close()  {
        System.out.println("NetworkClient.close");
        disconnect();
    }

    // 스프링의 의존관계 주입 종료 후
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        System.out.println("NetworkClient.afterPropertiesSet");
//        connect();
//        call("초기화 연결 메시지");
//    }
//
//    @Override
//    public void destroy() throws Exception {
//        System.out.println("NetworkClient.destroy");
//        disconnect();
//    }
}
