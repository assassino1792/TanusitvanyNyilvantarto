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

    function loadContent(url) {
        fetch(url)
            .then(response => {
                if (!response.ok) throw new Error('Hiba történt a tartalom betöltése során.');
                return response.text();
            })
            .then(html => {
                // Tartalom betöltése
                contentSection.innerHTML = html;

                // Új eseménykezelők hozzáadása a betöltött tartalomhoz
                attachEventHandlers();
            })
            .catch(error => {
                console.error('Hiba:', error);
                contentSection.innerHTML = '<p>Nem sikerült betölteni a tartalmat.</p>';
            });
    }

    // Menüpont kattintásra töltsük be a "Felhasználók" tartalmat
    const felhasznalokMenu = document.querySelector('[data-menu="felhasznalok"]');
    if (felhasznalokMenu) {
        felhasznalokMenu.addEventListener('click', () => {
            loadContent('/felhasznalok');
        });
    }

    // Az oldal betöltésekor eseménykezelők regisztrálása
    attachEventHandlers();

    // Globális elérhetőség (ha máshol is szükséges)
    window.loadContent = loadContent;
});
