<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript" src="js/jquery-3.6.0.min.js"></script>

<!DOCTYPE html>
<html>
<head>
	<!-- 共通部品の各種定義を呼び出す -->
	<c:import url="definition.jsp" charEncoding="UTF-8" />
	<meta charset="UTF-8">
	<title>SportsMap</title>
</head>
<body>
    <!-- 共通部品のヘッダを呼び出す -->
	<c:import url="header.jsp" charEncoding="UTF-8" />
    <h2>当サイトの強み</h2>
        <h3>1.GoogleMap上で家から近い活動場所を簡単に探せる</h3>
        <figure>
            <img src="" alt="">
            <figcaption>★ここに画像を貼る★</figcaption>
        </figure>
        <h3>2.豊富な検索オプション</h3>
            <ul>
                <li>例1） 大阪府北区で、女性メンバーを募集しているバレーボールサークルを探す</li>
                <li>例2） 東京都台東区で土曜か火曜に活動していて、経験年数3年以上のメンバーを募集しているサークルを探す</li>
            </ul>
        <h3>3.できたてほやほやのサービスなので登録サークルが少ない</h3>
            <p>登録したサークルが検索で常に上位表示されるのでメンバーを集めやすい</p>
    
    <h2>サークルを探す</h2>
        <h3>サークル情報</h3>
        <form action="SearchResult" method="post">
	        <dl>
	            <dt>種目<span class="required">※</span></dt>
	            <dd>
	               <c:import url="sportsList.jsp" charEncoding="UTF-8" />
	            </dd>
	            <dt>活動場所<span class="required">※</span></dt>
	            <dd>
	                <select name="prefecture" id="prefecture" required>
	                    <option value=""></option>
	                </select>
	                <select name="city" id="city" required>
	                    <option value=""></option>
	                </select>
	            </dd>
	            <dt>活動日 ※指定がない場合は全ての曜日を検索します</dt>
	            <dd>
	                <label><input type="checkbox" name="week" value="土曜">土曜</label>
	                <label><input type="checkbox" name="week" value="日曜">日曜</label>
	                <label><input type="checkbox" name="week" value="月曜">月曜</label>
	                <label><input type="checkbox" name="week" value="火曜">火曜</label>
	                <label><input type="checkbox" name="week" value="水曜">水曜</label>
	                <label><input type="checkbox" name="week" value="木曜">木曜</label>
	                <label><input type="checkbox" name="week" value="金曜">金曜</label>
	            </dd>
	        </dl> 
	        
	        <h3>募集条件</h3>
	        <dl>
	            <dt>性別</dt>		
	            <dd>
	                <label><input type="radio" name="sex" value="制限なし" checked>制限なし</label>
	                <label><input type="radio" name="sex" value="男性のみ">男性のみ</label>
	                <label><input type="radio" name="sex" value="女性のみ">女性のみ</label>
	            </dd>
	            <dt>経験年数</dt>	
	            <dd>
	                <label><input type="radio" name="carrier" value="初心者歓迎" checked>初心者歓迎</label>
	                <label><input type="radio" name="carrier" value="1年以上">1年以上</label>
	                <label><input type="radio" name="carrier" value="3年以上">3年以上</label>
	                <label><input type="radio" name="carrier" value="5年以上">5年以上</label>
	            </dd>
	        </dl>
	        <input type="submit" value="検索">
		</form>
    <h2>サークルを登録してメンバーを募集する</h2>
        <button onclick="location.href='CircleRegistration'">サークルを登録する</button>
    <!-- ★一旦コメントアウト
    <h2>記事</h2>
    	<ul>
	        <li><a href="">1.「入ったらアムウェイサークルだった...」の回避策3選</a></li>
	        <li><a href="">2.社会人サークルに入ってよかったことTop5</a></li>
	        <li><a href="">3.「社会人サークルで恋人をつくりたい」と思っている方へ</a></li>
		</ul>
	-->
        <!-- ★共通化する -->
     
    <h2>お問い合わせ</h2>
	    <form action="" method="post">
		    <dl>
		         <dt>件名<span class="required">※</span></dt>
		         <dd>
		            <input type="text" name="title" required>
		         </dd>
		         
		         <dt>本文<span class="required">※</span></dt>
		         <dd>
		             <textarea name="contents" required placeholder="ご意見や当サービスへの不満等、なんでもお気軽に問い合わせください"></textarea>
		         </dd>
		    </dl>
	    </form>
    <footer>
        <div>©2022 SportsMap</div>
    </footer>        

	<!-- 都道府県、市区町村設定 -->
	<script src="js/prefectureCity.js"></script>
	<!-- 種目でその他を選択した場合にドロップダウンを表示 -->
	<script src="js/sportsDropdown.js"></script>
</body>
</html>