# WiseCard API

WiseCard API는 카드 혜택 및 프로모션 정보를 제공하는 REST API 서버입니다. 사용자가 보유한 카드에 대한 최적의 혜택을 조회하고, 위치 기반 오프라인 매장 정보를 제공합니다.

## 목차

- [프로젝트 개요](#프로젝트-개요)
- [기술 스택](#기술-스택)
- [시스템 요구사항](#시스템-요구사항)
- [빌드 방법](#빌드-방법)
- [설치 방법](#설치-방법)
- [실행 방법](#실행-방법)
- [테스트 방법](#테스트-방법)
- [API 문서](#api-문서)
- [데이터베이스](#데이터베이스)
- [사용된 오픈소스](#사용된-오픈소스)
- [프로젝트 구조](#프로젝트-구조)

## 프로젝트 개요

WiseCard API는 다음과 같은 기능을 제공합니다:

- **카드 정보 조회**: 전체 카드 목록 조회 및 필터링
- **내 카드 조회**: 사용자가 보유한 카드에 대한 혜택 조회
- **오프라인 혜택 조회**: 위치 기반 오프라인 매장에서 사용 가능한 카드 혜택 조회
- **온라인 혜택 조회**: 온라인 쇼핑몰에서 사용 가능한 카드 혜택 조회
- **프로모션 정보 조회**: 카드사별 프로모션 정보 조회
- **gRPC 서비스**: 카드 데이터 및 프로모션 데이터 저장 서비스

## 기술 스택

- **언어**: Kotlin 1.9.25
- **프레임워크**: Spring Boot 3.5.7
- **빌드 도구**: Gradle 8.10.2
- **Java 버전**: JDK 21
- **데이터베이스**: MongoDB
- **통신 프로토콜**: 
  - REST API (HTTP)
  - gRPC (Protocol Buffers)
- **외부 API**: Kakao Map API
- **문서화**: Swagger (SpringDoc OpenAPI)

## 시스템 요구사항

- **JDK**: 21 이상
- **Gradle**: 8.10.2 이상 (또는 Gradle Wrapper 사용)
- **MongoDB**: MongoDB Atlas 또는 로컬 MongoDB 인스턴스
- **Docker** (선택사항): 컨테이너 기반 실행 시

## 빌드 방법

### 1. 저장소 클론

```bash
git clone https://github.com/CardMates/WiseCard_Back.git
cd wisecard-api
```

### 2. 환경 변수 설정

`src/main/resources/application.yml` 파일을 확인하거나 다음 환경 변수를 설정합니다:

readme.install 파일 참고

### 3. Gradle을 사용한 빌드

#### Gradle Wrapper 사용 (권장)

```bash
# Linux/Mac
./gradlew clean build

# Windows
gradlew.bat clean build
```

#### 로컬 Gradle 사용

```bash
gradle clean build
```

### 4. JAR 파일 생성

```bash
./gradlew bootJar
```

빌드된 JAR 파일은 `build/libs/wisecard-api-0.0.1-SNAPSHOT.jar`에 생성됩니다.

## 설치 방법

### 방법 1: JAR 파일 직접 실행

1. 빌드된 JAR 파일 실행:
```bash
java -jar build/libs/wisecard-api-0.0.1-SNAPSHOT.jar
```

2. 환경 변수와 함께 실행:
```bash
MONGODB_URI="your-mongodb-uri" \
SERVER_PORT=8081 \
GRPC_PORT=50052 \
LMM_API_KEY="your-kakao-api-key" \
java -jar build/libs/wisecard-api-0.0.1-SNAPSHOT.jar
```

### 방법 2: Docker를 사용한 실행

```bash
docker run -d \
  -p 8081:8081 \
  -p 50052:50052 \
  -e MONGODB_URI="your-mongodb-uri" \
  -e SERVER_PORT=8081 \
  -e GRPC_PORT=50052 \
  -e LMM_API_KEY="your-kakao-api-key" \
  wisecard-api:latest
```

### 방법 3: Gradle을 통한 직접 실행

```bash
./gradlew bootRun
```

## 실행 방법

애플리케이션이 성공적으로 시작되면 다음 포트에서 서비스가 제공됩니다:

- **REST API**: http://localhost:8081
- **gRPC 서버**: localhost:50052
- **Swagger UI**: http://localhost:8081/swagger-ui.html

### 실행 확인

```bash
open http://localhost:8081/swagger-ui.html
```

## 테스트 방법

### 단위 테스트 실행

```bash
./gradlew test
```

### 테스트 리포트 확인

테스트 리포트는 `build/reports/tests/test/index.html`에서 확인할 수 있습니다.

### API 테스트 예제

#### 1. 전체 카드 조회
```bash
curl http://localhost:8081/api/v1/cards
```

#### 2. 카드사별 필터링
```bash
curl "http://localhost:8081/api/v1/cards?cardCompany=SAMSUNG"
```

#### 3. 내 카드 조회
```bash
curl -X POST http://localhost:8081/api/v1/cards/my \
  -H "Content-Type: application/json" \
  -d '{"cardIds": [1, 2, 3]}'
```

#### 4. 오프라인 혜택 조회
```bash
curl -X POST "http://localhost:8081/api/v1/cards/offline?x=127.0276&y=37.4979&query=카페" \
  -H "Content-Type: application/json" \
  -d '{"cardIds": [1, 2]}'
```

#### 5. 프로모션 조회
```bash
curl http://localhost:8081/api/v1/promotions
```

## API 문서

Swagger UI를 통해 API 문서를 확인할 수 있습니다:

- **Swagger UI**: http://localhost:8081/swagger-ui.html
- **OpenAPI JSON**: http://localhost:8081/v3/api-docs

## 데이터베이스

### MongoDB 설정

이 프로젝트는 MongoDB를 데이터 저장소로 사용합니다.


## 사용된 오픈소스

이 프로젝트에서 사용된 주요 오픈소스 라이브러리:

### 프레임워크 및 라이브러리

1. **Spring Boot** (3.5.7)
   - 라이선스: Apache License 2.0
   - 용도: 웹 애플리케이션 프레임워크
   - 링크: https://spring.io/projects/spring-boot

2. **Kotlin** (1.9.25)
   - 라이선스: Apache License 2.0
   - 용도: 프로그래밍 언어
   - 링크: https://kotlinlang.org/

3. **gRPC** (1.63.0)
   - 라이선스: Apache License 2.0
   - 용도: RPC 프레임워크
   - 링크: https://grpc.io/

4. **Protocol Buffers** (3.25.3)
   - 라이선스: BSD 3-Clause License
   - 용도: 데이터 직렬화
   - 링크: https://developers.google.com/protocol-buffers

5. **Spring Data MongoDB**
   - 라이선스: Apache License 2.0
   - 용도: MongoDB 통합
   - 링크: https://spring.io/projects/spring-data-mongodb

6. **SpringDoc OpenAPI** (2.8.14)
   - 라이선스: Apache License 2.0
   - 용도: API 문서화
   - 링크: https://springdoc.org/

7. **Jackson Kotlin Module**
   - 라이선스: Apache License 2.0
   - 용도: JSON 직렬화/역직렬화
   - 링크: https://github.com/FasterXML/jackson-module-kotlin

### 테스트 라이브러리

1. **JUnit 5**
   - 라이선스: Eclipse Public License 2.0
   - 용도: 단위 테스트

2. **MockK** (1.13.13)
   - 라이선스: Apache License 2.0
   - 용도: Kotlin 모킹 라이브러리

3. **AssertJ** (3.26.3)
   - 라이선스: Apache License 2.0
   - 용도: 테스트 어설션

### 외부 API

1. **Kakao Map API**
   - 용도: 위치 기반 장소 검색
   - 링크: https://developers.kakao.com/docs/latest/ko/local/dev-guide

## 프로젝트 구조

```
wisecard-api/
├── src/
│   ├── main/
│   │   ├── kotlin/
│   │   │   └── com/wisecard/api/
│   │   │       ├── api/              # REST API 컨트롤러
│   │   │       ├── application/      # 애플리케이션 서비스
│   │   │       ├── domain/           # 도메인 모델 및 서비스
│   │   │       ├── infra/            # 인프라스트럭처 (MongoDB, gRPC, 외부 API)
│   │   │       └── core/             # 공통 설정 및 예외 처리
│   │   ├── proto/                    # Protocol Buffer 정의 파일
│   │   └── resources/
│   │       └── application.yml       # 애플리케이션 설정
│   └── test/                         # 테스트 코드
├── build.gradle.kts                  # Gradle 빌드 설정
├── settings.gradle.kts               # Gradle 프로젝트 설정
├── Dockerfile                        # Docker 이미지 빌드 파일
└── README.md                         # 프로젝트 문서
```