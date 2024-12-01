document.addEventListener("DOMContentLoaded", function () {
    deleteCall();
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


