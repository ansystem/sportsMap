$(function() {
    // 都道府県の設定処理
    getPrefectureSelection('#prefecture','prefectureCityCode.json');
    
    // 都道府県を変えた場合の処理
    $('#prefecture').on('change',function(e) {
        // 市区町村のリストを全削除
        $('#city').empty();
        // 設定した都道府県に紐づく市区町村をリスト表示
        getCitySelection('prefectureCityCode.json','#city', e.target.value);
    });
});

// 都道府県の設定処理
function getPrefectureSelection(prefSelectionId, jsonPath) {
    // JSONファイルから都道府県を取得
    $.getJSON(jsonPath,function(data) {
        var tmp = null;
        $.grep(data,
        function(obj,idx) {
            // 都道府県の重複を無くす為、各都道府県の1回目のみリストを作成する
            if (tmp != obj.prefectureName) {
                tmp = obj.prefectureName;
                $(prefSelectionId).append('<option value="' + obj.prefectureName + '">' + obj.prefectureName + '</option>');
            }
        })
    })
}
// 市区町村の設定処理
function getCitySelection(jsonPath,citySelectionId,prefectureName) {
    // JSONファイルから市区町村を取得
    $.getJSON(jsonPath, function (data) {
        $.grep(data,
            function (obj, idx) {
                // 設定した都道府県に紐づく市区町村のみリスト表示
                if (obj.prefectureName === prefectureName) {
                    $(citySelectionId).append('<option value="' + obj.cityName + '">' + obj.cityName + '</option>');
                }
            }
        );
    });
}
