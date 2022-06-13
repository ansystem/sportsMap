// 種目
let $sportsTypeHidden = document.getElementById('sportsTypeHidden');
let $sportsList = document.getElementsByName('sports');

let flag = false;
for ($sports of $sportsList) {
    if ($sports.value === $sportsTypeHidden.value) {
        $sports.checked = true;
        flag = true;
    }
}
if (flag==false) {
    let $otherSports = document.getElementById('otherSports');
    let options = $otherSports.options;
    document.getElementById('other').checked = true;
    $otherSports.disabled = false;

    for (option of options) {
        if (option.value===sportsTypeHidden.value) {
            option.selected = true;

        }
    }
}


// 活動曜日
let weekHiddenList = document.getElementById('weekHidden').value.split('、');
let $weekList = document.getElementsByName('week');

for (weekHidden of weekHiddenList) {
    for ($week of $weekList) {
        if (weekHidden === $week.value) {
            week.checked = true;
        }
    }
}


// 性別
let $sexList = document.getElementsByName('sex');
let $sexHidden = document.getElementById('sexHidden');

for ($sex of $sexList) {
    if ($sex.value === $sexHidden.value) {
        $sex.checked = true;
    }
}

// 経験年数
let $carrierList = document.getElementsByName('carrier');
let $carrierHidden = document.getElementById('carrierHidden');

for ($carrier of $carrierList) {
    if ($carrier.value === $carrierHidden.value) {
        $carrier.checked = true;
    }
}
