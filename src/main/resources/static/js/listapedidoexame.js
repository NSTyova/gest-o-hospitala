/**
 * Datatable histórico de consultas
*/
$(document).ready(function() {
    moment.locale('pt-BR');
    var table = $('#table-listapedido-historico').DataTable({
        searching : true,
        lengthMenu : [ 5, 10 ],
        processing : true,
        serverSide : true,
        responsive : true,
        order: [2, 'desc'],
        ajax : {
            url : '/pedidoExames/datatables/server',
            data : 'data'
        },
        columns : [
            {data : 'id'},
            {data : 'paciente.nome'},
            {data : 'paciente.telefone'},
            {data: 'dataRegistro', render:
                function( dataConsulta ) {
                    return moment(dataConsulta).format('LL');
                }
            },
            {data : 'medico.nome'},
            {data : 'tipoExame.descricao'},
            {orderable : false,	data : 'id', "render" : function(id) {
                    return '<a class="btn btn-success btn-sm btn-block" href="/pedidoExames/editar/pedidoExame/'
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