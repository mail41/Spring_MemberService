스프링 빈 설정 메타 정보 - BeanDefinition
=====================================

* 스프링 컨테이너는 BeanDefinition을 가지고 동작을 하고, 의존한다.
* 스프링 컨네이너는 이 메타정보를 기반으로 스프링 빈을 생성한다.
* @Bean, <bean> 당 각각 하나씩 메타 정보가 생성된다.
* AppConfig.class, AppConfig.xml 은 BeanDefinition에 의존한다.

***

ApplicationContext
===================

* AnnotationConfigApplicationContext는 ApplicationContext를 의존하는데,
  AnnotatedBeanDefinitionReader를 사용해서 AppConfig.class를 읽고 BeanDefinition을 생성(빈 메타정보 생성) 한다.

  (AppConfig에 생성된 Bean 설정정보를 읽어 들인다.)

