$(document).ready(function() {
    loadAllCustomers(); // Load table on start
});

function loadAllCustomers() {
    $.ajax({
        url: 'http://localhost:8080/api/v1/customer/all',
        method: 'GET',
        success: function(res) {
            let tbody = $("#customerTableBody");
            tbody.empty();
            res.data.forEach(c => {
                tbody.append(`
                    <tr onclick="bindCustomerRow('${c.cId}', '${c.cName}', '${c.cAddress}')" style="cursor:pointer;">
                        <td>${c.cId}</td>
                        <td>${c.cName}</td>
                        <td>${c.cAddress}</td>
                    </tr>
                `);
            });
        }
    });
}

function bindCustomerRow(id, name, address) {
    $('#txtId').val(id);
    $('#txtName').val(name);
    $('#txtAddress').val(address);
}

function clearCustomerFields() {
    $('#txtId').val('');
    $('#txtName').val('');
    $('#txtAddress').val('');
}

function saveCustomer() {
    $.ajax({
        url: 'http://localhost:8080/api/v1/customer',
        method: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({
            cId: $('#txtId').val(),
            cName: $('#txtName').val(),
            cAddress: $('#txtAddress').val()
        }),
        success: function(res) {
            if (res.status === 201) {
                alert(res.message);
                loadAllCustomers();
                clearCustomerFields();
            } else {
                alert("Customer Not Saved, Try Again");
            }
        },
        error: function(err) { alert(err.responseJSON ? err.responseJSON.message : "Save Error"); }
    });
}

function updateCustomer() {
    $.ajax({
        url: 'http://localhost:8080/api/v1/customer',
        method: 'PUT',
        contentType: 'application/json',
        data: JSON.stringify({
            cId: $('#txtId').val(),
            cName: $('#txtName').val(),
            cAddress: $('#txtAddress').val()
        }),
        success: function(res) {
            if (res.status === 201) {
                alert(res.message);
                loadAllCustomers();
                clearCustomerFields();
            } else {
                alert("Customer Not Updated, Try Again");
            }
        },
        error: function(err) { alert(err.responseJSON ? err.responseJSON.message : "Update Error"); }

    });
}

function deleteCustomer() {
    let id = $('#txtId').val();
    if(!id) return alert("Select a customer first!");
    $.ajax({
        url: 'http://localhost:8080/api/v1/customer?id=' + id,
        method: 'DELETE',
        success: function(res) {
            if (res.status === 200) {
                alert(res.message);
                loadAllCustomers();
                clearCustomerFields();
            } else {
                alert("Customer Not Deleted, Try Again");
            }
        }
    });
}