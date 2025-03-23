document.addEventListener("DOMContentLoaded", () => {
    // Inicializálás sütikből
    initializeDarkMode();
    initializeSidebar();

    // Az események regisztrálása automatikus, mert a HTML-ben már helyesen hivatkozol
});

// Sötét mód inicializálása
function initializeDarkMode() {
    const isDarkMode = getCookie('darkMode') === 'true';
    const body = document.body;
    const modeText = document.querySelector('.mode-text');

    if (isDarkMode) {
        body.classList.add('dark');
        modeText.textContent = 'Világos mód';
    } else {
        body.classList.remove('dark');
        modeText.textContent = 'Sötét mód';
    }
}

// Menü inicializálása
function initializeSidebar() {
    const isSidebarClosed = getCookie('sidebarClosed') === 'true';
    const sidebar = document.querySelector('.sidebar');
    const home = document.querySelector('.home');
    const toggleIcon = document.getElementById('toggle-icon');

    if (isSidebarClosed) {
        sidebar.classList.add('closed');
        home.classList.add('home-closed');
        toggleIcon.classList.replace('bxs-chevron-left-square', 'bxs-chevron-right-square');
    } else {
        sidebar.classList.remove('closed');
        home.classList.remove('home-closed');
        toggleIcon.classList.replace('bxs-chevron-right-square', 'bxs-chevron-left-square');
    }
}

// Sötét mód váltása
function toggleDarkMode() {
    const body = document.body;
    body.classList.toggle('dark');

    const modeText = document.querySelector('.mode-text');
    if (body.classList.contains('dark')) {
        modeText.textContent = 'Világos mód';
        setCookie('darkMode', 'true', 30); // Sötét mód mentése sütibe
    } else {
        modeText.textContent = 'Sötét mód';
        setCookie('darkMode', 'false', 30); // Világos mód mentése sütibe
    }
}

// Menü váltása
function toggleSidebar() {
    const sidebar = document.querySelector('.sidebar');
    const home = document.querySelector('.home');
    const toggleIcon = document.getElementById('toggle-icon');

    sidebar.classList.toggle('closed');
    home.classList.toggle('home-closed');

    if (sidebar.classList.contains('closed')) {
        toggleIcon.classList.replace('bxs-chevron-left-square', 'bxs-chevron-right-square');
        setCookie('sidebarClosed', 'true', 30); // Menü állapot mentése sütibe
    } else {
        toggleIcon.classList.replace('bxs-chevron-right-square', 'bxs-chevron-left-square');
        setCookie('sidebarClosed', 'false', 30); // Menü állapot mentése sütibe
    }
}

// Sütik beállítása
function setCookie(name, value, days) {
    const date = new Date();
    date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
    const expires = "expires=" + date.toUTCString();
    document.cookie = name + "=" + value + ";" + expires + ";path=/";
}

// Sütik lekérése
function getCookie(name) {
    const decodedCookie = decodeURIComponent(document.cookie);
    const cookies = decodedCookie.split(';');
    for (let i = 0; i < cookies.length; i++) {
        let c = cookies[i].trim();
        if (c.indexOf(name + "=") === 0) {
            return c.substring(name.length + 1, c.length);
        }
    }
    return "";
}

// mobil hamburger menü
function toggleMobileSidebar() {
        const sidebar = document.getElementById('sidebar');
        const overlay = document.getElementById('sidebarOverlay');

        sidebar.classList.toggle('mobile-open');
        overlay.classList.toggle('active');
    }

function closeMobileSidebar() {
        const sidebar = document.getElementById('sidebar');
        const overlay = document.getElementById('sidebarOverlay');

        sidebar.classList.remove('mobile-open');
        overlay.classList.remove('active');
    }

