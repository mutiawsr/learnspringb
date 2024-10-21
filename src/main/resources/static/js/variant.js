function openForm() {
    $.ajax({
        type: "get",
        url: "/variant/form",
        contentType: "html",
        success: function (variantForm) {
            $('#myModal').modal('show');
            $('.modal-title').html("Variant Form");
            $('.modal-body').html(variantForm);
            loadData();
        }
    });
}

function editForm(id) {
    $.ajax({
        type: "get",
        url: `/variant/edit/${id}`,
        contentType: "html",
        success: function (variantForm) {
            $('#myModal').modal('show');
            $('.modal-title').html("Variant Form");
            $('.modal-body').html(variantForm);
            loadData();
        }
    });
}

function loadData() {
    $.ajax({
        url: '/variant/categories',
        method: 'GET',
        success: function() {
            var selectedProductId = $('#productId').data('selected-product-id');
            if (selectedProductId) {
                var selectedCategoryId = $('#categoryId').data('selected-category-id');
                $('#categoryId').val(selectedCategoryId).trigger('change');
            }
        }
    });

    $('#categoryId').change(function() {
        var categoryId = $(this).val();
        $('#productId').empty().append(new Option("Select Product", "")).prop('disabled', true);

        if (categoryId) {
            $.ajax({
                url: '/variant/form/products/' + categoryId,
                method: 'GET',
                success: function(products) {
                    products.forEach(function(product) {
                        $('#productId').append(new Option(product.name, product.id));
                    });
                    $('#productId').prop('disabled', false);
                }
            });
        }
    });
}

function deleteForm(id) {
    $.ajax({
        type: "get",
        url: `/variant/deleteForm/${id}`,
        contentType: "html",
        success: function (variantForm) {
            $('#myModal').modal('show');
            $('.modal-title').html("Variant Form");
            $('.modal-body').html(variantForm);
        }
    });
}

function deleteVariant(id) {
    $.ajax({
        type: "get",
        url: `/variant/delete/${id}`,
        contentType: "html",
        success: function (response) {
            location.reload();
        }
    });
}
