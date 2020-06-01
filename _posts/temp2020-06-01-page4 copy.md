---
title: 스프링 - IoC Container - 3
tags: SPRING5 SPRING 스프링 IoC
key: ANT 기초 - 3
---

# IoC Container

스프링에서 IoC Container는 자바환경에서 스프링의 핵심 기능중에 하나입니다.

이 컨테이너는 스프링이 관리하는 빈(오브젝트)에서 부터 애플리케이션에 이르기까지 전반적인 관리를 담당합니다.

스프링의 IoC Container 종류를 확인하기 전 먼저 IoC(Inversion of Control)이 먼저 왜 필요한지에 관하여 간단히 다루어 본 다음

스프링에서 사용하는 IoC 종류를 설명하고 포스팅을 마치도록 하겠습니다.

## IoC?

IoC는 Inversion of Control 즉 제어의 역전이라는 뜻을 가지고 있습니다.

제어의 역전이라면 어떤 것을 역으로 제어 한다는 말일까요?

바로 모든 것이라고 할수 있습니다.

### 준비

먼저 아래와 같이 총알(Bullet) 클래스가 있다고 가정하겠습니다.

{% highlight java %}
interface Bullet {

    boolean nextBullet();
}
{% endhighlight %}

이 총알을 사용하는 클래스 총(Gun)이 생겼습니다.

{% highlight java %}
class Gun {

    public shoot() {
        // 소스 작성 영역
    }
}
{% endhighlight %}

이제부터 총과 총알의 관계를 설정해 보도록 하겠습니다.

{% highlight java %}
class Gun {

    Bullet bullet;

    public shoot() {
        // 소스 작성 영역
    }
}
{% endhighlight %}

이렇게 되면 총과 총알의 관계가 아래와 같이 설정 되었습니다.

{% highlight note %}
Gun > Bullet
{% endhighlight %}

자 이제는 좀더 들어가서 Bullet을 구현한 SmallBullet을 생성하겠습니다.

{% highlight java %}
class SmallBullet implements Bullet {

    public boolean nextBullet() {
        return true;
    }
}
{% endhighlight %}

자 준비는 끝났습니다.

그렇다면 이제 정방향 제어와 역방향 제어를 살펴 보겠습니다.

#### 정반향 제어

정방향 제어는 일반적인 제어문이라고 보셔도 무방합니다.

총(Gun)이 총알(Bullet)의 메시지를 교환하고 교환된 결과값을 사용하여 제어를 이어가는 상황을 우리는 정방향 제어라고 합니다.

정방향 제어일 경우의 소프트웨어의 복잡도는 내려가며 해당 제어 구문에서의 결과값을 즉시 확인 할수 있다는 장점을 가지고 있습니다.

아래와 같이 말입니다.

{% highlight java %}
class Gun {

    Bullet bullet;

    public shoot() {
        bullet = new SmallBullet();

        // 결과 값을 확인하여 후 처리 실행
        if(bullet.nextBullet()) {
            System.out.println("Pang ~!")
        } else {
            System.out.println("Ping ~ ~ ~")
        }
    }
}
{% endhighlight %}

