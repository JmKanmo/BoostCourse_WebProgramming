<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
<meta charset="utf-8">
<meta name="description"
	content="네이버 예약, 네이버 예약이 연동된 곳 어디서나 바로 예약하고, 네이버 예약 홈(나의예약)에서 모두 관리할 수 있습니다.">
<meta name="viewport"
	content="width=device-width,initial-scale=1,maximum-scale=1,minimum-scale=1,user-scalable=no">
<title>메인페이지</title>
<link href="./resources/css/style.css?ver=1.1" rel="stylesheet">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.7.3/handlebars.min.js"
	integrity="sha256-/PJBs6QWvXijOFIX04kZpLb6ZtSQckdOIavLWKKOgXU="
	crossorigin="anonymous"></script>
</head>

<body>
	<div id="container">
		<!-- 헤더영역 -->
		<div class="header">
			<header class="header_tit">
			<h1 class="logo">
				<a class="lnk_logo" title="네이버"> <span class="spr_bi ico_n_logo">네이버</span>
				</a> <a class="lnk_logo" title="예약"> <span
					class="spr_bi ico_bk_logo">예약</span>
				</a>
			</h1>
			<a class="btn_my"> <span title="예약확인">nebi25@naver</span>
			</a> </header>
		</div>
		<hr>
		<div class="event">
			<div id="top" class="section_visual">
				<div class="group_visual">
					<div class="container_visual">
						<div class="prev_e" style="display: none;">
							<div class="prev_inn">
								<a href="#" class="btn_pre_e" title="이전"> <i
									class="spr_book_event spr_event_pre">이전</i>
								</a>
							</div>
						</div>
						<div class="nxt_e" style="display: none;">
							<div class="nxt_inn">
								<a href="#" class="btn_nxt_e" title="다음"> <i
									class="spr_book_event spr_event_nxt">다음</i>
								</a>
							</div>
						</div>
						<div>
							<!-- 프로모션박스 -->
							<div class="container_visual">
								<!-- 슬라이딩기능: 이미지 (type = 'th')를 순차적으로 노출 -->
								<ul class="visual_img">

								</ul>
							</div>
							<span class="nxt_fix" style="display: none;"></span>
						</div>
					</div>
				</div>
			</div>
			<!-- 카테고리 탭 -->
			<div class="section_event_tab">
				<ul class="event_tab_lst tab_lst_min">
					<li class="item" data-category="0"><a class="anchor active">
							<span>전체리스트</span>
					</a></li>
				</ul>
			</div>
			<!-- 상품리스트 -->
			<div class="section_event_lst">
				<p class="event_lst_txt">
					바로 예매 가능한 행사가 <span class="pink"></span> 있습니다
				</p>
				<!-- 상품정보박스 -->
				<div class="wrap_event_box">
					<!-- 더보기 -->
					<div class="more">
						<button class="btn">
							<span>더보기</span>
						</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 푸터영역 -->
	<footer>
	<div class="gototop">
		<a href="#top" class="lnk_top"> <span class="lnk_top_text">TOP</span>
		</a>
	</div>
	<div class="footer">
		<p class="dsc_footer">네이버(주)는 통신판매의 당사자가 아니며, 상품의정보, 거래조건, 이용 및 환불
			등과 관련한 의무와 책임은 각 회원에게 있습니다.</p>
		<span class="copyright">© NAVER Corp.</span>
	</div>
	</footer>

	<script type="text/javascript"
		src="./resources/js/mainpage.js?ver=20200305"></script>
</body>

<!-- 이미지 프로모션 템플릿 -->
<script type="text/template" id="template-promotion">
	{{#promotions}}	
	<li class="item"> 
		<img src = {{saveFileName}} style = "width: 100%; height: 100%;" alt = ""> 
	</li>
	{{/promotions}}
</script>

<!-- 카테고리 탭 템플릿-->
<script type="text/template" id="template-category-tab">
	{{#categories}}
	<li class="item" data-category="{{id}}">
		<a class="anchor">
		<span>{{name}}</span>
		</a>
	</li>
	{{/categories}}
</script>

<!-- 상품정보카드 템플릿-->
<script type="text/template" id="template-product-list">
	<ul class="lst_event_box">
		{{#products}}
		<li class="item">
			<a href="/reservation/detail?id={{id}}&displayInfoId={{displayInfoId}}" class="item_book">
				<div class="item_preview"> 
					<img alt="" class="img_thumb" src= {{saveFileName}}>
					<span class="img_border"></span>
				</div>
	
				<div class="event_txt">
					<h4 class="event_txt_tit">
						<span>{{description}}</span> 
						<small class="sm">{{placeName}}</small>
					</h4>
					<p class="event_txt_dsc">{{content}}</p>
				</div>
			</a>
		</li>
		{{/products}}
	</ul>
</script>
</html>