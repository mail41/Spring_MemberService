조회 대상 빈이 2개 이상일 때 해결 방법
================================

* @Autowired 필드 명 매칭 
    * @Autowired는 타입 매칭을 시도하고, 이때 여러 빈이 있으면 필드 이름, 파라미터 이름으로 빈이름을 추가 매칭한다.
    * 필드명을 빈 이름으로 변경하면 된다.
    * 타입 매칭의 결과가 2개 이상일 때 필드 명, 파라미터 명으로 빈 이름 매칭
* @Qualifier : @Quilifier끼리 매칭 -> 빈 이름 매칭 -> 그래도 없으면? NoSuchBeanDefinitionException 예외 발생
    * @Quilifier는 추가 구분자를 붙여주는 방법이다. 주입시 추가적인 방법을 제공하는 것이지 빈 이름을 변경하는 것은 아니다.
    * 단점은 주입 받을 때 모은 코드에  @Quilifier를 붙여주어야 한다는 점이다.
* @Primary 사용
    * @Primary는 우선순위를 정하는 방법이다. @Autowired 시에 여러 빈이 매칭되면 @Primary가 우선권을 가진다.


*