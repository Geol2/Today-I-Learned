# 웹 개발자를 위한 대규모 서비스를 지탱하는 기술
- 사고 방식과 요령, 알고리즘이나 데이터 구조에서 대규모 데이터
- RDBMS로 다룰 수 없는 데이터를 처리하는 방법 및 검색 엔진
- 대규모 서비스가 될 것을 전제로 한 서버/인프라 시스템

  - 2~5장 : 대규모 데이터를 다루는 애플리케이션 개발에 필요한 개념, OS 캐시 개념, MySQL을 대규모 환경에서 운용할 때 주의점
  - 6~10장 : 구현단계, 알고리즘, 검색 엔진 개발에 대한 내용에 대한 설명
  - Apache 및 MySQL 설정, 프로그래밍 언어에 대한 설명, 기본적인 알고리즘 및 데이터 구조에 대해선 다루지 않는다.

[Chapter 3] OS 캐시와 분산 
- OS의 캐시 구조에 대한 이해
- 가상메모리 구조
- Linux의 페이지 캐시 원리
- VFS 가상파일시스템
- 국소성을 고려한 분산 : 액세스 패턴 고려
- 파티셔닝을 통한 분산

[Chpater 4] 분산을 고려한 MySQL 운용
- 인덱스의 중요성 : B+트리 구조
- 