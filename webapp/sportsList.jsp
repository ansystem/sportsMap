<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<label><input type="radio" name="sports" value="サッカー" checked>サッカー</label>
<label><input type="radio" name="sports" value="フットサル">フットサル</label>
<label><input type="radio" name="sports" value="野球">野球</label>
<label><input type="radio" name="sports" value="ソフトボール">ソフトボール</label>
<label><input type="radio" name="sports" value="テニス">テニス</label>
<label><input type="radio" name="sports" value="バスケットボール">バスケットボール</label>
<label><input type="radio" name="sports" value="ラグビー">ラグビー</label>
<label><input type="radio" name="sports" value="バレーボール">バレーボール</label>
<label><input type="radio" name="sports" value="アメリカンフットボール">アメリカンフットボール</label>
<label><input type="radio" name="sports" value="バドミントン">バドミントン</label>
<label><input type="radio" name="sports" value="ハンドボール">ハンドボール</label>
<label><input type="radio" name="sports" value="卓球">卓球</label>
<label><input type="radio" name="sports" value="柔道">柔道</label>
<label><input type="radio" name="sports" value="ボクシング">ボクシング</label>
<label><input type="radio" name="sports" value="ソフトバレーボール">ソフトバレーボール</label>
<label><input type="radio" name="sports" value="ビーチバレーボール">ビーチバレーボール</label>
<label><input type="radio" name="sports" value="ビーチフットボール">ビーチフットボール</label>
<label><input type="radio" name="sports" value="その他" id="other">その他</label>
<select name="otherSports" id="otherSports" disabled>
   	<option value="ドッジボール">ドッジボール</option>
	<option value="ゲートボール">ゲートボール</option>
	<option value="ボウリング">ボウリング</option>
	<option value="スカッシュ">スカッシュ</option>
	<option value="ビリヤード">ビリヤード</option>
	<option value="陸上">陸上</option>
	<option value="水泳">水泳</option>
	<option value="フィットネス">フィットネス</option>
	<option value="ヨガ">ヨガ</option>
	<option value="体操">体操</option>
	<option value="ダンス">ダンス</option>
	<option value="サイクリング">サイクリング</option>
	<option value="射撃">射撃</option>
	<option value="ダーツ">ダーツ</option>
	<option value="スキー">スキー</option>
	<option value="スノーボード">スノーボード</option>
	<option value="フィギュアスケート">フィギュアスケート</option>
	<option value="アイスホッケー">アイスホッケー</option>
	<option value="相撲">相撲</option>
	<option value="剣道">剣道</option>
	<option value="空手">空手</option>
	<option value="フェンシング">フェンシング</option>
	<option value="ムエタイ">ムエタイ</option>
	<option value="レスリング">レスリング</option>
	<option value="総合格闘技">総合格闘技</option>
	<option value="テコンドー">テコンドー</option>
	<option value="モータースポーツ">モータースポーツ</option>
	<option value="F1">F1</option>
	<option value="セパタクロー">セパタクロー</option>
	<option value="サーフィン">サーフィン</option>
	<option value="合気道">合気道</option>
	<option value="アーチェリー">アーチェリー</option>
	<option value="アームレスリング">アームレスリング</option>
	<option value="ウオーキング">ウオーキング</option>
	<option value="カヌー">カヌー</option>
	<option value="カバディ">カバディ</option>
	<option value="気功">気功</option>
	<option value="キックベースボール">キックベースボール</option>
	<option value="キャンプ">キャンプ</option>
	<option value="弓道">弓道</option>
	<option value="サンボ">サンボ</option>
	<option value="シュートボクシング">シュートボクシング</option>
	<option value="少林寺拳法">少林寺拳法</option>
	<option value="ジョギング・ランニング">ジョギング・ランニング</option>
	<option value="水球">水球</option>
	<option value="スケートボード">スケートボード</option>
	<option value="ストリートバスケット（3x3）">ストリートバスケット（3x3）</option>
	<option value="スノーモビル">スノーモビル</option>
	<option value="スピードスケート">スピードスケート</option>
	<option value="スポーツチャンバラ">スポーツチャンバラ</option>
	<option value="セーリング">セーリング</option>
	<option value="ソフトテニス">ソフトテニス</option>
	<option value="中国拳法">中国拳法</option>
	<option value="綱引競技">綱引競技</option>
	<option value="釣り">釣り</option>
	<option value="トライアスロン">トライアスロン</option>
	<option value="トランポリン">トランポリン</option>
	<option value="トレッキング（登山）">トレッキング（登山）</option>
	<option value="ハイキング">ハイキング</option>
	<option value="BMX（バイシクルモトクロス）">BMX（バイシクルモトクロス）</option>
	<option value="馬術">馬術</option>
	<option value="パワーリフティング">パワーリフティング</option>
	<option value="ビーチサッカー">ビーチサッカー</option>
	<option value="ビーチドッジボール">ビーチドッジボール</option>
	<option value="フォークダンス">フォークダンス</option>
	<option value="フライングディスク">フライングディスク</option>
	<option value="フリークライミング">フリークライミング</option>
	<option value="ホッケー">ホッケー</option>
	<option value="ホースシューズ">ホースシューズ</option>
	<option value="ボディビル">ボディビル</option>
	<option value="ボール体操">ボール体操</option>
	<option value="マウンテンバイク">マウンテンバイク</option>
	<option value="ラジオ体操">ラジオ体操</option>
	<option value="その他">その他</option>  
</select>