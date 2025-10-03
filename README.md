# 유레카에서 진행한 개인 미니프로젝트

### 랭킹관리 시스템 JDBC + SWING 사용해서 CRUD 구현  


## 테이블 생성 SQL문  
  ```sql
CREATE TABLE players (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE games (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    genre VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE game_rankings (
    id INT AUTO_INCREMENT PRIMARY KEY,
    player_id INT NOT NULL,
    game_id INT NOT NULL,
    score INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP

    -- FK 제약조건
    ADD CONSTRAINT fk_ranking_player FOREIGN KEY (player_id)
    REFERENCES players(player_id) ON DELETE CASCADE;

    ADD CONSTRAINT fk_ranking_game FOREIGN KEY (game_id)
    REFERENCES games(game_id) ON DELETE CASCADE;

);
```

## ERD & 프로젝트 구조

<p align="center">
  <img width="454" height="500" alt="ERD" src="https://github.com/user-attachments/assets/2e29c3f2-c782-4641-a7f5-0112779767cd" />
  <img width="454" height="500" alt="프로젝트 구조" src="https://github.com/user-attachments/assets/6b1ffc1d-7e95-4f1f-b1d4-784329fa8781" />
</p>  

## 데이터 흐름  
사용자가 Swing 화면(View)에서 입력/버튼 클릭  
Controller가 이벤트를 받아 Service 호출  
Service가 로직 검증 후 DAO 호출  
DAO가 MySQL에 SQL 실행 → DTO 객체 반환  
View -> 결과 표시  

## 주요 기능 
Player / Game / Ranking CRUD 기능
플레이어별 기록 조회 및 수정
전체 조회 및 검색 기능
랭킹 Top10 조회  
DB 제약조건(FK, CASCADE) 활용  
DAO/Service 분리로 객체지향적 설계  
싱글톤 패턴 적용 (DBManager.java)  

## 인덱스 기반 쿼리 최적화 
1000건의 데이터 -> 100만건 데이터 
=> EXPALAIN 결과를 확인해보고

<img width="454" height="151" alt="image" src="https://github.com/user-attachments/assets/294857ae-1a21-4667-a00d-eb7924456741" />


```sql
CREATE INDEX idx_score ON game_rankings(score);
```

<img width="1696" height="216" alt="image" src="https://github.com/user-attachments/assets/4c9eb3fe-a405-437c-beca-8dc6f864703b" />  

인덱스 추가 → type= ALL -> range scan 으로 바뀌고, rows **998700 ->189106**  수치 확 줄어듦 → 성능 체감

<img width="1681" height="471" alt="image" src="https://github.com/user-attachments/assets/a0866f07-282b-4bf9-87dd-c37132b073b7" />


## 실행 화면  

<p align="center">
  <img src="https://github.com/user-attachments/assets/707659d4-0ba0-4592-a2f1-2ef95bba5f52" width="45%" />
  <img src="https://github.com/user-attachments/assets/2125612e-6a85-4ba7-b375-b930536e6603" width="45%" />
</p>

<p align="center">
  <img src="https://github.com/user-attachments/assets/42a4b992-b071-4525-b3d2-2553b6efff86" width="45%" />
  <img src="https://github.com/user-attachments/assets/2ce8da8e-8c2f-4f1f-bfec-d660496211e9" width="45%" />
</p>


## Game20 인 게임 -> 랭킹 TOP10 조회
<img width="500" height="500" alt="image" src="https://github.com/user-attachments/assets/682038d4-5a09-4173-9213-e80067715fa7" />
