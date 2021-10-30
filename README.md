# :green_heart: 복지 가득한 집, bokzip :green_heart:
접근성 있는 UI/UX 설계를 통해 앱을 이용하여 편리하게 복지 제도를 확인하고, 신청할 수 있도록 사이트로 연결해주는 모바일 어플리케이션
> 개발기간 : 2021. 08. ~ 2021. 10.

캡쳐본

### 시연영상 

## :monocle_face: 구성원
|김은서|김혜정|
|:---:|:---:|
|<img src="https://user-images.githubusercontent.com/68772751/139533586-1edc542b-ab38-4b0e-ad30-8947aff3d70b.png" width="60%" />|<img src="https://user-images.githubusercontent.com/68772751/139533613-e4695172-50b5-4f12-8d39-0dd93de7b774.png" width="60%" />|

## :hammer: 개발환경
- Develop
```
- SpringBoot 2.5.3, 
- PostgreSQL(배포 DB)
- MySQL(테스트 DB)
- Java 11
```
- DevOps
```
- Heroku
- Git Action
```

## :white_check_mark: 핵심 기능
|기능|상세기능|진척도|
|:---:|:---:|:---:|
|**Login**|구글 로그인을 이용한 로그인|✔️|
||로그인된 유저 스크랩 기능 권한 인가|✔️|
|**Main**|중앙부처, 지역 데이터 전체 가져오기|✔️|
||중앙부처, 지역 데이터 id로 가져오기|✔️|
||중앙부처, 지역 데이터 조회수 증가시키기|✔️|
||(필터기능)중앙부처, 지역 데이터 카테고리 가져오기|✔️|
||(필터기능)중앙부처, 지역 데이터 지역 가져오기|✔️|
||(필터기능)중앙부처, 지역데이터 조회순/인기순 정렬하여 가져오기|✔️|
|**Recomand**|일반 데이터 전체 가져오기|✔️|
||일반 데이터 id로 가져오기|✔️|
||일반 데이터 전체 가져오기|✔️|
||일반 데이터 조회순 정렬하여 가져오기|✔️|
||일반 데이터 조회수 증가시키기|✔️|
|**Scrap**|인증된 사용자의 스크랩 목록 가져오기|✔️|
||권한이 있는 사용자 스크랩 요청하기|✔️|
||권한이 있는 사용자 스크랩 삭제하기|✔️|
## 📝 설계 문서

### 0. Convention
- [코드 컨벤션 및 커밋, 브랜치 규칙](https://github.com/bokzip/bokzip-backend/wiki)

### 1. API 

### 2. DB

### 3. Architecture
