(function ($, window, document) {
    $(function () {

        var authorControllerURL = "AuthorController";

        var $document = $(document);
        var $body = $('body');
        var $authorTableData = $('#tableData');

        $document.ready(function () {
            if ($body.attr('class') === 'authorList') {
                $ajax({
                    type: GET,
                    url: authorControllerURL + "?action=authorListAjax",
                    SUCCESS: function (authors) {
                        displayAuthors(authors);
                    },
                    ERROR: function (jqXHR, textStatus, errorThrown) {
                        alert("Could not get authors for this user due to: " + errorThrown.toString());
                    }
                });
            }
        });

        function displayAuthors(authors) {
            $.each(authors, function (index, author) {
                var row = '<tr class="authorListRow">' +
                        '<td>' + author.authorId + '</td>' +
                        '<td>' + author.authorName + '</td>' +
                        '<td>' + author.dateAdded + '</td>' +
                        '</tr>';
                $authorTableData.append(row);
            });
        }
    });
});

