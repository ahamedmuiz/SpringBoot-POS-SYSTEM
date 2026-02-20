$(document).ready(function() {
    loadAllItems();
});

function loadAllItems() {
    $.ajax({
        url: 'http://localhost:8080/api/v1/item/all',
        method: 'GET',
        success: function(res) {
            let tbody = $("#itemTableBody");
            tbody.empty();
            res.data.forEach(i => {
                tbody.append(`
                    <tr onclick="bindItemRow('${i.iCode}', '${i.iDescription}', ${i.iPrice}, ${i.iQty})" style="cursor:pointer;">
                        <td>${i.iCode}</td>
                        <td>${i.iDescription}</td>
                        <td>${i.iPrice}</td>
                        <td>${i.iQty}</td>
                    </tr>
                `);
            });
        }
    });
}

function bindItemRow(code, desc, price, qty) {
    $('#txtCode').val(code);
    $('#txtDesc').val(desc);
    $('#txtPrice').val(price);
    $('#txtQty').val(qty);
}

function clearItemFields() {
    $('#txtCode').val('');
    $('#txtDesc').val('');
    $('#txtPrice').val('');
    $('#txtQty').val('');
}

function saveItem() {
    $.ajax({
        url: 'http://localhost:8080/api/v1/item',
        method: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({
            iCode: $('#txtCode').val(),
            iDescription: $('#txtDesc').val(),
            iPrice: parseFloat($('#txtPrice').val()),
            iQty: parseInt($('#txtQty').val())
        }),
        success: function(res) {
            if (res.status === 201) {
                alert(res.message);
                loadAllItems();
                clearItemFields();
            } else {
                alert("Item Not Saved, Try Again");
            }
        },
        error: function(err) { alert(err.responseJSON ? err.responseJSON.message : "Save Error"); }

    });
}

function updateItem() {
    $.ajax({
        url: 'http://localhost:8080/api/v1/item',
        method: 'PUT',
        contentType: 'application/json',
        data: JSON.stringify({
            iCode: $('#txtCode').val(),
            iDescription: $('#txtDesc').val(),
            iPrice: parseFloat($('#txtPrice').val()),
            iQty: parseInt($('#txtQty').val())
        }),
        success: function(res) {
            if (res.status === 201) {
                alert(res.message);
                loadAllItems();
                clearItemFields();
            } else {
                alert("Item Not Updated, Try Again");
            }
        },
        error: function(err) { alert(err.responseJSON ? err.responseJSON.message : "Update Error"); }

    });
}

function deleteItem() {
    let id = $('#txtCode').val();
    if(!id) return alert("Select an item first!");
    $.ajax({
        url: 'http://localhost:8080/api/v1/item?id=' + id,
        method: 'DELETE',
        success: function(res) {
            if (res.status === 200) {
                alert(res.message);
                loadAllItems();
                clearItemFields();
            } else {
                alert("Item Not Deleted, Try Again");
            }
        }
    });
}