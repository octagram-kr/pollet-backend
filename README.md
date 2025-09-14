# 📋 Pollet

## 🙌🏻 멤버
<table>
  <tbody>
    <tr>
      <td align="center">
        <a href="https://github.com/Friox"><img src="https://avatars.githubusercontent.com/u/10986386?v=4" width="100px;" alt="이승훈"/><br /></a>
      </td>
      <td align="center">
        <a href="https://github.com/Bogeun-Kim"><img src="https://avatars.githubusercontent.com/u/83561045?v=4" width="100px;" alt="김보근"/><br /></a>
      </td>
      <td align="center">
        <a href="https://github.com/sseen2"><img src="https://avatars.githubusercontent.com/u/59137639?v=4" width="100px;" alt="김세은"/><br /></a>
      </td>
    </tr>
    <tr>
      <td align="center"><a href="https://github.com/Friox">이승훈</a></td>
      <td align="center"><a href="https://github.com/Bogeun-Kim">김보근</a></td>
      <td align="center"><a href="https://github.com/sseen2">김세은</a></td>
    </tr>
  </tbody>
</table>

## 📖 프로젝트 개요
**Pollet** 은 아이디어가 자유롭게 검증되고, 참여 응답을 통해 보상을 받을 수 있는 플랫폼으로,  
누구나 설문조사, AB테스트, 수요조사 등 다양한 사용자 리서치를 연계하여 등록하고 검증받을 수 있습니다.

### 📅 진행 기간
- 메인 : 2025. 08. 11. ~ 2025. 09. 14.
- 지속 개선 : 2025. 09. 15. ~

### 🎯 핵심 목표
- **각 파트 간 크로스펑셔널 협업 경험 강화**
- **모든 파트의 결과물이 포트폴리오로 활용 가능한 수준으로 제작**
- **핵심 기능이 실제 동작하는 MVP 구현**

## 🔧 기술 스택
### Backend
[![backend](https://skillicons.dev/icons?i=java,spring,redis,mysql,gradle)](https://skillicons.dev)
- **Language** : Java
- **Framework** : Spring Boot, Spring Security, Spring Batch
- **ORM** : Spring Data JPA, QueryDSL
- **DB** : MySQL (Production), H2 (Test)
- **Cache** : Redis
- **Authentication** : JWT
- **Build Tool** : Gradle

### Infrastructure & DevOps
[![infra,devops](https://skillicons.dev/icons?i=git,github,docker,aws,githubactions)](https://skillicons.dev)
- **Cloud Platform** : AWS (ECS(Fargate), RDS, ElastiCache, Load Balancer)
- **Container** : Docker
- **CI/CD** : GitHub Actions
- **VCS** : Git, GitHub

### Testing
- **Framework** : JUnit, Spring Boot Test
- **DB** : H2 (in-memory)
- **Test Utils** : AssertJ
- **Coverage** : JaCoCo

## 🏗️ 아키텍처
<img width="1644" height="990" alt="01" src="https://github.com/user-attachments/assets/8649c876-e3c8-45c1-a226-48de363821d0" />

### 도메인 주도 설계 (DDD)
```
src/main/java/com/octagram/pollet/
├── auth/           # 인증 도메인
├── gifticon/       # 기프티콘 도메인
├── global/         # 공통 기능
├── member/         # 회원 도메인
├── notification/   # 알림 도메인
└── survey/         # 설문조사 도메인
```

## 🚀 주요 특징
### 🔑 Auth 도메인
- **JWT 기반 인증 시스템** : Access Token과 Refresh Token을 활용한 Stateless 인증 및 인가
- **보안 강화** : 로그아웃된 토큰 Redis 관리, 쿠키 기반 Refresh Token 관리
- **OAuth2** : Google, Kakao OAuth2를 이용한 로그인

### 📋 Survey 도메인
- **설문조사, 문항, 문항 선택지** : 별개로 구분된 엔티티로 통계 조회 효율성 극대화
- **응답 정보, 문항 별 응답, 문항 별 선택지 응답** : 설문에 대한 응답을 기록
- **동시성 제어** : 설문 응답과 포인트 기록에 비관적 락을 적용하여 동시성 제어

## 📊 운영
- `GitHub Actions`를 통한 애플리케이션 빌드, `ECR` 업로드
- `AWS ECS` 기반으로, 서버리스 서비스인 `Fargate` 사용
- 여러 `Fargate` 컨테이너로 분산된 백엔드로 구성되어 고가용성 확보
- `RDS`, `ALB` 등 `AWS 서비스`를 사용하여 데이터 관리와 트래픽 분산 및 도메인 운영 지원