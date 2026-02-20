let cart = [];
let customersData = [];
let itemsData = [];

$(document).ready(function() {
    $('#txtDate').val(new Date().toISOString().split('T')[0]); // Set today's date

    // Fetch Data for Dropdowns
    $.get('http://localhost:8080/api/v1/customer/all', res => {
        customersData = res.data;
        res.data.forEach(c => $('#cmbCustomerId').append(`<option value="${c.cId}">${c.cId}</option>`));
    });

    $.get('http://localhost:8080/api/v1/item/all', res => {
        itemsData = res.data;
        res.data.forEach(i => $('#cmbItemCode').append(`<option value="${i.iCode}">${i.iCode}</option>`));
    });
});

// Auto-Fill Customer Name
$('#cmbCustomerId').change(function() {
    let customer = customersData.find(c => c.cId === $(this).val());
    $('#txtCustomerName').val(customer ? customer.cName : '');
});

// Auto-Fill Item Details
$('#cmbItemCode').change(function() {
    let item = itemsData.find(i => i.iCode === $(this).val());
    $('#txtItemDesc').val(item ? item.iDescription : '');
    $('#txtUnitPrice').val(item ? item.iPrice : '');
    $('#txtQtyOnHand').val(item ? item.iQty : '');
});

function addToCart() {
    let itemCode = $('#cmbItemCode').val(),
        desc = $('#txtItemDesc').val(),
        unitPrice = parseFloat($('#txtUnitPrice').val()),
        qtyOnHand = parseInt($('#txtQtyOnHand').val()),
        buyQty = parseInt($('#txtBuyQty').val());

    if (itemCode === 'Select Item' || !buyQty || buyQty <= 0) return alert("Please select a valid item and quantity.");

    let existingItem = cart.find(i => i.itemCode === itemCode);
    let totalRequested = (existingItem ? existingItem.quantity : 0) + buyQty;

    if (totalRequested > qtyOnHand) return alert("Insufficient Quantity on Hand!");

    if (existingItem) {
        existingItem.quantity += buyQty;
        existingItem.total = existingItem.quantity * unitPrice;
    } else {
        cart.push({ itemCode, desc, unitPrice, quantity: buyQty, total: unitPrice * buyQty });
    }

    updateCartUI();
    $('#txtBuyQty').val('');
}

function updateCartUI() {
    let tbody = $('#cartTableBody').empty();

    // Calculate total in one line using reduce
    let netTotal = cart.reduce((sum, item) => sum + item.total, 0);

    cart.forEach((item, index) => {
        tbody.append(`
            <tr>
                <td>${item.itemCode}</td>
                <td>${item.desc}</td>
                <td>${item.unitPrice}</td>
                <td>${item.quantity}</td>
                <td>${item.total.toFixed(2)}</td>
                <td><button class="btn btn-sm btn-danger" onclick="removeFromCart(${index})">Remove</button></td>
            </tr>
        `);
    });

    $('#lblTotal').text(netTotal.toFixed(2));
}

function removeFromCart(index) {
    cart.splice(index, 1);
    updateCartUI();
}

function submitOrder() {
    let orderDTO = {
        orderId: $('#txtOrderId').val(),
        orderDate: $('#txtDate').val(),
        customerId: $('#cmbCustomerId').val(),
        orderDetails: cart
    };

    if (!orderDTO.orderId || orderDTO.customerId === 'Select Customer' || !cart.length) {
        return alert("Please ensure Order ID, Customer, and Items are added.");
    }

    $.ajax({
        url: 'http://localhost:8080/api/v1/order',
        method: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(orderDTO),
        success: function(res) {
            if (res.status === 201) {
                alert("Order Placed Successfully!");
                cart = [];
                updateCartUI();

                // Group UI clearing into two lines
                $('#txtOrderId, #txtCustomerName, #txtItemDesc, #txtUnitPrice, #txtQtyOnHand').val('');
                $('#cmbCustomerId, #cmbItemCode').prop('selectedIndex', 0);
            }
        },
        error: err => alert(err.responseJSON ? err.responseJSON.message : "Error placing order")
    });
}