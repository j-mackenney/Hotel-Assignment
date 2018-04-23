$('#filter').change(function () {
    var filterValue = $(this).val();
    if(filterValue === 'Show all') {
        $('.listing').show();
    }
    else {
        $('.type').each(function() {
            if($(this).text() === filterValue) {
                $(this).closest('.listing').show();
            }
            else {
                $(this).closest('.listing').hide();
            }
        });
    }
});