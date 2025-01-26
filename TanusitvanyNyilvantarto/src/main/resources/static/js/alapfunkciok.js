const body = document.querySelector('body'),
      sidebar = body.querySelector('nav'),
      modeSwitch = body.querySelector(".toggle-switch"),
      modeText = body.querySelector(".mode-text");


    function toggleSidebar() {
        const sidebar = document.getElementById('sidebar');
        sidebar.classList.toggle('closed');
        sidebar.classList.toggle('open');
    }

    function toggleDarkMode() {
        const body = document.body;
        body.classList.toggle('dark');

        const modeText = document.querySelector('.mode-text');
        if (body.classList.contains('dark')) {
            modeText.textContent = 'Világos mód';
        } else {
            modeText.textContent = 'Sötét mód';
        }
    }

    function toggleSidebar() {
    const sidebar = document.querySelector('.sidebar');
    const home = document.querySelector('.home');
    const toggleIcon = document.getElementById('toggle-icon');

    sidebar.classList.toggle('closed');
    home.classList.toggle('home-closed');

    // Változtatja az ikon állapotát
    if (sidebar.classList.contains('closed')) {
        toggleIcon.classList.replace('bxs-chevron-left-square', 'bxs-chevron-right-square');
    } else {
        toggleIcon.classList.replace('bxs-chevron-right-square', 'bxs-chevron-left-square');
    }
}




