    function sortTable(oszlopIndex) {
        // Keressük meg a táblázatot és a sorokat
        const tableBody = document.querySelector("table tbody");
        const rows = Array.from(tableBody.rows);

        // Ellenőrizzük, hogy jelenleg növekvő vagy csökkenő irányban rendezzünk
        const jelenlegiIrany = tableBody.dataset.sortDirection || 'asc';
        const ujIrany = jelenlegiIrany === 'asc' ? 'desc' : 'asc';

        // Sorokat rendezzük a megadott oszlop alapján
        rows.sort((rowA, rowB) => {
            // Kivesszük az aktuális oszlop celláinak szövegét
            const cellA = rowA.cells[oszlopIndex].textContent.trim();
            const cellB = rowB.cells[oszlopIndex].textContent.trim();

            // Ha dátumokról van szó, dátum szerint rendezünk
            const datum = !isNaN(Date.parse(cellA)) && !isNaN(Date.parse(cellB));
            if (datum) {
                return ujIrany === 'asc'
                    ? new Date(cellA) - new Date(cellB)
                    : new Date(cellB) - new Date(cellA);
            }

            // Egyébként szöveg szerint rendezünk
            return ujIrany === 'asc'
                ? cellA.localeCompare(cellB, 'hu', { numeric: true })
                : cellB.localeCompare(cellA, 'hu', { numeric: true });
        });

        // Frissítjük a táblázatot a rendezett sorokkal
        tableBody.innerHTML = '';
        rows.forEach(row => tableBody.appendChild(row));

        // Tároljuk az új rendezési irányt
        tableBody.dataset.sortDirection = ujIrany;

        // Rendezési nyilak frissítése a fejlécben
        document.querySelectorAll(".sortable").forEach(header => {
            header.classList.remove("sort-asc", "sort-desc");
        });
        document.querySelectorAll(".sortable")[oszlopIndex].classList.add(`sort-${ujIrany}`); // Javítva az "oszlopIndex" változónév
    }