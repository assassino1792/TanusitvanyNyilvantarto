document.addEventListener("DOMContentLoaded", function () {
    alertek();
    felhasznalokTartalom();
    editForM();
    passwordForM();
    deleteForM();
});

function alertek() {
    setTimeout(() => {
        const alerts = document.querySelectorAll('.alert');
        alerts.forEach(alert => alert.style.display = 'none');
    }, 5000);
}

function felhasznalokTartalom() {
    const felhasznalokMenu = document.querySelector('[data-menu="felhasznalok"]');
    if (felhasznalokMenu) {
        felhasznalokMenu.addEventListener('click', () => {
            loadContent('/felhasznalok');
        });
    }
}

function loadContent(url) {
    fetch(url)
        .then(response => {
            if (!response.ok) {
                throw new Error('Hiba történt a tartalom betöltése során.');
            }
            return response.text();
        })
        .then(html => {
            const contentContainer = document.querySelector('.home');
            if (contentContainer) {
                contentContainer.innerHTML = html;
            }
        })
        .catch(error => {
            console.error(error);
        });
}

     function editForM() {
     const openEditModalButtons = document.querySelectorAll(".openModal");
     const form2Element = document.getElementById("editForm");


     openEditModalButtons.forEach(button => {
         button.addEventListener("click", function () {
             const userId = button.getAttribute("data-id");
             form2Element.action = `/felhasznalok/edit/${userId}`;
            fetch(`/felhasznalok/${userId}`)
    .then(response => {
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
        return response.json();
    })
    .then(data => {
        document.getElementById("newUsername").value = data.felhasznalonev;
        document.getElementById("newLastname").value = data.vezeteknev;
        document.getElementById("newFirstname").value = data.keresztnev;
        document.getElementById("newEmail").value = data.email;
    })
    .catch(error => {
        console.error("Error fetching user data:", error);
        alert("Nem sikerült betölteni a felhasználó adatait!");
            });
         });
     });
 };

    function passwordForM() {
      const openModalButtons = document.querySelectorAll(".openModal");
      const formElement = document.getElementById("passwordForm");


      openModalButtons.forEach(button => {
          button.addEventListener("click", function () {
              const userId = button.getAttribute("data-id");
              formElement.action = `/felhasznalok/updatepw/${userId}`;
          });
      });
  };

    function deleteForM () {
    const deleteButtons = document.querySelectorAll(".deleteButton");
    const deleteForm = document.getElementById("deleteForm");

    deleteButtons.forEach(button => {
        button.addEventListener("click", function () {
            const userId = button.getAttribute("data-id");
            deleteForm.action = `/felhasznalok/delete/${userId}`;
            const deleteModal = new bootstrap.Modal(document.getElementById("deleteModal"));
            deleteModal.show();
        });
    });
};
