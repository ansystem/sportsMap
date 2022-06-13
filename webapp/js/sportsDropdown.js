var other = document.getElementById('other');
var otherSports = document.getElementById('otherSports');
otherSports.disabled = true;

document.getElementsByName('sports').forEach(
    r => r.addEventListener('click',function() {
        if (other.checked) {
            otherSports.disabled = false;
        } else {
            otherSports.disabled = true;
        }
    })
);
