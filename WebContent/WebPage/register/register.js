window.onload = function() {

}
document.getElementById('myform').onsubmit = function(e) {  
    var e = e || window.event; 
    if (!repassword()) {
        e.preventDefault();
    }
    else window.event.returnValue = repassword();  
    
}


function repassword() {
    console.log('repassword')
    var password = $('#password')[0];
    var repassword = $('#repassword')[0];

    if (password.value !== repassword.value) {
        repassword.setCustomValidity("两次密码输入不一致");
        repassword.oninput = function() {
            this.setCustomValidity("");
        }
        return false;
    }

    else {
        repassword.setCustomValidity("");
        return true;
    }
}

