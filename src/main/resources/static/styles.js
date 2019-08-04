$(document).ready(function () {

    $('#styleTable').load("/styles-table");
    $("#del-form").submit(function (event) {
        event.preventDefault();
        deleting_style_submit();
    });
});

function deleting_style_submit() {

    var delFormData = {
        id: $("#styleId").val()
    };

    $.ajax({
        type: "DELETE",
        contentType: "application/json",
        url: "/api/styles/" + $("#styleId").val(),
        data: JSON.stringify(delFormData),
        dataType: 'json',
        cache: false,
        success: function (data) {
            console.log("SUCCESS : ", data);
            $('#styleTable').load("/styles-table");
            $("#del-form").prop("disabled", false);
        },
        error: function (e) {
            alert("проблема с удалением");
            console.log("ERROR : ", e);
            $("#del-form").prop("disabled", false);

        }
    });
}