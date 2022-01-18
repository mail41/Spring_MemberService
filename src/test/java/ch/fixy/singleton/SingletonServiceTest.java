package ch.fixy.singleton;

public class SingletonServiceTest {

    // 자기 자신을 내부의 private으로 static영역에 instance 딱 하나만 생성되게 한다.
    // 싱글톤 패턴을 적용하면 고객의 요청이 올 때 마다 객체를 생성하는 것이 아니라
    // 이미 만들어진 객체를 공유해서 효율적으로 사용할 수 있다.
    private static final SingletonServiceTest instance = new SingletonServiceTest();

    // 오직 getInstance() 메서드를 통해서만 조회할 수 있다.
    // 이 메서드를 호출하면 항상 같은 인스턴스를 반환한다.
    public static SingletonServiceTest getInstance() {
        return instance;
    }

    // 딱 1개의 객체 인스턴스만 존재해야 하므로,
    // 생성자를 private로 막아서 혹시라도 외부에서 new 키워드로 객체 인스턴스가 생성되는 것을 막는다.
    private SingletonServiceTest() {
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
