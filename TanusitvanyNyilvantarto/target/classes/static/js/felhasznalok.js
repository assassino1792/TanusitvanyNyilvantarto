document.addEventListener('DOMContentLoaded', () => {
    const contentSection = document.getElementById('content-section');

    function attachEventHandlers() {
        const bt_hozzaadvagyelrejt = document.getElementById('bt_hozzaadvagyelrejt');
        const mezok = document.querySelector('.mezok');

        if (bt_hozzaadvagyelrejt && mezok) {
            // Alapértelmezett állapotban rejtsük el
            mezok.style.display = 'none';

            // Esemény hozzáadása a gombra
            bt_hozzaadvagyelrejt.addEventListener('click', () => {
                if (mezok.style.display === 'none' || mezok.style.display === '') {
                    mezok.style.display = 'block';
                } else {
                    mezok.style.display = 'none';
                }
            });
        }
    }
      // Az alert üzenetek eltüntetése 2 másodperc után
        document.addEventListener("DOMContentLoaded", () => {
            setTimeout(() => {
                const alerts = document.querySelectorAll(".alert");
                alerts.forEach(alert => alert.style.display = 'none');
            }, 5000); // 2 másodperc múlva eltűnik
        });

    // Menüpont kattintásra töltsük be a "Felhasználók" tartalmat
    const felhasznalokMenu = document.querySelector('[data-menu="felhasznalok"]');
    if (felhasznalokMenu) {
        felhasznalokMenu.addEventListener('click', () => {
            loadContent('/felhasznalok');
        });
    }

});
