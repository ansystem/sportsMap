var password = document.getElementById('password');
var password2 = document.getElementById('password2');
var pwErrorMsg = document.getElementById('pwErrorMsg');
var pwLenErrorMsg = document.getElementById('pwLenErrorMsg');
var submit = document.querySelector('[type="submit"]');


password.addEventListener('keyup',function() {
    pwCheck();
});

password2.addEventListener('keyup',function() {
    pwCheck();
});

function pwCheck() {
    if (password.value.length < 8) {
        pwLenErrorMsg.style.display = 'block';
        pwErrorMsg.style.display = 'none';
        submit.disabled = true;
    } else if (password.value!=password2.value) {
        pwLenErrorMsg.style.display = 'none';
        pwErrorMsg.style.display = 'block';
        submit.disabled = true;
    } else {
        pwLenErrorMsg.style.display = 'none';
        pwErrorMsg.style.display = 'none';
        submit.disabled = false;
    } 
}