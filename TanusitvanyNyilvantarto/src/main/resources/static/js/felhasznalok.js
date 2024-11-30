
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
function loadContent(url) {
    fetch(url)
        .then(response => {
            if (!response.ok) {
                throw new Error('Hiba történt a tartalom betöltése során.');
            }
            return response.text();
        })
        .then(html => {
            const contentContainer = document.querySelector('.home'); // Ide töltjük a tartalmat
            if (contentContainer) {
                contentContainer.innerHTML = html;
            }
        })
        .catch(error => {
            console.error(error);
        });
}

//Jelszó modal
document.addEventListener("DOMContentLoaded", function () {
     const openModalButtons = document.querySelectorAll(".openModal");
     const formElement = document.getElementById("passwordForm");


     openModalButtons.forEach(button => {
         button.addEventListener("click", function () {
             const userId = button.getAttribute("data-id");
             formElement.action = `/felhasznalok/updatepw/${userId}`;
         });
     });
 });
</script>

//szerkesztés modal
    document.addEventListener("DOMContentLoaded", function () {
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
 });
