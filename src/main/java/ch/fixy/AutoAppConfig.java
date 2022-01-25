package ch.fixy;

import ch.fixy.repository.MemberRepository;
import ch.fixy.repository.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = "ch.fixy.member",
        basePackageClasses = AutoAppConfig.class ,
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
/*
    @ComponentScan : Component가 붙은 bean을 자동으로 등록해준다, AppConfig를 제외하기 위해 excludeFilters를 사용
                    보통 설정 정보를 컴포넌트 스캔 대상에서 제외하지는 않지만, 기존 예제 코드를 최대한 남기고 유지하기 위해서 이 방법을 선택

    basePackages : 탐색할 패키지의 시작 위치를 지정한다.

    basePackageClasses : 지정한 클래스의 패키지를 탐색 시작 위로 지정한다.
                     만약 지정하지 않으면 @ComponentScan이 붙은 설정 정보 클래스의 패키지가 시작 위치가 된다.

    *권장하는 방법*
    패키지 위치를 지정하지 않고, 설정 정보 클래스의 위치를 프로젝트 최상단에 두는 것이다. 최근 스프링 부트도 이 방법을 기본으로 제공한다.
    ch.fixy -> 프로젝트 시작 루트, 여기에 AppConfig 같은 메인 설정 정보를 두고, @ComponentScan 어노테이션을 붙이고, basePackages 지정은 생략한다.
              프로젝트 메인 설정 정보는 프로젝트를 대표하는 정보이기 때문에 프로젝트 시작 루트 위치에 두는 것이 좋다 생각한다.
              스프링 부트의 대표 시작 정보인 @SpringBootApplication을 이 프로젝트 시작 루트 위치에 두는 것이 관례이다.(이 설정안에 @ComponentScan이 들어있다)

    참고 : useDefaultFilters 옵션은 기본으로 켜져있는데, 이 옵션을 끄면 기본 스캔 대상들이 제외된다.
 */

public class AutoAppConfig {

    /*
     memoryMemberRepository 동일한 이름의 빈이 있지만, 수동 빈 등록이 우선권을 가진다.
     Overriding bean definition for bean 'memoryMemberRepository' with a different definition

     스프링 부트는 수동빈과 자동 빈 등록이 충돌나면 오류가 발생하도록 기본값이 바뀌었다.
     ->Consider renaming one of the beans or enabling overriding by setting spring.main.allow-bean-definition-overriding=true
     */
    @Bean(name = "memoryMemberRepository")
    MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

}
