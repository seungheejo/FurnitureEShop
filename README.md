# FurnitureEShop
회원가입 및 로그인, 상품의 재고조회, 장바구니, 게시판 기능이 있는 온라인 가구 쇼핑몰

## 프로젝트 개요
지금까지 배운 Oracle 데이터베이스 환경, Tomcat의 서버 환경을 구축하고, Java, JavaScript, Oracle database query, C tag, JQuery 등의 프로그래밍 언어를 이용해서 
Create, Read, Update, Delete 기능을 적용하여 프로젝트를 만들어 지금까지의 학습내용을 점검해 보기 위해 가구 쇼핑몰을 만들어 보았다.

## 프로젝트 기능
* 회원가입, 회원정보수정, 회원탈퇴
* 로그인, 로그아웃, 인터셉터를 이용한 로그인
게시판에서 글쓰기 버튼을 클릭할 때, 제품 상세 페이지에서 주문하기나 장바구니에 담기를 클릭할 때 인터셉터가 실행되어 로그인이 되어있지 않으면 로그인 페이지로 이동한다.
* 게시판 글쓰기, 읽기, 삭제하기
* 주문하기
주문하면 주문한 만큼의 수량이 product 데이터베이스의 수량에서 마이너스 된다.
* 장바구니 
제품을 장바구니에 담으면 데이터베이스에 저장되고, 장바구니에서 물품을 삭제하면 데이터베이스에서 삭제된다.
* 제품재고확인
제품 번호와 매장을 선택하면 데이터베이스에서 재고정보를 가져와서 보여준다.
* 부트스트랩
부트스트랩을 사용하여 메인 화면의 메뉴를 구성했고, 페이지에서 상위로 올라가는 기능과 게시판 및 로그인, 회원가입 폼을 만들었다.