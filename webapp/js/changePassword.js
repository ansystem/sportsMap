var passwordChange = document.getElementById('passwordChange');
var pwArea = document.getElementById('passwordArea');
var newPassword = document.getElementById('newPassword');
var newPassword2 = document.getElementById('newPassword2');
var errorMsg = document.getElementById('errorMsg');

passwordChange.addEventListener('change',function() {
    if (passwordChange.checked) {
        pwArea.style.display = 'block';
    } else {
        pwArea.style.display = 'none';
    }
});

function cancelsubmit() {
    if (passwordChange.checked) {
        if (newPassword.value!=newPassword2.value) {
            errorMsg.innerText = 'パスワードが一致していません';
            errorMsg.style.display = 'block';
            return false;
        } else if (newPassword.value.length < 8){
            errorMsg.innerText = 'パスワードは8文字以上にする必要があります';
            errorMsg.style.display = 'block';
            return false;
        } else {
            return true;
        }
    } else {
        return true;
    }

   
}
