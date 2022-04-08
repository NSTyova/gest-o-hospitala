$(document).ready(function () {
	moment.locale('pt-BR');
    var table = $('#table-cliente').DataTable({
    	searching: true,
    	order: [[ 1, "asc" ]],
    	lengthMenu: [5, 10],
        processing: true,
        serverSide: true,
        responsive: true,
        ajax: {
            url: '/clientes/datatables/server',
            data: 'data'
        },
        columns: [
            {data: 'id'},
            {data: 'nome'},
            {data: 'telefone'},
            {data: 'sexo'},
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
