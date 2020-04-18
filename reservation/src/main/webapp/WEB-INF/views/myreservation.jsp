<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="utf-8">
	<meta name="description" content="네이버 예약, 네이버 예약이 연동된 곳 어디서나 바로 예약하고, 네이버 예약 홈(나의예약)에서 모두 관리할 수 있습니다.">
	<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,minimum-scale=1,user-scalable=no">
	<title>예약내역페이지</title>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.7.3/handlebars.min.js"
		integrity="sha256-/PJBs6QWvXijOFIX04kZpLb6ZtSQckdOIavLWKKOgXU=" crossorigin="anonymous"></script>
	<link href="./resources/css/style.css?ver=1.1" rel="stylesheet">
</head>

<body>
	<div id="container">
		<div class="header">
			<header class="header_tit">
				<h1 class="logo">
					<a href="/reservation/main" class="lnk_logo" title="네이버"> <span class="spr_bi ico_n_logo">네이버</span>
					</a> <a class="lnk_logo" title="예약"> <span class="spr_bi ico_bk_logo">예약</span>
					</a>
				</h1>
				<c:choose>
					<c:when test="${sessionScope.email != null}">
						<a class="btn_my" href="/reservation/myreservation?resrv_email=${sessionScope.email}">
							<span title="예약확인">${sessionScope.email}</span>
						</a>
					</c:when>
					<c:otherwise>
						<a class="btn_my" href="#"> <span title="예약확인">내역없음</span>
						</a>
					</c:otherwise>
				</c:choose>
			</header>
		</div>
		<hr>
		<div class="ct">
			<div class="section_my">
				<!-- 예약 현황 -->
				<div class="my_summary" id="top">
					<ul class="summary_board">
						<!-- summary-template -->
					</ul>
				</div>
				<!--// 예약 현황 -->

				<!-- 내 예약 리스트 -->
				<div class="wrap_mylist">
					<ul class="list_cards" ng-if="bookedLists.length > 0" id="total_tag">
						<!-- history-template -->
					</ul>
				</div>
			</div>
		</div>
		<hr>
	</div>
	<footer>
		<div class="gototop">
			<a href="#top" class="lnk_top"> <span class="lnk_top_text">TOP</span>
			</a>
		</div>
		<div id="footer" class="footer">
			<p class="dsc_footer">네이버(주)는 통신판매의 당사자가 아니며, 상품의정보, 거래조건, 이용 및
				환불 등과 관련한 의무와 책임은 각 회원에게 있습니다.</p>
			<span class="copyright">© NAVER Corp.</span>
		</div>
	</footer>

	<!-- 취소 팝업 -->
	<!-- [D] 활성화 display:block, 아니오 버튼 or 닫기 버튼 클릭 시 숨김 display:none; -->
	<div class="popup_booking_wrapper" style="display: none;">
		<!-- popUp-template -->
	</div>
	<!--// 취소 팝업 -->
	<script type="text/javascript" src="./resources/js/myreservationpage.js?ver=1.1"></script>
</body>
<!-- summary template -->
<script type="text/template" id="template-summary">
	<li class="item">
			<!--[D] 선택 후 .on 추가 link_summary_board --> 
			<a href="#total_tag" class="link_summary_board on"> 
				<i class="spr_book2 ico_book2"></i>
				<em class="tit">전체</em> 
				<span class="figure">{{stateType 0}}</span>
			</a>
		</li>

		<li class="item">
			<a href="#confirmed_tag" class="link_summary_board">
				<i class="spr_book2 ico_book_ss"></i> 
				<em class="tit">이용예정</em>
				<span class="figure">{{stateType 2}}</span>
			</a>
		</li>

		<li class="item">
			<a href="#used_tag" class="link_summary_board">
				<i class="spr_book2 ico_check"></i> 
				<em class="tit">이용완료</em> 
				<span class="figure">{{stateType 1}}</span>
			</a>
		</li>

		<li class="item">
			<a href="#canceled_tag" class="link_summary_board">
				<i class="spr_book2 ico_back"></i> 
				<em class="tit">취소·환불</em> 
				<span class="figure">{{stateType 3}}</span>
			</a>
		</li>
</script>

<!-- history template -->
<script type="text/template" id="template-history">
	<!-- 이용예정 history -->
	<li class="card confirmed" id="confirmed_tag">
		<div class="link_booking_details">
			<div class="card_header">
				<div class="left"></div>
				<div class="middle">
					<!--[D] 예약 신청중: .ico_clock, 예약확정&이용완료: .ico_check2, 취소된 예약: .ico_cancel 추가 spr_book -->
					<i class="spr_book2 ico_check2"></i> <span class="tit">예약
						확정</span>
				</div>
				<div class="right"></div>
			</div>
		</div>

		{{#if (isEmpty "scheduledHistory")}}
		<div class="err" style="dis">
			<i class="spr_book ico_info_nolist"></i>
			<h1 class="tit">예약 리스트가 없습니다</h1>
		</div>
		{{else}}
		{{#scheduledHistory}}
		<article class="card_item">
			<a href="#confirmed_tag" onclick="return false" class="link_booking_details">
				<div class="card_body">
					<div class="left"></div>
					<div class="middle">
						<div class="card_detail">
							<em class="booking_number">No.{{reservationId}}</em>
							<h4 class="tit">{{description}}</h4>
							<ul class="detail">
								<li class="item"><span class="item_tit">일정</span>  
									{{splitDate reservationDate}} 예약 </em></li>
								<li class="item"><span class="item_tit">내역</span> <em class="item_dsc">
										{{#ticketHistory}}
										{{convertPriceType priceTypeName}} ({{price}}원) {{count}}장 =>
										{{purchasePrice price count}} 원<br>
										{{/ticketHistory}}
									</em></li>
								<li class="item"><span class="item_tit">장소</span> <em class="item_dsc">
										주소: {{placeStreet}} <br> 지번: {{placeLot}}
									</em></li>
								<li class="item"><span class="item_tit">업체</span> <em class="item_dsc">
										{{placeName}}
									</em></li>
							</ul>

							<div class="price_summary">
								<span class="price_tit">구매티켓 수</span> <em class="price_amount">
									<span>{{ticketCount}}</span> <span class="unit">장</span>
								</em>
							</div>

							<div class="price_summary">
								<span class="price_tit">결제 예정금액</span> <em class="price_amount">
									<span>{{ticketPrice}}</span> <span class="unit">원</span>
								</em>
							</div>
							<!-- [D] 예약 신청중, 예약 확정 만 취소가능, 취소 버튼 클릭 시 취소 팝업 활성화 -->
							<div class="booking_cancel" id="cancelScope" reservationId={{reservationId}}>
								<button class="btn">
									<span>취소</span>
								</button>
							</div>
						</div>
					</div>
					<div class="right"></div>
				</div>
				<div class="card_footer">
					<div class="left"></div>
					<div class="middle"></div>
					<div class="right"></div>
				</div>
			</a> <a href="#" class="fn fn-share1 naver-splugin btn_goto_share" title="공유하기"></a>
		</article>
		{{/scheduledHistory}}
		{{/if}}
	</li>

	<!-- 이용완료 history -->
	<li class="card used" id="used_tag">
		<div class="link_booking_details">
			<div class="card_header">
				<div class="left"></div>
				<div class="middle">
					<!--[D] 예약 신청중: .ico_clock, 예약확정&이용완료: .ico_check2, 취소된 예약: .ico_cancel 추가 spr_book -->
					<i class="spr_book2 ico_check2"></i> <span class="tit">이용
						완료</span>
				</div>
				<div class="right"></div>
			</div>
		</div>

		{{#if (isEmpty "usedHistory")}}
		<div class="err" style="dis">
			<i class="spr_book ico_info_nolist"></i>
			<h1 class="tit">예약 리스트가 없습니다</h1>
		</div>
		{{else}}
		{{#usedHistory}}
		<article class="card_item">
			<a href="#used_tag" class="link_booking_details">
				<div class="card_body">
					<div class="left"></div>
					<div class="middle">
						<div class="card_detail">
							<em class="booking_number">No.{{reservationId}}</em>
							<h4 class="tit">{{description}}</h4>
							<ul class="detail">
								<li class="item"><span class="item_tit">일정</span>  
								{{splitDate reservationDate}} 예약 </em></li>
								<li class="item"><span class="item_tit">내역</span> <em class="item_dsc">
										{{#ticketHistory}}
										{{convertPriceType priceTypeName}} ({{price}}원) {{count}}장 =>
										{{purchasePrice price count}} 원<br>
										{{/ticketHistory}}
									</em></li>
								<li class="item"><span class="item_tit">장소</span> <em class="item_dsc">
										주소: {{placeStreet}} <br> 지번: {{placeLot}}
									</em></li>
								<li class="item"><span class="item_tit">업체</span> <em class="item_dsc">
										{{placeName}}
									</em></li>
							</ul>

							<div class="price_summary">
								<span class="price_tit">구매티켓 수</span> <em class="price_amount">
									<span>{{ticketCount}}</span> <span class="unit">장</span>
								</em>
							</div>

							<div class="price_summary">
								<span class="price_tit">결제 예정금액</span> <em class="price_amount">
									<span>{{ticketPrice}}</span> <span class="unit">원</span>
								</em>
							</div>
							<div class="booking_cancel" id="reviewScope" reservationId={{reservationId}}>
								<a href="./reviewWrite.html"><button class="btn">
										<span>예매자 리뷰 남기기</span>
									</button></a>
							</div>
						</div>
					</div>
					<div class="right"></div>
				</div>
				<div class="card_footer">
					<div class="left"></div>
					<div class="middle"></div>
					<div class="right"></div>
				</div>
			</a>
		</article>
		{{/usedHistory}}
		{{/if}}
	</li>

	<!-- 취소.환불 history -->
	<li class="card used cancel" id="canceled_tag">
		<div class="link_booking_details">
			<div class="card_header">
				<div class="left"></div>
				<div class="middle">
					<!--[D] 예약 신청중: .ico_clock, 예약확정&이용완료: .ico_check2, 취소된 예약: .ico_cancel 추가 spr_book -->
					<i class="spr_book2 ico_cancel"></i> <span class="tit">취소된
						예약</span>
				</div>
				<div class="right"></div>
			</div>
		</div>

		{{#if (isEmpty "canceldHistory")}}
		<div class="err" style="dis">
			<i class="spr_book ico_info_nolist"></i>
			<h1 class="tit">예약 리스트가 없습니다</h1>
		</div>
		{{else}}
		{{#canceldHistory}}
		<article class="card_item">
			<a href="#canceled_tag" class="link_booking_details">
				<div class="card_body">
					<div class="left"></div>
					<div class="middle">
						<div class="card_detail">
							<em class="booking_number">No.{{reservationId}}</em>
							<h4 class="tit">{{description}}</h4>
							<ul class="detail">
								<li class="item"><span class="item_tit">일정</span>  
								{{splitDate reservationDate}} 예약 </em></li>
								<li class="item"><span class="item_tit">내역</span> <em class="item_dsc">
										{{#ticketHistory}}
										{{convertPriceType priceTypeName}} ({{price}}원) {{count}}장 =>
										{{purchasePrice price count}} 원<br>
										{{/ticketHistory}}
									</em></li>
								<li class="item"><span class="item_tit">장소</span> <em class="item_dsc">
										주소: {{placeStreet}} <br> 지번: {{placeLot}}
									</em></li>
								<li class="item"><span class="item_tit">업체</span> <em class="item_dsc">
										{{placeName}}
									</em></li>
							</ul>

							<div class="price_summary">
								<span class="price_tit">구매티켓 수</span> <em class="price_amount">
									<span>{{ticketCount}}</span> <span class="unit">장</span>
								</em>
							</div>

							<div class="price_summary">
								<span class="price_tit">결제 예정금액</span> <em class="price_amount">
									<span>{{ticketPrice}}</span> <span class="unit">원</span>
								</em>
							</div>
						</div>
					</div>
					<div class="right"></div>
				</div>
				<div class="card_footer">
					<div class="left"></div>
					<div class="middle"></div>
					<div class="right"></div>
				</div>
			</a>
		</article>
		{{/canceldHistory}}
		{{/if}}
	</li>
</script>

<!-- popUp template -->
<<script type="text/template" id="template-popup">
	<div class="dimm_dark" style="display: block"></div>
	<div class="popup_booking refund">
		<h1 class="pop_tit">
			<span>{{bookingHistory.description}}</span>
			<small class="sm">예약일: {{splitDate bookingHistory.reservationDate}}</small>
			<small class="sm">구매티켓수: {{bookingHistory.ticketCount}}장</small>
			<small class="sm">구매금액: {{bookingHistory.ticketPrice}}원</small>
		</h1>
		<div class="nomember_alert">
			<p>취소하시겠습니까?</p>
		</div>
		<div class="pop_bottom_btnarea">
			<div class="btn_gray">
				<a href="#" class="btn_bottom"><span>아니오</span></a>
			</div>
			<div class="btn_green" reservationId={{bookingHistory.reservationId}}>
				<a href="#" class="btn_bottom"><span>예</span></a>
			</div>
		</div>
		<!-- 닫기 -->
		<a href="#" class="popup_btn_close" title="close"> <i class="spr_book2 ico_cls"></i>
		</a>
		<!--// 닫기 -->
	</div>
	</script>

</html>