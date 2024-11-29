
document.addEventListener('DOMContentLoaded', () => {
    const bt_hozzaadvagyelrejt = document.getElementById('bt_hozzaadvagyelrejt');
    const kontener = document.getElementById('kontener');

    if (bt_hozzaadvagyelrejt && kontener) {
        // Gombra kattintva jelenjen meg vagy tűnjön el a konténer
        bt_hozzaadvagyelrejt.addEventListener('click', () => {
            if (kontener.style.display === 'none' || kontener.style.display === '') {
                kontener.style.display = 'block';
            } else {
                kontener.style.display = 'none';
            }
        });
    }

    // Automatikus eltüntetés alert üzenetekhez
    setTimeout(() => {
        const alerts = document.querySelectorAll('.alert');
        alerts.forEach(alert => alert.style.display = 'none');
    }, 5000);
});

    // Menüpont kattintásra töltsük be a "Felhasználók" tartalmat
    const felhasznalokMenu = document.querySelector('[data-menu="felhasznalok"]');
    if (felhasznalokMenu) {
        felhasznalokMenu.addEventListener('click', () => {
            loadContent('/felhasznalok');
        });
    }

