/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

<a href="#" onclick="confirmDelete(${livre.id})">Supprimer</a>
<script>
function confirmDelete(id) {
    Swal.fire({
        title: 'Sure?',
        text: 'Vous etes sur?',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Oui',
        cancelButtonText: 'Non'
    }).then((result) => {
        if (result.isConfirmed) {
            // send AJAX-request to server for delete livre
            $.ajax({
                url: '/deleteLivre',
                data: { id: id },
                type: 'POST',
                success: function() {
                    $(`tr[data-id="${id}"]`).remove();
                    // Update page or show alert success delete
                    location.reload();
                },
                error: function(jqXHR, textStatus, errorThrown) {
                Swal.fire({
                  icon: 'error',
                  title: 'Oops...',
                  text: 'Something is wrong'
                });
              }
            });
        }
    });
}