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

    function logout() {
            alert("Sikeres kijelentkezés!");
        }



