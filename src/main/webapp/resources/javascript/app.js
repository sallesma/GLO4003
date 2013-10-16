
// ------------  DatePicker --------------

$('#fromDate').datepicker({
});

$('#toDate').datepicker({
});

//------------  Filter Form Actions --------------

$('.filterSelect').change(function() { 
	$( '#filterForm').submit();
});

$('.removeFilter').click(function() {
	var inputToClear = $(this).parent().next('input[type=hidden]');
	inputToClear.val('');
	$( '#filterForm').submit();
})


