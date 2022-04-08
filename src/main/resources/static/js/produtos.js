/**
 * busca as fornecedores com auto-complete
 */
$("#fornecedor").autocomplete({
    source: function (request, response) {
        $.ajax({
            method: "GET",
            url: "/fornecedores/titulos",
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
$(document).ready(function() {
    moment.locale('pt-BR');
    var table = $('#table-produtos-lista').DataTable({
        searching : false,
        lengthMenu : [ 5, 10 ],
        processing : true,
        serverSide : true,
        responsive : true,
        order: [2, 'desc'],
        ajax : {
            url : '/produtos/datatables/server',
            data : 'data'
        },
        columns : [
            {data : 'id'},
            {data : 'nome'},
            {data: 'peso' },
            {data: 'laboratorio'},
            {data: 'origem.descricao' },
            {data: 'fornecedores.nome' },
            {orderable : false,	data : 'id', "render" : function(id) {
                    return '<a class="btn btn-success btn-sm btn-block" href="/consultas/editar/consulta/'
                            + id + '" role="button"><i class="fas fa-edit"></i></a>';
                }
            },
            {orderable : false,	data : 'id', "render" : function(id) {
                    return '<a class="btn btn-danger btn-sm btn-block" href="/consultas/excluir/consulta/'
                    + id +'" role="button" data-toggle="modal" data-target="#confirm-modal"><i class="fas fa-times-circle"></i></a>';
                }
            }
        ]
    });
});