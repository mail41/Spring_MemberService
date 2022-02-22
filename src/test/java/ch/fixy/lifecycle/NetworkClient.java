package ch.fixy.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

// InitializingBean, DisposableBean -> 오래되서 현재는 사용하지 않음
public class NetworkClient implements InitializingBean, DisposableBean  {

    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
        // 생성자 부분에서는 url이 없고, 객체를 생성한 다음에 외부에서 수정자 주입을 통해서 setUrl()이 호출되어야 url이 존재하게 된다.
        // 스프링 빈은 간단하게 "객체 생성" -> "의존관계 주입" 이러한 라이프사이클을 가진다. 이 과정이 끝난 다음에야 필요한 데이터를 사용할 수 있는 준비가 완료된다.
        /*
            스프링 빈의 이벤트 라이프사이클
            스프링 컨테이너 생성 -> 스프링 빈 생성 -> 의존관계 주입 -> 초기화 콜백(빈이 생성되고, 빈의 의존관계 주입이 완료된 후 호출)
             -> 사용 -> 소멸전 콜백(빈이 소멸되기 직전에 호출) -> 스프링 종료
         */
        connect();
        call("초기화 연결 메시지");
    }

    public void setUrl(String url) {
        this.url = url;
    }

    // 서비스 시작시 호출
    public void connect() {
        System.out.println("connect = " + url);
    }

    public void call(String message) {
        System.out.println("call :  = " + url + " message = " + message);
    }

    // 서비스 종료시 호출
    public void disconnect() {
        System.out.println("close = " + url);
    }

    // 초기화 인터페이스 InitializingBean
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("NetworkClient.afterPropertiesSet");
        connect();
        call("초기화 연결 메시지");
    }

    // 소멸 인터페이스 DisposableBean
    @Override
    public void destroy() throws Exception {
        System.out.println("NetworkClient.destroy");
        disconnect();
    }
}
