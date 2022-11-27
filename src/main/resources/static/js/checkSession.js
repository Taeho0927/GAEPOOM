function init() {
    const login_btn = document.getElementById('login_btn');
    const logout_btn = document.getElementById('logout_btn');
    const welcome_ph = document.getElementById('welcome_ph');
    if (fusername != ""){
        login_btn.style.display = 'none';
        logout_btn.style.display = 'block';
    }
    else {
        login_btn.style.display='block';
        logout_btn.style.display='none';
        welcome_ph.style.display='none';
    }
}