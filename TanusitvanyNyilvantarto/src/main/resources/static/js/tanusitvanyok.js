document.addEventListener("DOMContentLoaded", function () {
    deleteCall();
    EditCall();
    DetailsCall();
});

function deleteCall() {
    const deleteButtons = document.querySelectorAll(".deleteButton");
    const deleteForm = document.getElementById("deleteForm");

    deleteButtons.forEach(button => {
        button.addEventListener("click", function () {
            const id = button.getAttribute("data-id");
            deleteForm.action = `/tanusitvanyok/delete/${id}`;
        });
    });
}

  function EditCall() {
    const openEditModalButtons = document.querySelectorAll(".openModal");
    const form2Element = document.getElementById("editForm");

    openEditModalButtons.forEach(button => {
        button.addEventListener("click", function () {
            const tanusitvanyId = button.getAttribute("data-id");
            form2Element.action = `/tanusitvanyok/edit/${tanusitvanyId}`;

            fetch(`/tanusitvanyok/${tanusitvanyId}`)
                .then(response => {
                    if (!response.ok) {
                        throw new Error(`HTTP error! status: ${response.status}`);
                    }
                    return response.json();
                })
                .then(data => {
                    document.getElementById("EditHostname").value = data.szerverNev || '';
                    document.getElementById("EditTanusitvanyTipus").value = data.tanusitvanyTipus || '';
                    document.getElementById("EditCAStartTime").value = data.kezdetiIdo || '';
                    document.getElementById("EditCAExperationDate").value = data.lejaratiIdo || '';
                    document.getElementById("EditCAStatus").value = data.statusz || '';
                    document.getElementById("EditIssuerName").value = data.kiallitoNeve || '';
                    document.getElementById("EditDetails").value = data.reszletek || '';
                })
                .catch(error => {
                    console.error("Error fetching tanúsítvány data:", error);
                    alert("Nem sikerült betölteni a tanúsítvány adatait!");
                });
        });
    });
};

    function DetailsCall() {
    const tableRows = document.querySelectorAll(".table-row");
    const actionButtons = document.querySelectorAll(".openModal, .deleteButton");

    tableRows.forEach(row => {
        row.addEventListener("click", function () {
            const tanusitvanyId = row.getAttribute("data-id");

            fetch(`/tanusitvanyok/${tanusitvanyId}`)
                .then(response => {
                    if (!response.ok) {
                        throw new Error(`HTTP error! status: ${response.status}`);
                    }
                    return response.json();
                })
                .then(data => {
                    document.getElementById("detailsSzerverNev").textContent = data.szerverNev || '';
                    document.getElementById("detailsTanusitvanyTipus").textContent = data.tanusitvanyTipus || '';
                    document.getElementById("detailsKezdetiIdo").textContent = data.kezdetiIdo || '';
                    document.getElementById("detailsLejaratiIdo").textContent = data.lejaratiIdo || '';
                    document.getElementById("detailsStatusz").textContent = data.statusz || '';
                    document.getElementById("detailsKiallitoNeve").textContent = data.kiallitoNeve || '';
                    document.getElementById("detailsReszletek").textContent = data.reszletek || '';

                    // Modal ablak megnyitása
                    const detailsModal = new bootstrap.Modal(document.getElementById("detailsModal"));
                    detailsModal.show();
                })
                .catch(error => {
                    console.error("Error fetching data:", error);
                    alert("Nem sikerült betölteni a tanúsítvány adatait!");
                });
        });
    });

    // Gombokra való kattintás kizárása
    actionButtons.forEach(button => {
        button.addEventListener("click", function (event) {
            event.stopPropagation();
        });
    });
};



