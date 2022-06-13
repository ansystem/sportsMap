

function checkBoxRequired() {
    var weekList = document.getElementsByName('week');
    var msg = document.getElementById('Msg');

    for (week of weekList) {
        if (week.checked) {
            return true;
        }
    }

    msg.innerHTML = '活動曜日を1つ以上選択してください';
    msg.style.display = 'block';
    return false;
}