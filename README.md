# 밑바닥부터 만드는 컴퓨팅 시스템 프로젝트

[도서 링크](https://product.kyobobook.co.kr/detail/S000001033078) 

#### 1장 불 논리
* 간단한 불 논리 칩 구현
    * Not, And, Or, Xor, 멀티플렉서, 디멀티플렉서
    * 위 칩들의 멀티비트, 다입력 버전

#### 2장 불 연산
* 산술 칩 구현
    * 반가산기, 전가산기, 16비트 가산기, 증분기, ALU

#### 3장 순차 논리
* 순차 칩 구현
    * 1비트 레지스터, 16비트 레지스터, RAMn(n=8, 64, 512, 4K, 16K), 계수기(프로그램 카운터)

#### 4장 기계어
* 어셈블리 언어로 간단한 프로그램 작성

#### 5장 컴퓨터 아키텍처
* 메모리 칩 구현
    * RAM16K, Screen, Keyboard 칩 사용
* CPU 구현
    * ALU, 레지스터를 적절한 제어 논리로 연결
* 명령어 메모리는 내장 ROM32K 칩 사용
* 최종 컴퓨터 칩 구현
    * 앞의 메모리, CPU, 명령어 메모리 칩 사용

#### 6장 어셈블러
* 프로그래밍 언어를 선택하여 어셈블러 2단계로 구현 (자바 선택)
    * 기호 처리 기능이 없는 어셈블러, 기호 처리 기능 추가한 어셈블러 순으로 구현

#### 7장 가상 머신 1: 스택 산술
* 가상 머신(VM) 일부 구현
    * 가상 머신을 구현하는 것은 VM 코드를 대상 플랫폼 명령어로 번역하는 번역기를 만드는 것과 동치
    * 산술 및 논리 명령, 메모리 접근 명령을 핵 플랫폼 어셈블리 언어로 번역

#### 8장 가상 머신 2: 프로그램 제어
* 가상 머신 모든 기능 구현
    * 프로그램 흐름 제어 명령, 함수 호출 명령을 핵 플랫폼 어셈블리 언어로 번역