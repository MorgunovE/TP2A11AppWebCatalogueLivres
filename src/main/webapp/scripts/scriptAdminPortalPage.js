document.addEventListener("DOMContentLoaded", function() {
    const urlParams = new URLSearchParams(window.location.search);
    const locale = urlParams.get('locale') || 'en_US';
    const enButton = document.querySelector('button[value="en_US"]');
    const frButton = document.querySelector('button[value="fr_FR"]');

    if (locale === 'en_US') {
        enButton.style.display = 'none';
    } else if (locale === 'fr_FR') {
        frButton.style.display = 'none';
    }

    const price = document.getElementById('price').value;
    const quantity = document.getElementById('quantity').value;

    if (price < 0) {
        alert('Price must be greater than or equal to 0');
        event.preventDefault();
    }

    if (quantity < 0) {
        alert('Quantity must be greater than or equal to 0');
        event.preventDefault();
    }
});