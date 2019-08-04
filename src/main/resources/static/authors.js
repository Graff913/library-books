$(document).ready(function () {

    $('#authorTable').load("/authors-table");
    $("#del-form").submit(function (event) {
        event.preventDefault();
        deleting_author_submit();
    });
});

function deleting_author_submit() {

    var delFormData = {
        id: $("#authorId").val()
    };

    $.ajax({
        type: "DELETE",
        contentType: "application/json",
        url: "/api/authors/" + $("#authorId").val(),
        data: JSON.stringify(delFormData),
        dataType: 'json',
        cache: false,
        success: function (data) {
            console.log("SUCCESS : ", data);
            $('#authorTable').load("/authors-table");

            $("#del-form").prop("disabled", false);
        },
        error: function (e) {
            console.log("ERROR : ", e);
            alert("проблема с удалением");
            $("#del-form").prop("disabled", false);

        }
    });
}