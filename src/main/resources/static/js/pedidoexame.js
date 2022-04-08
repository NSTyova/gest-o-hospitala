/**
 * busca os pacientes com auto-complete
 */
$("#paciente").autocomplete({
    source: function (request, response) {
        $.ajax({
            method: "GET",
            url: "/pacientes/titulos",
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
 * busca as medico com auto-complete
 */
$("#med").autocomplete({
    source: function (request, response) {
        $.ajax({
            method: "GET",
            url: "/medicos/titulos",
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
 * busca os pacientes com auto-complete
 */
$("#tipoExame").autocomplete({
    source: function (request, response) {
        $.ajax({
            method: "GET",
            url: "/tipoExames/descricoes",
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
 * Datatable histórico de consultas
*/
$(document).ready(function() {
    moment.locale('pt-BR');
    var table = $('#table-funcionario-historico').DataTable({
        searching : false,
        lengthMenu : [ 5, 10 ],
        processing : true,
        serverSide : true,
        responsive : true,
        order: [2, 'desc'],
        ajax : {
            url : '/consultas/datatables/server/historico',
            data : 'data'
        },
        columns : [
            {data : 'id'},
            {data : 'paciente.nome'},
            {data : 'tipoExame.descricao'},
            {data: 'dataConsulta', render:
                function( dataConsulta ) {
                    return moment(dataConsulta).format('LLL');
                }
            },
            {data : 'medico.nome'},
            {data : 'especialidade.titulo'},
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