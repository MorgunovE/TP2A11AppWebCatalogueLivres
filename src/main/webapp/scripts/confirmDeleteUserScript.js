/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>

function confirmDeleteUserScript(id) {
    Swal.fire({
        title: 'Are you sure?',
        text: 'This action cannot be return!',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes!',
        cancelButtonText: 'No'
    }).then((result) => {
        if (result.isConfirmed) {
            // send ajax-requete for delete
            $.ajax({
                url: 'DeleteUserServlet',
                type: 'POST',
                data: { id: id },
                success: function(response) {
                    Swal.fire('Deleted!', 'User was deleted', 'success').then(() => {
                        location.reload(); // reload page after delete
                    });
                },
                error: function(error) {
                    Swal.fire('Error!', 'User delete is failed', 'error');
                }
            });
        }
    });
}

