function deleteBook(id) {
    var bookData = {
            id : id
        };

    $.ajax({
        type: "DELETE",
        contentType: "application/json",
        url: "/api/books/{" + id +"}",
        data: JSON.stringify(bookData),
        dataType: 'json',
        cache: false,
        success: function (data) {

            $('#bookTable').load("/books-table");
            console.log("SUCCESS : ", data);
        },
        error: function (e) {
            alert("проблема с удалением");
            console.log("ERROR : ", e);
        }
    });
}

function editBook(id) {
    $('#editTable').load("/editor/" + id);
}