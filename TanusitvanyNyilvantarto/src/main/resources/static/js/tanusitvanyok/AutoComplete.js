    document.getElementById("newAddHostname").addEventListener("input", function (event) {
        const query = this.value;

        if (query.length > 1) {
            fetch(`/autocomplete?query=${encodeURIComponent(query)}`)
                .then((response) => response.json())
                .then((data) => {
                    // Tárolja a találatokat az input elemhez kapcsolva
                    this.dataset.suggestions = JSON.stringify(data);

                    // Ha vannak találatok, az első javaslatot állítsa be helyőrzőként
                    if (data.length > 0) {
                        this.placeholder = data[0];
                    } else {
                        this.placeholder = "";
                    }
                });
        }
    });

    document.getElementById("newAddHostname").addEventListener("keydown", function (event) {
        // `Tab` billentyű lenyomására kiegészíti az input találatot
        if (event.key === "Tab") {
            event.preventDefault(); // Ne váltson másik mezőre
            const suggestions = JSON.parse(this.dataset.suggestions || "[]");
            if (suggestions.length > 0) {
                this.value = suggestions[0]; // Az első találat kiegészítése
            }
        }
    });