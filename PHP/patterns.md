# MVC 패턴
- CodeIgniter4 게시판을 만들면서 배운 MVC 패턴.

- 모델(Model) : 데이터베이스 쿼리 및 관련 로직을 처리하는 곳
```php
  function getBoardCount() {
    $builder = $this->db->table('board');
    $builder->selectCount('SNO');

    $data = $builder->get()->getResultArray();

    $count = $data[0]['SNO'];
    // $q = $this->db->getLastQuery();

    $this->db->close();
    return $count;
  }
```
- 게시판 데이터베이스의 데이터를 뿌려주기 위해 구현한 함수 예시.


- 컨트롤러(Controller) : 모델과 뷰 사이에 위치하며 뷰에서 사용자의 요청을 받아 반응을 해주거나 추가 모델의 로직을 맞게 처리하여 반응해준다.
```php
public function index()
  {
    // $request = service('request');

    // 게시판 데이터 불러오기
    $RM = new ResourceModel();
    $count = $RM->getBoardCount();
    $pageSize = 5; // 한 페이지당 게시글 수 설정 변수
    $block = 1; // getPage를 기준으러 좌우로 나타낼 개수

    $totalPageTmp = $count / $pageSize;
    $totalPage = ceil($totalPageTmp); // 바인딩할 페이징 계산

    $this->getPage = $this->request->getGet('page');
    if($this->getPage == null) {
      $this->getPage = 1;
    }

    $contentPaging = $RM->setContentPaging($this->getPage, $pageSize); // 각 페이지당 게시글의 수
    $data = $RM->getListBoard($contentPaging, $pageSize); // 한 페이지 당 게시글 데이터 분리

    $result['list'] = $data['list'];        // 게시글 데이터 배열
    $result['count'] = intval($totalPage);  // 총 페이지 카운트 수

    $result['start_page'] = max( $this->getPage - $block, 1 );
    $result['end_page'] = min( $this->getPage + $block, $result['count'] );
    $result['prev_page'] = max( $result['start_page'] - $block - 1, 1 );
    $result['next_page'] = min ( $result['end_page'] + $block + 1, $result['count'] );
    $result['page'] = $this->getPage;

    echo view('/header');
    echo view('/boards/table/table', $result);
    echo view('/boards/table/board');
	}
```
- 해당 URL에서 처리하는 컨트롤러의 일부 함수를 발췌.
- 게시판 데이터를 뷰로 바인딩해주며 페이징 처리를 구현해보았다.

뷰(View) : 사용자의 요청에 따라 컨트롤러와 상호작용을 함.
```php
<section>
  <div class="write_class">
    <p> 게시물 수정 페이지 </p>
    <h1> <?= esc( $SNO, 'html') ?>번 글 수정하기 </h1>
<!--        <form name="contentForm" id="contentForm" method="post" action="/Boards/update/--><?//= esc( $SNO, 'url') ?><!--">-->
      <div>
        <label for="sub"> 제목 </label>
        <input class="sub" type="text" id="sub" name="sub" value="<?= esc( $SUBJECT_NAME, 'html') ?>" maxlength='20' placeholder="제목을 입력하세요. (20글자)">
      </div>
  </div>
</section>

```

[CodeIgniter4 사용자 가이드 (한글)](http://ci4doc.cikorea.net/) 