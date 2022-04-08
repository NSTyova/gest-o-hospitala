/**
 * busca as fornecedores com auto-complete
 */
$("#produto").autocomplete({
    source: function (request, response) {
        $.ajax({
            method: "GET",
            url: "/produtos/titulos",
            data: {
            	termo: request.term
			},
            success: function (data) {
            	response(data);
            }
        });
    }
});

/**
 * busca os origens com auto-complete
 */
$("#origem").autocomplete({
    source: function (request, response) {
        $.ajax({
            method: "GET",
            url: "/origens/titulos",
            data: {
            	termo: request.term
			},
            success: function (data) {
            	response(data);
            }
        });
    }
});


/**
 * Datatable lsita de produtos
*/
$(document).ready(function () {
	moment.locale('pt-BR');
    var table = $('#table-estoque').DataTable({
    	searching: true,
    	order: [[ 1, "asc" ]],
    	lengthMenu: [5, 10],
        processing: true,
        serverSide: true,
        responsive: true,
        ajax: {
            url: '/estoque/datatables/server',
            data: 'data'
        },
        columns: [
            {data: 'id'},
            {data: 'produtos.nome'},
            {data: 'quantidade'},
            {orderable: false, 
             data: 'id',
                "render": function(id) {
                    return '<a class="btn btn-success btn-sm btn-block" href="/clientes/editar/'+ 
                    	id +'" role="button"><i class="fas fa-edit"></i></a>';
                }
            },
            {orderable: false,
             data: 'id',
                "render": function(id) {
                    return '<a class="btn btn-danger btn-sm btn-block" href="/clientes/excluir/'+ 
                    	id +'" role="button" data-toggle="modal" data-target="#confirm-modal"><i class="fas fa-times-circle"></i></a>';
                }               
            }
        ]
    });
}); 