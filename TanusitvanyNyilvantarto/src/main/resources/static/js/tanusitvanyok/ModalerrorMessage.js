   document.addEventListener("DOMContentLoaded", function () {
        const errorMessage = document.querySelector('.alert-danger');
        if (errorMessage) {
            const addModal = new bootstrap.Modal(document.getElementById('addModal'));
            addModal.show();

            // Modal bezárásakor töröljük az üzenetet
            const modalElement = document.getElementById('addModal');
            modalElement.addEventListener('hidden.bs.modal', function () {
                errorMessage.textContent = ''; // Üzenet törlése
                errorMessage.style.display = 'none'; // Hibaüzenet elrejtése
            });
        }
    });