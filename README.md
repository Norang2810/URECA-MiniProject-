유레카에서 진행한 개인 미니프로젝트

랭킹관리 시스템 JDBC + SWING 사용해서 CRUD 구현

ERD
<img width="954" height="1176" alt="image" src="https://github.com/user-attachments/assets/22b8bfc3-d59c-4974-a3a8-08d96ecd3659" />
테이블 생성 SQL문
  ```sql
CREATE TABLE player (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE game (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    genre VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE ranking (
    id INT AUTO_INCREMENT PRIMARY KEY,
    player_id INT NOT NULL,
    game_id INT NOT NULL,
    score INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

