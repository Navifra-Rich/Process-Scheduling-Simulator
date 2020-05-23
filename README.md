# Process-Scheduling-Simulator
<img src="https://user-images.githubusercontent.com/55074554/82725369-6f93fe80-9d17-11ea-87aa-ee4fe885dd67.png"></img>

Process Scheduling Simulator는(이하 pss) 운영체제가 프로세스에게 자원을 할당하는 방법 중 교육용으로 많이 사용되는 5개의 알고리즘을 사용해서 프로세스를 스케줄링하는 시뮬레이터입니다.

## 1. 시뮬레이터 개요

### 1.1 알고리즘 종류
<img src="https://user-images.githubusercontent.com/55074554/82726378-a91c3800-9d1e-11ea-9a85-6838746bfd42.png"></img>

FCFS, RR, SPN, SRTN, HRRN 5개의 알고리즘을 사용해서 스케줄링 가능

### 1.2 시각화 및 인터페이스
<img src="https://user-images.githubusercontent.com/55074554/82725843-d830aa80-9d1a-11ea-9ed9-4bbfebe3cf08.png"></img>

1. 프로세서 개수를 입력받음  
2. 스케줄링 유형을 선택- 드롭다운 리스트로 출력되는 콤보박스로 구성되어있음 (FCFS, RR, SPN, SRTN, HRRN, COVID)  
  (RR을 선택시, time quantum을 입력하기 위한 별도 패널 출력됨)  
3. Arrival Time, Burst Time을 입력하고 Insert 버튼을 클릭하면 우측 표에 자동으로 입력됨  
  (표에 있는 칸을 더블클릭해주면 표에서도 입력이 가능)  
4. “Simulation Start” 버튼을 눌러주면 하단 인터페이스에 프로세스가 할당되는 과정이 출력되며 표의 W.T, T.T, NTT 부분이 채워짐
5. “Reset” 버튼을 누르면 표와 하단 인터페이스의 내용이 초기화됨  

### 1.4 시스템 스팩

#### 1.4.1 멀티프로세서 지원
<img src="https://user-images.githubusercontent.com/55074554/82725577-da920500-9d18-11ea-9480-97494a26ef55.png"></img>

1개의 코어만을 사용하는 다른 프로젝트들과는 다르게 4개의 코어를 이용한 스케줄 시뮬레이팅 가능

#### 1.4.2 프로세스 한계
<img src= "https://user-images.githubusercontent.com/55074554/82725925-758bde80-9d1b-11ea-926e-8e38f4acf5b3.png"></img>
<img src="https://user-images.githubusercontent.com/55074554/82725963-c7ccff80-9d1b-11ea-9472-57a6e85d4089.png"></img>


최대 프로세서 : 4개  
최대 프로세스 : 15개  
최대 Burst time : 1000sec  
최대 Arrival time : 15000sec  


## 2. 프로젝트 개요

### 2.1 프로젝트 개발 환경

개발 언어  
<img src="https://user-images.githubusercontent.com/55074554/82725606-ff867800-9d18-11ea-9676-d9a636139273.png" width="100" height="100"></img>

팀원 모두에게 친숙하고, Swing 라이브러리를 사용해본 경험이 있어 Java를 개발언어로 결정
시각화는 앞서 말한대로 swing 라이브러리를 사용했고, 그 외에는 유닛 테스트를 위한 JUnit 라이브러리를 사용함

IDE 환경  
<img src="https://user-images.githubusercontent.com/55074554/82725662-6ad04a00-9d19-11ea-996c-6d143f48dd00.png" width="100" height="100"></img>  
무료로 사용이 가능하고, 모든 팀원이 사용 경험이 있는 Eclipse를 사용해서 개발함



### 2.2 이슈트래커를 사용한 체계적인 이슈관리
<img src="https://user-images.githubusercontent.com/55074554/80221717-a5928400-8680-11ea-8f48-0e81136d3de7.png" width="90%"></img>

현업에서도 자주 사용되는 Jira를 사용한 이슈 관리 및 에자일방법론 중 스크럼 방식을 사용해서 프로젝트 진행

### 2.3 Test Driven Development

<img src="https://user-images.githubusercontent.com/55074554/80449138-4ae77980-8959-11ea-9fd9-0618b4ce43e3.png"></img>
주요 메소드마다 단위테스트를 거치며 유지보수 및 리펙토링 효율을 높이기 위해 노력함  
특히 프로세스를 스케줄링 하는 로직은 이 프로그램의 핵심 기능이므로, 프로젝트 완료 때까지 계속 

### 2.4 Program Structure & Functional Specification

#### 2.4.1 Usecase Diagram

<img src="https://user-images.githubusercontent.com/55074554/82726035-648f9d00-9d1c-11ea-8aa3-591c31106e80.png"></img>

 프로그램의 진행에 필수적인 기능의 명세를 확보하기 위해 Usecase Diagram을 활용해서 목록을 작성함  
 
 요구분석 단계에서 필요한 기능을 추출해내기 위해 Usecase Diagram을 활용했습니다. user가 이 프로그램에 요구하는 핵심 기능을 중심으로 기능 수행에 필요한 절차들을 도출해냈습니다. 유저는 프로세스를 입력하고, 스케줄링 알고리즘을 선택하고, 시뮬레이터를 실행시킬 수 있어야 합니다. 그리고 이 기능을 위해 필요한 과정을 생각하며 useCase Diagram을 완성시켰습니다.  
 reset 버튼에는 초기화 시키는 것 뿐만 아니라 진행되던 쓰레드를 정지시키는 기능도 포함되어야 한다고 생각했고, 프로세서의 개수를 정하는 Set ProcessorNum 기능은 디폴트로 프로세서의 개수를 1개로 지정해서 extend로 작성해도 괜찮겠다는 의견이 있었습니다.

#### 2.4.2 Class Diagram

<img src="https://user-images.githubusercontent.com/55074554/82726131-15963780-9d1d-11ea-8083-363038b9d5a5.png"></img>

 설계 단계에서는 전체 구조를 파악하고, 클래스간 상호작용의 의존도를 파악하기 위해 Class Diagram을 활용함
 
 Scheduling, UI, Controller를 각각 Model, View, Controller로 사용해서 MVC패턴으로 구조를 확정했다.  
 물론 이 프로젝트는 출력되는 화면이 메인화면 하나 뿐이기 때문에 뷰와 모델간의 의존성이 높지 않았지만, 확장성을 대비하고 중간 매개체인 Controller 클래스를 활용해서 인터페이스와 비즈니스 로직을 구분해서 뷰와 모델아 서로에게 끼치는 영향을 최소화 하고싶었다.
